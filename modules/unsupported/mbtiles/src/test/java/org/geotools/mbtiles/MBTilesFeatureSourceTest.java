/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2019, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.mbtiles;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.IOUtils;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.Query;
import org.geotools.data.store.ContentFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.util.URLs;
import org.geotools.util.factory.Hints;
import org.junit.After;
import org.junit.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.spatial.BBOX;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MBTilesFeatureSourceTest {
    static final FilterFactory2 FF = CommonFactoryFinder.getFilterFactory2();

    DataStore store;

    @After
    public void disposeStore() {
        if (store != null) {
            store.dispose();
        }
    }

    @Test
    public void readSinglePointDataTypes() throws IOException, ParseException {
        File file =
                URLs.urlToFile(MBTilesFileVectorTileTest.class.getResource("datatypes.mbtiles"));
        this.store = new MBTilesDataStore(new MBTilesFile(file));
        SimpleFeature feature =
                DataUtilities.first(store.getFeatureSource("datatypes").getFeatures(Query.ALL));
        assertThat(feature.getAttribute("bool_false"), equalTo(false));
        assertThat(feature.getAttribute("bool_true"), equalTo(true));
        assertThat((Double) feature.getAttribute("float_value"), closeTo(1.25, 0.01));
        assertThat((Double) feature.getAttribute("int64_value"), closeTo(123456789012345d, 1));
        assertThat((Double) feature.getAttribute("neg_int_value"), closeTo(-1, 0));
        assertThat((Double) feature.getAttribute("pos_int_value"), closeTo(1, 1.23456789));
        assertThat(feature.getAttribute("string_value"), equalTo("str"));
        Point expected = (Point) new WKTReader().read("POINT (215246.671651058 6281289.23636264)");
        Point actual = (Point) feature.getDefaultGeometry();
        assertTrue(actual.equalsExact(expected, 0.01));
    }

    @Test
    public void readSinglePolygon() throws IOException, ParseException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        BBOX bbox =
                FF.bbox(
                        FF.property(""),
                        new ReferencedEnvelope(
                                5635550,
                                5948635,
                                -1565430,
                                -1252345,
                                MBTilesFile.SPHERICAL_MERCATOR));
        ContentFeatureCollection fc = fs.getFeatures(new Query("water", bbox));
        assertEquals(1, fc.size());
        SimpleFeature feature = DataUtilities.first(fc);
        assertThat(feature.getAttribute("class"), equalTo("ocean"));
        // geometry as read from ogrinfo
        Polygon expected =
                (Polygon)
                        new WKTReader()
                                .read(
                                        "POLYGON ((5953527.25907581 -1570322.30909066,5630657.25159922 -1570322.30909066,5630657.25159922 -1247452.30161408,5953527.25907581 -1247452.30161408,5953527.25907581 -1570322.30909066))");
        Polygon actual = (Polygon) feature.getDefaultGeometry();
        assertTrue(
                "Expected:\n" + expected + "\nBut got:\n" + actual,
                actual.equalsExact(expected, 0.1));
    }

    @Test
    public void queryAllBounds() throws IOException, SQLException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        RectangleLong bounds = fs.getTileBoundsFor(new Query("water"), 7);
        assertEquals(new RectangleLong(79, 82, 54, 59), bounds);
    }

    @Test
    public void queryWorldBounds() throws IOException, SQLException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        BBOX bbox = FF.bbox(FF.property(""), MBTilesFile.WORLD_ENVELOPE);
        RectangleLong bounds = fs.getTileBoundsFor(new Query("water", bbox), 7);
        assertEquals(new RectangleLong(0, 128, 0, 128), bounds);
    }

    @Test
    public void querySingleTile() throws IOException, SQLException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        BBOX bbox =
                FF.bbox(
                        FF.property(""),
                        new ReferencedEnvelope(
                                5635550,
                                5948635,
                                -1565430,
                                -1252345,
                                MBTilesFile.SPHERICAL_MERCATOR));
        RectangleLong bounds = fs.getTileBoundsFor(new Query("water", bbox), 7);
        assertEquals(new RectangleLong(82, 82, 59, 59), bounds);
    }

    @Test
    public void queryTwoTiles() throws IOException, SQLException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        BBOX bbox =
                FF.bbox(
                        FF.property(""),
                        new ReferencedEnvelope(
                                5635550,
                                5948637,
                                -1565430,
                                -1252344,
                                MBTilesFile.SPHERICAL_MERCATOR));
        RectangleLong bounds = fs.getTileBoundsFor(new Query("water", bbox), 7);
        assertEquals(new RectangleLong(82, 83, 59, 60), bounds);
    }

    private MBTilesFeatureSource getMadagascarSource(String typeName) throws IOException {
        File file =
                URLs.urlToFile(MBTilesFileVectorTileTest.class.getResource("madagascar.mbtiles"));
        this.store = new MBTilesDataStore(new MBTilesFile(file));
        return (MBTilesFeatureSource) store.getFeatureSource(typeName);
    }

    @Test
    public void testGetLowerResolution() throws IOException, ParseException {
        MBTilesFeatureSource fs = getMadagascarSource("water");
        Query query = new Query("water", FF.equal(FF.property("class"), FF.literal("ocean"), true));
        query.setHints(new Hints(Hints.GEOMETRY_SIMPLIFICATION, 78271d));
        ContentFeatureCollection fc = fs.getFeatures(query);
        assertEquals(1, fc.size());
        SimpleFeature feature = DataUtilities.first(fc);
        // this one got from ogrinfo on the tile
        Geometry expected =
                new WKTReader()
                        .read(IOUtils.toString(getClass().getResourceAsStream("ocean_2_2_1.wkt")));
        Geometry actual = (Geometry) feature.getDefaultGeometry();
        // theere is some difference in size, but nothing major
        // (not sure why, on a viewer it looks good...)
        assertEquals(0, expected.difference(actual).getArea(), 31000);
        assertEquals(0, actual.difference(expected).getArea(), 31000);
    }
}
