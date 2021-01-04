/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2003-2008, Open Source Geospatial Foundation (OSGeo)
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
/*
 * File is generated by 'Unit Tests Generator' developed under
 * 'Web Test Tools' project at http://sf.net/projects/wttools/
 * Copyright (C) 2001 "Artur Hefczyc" <kobit@users.sourceforge.net>
 * to all 'Web Test Tools' subprojects.
 *
 * No rights to files and no responsibility for code generated
 * by this tool are belonged to author of 'unittestsgen' utility.
 *
 */
package org.geotools.data.vpf.io;

import org.geotools.data.vpf.ifc.DataTypesDefinition;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test <code>RowField</code>.
 *
 * @source $URL$
 */
public class RowFieldTest implements DataTypesDefinition {
    /** Instance of tested class. */
    protected RowField varRowField;

    /** Method for testing original source method: double getAsDouble() from tested class */
    @Test
    public void testGetAsDouble() {
        double[] testVals = {
            Double.MAX_VALUE,
            Double.MIN_VALUE,
            Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            0
        };
        for (double testVal : testVals) {
            varRowField = new RowField(Double.valueOf(testVal), DATA_LONG_FLOAT);
            Assert.assertEquals(
                    "Test RowField for double value: " + testVal,
                    testVal,
                    varRowField.doubleValue(),
                    0);
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsDouble()

    /** Method for testing original source method: float getAsFloat() from tested class */
    @Test
    public void testGetAsFloat() {
        float[] testVals = {
            Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0
        };
        for (float testVal : testVals) {
            varRowField = new RowField(Float.valueOf(testVal), DATA_SHORT_FLOAT);
            Assert.assertEquals(
                    "Test RowField for float value: " + testVal,
                    testVal,
                    varRowField.floatValue(),
                    0);
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsFloat()

    /** Method for testing original source method: int getAsInt() from tested class */
    @Test
    public void testGetAsInt() {
        int[] testVals = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        for (int testVal : testVals) {
            varRowField = new RowField(Integer.valueOf(testVal), DATA_LONG_INTEGER);
            Assert.assertEquals(
                    "Testing RowField for integer value: " + testVal,
                    testVal,
                    varRowField.intValue());
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsInt()

    /** Method for testing original source method: long getAsLong() from tested class */
    @Test
    public void testGetAsLong() {
        long[] testVals = {Long.MAX_VALUE, Long.MIN_VALUE, 0};
        for (long testVal : testVals) {
            varRowField = new RowField(Long.valueOf(testVal), DATA_LONG_INTEGER);
            Assert.assertEquals(
                    "Testing RowField for long value: " + testVal,
                    testVal,
                    varRowField.longValue());
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsLong()

    /** Method for testing original source method: short getAsShort() from tested class */
    @Test
    public void testGetAsShort() {
        short[] testVals = {Short.MAX_VALUE, Short.MIN_VALUE, 0};
        for (short testVal : testVals) {
            varRowField = new RowField(Short.valueOf(testVal), DATA_SHORT_INTEGER);
            Assert.assertEquals(
                    "Testing RowField for short value: " + testVal,
                    testVal,
                    varRowField.shortValue());
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsShort()

    /**
     * Method for testing original source method: java.lang.String getAsString() from tested class
     */
    @Test
    public void testGetAsString() {
        String[] testVals = {null, ""}; // , "\0", "\n", "                       " };
        for (String testVal : testVals) {
            varRowField = new RowField(testVal, DATA_TEXT);
            Assert.assertEquals(
                    "Testing RowField for String value: " + testVal,
                    testVal == null ? "" : testVal,
                    varRowField.toString());
        } // end of for (int i = 0; i < testVals.length; i++)
    } // end of testGetAsString()

    public static final char[] TEST_TYPES = {
        DATA_TEXT,
        DATA_LEVEL1_TEXT,
        DATA_LEVEL2_TEXT,
        DATA_LEVEL3_TEXT,
        DATA_SHORT_FLOAT,
        DATA_LONG_FLOAT,
        DATA_SHORT_INTEGER,
        DATA_LONG_INTEGER,
        DATA_2_COORD_F,
        DATA_2_COORD_R,
        DATA_3_COORD_F,
        DATA_3_COORD_R,
        DATA_DATE_TIME,
        DATA_NULL_FIELD,
        DATA_TRIPLET_ID,
    };

    /** Method for testing original source method: char getType() from tested class */
    @Test
    public void testGetType() {
        for (char testType : TEST_TYPES) {
            Assert.assertEquals(
                    "Cheking type " + testType, testType, new RowField("1", testType).getType());
        } // end of for (int i = 0; i < TEST_TYPES.length; i++)
    } // end of testGetType()

    /** Method for testing original source method: java.lang.Object getValue() from tested class */
    @Test
    public void testGetValue() {
        String value = "1";
        Assert.assertSame("Cheking value.", value, new RowField(value, DATA_TEXT).getValue());
    } // end of testGetValue()
} // end of RowFieldTest
