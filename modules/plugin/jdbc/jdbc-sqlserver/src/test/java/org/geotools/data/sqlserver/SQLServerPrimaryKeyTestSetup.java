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
package org.geotools.data.sqlserver;

import org.geotools.jdbc.JDBCPrimaryKeyTestSetup;

@SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation") // not yet a JUnit4 test
public class SQLServerPrimaryKeyTestSetup extends JDBCPrimaryKeyTestSetup {

    public SQLServerPrimaryKeyTestSetup() {
        super(new SQLServerTestSetup());
    }

    @Override
    protected void createAutoGeneratedPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE auto ( pkey int IDENTITY(1,1) PRIMARY KEY, "
                        + "name VARCHAR(255), geom geometry)");

        run(
                "INSERT INTO auto (name,geom ) VALUES ('one',geometry::STGeomFromText('POINT(1 1)',4326))");
        run(
                "INSERT INTO auto (name,geom ) VALUES ('two',geometry::STGeomFromText('POINT(2 2)',4326))");
        run(
                "INSERT INTO auto (name,geom ) VALUES ('three',geometry::STGeomFromText('POINT(3 3)',4326))");

        run(
                "CREATE SPATIAL INDEX _auto_geometry_index on auto(geom) WITH (BOUNDING_BOX = (-10, -10, 10, 10))");
    }

    @Override
    protected void createSequencedPrimaryKeyTable() throws Exception {
        run("CREATE SEQUENCE seq_pkey_sequence START WITH 1 INCREMENT BY 1");
        run(
                "CREATE TABLE seq (pkey int PRIMARY KEY DEFAULT NEXT VALUE FOR seq_pkey_sequence, "
                        + " name VARCHAR(255), geom geometry)");
        // sql server has a specific datatype for auto-incrementing values: IDENTITY; as such it
        // does not mark columns with a default value from a sequence as auto-incrementing,
        // however using the primary key metadata table we can learn Geotools that it has to
        // use the sequence
        run(
                "INSERT INTO gt_pk_metadata (table_name, pk_column, pk_policy, pk_sequence) "
                        + " VALUES ('seq','pkey','sequence', 'seq_pkey_sequence')");

        run("INSERT INTO seq VALUES (NEXT VALUE FOR seq_pkey_sequence,'one',NULL)");
        run("INSERT INTO seq VALUES (NEXT VALUE FOR seq_pkey_sequence,'two',NULL)");
        run("INSERT INTO seq VALUES (NEXT VALUE FOR seq_pkey_sequence,'three',NULL)");
    }

    @Override
    protected void createNonIncrementingPrimaryKeyTable() throws Exception {
        run("CREATE TABLE noninc ( pkey int PRIMARY KEY, name VARCHAR(255), geom geometry)");

        run("INSERT INTO noninc VALUES (1, 'one', geometry::STGeomFromText('POINT(1 1)',4326))");
        run("INSERT INTO noninc VALUES (2, 'two', geometry::STGeomFromText('POINT(2 2)',4326))");
        run("INSERT INTO noninc VALUES (3, 'three', geometry::STGeomFromText('POINT(3 3)',4326))");

        run(
                "CREATE SPATIAL INDEX _noninc_geometry_index on noninc(geom) WITH (BOUNDING_BOX = (-10, -10, 10, 10))");
    }

    @Override
    protected void createMultiColumnPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE multi ( pkey1 int NOT NULL, pkey2 VARCHAR(255) NOT NULL, "
                        + "name VARCHAR(255), geom geometry)");
        run("ALTER TABLE multi ADD PRIMARY KEY (pkey1,pkey2)");

        run(
                "INSERT INTO multi VALUES (1, 'x', 'one', geometry::STGeomFromText('POINT(1 1)',4326))");
        run(
                "INSERT INTO multi VALUES (2, 'y', 'two', geometry::STGeomFromText('POINT(2 2)',4326))");
        run(
                "INSERT INTO multi VALUES (3, 'z', 'three', geometry::STGeomFromText('POINT(3 3)',4326))");

        run(
                "CREATE SPATIAL INDEX _multi_geometry_index on multi(geom) WITH (BOUNDING_BOX = (-10, -10, 10, 10))");
    }

    @Override
    protected void createNullPrimaryKeyTable() throws Exception {
        run("CREATE TABLE nokey (name VARCHAR(255))");

        run("INSERT INTO nokey VALUES ('one')");
        run("INSERT INTO nokey VALUES ('two')");
        run("INSERT INTO nokey VALUES ('three')");
    }

    @Override
    protected void createUniqueIndexTable() throws Exception {
        run("CREATE TABLE uniq ( pkey int, name VARCHAR(255), geom geometry)");
        run("CREATE UNIQUE INDEX uniq_pkey_index ON uniq(pkey)");
        run("INSERT INTO uniq VALUES (1, 'one',geometry::STGeomFromText('POINT(1 1)',4326))");
        run("INSERT INTO uniq VALUES (2, 'two',geometry::STGeomFromText('POINT(2 2)',4326))");
        run("INSERT INTO uniq VALUES (3, 'three',geometry::STGeomFromText('POINT(3 3)',4326))");

        // spatial indexes in sql server require a primary key
        // run("CREATE SPATIAL INDEX _uniq_geometry_index on uniq(geom) WITH (BOUNDING_BOX = (-10,
        // -10, 10, 10))");
    }

    @Override
    protected void createNonFirstColumnPrimaryKey() throws Exception {
        run(
                "CREATE TABLE nonfirst (name VARCHAR(255), pkey int IDENTITY(1,1) PRIMARY KEY, geom geometry)");

        run(
                "INSERT INTO nonfirst (name,geom ) VALUES ('one',geometry::STGeomFromText('POINT(1 1)',4326))");
        run(
                "INSERT INTO nonfirst (name,geom ) VALUES ('two',geometry::STGeomFromText('POINT(2 2)',4326))");
        run(
                "INSERT INTO nonfirst (name,geom ) VALUES ('three',geometry::STGeomFromText('POINT(3 3)',4326))");

        run(
                "CREATE SPATIAL INDEX _auto_geometry_index on nonfirst(geom) WITH (BOUNDING_BOX = (-10, -10, 10, 10))");
    }

    @Override
    protected void dropAutoGeneratedPrimaryKeyTable() throws Exception {
        run("DROP TABLE auto");
    }

    @Override
    protected void dropSequencedPrimaryKeyTable() throws Exception {
        runSafe("DELETE FROM gt_pk_metadata WHERE table_name='seq'");
        runSafe("DROP TABLE seq");
        runSafe("DROP SEQUENCE seq_pkey_sequence");
    }

    @Override
    protected void dropNonIncrementingPrimaryKeyTable() throws Exception {
        run("DROP TABLE noninc");
    }

    @Override
    protected void dropMultiColumnPrimaryKeyTable() throws Exception {
        run("DROP TABLE multi");
    }

    @Override
    protected void dropNullPrimaryKeyTable() throws Exception {
        run("DROP TABLE nokey");
    }

    @Override
    protected void dropUniqueIndexTable() throws Exception {
        run("DROP TABLE uniq");
    }

    @Override
    protected void dropNonFirstPrimaryKeyTable() throws Exception {
        run("DROP TABLE nonfirst");
    }
}
