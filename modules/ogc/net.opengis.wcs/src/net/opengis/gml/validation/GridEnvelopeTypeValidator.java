/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.gml.validation;

import java.util.List;

/**
 * A sample validator interface for {@link net.opengis.gml.GridEnvelopeType}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface GridEnvelopeTypeValidator {
  boolean validate();

  boolean validateLow(List value);

  boolean validateHigh(List value);
}
