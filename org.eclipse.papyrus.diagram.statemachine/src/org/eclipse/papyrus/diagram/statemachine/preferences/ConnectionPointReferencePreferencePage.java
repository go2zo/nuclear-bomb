package org.eclipse.papyrus.diagram.statemachine.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PackageEditPart;
import org.eclipse.papyrus.diagram.statemachine.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;

/**
 * @generated
 */
public class ConnectionPointReferencePreferencePage extends
		AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = PackageEditPart.MODEL_ID + "_ConnectionPointReference";
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.WIDTH), 4);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.HEIGHT), 4);

		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));

		// Set the default for the gradient
		//store.setDefault(org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.GRADIENT_POLICY),false);
		//org.eclipse.papyrus.preferences.utils.GradientPreferenceConverter gradientPreferenceConverter = new  org.eclipse.papyrus.preferences.utils.GradientPreferenceConverter(
		//		new org.eclipse.swt.graphics.RGB(255, 255, 255),
		//		new org.eclipse.swt.graphics.RGB(0, 0, 0), 0, 0);
		//store.setDefault(org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());

	}

	/**
	 * @generated
	 */
	public ConnectionPointReferencePreferencePage() {
		super();
		setPreferenceKey(PackageEditPart.MODEL_ID + "_ConnectionPointReference");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.ID;
	}

}
