/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.properties.widgets;

import org.eclipse.swt.widgets.Composite;

/**
 * A PropertyEditor for editing booleans through a CheckBox
 * 
 * @see org.eclipse.papyrus.widgets.editors.BooleanCheckbox
 * 
 * @author Camille Letavernier
 */
public class BooleanCheckbox extends AbstractPropertyEditor {

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *        The composite in which the widget will be displayed
	 * @param style
	 *        The style for the widget
	 */
	public BooleanCheckbox(Composite parent, int style) {
		super(new org.eclipse.papyrus.widgets.editors.BooleanCheckbox(parent, style));
	}
}
