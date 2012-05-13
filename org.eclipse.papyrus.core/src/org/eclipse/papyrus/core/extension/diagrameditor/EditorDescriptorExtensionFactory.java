/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin  Cedric.dumoulin@lifl.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.core.extension.diagrameditor;

import static org.eclipse.papyrus.core.Activator.log;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.papyrus.core.extension.BadNameExtensionException;
import org.eclipse.papyrus.core.extension.ExtensionUtils;
import org.eclipse.papyrus.core.extension.ExtensionException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * A factory used to create editor descriptor object from Eclipse extensions points elements.
 * 
 *@author Cedric Dumoulin
 *@author Patrick Tessier
 */
public class EditorDescriptorExtensionFactory extends ExtensionUtils {

	/** singleton eINSTANCE of this class */
	public final static EditorDescriptorExtensionFactory eINSTANCE = new EditorDescriptorExtensionFactory();

	/** constant for the editor diagram **/
	public final static String EDITOR_DIAGRAM_EXTENSIONPOINT = "editorDiagram";

	/** constant for the attribute factoryClass **/
	public final static String FACTORYCLASS_ATTRIBUTE = "factoryClass";

	/** constant for the attribute contextId **/
	public final static String ACTIONBARCONTRIBUTORID_ATTRIBUTE = "actionBarContributorId";

	/** constant for the attribute icon **/
	public final static String ICON_ATTRIBUTE = "icon";

	/**
	 * @return the eINSTANCE
	 */
	public static EditorDescriptorExtensionFactory getInstance() {
		return eINSTANCE;
	}

	/**
	 * Create a descriptor instance corresponding to the ConfigurationElement.
	 * 
	 * @param element
	 *        an {@link IConfigurationElement} see eclipse extension point
	 * @return a nestedEditorDescriptor strucure that contains information to create diagrams
	 * @throws BadNameExtensionException
	 */
	@SuppressWarnings("unchecked")
	public EditorDescriptor createNestedEditorDescriptor(IConfigurationElement element) throws ExtensionException {
		EditorDescriptor res;

		checkTagName(element, EDITOR_DIAGRAM_EXTENSIONPOINT);

		res = new EditorDescriptor();
		res.setEditorFactoryClass((Class<IPluggableEditorFactory>)parseClass(element, FACTORYCLASS_ATTRIBUTE, EDITOR_DIAGRAM_EXTENSIONPOINT));
		res.setActionBarContributorId(element.getAttribute(ACTIONBARCONTRIBUTORID_ATTRIBUTE));
		String iconPath = element.getAttribute(ICON_ATTRIBUTE);
		if(iconPath != null) {
			res.setIcon(AbstractUIPlugin.imageDescriptorFromPlugin(element.getNamespaceIdentifier(), iconPath));
		}

		if(log.isDebugEnabled()) {
			log.debug("Read editor descriptor " + res);
		}
		return res;
	}
}
