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
package org.geotools.xs.bindings;

import javax.xml.namespace.QName;
import org.geotools.xs.XS;
import org.geotools.xsd.InstanceComponent;
import org.geotools.xsd.SimpleBinding;

/**
 * Binding object for the type http://www.w3.org/2001/XMLSchema:gDay.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xs:simpleType name="gDay" id="gDay"&gt;
 *      &lt;xs:annotation&gt;
 *          &lt;xs:appinfo&gt;
 *              &lt;hfp:hasFacet name="pattern"/&gt;
 *              &lt;hfp:hasFacet name="enumeration"/&gt;
 *              &lt;hfp:hasFacet name="whiteSpace"/&gt;
 *              &lt;hfp:hasFacet name="maxInclusive"/&gt;
 *              &lt;hfp:hasFacet name="maxExclusive"/&gt;
 *              &lt;hfp:hasFacet name="minInclusive"/&gt;
 *              &lt;hfp:hasFacet name="minExclusive"/&gt;
 *              &lt;hfp:hasProperty name="ordered" value="partial"/&gt;
 *              &lt;hfp:hasProperty name="bounded" value="false"/&gt;
 *              &lt;hfp:hasProperty name="cardinality" value="countably infinite"/&gt;
 *              &lt;hfp:hasProperty name="numeric" value="false"/&gt;
 *          &lt;/xs:appinfo&gt;
 *          &lt;xs:documentation source="http://www.w3.org/TR/xmlschema-2/#gDay"/&gt;
 *      &lt;/xs:annotation&gt;
 *      &lt;xs:restriction base="xs:anySimpleType"&gt;
 *          &lt;xs:whiteSpace value="collapse" fixed="true" id="gDay.whiteSpace"/&gt;
 *      &lt;/xs:restriction&gt;
 *  &lt;/xs:simpleType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class XSGDayBinding implements SimpleBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return XS.GDAY;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public int getExecutionMode() {
        return AFTER;
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
        return null;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(InstanceComponent instance, Object value) throws Exception {
        // TODO: implement me
        return null;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public String encode(Object object, String value) {
        // TODO: implement
        return null;
    }
}
