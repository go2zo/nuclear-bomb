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
package org.eclipse.papyrus.sysml.diagram.blockdefinition.provider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.common.parser.FlowPortLabelParser;
import org.eclipse.papyrus.sysml.diagram.common.parser.FlowPropertyLabelParser;
import org.eclipse.papyrus.sysml.diagram.common.parser.UnitLabelParser;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.uml.diagram.common.parser.AssociationEndLabelParser;
import org.eclipse.papyrus.uml.diagram.common.parser.ConstraintLabelParser;
import org.eclipse.papyrus.uml.diagram.common.parser.NamedElementLabelParser;
import org.eclipse.papyrus.uml.diagram.common.parser.OperationLabelParser;
import org.eclipse.papyrus.uml.diagram.common.parser.PropertyLabelParser;
import org.eclipse.papyrus.uml.diagram.common.parser.ReceptionLabelParser;
import org.eclipse.papyrus.uml.diagram.common.utils.UMLGraphicalTypes;

/**
 * Parser provider for labels used by this diagram.
 */
public class ParserProvider extends AbstractProvider implements IParserProvider {

	/** map from graphical hint to parsers */
	private static final Map<String, IParser> graphicalHintToParser = new HashMap<String, IParser>();

	static {
		graphicalHintToParser.put(UMLGraphicalTypes.LABEL_UML_NAMEDELEMENT_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.AFFIXEDLABEL_UML_NAMEDELEMENT_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_FLOWPORT_AS_LABEL_ID, new FlowPortLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_FLOWPROPERTY_AS_LABEL_ID, new FlowPropertyLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_CONSTRAINTPROPERTY_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_CONSTRAINT_AS_LABEL_ID, new ConstraintLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_PART_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_REFERENCE_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.SHAPE_SYSML_VALUE_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_PORT_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_PROPERTY_AS_LABEL_ID, new PropertyLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_OPERATION_AS_LABEL_ID, new OperationLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_RECEPTION_AS_LABEL_ID, new ReceptionLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.SHAPE_UML_ENUMERATIONLITERAL_AS_LABEL_ID, new NamedElementLabelParser());

		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_BLOCK_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_CONSTRAINTBLOCK_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_DIMENSION_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_FLOWSPECIFICATION_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_VALUETYPE_NAME_ID, new NamedElementLabelParser());

		graphicalHintToParser.put(SysMLGraphicalTypes.LABEL_SYSML_UNIT_NAME_ID, new UnitLabelParser());
		
		graphicalHintToParser.put(UMLGraphicalTypes.LINKLABEL_UML_NAMEDELEMENT_NAME_ID, new NamedElementLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.LINKLABEL_UML_ASSOCIATION_SOURCE_ROLE_ID, new AssociationEndLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.LINKLABEL_UML_ASSOCIATION_SOURCE_MULTIPLICITY_ID, new AssociationEndLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.LINKLABEL_UML_ASSOCIATION_TARGET_ROLE_ID, new AssociationEndLabelParser());
		graphicalHintToParser.put(UMLGraphicalTypes.LINKLABEL_UML_ASSOCIATION_TARGET_MULTIPLICITY_ID, new AssociationEndLabelParser());

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean provides(IOperation operation) {
		if(operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation)operation).getHint();
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public IParser getParser(IAdaptable hint) {
		String parserHint = (String)hint.getAdapter(String.class);
		if(parserHint != null) {
			IParser parser = graphicalHintToParser.get(parserHint);
			if(parser != null) {
				return parser;
			}
		}

		View view = (View)hint.getAdapter(View.class);
		if(view != null) {
			IParser parser = graphicalHintToParser.get(view.getType());
			if(parser != null) {
				return parser;
			}
		}

		return null;
	}

}
