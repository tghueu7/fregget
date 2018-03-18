/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.ows11.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link net.opengis.ows11.IdentificationType}. This doesn't
 * really do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface IdentificationTypeValidator {
  boolean validate();

  boolean validateBoundingBoxGroup(FeatureMap value);

  boolean validateBoundingBox(EList value);

  boolean validateOutputFormat(EList value);

  boolean validateAvailableCRSGroup(FeatureMap value);

  boolean validateAvailableCRS(EList value);
}
