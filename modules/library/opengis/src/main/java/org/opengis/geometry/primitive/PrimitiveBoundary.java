/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2003-2005, Open Geospatial Consortium Inc.
 *
 *    All Rights Reserved. http://www.opengis.org/legal/
 */
package org.opengis.geometry.primitive;

import org.opengis.geometry.Boundary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Primitive primitive} objects. This is the root for the various
 * return types of the {@link org.opengis.geometry.Geometry#getBoundary getBoundary()} method for
 * subtypes of {@link Primitive}. Since points have no boundary, no special subclass is needed
 * for their boundary.
 *
 * @author Martin Desruisseaux (IRD)
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @source $URL$
 * @since GeoAPI 1.0
 */
@UML(identifier = "GM_PrimitiveBoundary", specification = ISO_19107)
public interface PrimitiveBoundary extends Boundary {
}
