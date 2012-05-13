/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
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
package org.eclipse.papyrus.diagram.common.commands;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.umlutils.ui.VisualInformationPapyrusConstant;
import org.eclipse.papyrus.umlutils.ui.command.CreateEAnnotationCommand;

/**
 * The Class CreateHyperLinkCommand. this has in charge to add a new entry list of hyperlinks
 * 
 */
public class CreateHyperLinkWebCommand extends CreateEAnnotationCommand {

	/** The hyperlink kind. */
	public String tooltiptext;

	/** The localization. */
	public String link;
	
	/** to set if is a default hyperlink */
	public boolean isDefault;

	/**
	 * Instantiates a new creates the hyper link command.
	 * 
	 * @param domain
	 *        the domain
	 * @param object
	 *        the object for example the view
	 * @param tooltiptext
	 *        the hyperlink kind see {@link VisualInformationPapyrusConstant}
	 * @param link
	 *        the localization
	 *@param isDefault
	 * 		  to set this hyperlink as default
	 */
	public CreateHyperLinkWebCommand(TransactionalEditingDomain domain, EModelElement object, String tooltiptext, String link, boolean isDefault) {
		super(domain, object, VisualInformationPapyrusConstant.HYPERLINK_WEB);
		this.tooltiptext = tooltiptext;
		this.link = link;
		this.isDefault= isDefault;
	}

	/**
	 * {@inheritedDoc}
	 */
	protected void doExecute() {
		EAnnotation eAnnotation = createEAnnotation();
		eAnnotation.getDetails().put(VisualInformationPapyrusConstant.HYPERLINK_TOOLTYPE_TEXT, this.tooltiptext);
		eAnnotation.getDetails().put(VisualInformationPapyrusConstant.HYPERLINK_WEB_LINK, this.link);
		eAnnotation.getDetails().put(VisualInformationPapyrusConstant.HYPERLINK_IS_DEFAULT_NAVIGATION, ""+this.isDefault);
		attachEannotation(eAnnotation, getObject());
	}

}
