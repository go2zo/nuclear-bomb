/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.portandflows.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.papyrus.sysml.portandflows.FlowDirection;
import org.eclipse.papyrus.sysml.portandflows.FlowProperty;
import org.eclipse.papyrus.sysml.portandflows.PortandflowsPackage;
import org.eclipse.papyrus.sysml.util.SysmlResource;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Flow Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.papyrus.sysml.portandflows.impl.FlowPropertyImpl#getBase_Property <em>Base Property</em>}</li>
 * <li>{@link org.eclipse.papyrus.sysml.portandflows.impl.FlowPropertyImpl#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class FlowPropertyImpl extends EObjectImpl implements FlowProperty {

	/**
	 * The cached value of the '{@link #getBase_Property() <em>Base Property</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase_Property()
	 * @generated
	 * @ordered
	 */
	protected Property base_Property;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final FlowDirection DIRECTION_EDEFAULT = FlowDirection.INOUT;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected FlowDirection direction = DIRECTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FlowPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Property basicGetBase_Property() {
		return base_Property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch(featureID) {
		case PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY:
			if(resolve)
				return getBase_Property();
			return basicGetBase_Property();
		case PortandflowsPackage.FLOW_PROPERTY__DIRECTION:
			return getDirection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch(featureID) {
		case PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY:
			return base_Property != null;
		case PortandflowsPackage.FLOW_PROPERTY__DIRECTION:
			return direction != DIRECTION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch(featureID) {
		case PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY:
			setBase_Property((Property)newValue);
			return;
		case PortandflowsPackage.FLOW_PROPERTY__DIRECTION:
			setDirection((FlowDirection)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortandflowsPackage.Literals.FLOW_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch(featureID) {
		case PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY:
			setBase_Property((Property)null);
			return;
		case PortandflowsPackage.FLOW_PROPERTY__DIRECTION:
			setDirection(DIRECTION_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Property getBase_Property() {
		if(base_Property != null && base_Property.eIsProxy()) {
			InternalEObject oldBase_Property = (InternalEObject)base_Property;
			base_Property = (Property)eResolveProxy(oldBase_Property);
			if(base_Property != oldBase_Property) {
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY, oldBase_Property, base_Property));
			}
		}
		return base_Property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FlowDirection getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Image getIcon() {

		Image ImageNotFound = null;

		if(getBase_Property() != null) {
			Stereotype st = getBase_Property().getAppliedStereotype(SysmlResource.FLOW_PROPERTY_ID);

			Image defaultImage = st.getIcons().get(0);
			Image image = null;

			if(getDirection() == FlowDirection.OUT) {
				image = st.getIcons().get(1);
			} else if(getDirection() == FlowDirection.INOUT) {
				image = st.getIcons().get(2);
			} else {
				// Default : IN
				image = defaultImage;
			}

			return image;

		} else {
			return ImageNotFound;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase_Property(Property newBase_Property) {
		Property oldBase_Property = base_Property;
		base_Property = newBase_Property;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortandflowsPackage.FLOW_PROPERTY__BASE_PROPERTY, oldBase_Property, base_Property));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDirection(FlowDirection newDirection) {
		FlowDirection oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortandflowsPackage.FLOW_PROPERTY__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} // FlowPropertyImpl
