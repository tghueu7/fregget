/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2018, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.postgis;

import org.geotools.factory.Hints;
import org.geotools.util.Converter;
import org.geotools.util.ConverterFactory;
import org.geotools.util.Converters;
import org.postgresql.jdbc.PgArray;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

/**
 * ConverterFactory for handling {@link org.postgresql.jdbc.PgArray} conversions
 */
public class PGArrayConverterFactory implements ConverterFactory {

    @Override
    public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
        if (target.isArray() && PgArray.class.isAssignableFrom(source)) {
            return new Converter() {

                public Object convert(Object source, Class target) throws Exception {
                    PgArray pgArray = (PgArray) source;
                    Object array = pgArray.getArray();
                    int length = Array.getLength(array);
                    Class componentType = target.getComponentType();
                    Object result = Array.newInstance(componentType, length);
                    for (int i = 0; i < length; i++) {
                        Object original = Array.get(array, i);
                        if (original == null) {
                            Array.set(result, i, null);
                        } else {
                            Object converted = Converters.convert(original, componentType);
                            if (converted == null) {
                                throw new RuntimeException("Failed to convert " + original + " to " + componentType);
                            }
                            Array.set(result, i, converted);
                        }
                    }

                    return result;
                }
            };
        }
        return null;
    }
}
