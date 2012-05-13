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

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;

public class ElementTypes extends AbstractElementTypeEnumerator {

	/** ********************************************************* */
	/** SysML Block Definition Diagram specific elements **************** */
	/** ********************************************************* */

	/** SysML Block Definition Diagram :: Diagram */
	public static final String DIAGRAM_ID = "BlockDefinition"; //$NON-NLS-1$




	/** ********************************************************* */
	/** SysML Block Definition Diagram - ClassDiagram related elements */
	/** ********************************************************* */


	/** ClassDiagram :: MODEL */
	public static final IHintedType MODEL = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Model_2005"); //$NON-NLS-1$

	public static final String MODEL_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = "7009"; //$NON-NLS-1$

	public static final String MODEL_LABEL_NAME_TN_HINT = "5020"; //$NON-NLS-1$

	/** ClassDiagram :: MODEL_CN */
	public static final IHintedType MODEL_CN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Model_3024"); //$NON-NLS-1$

	public static final String MODEL_CN_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = "7030"; //$NON-NLS-1$

	public static final String MODEL_CN_LABEL_NAME_HINT = "5052"; //$NON-NLS-1$

	/** ClassDiagram :: PACKAGE */
	public static final IHintedType PACKAGE = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Package_2007"); //$NON-NLS-1$

	public static final String PACKAGE_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = "7016"; //$NON-NLS-1$

	public static final String PACKAGE_LABEL_NAME_HINT = "5026"; //$NON-NLS-1$

	/** ClassDiagram :: PACKAGE_CN */
	public static final IHintedType PACKAGE_CN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Package_3009"); //$NON-NLS-1$

	public static final String PACKAGE_CN_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = "7010"; //$NON-NLS-1$

	public static final String PACKAGE_CN_LABEL_NAME_HINT = "5017"; //$NON-NLS-1$

	/** ClassDiagram :: INSTANCE_SPECIFICATION */
	public static final IHintedType INSTANCE_SPECIFICATION = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.InstanceSpecification_2001"); //$NON-NLS-1$

	public static final String INSTANCE_SPECIFICATION_COMPARTMENT_SLOT_HINT = "7001"; //$NON-NLS-1$

	public static final String INSTANCE_SPECIFICATION_LABEL_NAME_HINT = "5002"; //$NON-NLS-1$

	/** ClassDiagram :: INSTANCE_SPECIFICATION_CN */
	public static final IHintedType INSTANCE_SPECIFICATION_CN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.InstanceSpecification_3020"); //$NON-NLS-1$

	public static final String INSTANCE_SPECIFICATION_CN_COMPARTMENT_SLOT_HINT = "7035"; //$NON-NLS-1$

	public static final String INSTANCE_SPECIFICATION_CN_LABEL_NAME_HINT = "5040"; //$NON-NLS-1$

	/** ClassDiagram :: CONSTRAINT */
	public static final IHintedType CONSTRAINT = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Constraint_2011"); //$NON-NLS-1$

	public static final String CONSTRAINT_LABEL_NAME_HINT = "5037"; //$NON-NLS-1$

	public static final String CONSTRAINT_LABEL_BODY_HINT = "5159"; //$NON-NLS-1$

	/** ClassDiagram :: CONSTRAINT_CN */
	public static final IHintedType CONSTRAINT_CN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Constraint_3029"); //$NON-NLS-1$

	public static final String CONSTRAINT_CN_LABEL_NAME_HINT = "5064"; //$NON-NLS-1$

	public static final String CONSTRAINT_CN_LABEL_BODY_HINT = "5160"; //$NON-NLS-1$

	/** ClassDiagram :: COMMENT */
	public static final IHintedType COMMENT = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Comment_2012"); //$NON-NLS-1$

	public static final String COMMENT_LABEL_BODY_HINT = "5038"; //$NON-NLS-1$

	/** ClassDiagram :: COMMENT_CN */
	public static final IHintedType COMMENT_CN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Comment_3028"); //$NON-NLS-1$

	public static final String COMMENT_CN_LABEL_BODY_HINT = "5063"; //$NON-NLS-1$


	/** ClassDiagram :: COMMENT_ANNOTATED_ELEMENT */
	public static final IHintedType COMMENT_ANNOTATED_ELEMENT = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.CommentAnnotatedElement_4013"); //$NON-NLS-1$

	/** ClassDiagram :: CONSTRAINT_CONSTRAINED_ELEMENT */
	public static final IHintedType CONSTRAINT_CONSTRAINED_ELEMENT = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.ConstraintConstrainedElement_4014"); //$NON-NLS-1$

	/** ClassDiagram :: ChildLabelNodes */
	public static final IHintedType INSTANCE_SPECIFICATION_SLOT_CLN = (IHintedType)getElementType("org.eclipse.papyrus.diagram.clazz.Slot_3030"); //$NON-NLS-1$

}
