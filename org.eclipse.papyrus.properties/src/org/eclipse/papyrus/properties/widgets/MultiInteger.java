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

import org.eclipse.papyrus.widgets.editors.ICommitListener;
import org.eclipse.papyrus.widgets.editors.MultipleIntegerEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * A PropertyEditor for editing multiple integers in a List
 * 
 * @see org.eclipse.papyrus.widgets.editors.MultipleIntegerEditor
 * 
 * @author Camille Letavernier
 */
public class MultiInteger extends AbstractPropertyEditor {

	/**
	 * The MultipleIntegerEditor widget
	 */
	protected MultipleIntegerEditor editor;

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *        The composite in which the widget will be displayed
	 * @param style
	 *        The style for the widget
	 */
	public MultiInteger(Composite parent, int style) {
		editor = new MultipleIntegerEditor(parent, style);
		super.setEditor(editor);
	}

	@Override
	protected void doBinding() {
		editor.setOrdered(input.isOrdered(propertyPath));
		editor.setUnique(input.isUnique(propertyPath));

		if(getInputObservableList() instanceof ICommitListener) {
			editor.addCommitListener((ICommitListener)getInputObservableList());
		}

		super.doBinding();
	}
}
