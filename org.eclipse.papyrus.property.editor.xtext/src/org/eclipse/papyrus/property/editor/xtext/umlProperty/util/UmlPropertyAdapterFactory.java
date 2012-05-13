/**
 * <copyright>
 * </copyright>
 *

 */
package org.eclipse.papyrus.property.editor.xtext.umlProperty.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.property.editor.xtext.umlProperty.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.UmlPropertyPackage
 * @generated
 */
public class UmlPropertyAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static UmlPropertyPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UmlPropertyAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = UmlPropertyPackage.eINSTANCE;
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
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
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
  protected UmlPropertySwitch<Adapter> modelSwitch =
    new UmlPropertySwitch<Adapter>()
    {
      @Override
      public Adapter casePropertyRule(PropertyRule object)
      {
        return createPropertyRuleAdapter();
      }
      @Override
      public Adapter caseTypeRule(TypeRule object)
      {
        return createTypeRuleAdapter();
      }
      @Override
      public Adapter caseQualifiedName(QualifiedName object)
      {
        return createQualifiedNameAdapter();
      }
      @Override
      public Adapter caseMultiplicityRule(MultiplicityRule object)
      {
        return createMultiplicityRuleAdapter();
      }
      @Override
      public Adapter caseBoundSpecification(BoundSpecification object)
      {
        return createBoundSpecificationAdapter();
      }
      @Override
      public Adapter caseModifiersRule(ModifiersRule object)
      {
        return createModifiersRuleAdapter();
      }
      @Override
      public Adapter caseModifierSpecification(ModifierSpecification object)
      {
        return createModifierSpecificationAdapter();
      }
      @Override
      public Adapter caseRedefinesRule(RedefinesRule object)
      {
        return createRedefinesRuleAdapter();
      }
      @Override
      public Adapter caseSubsetsRule(SubsetsRule object)
      {
        return createSubsetsRuleAdapter();
      }
      @Override
      public Adapter caseDefaultValueRule(DefaultValueRule object)
      {
        return createDefaultValueRuleAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
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
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.PropertyRule <em>Property Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.PropertyRule
   * @generated
   */
  public Adapter createPropertyRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.TypeRule <em>Type Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.TypeRule
   * @generated
   */
  public Adapter createTypeRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.QualifiedName <em>Qualified Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.QualifiedName
   * @generated
   */
  public Adapter createQualifiedNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.MultiplicityRule <em>Multiplicity Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.MultiplicityRule
   * @generated
   */
  public Adapter createMultiplicityRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.BoundSpecification <em>Bound Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.BoundSpecification
   * @generated
   */
  public Adapter createBoundSpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.ModifiersRule <em>Modifiers Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.ModifiersRule
   * @generated
   */
  public Adapter createModifiersRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.ModifierSpecification <em>Modifier Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.ModifierSpecification
   * @generated
   */
  public Adapter createModifierSpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.RedefinesRule <em>Redefines Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.RedefinesRule
   * @generated
   */
  public Adapter createRedefinesRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.SubsetsRule <em>Subsets Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.SubsetsRule
   * @generated
   */
  public Adapter createSubsetsRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.DefaultValueRule <em>Default Value Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.DefaultValueRule
   * @generated
   */
  public Adapter createDefaultValueRuleAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //UmlPropertyAdapterFactory
