/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2017, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.gml3.bindings;

import com.vividsolutions.jts.geom.*;
import org.geotools.geometry.jts.CurvedGeometryFactory;
import org.geotools.gml3.ArcParameters;
import org.geotools.gml3.Circle;
import org.geotools.gml3.GML;
import org.geotools.measure.Measure;
import org.geotools.xml.AbstractComplexBinding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.opengis.geometry.DirectPosition;

import javax.xml.namespace.QName;


public class CircleByCenterPointBinding extends AbstractComplexBinding {
    GeometryFactory gFactory;
    CoordinateSequenceFactory csFactory;
    ArcParameters arcParameters;

    public CircleByCenterPointBinding(GeometryFactory gFactory, CoordinateSequenceFactory csFactory, ArcParameters arcParameters) {
        this.gFactory = gFactory;
        this.csFactory = csFactory;
        this.arcParameters = arcParameters;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return GML.CircleByCenterPointType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Class getType() {
        return LineString.class;
    }

    @Override
    public int getExecutionMode() {
        return BEFORE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(ElementInstance instance, Node node, Object value)
        throws Exception {

        DirectPosition center = (DirectPosition) node.getChildValue("pos");
        Measure radius = (Measure) node.getChildValue("radius");

        // TODO: handle

        Circle circle = new Circle(new Coordinate(center.getOrdinate(0), center.getOrdinate(1)), radius.doubleValue());
        Coordinate p1 = circle.getPoint(0);
        Coordinate p2 = circle.getPoint(Math.PI / 2);
        Coordinate p3 = circle.getPoint(Math.PI);
        Coordinate p4 = circle.getPoint(-Math.PI / 2);
        CoordinateSequence cs = csFactory.create(5, 2);
        cs.setOrdinate(0, 0, p1.x);
        cs.setOrdinate(0, 1, p1.y);
        cs.setOrdinate(1, 0, p2.x);
        cs.setOrdinate(1, 1, p2.y);
        cs.setOrdinate(2, 0, p3.x);
        cs.setOrdinate(2, 1, p3.y);
        cs.setOrdinate(2, 0, p4.x);
        cs.setOrdinate(2, 1, p4.y);
        cs.setOrdinate(3, 0, p1.x);
        cs.setOrdinate(3, 1, p1.y);


        CurvedGeometryFactory factory = GML3ParsingUtils.getCurvedGeometryFactory(arcParameters,
                gFactory, cs);

        return factory.createCurvedGeometry(cs);

    }

}

