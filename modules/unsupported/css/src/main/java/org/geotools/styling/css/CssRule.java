/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2014, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.styling.css;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.geotools.styling.css.selector.PseudoClass;
import org.geotools.styling.css.selector.Selector;
import org.geotools.styling.css.util.PseudoClassExtractor;
import org.geotools.util.Converters;

public class CssRule {

    public static final Integer NO_Z_INDEX = null;

    public Selector selector;

    public Map<PseudoClass, List<Property>> properties;

    public String comment;

    public List<CssRule> ancestry;

    public CssRule(Selector selector, List<Property> properties) {
        super();
        this.selector = selector;
        PseudoClassExtractor extractor = new PseudoClassExtractor();
        selector.accept(extractor);
        this.properties = new HashMap<PseudoClass, List<Property>>();
        Set<PseudoClass> pseudoClasses = extractor.getPseudoClasses();
        for (PseudoClass ps : pseudoClasses) {
            this.properties.put(ps, properties);
        }
    }

    public CssRule(String comment, Selector selector, List<Property> properties) {
        this(selector, properties);
        this.comment = comment;
    }

    public CssRule(Selector selector, Map<PseudoClass, List<Property>> properties, String comment) {
        this.selector = selector;
        this.properties = properties;
        this.comment = comment;
    }

    @Override
    public String toString() {
        String base = "Rule [selector=" + selector + ", properties=" + properties + "]";
        if (ancestry == null) {
            return base;
        }
        StringBuilder sb = new StringBuilder(base);
        sb.append("\nAncestry (lowest to highest priority):");
        int idx = 1;
        for (CssRule ancestor : ancestry) {
            sb.append("\n")
                    .append(idx)
                    .append(") ")
                    .append("[selector=" + ancestor.selector + "\n   properties="
                            + ancestor.properties + "]");
            idx++;
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result + ((selector == null) ? 0 : selector.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CssRule other = (CssRule) obj;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (selector == null) {
            if (other.selector != null)
                return false;
        } else if (!selector.equals(other.selector))
            return false;
        return true;
    }

    public Map<String, List<Value>> getPropertyValues(PseudoClass pseudoClass,
            String... symbolizerPrefixes) {
        List<Property> psProperties = properties.get(pseudoClass);
        if (psProperties == null) {
            return Collections.emptyMap();
        }
        Map<String, List<Value>> result = new HashMap<>();
        if (symbolizerPrefixes != null && symbolizerPrefixes.length > 0) {
            for (String symbolizerPrefix : symbolizerPrefixes) {
                for (Property property : psProperties) {
                    if (symbolizerPrefix == null || property.name.startsWith(symbolizerPrefix)
                            || property.name.startsWith("-gt-" + symbolizerPrefix)) {
                        result.put(property.name, property.values);
                    }
                }
            }
        } else {
            for (Property property : psProperties) {
                result.put(property.name, property.values);
            }
        }

        return result;
    }

    public boolean hasProperty(PseudoClass pseudoClass, String propertyName) {
        List<Property> psProperties = properties.get(pseudoClass);
        if (psProperties == null) {
            return false;
        }
        for (Property property : psProperties) {
            if (propertyName.equals(property.name)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasAnyProperty(PseudoClass pseudoClass, Collection<String> propertyNames) {
        List<Property> psProperties = properties.get(pseudoClass);
        if (psProperties == null) {
            return false;
        }
        for (Property property : psProperties) {
            if (propertyNames.contains(property.name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This rule covers the other if it has the same selector, and has all the properties of the
     * other, plus eventually some more
     * 
     * @param other
     * @return
     */
    public boolean covers(CssRule other) {
        if (!other.selector.equals(selector)) {
            return false;
        }
        Set<PseudoClass> pseudoClasses = this.properties.keySet();
        Set<PseudoClass> otherPseudoClasses = other.properties.keySet();
        if (!pseudoClasses.containsAll(otherPseudoClasses)) {
            return false;
        }
        for (PseudoClass pc : otherPseudoClasses) {
            List<Property> properties = this.properties.get(pc);
            List<Property> otherProperties = other.properties.get(pc);
            for (Property p : otherProperties) {
                if (!properties.contains(p)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Extracts a sub-rule at the given z-index. Will return null if this rule has nothing at that
     * specific z-index
     * 
     * @param zIndex
     * @return
     */
    public CssRule getSubRuleByZIndex(Integer zIndex) {
        Map<PseudoClass, List<Property>> zProperties = new HashMap<>();
        List<Integer> zIndexes = new ArrayList<>();
        for (Map.Entry<PseudoClass, List<Property>> entry : this.properties.entrySet()) {
            List<Property> props = entry.getValue();
            collectZIndexesInProperties(props, zIndexes);
            // the list of z-index values is positional, people will normally set them in
            // increasing order, but we don't want to make assumptions... users could
            // even repeat the same z-index multiple times, take care of that as well
            ListIterator<Integer> it = zIndexes.listIterator();
            while (it.hasNext()) {
                int zIndexPosition = it.nextIndex();
                Integer nextZIndex = it.next();
                if (nextZIndex == NO_Z_INDEX) {
                    // this set of properties is z-index indepenent
                    zProperties.put(entry.getKey(), props);
                } else if (!nextZIndex.equals(zIndex)) {
                    continue;
                } else {
                    // extract the property values at that position
                    List<Property> zIndexProperties = new ArrayList<>();
                    for (Property property : props) {
                        if (isZIndex(property)) {
                            continue;
                        }
                        List<Value> values = property.getValues();
                        if (zIndexPosition < values.size()) {
                            Property p = new Property(property.name, Arrays.asList(values
                                    .get(zIndexPosition)));
                            zIndexProperties.add(p);
                        }
                    }
                    // if we collected any, add to the result
                    if (zIndexProperties.size() > 0) {
                        zProperties.put(entry.getKey(), zIndexProperties);
                    }
                }
            }
        }

        if (zProperties.size() > 0) {
            return new CssRule(this.selector, zProperties, this.comment);
        } else {
            return null;
        }
    }

    /**
     * Returns all z-index values used by this rule
     * 
     * @return
     */
    public Set<Integer> getZIndexes() {
        Set<Integer> indexes = new TreeSet<>(new ZIndexComparator());
        List<Integer> singleListIndexes = new ArrayList<>();
        for (List<Property> list : properties.values()) {
            collectZIndexesInProperties(list, singleListIndexes);
            indexes.addAll(singleListIndexes);
        }

        return indexes;
    }

    /**
     * Returns the z-index values, in the order they are submitted
     * 
     * @param properties
     * @return
     */
    void collectZIndexesInProperties(List<Property> properties, List<Integer> zIndexes) {
        if (zIndexes.size() > 0) {
            zIndexes.clear();
        }
        for (Property property : properties) {
            if (isZIndex(property)) {
                if (zIndexes.size() > 0) {
                    // we have two z-index in the same set of properties? keep the latest
                    zIndexes.clear();
                }
                List<Value> values = property.getValues();
                for (Value value : values) {
                    if (value instanceof Value.Literal) {
                        String body = ((Value.Literal) value).body;
                        Integer zIndex = Converters.convert(body, Integer.class);
                        if (zIndex == null) {
                            throw new IllegalArgumentException(
                                    "Invalid value for z-index, it should be an integer: " + body);
                        } else {
                            zIndexes.add(zIndex);
                        }
                    } else {
                        throw new IllegalArgumentException(
                                "z-index must be integer literals, they cannot be expressions, multi-values or any other type: "
                                        + value);
                    }
                }
            }
        }
        // if we did not find the z-index property, the only z-index is teh default one (which is
        if (zIndexes.isEmpty()) {
            zIndexes.add(null);
        }
    }

    private boolean isZIndex(Property property) {
        String name = property.getName();
        return "z-index".equals(name) || "raster-z-index".equals(name);
    }

}
