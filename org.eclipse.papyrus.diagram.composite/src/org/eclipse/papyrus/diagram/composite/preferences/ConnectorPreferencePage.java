/*****************************************************************************
 * Copyright (c) 2009-2011 CEA LIST.
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
package org.eclipse.papyrus.diagram.composite.preferences;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.common.util.StringComparator;
import org.eclipse.papyrus.diagram.composite.edit.parts.CompositeStructureDiagramEditPart;
import org.eclipse.papyrus.diagram.composite.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.preferences.pages.AbstractPapyrusLinkPreferencePage;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;

/**
 * @generated
 */
public class ConnectorPreferencePage extends AbstractPapyrusLinkPreferencePage {

	/**
	 * @generated
	 */
	public ConnectorPreferencePage() {
		super();
		setPreferenceKey(CompositeStructureDiagramEditPart.MODEL_ID + "_Connector");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = CompositeStructureDiagramEditPart.MODEL_ID + "_Connector";
		Map<String, Boolean> map = getStaticLabelVisibilityPreferences();
		for(String role : map.keySet()) {
			String preferenceName = PreferenceConstantHelper.getLabelElementConstant(key, role, PreferenceConstantHelper.LABEL_VISIBILITY);
			store.setDefault(preferenceName, map.get(role));
		}

	}


	/**
	 * @generated
	 */
	private static TreeMap<String, String> getStaticLabelRole() {
		TreeMap<String, String> map = new TreeMap<String, String>(new StringComparator());
		map.put("Stereotype", "");//$NON-NLS-1$ //$NON-NLS-2$
		map.put("Name", "");//$NON-NLS-1$ //$NON-NLS-2$
		map.put("SourceMultiplicity", "");//$NON-NLS-1$ //$NON-NLS-2$
		map.put("TargetMultiplicity", "");//$NON-NLS-1$ //$NON-NLS-2$
		return map;
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticLabelVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		map.put("Stereotype", Boolean.TRUE);


		map.put("Name", Boolean.FALSE);//$NON-NLS-1$


		map.put("SourceMultiplicity", Boolean.FALSE);//$NON-NLS-1$


		map.put("TargetMultiplicity", Boolean.FALSE);//$NON-NLS-1$

		return map;
	}

	/**
	 * @generated
	 */
	protected TreeMap<String, String> getLabelRole() {
		return getStaticLabelRole();
	}


}
