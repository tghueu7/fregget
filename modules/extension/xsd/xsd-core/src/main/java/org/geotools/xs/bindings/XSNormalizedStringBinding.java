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
import org.geotools.xsd.AbstractSimpleBinding;
import org.geotools.xsd.InstanceComponent;

/**
 * Binding object for the type http://www.w3.org/2001/XMLSchema:normalizedString.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xs:simpleType name="normalizedString" id="normalizedString"&gt;
 *      &lt;xs:annotation&gt;
 *          &lt;xs:documentation source="http://www.w3.org/TR/xmlschema-2/#normalizedString"/&gt;
 *      &lt;/xs:annotation&gt;
 *      &lt;xs:restriction base="xs:string"&gt;
 *          &lt;xs:whiteSpace value="replace" id="normalizedString.whiteSpace"/&gt;
 *      &lt;/xs:restriction&gt;
 *  &lt;/xs:simpleType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class XSNormalizedStringBinding extends AbstractSimpleBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return XS.NORMALIZEDSTRING;
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
     * This binding delegates to its parent binding which returns objecs of type {@link String}.
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Class getType() {
        return String.class;
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
        // Simply return string value, Whitespace facet is already handled
        return value;
    }
}
