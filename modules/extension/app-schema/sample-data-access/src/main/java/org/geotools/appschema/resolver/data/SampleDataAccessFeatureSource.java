/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2008-2011, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.appschema.resolver.data;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.geotools.data.DataAccess;
import org.geotools.data.FeatureListener;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.data.QueryCapabilities;
import org.geotools.data.ResourceInfo;
import org.geotools.feature.FeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;

/**
 * {@link org.geotools.data.FeatureSource} for {@link SampleDataAccess}.
 *
 * @author Ben Caradoc-Davies (CSIRO Earth Science and Resource Engineering)
 * @version $Id$
 * @since 2.6
 */
public class SampleDataAccessFeatureSource implements FeatureSource<FeatureType, Feature> {

    /**
     * Unsupported operation.
     *
     * @see org.geotools.data.FeatureSource#addFeatureListener(org.geotools.data.FeatureListener)
     */
    @Override
    public void addFeatureListener(FeatureListener listener) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getBounds()
     */
    @Override
    public ReferencedEnvelope getBounds() throws IOException {
        // FIXME implement this
        return null;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getBounds(org.geotools.data.Query)
     */
    @Override
    public ReferencedEnvelope getBounds(Query query) throws IOException {
        // FIXME implement this
        return null;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getCount(org.geotools.data.Query)
     */
    @Override
    public int getCount(Query query) throws IOException {
        // FIXME implement this
        return 0;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getDataStore()
     */
    @Override
    public DataAccess<FeatureType, Feature> getDataStore() {
        // FIXME implement this
        return null;
    }

    /**
     * Return a {@link FeatureCollection} containing the sample features.
     *
     * @see org.geotools.data.FeatureSource#getFeatures()
     */
    @Override
    public FeatureCollection<FeatureType, Feature> getFeatures() throws IOException {
        SampleDataAccessFeatureCollection fc = new SampleDataAccessFeatureCollection();
        fc.addAll(SampleDataAccessData.createMappedFeatures());
        return fc;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getFeatures(org.opengis.filter.Filter)
     */
    @Override
    public FeatureCollection<FeatureType, Feature> getFeatures(Filter filter) throws IOException {
        // FIXME temporary hack
        return getFeatures();
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getFeatures(org.geotools.data.Query)
     */
    @Override
    public FeatureCollection<FeatureType, Feature> getFeatures(Query query) throws IOException {
        // FIXME temporary hack
        return getFeatures();
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getInfo()
     */
    @Override
    public ResourceInfo getInfo() {
        // FIXME implement this
        return null;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getName()
     */
    @Override
    public Name getName() {
        return SampleDataAccessData.MAPPEDFEATURE_TYPE_NAME;
    }

    /**
     * Not yet implemented.
     *
     * @see org.geotools.data.FeatureSource#getQueryCapabilities()
     */
    @Override
    public QueryCapabilities getQueryCapabilities() {
        // FIXME implement this
        return null;
    }

    /**
     * Return feature type.
     *
     * @see org.geotools.data.FeatureSource#getSchema()
     */
    @Override
    public FeatureType getSchema() {
        return SampleDataAccessData.MAPPEDFEATURE_TYPE;
    }

    /**
     * Return an empty list of no hints.
     *
     * @see org.geotools.data.FeatureSource#getSupportedHints()
     */
    @Override
    public Set<Key> getSupportedHints() {
        return new HashSet<>();
    }

    /**
     * Unsupported operation.
     *
     * @see org.geotools.data.FeatureSource#removeFeatureListener(org.geotools.data.FeatureListener)
     */
    @Override
    public void removeFeatureListener(FeatureListener listener) {
        throw new UnsupportedOperationException();
    }
}
