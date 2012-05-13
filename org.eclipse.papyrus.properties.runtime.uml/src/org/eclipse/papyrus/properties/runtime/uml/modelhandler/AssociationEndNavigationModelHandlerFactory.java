/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.properties.runtime.uml.modelhandler;

import org.eclipse.papyrus.properties.runtime.modelhandler.emf.EMFModelHandlerFactory;
import org.w3c.dom.Node;


/**
 * factory for Association end navigation model handlers
 */
public class AssociationEndNavigationModelHandlerFactory extends EMFModelHandlerFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AssociationEndNavigationModelHandler createModelHandler(Node modelHandlerNode) {
		return new AssociationEndNavigationModelHandler();
	}

}
