package org.eclipse.papyrus.diagram.activity.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.activity.edit.parts.ActivityDiagramEditPart;
import org.eclipse.papyrus.diagram.activity.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;

/**
 * @generated
 */
public class ActivityCNPreferencePage extends AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public static final String compartments[] = { "ActivityFigureContentCompartment", "ActivityFigureParameterCompartment", "ActivityFigurePreconditionCompartment", "ActivityFigurePostCondtionCompartment"

	};

	/**
	 * @generated
	 */
	public ActivityCNPreferencePage() {
		super();
		setPreferenceKey(ActivityDiagramEditPart.MODEL_ID + "_ActivityCN");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.getInstance().ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = ActivityDiagramEditPart.MODEL_ID + "_ActivityCN";
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.WIDTH), 200);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.HEIGHT), 100);

		for(String name : compartments) {
			String preferenceName = PreferenceConstantHelper.getCompartmentElementConstant(key, name, PreferenceConstantHelper.COMPARTMENT_VISIBILITY);
			store.setDefault(preferenceName, true);
		}

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
	@Override
	protected void initializeCompartmentsList() {
		for(String name : compartments) {
			this.compartmentsList.add(name);
		}
	}

}
