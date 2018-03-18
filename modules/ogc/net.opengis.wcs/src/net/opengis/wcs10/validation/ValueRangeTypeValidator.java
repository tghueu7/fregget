/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wcs10.validation;

import net.opengis.wcs10.ClosureType;
import net.opengis.wcs10.TypedLiteralType;

/**
 * A sample validator interface for {@link net.opengis.wcs10.ValueRangeType}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ValueRangeTypeValidator {
  boolean validate();

  boolean validateMin(TypedLiteralType value);

  boolean validateMax(TypedLiteralType value);

  boolean validateAtomic(boolean value);

  boolean validateClosure(ClosureType value);

  boolean validateSemantic(String value);

  boolean validateType(String value);
}
