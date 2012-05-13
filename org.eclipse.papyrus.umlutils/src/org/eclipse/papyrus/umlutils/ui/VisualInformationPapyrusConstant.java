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
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.umlutils.ui;

public interface VisualInformationPapyrusConstant {

	public static String STEREOTYPE_ANNOTATION = "Stereotype_Annotation";

	/**
	 * the kind of presentation for stereotype
	 */
	public static String STEREOTYPE_PRESENTATION_KIND = "Stereotype_Presentation_Kind";

	/**
	 * the list of stereotype to display
	 */
	public static String STEREOTYPE_LIST = "StereotypeList";

	/**
	 * the list of stereotype to display
	 */
	public static String STEREOTYPE_WITHQN_LIST = "StereotypeWithQualifiedNameList";

	/**
	 * 
	 */
	public static String TEXT_ICON_STEREOTYPE_PRESENTATION = "TextIconStereotype";

	/**
	 * 
	 */
	public static String ICON_STEREOTYPE_PRESENTATION = "IconStereotype";

	/**
	 * 
	 */
	public static String IMAGE_STEREOTYPE_PRESENTATION = "ImageStereotype";

	/**
	 * 
	 */
	public static String STEREOTYPE_TEXT_HORIZONTAL_PRESENTATION = "HorizontalStereo";

	/**
	 * 
	 */
	public static String STEREOTYPE_TEXT_VERTICAL_PRESENTATION = "VerticalStereo";

	/**
	 * 
	 */
	public static String PROPERTY_STEREOTYPE_DISPLAY = "PropStereoDisplay";

	/**
	 * 
	 */
	// @unused
	public static String PROPERTY_STEREOTYPE_PROPERTY_VALUES_DISPLAY = "PropStereoPropValuesDisplay";

	/**
	 * 
	 */
	// @unused
	public static String OPAQUE_EXPRESSION_BODY_DISPLAY = "OpaqueExpressionBodyDisplay";

	/**
	 * String represent the location of stereotype properties within a comment
	 */
	public static String STEREOTYPE_COMMENT_LOCATION = "Comment";

	/**
	 * String represent the location of stereotype properties within a compartment of a graph node
	 */
	public static String STEREOTYPE_COMPARTMENT_LOCATION = "Compartment";

	/**
	 * properties of applied stereotypes are enclose in braces
	 */
	public static String STEREOTYPE_BRACE_LOCATION = "With brace";

	/** The qualified name. */
	public static String QUALIFIED_NAME = "QualifiedName";

	/** The qualified name depth. */
	public static String QUALIFIED_NAME_DEPTH = "QualifiedNameDepth";

	/**
	 * The GRADIENT.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public static String GRADIENT = "Gradient";

	/** The GRADIEN t_ value. */
	/**
	 * @deprecated
	 */
	@Deprecated
	public static String GRADIENT_VALUE = "GradientValue";

	public static String SHADOWFIGURE = "ShadowFigure";

	public static String SHADOWFIGURE_VALUE = "ShadowFigure_Value";

	public static String DISPLAY_NAMELABELICON = "displayNameLabelIcon";

	public static String DISPLAY_NAMELABELICON_VALUE = "displayNameLabelIcon_value";

	public static String LAYOUTFIGURE = "layoutFigure";

	public static String LAYOUTFIGURE_VALUE = "layoutFigure_value";

	public static String STEREOTYPE_PROPERTY_LOCATION = "StereotypePropertyLocation";

	/**
	 * The Constant P_STEREOTYPE_NAME_DISPLAY_USER_CONTROLLED.
	 */
	public final static String P_STEREOTYPE_NAME_DISPLAY_USER_CONTROLLED = "User Controlled";

	/**
	 * The Constant P_STEREOTYPE_NAME_DISPLAY_UML_CONFORM.
	 */
	public final static String P_STEREOTYPE_NAME_DISPLAY_UML_CONFORM = "UML Compatibility (lower case first letter - default)";

	/**
	 * The Constant P_STEREOTYPE_NAME_APPEARANCE.
	 */
	public static final String P_STEREOTYPE_NAME_APPEARANCE = "ProfileApplicationPreferenceConstants.stereotype.name.appearance";

	/**
	 * Separator for stereotype properties list, i.e. the separator between each properties in the
	 * list. It is set to "<code>,</code>" by default
	 */
	public static final String STEREOTYPE_PROPERTIES_LIST_SEPARATOR = ",";

	/** key for the appearance of properties or other specific display */
	public static final String CUSTOM_APPEARENCE_ANNOTATION = "CustomAppearance_Annotation";

	/**
	 * this is a key of eAnnnotation that contains hypertext link or referenced document
	 **/
	public static final String HYPERLINK_DIAGRAM = "PapyrusHyperLink_Diagram";

	/**
	 * this is a value of a eAnnnotation detail that explain the kind of the link
	 **/

	public static final String HYPERLINK_DOCUMENT = "PapyrusHyperLink_Document";

	public static final String HYPERLINK_WEB = "PapyrusHyperLink_web";

	public static final String HYPERLINK_TOOLTYPE_TEXT = "tooltip_text";

	public static final String HYPERLINK_DIAGRAM_NAME = "diagram_name";
	
	public static final String HYPERLINK_IS_DEFAULT_NAVIGATION = "is_default_navigation";

	public static final String HYPERLINK_DOCUMENT_LOCALIZATION = "localization";

	public static final String HYPERLINK_WEB_LINK = "link";

	/** key for the appearance of properties or other specific display */
	public static final String CUSTOM_APPEARANCE_MASK_VALUE = "CustomAppearance_MaskValue";
}
