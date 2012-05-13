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
package org.eclipse.papyrus.parsers.texteditor.completionproposals;

import java.util.List;
import java.util.Vector;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * Completion proposal computer for visibilities.
 */
public class DerivedPropertyCompletionProposal implements ICompletionProposalComputer {

	/**
	 * Text inserted in the editor.
	 */
	final static public String[] derivedPropertyStrings = { "/", };

	/**
	 * Text displayed in the information window.
	 */
	final static public String[] derivedPropertyStringsInfo = { "Derived Property", };

	/**
	 * Text displayed in the completion area window.
	 */
	final static public String[] derivedPropertyStringsName = { "/", };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cea.papyrus.classdiagram.parsers.texteditor.completionproposals.ICompletionProposalComputer
	 * #generateCompletionProposals(int, int, java.lang.String)
	 */
	/**
	 * 
	 * 
	 * @param selectionRange
	 * @param prefix
	 * @param documentOffset
	 * 
	 * @return
	 */
	public List<ICompletionProposal> generateCompletionProposals(int documentOffset, int selectionRange, String prefix) {
		Vector<ICompletionProposal> v = new Vector<ICompletionProposal>();

		// adds each Completion proposal
		ICompletionProposal proposal = null;

		// adds all standard multiplicities (static strings...)
		for(int i = 0; i < derivedPropertyStrings.length; i++) {
			if(derivedPropertyStrings[i].startsWith(prefix)) {
				proposal = new CompletionProposal(derivedPropertyStrings[i], documentOffset - prefix.length(), prefix
						.length()
						+ selectionRange, derivedPropertyStrings[i].length(), null, derivedPropertyStringsName[i],
						null, derivedPropertyStringsInfo[i]);
				v.add(proposal);
			}
		}
		return v;
	}
}
