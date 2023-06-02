package org.geotools.referencing.factory;

import org.geotools.util.factory.FactoryRegistryException;
import org.geotools.util.factory.Hints;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.cs.CoordinateSystemAxis;

import java.util.Comparator;

public abstract class OrderedAxisCRSAuthorityFactory extends TransformedAuthorityFactory implements
        CRSAuthorityFactory, Comparator<CoordinateSystemAxis> {
    public OrderedAxisCRSAuthorityFactory(String authority, Hints userHints)
            throws FactoryRegistryException {
        super(authority, userHints);
    }

    public OrderedAxisCRSAuthorityFactory(AuthorityFactory factory) {
        super(factory);
    }
}
