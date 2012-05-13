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
package org.eclipse.papyrus.sysml.diagram.blockdefinition;

import org.eclipse.papyrus.core.adaptor.gmf.GmfEditorFactory;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.ElementTypes;

public class BlockDefinitionDiagramEditorFactory extends GmfEditorFactory {

	public BlockDefinitionDiagramEditorFactory() {
		super(BlockDefinitionDiagramForMultiEditor.class, ElementTypes.DIAGRAM_ID);
	}

}
