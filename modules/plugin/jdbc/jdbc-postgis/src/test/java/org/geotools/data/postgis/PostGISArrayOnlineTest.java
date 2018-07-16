/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2018, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.postgis;

import static org.junit.Assert.assertArrayEquals;

import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureStore;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.data.store.ContentFeatureCollection;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.jdbc.JDBCTestSetup;
import org.geotools.jdbc.JDBCTestSupport;
import org.geotools.util.Converters;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.Id;
import org.opengis.filter.identity.FeatureId;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PostGISArrayOnlineTest extends JDBCTestSupport {

    private static final Logger LOGGER = Logger.getLogger(PostGISArrayOnlineTest.class.getName());

    @Override
    protected JDBCTestSetup createTestSetup() {
        return new PostGISArrayTestSetup(new PostGISTestSetup());
    }

    @Test
    public void testWritable() throws Exception {
        ContentFeatureSource fs = dataStore.getFeatureSource(tname("arraytest"));
        assertTrue(fs instanceof FeatureStore);
    }

    @Test
    public void testRead() throws Exception {
        FilterFactory ff = dataStore.getFilterFactory();

        // check the non null array
        SimpleFeature first =
                getSingleArrayFeature(
                        ff.id(Collections.singleton(ff.featureId(tname("arraytest") + ".0"))));
        assertArrayEquals(new String[] {"A", "B"}, (String[]) first.getAttribute(aname("strings")));
        assertArrayEquals(new Integer[] {1, 2}, (Integer[]) first.getAttribute(aname("ints")));
        assertArrayEquals(new Double[] {3.4, 5.6}, (Double[]) first.getAttribute(aname("floats")));
        Timestamp date = Converters.convert("2009-06-28 15:12:41", Timestamp.class);
        assertArrayEquals(
                new Timestamp[] {date}, (Timestamp[]) first.getAttribute(aname("timestamps")));

        // check the one containing null values inside non null arrays
        SimpleFeature nullValues =
                getSingleArrayFeature(
                        ff.id(Collections.singleton(ff.featureId(tname("arraytest") + ".1"))));
        assertArrayEquals(
                new String[] {null, "C"}, (String[]) nullValues.getAttribute(aname("strings")));
        assertArrayEquals(
                new Integer[] {null, 3}, (Integer[]) nullValues.getAttribute(aname("ints")));
        assertArrayEquals(
                new Double[] {null, 7.8}, (Double[]) nullValues.getAttribute(aname("floats")));
        assertArrayEquals(
                new Timestamp[] {null, date},
                (Timestamp[]) nullValues.getAttribute(aname("timestamps")));

        // check the one containing null arrays
        SimpleFeature nullArrays =
                getSingleArrayFeature(
                        ff.id(Collections.singleton(ff.featureId(tname("arraytest") + ".2"))));
        assertNull(nullArrays.getAttribute(aname("strings")));
        assertNull(nullArrays.getAttribute(aname("ints")));
        assertNull(nullArrays.getAttribute(aname("floats")));
    }

    @Test
    public void testWrite() throws Exception {
        SimpleFeatureStore fs = (SimpleFeatureStore) dataStore.getFeatureSource(tname("arraytest"));

        // build new feature
        SimpleFeatureType schema = fs.getSchema();
        SimpleFeatureBuilder fb = new SimpleFeatureBuilder(schema);
        String[] stringArray = {null, "test"};
        fb.set("strings", stringArray);
        Integer[] intArray = {null, 1234};
        fb.set("ints", intArray);
        Double[] floatArray = {null, 123.4};
        fb.set("floats", floatArray);
        fb.set("timestamps", null);
        SimpleFeature feature = fb.buildFeature(null);

        List<FeatureId> ids = fs.addFeatures(DataUtilities.collection(feature));
        assertEquals(1, ids.size());

        // read back and check
        FilterFactory ff = dataStore.getFilterFactory();
        SimpleFeature read = getSingleArrayFeature(ff.id(Collections.singleton(ids.get(0))));
        assertArrayEquals(stringArray, (String[]) read.getAttribute(aname("strings")));
        assertArrayEquals(intArray, (Integer[]) read.getAttribute(aname("ints")));
        assertArrayEquals(floatArray, (Double[]) read.getAttribute(aname("floats")));
        assertNull(read.getAttribute(aname("timestamps")));
    }

    private SimpleFeature getSingleArrayFeature(Id filter) throws IOException {
        ContentFeatureSource fs = dataStore.getFeatureSource(tname("arraytest"));
        ContentFeatureCollection fc = fs.getFeatures(filter);
        return DataUtilities.first(fc);
    }
}
