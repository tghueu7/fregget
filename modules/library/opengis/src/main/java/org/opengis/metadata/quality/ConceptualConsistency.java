/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2004-2005, Open Geospatial Consortium Inc.
 *
 *    All Rights Reserved. http://www.opengis.org/legal/
 */
package org.opengis.metadata.quality;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Adherence to rules of the conceptual schema.
 *
 * @author Martin Desruisseaux (IRD)
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @source $URL$
 * @since GeoAPI 2.0
 */
@UML(identifier = "DQ_ConceptualConsistency", specification = ISO_19115)
public interface ConceptualConsistency extends LogicalConsistency {
}
