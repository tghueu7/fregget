/**
 * <copyright>
 * </copyright>
 * <p>
 * $Id$
 */
package org.geotools.data.efeature;

import org.opengis.feature.Attribute;
import org.opengis.feature.Property;

/**
 * Generic interface for accessing {@link Attribute feature attribute} data.
 * <p>
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link EFeatureAttribute#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 * This class extends {@link EFeatureProperty}. </p>
 *
 * @param <V> - Actual {@link Property#getValue() property value} class.
 * @author kengu
 * @source $URL$
 * @see EFeatureProperty
 * @see EFeaturePackage#getEFeatureProperty()
 * @see EFeaturePackage#getEFeatureAttribute()
 */
public interface EFeatureAttribute<V> extends EFeatureProperty<V, Attribute> {

    /**
     * Get the attribute {@link EFeatureAttributeInfo structure} instance.
     *
     * @return the value of the '<em>Structure</em>' attribute.
     */
    @Override
    public EFeatureAttributeInfo getStructure();

} // EFeatureAttribute
