package org.geotools.data.shapefile;

import java.io.File;
import java.net.URL;

import org.junit.Assume;
import org.junit.Before;

public class IndexedDbfShapefileDataStoreTest extends ShapefileDataStoreTest {

    @Before
    public void checkOdbcDrivers() {
        Assume.assumeTrue(OdbcRecnoIndexManager.isAvailable());
    }
    
    @Override
    protected ShapefileDataStore getShapefileDataStore(URL toURL) {
        ShapefileDataStore store = super.getShapefileDataStore(toURL);
        store.setRecnoIndexed(true);
        return store;
    }
    
    @Override
    protected ShapefileDataStore createDataStore(File f) throws Exception {
        ShapefileDataStore ds = super.createDataStore(f);
        ds.setRecnoIndexed(true);
        return ds;
    }
}
