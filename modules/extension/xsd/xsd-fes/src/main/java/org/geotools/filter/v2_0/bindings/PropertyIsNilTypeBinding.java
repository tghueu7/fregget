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
package org.geotools.filter.v2_0.bindings;

import org.geotools.filter.v1_0.OGC;
import org.geotools.filter.v2_0.FES;
import org.geotools.xml.AbstractComplexBinding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.PropertyIsNil;
import org.opengis.filter.PropertyIsNull;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.PropertyName;

import javax.xml.namespace.QName;


/**
 * Binding object for the type http://www.opengis.net/ogc:{@link net.opengis.fes20.PropertyIsNilType}
 * @source $URL$
 */
public class PropertyIsNilTypeBinding extends AbstractComplexBinding {
    private FilterFactory factory;

    public PropertyIsNilTypeBinding(FilterFactory factory) {
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return FES.PropertyIsNilType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Class getType() {
        return PropertyIsNil.class;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value)
        throws Exception {
        Expression property = (Expression) node.getChildValue(Expression.class);
        String nilReason = null;
        if(node.hasAttribute("nilReason")) {
            nilReason = (String) node.getAttribute("nilReason").getValue();
        }
        return factory.isNil(property, nilReason);
    }

    public Object getProperty(Object object, QName name)
        throws Exception {
        PropertyIsNil isNil = (PropertyIsNil) object;

        if (OGC.PropertyName.equals(name) && isNil.getExpression() instanceof PropertyName) {
            return isNil.getExpression();
        }

        if (OGC.Literal.equals(name) && isNil.getExpression() instanceof Literal) {
            return isNil.getExpression();
        }

        return null;
    }
}
