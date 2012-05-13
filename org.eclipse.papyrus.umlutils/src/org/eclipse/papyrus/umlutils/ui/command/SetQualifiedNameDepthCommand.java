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
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.umlutils.ui.command;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.umlutils.ui.VisualInformationPapyrusConstant;

// TODO: Auto-generated Javadoc
/**
 * The Class SetQualifiedNameDepthCommand.
 */
public class SetQualifiedNameDepthCommand extends CreateEAnnotationCommand {

	/** The qualified namedepht. */
	private String qualifiedNamedepht;

	/**
	 * Instantiates a new sets the qualified name depth command.
	 * 
	 * @param domain
	 *        the domain
	 * @param object
	 *        the object
	 * @param depht
	 *        the depht
	 */
	public SetQualifiedNameDepthCommand(TransactionalEditingDomain domain, EModelElement object, int depht) {
		super(domain, object, VisualInformationPapyrusConstant.QUALIFIED_NAME);
		this.qualifiedNamedepht = "" + depht;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		EAnnotation qualifiedNameEAnnotation = createEAnnotation();
		replaceEannotation(qualifiedNameEAnnotation, getObject());
		replaceEntry(qualifiedNameEAnnotation, VisualInformationPapyrusConstant.QUALIFIED_NAME_DEPTH,
				qualifiedNamedepht);
	}

}
