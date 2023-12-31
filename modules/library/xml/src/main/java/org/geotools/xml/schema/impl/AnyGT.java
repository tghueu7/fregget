/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.xml.schema.impl;

import java.net.URI;
import org.geotools.xml.schema.Any;
import org.geotools.xml.schema.Element;
import org.geotools.xml.schema.ElementGrouping;

/** @author dzwiers */
public class AnyGT implements Any {
    private int min = 1;
    private int max = 1;
    private URI ns = null;

    private AnyGT() {
        // do nothing
    }

    public AnyGT(URI namespace) {
        ns = namespace;
    }

    public AnyGT(URI namespace, int min, int max) {
        ns = namespace;
        this.min = min;
        this.max = max;
    }

    /** @see org.geotools.xml.schema.Any#getId() */
    @Override
    public String getId() {
        return null;
    }

    /** @see org.geotools.xml.schema.ElementGrouping#getMaxOccurs() */
    @Override
    public int getMaxOccurs() {
        return max;
    }

    /** @see org.geotools.xml.schema.ElementGrouping#getMinOccurs() */
    @Override
    public int getMinOccurs() {
        return min;
    }

    /** @see org.geotools.xml.schema.Any#getNamespace() */
    @Override
    public URI getNamespace() {
        return ns;
    }

    /** @see org.geotools.xml.schema.ElementGrouping#getGrouping() */
    @Override
    public int getGrouping() {
        return ElementGrouping.ANY;
    }

    /** @see org.geotools.xml.schema.ElementGrouping#findChildElement(java.lang.String) */
    @Override
    public Element findChildElement(String name) {
        return null;
    }

    @Override
    public Element findChildElement(String localName, URI namespaceURI) {
        return null;
    }
}
