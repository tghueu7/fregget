/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.jdbc;

import java.sql.SQLException;

public abstract class JDBCBooleanTestSetup extends JDBCDelegatingTestSetup {

    protected JDBCBooleanTestSetup(JDBCTestSetup delegate) {
        super(delegate);
    }

    @Override
    protected final void setUpData() throws Exception {
        // kill all the data
        try {
            dropBooleanTable();
        } catch (SQLException e) {
        }

        // create all the data
        createBooleanTable();
    }

    /**
     * Creates a table with the following schema:
     *
     * <p>b( id:Integer; boolProperty: Boolean)
     *
     * <p>The table should contain the following data. 0 | false 1 | true
     */
    protected abstract void createBooleanTable() throws Exception;

    /** Drops the "b" table previously created */
    protected abstract void dropBooleanTable() throws Exception;
}
