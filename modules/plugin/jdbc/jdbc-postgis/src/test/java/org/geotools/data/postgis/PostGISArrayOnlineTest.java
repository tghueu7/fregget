/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2017, Open Source Geospatial Foundation (OSGeo)
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
import org.geotools.data.store.ContentFeatureCollection;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.jdbc.JDBCTestSetup;
import org.geotools.jdbc.JDBCTestSupport;
import org.geotools.util.Converters;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.Id;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.logging.Logger;

public class PostGISArrayOnlineTest extends JDBCTestSupport {

    private static final Logger LOGGER = Logger.getLogger(PostGISArrayOnlineTest.class.getName());

    @Override
    protected JDBCTestSetup createTestSetup() {
        return new PostGISArrayTestSetup();
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
        
        // check the one containing nulls
        SimpleFeature nulls =
                getSingleArrayFeature(
                        ff.id(Collections.singleton(ff.featureId(tname("arraytest") + ".1"))));
        assertNull(nulls.getAttribute(aname("strings")));
        assertNull(nulls.getAttribute(aname("ints")));
        assertNull(nulls.getAttribute(aname("floats")));
    }

    private SimpleFeature getSingleArrayFeature(Id filter) throws IOException {
        ContentFeatureSource fs = dataStore.getFeatureSource(tname("arraytest"));
        ContentFeatureCollection fc = fs.getFeatures(filter);
        return DataUtilities.first(fc);
    }
}
