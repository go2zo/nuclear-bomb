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
package org.eclipse.papyrus.properties.runtime.modelhandler.emf;

import org.eclipse.papyrus.properties.runtime.modelhandler.IPropertyModelHandlerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * Abstract factory for EMF Model Handlers
 */
public abstract class EMFModelHandlerFactory implements IPropertyModelHandlerFactory {

	/**
	 * {@inheritDoc}
	 */
	public abstract IEMFModelHandler createModelHandler(Node modelHandlerNode);

	/**
	 * Retrieve the name of the feature to modify, from a given configuration node
	 * 
	 * @param node
	 *        the configuration node
	 * @return the name of the feature to modify or <code>null</code>
	 */
	protected String retrieveFeatureName(Node node) {
		String featureName = null;
		NamedNodeMap featureAttributes = node.getAttributes();
		if(featureAttributes != null) {
			Node featureNameNode = featureAttributes.getNamedItem("name");
			if(featureNameNode != null) {
				featureName = featureNameNode.getNodeValue();
			}
		}
		return featureName;
	}

}
