/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2019, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.mbtiles;

import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultEngineeringCRS;
import org.geotools.util.logging.Logging;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MBTilesFeatureSource extends ContentFeatureSource {

    static final Logger LOGGER = Logging.getLogger(MBTilesFeatureSource.class);

    static final CoordinateReferenceSystem DEFAULT_CRS;

    static {
        CoordinateReferenceSystem crs;
        try {
            // try with web mercator
            crs = CRS.decode("EPSG:3857", true);
        } catch (FactoryException e) {
            LOGGER.log(
                    Level.WARNING,
                    "Could not initialize web mercator, geometry fields will use an engineering CRS",
                    e);
            crs = DefaultEngineeringCRS.GENERIC_2D;
        }
        DEFAULT_CRS = crs;
    }

    private static final int MAX_ATTEMPTS = 1000;
    private final VectorLayerMetadata layerMetadata;
    private final MBTilesFile mbtiles;

    public MBTilesFeatureSource(
            ContentEntry entry, VectorLayerMetadata layerMetadata, MBTilesFile mbtiles) {
        super(entry, null);
        this.layerMetadata = layerMetadata;
        this.mbtiles = mbtiles;
    }

    @Override
    protected ReferencedEnvelope getBoundsInternal(Query query) throws IOException {
        if (query.getFilter() == null || query.getFilter() == Filter.INCLUDE) {}

        return null;
    }

    @Override
    protected int getCountInternal(Query query) throws IOException {
        // no reasonable way to count quickly, each tile can contain a different number of features
        return -1;
    }

    @Override
    protected SimpleFeatureType buildFeatureType() throws IOException {
        SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
        tb.setName(getEntry().getName());
        LinkedHashMap<String, Class> fieldBindings = layerMetadata.getFieldBindings();
        fieldBindings.entrySet().forEach(e -> tb.add(e.getKey(), e.getValue()));
        String geometryName = guessGeometryName(fieldBindings.keySet());
        // TODO: make the CRS configurable
        // TODO: guess the geometry type somehow?
        tb.add(geometryName, Geometry.class, DEFAULT_CRS);

        return tb.buildFeatureType();
    }

    private String guessGeometryName(Set<String> attributeNames) {
        String geometryName = "the_geom";
        for (int i = 0; i < MAX_ATTEMPTS && attributeNames.contains(geometryName); i++) {
            geometryName = geometryName + i;
        }
        if (attributeNames.contains(geometryName)) {
            throw new RuntimeException(
                    "Unexpected, could not find a unique geometry name after appending the first "
                            + MAX_ATTEMPTS
                            + " integers to 'the_geom'");
        }
        return geometryName;
    }

    @Override
    protected FeatureReader<SimpleFeatureType, SimpleFeature> getReaderInternal(Query query)
            throws IOException {
        return null;
    }
}
