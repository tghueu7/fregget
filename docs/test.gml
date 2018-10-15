<?xml version="1.0" encoding="UTF-8"?>
<wfs:FeatureCollection xmlns:importer="http://geoserver.org/importer"
                       xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml"
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" numberOfFeatures="10"
                       timeStamp="2018-10-15T10:28:20.660Z"
                       xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1/wfs.xsd">
    <gml:featureMembers>
        <importer:curves gml:id="curves.0">
            <gml:name>Single arc</gml:name>
            <importer:geometry>
                <gml:Curve srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                           srsDimension="2">
                    <gml:segments>
                        <gml:ArcString interpolation="circularArc3Points">
                            <gml:posList>10 15 15 20 20 15</gml:posList>
                        </gml:ArcString>
                    </gml:segments>
                </gml:Curve>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.1">
            <gml:name>Arc string</gml:name>
            <importer:geometry>
                <gml:Curve srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                           srsDimension="2">
                    <gml:segments>
                        <gml:ArcString interpolation="circularArc3Points">
                            <gml:posList>10 35 15 40 20 35 25 30 30 35</gml:posList>
                        </gml:ArcString>
                    </gml:segments>
                </gml:Curve>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.2">
            <gml:name>Compound line string</gml:name>
            <importer:geometry>
                <gml:Curve srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                           srsDimension="2">
                    <gml:segments>
                        <gml:LineStringSegment interpolation="linear">
                            <gml:posList>10 45 20 45</gml:posList>
                        </gml:LineStringSegment>
                        <gml:ArcString interpolation="circularArc3Points">
                            <gml:posList>20 45 23 48 20 51</gml:posList>
                        </gml:ArcString>
                        <gml:LineStringSegment interpolation="linear">
                            <gml:posList>20 51 10 51</gml:posList>
                        </gml:LineStringSegment>
                    </gml:segments>
                </gml:Curve>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.3">
            <gml:name>Closed mixed line</gml:name>
            <importer:geometry>
                <gml:Curve srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                           srsDimension="2">
                    <gml:segments>
                        <gml:LineStringSegment interpolation="linear">
                            <gml:posList>10 78 10 75 20 75 20 78</gml:posList>
                        </gml:LineStringSegment>
                        <gml:ArcString interpolation="circularArc3Points">
                            <gml:posList>20 78 15 80 10 78</gml:posList>
                        </gml:ArcString>
                    </gml:segments>
                </gml:Curve>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.4">
            <gml:name>Circle</gml:name>
            <importer:geometry>
                <gml:Polygon srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                             srsDimension="2">
                    <gml:exterior>
                        <gml:Ring>
                            <gml:curveMember>
                                <gml:Curve>
                                    <gml:segments>
                                        <gml:ArcString interpolation="circularArc3Points">
                                            <gml:posList>10 150 15 145 20 150 15 155 10 150
                                            </gml:posList>
                                        </gml:ArcString>
                                    </gml:segments>
                                </gml:Curve>
                            </gml:curveMember>
                        </gml:Ring>
                    </gml:exterior>
                </gml:Polygon>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.5">
            <gml:name>Compound polygon</gml:name>
            <importer:geometry>
                <gml:Polygon srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                             srsDimension="2">
                    <gml:exterior>
                        <gml:Ring>
                            <gml:curveMember>
                                <gml:LineString>
                                    <gml:posList>6 10 10 1 14 10</gml:posList>
                                </gml:LineString>
                            </gml:curveMember>
                            <gml:curveMember>
                                <gml:Curve>
                                    <gml:segments>
                                        <gml:ArcString interpolation="circularArc3Points">
                                            <gml:posList>14 10 10 14 6 10</gml:posList>
                                        </gml:ArcString>
                                    </gml:segments>
                                </gml:Curve>
                            </gml:curveMember>
                        </gml:Ring>
                    </gml:exterior>
                </gml:Polygon>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.6">
            <gml:name>Compound polygon with hole</gml:name>
            <importer:geometry>
                <gml:Polygon srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                             srsDimension="2">
                    <gml:exterior>
                        <gml:Ring>
                            <gml:curveMember>
                                <gml:LineString>
                                    <gml:posList>20 30 11 30 7 22 7 15 11 10 21 10 27 30
                                    </gml:posList>
                                </gml:LineString>
                            </gml:curveMember>
                            <gml:curveMember>
                                <gml:Curve>
                                    <gml:segments>
                                        <gml:ArcString interpolation="circularArc3Points">
                                            <gml:posList>27 30 25 27 20 30</gml:posList>
                                        </gml:ArcString>
                                    </gml:segments>
                                </gml:Curve>
                            </gml:curveMember>
                        </gml:Ring>
                    </gml:exterior>
                    <gml:interior>
                        <gml:Ring>
                            <gml:curveMember>
                                <gml:Curve>
                                    <gml:segments>
                                        <gml:ArcString interpolation="circularArc3Points">
                                            <gml:posList>10 17 15 12 20 17 15 22 10 17</gml:posList>
                                        </gml:ArcString>
                                    </gml:segments>
                                </gml:Curve>
                            </gml:curveMember>
                        </gml:Ring>
                    </gml:interior>
                </gml:Polygon>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.7">
            <gml:name>Multipolygon with curves</gml:name>
            <importer:geometry>
                <gml:MultiSurface srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                                  srsDimension="2">
                    <gml:surfaceMember>
                        <gml:Polygon>
                            <gml:exterior>
                                <gml:Ring>
                                    <gml:curveMember>
                                        <gml:LineString>
                                            <gml:posList>6 10 10 1 14 10</gml:posList>
                                        </gml:LineString>
                                    </gml:curveMember>
                                    <gml:curveMember>
                                        <gml:Curve>
                                            <gml:segments>
                                                <gml:ArcString interpolation="circularArc3Points">
                                                    <gml:posList>14 10 10 14 6 10</gml:posList>
                                                </gml:ArcString>
                                            </gml:segments>
                                        </gml:Curve>
                                    </gml:curveMember>
                                </gml:Ring>
                            </gml:exterior>
                            <gml:interior>
                                <gml:Ring>
                                    <gml:curveMember>
                                        <gml:LineString>
                                            <gml:posList>13 10 10 2 7 10</gml:posList>
                                        </gml:LineString>
                                    </gml:curveMember>
                                    <gml:curveMember>
                                        <gml:Curve>
                                            <gml:segments>
                                                <gml:ArcString interpolation="circularArc3Points">
                                                    <gml:posList>7 10 10 13 13 10</gml:posList>
                                                </gml:ArcString>
                                            </gml:segments>
                                        </gml:Curve>
                                    </gml:curveMember>
                                </gml:Ring>
                            </gml:interior>
                        </gml:Polygon>
                    </gml:surfaceMember>
                    <gml:surfaceMember>
                        <gml:Polygon>
                            <gml:exterior>
                                <gml:Ring>
                                    <gml:curveMember>
                                        <gml:LineString>
                                            <gml:posList>106 110 110 101 114 110</gml:posList>
                                        </gml:LineString>
                                    </gml:curveMember>
                                    <gml:curveMember>
                                        <gml:Curve>
                                            <gml:segments>
                                                <gml:ArcString interpolation="circularArc3Points">
                                                    <gml:posList>114 110 110 114 106 110
                                                    </gml:posList>
                                                </gml:ArcString>
                                            </gml:segments>
                                        </gml:Curve>
                                    </gml:curveMember>
                                </gml:Ring>
                            </gml:exterior>
                        </gml:Polygon>
                    </gml:surfaceMember>
                </gml:MultiSurface>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.8">
            <gml:name>Multicurve</gml:name>
            <importer:geometry>
                <gml:MultiCurve srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                                srsDimension="2">
                    <gml:curveMember>
                        <gml:LineString>
                            <gml:posList>0 0 5 5</gml:posList>
                        </gml:LineString>
                    </gml:curveMember>
                    <gml:curveMember>
                        <gml:Curve>
                            <gml:segments>
                                <gml:ArcString interpolation="circularArc3Points">
                                    <gml:posList>4 0 4 4 8 4</gml:posList>
                                </gml:ArcString>
                            </gml:segments>
                        </gml:Curve>
                    </gml:curveMember>
                </gml:MultiCurve>
            </importer:geometry>
        </importer:curves>
        <importer:curves gml:id="curves.9">
            <gml:name>SquareHole2Points</gml:name>
            <importer:geometry>
                <gml:Polygon srsName="http://www.opengis.net/gml/srs/epsg.xml#404000"
                             srsDimension="2">
                    <gml:exterior>
                        <gml:LinearRing>
                            <gml:posList>-10 -10 -10 -8 -8 -8 -8 -10 -10 -10</gml:posList>
                        </gml:LinearRing>
                    </gml:exterior>
                    <gml:interior>
                        <gml:Ring>
                            <gml:curveMember>
                                <gml:Curve>
                                    <gml:segments>
                                        <gml:ArcString interpolation="circularArc3Points">
                                            <gml:posList>-9 -8.5 -9 -9.5 -9 -8.5</gml:posList>
                                        </gml:ArcString>
                                    </gml:segments>
                                </gml:Curve>
                            </gml:curveMember>
                        </gml:Ring>
                    </gml:interior>
                </gml:Polygon>
            </importer:geometry>
        </importer:curves>
    </gml:featureMembers>
</wfs:FeatureCollection>