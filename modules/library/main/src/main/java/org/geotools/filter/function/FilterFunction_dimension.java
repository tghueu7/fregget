package org.geotools.filter.function;

/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2005-2008, Open Source Geospatial Foundation (OSGeo)
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

// this code is autogenerated - you shouldnt be modifying it!

import static org.geotools.filter.capability.FunctionNameImpl.parameter;

import com.vividsolutions.jts.geom.Geometry;
import org.geotools.filter.FunctionExpressionImpl;
import org.geotools.filter.capability.FunctionNameImpl;
import org.opengis.filter.capability.FunctionName;

/** @source $URL$ */
public class FilterFunction_dimension extends FunctionExpressionImpl {

  public static FunctionName NAME =
      new FunctionNameImpl(
          "dimension",
          parameter("dimension", Integer.class),
          parameter("geometry", Geometry.class));

  public FilterFunction_dimension() {
    super(NAME);
  }

  public Object evaluate(Object feature) {
    Geometry arg0;

    try { // attempt to get value and perform conversion
      arg0 = getExpression(0).evaluate(feature, Geometry.class);
    } catch (Exception e) // probably a type error
    {
      throw new IllegalArgumentException(
          "Filter Function problem for function dimension argument #0 - expected type Geometry");
    }

    return new Integer(StaticGeometry.dimension(arg0));
  }
}
