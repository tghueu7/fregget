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
package org.geotools.ml.bindings;

import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.geotools.ml.Attachment;
import org.geotools.ml.MimeType;
import org.geotools.xsd.AbstractComplexBinding;
import org.geotools.xsd.ElementInstance;
import org.geotools.xsd.Node;

/**
 * Strategy object for the type http://mails/refractions/net:attachmentType.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xsd:complexType name="attachmentType"&gt;
 *      &lt;xsd:group ref="ml:attachmentContent"/&gt;
 *      &lt;xsd:attribute ref="ml:name" use="required"/&gt;
 *  &lt;/xsd:complexType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class MLAttachmentTypeBinding extends AbstractComplexBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return ML.ATTACHMENTTYPE;
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

    @Override
    public Class getType() {
        return Attachment.class;
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
        Map mime = (Map) node.getChildValue("mimetype");
        MimeType mimeType = new MimeType((String) mime.get("type"), (String) mime.get("subtype"));

        String name = (String) node.getChildValue("name");

        // content is optional
        List contentList = node.getChildValues("content");
        StringBuffer content = new StringBuffer();

        for (Object o : contentList) {
            content.append((String) o);
        }

        return new Attachment(name, mimeType, content.toString());
    }
}
