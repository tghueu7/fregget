package org.geotools.data.ingres;

import org.geotools.jdbc.JDBCPrimaryKeyTestSetup;
import org.geotools.jdbc.JDBCTestSetup;

/**
 * @source $URL$
 */
public class IngresPrimaryKeyTestSetup extends JDBCPrimaryKeyTestSetup {

    protected IngresPrimaryKeyTestSetup(JDBCTestSetup delegate) {
        super(delegate);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void createAutoGeneratedPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"auto\"(\"key\" int auto_increment primary key, \"name\" varchar, " +
                "\"geom\" GEOMETRY)");
        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('one',NULL)");
        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('two',NULL)");
        run("INSERT INTO \"auto\" (\"name\",\"geom\" ) VALUES ('three',NULL)");
    }

    @Override
    protected void createMultiColumnPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"multi\" ( \"key1\" int NOT NULL, \"key2\" VARCHAR(256) NOT NULL, "
                + "\"name\" VARCHAR(256), \"geom\" GEOMETRY)");
        run("ALTER TABLE \"multi\" ADD PRIMARY KEY (\"key1\",\"key2\")");

        run("INSERT INTO \"multi\" VALUES (1, 'x', 'one', NULL)");
        run("INSERT INTO \"multi\" VALUES (2, 'y', 'two', NULL)");
        run("INSERT INTO \"multi\" VALUES (3, 'z', 'three', NULL)");
    }

    @Override
    protected void createNonIncrementingPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"noninc\"(\"key\" int primary key, \"name\" varchar, \"geom\" " +
                "GEOMETRY)");

        run("INSERT INTO \"noninc\" VALUES (1, 'one', NULL)");
        run("INSERT INTO \"noninc\" VALUES (2, 'two', NULL)");
        run("INSERT INTO \"noninc\" VALUES (3, 'three', NULL)");
    }

    @Override
    protected void createSequencedPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"seq\" ( \"key\" int PRIMARY KEY, \"name\" VARCHAR(256), \"geom\" " +
                "GEOMETRY)");
        run("CREATE SEQUENCE SEQ_KEY_SEQUENCE START WITH 1");

        run("INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES (" +
                "(NEXT VALUE FOR SEQ_KEY_SEQUENCE),'one',NULL)");
        run("INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES (" +
                "(NEXT VALUE FOR SEQ_KEY_SEQUENCE),'two',NULL)");
        run("INSERT INTO \"seq\" (\"key\", \"name\",\"geom\" ) VALUES (" +
                "(NEXT VALUE FOR SEQ_KEY_SEQUENCE),'three',NULL)");
    }

    @Override
    protected void createNullPrimaryKeyTable() throws Exception {
        run("CREATE TABLE \"nokey\" ( \"name\" VARCHAR(256))");
        run("INSERT INTO \"nokey\" VALUES ('one')");
        run("INSERT INTO \"nokey\" VALUES ('two')");
        run("INSERT INTO \"nokey\" VALUES ('three')");
    }

    @Override
    protected void createUniqueIndexTable() throws Exception {
        run("CREATE TABLE \"uniq\"(\"key\" int, \"name\" varchar(256), \"geom\" GEOMETRY)");

        run("CREATE UNIQUE INDEX \"uniq_key_index\" ON \"uniq\"(\"key\")");
        run("INSERT INTO \"uniq\" VALUES (1,'one',NULL)");
        run("INSERT INTO \"uniq\" VALUES (2,'two',NULL)");
        run("INSERT INTO \"uniq\" VALUES (3,'three',NULL)");
    }

    @Override
    protected void dropAutoGeneratedPrimaryKeyTable() throws Exception {
        runSafe("DROP TABLE \"auto\"");
    }

    @Override
    protected void dropMultiColumnPrimaryKeyTable() throws Exception {
        runSafe("DROP TABLE \"multi\"");
    }

    @Override
    protected void dropNonIncrementingPrimaryKeyTable() throws Exception {
        runSafe("DROP TABLE \"noninc\"");
    }

    @Override
    protected void dropSequencedPrimaryKeyTable() throws Exception {
        runSafe("DROP SEQUENCE SEQ_KEY_SEQUENCE");
        runSafe("DROP TABLE \"seq\"");
    }

    @Override
    protected void dropNullPrimaryKeyTable() throws Exception {
        runSafe("DROP TABLE \"nokey\"");
    }

    @Override
    protected void dropUniqueIndexTable() throws Exception {
        runSafe("DROP TABLE \"uniq\"");
    }
}
