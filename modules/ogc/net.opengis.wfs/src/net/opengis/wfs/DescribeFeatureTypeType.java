/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wfs;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Describe Feature Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The DescribeFeatureType operation allows a client application to request that a Web Feature
 * Service describe one or more feature types. A Web Feature Service must be able to generate
 * feature descriptions as valid GML3 application schemas.
 *
 * <p>The schemas generated by the DescribeFeatureType operation can be used by a client application
 * to validate the output.
 *
 * <p>Feature instances within the WFS interface must be specified using GML3. The schema of feature
 * instances specified within the WFS interface must validate against the feature schemas generated
 * by the DescribeFeatureType request.
 * <!-- end-model-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link net.opengis.wfs.DescribeFeatureTypeType#getTypeName <em>Type Name</em>}
 *   <li>{@link net.opengis.wfs.DescribeFeatureTypeType#getOutputFormat <em>Output Format</em>}
 * </ul>
 *
 * @see net.opengis.wfs.WfsPackage#getDescribeFeatureTypeType()
 * @model extendedMetaData="name='DescribeFeatureTypeType' kind='elementOnly'"
 * @generated
 */
public interface DescribeFeatureTypeType extends BaseRequestType {
  /**
   * Returns the value of the '<em><b>Type Name</b></em>' attribute list. The list contents are of
   * type {@link java.lang.Object}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The TypeName element is used to enumerate the feature types to be described. If no TypeName
   * elements are specified then all features should be described. The name must be a valid type
   * that belongs to the feature content as defined by the GML Application Schema.
   * <!-- end-model-doc -->
   *
   * @return the value of the '<em>Type Name</em>' attribute list.
   * @see net.opengis.wfs.WFSPackage#getDescribeFeatureTypeType_TypeName()
   * @model type="javax.xml.namespace.QName"
   */
  EList getTypeName();

  /**
   * Returns the value of the '<em><b>Output Format</b></em>' attribute. The default value is <code>
   * "text/xml; subtype=gml/3.1.1"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The outputFormat attribute is used to specify what schema description language should be used
   * to describe features. The default value of 'text/xml; subtype=3.1.1' means that the WFS must
   * generate a GML3 application schema that can be used to validate the GML3 output of a GetFeature
   * request or feature instances specified in Transaction operations. For the purposes of
   * experimentation, vendor extension, or even extensions that serve a specific community of
   * interest, other acceptable output format values may be advertised by a WFS service in the
   * capabilities document. The meaning of such values in not defined in the WFS specification. The
   * only proviso is such cases is that clients may safely ignore outputFormat values that do not
   * recognize.
   * <!-- end-model-doc -->
   *
   * @return the value of the '<em>Output Format</em>' attribute.
   * @see #isSetOutputFormat()
   * @see #unsetOutputFormat()
   * @see #setOutputFormat(String)
   * @see net.opengis.wfs.WfsPackage#getDescribeFeatureTypeType_OutputFormat()
   * @model default="text/xml; subtype=gml/3.1.1" unique="false" unsettable="true"
   *     dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute'
   *     name='outputFormat'"
   * @generated
   */
  String getOutputFormat();

  /**
   * Sets the value of the '{@link net.opengis.wfs.DescribeFeatureTypeType#getOutputFormat
   * <em>Output Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Output Format</em>' attribute.
   * @see #isSetOutputFormat()
   * @see #unsetOutputFormat()
   * @see #getOutputFormat()
   * @generated
   */
  void setOutputFormat(String value);

  /**
   * Unsets the value of the '{@link net.opengis.wfs.DescribeFeatureTypeType#getOutputFormat
   * <em>Output Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isSetOutputFormat()
   * @see #getOutputFormat()
   * @see #setOutputFormat(String)
   * @generated
   */
  void unsetOutputFormat();

  /**
   * Returns whether the value of the '{@link
   * net.opengis.wfs.DescribeFeatureTypeType#getOutputFormat <em>Output Format</em>}' attribute is
   * set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return whether the value of the '<em>Output Format</em>' attribute is set.
   * @see #unsetOutputFormat()
   * @see #getOutputFormat()
   * @see #setOutputFormat(String)
   * @generated
   */
  boolean isSetOutputFormat();
} // DescribeFeatureTypeType
