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
 *  Remi Schnekenburger (CEA LIST) Remi.Schnekenburger@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.parsers.texteditor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Basic word detector for the scanner.<BR>
 * 
 * @author Remi SCHNEKENBURGER
 * @see org.eclipse.jface.text.rules.IWordDetector
 */
public class WordDetector implements IWordDetector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	 */
	/**
	 * 
	 * 
	 * @param character
	 * 
	 * @return
	 */
	public boolean isWordPart(char character) {
		return Character.isLetter(character);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	 */
	/**
	 * 
	 * 
	 * @param character
	 * 
	 * @return
	 */
	public boolean isWordStart(char character) {
		return Character.isLetter(character);
	}
}
