/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package net.opengis.wfs20;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Additional Objects Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link net.opengis.wfs20.AdditionalObjectsType#getValueCollection <em>Value
 *       Collection</em>}
 *   <li>{@link net.opengis.wfs20.AdditionalObjectsType#getSimpleFeatureCollectionGroup <em>Simple
 *       Feature Collection Group</em>}
 *   <li>{@link net.opengis.wfs20.AdditionalObjectsType#getSimpleFeatureCollection <em>Simple
 *       Feature Collection</em>}
 * </ul>
 *
 * @see net.opengis.wfs20.Wfs20Package#getAdditionalObjectsType()
 * @model extendedMetaData="name='additionalObjects_._type' kind='elementOnly'"
 * @generated
 */
public interface AdditionalObjectsType extends EObject {
  /**
   * Returns the value of the '<em><b>Value Collection</b></em>' containment reference.
   * <!-- begin-user-doc -->
   *
   * <p>If the meaning of the '<em>Value Collection</em>' containment reference isn't clear, there
   * really should be more of a description here...
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Value Collection</em>' containment reference.
   * @see #setValueCollection(ValueCollectionType)
   * @see net.opengis.wfs20.Wfs20Package#getAdditionalObjectsType_ValueCollection()
   * @model containment="true" extendedMetaData="kind='element' name='ValueCollection'
   *     namespace='##targetNamespace'"
   * @generated
   */
  ValueCollectionType getValueCollection();

  /**
   * Sets the value of the '{@link net.opengis.wfs20.AdditionalObjectsType#getValueCollection
   * <em>Value Collection</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Value Collection</em>' containment reference.
   * @see #getValueCollection()
   * @generated
   */
  void setValueCollection(ValueCollectionType value);

  /**
   * Returns the value of the '<em><b>Simple Feature Collection Group</b></em>' attribute list. The
   * list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   *
   * <p>If the meaning of the '<em>Simple Feature Collection Group</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Simple Feature Collection Group</em>' attribute list.
   * @see net.opengis.wfs20.Wfs20Package#getAdditionalObjectsType_SimpleFeatureCollectionGroup()
   * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false"
   *     extendedMetaData="kind='group' name='SimpleFeatureCollection:group'
   *     namespace='##targetNamespace'"
   * @generated
   */
  FeatureMap getSimpleFeatureCollectionGroup();

  /**
   * Returns the value of the '<em><b>Simple Feature Collection</b></em>' containment reference.
   * <!-- begin-user-doc -->
   *
   * <p>If the meaning of the '<em>Simple Feature Collection</em>' containment reference isn't
   * clear, there really should be more of a description here...
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Simple Feature Collection</em>' containment reference.
   * @see #setSimpleFeatureCollection(SimpleFeatureCollectionType)
   * @see net.opengis.wfs20.Wfs20Package#getAdditionalObjectsType_SimpleFeatureCollection()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *     extendedMetaData="kind='element' name='SimpleFeatureCollection'
   *     namespace='##targetNamespace' group='SimpleFeatureCollection:group'"
   * @generated
   */
  SimpleFeatureCollectionType getSimpleFeatureCollection();

  /**
   * Sets the value of the '{@link
   * net.opengis.wfs20.AdditionalObjectsType#getSimpleFeatureCollection <em>Simple Feature
   * Collection</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Simple Feature Collection</em>' containment reference.
   * @see #getSimpleFeatureCollection()
   * @generated
   */
  void setSimpleFeatureCollection(SimpleFeatureCollectionType value);
} // AdditionalObjectsType
