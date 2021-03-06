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
package org.eclipse.papyrus.uml.diagram.common.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.papyrus.sysml.diagram.common.preferences.ILabelPreferenceConstants;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Semantic Parser for {@link Reception}
 */
public class ReceptionLabelParser extends NamedElementLabelParser {

	/** The String format for displaying a Property with visibility */
	protected static final String PREFIX = "\u00ABsignal\u00BB ";

	/** The String format for displaying a Property label with its name */
	protected static final String NAME_FORMAT = "%s%s";

	/** The String format for displaying a Property label with its type */
	protected static final String TYPE_FORMAT = "%s: %s";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintString(IAdaptable element, int flags) {
		String result = PREFIX;
		EObject eObject = (EObject)element.getAdapter(EObject.class);

		if((eObject != null) && (eObject instanceof Reception)) {

			Reception reception = (Reception)eObject;

			// manage name
			if(((flags & ILabelPreferenceConstants.DISP_NAME) == ILabelPreferenceConstants.DISP_NAME) && (reception.isSetName())) {
				String name = reception.getName();
				result = String.format(NAME_FORMAT, result, name);
			}

			// manage signal
			if(((flags & ILabelPreferenceConstants.DISP_SIGNAL) == ILabelPreferenceConstants.DISP_SIGNAL)) {
				String type = "<Undefined>";
				if(reception.getSignal() != null) {
					type = reception.getSignal().getName();
				}
				result = String.format(TYPE_FORMAT, result, type);
			}
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAffectingEvent(Object event, int flags) {

		if(event instanceof Notification) {
			Object feature = ((Notification)event).getFeature();
			if(feature instanceof EStructuralFeature) {
				return UMLPackage.eINSTANCE.getNamedElement_Name().equals(feature) || UMLPackage.eINSTANCE.getReception_Signal().equals(feature);
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EObject> getSemanticElementsBeingParsed(EObject element) {
		List<EObject> semanticElementsBeingParsed = new ArrayList<EObject>();

		if((element != null) && (element instanceof Reception)) {
			Reception semElement = (Reception)element;

			semanticElementsBeingParsed.add(semElement);
			if(semElement.getSignal() != null) {
				semanticElementsBeingParsed.add(semElement.getSignal());
			}
		}
		return semanticElementsBeingParsed;
	}
}
