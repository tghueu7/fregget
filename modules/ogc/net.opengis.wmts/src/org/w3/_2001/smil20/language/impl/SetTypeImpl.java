/**
 */
package org.w3._2001.smil20.language.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.w3._2001.smil20.FillDefaultType;
import org.w3._2001.smil20.FillTimingAttrsType;
import org.w3._2001.smil20.RestartDefaultType;
import org.w3._2001.smil20.RestartTimingType;
import org.w3._2001.smil20.SyncBehaviorDefaultType;
import org.w3._2001.smil20.SyncBehaviorType;

import org.w3._2001.smil20.impl.SetPrototypeImpl;

import org.w3._2001.smil20.language.LanguagePackage;
import org.w3._2001.smil20.language.SetType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getAlt <em>Alt</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getDur <em>Dur</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getFillDefault <em>Fill Default</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getLongdesc <em>Longdesc</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getRepeat <em>Repeat</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getRepeatCount <em>Repeat Count</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getRepeatDur <em>Repeat Dur</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getRestart <em>Restart</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getRestartDefault <em>Restart Default</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#isSkipContent <em>Skip Content</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getSyncBehavior <em>Sync Behavior</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getSyncBehaviorDefault <em>Sync Behavior Default</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getSyncTolerance <em>Sync Tolerance</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getSyncToleranceDefault <em>Sync Tolerance Default</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getTargetElement <em>Target Element</em>}</li>
 *   <li>{@link org.w3._2001.smil20.language.impl.SetTypeImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SetTypeImpl extends SetPrototypeImpl implements SetType {
    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap group;

    /**
     * The default value of the '{@link #getAlt() <em>Alt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlt()
     * @generated
     * @ordered
     */
    protected static final String ALT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAlt() <em>Alt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlt()
     * @generated
     * @ordered
     */
    protected String alt = ALT_EDEFAULT;

    /**
     * The default value of the '{@link #getBegin() <em>Begin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
    protected static final String BEGIN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBegin() <em>Begin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
    protected String begin = BEGIN_EDEFAULT;

    /**
     * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClass_()
     * @generated
     * @ordered
     */
    protected static final String CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClass_()
     * @generated
     * @ordered
     */
    protected String class_ = CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getDur() <em>Dur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDur()
     * @generated
     * @ordered
     */
    protected static final String DUR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDur() <em>Dur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDur()
     * @generated
     * @ordered
     */
    protected String dur = DUR_EDEFAULT;

    /**
     * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected static final String END_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected String end = END_EDEFAULT;

    /**
     * The default value of the '{@link #getFill() <em>Fill</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFill()
     * @generated
     * @ordered
     */
    protected static final FillTimingAttrsType FILL_EDEFAULT = FillTimingAttrsType.DEFAULT;

    /**
     * The cached value of the '{@link #getFill() <em>Fill</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFill()
     * @generated
     * @ordered
     */
    protected FillTimingAttrsType fill = FILL_EDEFAULT;

    /**
     * This is true if the Fill attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean fillESet;

    /**
     * The default value of the '{@link #getFillDefault() <em>Fill Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFillDefault()
     * @generated
     * @ordered
     */
    protected static final FillDefaultType FILL_DEFAULT_EDEFAULT = FillDefaultType.INHERIT;

    /**
     * The cached value of the '{@link #getFillDefault() <em>Fill Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFillDefault()
     * @generated
     * @ordered
     */
    protected FillDefaultType fillDefault = FILL_DEFAULT_EDEFAULT;

    /**
     * This is true if the Fill Default attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean fillDefaultESet;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected static final String LANG_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected String lang = LANG_EDEFAULT;

    /**
     * The default value of the '{@link #getLongdesc() <em>Longdesc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongdesc()
     * @generated
     * @ordered
     */
    protected static final String LONGDESC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLongdesc() <em>Longdesc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongdesc()
     * @generated
     * @ordered
     */
    protected String longdesc = LONGDESC_EDEFAULT;

    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final String MAX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected String max = MAX_EDEFAULT;

    /**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected static final String MIN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected String min = MIN_EDEFAULT;

    /**
     * The default value of the '{@link #getRepeat() <em>Repeat</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeat()
     * @generated
     * @ordered
     */
    protected static final BigInteger REPEAT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepeat() <em>Repeat</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeat()
     * @generated
     * @ordered
     */
    protected BigInteger repeat = REPEAT_EDEFAULT;

    /**
     * The default value of the '{@link #getRepeatCount() <em>Repeat Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeatCount()
     * @generated
     * @ordered
     */
    protected static final BigDecimal REPEAT_COUNT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepeatCount() <em>Repeat Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeatCount()
     * @generated
     * @ordered
     */
    protected BigDecimal repeatCount = REPEAT_COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getRepeatDur() <em>Repeat Dur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeatDur()
     * @generated
     * @ordered
     */
    protected static final String REPEAT_DUR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepeatDur() <em>Repeat Dur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepeatDur()
     * @generated
     * @ordered
     */
    protected String repeatDur = REPEAT_DUR_EDEFAULT;

    /**
     * The default value of the '{@link #getRestart() <em>Restart</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRestart()
     * @generated
     * @ordered
     */
    protected static final RestartTimingType RESTART_EDEFAULT = RestartTimingType.DEFAULT;

    /**
     * The cached value of the '{@link #getRestart() <em>Restart</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRestart()
     * @generated
     * @ordered
     */
    protected RestartTimingType restart = RESTART_EDEFAULT;

    /**
     * This is true if the Restart attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean restartESet;

    /**
     * The default value of the '{@link #getRestartDefault() <em>Restart Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRestartDefault()
     * @generated
     * @ordered
     */
    protected static final RestartDefaultType RESTART_DEFAULT_EDEFAULT = RestartDefaultType.INHERIT;

    /**
     * The cached value of the '{@link #getRestartDefault() <em>Restart Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRestartDefault()
     * @generated
     * @ordered
     */
    protected RestartDefaultType restartDefault = RESTART_DEFAULT_EDEFAULT;

    /**
     * This is true if the Restart Default attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean restartDefaultESet;

    /**
     * The default value of the '{@link #isSkipContent() <em>Skip Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSkipContent()
     * @generated
     * @ordered
     */
    protected static final boolean SKIP_CONTENT_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSkipContent() <em>Skip Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSkipContent()
     * @generated
     * @ordered
     */
    protected boolean skipContent = SKIP_CONTENT_EDEFAULT;

    /**
     * This is true if the Skip Content attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean skipContentESet;

    /**
     * The default value of the '{@link #getSyncBehavior() <em>Sync Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncBehavior()
     * @generated
     * @ordered
     */
    protected static final SyncBehaviorType SYNC_BEHAVIOR_EDEFAULT = SyncBehaviorType.DEFAULT;

    /**
     * The cached value of the '{@link #getSyncBehavior() <em>Sync Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncBehavior()
     * @generated
     * @ordered
     */
    protected SyncBehaviorType syncBehavior = SYNC_BEHAVIOR_EDEFAULT;

    /**
     * This is true if the Sync Behavior attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean syncBehaviorESet;

    /**
     * The default value of the '{@link #getSyncBehaviorDefault() <em>Sync Behavior Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncBehaviorDefault()
     * @generated
     * @ordered
     */
    protected static final SyncBehaviorDefaultType SYNC_BEHAVIOR_DEFAULT_EDEFAULT = SyncBehaviorDefaultType.INHERIT;

    /**
     * The cached value of the '{@link #getSyncBehaviorDefault() <em>Sync Behavior Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncBehaviorDefault()
     * @generated
     * @ordered
     */
    protected SyncBehaviorDefaultType syncBehaviorDefault = SYNC_BEHAVIOR_DEFAULT_EDEFAULT;

    /**
     * This is true if the Sync Behavior Default attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean syncBehaviorDefaultESet;

    /**
     * The default value of the '{@link #getSyncTolerance() <em>Sync Tolerance</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncTolerance()
     * @generated
     * @ordered
     */
    protected static final String SYNC_TOLERANCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSyncTolerance() <em>Sync Tolerance</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncTolerance()
     * @generated
     * @ordered
     */
    protected String syncTolerance = SYNC_TOLERANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getSyncToleranceDefault() <em>Sync Tolerance Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncToleranceDefault()
     * @generated
     * @ordered
     */
    protected static final String SYNC_TOLERANCE_DEFAULT_EDEFAULT = "inherit";

    /**
     * The cached value of the '{@link #getSyncToleranceDefault() <em>Sync Tolerance Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSyncToleranceDefault()
     * @generated
     * @ordered
     */
    protected String syncToleranceDefault = SYNC_TOLERANCE_DEFAULT_EDEFAULT;

    /**
     * This is true if the Sync Tolerance Default attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean syncToleranceDefaultESet;

    /**
     * The default value of the '{@link #getTargetElement() <em>Target Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetElement()
     * @generated
     * @ordered
     */
    protected static final String TARGET_ELEMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetElement() <em>Target Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetElement()
     * @generated
     * @ordered
     */
    protected String targetElement = TARGET_ELEMENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnyAttribute()
     * @generated
     * @ordered
     */
    protected FeatureMap anyAttribute;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SetTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LanguagePackage.Literals.SET_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, LanguagePackage.SET_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap)getGroup().<FeatureMap.Entry>list(LanguagePackage.Literals.SET_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getAlt() {
        return alt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAlt(String newAlt) {
        String oldAlt = alt;
        alt = newAlt;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__ALT, oldAlt, alt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBegin() {
        return begin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBegin(String newBegin) {
        String oldBegin = begin;
        begin = newBegin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__BEGIN, oldBegin, begin));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getClass_() {
        return class_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setClass(String newClass) {
        String oldClass = class_;
        class_ = newClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__CLASS, oldClass, class_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDur() {
        return dur;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDur(String newDur) {
        String oldDur = dur;
        dur = newDur;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__DUR, oldDur, dur));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getEnd() {
        return end;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEnd(String newEnd) {
        String oldEnd = end;
        end = newEnd;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__END, oldEnd, end));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FillTimingAttrsType getFill() {
        return fill;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFill(FillTimingAttrsType newFill) {
        FillTimingAttrsType oldFill = fill;
        fill = newFill == null ? FILL_EDEFAULT : newFill;
        boolean oldFillESet = fillESet;
        fillESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__FILL, oldFill, fill, !oldFillESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetFill() {
        FillTimingAttrsType oldFill = fill;
        boolean oldFillESet = fillESet;
        fill = FILL_EDEFAULT;
        fillESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__FILL, oldFill, FILL_EDEFAULT, oldFillESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetFill() {
        return fillESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FillDefaultType getFillDefault() {
        return fillDefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFillDefault(FillDefaultType newFillDefault) {
        FillDefaultType oldFillDefault = fillDefault;
        fillDefault = newFillDefault == null ? FILL_DEFAULT_EDEFAULT : newFillDefault;
        boolean oldFillDefaultESet = fillDefaultESet;
        fillDefaultESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__FILL_DEFAULT, oldFillDefault, fillDefault, !oldFillDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetFillDefault() {
        FillDefaultType oldFillDefault = fillDefault;
        boolean oldFillDefaultESet = fillDefaultESet;
        fillDefault = FILL_DEFAULT_EDEFAULT;
        fillDefaultESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__FILL_DEFAULT, oldFillDefault, FILL_DEFAULT_EDEFAULT, oldFillDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetFillDefault() {
        return fillDefaultESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLang() {
        return lang;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLang(String newLang) {
        String oldLang = lang;
        lang = newLang;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__LANG, oldLang, lang));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLongdesc() {
        return longdesc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLongdesc(String newLongdesc) {
        String oldLongdesc = longdesc;
        longdesc = newLongdesc;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__LONGDESC, oldLongdesc, longdesc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMax(String newMax) {
        String oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMin(String newMin) {
        String oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__MIN, oldMin, min));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BigInteger getRepeat() {
        return repeat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRepeat(BigInteger newRepeat) {
        BigInteger oldRepeat = repeat;
        repeat = newRepeat;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__REPEAT, oldRepeat, repeat));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BigDecimal getRepeatCount() {
        return repeatCount;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRepeatCount(BigDecimal newRepeatCount) {
        BigDecimal oldRepeatCount = repeatCount;
        repeatCount = newRepeatCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__REPEAT_COUNT, oldRepeatCount, repeatCount));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRepeatDur() {
        return repeatDur;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRepeatDur(String newRepeatDur) {
        String oldRepeatDur = repeatDur;
        repeatDur = newRepeatDur;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__REPEAT_DUR, oldRepeatDur, repeatDur));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RestartTimingType getRestart() {
        return restart;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRestart(RestartTimingType newRestart) {
        RestartTimingType oldRestart = restart;
        restart = newRestart == null ? RESTART_EDEFAULT : newRestart;
        boolean oldRestartESet = restartESet;
        restartESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__RESTART, oldRestart, restart, !oldRestartESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetRestart() {
        RestartTimingType oldRestart = restart;
        boolean oldRestartESet = restartESet;
        restart = RESTART_EDEFAULT;
        restartESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__RESTART, oldRestart, RESTART_EDEFAULT, oldRestartESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetRestart() {
        return restartESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RestartDefaultType getRestartDefault() {
        return restartDefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRestartDefault(RestartDefaultType newRestartDefault) {
        RestartDefaultType oldRestartDefault = restartDefault;
        restartDefault = newRestartDefault == null ? RESTART_DEFAULT_EDEFAULT : newRestartDefault;
        boolean oldRestartDefaultESet = restartDefaultESet;
        restartDefaultESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__RESTART_DEFAULT, oldRestartDefault, restartDefault, !oldRestartDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetRestartDefault() {
        RestartDefaultType oldRestartDefault = restartDefault;
        boolean oldRestartDefaultESet = restartDefaultESet;
        restartDefault = RESTART_DEFAULT_EDEFAULT;
        restartDefaultESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__RESTART_DEFAULT, oldRestartDefault, RESTART_DEFAULT_EDEFAULT, oldRestartDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetRestartDefault() {
        return restartDefaultESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSkipContent() {
        return skipContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSkipContent(boolean newSkipContent) {
        boolean oldSkipContent = skipContent;
        skipContent = newSkipContent;
        boolean oldSkipContentESet = skipContentESet;
        skipContentESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__SKIP_CONTENT, oldSkipContent, skipContent, !oldSkipContentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetSkipContent() {
        boolean oldSkipContent = skipContent;
        boolean oldSkipContentESet = skipContentESet;
        skipContent = SKIP_CONTENT_EDEFAULT;
        skipContentESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__SKIP_CONTENT, oldSkipContent, SKIP_CONTENT_EDEFAULT, oldSkipContentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetSkipContent() {
        return skipContentESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SyncBehaviorType getSyncBehavior() {
        return syncBehavior;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSyncBehavior(SyncBehaviorType newSyncBehavior) {
        SyncBehaviorType oldSyncBehavior = syncBehavior;
        syncBehavior = newSyncBehavior == null ? SYNC_BEHAVIOR_EDEFAULT : newSyncBehavior;
        boolean oldSyncBehaviorESet = syncBehaviorESet;
        syncBehaviorESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__SYNC_BEHAVIOR, oldSyncBehavior, syncBehavior, !oldSyncBehaviorESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetSyncBehavior() {
        SyncBehaviorType oldSyncBehavior = syncBehavior;
        boolean oldSyncBehaviorESet = syncBehaviorESet;
        syncBehavior = SYNC_BEHAVIOR_EDEFAULT;
        syncBehaviorESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__SYNC_BEHAVIOR, oldSyncBehavior, SYNC_BEHAVIOR_EDEFAULT, oldSyncBehaviorESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetSyncBehavior() {
        return syncBehaviorESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SyncBehaviorDefaultType getSyncBehaviorDefault() {
        return syncBehaviorDefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSyncBehaviorDefault(SyncBehaviorDefaultType newSyncBehaviorDefault) {
        SyncBehaviorDefaultType oldSyncBehaviorDefault = syncBehaviorDefault;
        syncBehaviorDefault = newSyncBehaviorDefault == null ? SYNC_BEHAVIOR_DEFAULT_EDEFAULT : newSyncBehaviorDefault;
        boolean oldSyncBehaviorDefaultESet = syncBehaviorDefaultESet;
        syncBehaviorDefaultESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT, oldSyncBehaviorDefault, syncBehaviorDefault, !oldSyncBehaviorDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetSyncBehaviorDefault() {
        SyncBehaviorDefaultType oldSyncBehaviorDefault = syncBehaviorDefault;
        boolean oldSyncBehaviorDefaultESet = syncBehaviorDefaultESet;
        syncBehaviorDefault = SYNC_BEHAVIOR_DEFAULT_EDEFAULT;
        syncBehaviorDefaultESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT, oldSyncBehaviorDefault, SYNC_BEHAVIOR_DEFAULT_EDEFAULT, oldSyncBehaviorDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetSyncBehaviorDefault() {
        return syncBehaviorDefaultESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSyncTolerance() {
        return syncTolerance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSyncTolerance(String newSyncTolerance) {
        String oldSyncTolerance = syncTolerance;
        syncTolerance = newSyncTolerance;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__SYNC_TOLERANCE, oldSyncTolerance, syncTolerance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSyncToleranceDefault() {
        return syncToleranceDefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSyncToleranceDefault(String newSyncToleranceDefault) {
        String oldSyncToleranceDefault = syncToleranceDefault;
        syncToleranceDefault = newSyncToleranceDefault;
        boolean oldSyncToleranceDefaultESet = syncToleranceDefaultESet;
        syncToleranceDefaultESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT, oldSyncToleranceDefault, syncToleranceDefault, !oldSyncToleranceDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetSyncToleranceDefault() {
        String oldSyncToleranceDefault = syncToleranceDefault;
        boolean oldSyncToleranceDefaultESet = syncToleranceDefaultESet;
        syncToleranceDefault = SYNC_TOLERANCE_DEFAULT_EDEFAULT;
        syncToleranceDefaultESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT, oldSyncToleranceDefault, SYNC_TOLERANCE_DEFAULT_EDEFAULT, oldSyncToleranceDefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetSyncToleranceDefault() {
        return syncToleranceDefaultESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTargetElement() {
        return targetElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetElement(String newTargetElement) {
        String oldTargetElement = targetElement;
        targetElement = newTargetElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LanguagePackage.SET_TYPE__TARGET_ELEMENT, oldTargetElement, targetElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, LanguagePackage.SET_TYPE__ANY_ATTRIBUTE);
        }
        return anyAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case LanguagePackage.SET_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case LanguagePackage.SET_TYPE__ANY:
                return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
            case LanguagePackage.SET_TYPE__ANY_ATTRIBUTE:
                return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case LanguagePackage.SET_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case LanguagePackage.SET_TYPE__ANY:
                if (coreType) return getAny();
                return ((FeatureMap.Internal)getAny()).getWrapper();
            case LanguagePackage.SET_TYPE__ALT:
                return getAlt();
            case LanguagePackage.SET_TYPE__BEGIN:
                return getBegin();
            case LanguagePackage.SET_TYPE__CLASS:
                return getClass_();
            case LanguagePackage.SET_TYPE__DUR:
                return getDur();
            case LanguagePackage.SET_TYPE__END:
                return getEnd();
            case LanguagePackage.SET_TYPE__FILL:
                return getFill();
            case LanguagePackage.SET_TYPE__FILL_DEFAULT:
                return getFillDefault();
            case LanguagePackage.SET_TYPE__ID:
                return getId();
            case LanguagePackage.SET_TYPE__LANG:
                return getLang();
            case LanguagePackage.SET_TYPE__LONGDESC:
                return getLongdesc();
            case LanguagePackage.SET_TYPE__MAX:
                return getMax();
            case LanguagePackage.SET_TYPE__MIN:
                return getMin();
            case LanguagePackage.SET_TYPE__REPEAT:
                return getRepeat();
            case LanguagePackage.SET_TYPE__REPEAT_COUNT:
                return getRepeatCount();
            case LanguagePackage.SET_TYPE__REPEAT_DUR:
                return getRepeatDur();
            case LanguagePackage.SET_TYPE__RESTART:
                return getRestart();
            case LanguagePackage.SET_TYPE__RESTART_DEFAULT:
                return getRestartDefault();
            case LanguagePackage.SET_TYPE__SKIP_CONTENT:
                return isSkipContent();
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR:
                return getSyncBehavior();
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT:
                return getSyncBehaviorDefault();
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE:
                return getSyncTolerance();
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT:
                return getSyncToleranceDefault();
            case LanguagePackage.SET_TYPE__TARGET_ELEMENT:
                return getTargetElement();
            case LanguagePackage.SET_TYPE__ANY_ATTRIBUTE:
                if (coreType) return getAnyAttribute();
                return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
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
            case LanguagePackage.SET_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case LanguagePackage.SET_TYPE__ANY:
                ((FeatureMap.Internal)getAny()).set(newValue);
                return;
            case LanguagePackage.SET_TYPE__ALT:
                setAlt((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__BEGIN:
                setBegin((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__CLASS:
                setClass((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__DUR:
                setDur((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__END:
                setEnd((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__FILL:
                setFill((FillTimingAttrsType)newValue);
                return;
            case LanguagePackage.SET_TYPE__FILL_DEFAULT:
                setFillDefault((FillDefaultType)newValue);
                return;
            case LanguagePackage.SET_TYPE__ID:
                setId((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__LANG:
                setLang((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__LONGDESC:
                setLongdesc((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__MAX:
                setMax((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__MIN:
                setMin((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__REPEAT:
                setRepeat((BigInteger)newValue);
                return;
            case LanguagePackage.SET_TYPE__REPEAT_COUNT:
                setRepeatCount((BigDecimal)newValue);
                return;
            case LanguagePackage.SET_TYPE__REPEAT_DUR:
                setRepeatDur((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__RESTART:
                setRestart((RestartTimingType)newValue);
                return;
            case LanguagePackage.SET_TYPE__RESTART_DEFAULT:
                setRestartDefault((RestartDefaultType)newValue);
                return;
            case LanguagePackage.SET_TYPE__SKIP_CONTENT:
                setSkipContent((Boolean)newValue);
                return;
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR:
                setSyncBehavior((SyncBehaviorType)newValue);
                return;
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT:
                setSyncBehaviorDefault((SyncBehaviorDefaultType)newValue);
                return;
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE:
                setSyncTolerance((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT:
                setSyncToleranceDefault((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__TARGET_ELEMENT:
                setTargetElement((String)newValue);
                return;
            case LanguagePackage.SET_TYPE__ANY_ATTRIBUTE:
                ((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
            case LanguagePackage.SET_TYPE__GROUP:
                getGroup().clear();
                return;
            case LanguagePackage.SET_TYPE__ANY:
                getAny().clear();
                return;
            case LanguagePackage.SET_TYPE__ALT:
                setAlt(ALT_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__BEGIN:
                setBegin(BEGIN_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__CLASS:
                setClass(CLASS_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__DUR:
                setDur(DUR_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__END:
                setEnd(END_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__FILL:
                unsetFill();
                return;
            case LanguagePackage.SET_TYPE__FILL_DEFAULT:
                unsetFillDefault();
                return;
            case LanguagePackage.SET_TYPE__ID:
                setId(ID_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__LANG:
                setLang(LANG_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__LONGDESC:
                setLongdesc(LONGDESC_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__MAX:
                setMax(MAX_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__MIN:
                setMin(MIN_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__REPEAT:
                setRepeat(REPEAT_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__REPEAT_COUNT:
                setRepeatCount(REPEAT_COUNT_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__REPEAT_DUR:
                setRepeatDur(REPEAT_DUR_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__RESTART:
                unsetRestart();
                return;
            case LanguagePackage.SET_TYPE__RESTART_DEFAULT:
                unsetRestartDefault();
                return;
            case LanguagePackage.SET_TYPE__SKIP_CONTENT:
                unsetSkipContent();
                return;
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR:
                unsetSyncBehavior();
                return;
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT:
                unsetSyncBehaviorDefault();
                return;
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE:
                setSyncTolerance(SYNC_TOLERANCE_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT:
                unsetSyncToleranceDefault();
                return;
            case LanguagePackage.SET_TYPE__TARGET_ELEMENT:
                setTargetElement(TARGET_ELEMENT_EDEFAULT);
                return;
            case LanguagePackage.SET_TYPE__ANY_ATTRIBUTE:
                getAnyAttribute().clear();
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
            case LanguagePackage.SET_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case LanguagePackage.SET_TYPE__ANY:
                return !getAny().isEmpty();
            case LanguagePackage.SET_TYPE__ALT:
                return ALT_EDEFAULT == null ? alt != null : !ALT_EDEFAULT.equals(alt);
            case LanguagePackage.SET_TYPE__BEGIN:
                return BEGIN_EDEFAULT == null ? begin != null : !BEGIN_EDEFAULT.equals(begin);
            case LanguagePackage.SET_TYPE__CLASS:
                return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
            case LanguagePackage.SET_TYPE__DUR:
                return DUR_EDEFAULT == null ? dur != null : !DUR_EDEFAULT.equals(dur);
            case LanguagePackage.SET_TYPE__END:
                return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
            case LanguagePackage.SET_TYPE__FILL:
                return isSetFill();
            case LanguagePackage.SET_TYPE__FILL_DEFAULT:
                return isSetFillDefault();
            case LanguagePackage.SET_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case LanguagePackage.SET_TYPE__LANG:
                return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
            case LanguagePackage.SET_TYPE__LONGDESC:
                return LONGDESC_EDEFAULT == null ? longdesc != null : !LONGDESC_EDEFAULT.equals(longdesc);
            case LanguagePackage.SET_TYPE__MAX:
                return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
            case LanguagePackage.SET_TYPE__MIN:
                return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
            case LanguagePackage.SET_TYPE__REPEAT:
                return REPEAT_EDEFAULT == null ? repeat != null : !REPEAT_EDEFAULT.equals(repeat);
            case LanguagePackage.SET_TYPE__REPEAT_COUNT:
                return REPEAT_COUNT_EDEFAULT == null ? repeatCount != null : !REPEAT_COUNT_EDEFAULT.equals(repeatCount);
            case LanguagePackage.SET_TYPE__REPEAT_DUR:
                return REPEAT_DUR_EDEFAULT == null ? repeatDur != null : !REPEAT_DUR_EDEFAULT.equals(repeatDur);
            case LanguagePackage.SET_TYPE__RESTART:
                return isSetRestart();
            case LanguagePackage.SET_TYPE__RESTART_DEFAULT:
                return isSetRestartDefault();
            case LanguagePackage.SET_TYPE__SKIP_CONTENT:
                return isSetSkipContent();
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR:
                return isSetSyncBehavior();
            case LanguagePackage.SET_TYPE__SYNC_BEHAVIOR_DEFAULT:
                return isSetSyncBehaviorDefault();
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE:
                return SYNC_TOLERANCE_EDEFAULT == null ? syncTolerance != null : !SYNC_TOLERANCE_EDEFAULT.equals(syncTolerance);
            case LanguagePackage.SET_TYPE__SYNC_TOLERANCE_DEFAULT:
                return isSetSyncToleranceDefault();
            case LanguagePackage.SET_TYPE__TARGET_ELEMENT:
                return TARGET_ELEMENT_EDEFAULT == null ? targetElement != null : !TARGET_ELEMENT_EDEFAULT.equals(targetElement);
            case LanguagePackage.SET_TYPE__ANY_ATTRIBUTE:
                return anyAttribute != null && !anyAttribute.isEmpty();
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
        result.append(" (group: ");
        result.append(group);
        result.append(", alt: ");
        result.append(alt);
        result.append(", begin: ");
        result.append(begin);
        result.append(", class: ");
        result.append(class_);
        result.append(", dur: ");
        result.append(dur);
        result.append(", end: ");
        result.append(end);
        result.append(", fill: ");
        if (fillESet) result.append(fill); else result.append("<unset>");
        result.append(", fillDefault: ");
        if (fillDefaultESet) result.append(fillDefault); else result.append("<unset>");
        result.append(", id: ");
        result.append(id);
        result.append(", lang: ");
        result.append(lang);
        result.append(", longdesc: ");
        result.append(longdesc);
        result.append(", max: ");
        result.append(max);
        result.append(", min: ");
        result.append(min);
        result.append(", repeat: ");
        result.append(repeat);
        result.append(", repeatCount: ");
        result.append(repeatCount);
        result.append(", repeatDur: ");
        result.append(repeatDur);
        result.append(", restart: ");
        if (restartESet) result.append(restart); else result.append("<unset>");
        result.append(", restartDefault: ");
        if (restartDefaultESet) result.append(restartDefault); else result.append("<unset>");
        result.append(", skipContent: ");
        if (skipContentESet) result.append(skipContent); else result.append("<unset>");
        result.append(", syncBehavior: ");
        if (syncBehaviorESet) result.append(syncBehavior); else result.append("<unset>");
        result.append(", syncBehaviorDefault: ");
        if (syncBehaviorDefaultESet) result.append(syncBehaviorDefault); else result.append("<unset>");
        result.append(", syncTolerance: ");
        result.append(syncTolerance);
        result.append(", syncToleranceDefault: ");
        if (syncToleranceDefaultESet) result.append(syncToleranceDefault); else result.append("<unset>");
        result.append(", targetElement: ");
        result.append(targetElement);
        result.append(", anyAttribute: ");
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} //SetTypeImpl
