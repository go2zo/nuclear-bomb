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
package org.eclipse.papyrus.sysml.diagram.common.figure;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.diagram.common.figure.node.ClassifierFigure;

public class DimensionFigure extends ClassifierFigure {

	public DimensionFigure() {
		super("dimension"); //$NON-NLS-1$
	}

	protected void createContentPane(List<String> compartments) {
		super.createContentPane(getUpdatedListOfCompartments(compartments));
	}

	/**
	 * Get the list of compartments, updated with specific ones
	 * 
	 * @param compartments
	 *        the original list of compartments (untouched)
	 * @return the new completed list of compartments
	 */
	private List<String> getUpdatedListOfCompartments(List<String> compartments) {
		compartments = new ArrayList<String>(compartments);
		return compartments;
	}

}
