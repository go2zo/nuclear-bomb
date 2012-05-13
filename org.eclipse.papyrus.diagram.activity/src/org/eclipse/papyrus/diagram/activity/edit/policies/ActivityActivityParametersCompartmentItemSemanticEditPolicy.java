/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.activity.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.diagram.activity.edit.commands.ParameterCreateCommand;
import org.eclipse.papyrus.diagram.activity.providers.UMLElementTypes;

/**
 * @generated
 */
public class ActivityActivityParametersCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ActivityActivityParametersCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.Activity_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Parameter_3001 == req.getElementType()) {
			return getGEFWrapper(new ParameterCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
