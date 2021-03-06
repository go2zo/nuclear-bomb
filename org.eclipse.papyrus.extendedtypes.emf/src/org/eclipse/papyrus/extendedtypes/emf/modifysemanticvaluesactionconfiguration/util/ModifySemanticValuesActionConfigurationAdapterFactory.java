/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.extendedtypes.ActionConfiguration;
import org.eclipse.papyrus.extendedtypes.PostActionConfiguration;

import org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ModifySemanticValuesActionConfigurationPackage
 * @generated
 */
public class ModifySemanticValuesActionConfigurationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModifySemanticValuesActionConfigurationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModifySemanticValuesActionConfigurationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModifySemanticValuesActionConfigurationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModifySemanticValuesActionConfigurationSwitch<Adapter> modelSwitch =
		new ModifySemanticValuesActionConfigurationSwitch<Adapter>() {
			@Override
			public Adapter caseModifySemanticValuesActionConfiguration(ModifySemanticValuesActionConfiguration object) {
				return createModifySemanticValuesActionConfigurationAdapter();
			}
			@Override
			public Adapter caseFeatureToSet(FeatureToSet object) {
				return createFeatureToSetAdapter();
			}
			@Override
			public Adapter caseFeatureValue(FeatureValue object) {
				return createFeatureValueAdapter();
			}
			@Override
			public Adapter caseDynamicValue(DynamicValue object) {
				return createDynamicValueAdapter();
			}
			@Override
			public Adapter caseConstantValue(ConstantValue object) {
				return createConstantValueAdapter();
			}
			@Override
			public Adapter caseListValue(ListValue object) {
				return createListValueAdapter();
			}
			@Override
			public Adapter caseQueryExecutionValue(QueryExecutionValue object) {
				return createQueryExecutionValueAdapter();
			}
			@Override
			public Adapter caseActionConfiguration(ActionConfiguration object) {
				return createActionConfigurationAdapter();
			}
			@Override
			public Adapter casePostActionConfiguration(PostActionConfiguration object) {
				return createPostActionConfigurationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ModifySemanticValuesActionConfiguration <em>Modify Semantic Values Action Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ModifySemanticValuesActionConfiguration
	 * @generated
	 */
	public Adapter createModifySemanticValuesActionConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.FeatureToSet <em>Feature To Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.FeatureToSet
	 * @generated
	 */
	public Adapter createFeatureToSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.FeatureValue <em>Feature Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.FeatureValue
	 * @generated
	 */
	public Adapter createFeatureValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.DynamicValue <em>Dynamic Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.DynamicValue
	 * @generated
	 */
	public Adapter createDynamicValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ConstantValue <em>Constant Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ConstantValue
	 * @generated
	 */
	public Adapter createConstantValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ListValue <em>List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.ListValue
	 * @generated
	 */
	public Adapter createListValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.QueryExecutionValue <em>Query Execution Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.emf.modifysemanticvaluesactionconfiguration.QueryExecutionValue
	 * @generated
	 */
	public Adapter createQueryExecutionValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.ActionConfiguration <em>Action Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.ActionConfiguration
	 * @generated
	 */
	public Adapter createActionConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.extendedtypes.PostActionConfiguration <em>Post Action Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.extendedtypes.PostActionConfiguration
	 * @generated
	 */
	public Adapter createPostActionConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModifySemanticValuesActionConfigurationAdapterFactory
