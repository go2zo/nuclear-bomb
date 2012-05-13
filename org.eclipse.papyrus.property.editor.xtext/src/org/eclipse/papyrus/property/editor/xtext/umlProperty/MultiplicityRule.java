/**
 * <copyright>
 * </copyright>
 *

 */
package org.eclipse.papyrus.property.editor.xtext.umlProperty;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.property.editor.xtext.umlProperty.MultiplicityRule#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.UmlPropertyPackage#getMultiplicityRule()
 * @model
 * @generated
 */
public interface MultiplicityRule extends EObject
{
  /**
   * Returns the value of the '<em><b>Bounds</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.papyrus.property.editor.xtext.umlProperty.BoundSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounds</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounds</em>' containment reference list.
   * @see org.eclipse.papyrus.property.editor.xtext.umlProperty.UmlPropertyPackage#getMultiplicityRule_Bounds()
   * @model containment="true"
   * @generated
   */
  EList<BoundSpecification> getBounds();

} // MultiplicityRule
