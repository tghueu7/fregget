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

import org.geotools.jdbc.JDBCDelegatingTestSetup;

import java.util.logging.Logger;

public class PostGISArrayTestSetup extends JDBCDelegatingTestSetup {

    private static final Logger LOGGER = Logger.getLogger(PostGISArrayTestSetup.class.getName());

    protected boolean hasException = false;

    static boolean LOGGED = false;

    protected PostGISArrayTestSetup() {
        super(new PostGISTestSetup());
    }

    @Override
    protected void setUpData() throws Exception {
        dropTestArrayTable();
        createTestArrayTable();
    }

    private void createTestArrayTable() throws Exception {

        String sql =
                "CREATE TABLE \"arraytest\" ("
                        + "\"id\" INT, \"strings\" VARCHAR[], \"ints\" int[], \"floats\" float[], \"timestamps\" TIMESTAMP[], PRIMARY KEY(id))";
        run(sql);

        run(
                "INSERT INTO \"arraytest\" VALUES (0, ARRAY['A','B'], ARRAY[1,2], ARRAY[3.4, 5.6], ARRAY[TO_TIMESTAMP('2009-06-28 15:12:41', 'yyyy-MM-dd HH24:mi:ss')])");
        run("INSERT INTO \"arraytest\" VALUES (1, NULL, NULL, NULL, NULL)");
    }

    private void dropTestArrayTable() throws Exception {
        runSafe("DROP TABLE \"arraytest\" cascade");
    }
}
