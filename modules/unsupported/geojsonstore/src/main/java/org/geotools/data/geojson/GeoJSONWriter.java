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
package org.geotools.data.geojson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.PropertyType;

/**
 * Wrapper to handle writing GeoJSON FeatureCollections
 *
 * @author ian
 */

public class GeoJSONWriter implements AutoCloseable {
    private FeatureJSON writer = new FeatureJSON();
    private OutputStream out;

    org.locationtech.jts.io.geojson.GeoJsonWriter jWriter =
            new org.locationtech.jts.io.geojson.GeoJsonWriter();

    JsonGenerator generator;

    public GeoJSONWriter(OutputStream outputStream) throws IOException {

        jWriter.setEncodeCRS(false);
        if (outputStream instanceof BufferedOutputStream) {
            this.out = outputStream;
        } else {
            this.out = new BufferedOutputStream(outputStream);
        }
        JsonFactory factory = new JsonFactory();
        generator = factory.createGenerator(outputStream);
        generator.writeStartObject();
        generator.writeStringField("type", "FeatureCollection");
        generator.writeFieldName("features");
        generator.writeStartArray();
    }

    public void write(SimpleFeature currentFeature) throws IOException {
        // System.out.println("JSONWriter writing " + currentFeature.getID());
        generator.writeStartObject();
        generator.writeStringField("type", "Feature");
        generator.writeFieldName("properties");
        generator.writeStartObject();
        for (Property p : currentFeature.getProperties()) {
            PropertyType type = p.getType();
            if (type instanceof GeometryType) {
                continue;
            }
            Object value = p.getValue();
            String name = p.getName().getLocalPart();
            if (value == null) {
                generator.writeNullField(name);
                continue;
            }
            Class<?> binding = p.getType().getBinding();
            if (binding == Integer.class) {
                generator.writeNumberField(name, (int) value);
            } else if (binding == Double.class) {
                generator.writeNumberField(name, (double) value);
            } else {
                generator.writeFieldName(name);
                generator.writeString(value.toString());
            }
        }
        generator.writeEndObject();

        generator.writeFieldName("geometry");
        Geometry defaultGeometry = (Geometry) currentFeature.getDefaultGeometry();
        if (defaultGeometry != null) {
            String gString = jWriter.write(defaultGeometry);

            generator.writeRawValue(gString);
            generator.writeStringField("id", currentFeature.getID());
        } else {
            generator.writeNull();
        }
        generator.writeEndObject();
        generator.flush();
        // TODO: remove for speed later
        out.flush();
    }

    @Override
    public void close() throws IOException {
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        out.close();
    }
}
