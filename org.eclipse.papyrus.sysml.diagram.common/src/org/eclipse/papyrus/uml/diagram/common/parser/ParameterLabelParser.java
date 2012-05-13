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
import org.eclipse.papyrus.umlutils.ValueSpecificationUtil;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Semantic Parser for {@link Parameter}
 */
public class ParameterLabelParser extends NamedElementLabelParser {

	/** The String format for displaying a {@link Parameter} with direction */
	protected static final String DIRECTION_FORMAT = "%s %s";

	/** The String format for displaying a {@link Parameter} label with its name */
	protected static final String NAME_FORMAT = "%s%s";

	/** The String format for displaying a {@link Parameter} label with its type */
	protected static final String TYPE_FORMAT = "%s: %s";

	/** The String format for displaying a {@link Parameter} label with its multiplicity */
	protected static final String MULTIPLICITY_FORMAT = "%s [%s..%s]";

	/** The String format for displaying a {@link Parameter} label with its default value */
	protected static final String DEFAULT_VALUE_FORMAT = "%s= %s";

	/** The String format for displaying a {@link Parameter} label with its modifiers */
	protected static final String MODIFIER_FORMAT = "%s{%s}";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintString(IAdaptable element, int flags) {
		String result = "";
		EObject eObject = (EObject)element.getAdapter(EObject.class);

		if((eObject != null) && (eObject instanceof Parameter)) {

			Parameter parameter = (Parameter)eObject;

			// manage direction
			if((flags & ILabelPreferenceConstants.DISP_PARAM_DIRECTION) == ILabelPreferenceConstants.DISP_PARAM_DIRECTION) {
				String direction;
				switch(parameter.getDirection().getValue()) {
				case ParameterDirectionKind.IN:
					direction = "in";
					break;
				case ParameterDirectionKind.OUT:
					direction = "out";
					break;
				case ParameterDirectionKind.INOUT:
					direction = "inout";
					break;
				case ParameterDirectionKind.RETURN:
					direction = "return";
					break;
				default:
					direction = "in";
					break;
				}
				result = String.format(DIRECTION_FORMAT, direction, result);

				// manage name
				if(((flags & ILabelPreferenceConstants.DISP_PARAM_NAME) == ILabelPreferenceConstants.DISP_PARAM_NAME) && (parameter.isSetName())) {
					String name = parameter.getName();
					result = String.format(NAME_FORMAT, result, name);
				}

				// manage type
				if(((flags & ILabelPreferenceConstants.DISP_PARAM_TYPE) == ILabelPreferenceConstants.DISP_PARAM_TYPE)) {
					String type = "<Undefined>";
					if(parameter.getType() != null) {
						type = parameter.getType().getName();
					}
					result = String.format(TYPE_FORMAT, result, type);
				}

				// manage multiplicity
				if(((flags & ILabelPreferenceConstants.DISP_PARAM_MULTIPLICITY) == ILabelPreferenceConstants.DISP_PARAM_MULTIPLICITY) && (parameter.getLower() != 1 || parameter.getUpper() != 1)) {
					String lower = ValueSpecificationUtil.getSpecificationValue(parameter.getLowerValue());
					String upper = ValueSpecificationUtil.getSpecificationValue(parameter.getUpperValue());
					result = String.format(MULTIPLICITY_FORMAT, result, lower, upper);
				}

				// manage default value
				if(((flags & ILabelPreferenceConstants.DISP_PARAM_DEFAULTVALUE) == ILabelPreferenceConstants.DISP_PARAM_DEFAULTVALUE) && ((parameter.getDefaultValue() != null))) {
					ValueSpecification valueSpecification = parameter.getDefaultValue();
					if((valueSpecification instanceof InstanceValue && parameter.getType().equals(valueSpecification.getType())) || !(valueSpecification instanceof InstanceValue)) {
						result = String.format(DEFAULT_VALUE_FORMAT, result, ValueSpecificationUtil.getSpecificationValue(valueSpecification));
					}
				}

				// manage modifier
				if((flags & ILabelPreferenceConstants.DISP_PARAM_MODIFIERS) == ILabelPreferenceConstants.DISP_PARAM_MODIFIERS) {
					StringBuffer sb = new StringBuffer();
					if(parameter.isOrdered()) {
						sb.append(sb.length() == 0 ? "ordered" : ", ordered");
					}
					if(parameter.isUnique()) {
						sb.append(sb.length() == 0 ? "unique" : ", unique");
					}
					if(parameter.isStream()) {
						sb.append(sb.length() == 0 ? "stream" : ", stream");
					}
					if(parameter.isException()) {
						sb.append(sb.length() == 0 ? "exception" : ", exception");
					}
					if(sb.length() != 0) {
						result = String.format(MODIFIER_FORMAT, result, sb.toString());
					}
				}
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
				return UMLPackage.eINSTANCE.getNamedElement_Name().equals(feature) || UMLPackage.eINSTANCE.getTypedElement_Type().equals(feature) || UMLPackage.eINSTANCE.getParameter_Direction().equals(feature) || UMLPackage.eINSTANCE.getParameter_DefaultValue().equals(feature) || UMLPackage.eINSTANCE.getParameter_IsStream().equals(feature) || UMLPackage.eINSTANCE.getParameter_IsException().equals(feature) || UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(feature) || UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(feature) || UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue().equals(feature) || UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue().equals(feature);
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

		if((element != null) && (element instanceof Parameter)) {
			Parameter semElement = (Parameter)element;

			semanticElementsBeingParsed.add(semElement);
			if(semElement.getType() != null) {
				semanticElementsBeingParsed.add(semElement.getType());
			}
			if(semElement.getDefaultValue() != null) {
				semanticElementsBeingParsed.add(semElement.getDefaultValue());
			}
			if(semElement.getLowerValue() != null) {
				semanticElementsBeingParsed.add(semElement.getLowerValue());
			}
			if(semElement.getUpperValue() != null) {
				semanticElementsBeingParsed.add(semElement.getUpperValue());
			}
		}
		return semanticElementsBeingParsed;
	}
}
