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

import java.io.IOException;
import java.util.NoSuchElementException;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

class MBTilesFeatureReader implements SimpleFeatureReader {

    private final MBTilesFile.TileIterator tiles;
    private final SimpleFeatureType schema;
    private final MBtilesCache cache;
    private SimpleFeatureIterator currentIterator;

    public MBTilesFeatureReader(
            MBTilesFile.TileIterator tiles, SimpleFeatureType schema, MBtilesCache cache) {
        this.tiles = tiles;
        this.schema = schema;
        this.cache = cache;
    }

    @Override
    public SimpleFeatureType getFeatureType() {
        return schema;
    }

    @Override
    public SimpleFeature next()
            throws IOException, IllegalArgumentException, NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return currentIterator.next();
    }

    @Override
    public boolean hasNext() throws IOException {
        if (currentIterator != null && currentIterator.hasNext()) {
            return true;
        }

        while (tiles.hasNext()) {
            SimpleFeatureCollection features =
                    cache.getFeatures(tiles.next(), schema.getTypeName());
            // was the layer not found in the tile? Can happen, some layers show up only at certain
            // zoom levels
            if (features == null) {
                continue;
            }
            currentIterator = features.features();
            if (currentIterator != null && currentIterator.hasNext()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void close() throws IOException {
        tiles.close();
    }
}
