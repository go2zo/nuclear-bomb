/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.common.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.sysml.diagram.common.preferences.ILabelPreferenceConstants;
import org.eclipse.papyrus.sysml.requirements.Requirement;
import org.eclipse.papyrus.uml.diagram.common.parser.NamedElementLabelParser;
import org.eclipse.papyrus.umlutils.ElementUtil;
import org.eclipse.uml2.uml.Class;

/**
 * Semantic Parser for {@link Requirement}
 */
public class RequirementLabelParser extends NamedElementLabelParser {

	/** The String format for displaying a Requirement name */
	protected static final String NAME_FORMAT = "%s";

	/** The String format for displaying a FlowProperty with direction */
	protected static final String ID_FORMAT = "%s{id = %s}";

	/**
	 * {@inheritDoc}
	 */
	public String getPrintString(IAdaptable element, int flags) {

		String result = "";
		EObject eObject = (EObject)element.getAdapter(EObject.class);

		if((eObject != null) && (eObject instanceof Class)) {

			Class clazz = (Class)eObject;
			Requirement requirement = ElementUtil.getStereotypeApplication(clazz, Requirement.class);

			// manage name
			if((flags & ILabelPreferenceConstants.DISP_NAME) == ILabelPreferenceConstants.DISP_NAME) {
				String name = clazz.getName();
				result = String.format(NAME_FORMAT, name);
			}


			if(requirement != null) {

				// manage id
				if((flags & ILabelPreferenceConstants.DISP_ID) == ILabelPreferenceConstants.DISP_ID) {
					String id = requirement.getId();
					result = String.format(ID_FORMAT, result, id);
				}
			}
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EObject> getSemanticElementsBeingParsed(EObject element) {
		List<EObject> semanticElementsBeingParsed = new ArrayList<EObject>();

		if((element != null) && (element instanceof Class)) {
			Class semElement = (Class)element;

			semanticElementsBeingParsed.add(semElement);

			Requirement requirement = ElementUtil.getStereotypeApplication(semElement, Requirement.class);
			if(requirement != null) {
				semanticElementsBeingParsed.add(requirement);
			}
		}
		return semanticElementsBeingParsed;
	}
}
