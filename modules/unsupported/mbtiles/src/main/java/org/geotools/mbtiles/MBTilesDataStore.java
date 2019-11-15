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

import org.geotools.data.DataSourceException;
import org.geotools.data.store.ContentDataStore;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.NameImpl;
import org.opengis.feature.type.Name;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MBTilesDataStore extends ContentDataStore {

    MBTilesFile mbtiles;
    LinkedHashMap<String, VectorLayerMetadata> layers;

    public MBTilesDataStore(MBTilesFile mbtiles) throws IOException {
        this.mbtiles = mbtiles;
        MBTilesMetadata metadata = mbtiles.loadMetaData();
        if (!MBTilesMetadata.t_format.PBF.equals(metadata.getFormat())) {
            throw new DataSourceException(
                    "Expected 'PBF' as the format, but found " + metadata.getFormat());
        }
        if (metadata.getJson() == null) {
            throw new DataSourceException(
                    "Cannot find 'json' metadata field, required to load the layers and their structure");
        }
        try {
            VectorLayersMetadata vectorLayersMetadata =
                    VectorLayersMetadata.parseMetadata(metadata.getJson());
            layers = vectorLayersMetadata.getLayersMap();
        } catch (Exception e) {
            throw new DataSourceException(
                    "Could not parse the 'json' metadata field, failed to initialize the store", e);
        }
    }

    @Override
    protected List<Name> createTypeNames() throws IOException {
        return layers.keySet().stream()
                .map(id -> new NameImpl(getNamespaceURI(), id))
                .collect(Collectors.toList());
    }

    @Override
    protected ContentFeatureSource createFeatureSource(ContentEntry entry) throws IOException {
        VectorLayerMetadata layerMetadata = layers.get(entry.getTypeName());
        return new MBTilesFeatureSource(entry, layerMetadata, mbtiles);
    }
}
