/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.gml.validation;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.opengis.gml.EnvelopeType}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface EnvelopeTypeValidator {
  boolean validate();

  boolean validatePos(EList value);
}
