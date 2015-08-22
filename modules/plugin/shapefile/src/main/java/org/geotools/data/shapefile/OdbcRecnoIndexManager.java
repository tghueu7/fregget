/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2002-2015, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.shapefile;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;
import org.geotools.data.jdbc.FilterToSQLException;
import org.geotools.data.shapefile.files.ShpFileType;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.index.CloseableIterator;
import org.geotools.data.shapefile.index.Data;
import org.geotools.data.shapefile.index.DataDefinition;
import org.geotools.data.shapefile.shp.IndexFile;
import org.geotools.filter.FilterCapabilities;
import org.geotools.util.logging.Logging;
import org.opengis.filter.ExcludeFilter;
import org.opengis.filter.Filter;
import org.opengis.filter.IncludeFilter;
import org.opengis.filter.PropertyIsLike;
import org.opengis.filter.PropertyIsNull;
import org.opengis.filter.expression.Add;
import org.opengis.filter.expression.Divide;
import org.opengis.filter.expression.Multiply;
import org.opengis.filter.expression.Subtract;

/**
 * Manages the optional RECNO field index on behalf of the {@link ShapefileDataStore} It only works for Windows SO's.
 * 
 * @author Alvaro Huarte - Tracasa / ahuarte@tracasa.es
 */
public class OdbcRecnoIndexManager implements Closeable {
    static final Logger LOGGER = Logging.getLogger(OdbcRecnoIndexManager.class);

    // The Microsoft Visual FoxPro Driver is available!
    static boolean MICROSOFT_FOXPRO_DRIVER_INSTALLED = false;

    // The Advantage StreamlineSQL ODBC is available!
    static boolean ADVANTAGE_ODBC_DRIVER_INSTALLED = false;

    // Describes the allowed filters we support for alphanumeric Dbase queries.
    static final FilterCapabilities filterCapabilities = new FilterCapabilities();

    ShpFiles shpFiles;

    ShapefileDataStore store;

    private BasicDataSource dataSource;

    // Static constructor of RecnoIndexManager class.
    static {
        boolean runningWindows = System.getProperty("os.name").toUpperCase().contains("WINDOWS");

        /*
         * TODO: Now, it only works for two ODBC drivers running in Windows SO's: - Microsoft ODBC FoxPro Driver (x86). - Advantage StreamlineSQL ODBC
         * driver (x86/x64).
         * 
         * It is feasible use the 'Advantage StreamlineSQL ODBC' in Linux platforms. See...
         * http://devzone.advantagedatabase.com/dz/content.aspx?Key=20&Release=16&Product=14 http://scn.sap.com/docs/DOC-39207
         */
        if (runningWindows) {
            java.sql.Connection connection = null;

            String connectionString = null;
            String tablePath = System.getProperty("user.dir");

            // Get if available two 'superfast' JDBC driver of Windows for DBF tables.
            // 1) Microsoft Visual FoxPro Driver:
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                connectionString = "jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB="
                        + tablePath + ";";

                if ((connection = java.sql.DriverManager.getConnection(connectionString, "", "")) != null) {
                    MICROSOFT_FOXPRO_DRIVER_INSTALLED = true;
                    connection.close();

                    LOGGER.info("The 'Microsoft Visual FoxPro Driver' is available!");
                }
            } catch (Exception error) {
                LOGGER.info("The 'Microsoft Visual FoxPro Driver' is not available!");
            }
            // 2) Advantage StreamlineSQL ODBC Driver:
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                connectionString = "jdbc:odbc:Driver={Advantage StreamlineSQL ODBC};DataDirectory="
                        + tablePath
                        + ";DefaultType=FoxPro;ServerTypes=1;AdvantageLocking=OFF;Pooling=FALSE;ShowDeleted=FALSE;";

                if ((connection = java.sql.DriverManager.getConnection(connectionString, "", "")) != null) {
                    ADVANTAGE_ODBC_DRIVER_INSTALLED = true;
                    connection.close();

                    LOGGER.info("The 'Advantage StreamlineSQL ODBC Driver' is available!");
                }
            } catch (Exception error) {
                LOGGER.info("The 'Advantage StreamlineSQL ODBC Driver' is not available!");
            }

            // Common alphanumeric filter capabilities of the JDBC providers.
            filterCapabilities.addAll(FilterCapabilities.LOGICAL_OPENGIS);
            filterCapabilities.addAll(FilterCapabilities.SIMPLE_COMPARISONS_OPENGIS);
            filterCapabilities.addType(Add.class);
            filterCapabilities.addType(Subtract.class);
            filterCapabilities.addType(Multiply.class);
            filterCapabilities.addType(Divide.class);
            filterCapabilities.addType(PropertyIsNull.class);
            filterCapabilities.addType(IncludeFilter.class);
            filterCapabilities.addType(ExcludeFilter.class);
            filterCapabilities.addType(PropertyIsLike.class);
        }
    }

    // RecnoIndexManager constructor.
    public OdbcRecnoIndexManager(ShpFiles shpFiles, ShapefileDataStore store) {
        this.shpFiles = shpFiles;
        this.store = store;

        String connectionString;
        String tablePath = store.shpFiles.getFileNames().get(ShpFileType.DBF);
        if (ADVANTAGE_ODBC_DRIVER_INSTALLED) {
            connectionString = "jdbc:odbc:Driver={Advantage StreamlineSQL ODBC};DataDirectory="
                    + tablePath
                    + ";DefaultType=FoxPro;ServerTypes=1;AdvantageLocking=OFF;Pooling=FALSE;ShowDeleted=FALSE;";
        } else if (MICROSOFT_FOXPRO_DRIVER_INSTALLED) {
            connectionString = "jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB="
                    + tablePath + ";";
        } else {
            throw new IllegalStateException(
                    "Cannot instantiate the class, no supported ODBC driver found");
        }

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("sun.jdbc.odbc.JdbcOdbcDriver");
        dataSource.setUrl(connectionString);
        dataSource.setDefaultReadOnly(true);
        if (store.getCharset() != null) {
            dataSource.setConnectionProperties("charSet=" + store.getCharset());
        }
    }

    /**
     * Attempts to close the specified connection managed.
     */
    protected void closeConnection(java.sql.Connection connection) {
        org.geotools.data.jdbc.JDBCUtils.close(connection, null, null);
    }

    /**
     * Returns the record index collection that matches with the specified filter using one super fast ODBC Driver.
     */
    private List<Integer> queryRecnoIndex(Filter filter, java.nio.charset.Charset charSet,
            int maxFeatures) throws SQLException, ClassNotFoundException, IOException,
            FilterToSQLException {
        List<Integer> recnoList = new ArrayList<Integer>();
        String tableName = store.getTypeName().getLocalPart();
        try (Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement()) {
            String whereFilter = new RecnoFilterToSQL(filterCapabilities).encodeToString(filter);
            String sql = null;
            if (ADVANTAGE_ODBC_DRIVER_INSTALLED) {
                sql = maxFeatures != -1 && maxFeatures < Integer.MAX_VALUE ? String.format(
                        "SELECT TOP %d ROWID FROM [%s] %s;", maxFeatures, tableName, whereFilter)
                        : String.format("SELECT ROWID FROM [%s] %s;", tableName, whereFilter);
            } else if (MICROSOFT_FOXPRO_DRIVER_INSTALLED) {
                sql = maxFeatures != -1 && maxFeatures < Integer.MAX_VALUE ? String.format(
                        "SELECT TOP %d recno() FROM [%s] %s;", maxFeatures, tableName, whereFilter)
                        : String.format("SELECT recno() FROM [%s] %s;", tableName, whereFilter);
            } else {
                throw new IllegalStateException("No supported driver found!");
            }
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Integer id = OdbcRecnoIndexManager.ConvertRowidToRecno(rs.getString(1));
                    recnoList.add(id - 1);
                }
            }
        }
        return recnoList;
    }

    /**
     * Convert the specified Advantage StreamlineSQL ROWID to the DBF RECNO value.
     */
    private static int ConvertRowidToRecno(String rowID) {
        final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        // The RecNo is the last 6 characters of the ROWID.
        int recno = 0;
        recno += BASE64.indexOf(rowID.charAt(12)) * 1073741824;
        recno += BASE64.indexOf(rowID.charAt(13)) * 16777216;
        recno += BASE64.indexOf(rowID.charAt(14)) * 262144;
        recno += BASE64.indexOf(rowID.charAt(15)) * 4096;
        recno += BASE64.indexOf(rowID.charAt(16)) * 64;
        recno += BASE64.indexOf(rowID.charAt(17));
        return recno;
    }

    /**
     * Returns if the RECNO field index can be used.
     */
    public static boolean isAvailable() {
        return MICROSOFT_FOXPRO_DRIVER_INSTALLED || ADVANTAGE_ODBC_DRIVER_INSTALLED;
    }

    /**
     * Uses the optional Recno field to quickly lookup the shp offset and the record number for the list of fids. Now it only works for two ODBC
     * drivers running in Windows SO's: - Microsoft ODBC FoxPro Driver (x86). - Advantage StreamlineSQL ODBC driver (x86/x64).
     * 
     * @todo It is feasible use the 'Advantage StreamlineSQL ODBC' in Linux platforms.
     */
    public CloseableIterator<Data> queryRecnoIndex(Filter filter, int maxFeatures) throws SQLException, ClassNotFoundException,
            IOException, FilterToSQLException {
        if (isAvailable() && filter != null && supports(filter)) {

            List<Integer> recnoList = queryRecnoIndex(filter, store.charset, maxFeatures);
            List<Data> records = new ArrayList<Data>();
            if (recnoList == null)
                return null;

            if (recnoList.size() > 0) {
                IndexFile shx = store.shpManager.openIndexFile();

                try {
                    DataDefinition def = new DataDefinition("US-ASCII");
                    def.addField(Integer.class);
                    def.addField(Long.class);

                        for (int i = 0, icount = recnoList.size(); i < icount; i++) {
                            int recno = recnoList.get(i);

                            Data data = new Data(def);
                            data.addValue(new Integer(recno + 1));
                            data.addValue(new Long(shx.getOffsetInBytes(recno)));

                            records.add(data);
                        }
                    recnoList.clear();
                } finally {
                    shx.close();
                }
            }
            return new CloseableIteratorWrapper<Data>(records.iterator());
        }
        
        return null;
    }

    public boolean supports(Filter filter) {
        if (filter == Filter.INCLUDE || filter == Filter.EXCLUDE) {
            return false;
        }
        return filterCapabilities.fullySupports(filter);
    }

    @Override
    public void close() throws IOException {
        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
    
    
}
