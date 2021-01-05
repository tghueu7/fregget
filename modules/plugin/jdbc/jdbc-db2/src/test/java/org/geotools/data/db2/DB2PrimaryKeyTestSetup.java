/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */
package org.geotools.data.db2;

import java.sql.Connection;
import java.sql.SQLException;
import org.geotools.jdbc.JDBCPrimaryKeyTestSetup;

@SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation") // not yet a JUnit4 test
public class DB2PrimaryKeyTestSetup extends JDBCPrimaryKeyTestSetup {

    protected DB2PrimaryKeyTestSetup() {
        super(new DB2TestSetup());
    }

    @Override
    protected void createAutoGeneratedPrimaryKeyTable() throws Exception {
        try (Connection con = getDataSource().getConnection()) {

            String stmt =
                    "create table "
                            + DB2TestUtil.SCHEMA_QUOTED
                            + ".\"auto\" (\"key\" int generated always as identity (start with 1, increment by 1),\"name\" varchar(255),  \"geom\" DB2GSE.ST_GEOMETRY, primary key (\"key\"))";
            con.prepareStatement(stmt).execute();
            DB2Util.executeRegister(DB2TestUtil.SCHEMA, "auto", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"auto\" (\"name\",\"geom\")  VALUES ('one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"auto\" (\"name\",\"geom\")  VALUES ('two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"auto\" (\"name\",\"geom\")  VALUES ('three',NULL)")
                    .execute();
        }
    }

    private String getSquenceName() {
        return "seq_key_SEQUENCE";
    }

    private String getSquenceNameQuoted() {
        return DB2TestUtil.SCHEMA_QUOTED + ".\"" + getSquenceName() + "\"";
    }

    @Override
    protected void createSequencedPrimaryKeyTable() throws Exception {

        try (Connection con = getDataSource().getConnection()) {
            con.prepareStatement(
                            "CREATE SEQUENCE "
                                    + getSquenceNameQuoted()
                                    + " AS INTEGER  start with 1")
                    .execute();
            con.prepareStatement(
                            "create table "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"seq\" (\"key\" int not null,\"name\" varchar(255),  \"geom\" DB2GSE.ST_GEOMETRY, primary key (\"key\"))")
                    .execute();
            DB2Util.executeRegister(DB2TestUtil.SCHEMA, "seq", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"seq\" VALUES (next value for "
                                    + getSquenceNameQuoted()
                                    + ",'one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"seq\" VALUES (next value for "
                                    + getSquenceNameQuoted()
                                    + ",'two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"seq\" VALUES (next value for "
                                    + getSquenceNameQuoted()
                                    + ",'three',NULL)")
                    .execute();
        }
    }

    @Override
    protected void createNonIncrementingPrimaryKeyTable() throws Exception {
        try (Connection con = getDataSource().getConnection()) {
            con.prepareStatement(
                            "create table "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"noninc\" (\"key\" int not null,\"name\" varchar(255),  \"geom\" DB2GSE.ST_GEOMETRY, primary key (\"key\"))")
                    .execute();
            DB2Util.executeRegister(DB2TestUtil.SCHEMA, "noninc", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"noninc\" VALUES (1,'one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"noninc\" VALUES (2,'two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"noninc\" VALUES (3,'three',NULL)")
                    .execute();
        }
    }

    @Override
    protected void createMultiColumnPrimaryKeyTable() throws Exception {
        try (Connection con = getDataSource().getConnection()) {
            con.prepareStatement(
                            "create table "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"multi\" (\"key1\" int not null, \"key2\" varchar(255) not null,\"name\" varchar(255) , \"geom\" DB2GSE.ST_GEOMETRY, primary key (\"key1\",\"key2\"))")
                    .execute();
            DB2Util.executeRegister(DB2TestUtil.SCHEMA, "multi", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"multi\" VALUES (1,'x','one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"multi\" VALUES (2,'y','two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"multi\" VALUES (3,'z','three',NULL)")
                    .execute();
        }
    }

    @Override
    protected void createNullPrimaryKeyTable() throws Exception {
        // set up table
        try (Connection con = getDataSource().getConnection()) {
            con.prepareStatement(
                            "CREATE TABLE "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nokey\" ( \"name\" varchar(255) )")
                    .execute();

            // insert data
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nokey\" (\"name\") VALUES ( 'one' )")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nokey\" (\"name\") VALUES ( 'two' )")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nokey\" (\"name\") VALUES ( 'three' )")
                    .execute();
        }
    }

    @Override
    protected void createNonFirstColumnPrimaryKey() throws Exception {
        try (Connection con = getDataSource().getConnection()) {

            String stmt =
                    "create table "
                            + DB2TestUtil.SCHEMA_QUOTED
                            + ".\"nonfirst\" (\"name\" varchar(255), \"key\" int generated always as identity (start with 1, increment by 1), \"geom\" DB2GSE.ST_GEOMETRY, primary key (\"key\"))";
            con.prepareStatement(stmt).execute();
            DB2Util.executeRegister(
                    DB2TestUtil.SCHEMA, "nonfirst", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nonfirst\" (\"name\",\"geom\")  VALUES ('one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nonfirst\" (\"name\",\"geom\")  VALUES ('two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"nonfirst\" (\"name\",\"geom\")  VALUES ('three',NULL)")
                    .execute();
        }
    }

    private String getUniqueIndexName() {
        return "uniq_key_INDEX";
    }

    private String getUniqueIndexNameQuoted() {
        return DB2TestUtil.SCHEMA_QUOTED + ".\"" + getUniqueIndexName() + "\"";
    }

    @Override
    protected void createUniqueIndexTable() throws Exception {
        try (Connection con = getDataSource().getConnection()) {
            con.prepareStatement(
                            "create table "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"uniq\" (\"key\" int not null,\"name\" varchar(255),  \"geom\" DB2GSE.ST_GEOMETRY)")
                    .execute();
            con.prepareStatement(
                            "CREATE UNIQUE INDEX "
                                    + getUniqueIndexNameQuoted()
                                    + " ON "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"uniq\"(\"key\")")
                    .execute();
            DB2Util.executeRegister(DB2TestUtil.SCHEMA, "uniq", "geom", DB2TestUtil.SRSNAME, con);

            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"uniq\" VALUES (1,'one',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"uniq\" VALUES (2,'two',NULL)")
                    .execute();
            con.prepareStatement(
                            "INSERT INTO "
                                    + DB2TestUtil.SCHEMA_QUOTED
                                    + ".\"uniq\" VALUES (3,'three',NULL)")
                    .execute();
        }
    }

    @Override
    protected void dropAutoGeneratedPrimaryKeyTable() throws Exception {
        dropSpatialTable("auto");
    }

    @Override
    protected void dropSequencedPrimaryKeyTable() throws Exception {
        dropSpatialTable("seq");
        try (Connection con = getDataSource().getConnection()) {
            DB2TestUtil.dropSequence(DB2TestUtil.SCHEMA, getSquenceName(), con);
        } catch (SQLException e) {
        }
    }

    @Override
    protected void dropNonIncrementingPrimaryKeyTable() throws Exception {
        dropSpatialTable("noninc");
    }

    @Override
    protected void dropMultiColumnPrimaryKeyTable() throws Exception {
        dropSpatialTable("multi");
    }

    private void dropSpatialTable(String tableName) throws Exception {
        try (Connection con = getDataSource().getConnection()) {
            DB2Util.executeUnRegister(DB2TestUtil.SCHEMA, tableName, "goem", con);
            DB2TestUtil.dropTable(DB2TestUtil.SCHEMA, tableName, con);
        } catch (SQLException e) {
        }
    }

    @Override
    protected void dropNullPrimaryKeyTable() throws Exception {
        dropSpatialTable("nokey");
    }

    @Override
    protected void dropUniqueIndexTable() throws Exception {
        dropSpatialTable("uniq");
    }

    @Override
    protected void dropNonFirstPrimaryKeyTable() throws Exception {
        dropSpatialTable("nonfirst");
    }
}
