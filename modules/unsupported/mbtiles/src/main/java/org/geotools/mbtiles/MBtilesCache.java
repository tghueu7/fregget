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
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.GeometryClipper;
import org.geotools.util.SoftValueHashMap;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import no.ecc.vectortile.VectorTileDecoder;

/** Caches MBTiles in their parsed and clipped form, to avoid re-parsing the tiles over and over. */
public class MBtilesCache {

    SoftValueHashMap<MBTilesTileLocation, Map<String, SimpleFeatureCollection>> cache =
            new SoftValueHashMap<>(0);
    Map<String, SimpleFeatureType> schemas = new HashMap<>();
    
    public MBtilesCache(Map<String, SimpleFeatureType> schemas) {
        this.schemas = schemas;
    }

    public SimpleFeatureCollection getFeatures(MBTilesTile tile, String layerName)
            throws IOException {
        Map<String, SimpleFeatureCollection> layers = cache.get(tile);
        if (layers == null) {
            System.out.println("Miss for " + tile);
            layers = fillCache(tile);
            cache.put(tile, layers);
        }
        return layers.get(layerName);
    }

    private Map<String, SimpleFeatureCollection> fillCache(MBTilesTile tile) throws IOException {
        VectorTileDecoder decoder = new VectorTileDecoder();
        decoder.setAutoScale(false);

        byte[] gzippedData = getPbfFromTile(tile);
        Map<String, LayerFeatureBuilder> builders = new HashMap<>();
        for (VectorTileDecoder.Feature mvtFeature : decoder.decode(gzippedData)) {
            String layer = mvtFeature.getLayerName();
            // skip unknown layers, as a safety measure
            if (schemas.get(layer) == null) {
                continue;
            }
            LayerFeatureBuilder builder =
                    builders.computeIfAbsent(
                            layer, l -> new LayerFeatureBuilder(tile, schemas.get(layer)));
            builder.addFeature(mvtFeature);
        }

        // remap to a map from names to collections
        return builders.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), v -> v.getValue().result));
    }

    private byte[] getPbfFromTile(MBTilesTile tile) throws IOException {
        // from spec, the MVT contents are g-zipped
        byte[] raw = tile.getData();
        return IOUtils.toByteArray(new GZIPInputStream(new ByteArrayInputStream(raw)));
    }

    /**
     * Converts MVT screen features into GeoTools geograhic features, accumulating them in a feature
     * collection
     */
    private static class LayerFeatureBuilder {

        private final String featureIdPrefix;
        private final ListFeatureCollection result;
        private final SimpleFeatureBuilder builder;
        private final MBTilesTile tile;
        private final String geometryName;
        private final SimpleFeatureType schema;
        private GeometryProcessor processor;

        LayerFeatureBuilder(MBTilesTile tile, SimpleFeatureType schema) {
            this.geometryName = schema.getGeometryDescriptor().getLocalName();
            String typeName = schema.getTypeName();
            this.featureIdPrefix =
                    typeName
                            + "."
                            + tile.getZoomLevel()
                            + "."
                            + tile.getTileRow()
                            + "."
                            + tile.getTileColumn()
                            + ".";
            this.schema = schema;
            this.builder = new SimpleFeatureBuilder(schema);
            this.result = new ListFeatureCollection(schema);
            this.tile = tile;
        }

        void addFeature(VectorTileDecoder.Feature mvtFeature) {
            Geometry geometry = getGeometry(tile, mvtFeature);
            // geometry might have been fully outside boundaries
            if (geometry == null) {
                return;
            }
            builder.set(geometryName, geometry);
            // collect all the attributes
            Map<String, Object> attributes = mvtFeature.getAttributes();
            for (AttributeDescriptor ad : schema.getAttributeDescriptors()) {
                String attributeName = ad.getLocalName();
                Object value = attributes.get(attributeName);
                if (value != null) {
                    builder.set(attributeName, value);
                }
            }

            SimpleFeature feature = builder.buildFeature(featureIdPrefix + mvtFeature.getId());

            // todo: handle the un-finished features
            result.add(feature);
        }

        private Geometry getGeometry(MBTilesTile tile, VectorTileDecoder.Feature mvtFeature) {
            Geometry screenGeometry = mvtFeature.getGeometry();
            int extent = mvtFeature.getExtent();
            if (this.processor == null || processor.extent != extent) {
                this.processor = new GeometryProcessor(tile, extent);
            }

            return processor.process(screenGeometry);
        }
    }

    /**
     * Processes a screen space MBTiles geometry, clipping it to the tile boundaries, and turning it
     * into a geographic space one.
     */
    private static class GeometryProcessor {
        final AffineTransformation at;
        private final GeometryClipper clipper;
        MBTilesTile tile;
        int extent;

        GeometryProcessor(MBTilesTile tile, int extent) {
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
                            Math.pow(
                                    2,
                                    tile.getZoomLevel())); // number of tile columns/rows for chosen
            // zoom level
            double resX = WORLD_ENVELOPE.getSpan(0) / numberOfTiles; // points per tile
            double resY = WORLD_ENVELOPE.getSpan(1) / numberOfTiles; // points per tile
            double offsetX = WORLD_ENVELOPE.getMinimum(0);
            double offsetY = WORLD_ENVELOPE.getMinimum(1);

            double tx = offsetX + tile.getTileColumn() * resX;
            double ty = offsetY + (tile.getTileRow() + 1) * resY;
            double mx = resX / extent;
            double my = resY / extent;

            // affine transformation
            this.at =
                    new AffineTransformation() {
                        @Override
                        public void filter(CoordinateSequence seq, int i) {
                            // java-vector-tile uses the exact same Coordinate object for first and
                            // last
                            // element of a ring, but we don't want to transform it twice
                            if (seq instanceof CoordinateSequence
                                    && i > 0 // don't consider points
                                    && i == seq.size() - 1
                                    && seq.getCoordinate(0) == seq.getCoordinate(i)) {
                                return;
                            }
                            super.filter(seq, i);
                        }
                    };
            at.setToScale(mx, -my);
            at.translate(tx, ty);

            // and clipper in screen coordinates
            this.clipper = new GeometryClipper(new Envelope(0, extent, 0, extent));
        }

        /**
         * Clips the geometry to the tile extents and rescales it to real world coordinates
         *
         * @param screenGeometry the input geometry
         * @return the rescaled geometry, might have happened either in place, so the returned
         *     geometry is the same object as the screen geometry, or could be a different object
         */
        Geometry process(Geometry screenGeometry) {
            Geometry clipped = clipper.clipSafe(screenGeometry, false, 1);
            if (clipped == null) {
                return null;
            }
            clipped.apply(at);

            return clipped;
        }
    }
}
