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
 * Extends {@link ServiceInfo} with information about the file structure of the resource
 * 
 * @author Andrea Aime - GeoSolutions
 *
 */
public interface FileServiceInfo extends ServiceInfo {

    /**
     * File or directory providing resource content. Optional information provided to facilitate
     * moving, renaming or deleting resource contents on disk.
     *
     * For resources specified using a directory (such as a tileset) the entire directory contents
     * are considered to define the resource.
     * 
     * @return files or directory providing resource content
     */
    List<File> getFiles();

    /**
     * File or directory representing support files that do not qualify as data itself (e.g.
     * indexes, caches) that would still have to be dropped along with the data in case of removal
     */
    List<File> getSupportFiles();

}
