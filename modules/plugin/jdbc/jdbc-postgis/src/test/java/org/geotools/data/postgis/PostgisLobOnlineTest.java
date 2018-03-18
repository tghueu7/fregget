package org.geotools.data.postgis;

import org.geotools.jdbc.JDBCLobOnlineTest;
import org.geotools.jdbc.JDBCLobTestSetup;

/** @source $URL$ */
public class PostgisLobOnlineTest extends JDBCLobOnlineTest {

  @Override
  protected JDBCLobTestSetup createTestSetup() {
    return new PostgisLobTestSetup(new PostGISTestSetup());
  }
}
