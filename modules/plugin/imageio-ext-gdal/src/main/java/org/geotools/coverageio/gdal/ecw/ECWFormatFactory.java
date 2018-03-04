/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2007-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.coverageio.gdal.ecw;

import it.geosolutions.imageio.plugins.ecw.ECWImageReaderSpi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.coverage.grid.io.GridFormatFactorySpi;
import org.geotools.coverageio.BaseGridFormatFactorySPI;
import org.opengis.coverage.grid.Format;


/**
 * Implementation of the {@link Format} service provider interface for ECW
 * files.
 *
 * @author Daniele Romagnoli, GeoSolutions
 * @author Simone Giannecchini (simboss), GeoSolutions
 * @source $URL$
 * @since 2.5.x
 */
public final class ECWFormatFactory extends BaseGridFormatFactorySPI implements 
        GridFormatFactorySpi {
    /**
     * Logger.
     */
    private final static Logger LOGGER = org.geotools.util.logging.Logging.getLogger(
            "org.geotools.coverageio.gdal.ecw");

    /**
     * Tells me if the coverage plugin to access ECW is available or not.
     *
     * @return {@code true} if the plugin is available, {@code false} otherwise.
     */
    public boolean isAvailable() {
        boolean available = true;

        // if these classes are here, then the runtime environment has
        // access to JAI and the JAI ImageI/O toolbox.
        try {
            Class.forName("it.geosolutions.imageio.plugins.ecw.ECWImageReaderSpi");
            available = new ECWImageReaderSpi().isAvailable();

            if (LOGGER.isLoggable(Level.FINE)) {
                if (available)
                    LOGGER.fine("ECWFormatFactory is availaible.");
                else
                    LOGGER.fine("ECWFormatFactory is not availaible.");
            }
        } catch (ClassNotFoundException cnf) {
            if (LOGGER.isLoggable(Level.FINE))
                LOGGER.fine("ECWFormatFactory is not availaible.");

            available = false;
        }

        return available;
    }

    /**
     * Creating a {@link ECWFormat}
     *
     * @return A {@link ECWFormat}
     */
    public ECWFormat createFormat() {
        return new ECWFormat();
    }
}
