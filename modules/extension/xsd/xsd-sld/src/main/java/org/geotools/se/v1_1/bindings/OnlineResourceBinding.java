/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2010, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.se.v1_1.bindings;

import org.geotools.se.v1_1.SE;
import org.geotools.sld.bindings.SLDOnlineResourceBinding;
import org.geotools.styling.ResourceLocator;
import org.geotools.xml.*;

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/se:OnlineResource.
 * <p>
 * <p>
 * <p>
 * <pre>
 *  <code>
 *  &lt;xsd:element name="OnlineResource" type="se:OnlineResourceType"&gt;
 *      &lt;xsd:annotation&gt;
 *          &lt;xsd:documentation&gt;
 *          An "OnlineResource" is typically used to refer to an HTTP URL.
 *        &lt;/xsd:documentation&gt;
 *      &lt;/xsd:annotation&gt;
 *  &lt;/xsd:element&gt;
 *
 *   </code>
 * </pre>
 * <p>
 * </p>
 *
 * @generated
 * @source $URL$
 */
public class OnlineResourceBinding extends SLDOnlineResourceBinding {

    public OnlineResourceBinding(ResourceLocator resourceLocator) {
        super(resourceLocator);
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return SE.OnlineResource;
    }
}
