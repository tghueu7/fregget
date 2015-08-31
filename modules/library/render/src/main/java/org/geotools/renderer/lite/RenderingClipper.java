/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2015, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.renderer.lite;

import java.awt.Rectangle;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.geometry.jts.GeometryClipper;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.referencing.operation.transform.ConcatenatedTransform;
import org.geotools.renderer.style.Style2D;
import org.geotools.util.logging.Logging;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.util.PolygonExtracter;

class RenderingClipper {

    static final Logger LOGGER = Logging.getLogger(RenderingClipper.class);

    MathTransform screenToSourceCRS;

    CoordinateReferenceSystem sourceCRS;

    CoordinateReferenceSystem renderingCRS;

    int metaBuffer;

    Rectangle screenSize;

    GeometryClipper baselineClipper;

    private MathTransform srcToWGS84;

    private MathTransform renderingToWgs84;

    public RenderingClipper(Rectangle screenSize, MathTransform screenToWorld,
            CoordinateReferenceSystem renderingCRS, CoordinateReferenceSystem sourceCRS,
            int metaBuffer) throws FactoryException {
        this.renderingCRS = renderingCRS;
        this.sourceCRS = sourceCRS;
        this.screenSize = screenSize;
        this.screenToSourceCRS = ConcatenatedTransform.create(screenToWorld,
                CRS.findMathTransform(renderingCRS, sourceCRS, true));
        this.srcToWGS84 = CRS.findMathTransform(sourceCRS, DefaultGeographicCRS.WGS84);
        this.renderingToWgs84 = CRS.findMathTransform(renderingCRS, DefaultGeographicCRS.WGS84);
        this.metaBuffer = metaBuffer;
        try {
            this.baselineClipper = buildClipper(metaBuffer);
        } catch (TransformException e) {
            LOGGER.log(Level.FINE,
                    "Failed to build base geometry clipper, will continue with  a per feature clipper instead",
                    e);
        }
    }

    public Geometry clip(Geometry geometry, Style2D style) {
        double size = RendererUtilities.getStyle2DSize(style);
        GeometryClipper clipper = null;
        if (size / 2 > metaBuffer) {
            try {
                if (baselineClipper != null) {
                    clipper = buildClipper(size / 2);
                }
            } catch (Exception e) {
                LOGGER.log(Level.FINE,
                        "Failed to build local clipper, will continue with a local geometry clipper instead");
            }
        } else {
            clipper = baselineClipper;
        }

        if (clipper == null) {
            // if we failed to build the clipper, or the baseline clipper could not be built
            // to start with, sign of a problematic area, we perform the intersection
            // in WGS84
            if (clipper == null) {
                try {
                    // go from geometry bounds to wgs84 (important to use the bounds, the geometry
                    // might already be invalid
                    Geometry srcBoundsWGS84 = JTS.transform(geometry.getEnvelope(), srcToWGS84);

                    // go from expanded rendering bounds to wgs84
                    Envelope env = new Envelope(screenSize.getMinX(), screenSize.getMaxX(),
                            screenSize.getMinY(), screenSize.getMaxY());
                    env.expandBy(Math.max(size / 2, metaBuffer));
                    Polygon renderingArea = JTS.toGeometry(env);

                    // intersect the two in wgs84
                    Geometry renderingBoundsWGS84 = JTS.transform(renderingArea, renderingToWgs84);
                    List polygons = PolygonExtracter
                            .getPolygons(renderingBoundsWGS84.intersection(srcBoundsWGS84));
                    if (polygons.isEmpty()) {
                        return null;
                    }

                    // get the intersection and go back to wgs84
                    Polygon intersection = (Polygon) polygons.get(0);
                    Geometry sourceClipBounds = JTS.transform(intersection, srcToWGS84.inverse());

                    clipper = new GeometryClipper(sourceClipBounds.getEnvelopeInternal());
                } catch (Exception e) {
                    LOGGER.log(Level.FINE,
                            "Failed to build local geometry clipper, will return the full geometry instead");
                }

            }
        }

        if (clipper == null) {
            return geometry;
        } else {
            return clipper.clip(geometry, false);
        }
    }

    private GeometryClipper buildClipper(double clipBuffer) throws TransformException {
        ReferencedEnvelope env = new ReferencedEnvelope(screenSize.getMinX(), screenSize.getMaxX(),
                screenSize.getMinY(), screenSize.getMaxY(), null);
        env.expandBy(clipBuffer);
        ReferencedEnvelope transformed = ReferencedEnvelope
                .reference(CRS.transform(screenToSourceCRS, env));

        GeometryClipper clipper = new GeometryClipper(transformed);
        return clipper;
    }

}
