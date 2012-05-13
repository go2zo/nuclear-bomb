/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.service.types.matcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;
import org.eclipse.papyrus.sysml.blocks.Dimension;
import org.eclipse.papyrus.uml.service.types.utils.ElementUtil;
import org.eclipse.uml2.uml.InstanceSpecification;

/**
 * Test if current {@link InstanceSpecification} is a {@link Dimension}
 */
public class DimensionMatcher implements IElementMatcher {

	public boolean matches(EObject eObject) {

		boolean isMatch = false;
		if(eObject instanceof InstanceSpecification) {

			InstanceSpecification element = (InstanceSpecification)eObject;
			if(ElementUtil.getStereotypeApplication(element, Dimension.class) != null) {
				isMatch = true;
			}
		}
		return isMatch;
	}

}
