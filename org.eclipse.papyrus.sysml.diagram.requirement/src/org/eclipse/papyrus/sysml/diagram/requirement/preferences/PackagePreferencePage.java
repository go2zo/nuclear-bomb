package org.eclipse.papyrus.sysml.diagram.requirement.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.clazz.part.Messages;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.sysml.diagram.requirement.edit.part.RequirementDiagramEditPart;


/**
 * Preference Page for Class usage in Requirement Diagram
 * 
 */
public class PackagePreferencePage extends RequirementDiagramNodePreferencePage {

	/**
	 * the list of the compartments for this node
	 */
	public static final String compartments[] = { Messages.PackagePackageableElementCompartmentEditPartCN_title };


	/**
	 * Constructor.
	 * 
	 */
	public PackagePreferencePage() {
		super();
		setPreferenceKey(RequirementDiagramEditPart.DIAGRAM_ID + "_Package"); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param store
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = RequirementDiagramEditPart.DIAGRAM_ID + "_Package";
		// set the true value for the compartment visibility
		for(String name : compartments) {
			String preferenceName = PreferenceConstantHelper.getCompartmentElementConstant(key, name, PreferenceConstantHelper.COMPARTMENT_VISIBILITY);
			store.setDefault(preferenceName, true);
		}
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.preferences.pages.AbstractPapyrusNodePreferencePage#initializeCompartmentsList()
	 * 
	 */
	@Override
	protected void initializeCompartmentsList() {
		for(String name : compartments) {
			this.compartmentsList.add(name);
		}
	}
}
