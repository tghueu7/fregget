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
package org.geotools.data.h2;

import org.geotools.jdbc.JDBCPrimaryKeyTestSetup;

@SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation") // not yet a JUnit4 test
public class H2PrimaryKeyTestSetup extends JDBCPrimaryKeyTestSetup {

    protected H2PrimaryKeyTestSetup() {
        super(new H2TestSetup());
    }

    @Override
    protected void createAutoGeneratedPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE \"auto\" ( \"key\" int AUTO_INCREMENT(1) PRIMARY KEY, "
                        + "\"name\" VARCHAR, \"geom\" GEOMETRY)");
        run("CALL AddGeometryColumn(NULL, 'auto', 'geom', 4326, 'GEOMETRY', 2)");

        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('one',NULL)");
        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('two',NULL)");
        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('three',NULL)");
    }

    @Override
    protected void createUniqueIndexTable() throws Exception {
        run("CREATE TABLE \"uniq\" ( \"key\" int, " + "\"name\" VARCHAR, \"geom\" GEOMETRY)");
        run("CALL AddGeometryColumn(NULL, 'uniq', 'geom', 4326, 'GEOMETRY', 2)");

        run("CREATE UNIQUE INDEX \"uniq_key_index\" ON \"uniq\" ( \"key\" )");
        run("INSERT INTO \"uniq\" VALUES (1, 'one',NULL)");
        run("INSERT INTO \"uniq\" VALUES (2, 'two',NULL)");
        run("INSERT INTO \"uniq\" VALUES (3, 'three',NULL)");
    }

    @Override
    protected void createSequencedPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE \"seq\" ( \"key\" int PRIMARY KEY, "
                        + "\"name\" VARCHAR, \"geom\" GEOMETRY)");
        run("CALL AddGeometryColumn(NULL, 'seq', 'geom', 4326, 'GEOMETRY', 2)");
        run("CREATE SEQUENCE SEQ_KEY_SEQUENCE START WITH 1");

        run(
                "INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES ("
                        + "(SELECT NEXTVAL('SEQ_KEY_SEQUENCE')),'one',NULL)");
        run(
                "INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES ("
                        + "(SELECT NEXTVAL('SEQ_KEY_SEQUENCE')),'two',NULL)");
        run(
                "INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES ("
                        + "(SELECT NEXTVAL('SEQ_KEY_SEQUENCE')),'three',NULL)");
    }

    @Override
    protected void createNonIncrementingPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE \"noninc\" ( \"key\" int PRIMARY KEY, "
                        + "\"name\" VARCHAR, \"geom\" BLOB)");
        run("CALL AddGeometryColumn(NULL, 'noninc', 'geom', 4326, 'GEOMETRY', 2)");

        run("INSERT INTO \"noninc\" VALUES (1, 'one', NULL)");
        run("INSERT INTO \"noninc\" VALUES (2, 'two', NULL)");
        run("INSERT INTO \"noninc\" VALUES (3, 'three', NULL)");
    }

    @Override
    protected void createMultiColumnPrimaryKeyTable() throws Exception {
        run(
                "CREATE TABLE \"multi\" ( \"key1\" int NOT NULL, \"key2\" VARCHAR NOT NULL, "
                        + "\"name\" VARCHAR, \"geom\" GEOMETRY)");
        run("CALL AddGeometryColumn(NULL, 'multi', 'geom', 4326, 'GEOMETRY', 2)");
        run("ALTER TABLE \"multi\" ADD PRIMARY KEY (\"key1\",\"key2\")");

        run("INSERT INTO \"multi\" VALUES (1, 'x', 'one', NULL)");
        run("INSERT INTO \"multi\" VALUES (2, 'y', 'two', NULL)");
        run("INSERT INTO \"multi\" VALUES (3, 'z', 'three', NULL)");
    }

    @Override
    protected void createNullPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"nokey\" (\"name\" VARCHAR)");
        run("INSERT INTO \"nokey\" VALUES ('one')");
        run("INSERT INTO \"nokey\" VALUES ('two')");
        run("INSERT INTO \"nokey\" VALUES ('trhee')");
    }

    @Override
    protected void createNonFirstColumnPrimaryKey() throws Exception {
        run(
                "CREATE TABLE \"nonfirst\" (\"name\" VARCHAR, \"key\" int AUTO_INCREMENT(1) PRIMARY KEY, \"geom\" GEOMETRY)");
        run("CALL AddGeometryColumn(NULL, 'auto', 'geom', 4326, 'GEOMETRY', 2)");

        run("INSERT INTO \"nonfirst\" (\"name\",\"geom\" ) VALUES ('one',NULL)");
        run("INSERT INTO \"nonfirst\" (\"name\",\"geom\" ) VALUES ('two',NULL)");
        run("INSERT INTO \"nonfirst\" (\"name\",\"geom\" ) VALUES ('three',NULL)");
    }

    @Override
    protected void dropAutoGeneratedPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"auto\"");
    }

    @Override
    protected void dropUniqueIndexTable() throws Exception {
        run("DROP TABLE \"uniq\"");
    }

    @Override
    protected void dropSequencedPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"seq\"");
        run("DROP SEQUENCE SEQ_KEY_SEQUENCE");
    }

    @Override
    protected void dropNonIncrementingPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"noninc\"");
    }

    @Override
    protected void dropMultiColumnPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"multi\"");
    }

    @Override
    protected void dropNullPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"nokey\"");
    }

    @Override
    protected void dropNonFirstPrimaryKeyTable() throws Exception {
        run("DROP TABLE \"nonfirst\"");
    }
}
