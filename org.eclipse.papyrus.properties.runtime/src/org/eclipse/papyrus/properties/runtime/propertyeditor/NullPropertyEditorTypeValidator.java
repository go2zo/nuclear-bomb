/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.properties.runtime.propertyeditor;


/**
 * Validator which is always valid
 */
public class NullPropertyEditorTypeValidator implements IPropertyEditorTypeValidator {

	/**
	 * {@inheritDoc}
	 */
	public boolean isValidForValue(Object valueToEdit) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPropertyEditor(AbstractPropertyEditor editor) {
		// nothing here
	}

}
