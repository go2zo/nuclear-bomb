/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.modelexplorer.handler;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.sasheditor.contentprovider.IPageMngr;

/**
 * Handler for the delete Diagram action
 * 
 * 
 * 
 */
public class DeleteDiagramHandler extends AbstractCommandHandler {


	/**
	 * 
	 * @see org.eclipse.papyrus.modelexplorer.handler.AbstractCommandHandler#getCommand()
	 * 
	 * @return
	 */
	@Override
	protected Command getCommand() {
		TransactionalEditingDomain editingDomain = getEditingDomain();
		final IPageMngr pageMngr = getPageManager();

		List<Diagram> diagrams = getSelectedDiagrams();

		if(editingDomain != null && pageMngr != null && !diagrams.isEmpty()) {
			CompoundCommand command = new CompoundCommand();

			for(final Diagram diagram : diagrams) {
				Command sashRemoveComd = new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						if(pageMngr.isOpen(diagram)) {
							pageMngr.closePage(diagram);
						}
						pageMngr.removePage(diagram);
					}
				};
				EList<EObject> diags = diagram.eResource().getContents();
				command.append(sashRemoveComd);
				command.append(new RemoveCommand(editingDomain, diags, diagram));
			}
			return command.isEmpty() ? UnexecutableCommand.INSTANCE : command;
		}
		return UnexecutableCommand.INSTANCE;
	}
}
