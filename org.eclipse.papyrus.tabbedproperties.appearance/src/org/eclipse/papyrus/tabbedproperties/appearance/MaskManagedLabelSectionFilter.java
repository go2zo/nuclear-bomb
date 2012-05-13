/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.tabbedproperties.appearance;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.papyrus.diagram.common.editpolicies.IMaskManagedLabelEditPolicy;

/**
 * Filter for the {@link MaskManagedLabelSection} section
 */
public class MaskManagedLabelSectionFilter implements IFilter {

	/**
	 * {@inheritDoc}
	 */
	public boolean select(Object object) {
		if(object instanceof IGraphicalEditPart) {
			// if the edit part is a graphical edit part, it tests if it has the right edit policy
			return (((IGraphicalEditPart)object)
					.getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY) != null);
		}
		return false;
	}

}
