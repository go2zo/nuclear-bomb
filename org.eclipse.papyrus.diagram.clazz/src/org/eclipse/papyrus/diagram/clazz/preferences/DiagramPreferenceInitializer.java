/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.diagram.clazz.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.clazz.part.UMLDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();

		PropertyPreferencePage.initDefaults(store);

		DataTypePreferencePage.initDefaults(store);

		CommentPreferencePage.initDefaults(store);

		ClassifierTemplateParameterPreferencePage.initDefaults(store);

		ClassPreferencePage.initDefaults(store);

		EnumerationLiteralPreferencePage.initDefaults(store);

		PackagePreferencePage.initDefaults(store);

		ConnectableElementTemplateParameterPreferencePage.initDefaults(store);

		InterfacePreferencePage.initDefaults(store);

		ShortCutDiagramPreferencePage.initDefaults(store);

		PrimitiveTypePreferencePage.initDefaults(store);

		SlotPreferencePage.initDefaults(store);

		InstanceSpecificationPreferencePage.initDefaults(store);

		TemplateSignaturePreferencePage.initDefaults(store);

		AssociationClassPreferencePage.initDefaults(store);

		OperationPreferencePage.initDefaults(store);

		ModelPreferencePage.initDefaults(store);

		ShapeNamedElementPreferencePage.initDefaults(store);

		AssociationNodePreferencePage.initDefaults(store);

		TimeObservationPreferencePage.initDefaults(store);

		OperationTemplateParameterPreferencePage.initDefaults(store);

		ComponentPreferencePage.initDefaults(store);

		DurationObservationPreferencePage.initDefaults(store);

		SignalPreferencePage.initDefaults(store);

		ReceptionPreferencePage.initDefaults(store);

		TemplateParameterPreferencePage.initDefaults(store);

		EnumerationPreferencePage.initDefaults(store);

		ConstraintPreferencePage.initDefaults(store);

		DefaultNamedElementPreferencePage.initDefaults(store);

		RedefinableTemplateSignaturePreferencePage.initDefaults(store);

		ContainmentCirclePreferencePage.initDefaults(store);

		DependencyNodePreferencePage.initDefaults(store);

		AssociationClassDashedLinkPreferencePage.initDefaults(store);

		DependencyLinkPreferencePage.initDefaults(store);

		TemplateBindingPreferencePage.initDefaults(store);

		PackageImportPreferencePage.initDefaults(store);

		PackageMergePreferencePage.initDefaults(store);

		AbstractionPreferencePage.initDefaults(store);

		ProfileApplicationPreferencePage.initDefaults(store);

		RealizationPreferencePage.initDefaults(store);

		ContainmentSubLinkPreferencePage.initDefaults(store);

		GeneralizationPreferencePage.initDefaults(store);

		DependencyBranchLinkPreferencePage.initDefaults(store);

		UsagePreferencePage.initDefaults(store);

		InstanceSpecificationLinkPreferencePage.initDefaults(store);

		SubstitutionPreferencePage.initDefaults(store);

		ContainmentLinkPreferencePage.initDefaults(store);

		ElementImportPreferencePage.initDefaults(store);

		AssociationLinkPreferencePage.initDefaults(store);

		GeneralizationSetPreferencePage.initDefaults(store);

		InterfaceRealizationPreferencePage.initDefaults(store);

		AssociationBranchLinkPreferencePage.initDefaults(store);

		AssociationClassLinkPreferencePage.initDefaults(store);

	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
