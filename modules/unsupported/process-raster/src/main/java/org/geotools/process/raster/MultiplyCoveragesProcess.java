/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011-2015, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2008-2011 TOPP - www.openplans.org.
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
package org.geotools.process.raster;

import java.util.ArrayList;
import java.util.List;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.processing.CoverageProcessor;
import org.geotools.process.ProcessException;
import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.ProgressListener;

/**
 * Multiply two coverages together (pixel by pixel). Output pixel[i][j] = source0CoveragePixel[i][j]
 * * source1CoveragePixel[i][j]
 *
 * <p>The two coverages need to have the same envelope and same resolution (same gridGeometry).
 *
 * @author Daniele Romagnoli - GeoSolutions
 */
@DescribeProcess(
    title = "Multiply Coverages",
    description =
            "Returns a raster generated by pixel-by-pixel multiplication of two source rasters.  Source rasters must have the same bounding box and resolution."
)
public class MultiplyCoveragesProcess implements RasterProcess {

    private static final CoverageProcessor PROCESSOR = CoverageProcessor.getInstance();

    @DescribeResult(name = "result", description = "Computed raster")
    public GridCoverage2D execute(
            @DescribeParameter(name = "coverageA", description = "First input raster")
                    GridCoverage2D coverageA,
            @DescribeParameter(name = "coverageB", description = "Second input raster")
                    GridCoverage2D coverageB,
            ProgressListener progressListener)
            throws ProcessException {

        // //
        //
        // Initialization: compatibility checks
        //
        // //
        BaseCoverageAlgebraProcess.checkCompatibleCoverages(coverageA, coverageB);

        // //
        //
        // Doing the Operation
        //
        // //
        final ParameterValueGroup param = PROCESSOR.getOperation("Multiply").getParameters();
        List<GridCoverage2D> sources = new ArrayList<GridCoverage2D>();
        sources.add(coverageA);
        sources.add(coverageB);
        param.parameter("Sources").setValue(sources);
        return (GridCoverage2D) PROCESSOR.doOperation(param);
    }
}
