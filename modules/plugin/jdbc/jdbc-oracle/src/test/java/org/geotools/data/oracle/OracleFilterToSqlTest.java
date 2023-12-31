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
package org.geotools.data.oracle;

import org.geotools.factory.CommonFactoryFinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.spatial.BBOX;
import org.opengis.filter.spatial.Contains;
import org.opengis.filter.spatial.Crosses;
import org.opengis.filter.spatial.DWithin;
import org.opengis.filter.spatial.Intersects;
import org.opengis.filter.spatial.Overlaps;

public class OracleFilterToSqlTest {

    OracleFilterToSQL encoder;

    FilterFactory2 ff;

    GeometryFactory gf;

    @Before
    public void setUp() throws Exception {
        encoder = new OracleFilterToSQL(null);
        ff = CommonFactoryFinder.getFilterFactory2(null);
        gf = new GeometryFactory();
    }

    @Test
    public void testIncludeEncoding() throws Exception {
        // nothing to filter, no WHERE clause
        Assert.assertEquals("WHERE 1 = 1", encoder.encodeToString(Filter.INCLUDE));
    }

    @Test
    public void testExcludeEncoding() throws Exception {
        Assert.assertEquals("WHERE 0 = 1", encoder.encodeToString(Filter.EXCLUDE));
    }

    @Test
    public void testBboxFilter() throws Exception {
        BBOX bbox = ff.bbox("GEOM", -180, -90, 180, 90, "EPSG:4326");
        String encoded = encoder.encodeToString(bbox);
        Assert.assertEquals(
                "WHERE SDO_RELATE(\"GEOM\", ?, 'mask=anyinteract querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testLooseBboxFilter() throws Exception {
        BBOX bbox = ff.bbox("GEOM", -180, -90, 180, 90, "EPSG:4326");
        encoder.setLooseBBOXEnabled(true);
        String encoded = encoder.encodeToString(bbox);
        Assert.assertEquals(
                "WHERE SDO_FILTER(\"GEOM\", ?, 'mask=anyinteract querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testContainsFilter() throws Exception {
        Contains contains =
                ff.contains(
                        ff.property("SHAPE"),
                        ff.literal(gf.createPoint(new Coordinate(10.0, -10.0))));
        String encoded = encoder.encodeToString(contains);
        Assert.assertEquals(
                "WHERE SDO_RELATE(\"SHAPE\", ?, 'mask=contains querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testCrossesFilter() throws Exception {
        Crosses crosses =
                ff.crosses(
                        ff.property("GEOM"),
                        ff.literal(
                                gf.createLineString(
                                        new Coordinate[] {
                                            new Coordinate(-10.0d, -10.0d), new Coordinate(10d, 10d)
                                        })));
        String encoded = encoder.encodeToString(crosses);
        Assert.assertEquals(
                "WHERE SDO_RELATE(\"GEOM\", ?, 'mask=overlapbdydisjoint querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testIntersectsFilter() throws Exception {
        Intersects intersects =
                ff.intersects(
                        ff.property("GEOM"),
                        ff.literal(
                                gf.createLineString(
                                        new Coordinate[] {
                                            new Coordinate(-10.0d, -10.0d), new Coordinate(10d, 10d)
                                        })));
        String encoded = encoder.encodeToString(intersects);
        Assert.assertEquals(
                "WHERE SDO_RELATE(\"GEOM\", ?, 'mask=anyinteract querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testOverlapsFilter() throws Exception {
        Overlaps overlaps =
                ff.overlaps(
                        ff.property("GEOM"),
                        ff.literal(
                                gf.createLineString(
                                        new Coordinate[] {
                                            new Coordinate(-10.0d, -10.0d), new Coordinate(10d, 10d)
                                        })));
        String encoded = encoder.encodeToString(overlaps);
        Assert.assertEquals(
                "WHERE SDO_RELATE(\"GEOM\", ?, 'mask=overlapbdyintersect querytype=WINDOW') = 'TRUE' ",
                encoded);
    }

    @Test
    public void testDWithinFilterWithUnit() throws Exception {
        Coordinate coordinate = new Coordinate();
        DWithin dwithin =
                ff.dwithin(
                        ff.property("GEOM"),
                        ff.literal(gf.createPoint(coordinate)),
                        10.0,
                        "kilometers");
        String encoded = encoder.encodeToString(dwithin);
        Assert.assertEquals(
                "WHERE SDO_WITHIN_DISTANCE(\"GEOM\",?,'distance=10.0 unit=km') = 'TRUE' ", encoded);
    }

    @Test
    public void testDWithinFilterWithoutUnit() throws Exception {
        Coordinate coordinate = new Coordinate();
        DWithin dwithin =
                ff.dwithin(ff.property("GEOM"), ff.literal(gf.createPoint(coordinate)), 10.0, null);
        String encoded = encoder.encodeToString(dwithin);
        Assert.assertEquals(
                "WHERE SDO_WITHIN_DISTANCE(\"GEOM\",?,'distance=10.0') = 'TRUE' ", encoded);
    }

    // THIS ONE WON'T PASS RIGHT NOW, BUT WE NEED TO PUT A TEST LIKE THIS
    // SOMEHWERE
    // THAT IS, SOMETHING CHECKING THAT TYPED FIDS GET CONVERTED INTO THE PROPER
    // WHERE CLAUSE
    // public void testFIDEncoding() throws Exception {
    // encoder = new SQLEncoderOracle("FID",new HashMap());
    //
    // Filter filter = filterFactory.createFidFilter("FID.1");
    // String value = encoder.encode(filter);
    // assertEquals("WHERE FID = '1'",value);
    //
    // FidFilter fidFilter = filterFactory.createFidFilter();
    // fidFilter.addFid("FID.1");
    // fidFilter.addFid("FID.3");
    // value = encoder.encode(fidFilter);
    // // depending on the iterator order it may be swapped
    // assertTrue("WHERE FID = '3' OR FID = '1'".equals(value) ||
    // "WHERE FID = '1' OR FID = '3'".equals(value));
    // }
}
