/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wcs10.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link net.opengis.wcs10.HTTPType}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface HTTPTypeValidator {
  boolean validate();

  boolean validateGroup(FeatureMap value);

  boolean validateGet(EList value);

  boolean validatePost(EList value);
}
