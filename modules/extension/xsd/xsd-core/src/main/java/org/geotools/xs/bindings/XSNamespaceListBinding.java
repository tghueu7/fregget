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
 * Binding object for the type http://www.w3.org/2001/XMLSchema:namespaceList.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xs:simpleType name="namespaceList"&gt;
 *      &lt;xs:annotation&gt;
 *          &lt;xs:documentation&gt;    A utility type, not for public use&lt;/xs:documentation&gt;
 *      &lt;/xs:annotation&gt;
 *      &lt;xs:union&gt;
 *          &lt;xs:simpleType&gt;
 *              &lt;xs:restriction base="xs:token"&gt;
 *                  &lt;xs:enumeration value="##any"/&gt;
 *                  &lt;xs:enumeration value="##other"/&gt;
 *              &lt;/xs:restriction&gt;
 *          &lt;/xs:simpleType&gt;
 *          &lt;xs:simpleType&gt;
 *              &lt;xs:list&gt;
 *                  &lt;xs:simpleType&gt;
 *                      &lt;xs:union memberTypes="xs:anyURI"&gt;
 *                          &lt;xs:simpleType&gt;
 *                              &lt;xs:restriction base="xs:token"&gt;
 *                                  &lt;xs:enumeration value="##targetNamespace"/&gt;
 *                                  &lt;xs:enumeration value="##local"/&gt;
 *                              &lt;/xs:restriction&gt;
 *                          &lt;/xs:simpleType&gt;
 *                      &lt;/xs:union&gt;
 *                  &lt;/xs:simpleType&gt;
 *              &lt;/xs:list&gt;
 *          &lt;/xs:simpleType&gt;
 *      &lt;/xs:union&gt;
 *  &lt;/xs:simpleType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class XSNamespaceListBinding implements SimpleBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return XS.NAMESPACELIST;
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
