/**
 */
package org.w3._2001.smil20.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.w3._2001.smil20.AccumulateType;
import org.w3._2001.smil20.AdditiveType;
import org.w3._2001.smil20.AnimateColorPrototype;
import org.w3._2001.smil20.AttributeTypeType;
import org.w3._2001.smil20.Smil20Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Animate Color Prototype</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getAccumulate <em>Accumulate</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getAdditive <em>Additive</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getAttributeName <em>Attribute Name</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getAttributeType <em>Attribute Type</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getBy <em>By</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.w3._2001.smil20.impl.AnimateColorPrototypeImpl#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnimateColorPrototypeImpl extends MinimalEObjectImpl.Container implements AnimateColorPrototype {
    /**
     * The default value of the '{@link #getAccumulate() <em>Accumulate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAccumulate()
     * @generated
     * @ordered
     */
    protected static final AccumulateType ACCUMULATE_EDEFAULT = AccumulateType.NONE;

    /**
     * The cached value of the '{@link #getAccumulate() <em>Accumulate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAccumulate()
     * @generated
     * @ordered
     */
    protected AccumulateType accumulate = ACCUMULATE_EDEFAULT;

    /**
     * This is true if the Accumulate attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean accumulateESet;

    /**
     * The default value of the '{@link #getAdditive() <em>Additive</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditive()
     * @generated
     * @ordered
     */
    protected static final AdditiveType ADDITIVE_EDEFAULT = AdditiveType.REPLACE;

    /**
     * The cached value of the '{@link #getAdditive() <em>Additive</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditive()
     * @generated
     * @ordered
     */
    protected AdditiveType additive = ADDITIVE_EDEFAULT;

    /**
     * This is true if the Additive attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean additiveESet;

    /**
     * The default value of the '{@link #getAttributeName() <em>Attribute Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeName()
     * @generated
     * @ordered
     */
    protected static final String ATTRIBUTE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAttributeName() <em>Attribute Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeName()
     * @generated
     * @ordered
     */
    protected String attributeName = ATTRIBUTE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getAttributeType() <em>Attribute Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeType()
     * @generated
     * @ordered
     */
    protected static final AttributeTypeType ATTRIBUTE_TYPE_EDEFAULT = AttributeTypeType.AUTO;

    /**
     * The cached value of the '{@link #getAttributeType() <em>Attribute Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeType()
     * @generated
     * @ordered
     */
    protected AttributeTypeType attributeType = ATTRIBUTE_TYPE_EDEFAULT;

    /**
     * This is true if the Attribute Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean attributeTypeESet;

    /**
     * The default value of the '{@link #getBy() <em>By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBy()
     * @generated
     * @ordered
     */
    protected static final String BY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBy() <em>By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBy()
     * @generated
     * @ordered
     */
    protected String by = BY_EDEFAULT;

    /**
     * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
    protected static final String FROM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
    protected String from = FROM_EDEFAULT;

    /**
     * The default value of the '{@link #getTo() <em>To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTo()
     * @generated
     * @ordered
     */
    protected static final String TO_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTo()
     * @generated
     * @ordered
     */
    protected String to = TO_EDEFAULT;

    /**
     * The default value of the '{@link #getValues() <em>Values</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
    protected static final String VALUES_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValues() <em>Values</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
    protected String values = VALUES_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AnimateColorPrototypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Smil20Package.Literals.ANIMATE_COLOR_PROTOTYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AccumulateType getAccumulate() {
        return accumulate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAccumulate(AccumulateType newAccumulate) {
        AccumulateType oldAccumulate = accumulate;
        accumulate = newAccumulate == null ? ACCUMULATE_EDEFAULT : newAccumulate;
        boolean oldAccumulateESet = accumulateESet;
        accumulateESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE, oldAccumulate, accumulate, !oldAccumulateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetAccumulate() {
        AccumulateType oldAccumulate = accumulate;
        boolean oldAccumulateESet = accumulateESet;
        accumulate = ACCUMULATE_EDEFAULT;
        accumulateESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE, oldAccumulate, ACCUMULATE_EDEFAULT, oldAccumulateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetAccumulate() {
        return accumulateESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AdditiveType getAdditive() {
        return additive;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAdditive(AdditiveType newAdditive) {
        AdditiveType oldAdditive = additive;
        additive = newAdditive == null ? ADDITIVE_EDEFAULT : newAdditive;
        boolean oldAdditiveESet = additiveESet;
        additiveESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE, oldAdditive, additive, !oldAdditiveESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetAdditive() {
        AdditiveType oldAdditive = additive;
        boolean oldAdditiveESet = additiveESet;
        additive = ADDITIVE_EDEFAULT;
        additiveESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE, oldAdditive, ADDITIVE_EDEFAULT, oldAdditiveESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetAdditive() {
        return additiveESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAttributeName(String newAttributeName) {
        String oldAttributeName = attributeName;
        attributeName = newAttributeName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_NAME, oldAttributeName, attributeName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AttributeTypeType getAttributeType() {
        return attributeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAttributeType(AttributeTypeType newAttributeType) {
        AttributeTypeType oldAttributeType = attributeType;
        attributeType = newAttributeType == null ? ATTRIBUTE_TYPE_EDEFAULT : newAttributeType;
        boolean oldAttributeTypeESet = attributeTypeESet;
        attributeTypeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE, oldAttributeType, attributeType, !oldAttributeTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetAttributeType() {
        AttributeTypeType oldAttributeType = attributeType;
        boolean oldAttributeTypeESet = attributeTypeESet;
        attributeType = ATTRIBUTE_TYPE_EDEFAULT;
        attributeTypeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE, oldAttributeType, ATTRIBUTE_TYPE_EDEFAULT, oldAttributeTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetAttributeType() {
        return attributeTypeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBy() {
        return by;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBy(String newBy) {
        String oldBy = by;
        by = newBy;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__BY, oldBy, by));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFrom() {
        return from;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFrom(String newFrom) {
        String oldFrom = from;
        from = newFrom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__FROM, oldFrom, from));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTo() {
        return to;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTo(String newTo) {
        String oldTo = to;
        to = newTo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__TO, oldTo, to));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getValues() {
        return values;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setValues(String newValues) {
        String oldValues = values;
        values = newValues;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Smil20Package.ANIMATE_COLOR_PROTOTYPE__VALUES, oldValues, values));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE:
                return getAccumulate();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE:
                return getAdditive();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_NAME:
                return getAttributeName();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE:
                return getAttributeType();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__BY:
                return getBy();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__FROM:
                return getFrom();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__TO:
                return getTo();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__VALUES:
                return getValues();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE:
                setAccumulate((AccumulateType)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE:
                setAdditive((AdditiveType)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_NAME:
                setAttributeName((String)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE:
                setAttributeType((AttributeTypeType)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__BY:
                setBy((String)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__FROM:
                setFrom((String)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__TO:
                setTo((String)newValue);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__VALUES:
                setValues((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE:
                unsetAccumulate();
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE:
                unsetAdditive();
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_NAME:
                setAttributeName(ATTRIBUTE_NAME_EDEFAULT);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE:
                unsetAttributeType();
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__BY:
                setBy(BY_EDEFAULT);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__FROM:
                setFrom(FROM_EDEFAULT);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__TO:
                setTo(TO_EDEFAULT);
                return;
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__VALUES:
                setValues(VALUES_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ACCUMULATE:
                return isSetAccumulate();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ADDITIVE:
                return isSetAdditive();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_NAME:
                return ATTRIBUTE_NAME_EDEFAULT == null ? attributeName != null : !ATTRIBUTE_NAME_EDEFAULT.equals(attributeName);
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__ATTRIBUTE_TYPE:
                return isSetAttributeType();
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__BY:
                return BY_EDEFAULT == null ? by != null : !BY_EDEFAULT.equals(by);
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__FROM:
                return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__TO:
                return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
            case Smil20Package.ANIMATE_COLOR_PROTOTYPE__VALUES:
                return VALUES_EDEFAULT == null ? values != null : !VALUES_EDEFAULT.equals(values);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (accumulate: ");
        if (accumulateESet) result.append(accumulate); else result.append("<unset>");
        result.append(", additive: ");
        if (additiveESet) result.append(additive); else result.append("<unset>");
        result.append(", attributeName: ");
        result.append(attributeName);
        result.append(", attributeType: ");
        if (attributeTypeESet) result.append(attributeType); else result.append("<unset>");
        result.append(", by: ");
        result.append(by);
        result.append(", from: ");
        result.append(from);
        result.append(", to: ");
        result.append(to);
        result.append(", values: ");
        result.append(values);
        result.append(')');
        return result.toString();
    }

} //AnimateColorPrototypeImpl
