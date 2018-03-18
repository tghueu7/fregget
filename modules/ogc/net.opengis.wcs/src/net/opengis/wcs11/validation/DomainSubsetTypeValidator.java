/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wcs11.validation;

import net.opengis.ows11.BoundingBoxType;
import net.opengis.wcs11.TimeSequenceType;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link net.opengis.wcs11.DomainSubsetType}. This doesn't really
 * do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface DomainSubsetTypeValidator {
  boolean validate();

  boolean validateBoundingBoxGroup(FeatureMap value);

  boolean validateBoundingBox(BoundingBoxType value);

  boolean validateTemporalSubset(TimeSequenceType value);
}
