/**
 * <copyright>
 * </copyright>
 * <p>
 * $Id$
 */
package org.geotools.data.efeature;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @model kind="package"
 * @generated
 * @source $URL$
 * @see org.geotools.data.efeature.EFeatureFactory
 */
public interface EFeaturePackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "efeature";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://geotools.org/data/efeature/efeature.ecore/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "efeature";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EFeaturePackage eINSTANCE = org.geotools.data.efeature.impl.EFeaturePackageImpl.init();

    /**
     * The meta object id for the 
     * '{@link org.geotools.data.efeature.impl.EFeatureImpl <em>EFeature</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.impl.EFeatureImpl
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeature()
     */
    int EFEATURE = 0;

    /**
     * The feature id for the '<em><b>ID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE__ID = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE__DATA = 1;

    /**
     * The feature id for the '<em><b>SRID</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE__SRID = 2;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE__DEFAULT = 3;

    /**
     * The feature id for the '<em><b>Structure</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE__STRUCTURE = 4;

    /**
     * The number of structural features of the '<em>EFeature</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EFEATURE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '<em>Property</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureProperty
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureProperty()
     */
    int EFEATURE_PROPERTY = 13;

    /**
     * The meta object id for the '<em>Attribute</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureAttribute
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureAttribute()
     */
    int EFEATURE_ATTRIBUTE = 14;

    /**
     * The meta object id for the '<em>Geometry</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureGeometry
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureGeometry()
     */
    int EFEATURE_GEOMETRY = 15;

    /**
     * The meta object id for the '<em>EStructural Feature</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @see org.eclipse.emf.ecore.EStructuralFeature
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEStructuralFeature()
     */
    int ESTRUCTURAL_FEATURE = 11;

    /**
     * The meta object id for the '<em>Geometry</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see com.vividsolutions.jts.geom.Geometry
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getGeometry()
     */
    int GEOMETRY = 6;

    /**
     * The meta object id for the '<em>EStructure Info</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EStructureInfo
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEStructureInfo()
     */
    int ESTRUCTURE_INFO = 7;

    /**
     * The meta object id for the '<em>Info</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureInfo
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureInfo()
     */
    int EFEATURE_INFO = 8;

    /**
     * The meta object id for the '<em>Attribute Info</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureAttributeInfo
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureAttributeInfo()
     */
    int EFEATURE_ATTRIBUTE_INFO = 9;

    /**
     * The meta object id for the '<em>Geometry Info</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.efeature.EFeatureGeometryInfo
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureGeometryInfo()
     */
    int EFEATURE_GEOMETRY_INFO = 10;

    /**
     * The meta object id for the '<em>List</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see java.util.List
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getList()
     */
    int LIST = 12;

    /**
     * The meta object id for the '<em>Feature</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.opengis.feature.Feature
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getFeature()
     */
    int FEATURE = 1;

    /**
     * The meta object id for the '<em>Property</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.opengis.feature.Property
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getProperty()
     */
    int PROPERTY = 2;

    /**
     * The meta object id for the '<em>Attribute</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @see org.opengis.feature.Attribute
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getAttribute()
     */
    int ATTRIBUTE = 3;

    /**
     * The meta object id for the '<em>Transaction</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @see org.geotools.data.Transaction
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getTransaction()
     */
    int TRANSACTION = 4;

    /**
     * The meta object id for the '<em>Geometry Attribute</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @see org.opengis.feature.GeometryAttribute
     * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getGeometryAttribute()
     */
    int GEOMETRY_ATTRIBUTE = 5;

    /**
     * Returns the meta object for class 
     * '{@link org.geotools.data.efeature.EFeature <em>EFeature</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>EFeature</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature
     */
    EClass getEFeature();

    /**
     * Returns the meta object for the attribute 
     * '{@link org.geotools.data.efeature.EFeature#getID <em>ID</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>ID</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature#getID()
     * @see #getEFeature()
     */
    EAttribute getEFeature_ID();

    /**
     * Returns the meta object for the attribute 
     * '{@link org.geotools.data.efeature.EFeature#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Data</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature#getData()
     * @see #getEFeature()
     */
    EAttribute getEFeature_Data();

    /**
     * Returns the meta object for the attribute 
     * '{@link org.geotools.data.efeature.EFeature#getSRID <em>SRID</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>SRID</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature#getSRID()
     * @see #getEFeature()
     */
    EAttribute getEFeature_SRID();

    /**
     * Returns the meta object for the attribute 
     * '{@link org.geotools.data.efeature.EFeature#getDefault <em>Default</em>}'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Default</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature#getDefault()
     * @see #getEFeature()
     */
    EAttribute getEFeature_Default();

    /**
     * Returns the meta object for the attribute '
     * {@link org.geotools.data.efeature.EFeature#getStructure <em>Structure</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Structure</em>'.
     * @generated
     * @see org.geotools.data.efeature.EFeature#getStructure()
     * @see #getEFeature()
     */
    EAttribute getEFeature_Structure();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.efeature.EFeatureProperty <em>Property</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Property</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureProperty" serializeable="false" 
     * typeParameters="V T" TBounds="org.geotools.data.efeature.Property"
     * @generated
     * @see org.geotools.data.efeature.EFeatureProperty
     */
    EDataType getEFeatureProperty();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.efeature.EFeatureAttribute <em>Attribute</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Attribute</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureAttribute" serializeable="false" 
     * typeParameters="V"
     * @generated
     * @see org.geotools.data.efeature.EFeatureAttribute
     */
    EDataType getEFeatureAttribute();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.efeature.EFeatureGeometry <em>Geometry</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Geometry</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureGeometry" serializeable="false" 
     * typeParameters="V" VBounds="org.geotools.data.efeature.Geometry"
     * @generated
     * @see org.geotools.data.efeature.EFeatureGeometry
     */
    EDataType getEFeatureGeometry();

    /**
     * Returns the meta object for data type 
     * '{@link org.eclipse.emf.ecore.EStructuralFeature <em>EStructural Feature</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>EStructural Feature</em>'.
     * @model instanceClass="org.eclipse.emf.ecore.EStructuralFeature"
     * @generated
     * @see org.eclipse.emf.ecore.EStructuralFeature
     */
    EDataType getEStructuralFeature();

    /**
     * Returns the meta object for data type 
     * '{@link com.vividsolutions.jts.geom.Geometry <em>Geometry</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Geometry</em>'.
     * @model instanceClass="com.vividsolutions.jts.geom.Geometry"
     * @generated
     * @see com.vividsolutions.jts.geom.Geometry
     */
    EDataType getGeometry();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.efeature.EStructureInfo <em>EStructure Info</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>EStructure Info</em>'.
     * @model instanceClass="org.geotools.data.efeature.EStructureInfo" serializeable="false"
     * @generated
     * @see org.geotools.data.efeature.EStructureInfo
     */
    EDataType getEStructureInfo();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.efeature.EFeatureInfo <em>Info</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Info</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureInfo" serializeable="false"
     * @generated
     * @see org.geotools.data.efeature.EFeatureInfo
     */
    EDataType getEFeatureInfo();

    /**
     * Returns the meta object for data type '
     * {@link org.geotools.data.efeature.EFeatureAttributeInfo <em>Attribute Info</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Attribute Info</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureAttributeInfo" serializeable="false"
     * @generated
     * @see org.geotools.data.efeature.EFeatureAttributeInfo
     */
    EDataType getEFeatureAttributeInfo();

    /**
     * Returns the meta object for data type '
     * {@link org.geotools.data.efeature.EFeatureGeometryInfo <em>Geometry Info</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Geometry Info</em>'.
     * @model instanceClass="org.geotools.data.efeature.EFeatureGeometryInfo" serializeable="false"
     * @generated
     * @see org.geotools.data.efeature.EFeatureGeometryInfo
     */
    EDataType getEFeatureGeometryInfo();

    /**
     * Returns the meta object for data type '{@link java.util.List <em>List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>List</em>'.
     * @model instanceClass="java.util.List" serializeable="false" typeParameters="T"
     * @generated
     * @see java.util.List
     */
    EDataType getList();

    /**
     * Returns the meta object for data type '{@link org.opengis.feature.Feature <em>Feature</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Feature</em>'.
     * @model instanceClass="org.opengis.feature.Feature" serializeable="false"
     * @generated
     * @see org.opengis.feature.Feature
     */
    EDataType getFeature();

    /**
     * Returns the meta object for data type 
     * '{@link org.opengis.feature.Property <em>Property</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Property</em>'.
     * @model instanceClass="org.opengis.feature.Property"
     * @generated
     * @see org.opengis.feature.Property
     */
    EDataType getProperty();

    /**
     * Returns the meta object for data type 
     * '{@link org.opengis.feature.Attribute <em>Attribute</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Attribute</em>'.
     * @model instanceClass="org.opengis.feature.Attribute"
     * @generated
     * @see org.opengis.feature.Attribute
     */
    EDataType getAttribute();

    /**
     * Returns the meta object for data type 
     * '{@link org.geotools.data.Transaction <em>Transaction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Transaction</em>'.
     * @model instanceClass="org.geotools.data.Transaction" serializeable="false"
     * @generated
     * @see org.geotools.data.Transaction
     */
    EDataType getTransaction();

    /**
     * Returns the meta object for data type 
     * '{@link org.opengis.feature.GeometryAttribute <em>Geometry Attribute</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Geometry Attribute</em>'.
     * @model instanceClass="org.opengis.feature.GeometryAttribute" serializeable="false"
     * @generated
     * @see org.opengis.feature.GeometryAttribute
     */
    EDataType getGeometryAttribute();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    EFeatureFactory getEFeatureFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the 
         * '{@link org.geotools.data.efeature.impl.EFeatureImpl <em>EFeature</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.impl.EFeatureImpl
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeature()
         */
        EClass EFEATURE = eINSTANCE.getEFeature();

        /**
         * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EFEATURE__ID = eINSTANCE.getEFeature_ID();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EFEATURE__DATA = eINSTANCE.getEFeature_Data();

        /**
         * The meta object literal for the '<em><b>SRID</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EFEATURE__SRID = eINSTANCE.getEFeature_SRID();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EFEATURE__DEFAULT = eINSTANCE.getEFeature_Default();

        /**
         * The meta object literal for the '<em><b>Structure</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EFEATURE__STRUCTURE = eINSTANCE.getEFeature_Structure();

        /**
         * The meta object literal for the '<em>Property</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureProperty
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureProperty()
         */
        EDataType EFEATURE_PROPERTY = eINSTANCE.getEFeatureProperty();

        /**
         * The meta object literal for the '<em>Attribute</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureAttribute
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureAttribute()
         */
        EDataType EFEATURE_ATTRIBUTE = eINSTANCE.getEFeatureAttribute();

        /**
         * The meta object literal for the '<em>Geometry</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureGeometry
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureGeometry()
         */
        EDataType EFEATURE_GEOMETRY = eINSTANCE.getEFeatureGeometry();

        /**
         * The meta object literal for the '<em>EStructural Feature</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.eclipse.emf.ecore.EStructuralFeature
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEStructuralFeature()
         */
        EDataType ESTRUCTURAL_FEATURE = eINSTANCE.getEStructuralFeature();

        /**
         * The meta object literal for the '<em>Geometry</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see com.vividsolutions.jts.geom.Geometry
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getGeometry()
         */
        EDataType GEOMETRY = eINSTANCE.getGeometry();

        /**
         * The meta object literal for the '<em>EStructure Info</em>' data type.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EStructureInfo
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEStructureInfo()
         */
        EDataType ESTRUCTURE_INFO = eINSTANCE.getEStructureInfo();

        /**
         * The meta object literal for the '<em>Info</em>' data type.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureInfo
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureInfo()
         */
        EDataType EFEATURE_INFO = eINSTANCE.getEFeatureInfo();

        /**
         * The meta object literal for the '<em>Attribute Info</em>' data type.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureAttributeInfo
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureAttributeInfo()
         */
        EDataType EFEATURE_ATTRIBUTE_INFO = eINSTANCE.getEFeatureAttributeInfo();

        /**
         * The meta object literal for the '<em>Geometry Info</em>' data type.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.efeature.EFeatureGeometryInfo
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getEFeatureGeometryInfo()
         */
        EDataType EFEATURE_GEOMETRY_INFO = eINSTANCE.getEFeatureGeometryInfo();

        /**
         * The meta object literal for the '<em>List</em>' data type.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         * @see java.util.List
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getList()
         */
        EDataType LIST = eINSTANCE.getList();

        /**
         * The meta object literal for the '<em>Feature</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.opengis.feature.Feature
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getFeature()
         */
        EDataType FEATURE = eINSTANCE.getFeature();

        /**
         * The meta object literal for the '<em>Property</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.opengis.feature.Property
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getProperty()
         */
        EDataType PROPERTY = eINSTANCE.getProperty();

        /**
         * The meta object literal for the '<em>Attribute</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.opengis.feature.Attribute
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getAttribute()
         */
        EDataType ATTRIBUTE = eINSTANCE.getAttribute();

        /**
         * The meta object literal for the '<em>Transaction</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         * @see org.geotools.data.Transaction
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getTransaction()
         */
        EDataType TRANSACTION = eINSTANCE.getTransaction();

        /**
         * The meta object literal for the '<em>Geometry Attribute</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         * @see org.opengis.feature.GeometryAttribute
         * @see org.geotools.data.efeature.impl.EFeaturePackageImpl#getGeometryAttribute()
         */
        EDataType GEOMETRY_ATTRIBUTE = eINSTANCE.getGeometryAttribute();

    }

} // EFeaturePackage
