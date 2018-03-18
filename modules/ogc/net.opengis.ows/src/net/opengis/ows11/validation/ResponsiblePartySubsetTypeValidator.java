/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.ows11.validation;

import net.opengis.ows11.CodeType;
import net.opengis.ows11.ContactType;

/**
 * A sample validator interface for {@link net.opengis.ows11.ResponsiblePartySubsetType}. This
 * doesn't really do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ResponsiblePartySubsetTypeValidator {
  boolean validate();

  boolean validateIndividualName(String value);

  boolean validatePositionName(String value);

  boolean validateContactInfo(ContactType value);

  boolean validateRole(CodeType value);
}
