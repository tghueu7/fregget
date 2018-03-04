/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2003-2005, Open Geospatial Consortium Inc.
 *
 *    All Rights Reserved. http://www.opengis.org/legal/
 */
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Two distinct positions joined by a geodesic curve. The control points of a {@code Geodesic}
 * shall all lie on the geodesic between its start point and end point. Between these two points,
 * a geodesic curve defined from the 
 * {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} or geoid model
 * used by the 
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}
 * may
 * be used to interpolate other positions. Any other point in the 
 * {@link #getControlPoints controlPoint}
 * array must fall on this geodesic.
 *
 * @author Martin Desruisseaux (IRD)
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @source $URL$
 * @see GeometryFactory#createGeodesic
 * @since GeoAPI 1.0
 */
@UML(identifier = "GM_Geodesic", specification = ISO_19107)
public interface Geodesic extends GeodesicString {
}
