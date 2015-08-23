/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2005-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.referencing.operation.transform;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.jai.Warp;
import javax.media.jai.WarpAffine;
import javax.media.jai.WarpGrid;

import org.geotools.referencing.operation.matrix.XAffineTransform;
import org.geotools.util.logging.Logging;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;

/**
 * Builds {@link Warp} objects that approximate a specified {@link MathTransform} in a certain
 * rectangular domain within the specified tolerance
 * 
 * @author Andrea Aime - GeoSolutions
 *
 *
 * @source $URL$
 */
public class WarpBuilder {
    static final Logger LOGGER = Logging.getLogger(WarpBuilder.class);
    
    /**
     * A hint to dump grid to a property file for debugging and display purposes
     */
    static final boolean DUMP_GRIDS = Boolean.getBoolean("org.geotools.dump.warp.grid");
    
    /**
     * The max distance tolerated between the actual projected point and the approximate version
     * built by the optimized warp transform
     */
    final double tolerance;
    
        
    /**
     * The maximum number of positions in the warp grid, we don't want too large ones
     */
    int maxPositions = -1;

    /**
     * Creates a new warp builder
     */
    public WarpBuilder(double tolerance) {
        this.tolerance = tolerance; 
    }
    
        
    /**
     * Sets the maximum number of positions in the optimized grid, in case we have more we'll
     * fall back to the warp adapter. By default there is no limit
     * @param maxPositions
     */
    public void setMaxPositions(int maxPositions) {
       this.maxPositions = maxPositions;
    }

    /**
     * 
     * @param mt The math transform to be approximated
     * @param domain The domain in which the transform will be approximated
     * @param tolerance
     * @return
     */
    public Warp buildWarp(MathTransform2D mt, Rectangle domain) throws TransformException {
        // first simple case, the tx is affine
        if(mt instanceof AffineTransform2D) {
            return new WarpAffine((AffineTransform2D) mt);
        }
        
        // second simple case, the caller does not want any optimization
        if (tolerance == 0) {
            return new WarpAdapter(null, mt);
        }
        
        // get the bounds and perform sanity check
        final double minx = domain.getMinX();
        final double maxx = domain.getMaxX();
        final double miny = domain.getMinY();
        final double maxy = domain.getMaxY();
        final int width = (int) (maxx - minx);
        final int heigth = (int) (maxy - miny);
        if(abs(width) == 0 || heigth == 0) {
            throw new IllegalArgumentException("The domain is empty!");
        }
        
        /* 
         * Prepare to build a warp grid. A warp grid requires a set of uniform cells, but the
         * number of rows and cols may differ. The following method will drill down using a 
         * recursive division algorithm to find the optimal number of divisions along the
         * x and y axis
         */
        int[] rowCols;
        try {
            rowCols = computeOptimalDepths(mt, minx, maxx, miny, maxy, 0, 0);
        } catch (Exception e) {
            return new WarpAdapter(null, mt);
        }
        
        if(rowCols[0] == 0 && rowCols[1] == 0) {
            // we can use an affine transformation
            
            // the transformation is flat enough that we can use an affine transform
            // build the affine transform from the deltas over the whole window, since
            // this is how we evaluated the tolerance respect in computeOptimalDepths,
            // from the lower left corner to the others
            final double[] ordinates = new double[6];
            ordinates[0] = minx;
            ordinates[1] = miny;
            ordinates[2] = minx;
            ordinates[3] = maxy;
            ordinates[4] = maxx;
            ordinates[5] = miny;
            mt.transform(ordinates, 0, ordinates, 0, 3);
            
            
            // build the affine transform coefficients
            final double m00 = (ordinates[4] - ordinates[0]) / width;
            final double m10 = (ordinates[5] - ordinates[1]) / width;
            final double m01 = (ordinates[2] - ordinates[0]) / heigth;
            final double m11 = (ordinates[3] - ordinates[1]) / heigth;
            final double m02 = ordinates[0]; 
            final double m12 = ordinates[1];
            
            AffineTransform at = new AffineTransform(m00, m10, m01, m11, m02, m12);
            at.translate(-minx, -miny);
            XAffineTransform.round(at, 1e-6);
            LOGGER.log(Level.FINE, "Optimizing the warp into an affine transformation: {0}", at);
            return new WarpAffine(at);
        } else {
            // unfortunately the steps are integers, meaning we won't fit the grid exactly with them
            int stepx = (int) (width / pow(2, rowCols[1])); 
            int stepy = (int) (heigth / pow(2, rowCols[0]));

            // compute rows and cols
            int cols = width / stepx ;
            int rows = heigth / stepy;
            int cmax = (int) (minx + cols * stepx);
            int rmax = (int) (miny + rows * stepy);
            
            // in case we would end up generating a too large grid we fall back to the
            // warp adapter
            if (maxPositions > 0 && cols * rows > maxPositions) {
                LOGGER.log(Level.FINE, "Bailing out to WarpAdapter, the number of rows and col " +
                		"grew too much, rows: " + rows + " and cols: " + cols);
                return new WarpAdapter(null, mt);
            }
            
            // due to integer rounding we might not be covering the entire raster with the grid,
            // if so compensate by either adding a row/column or by adding a pixel to the step
            // whatever makes the resulting grid be the smallest)
            if(cmax < maxx) {
                // use the better match between adding a column and adding a pixel to the step
                if((cmax + stepx) < cols * (stepx + 1)) {
                    cmax += stepx;
                    cols++;
                } else {
                    stepx++;
                    cmax = (int) (minx + cols * stepx); 
                }
            }
            if(rmax < maxy) {
                // use the better match between adding a row and adding a pixel to the step
                if((rmax + stepy) < rows * (stepy + 1)) {
                    rmax += stepy;
                    rows++;
                } else {
                    stepy++;
                    rmax = (int) (miny + rows * stepy);
                }
            }

            // fill in the original grid
            final float[] warpPositions = new float[(rows + 1) * (cols + 1) * 2];
            int r = (int) miny;
            int idx = 0;
            while(r <= rmax) {
                int c = (int) minx;
                while(c <= cmax) {
                    // make sure we don't go out of the original domain
                    // as doing that might result in getting outside of the projection
                    // validity area, or crossing the dateline/poles
                    warpPositions[idx++] = (float) Math.min(c, maxx);
                    warpPositions[idx++] = (float) Math.min(r, maxy);
                    c += stepx;
                }
                r += stepy;
            }
            if(DUMP_GRIDS) {
                dumpPropertyFile(warpPositions, "original");
            }
            
            // transform it to target
            mt.transform(warpPositions, 0, warpPositions, 0, warpPositions.length / 2);
            if(DUMP_GRIDS) {
                dumpPropertyFile(warpPositions, "transformed");
            }
            
            LOGGER.log(Level.FINE, "Optimizing the warp into an grid warp {0} x {1}", new Object[] {rows, cols});
            
            return new WarpGrid((int) minx, stepx, cols, (int) miny, stepy, rows, warpPositions);
        }
    }

    
    
    /**
     * A debugging aid that dumps the grids to a file
     * @param points
     * @param name
     */
    void dumpPropertyFile(float[] points, String name) {
        long start = System.currentTimeMillis();
        
        BufferedWriter writer = null;
        try {
            File output  = File.createTempFile(start + name, ".properties");
            writer = new BufferedWriter(new FileWriter(output));
            writer.write("_=geom:Point:srid=32632"); writer.newLine();
            for (int i = 0; i < points.length; i+=2) {
                writer.write("p." + (i / 2) + "=POINT(" + points[i] + " " + points[i + 1] + ")");
                writer.newLine();
            }
            LOGGER.info(name + " dumped as " + output.getName());
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to dump points: " + e.getMessage(), e);
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
