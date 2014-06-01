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
package org.geotools.styling.css.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base class to build a power set from a set of object, filtering it during construction to avoid
 * trying sub-trees that lead to no results
 * 
 * @author Andrea Aime - GeoSolutions
 * 
 * @param <T>
 * @param <R>
 */
public abstract class FilteredPowerSetBuilder<T, R> {

    private List<T> domain;

    private Set<Signature> rejects = new HashSet<>();

    public FilteredPowerSetBuilder(List<T> domain) {
        this.domain = domain;
    }

    private boolean rejected(Signature s, int k) {
        // see if rejected already
        for (Signature reject : rejects) {
            if (s.contains(reject, k)) {
                return true;
            }
        }

        return false;
    }

    public List<R> buildPowerSet() {
        List<R> result = new ArrayList<>();
        Signature s = Signature.newSignature(domain.size());
        fill(s, 0, domain.size(), result);
        result = postFilterResult(result);
        return result;
    }

    protected List<R> postFilterResult(List<R> result) {
        return result;
    }

    void fill(Signature s, int k, int n, List<R> result) {
        if (k == n) {
            if (!rejected(s, k)) {
                List<T> objects = listFromSignature(s);
                if (!objects.isEmpty()) {
                    if (accept(objects)) {
                        result.add(buildResult(objects));
                    } else {
                        rejects.add((Signature) s.clone());
                    }
                }
            }
            return;
        }
        s.set(k, true);
        if (!rejected(s, k)) {
            fill(s, k + 1, n, result);
        }
        s.set(k, false);
        if (!rejected(s, k)) {
            fill(s, k + 1, n, result);
        }
    }

    protected abstract R buildResult(List<T> objects);

    protected abstract boolean accept(List<T> set);

    private List<T> listFromSignature(Signature signature) {
        List<T> test = new ArrayList<>();
        for (int i = 0; i < domain.size(); i++) {
            if (signature.get(i)) {
                test.add(domain.get(i));
            }
        }
        return test;
    }

}
