/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.paletteconfiguration;

import org.eclipse.emf.common.util.EList;

import org.eclipse.papyrus.core.queries.configuration.QueryConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tool Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.paletteconfiguration.ToolConfiguration#getPreValidationQuery <em>Pre Validation Query</em>}</li>
 *   <li>{@link org.eclipse.papyrus.paletteconfiguration.ToolConfiguration#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.papyrus.paletteconfiguration.ToolConfiguration#getElementDescriptors <em>Element Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.paletteconfiguration.PaletteconfigurationPackage#getToolConfiguration()
 * @model
 * @generated
 */
public interface ToolConfiguration extends LeafConfiguration {
	/**
	 * Returns the value of the '<em><b>Pre Validation Query</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Validation Query</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Validation Query</em>' reference.
	 * @see #setPreValidationQuery(QueryConfiguration)
	 * @see org.eclipse.papyrus.paletteconfiguration.PaletteconfigurationPackage#getToolConfiguration_PreValidationQuery()
	 * @model
	 * @generated
	 */
	QueryConfiguration getPreValidationQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.paletteconfiguration.ToolConfiguration#getPreValidationQuery <em>Pre Validation Query</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Validation Query</em>' reference.
	 * @see #getPreValidationQuery()
	 * @generated
	 */
	void setPreValidationQuery(QueryConfiguration value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"CreationTool"</code>.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.paletteconfiguration.ToolKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.paletteconfiguration.ToolKind
	 * @see #setKind(ToolKind)
	 * @see org.eclipse.papyrus.paletteconfiguration.PaletteconfigurationPackage#getToolConfiguration_Kind()
	 * @model default="CreationTool" required="true"
	 * @generated
	 */
	ToolKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.paletteconfiguration.ToolConfiguration#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.paletteconfiguration.ToolKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ToolKind value);

	/**
	 * Returns the value of the '<em><b>Element Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.paletteconfiguration.ElementDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Descriptors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Descriptors</em>' containment reference list.
	 * @see org.eclipse.papyrus.paletteconfiguration.PaletteconfigurationPackage#getToolConfiguration_ElementDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ElementDescriptor> getElementDescriptors();

} // ToolConfiguration
