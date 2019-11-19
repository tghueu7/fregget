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

import static org.geotools.mbtiles.MBTilesFile.WORLD_ENVELOPE;

import org.apache.commons.io.IOUtils;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureReader;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.zip.GZIPInputStream;

import no.ecc.vectortile.VectorTileDecoder;

public class MBTilesFeatureReader implements SimpleFeatureReader {

    private final MBTilesFile.TileIterator tiles;
    private final SimpleFeatureType schema;
    private final SimpleFeatureBuilder builder;
    private SimpleFeatureIterator currentIterator;

    public MBTilesFeatureReader(MBTilesFile.TileIterator tiles, SimpleFeatureType schema, long z) {
        this.tiles = tiles;
        this.schema = schema;
        this.builder = new SimpleFeatureBuilder(schema);
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
            SimpleFeatureCollection features = toFeatures(tiles.next());
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

    private SimpleFeatureCollection toFeatures(MBTilesTile tile) throws IOException {
        VectorTileDecoder decoder = new VectorTileDecoder();
        decoder.setAutoScale(false);
        String geometryName = schema.getGeometryDescriptor().getLocalName();
        String typeName = schema.getTypeName();
        ListFeatureCollection result = new ListFeatureCollection(schema);

        byte[] gzippedData = getPbfFromTile(tile);

        for (VectorTileDecoder.Feature mvtFeature : decoder.decode(gzippedData, typeName)) {
            Geometry geometry = getGeometry(tile, mvtFeature);
            builder.set(geometryName, geometry);
            Map<String, Object> attributes = mvtFeature.getAttributes();
            for (AttributeDescriptor ad : schema.getAttributeDescriptors()) {
                String attributeName = ad.getLocalName();
                Object value = attributes.get(attributeName);
                if (value != null) {
                    builder.set(attributeName, value);
                }
            }
            SimpleFeature feature = builder.buildFeature(typeName + mvtFeature.getId());

            // todo: handle the un-finished features
            result.add(feature);
        }

        return result;
    }

    private Geometry getGeometry(MBTilesTile tile, VectorTileDecoder.Feature mvtFeature) {
        Geometry screenGeometry = mvtFeature.getGeometry();
        int extent = mvtFeature.getExtent();

        // This needs an explaination... the tiles follow the TMS spec, so they go from south to
        // north, however the geometry inside the tile uses screen coordinate system, so top to
        // bottom instead. Confused? Here are excerpts from the spec:

        // The tiles table contains tiles and the values used to locate them. The zoom_level,
        // tile_column, and tile_row columns MUST encode the location of the tile, following the
        // Tile Map // Service Specification, with the restriction that the global-mercator (aka
        // Spherical Mercator) profile MUST be used.

        // Geometry data in a Vector Tile is defined in a screen coordinate system.
        // The upper left corner of the tile (as displayed by default) is the origin of the
        // coordinate system.
        // The X axis is positive to the right, and the Y axis is positive downward.
        long numberOfTiles =
                Math.round(
                        Math.pow(2, tile.getZoomLevel())); // number of tile columns/rows for chosen
        // zoom level
        double resX = WORLD_ENVELOPE.getSpan(0) / numberOfTiles; // points per tile
        double resY = WORLD_ENVELOPE.getSpan(1) / numberOfTiles; // points per tile
        double offsetX = WORLD_ENVELOPE.getMinimum(0);
        double offsetY = WORLD_ENVELOPE.getMinimum(1);

        double tx = offsetX + tile.getTileColumn() * resX;
        double ty = offsetY + (tile.getTileRow() + 1) * resY;
        double mx = resX / extent;
        double my = resY / extent;

        AffineTransformation at = new AffineTransformation();
        at.setToScale(mx, -my);
        at.translate(tx, ty);
        screenGeometry.apply(at);

        return screenGeometry;
    }

    private byte[] getPbfFromTile(MBTilesTile tile) throws IOException {
        // from spec, the MVT contents are g-zipped
        byte[] raw = tile.getData();
        return IOUtils.toByteArray(new GZIPInputStream(new ByteArrayInputStream(raw)));
    }
}
