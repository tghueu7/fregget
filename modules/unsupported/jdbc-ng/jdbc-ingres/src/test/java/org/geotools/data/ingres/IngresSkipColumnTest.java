package org.geotools.data.ingres;

import org.geotools.jdbc.JDBCSkipColumnTest;
import org.geotools.jdbc.JDBCSkipColumnTestSetup;

/**
 * @source $URL$
 */
public class IngresSkipColumnTest extends JDBCSkipColumnTest {

    @Override
    protected JDBCSkipColumnTestSetup createTestSetup() {
        return new IngresSkipColumnTestSetup();
    }

}
