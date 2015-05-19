/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2015, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data;

import java.io.File;
import java.util.List;

/**
 * Default implementation of FileResourceInfo; a simple java bean.
 */
public class DefaultFileResourceInfo extends DefaultResourceInfo implements FileResourceInfo {

    List<File> files;

    List<File> supportFiles;

    /**
     * @return the files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * @return the supportFiles
     */
    public List<File> getSupportFiles() {
        return supportFiles;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * @param supportFiles the supportFiles to set
     */
    public void setSupportFiles(List<File> supportFiles) {
        this.supportFiles = supportFiles;
    }
}
