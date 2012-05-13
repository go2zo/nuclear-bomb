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
package org.eclipse.papyrus.sysml.diagram.common.dialogs;

import org.eclipse.papyrus.sysml.blocks.BlocksPackage;
import org.eclipse.papyrus.sysml.diagram.common.messages.Messages;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Dialog for initialization of Part (Property) type (the type is either selected or created).
 */
public class CreateOrSelectBlockPropertyTypeDialog extends CreateOrSelectTypeDialog {

	/** Constructor */
	public CreateOrSelectBlockPropertyTypeDialog(Shell shell, NamedElement owner) {
		super(shell, owner, SysMLElementTypes.BLOCK, BlocksPackage.eINSTANCE.getBlock(), UMLElementTypes.PACKAGE, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDialogTitle() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_DialogTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSelectionSectionTitle() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectionSectionTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSelectionSectionRadioLabel() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectionSectionRadioLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCreationSectionTitle() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_CreationSectionTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCreationSectionRadioLabel() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_CreationSectionRadioLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNewTypeNameLabel() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_NewTypeNameLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNewTypeContainerNameLabel() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_NewTypeContainerNameLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNewTypeContainerDialogTitle() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectNewTypeContainerDialogTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNewTypeContainerDialogMessage() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectNewTypeContainerDialogMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTypeDialogTitle() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectTypeDialogTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTypeDialogMessage() {
		return Messages.CreateOrSelectBlockPropertyTypeDialog_SelectTypeDialogMessage;
	}
}
