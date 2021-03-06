/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.documentation.view;

 
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.texteditor.IUpdate;

public class TextViewerAction extends Action implements IUpdate {
	private int operationCode = -1;
	private ITextOperationTarget operationTarget;

	public TextViewerAction(ITextViewer viewer, int operationCode) {
		this.operationCode = operationCode;
		operationTarget = viewer.getTextOperationTarget();
		update();
	}
	public void update() {
		boolean wasEnabled = isEnabled();
		boolean isEnabled = (operationTarget != null && operationTarget.canDoOperation(operationCode));
		setEnabled(isEnabled);
		if (wasEnabled != isEnabled) {
			firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE : Boolean.FALSE, isEnabled ? Boolean.TRUE : Boolean.FALSE);
		}
	}
	public void run() {
		if (operationCode != -1 && operationTarget != null) {
			operationTarget.doOperation(operationCode);
		}
	}
}