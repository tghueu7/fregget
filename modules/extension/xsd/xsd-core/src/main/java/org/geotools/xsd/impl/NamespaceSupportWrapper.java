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
package org.geotools.xsd.impl;

import java.util.Enumeration;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import org.xml.sax.helpers.NamespaceSupport;

/** NamespaceContext wrapper around namespace support. */
@SuppressWarnings("unchecked") // Java 9 has different API
public class NamespaceSupportWrapper implements NamespaceContext {
    NamespaceSupport namespaceSupport;

    public NamespaceSupportWrapper(NamespaceSupport namesaceSupport) {
        this.namespaceSupport = namesaceSupport;
    }

    public NamespaceSupport getNamespaceSupport() {
        return namespaceSupport;
    }

    @Override
    public String getNamespaceURI(String prefix) {
        return namespaceSupport.getURI(prefix);
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return namespaceSupport.getPrefix(namespaceURI);
    }

    @Override
    public Iterator getPrefixes(String namespaceURI) {
        final Enumeration e = namespaceSupport.getPrefixes(namespaceURI);

        return new Iterator() {
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return e.hasMoreElements();
            }

            @Override
            public Object next() {
                return e.nextElement();
            }
        };
    }
}
