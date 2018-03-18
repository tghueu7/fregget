/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.ows11.validation;

import net.opengis.ows11.OperationsMetadataType;
import net.opengis.ows11.ServiceIdentificationType;
import net.opengis.ows11.ServiceProviderType;

/**
 * A sample validator interface for {@link net.opengis.ows11.CapabilitiesBaseType}. This doesn't
 * really do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CapabilitiesBaseTypeValidator {
  boolean validate();

  boolean validateServiceIdentification(ServiceIdentificationType value);

  boolean validateServiceProvider(ServiceProviderType value);

  boolean validateOperationsMetadata(OperationsMetadataType value);

  boolean validateUpdateSequence(String value);

  boolean validateVersion(String value);
}
