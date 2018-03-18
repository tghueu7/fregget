/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wcs10.validation;

import org.w3.xlink.ActuateType;
import org.w3.xlink.ShowType;

/**
 * A sample validator interface for {@link net.opengis.wcs10.OnlineResourceType}. This doesn't
 * really do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OnlineResourceTypeValidator {
  boolean validate();

  boolean validateActuate(ActuateType value);

  boolean validateArcrole(String value);

  boolean validateHref(String value);

  boolean validateRole(String value);

  boolean validateShow(ShowType value);

  boolean validateTitle(String value);

  boolean validateType(String value);
}
