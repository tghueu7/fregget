package org.geotools.data.sqlserver.reader;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import junit.framework.TestCase;

/**
 * Binary test data have been produced by executing this query in SQL Server, and then removing the
 * prefix '0x': select geometry::STGeomFromText('wktstring',srid);
 *
 * @source $URL$
 */
public class SQLServerBinaryReaderTest extends TestCase {

  public void testPoint() throws Exception {
    String geometryPointWKT = "POINT (5 10)";
    String geometryPointBinary = "E6100000010C00000000000014400000000000002440";
    testGeometry(geometryPointBinary, geometryPointWKT, 4326);
  }

  public void testEmptyPointGeometry() throws Exception {
    String geometryEmptyWKT = "POINT EMPTY";
    String geometryEmptyBinary = "000000000104000000000000000001000000FFFFFFFFFFFFFFFF01";
    testGeometry(geometryEmptyBinary, geometryEmptyWKT);
  }

  public void testEmptyPolygonGeometry() throws Exception {
    String geometryEmptyWKT = "POLYGON EMPTY";
    String geometryEmptyBinary = "000000000104000000000000000001000000FFFFFFFFFFFFFFFF03";
    testGeometry(geometryEmptyBinary, geometryEmptyWKT);
  }

  public void testEmptyGeometryCollection() throws Exception {
    String geometryEmptyWKT = "GEOMETRYCOLLECTION EMPTY";
    String geometryEmptyBinary = "000000000104000000000000000001000000FFFFFFFFFFFFFFFF07";
    testGeometry(geometryEmptyBinary, geometryEmptyWKT);
  }

  public void testPolygon() throws Exception {
    String geometryPolygonWkt =
        "POLYGON ((-680000 6100000, -670000 6100000, -670000 6090000, -680000 6090000, -680000 6100000))"; // 32633
    String geometryPolygonBinary =
        "797F00000104050000000000000080C024C1000000000845574100000000607224C1000000000845574100000000607224C100000000443B57410000000080C024C100000000443B57410000000080C024C1000000000845574101000000020000000001000000FFFFFFFF0000000003";
    testGeometry(geometryPolygonBinary, geometryPolygonWkt, 32633);
  }

  public void testLineStringWithZ() throws Exception {
    String geometryLineStringWithZWKT = "LINESTRING (0 1 1, 3 2 2, 4 5 NaN)";
    String geometryLineStringWithZBinary =
        "E61000000105030000000000000000000000000000000000F03F0000000000000840000000000000004000000000000010400000000000001440000000000000F03F0000000000000040000000000000F8FF01000000010000000001000000FFFFFFFF0000000002";
    Geometry geometry =
        testGeometry(geometryLineStringWithZBinary, geometryLineStringWithZWKT, 4326);
    assertEquals(1.0, geometry.getCoordinates()[0].z, 0);
    assertEquals(2.0, geometry.getCoordinates()[1].z, 0);
    assertEquals(Double.NaN, geometry.getCoordinates()[2].z, 0);
  }

  public void testGeometryCollection() throws Exception {
    String geometryCollectionWKT =
        "GEOMETRYCOLLECTION (POINT (4 0), LINESTRING (4 2, 5 3), POLYGON ((0 0, 3 0, 3 3, 0 3, 0 0),(1 1, 1 2, 2 2, 2 1, 1 1)))";
    String geometryCollectionBinary =
        "0000000001040D0000000000000000001040000000000000000000000000000010400000000000000040000000000000144000000000000008400000000000000000000000000000000000000000000008400000000000000000000000000000084000000000000008400000000000000000000000000000084000000000000000000000000000000000000000000000F03F000000000000F03F000000000000F03F0000000000000040000000000000004000000000000000400000000000000040000000000000F03F000000000000F03F000000000000F03F04000000010000000001010000000203000000000800000004000000FFFFFFFF0000000007000000000000000001000000000100000002000000000200000003";
    testGeometry(geometryCollectionBinary, geometryCollectionWKT, 0);

    String wkt2 =
        "GEOMETRYCOLLECTION (POINT (4.2585 0), MULTILINESTRING ((10 10, 20 20, 10 40.999), (40 40, 30 30, 40 20, 30 10)), POLYGON ((0 0, 3 0, 3 3, 0 3, 0 0),(1 1, 1 2, 2 2, 2 1, 1 1)))";
    String binary2 =
        "0000000001041200000062105839B40811400000000000000000000000000000244000000000000024400000000000003440000000000000344000000000000024401D5A643BDF7F4440000000000000444000000000000044400000000000003E400000000000003E40000000000000444000000000000034400000000000003E4000000000000024400000000000000000000000000000000000000000000008400000000000000000000000000000084000000000000008400000000000000000000000000000084000000000000000000000000000000000000000000000F03F000000000000F03F000000000000F03F0000000000000040000000000000004000000000000000400000000000000040000000000000F03F000000000000F03F000000000000F03F050000000100000000010100000001040000000208000000000D00000006000000FFFFFFFF0000000007000000000000000001000000000100000005020000000100000002020000000200000002000000000300000003";
    testGeometry(binary2, wkt2, 0);
  }

  public void testMultiLinestring() throws Exception {
    String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10)) "; // 4326
    String binary =
        "00000000010407000000000000000000244000000000000024400000000000003440000000000000344000000000000024400000000000004440000000000000444000000000000044400000000000003E400000000000003E40000000000000444000000000000034400000000000003E400000000000002440020000000100000000010300000003000000FFFFFFFF0000000005000000000000000002000000000100000002";
    testGeometry(binary, wkt, 0);
  }

  public void testMultiPoint() throws Exception {
    String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
    String binary =
        "000000000104040000000000000000002440000000000000444000000000000044400000000000003E40000000000000344000000000000034400000000000003E40000000000000244004000000010000000001010000000102000000010300000005000000FFFFFFFF0000000004000000000000000001000000000100000001000000000200000001000000000300000001";
    testGeometry(binary, wkt, 0);
  }

  public void testMultiPolygon() throws Exception {
    String wkt = "MULTIPOLYGON (((30 20, 10 40, 45 40, 30 20)),((15 5, 40 10, 10 20, 5 10, 15 5)))";
    String binary =
        "000000000104090000000000000000003E40000000000000344000000000000024400000000000004440000000000080464000000000000044400000000000003E4000000000000034400000000000002E4000000000000014400000000000004440000000000000244000000000000024400000000000003440000000000000144000000000000024400000000000002E400000000000001440020000000200000000020400000003000000FFFFFFFF0000000006000000000000000003000000000100000003";
    testGeometry(binary, wkt, 0);
  }

  public void testSingleLineSegment() throws Exception {
    String geometryPointWKT = "LINESTRING (5 10, 10 10)";
    String geometryPointBinary =
        "0000000001140000000000001440000000000000244000000000000024400000000000002440";
    testGeometry(geometryPointBinary, geometryPointWKT, 0);
  }

  public void testGeographyExtremeValues() throws Exception {
    String wkt = "LINESTRING (-90 -15069, 90 15069)";
    String binary = "E6100000011400000000008056C000000000806ECDC0000000000080564000000000806ECD40";
    testGeometry(binary, wkt, 4326);
  }

  public void testMultipolygonWithHole() throws Exception {
    String wkt =
        "MULTIPOLYGON (((463763.254 5545934.95, 463763.769 5545931.91, 463761.968 5545929.407, 463744.287 5545918.541, 463742.321 5545917.919, 463738.83 5545915.773, 463735.313 5545914.667, 463731.694 5545915.371, 463729.272 5545916.87, 463727.262 5545918.888, 463738.43 5545900.483, 463739.819 5545905.063, 463743.178 5545908.473, 463764.645 5545921.666, 463769.141 5545922.399, 463772.855 5545919.761, 463775.693 5545915.271, 463782.453 5545919.543, 463779.766 5545923.794, 463778.984 5545928.318, 463781.631 5545932.069, 463814.246 5545952.684, 463825.78 5545959.975, 463828.795 5545960.496, 463831.296 5545958.732, 463835.051 5545952.791, 463840.755 5545956.396, 463831.231 5545971.462, 463824.015 5545982.877, 463822.135 5545985.851, 463809.563 5546005.741, 463798.401 5546023.4, 463787.328 5546040.918, 463784.125 5546045.987, 463767.166 5546072.815, 463708.864 5546035.963, 463694.037 5546059.42, 463694.021 5546059.409, 463687.551 5546054.713, 463715.928 5546009.821, 463723.413 5545997.979, 463763.254 5545934.95), (463761.688 5546061.371, 463764.703 5546061.891, 463767.203 5546060.127, 463778.421 5546042.382, 463781.626 5546037.312, 463792.409 5546020.252, 463803.327 5546002.981, 463814.244 5545985.71, 463823.018 5545971.829, 463823.539 5545968.814, 463821.776 5545966.314, 463803.185 5545954.563, 463794.727 5545949.217, 463789.201 5545945.723, 463783.675 5545942.23, 463777.626 5545938.406, 463774.61 5545937.885, 463772.109 5545939.649, 463761.427 5545956.548, 463757.967 5545962.023, 463752.086 5545971.327, 463746.206 5545980.629, 463742.478 5545986.527, 463736.597 5545995.831, 463730.717 5546005.133, 463727.512 5546010.202, 463716.296 5546027.947, 463715.775 5546030.963, 463717.539 5546033.464, 463723.589 5546037.288, 463729.115 5546040.781, 463734.641 5546044.274, 463743.099 5546049.62, 463761.688 5546061.371)))";
    String binary =
        "E864000001044C000000759318044D4E1C41CDCCCCBCF32755416ABC74134F4E1C41A4703DFAF22755415A643BDF474E1C41BA490C5AF2275541F853E325014E1C4177BE9FA2EF2755415839B448F94D1C4160E5D07AEF2755411F85EB51EB4D1C41FED478F1EE2755416F128340DD4D1C41C520B0AAEE2755419EEFA7C6CE4D1C41C976BED7EE275541022B8716C54D1C417B14AE37EF2755415EBA490CBD4D1C41F4FDD4B8EF27554185EB51B8E94D1C41D578E91EEB2755419EEFA746EF4D1C4127310844EC275541CBA145B6FC4D1C41CBA1451EED27554148E17A94524E1C4177BE9F6AF0275541D34D6290644E1C414C378999F0275541B81E856B734E1C415839B4F0EF275541C1CAA1C57E4E1C41621058D1EE275541643BDFCF994E1C411283C0E2EF275541D34D62108F4E1C4160E5D0F2F02755412DB29DEF8B4E1C41AC1C5A14F22755412FDD2486964E1C41FA7E6A04F32755418B6CE7FB184F1C41F0A7C62BF8275541EC51B81E474F1C41666666FEF9275541E17A142E534F1C41C976BE1FFA275541BE9F1A2F5D4F1C418716D9AEF9275541105839346C4F1C4177BE9F32F827554152B81E05834F1C4162105819F927554196438BEC5C4F1C41736891DDFC275541F6285C0F404F1C419CC420B8FF275541A4703D8A384F1C41B4C87676002855416F128340064F1C41448B6C6F0528554177BE9F9AD94E1C419A9999D909285541643BDF4FAD4E1C411283C03A0E28554100000080A04E1C410C022B7F0F2855416DE7FBA95C4E1C41C3F52834162855417F6ABC74734D1C41C1CAA1FD0C285541F853E325384D1C41AE47E1DA1228554125068115384D1C41560E2DDA12285541105839341E4D1C41C1CAA1AD11285541CBA145B68F4D1C4196438B7406285541D578E9A6AD4D1C419EEFA77E03285541759318044D4E1C41CDCCCCBCF32755416F1283C0464E1C41C976BE5713285541643BDFCF524E1C41DD24067913285541643BDFCF5C4E1C419CC4200813285541BE9F1AAF894E1C4121B072980E285541DD240681964E1C41D9CEF7530D28554160E5D0A2C14E1C419CC42010092855418716D94EED4E1C4139B4C8BE04285541D122DBF9184F1C41D7A3706D002855418D976E123C4F1C4104560EF5FC275541B29DEF273E4F1C4175931834FC27554177BE9F1A374F1C4175931894FB275541D7A370BDEC4E1C41273108A4F827554121B072E8CA4E1C41F853E34DF7275541AAF1D2CDB44E1C41CBA1456EF6275541333333B39E4E1C41EC51B88EF5275541DD240681864E1C416DE7FB99F42755410AD7A3707A4E1C410AD7A378F42755412DB29D6F704E1C414C3789E9F4275541EE7C3FB5454E1C41986E1223F92755417D3F35DE374E1C41FED47881FA2755414E621058204E1C416891EDD4FC275541FCA9F1D2084E1C4137894128FF275541FED478E9F94D1C41355EBAA100285541CFF75363E24D1C41A01A2FF5022855417D3F35DECA4D1C416F128348052855415EBA490CBE4D1C416891ED8C06285541BE9F1A2F914D1C41E3A59BFC0A2855419A9999198F4D1C41C1CAA1BD0B285541B29DEF27964D1C410E2DB25D0C285541E5D0225BAE4D1C418D976E520D2855415C8FC275C44D1C416DE7FB310E285541D34D6290DA4D1C414C3789110F28554189416065FC4D1C417B14AE67102855416F1283C0464E1C41C976BE5713285541020000000200000000002A00000002000000FFFFFFFF0000000006000000000000000003";
    testGeometry(binary, wkt, 25832);
  }

  private Geometry testGeometry(String geometryBinary, String geometryWKT) throws Exception {
    WKTReader readerWkt = new WKTReader((new GeometryFactory(new PrecisionModel(), 0)));
    return testGeometry(geometryBinary, geometryWKT, readerWkt);
  }

  private Geometry testGeometry(String geometryBinary, String geometryWKT, WKTReader wktReader)
      throws ParseException, IOException {
    byte[] bytes = WKBReader.hexToBytes(geometryBinary);
    Geometry geometryFromWkt = wktReader.read(geometryWKT);
    SqlServerBinaryReader reader = new SqlServerBinaryReader();
    Geometry geometryFromBinary = reader.read(bytes);
    assertEquals(geometryFromWkt, geometryFromBinary);
    return geometryFromBinary;
  }

  private Geometry testGeometry(String geometryBinary, String geometryWKT, int srid)
      throws Exception {
    WKTReader readerWkt = new WKTReader((new GeometryFactory(new PrecisionModel(), srid)));
    Geometry geometry = testGeometry(geometryBinary, geometryWKT, readerWkt);
    assertEquals(srid, geometry.getSRID());
    return geometry;
  }
}
