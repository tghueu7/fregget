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

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.geotools.data.store.ContentFeatureSource;
import org.geotools.referencing.CRS;
import org.geotools.util.URLs;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.referencing.FactoryException;

import java.io.File;
import java.io.IOException;

public class MBTilesDataStoreTest {

    private static final String DATATYPES = "datatypes";

    @Test
    public void testDataTypes() throws IOException, FactoryException {
        File file =
                URLs.urlToFile(MBTilesFileVectorTileTest.class.getResource("datatypes.mbtiles"));
        MBTilesDataStore store = new MBTilesDataStore(new MBTilesFile(file));
        assertThat(store.getTypeNames(), arrayContaining("datatypes"));

        ContentFeatureSource fs = store.getFeatureSource(DATATYPES);
        assertNotNull(fs);

        // check the schema
        SimpleFeatureType schema = fs.getSchema();
        assertThat(schema.getTypeName(), equalTo(DATATYPES));

        System.out.println(schema.getAttributeDescriptors());

        // the default geometry
        GeometryDescriptor geom = schema.getGeometryDescriptor();
        assertThat(geom.getLocalName(), equalTo("the_geom"));
        assertThat(geom.getCoordinateReferenceSystem(), equalTo(CRS.decode("EPSG:3857", true)));

        // check boolean
        AttributeDescriptor bd = schema.getDescriptor("bool_true");
        assertThat(bd.getType().getBinding(), equalTo(Boolean.class));
        
        // check number (there is no distinction of subtypes, everything is "Number"
        AttributeDescriptor fd = schema.getDescriptor("float_value");
        assertThat(fd.getType().getBinding(), equalTo(Double.class));

        AttributeDescriptor sd = schema.getDescriptor("string_value");
        assertThat(sd.getType().getBinding(), equalTo(String.class));
    }
}
