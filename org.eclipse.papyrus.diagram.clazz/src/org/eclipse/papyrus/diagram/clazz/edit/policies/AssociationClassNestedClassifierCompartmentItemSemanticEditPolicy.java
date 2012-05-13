/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.diagram.clazz.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.diagram.clazz.edit.commands.NestedClassForComponentCreateCommand;
import org.eclipse.papyrus.diagram.clazz.providers.UMLElementTypes;

/**
 * @generated
 */
public class AssociationClassNestedClassifierCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public AssociationClassNestedClassifierCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.AssociationClass_2013);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Class_3004 == req.getElementType()) {
			return getGEFWrapper(new NestedClassForComponentCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
