/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *  Vincent Lorenzo (CEA-LIST) vincent.lorenzo@cea.fr @deprecated
 *****************************************************************************/
package org.eclipse.papyrus.modelexplorer.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.workspace.ui.actions.RedoActionWrapper;
import org.eclipse.emf.workspace.ui.actions.UndoActionWrapper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.modelexplorer.NavigatorUtils;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * The Class DefaultEMFActionsFactory for creating a default action from EMF.
 * Undefined actions are not still in use or might be overridden
 * 
 * @author Emilien Perico
 * @deprecated These actions are now rewritten using the Eclipse Framework
 */
@Deprecated
public class DefaultEMFActionsFactory implements IActionHandlerFactory {

	protected List<Action> actions = new LinkedList<Action>();

	protected CutAction cutAction;

	protected CopyAction copyAction;

	protected PasteAction pasteAction;

	protected UndoActionWrapper undoAction;

	protected RedoActionWrapper redoAction;

	protected LoadResourceAction loadResourceAction;

	/**
	 * {@inheritDoc}
	 */
	public List<Action> createActions(EditingDomain editingDomain) {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

		// Create Cut action
		this.cutAction = new CutAction(editingDomain);
		this.cutAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		actions.add(cutAction);

		// Create Copy action
		this.copyAction = new CopyAction(editingDomain);
		this.copyAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		actions.add(copyAction);

		// Create Paste action
		this.pasteAction = new PasteAction(editingDomain);
		this.pasteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		actions.add(pasteAction);

		// Undo action
		this.undoAction = new UndoActionWrapper();
		this.undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		actions.add(undoAction);

		// Redo action
		this.redoAction = new RedoActionWrapper();
		this.redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		actions.add(redoAction);

		// Load Resource action
		this.loadResourceAction = new LoadResourceAction(editingDomain);
		actions.add(loadResourceAction);

		return actions;
	}

	/**
	 * {@inheritDoc}
	 */
	public void activate(CommonNavigator activeViewPart) {
		cutAction.setActiveWorkbenchPart(activeViewPart);
		copyAction.setActiveWorkbenchPart(activeViewPart);
		pasteAction.setActiveWorkbenchPart(activeViewPart);
		undoAction.setActiveWorkbenchPart(activeViewPart);
		redoAction.setActiveWorkbenchPart(activeViewPart);
		loadResourceAction.setActiveWorkbenchPart(activeViewPart);

		ISelectionProvider selectionProvider = null;
		if(activeViewPart.getCommonViewer() instanceof ISelectionProvider) {
			selectionProvider = activeViewPart.getCommonViewer();
			selectionProvider.addSelectionChangedListener(cutAction);
			selectionProvider.addSelectionChangedListener(copyAction);
			selectionProvider.addSelectionChangedListener(pasteAction);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void deactivate(CommonNavigator activeViewPart) {
		cutAction.setActiveWorkbenchPart(null);
		copyAction.setActiveWorkbenchPart(null);
		pasteAction.setActiveWorkbenchPart(null);
		undoAction.setActiveWorkbenchPart(null);
		redoAction.setActiveWorkbenchPart(null);
		loadResourceAction.setActiveWorkbenchPart(null);

		ISelectionProvider selectionProvider = null;
		if(activeViewPart.getCommonViewer() instanceof ISelectionProvider) {
			selectionProvider = activeViewPart.getCommonViewer();
			selectionProvider.removeSelectionChangedListener(cutAction);
			selectionProvider.removeSelectionChangedListener(copyAction);
			selectionProvider.removeSelectionChangedListener(pasteAction);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void fillActionBars(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void update(IStructuredSelection structuredSelection) {
		ArrayList array = new ArrayList();
		Iterator iterator = structuredSelection.iterator();
		while(iterator.hasNext()) {
			Object object = iterator.next();
			if(NavigatorUtils.resolveSemanticObject(object) != null) {
				array.add(NavigatorUtils.resolveSemanticObject(object));
			}
		}
		StructuredSelection st = new StructuredSelection(array);
		cutAction.updateSelection(st);
		cutAction.setEnabled((cutAction.createCommand(st.toList())).canExecute());
		copyAction.updateSelection(st);
		copyAction.setEnabled((copyAction.createCommand(st.toList())).canExecute());
		pasteAction.updateSelection(st);
		pasteAction.setEnabled((pasteAction.createCommand(st.toList())).canExecute());
		loadResourceAction.update();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Action> getActions() {
		return actions;
	}

}
