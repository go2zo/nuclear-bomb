/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package org.eclipse.papyrus.properties.tabbed.profile.imagesection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.papyrus.properties.tabbed.profile.AbstractViewSection;
import org.eclipse.papyrus.properties.tabbed.profile.composite.ExprTextComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The Image Location section on the element tab.
 */
public class ImageExpressionSection extends AbstractViewSection {

	/**
	 * 
	 */
	private ExprTextComposite exprComposite;

	/**
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 *
	 * @param parent
	 * @param tabbedPropertySheetPage
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {

		super.createControls(parent, tabbedPropertySheetPage);
		exprComposite = new ExprTextComposite("Expr:");
		exprComposite.createContent(parent, tabbedPropertySheetPage.getWidgetFactory());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refresh() {
		exprComposite.refresh();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.properties.tabbed.profile.AbstractViewSection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 *
	 * @param part
	 * @param selection
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		if (getElement()!=null) {
			exprComposite.setElement(getElement());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		super.dispose();
		if (exprComposite != null) {
			exprComposite.dispose();
		}
	}
}