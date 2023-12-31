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
package org.geotools.gml3.bindings;

import javax.xml.namespace.QName;
import org.geotools.gml3.GML;
import org.geotools.xsd.AbstractComplexBinding;
import org.geotools.xsd.ElementInstance;
import org.geotools.xsd.Node;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Polygon;

/**
 * Binding object for the type http://www.opengis.net/gml:BoundingShapeType.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;complexType name="BoundingShapeType"&gt;
 *      &lt;annotation&gt;
 *          &lt;documentation&gt;Bounding shape.&lt;/documentation&gt;
 *      &lt;/annotation&gt;
 *      &lt;sequence&gt;
 *          &lt;choice&gt;
 *              &lt;element ref="gml:Envelope"/&gt;
 *              &lt;element ref="gml:Null"/&gt;
 *          &lt;/choice&gt;
 *      &lt;/sequence&gt;
 *  &lt;/complexType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class BoundingShapeTypeBinding extends AbstractComplexBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return GML.BoundingShapeType;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Class getType() {
        return Envelope.class;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        Envelope envelope = node.getChildValue(Envelope.class);

        if (envelope == null) {
            envelope = new Envelope();
            envelope.setToNull();
        }

        return envelope;
    }

    @Override
    public Object getProperty(Object object, QName name) {
        // check for a polygon
        if (object instanceof Polygon) {
            object = ((Polygon) object).getEnvelopeInternal();
        }
        Envelope e = (Envelope) object;

        if ("Envelope".equals(name.getLocalPart()) && !e.isNull()) {
            return e;
        }
        if ("Null".equals(name.getLocalPart()) && e.isNull()) {
            return e;
        }

        return null;
    }
}
