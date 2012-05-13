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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.papyrus.sysml.diagram.common.preferences.ILabelPreferenceConstants;
import org.eclipse.papyrus.sysml.portandflows.FlowDirection;
import org.eclipse.papyrus.sysml.portandflows.FlowPort;
import org.eclipse.papyrus.sysml.portandflows.PortandflowsPackage;
import org.eclipse.papyrus.uml.diagram.common.parser.PropertyLabelParser;
import org.eclipse.papyrus.umlutils.ElementUtil;
import org.eclipse.papyrus.umlutils.ValueSpecificationUtil;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * Semantic Parser for {@link FlowPort}
 */
public class FlowPortLabelParser extends PropertyLabelParser {

	/** The String format for displaying a FlowProperty with direction */
	protected static final String DIRECTION_FORMAT = "%s %s";

	/** The String format for displaying a FlowProperty with direction */
	protected static final String CONJUGATED_FORMAT = "~%s";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintString(IAdaptable element, int flags) {
		String result = "";
		EObject eObject = (EObject)element.getAdapter(EObject.class);

		if((eObject != null) && (eObject instanceof Port)) {

			Property property = (Property)eObject;

			FlowPort flowPort = ElementUtil.getStereotypeApplication(property, FlowPort.class);
			if(flowPort != null) {

				// manage direction
				if((flags & ILabelPreferenceConstants.DISP_DIRECTION) == ILabelPreferenceConstants.DISP_DIRECTION) {
					String direction;
					switch(flowPort.getDirection().getValue()) {
					case FlowDirection.IN_VALUE:
						direction = "in";
						break;
					case FlowDirection.OUT_VALUE:
						direction = "out";
						break;
					case FlowDirection.INOUT_VALUE:
						direction = "inout";
						break;
					default:
						direction = "inout";
						break;
					}
					result = String.format(DIRECTION_FORMAT, direction, result);
				}
			}

			// manage visibility
			if((flags & ILabelPreferenceConstants.DISP_VISIBILITY) == ILabelPreferenceConstants.DISP_VISIBILITY) {
				String visibility;
				switch(property.getVisibility().getValue()) {
				case VisibilityKind.PACKAGE:
					visibility = "~";
					break;
				case VisibilityKind.PUBLIC:
					visibility = "+";
					break;
				case VisibilityKind.PROTECTED:
					visibility = "#";
					break;
				case VisibilityKind.PRIVATE:
					visibility = "-";
					break;
				default:
					visibility = "+";
					break;
				}
				result = String.format(VISIBILITY_FORMAT, visibility);
			}

			// manage derived modifier
			if(((flags & ILabelPreferenceConstants.DISP_DERIVE) == ILabelPreferenceConstants.DISP_DERIVE) && (property.isDerived())) {
				result = String.format(DERIVED_FORMAT, result);
			}

			// manage name
			if(((flags & ILabelPreferenceConstants.DISP_NAME) == ILabelPreferenceConstants.DISP_NAME) && (property.isSetName())) {
				String name = property.getName();
				result = String.format(NAME_FORMAT, result, name);
			}

			// manage type and conjugated property
			if(((flags & ILabelPreferenceConstants.DISP_TYPE) == ILabelPreferenceConstants.DISP_TYPE)) {

				String type = "<Undefined>";
				if(property.getType() != null) {
					type = property.getType().getName();
				}

				if(((flags & ILabelPreferenceConstants.DISP_TYPE) == ILabelPreferenceConstants.DISP_TYPE)) {
					if((flowPort != null) && (flowPort.isIsConjugated())) {
						type = String.format(CONJUGATED_FORMAT, type);
					}
				}

				result = String.format(TYPE_FORMAT, result, type);
			}

			// manage multiplicity
			if(((flags & ILabelPreferenceConstants.DISP_MULTIPLICITY) == ILabelPreferenceConstants.DISP_MULTIPLICITY) && (property.getLower() != 1 || property.getUpper() != 1)) {
				String lower = ValueSpecificationUtil.getSpecificationValue(property.getLowerValue());
				String upper = ValueSpecificationUtil.getSpecificationValue(property.getUpperValue());
				result = String.format(MULTIPLICITY_FORMAT, result, lower, upper);
			}

			// manage default value
			if(((flags & ILabelPreferenceConstants.DISP_DEFAULTVALUE) == ILabelPreferenceConstants.DISP_DEFAULTVALUE) && ((property.getDefaultValue() != null))) {
				ValueSpecification valueSpecification = property.getDefaultValue();
				if(valueSpecification instanceof InstanceValue && property.getType().equals(valueSpecification.getType())) {
					result = String.format(DEFAULT_VALUE_FORMAT, result, ValueSpecificationUtil.getSpecificationValue(valueSpecification));
				}
			}

			// manage modifier
			if((flags & ILabelPreferenceConstants.DISP_MODIFIERS) == ILabelPreferenceConstants.DISP_MODIFIERS) {
				StringBuffer sb = new StringBuffer();
				if(property.isReadOnly()) {
					sb.append(sb.length() == 0 ? "readOnly" : ", readOnly");
				}
				if(property.isOrdered()) {
					sb.append(sb.length() == 0 ? "ordered" : ", ordered");
				}
				if(property.isUnique()) {
					sb.append(sb.length() == 0 ? "unique" : ", unique");
				}
				if(property.isDerivedUnion()) {
					sb.append(sb.length() == 0 ? "union" : ", union");
				}
				EList<Property> redefinedProperties = property.getRedefinedProperties();
				if(redefinedProperties != null && !redefinedProperties.isEmpty()) {
					for(Property p : redefinedProperties) {
						sb.append(sb.length() == 0 ? p.getName() : ", redefines " + p.getName());
					}
				}
				if(sb.length() != 0) {
					result = String.format(MODIFIER_FORMAT, result, sb.toString());
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
				return PortandflowsPackage.eINSTANCE.getFlowPort_Direction().equals(feature) || PortandflowsPackage.eINSTANCE.getFlowPort_IsConjugated().equals(feature) || super.isAffectingEvent(event, flags);
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EObject> getSemanticElementsBeingParsed(EObject element) {
		List<EObject> semanticElementsBeingParsed = super.getSemanticElementsBeingParsed(element);

		if((element != null) && (element instanceof Port)) {
			Port semElement = (Port)element;

			FlowPort flowPort = ElementUtil.getStereotypeApplication(semElement, FlowPort.class);
			if(flowPort != null) {
				semanticElementsBeingParsed.add(flowPort);
			}
		}
		return semanticElementsBeingParsed;
	}
}
