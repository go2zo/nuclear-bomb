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
package org.eclipse.papyrus.uml.service.types.utils;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

/**
 * This singleton is used to find a new element name with
 * no duplication in the same workspace.
 * 
 * Copied in current plug-in to avoid dependencies with oep.diagram.common.
 */
public class NamedElementHelper {

	/** Singleton instance */
	public static NamedElementHelper EINSTANCE = new NamedElementHelper();

	private String baseString = "default";

	/**
	 * Get the base string to use for default name creation
	 * 
	 * @return the base string
	 */
	public String getBaseString() {
		return baseString;
	}

	/**
	 * Generic method that returns a new unique name within a {@link Namespace}.
	 * 
	 * @param umlParent
	 *        the parent of the element to create
	 * @param eclass
	 *        the eClass of the element to name
	 * 
	 * @return a distinguishable name within the {@link Namespace} of the umlParent
	 */
	public String getNewUMLElementName(Element umlParent, EClass eclass) {
		return getNewUMLElementName(umlParent, eclass.getName());
	}

	/**
	 * Generic method that returns a new unique name within a {@link Namespace}.
	 * 
	 * @param umlParent
	 *        the parent of the element to create
	 * @param baseString
	 *        the base string for the new element name
	 * 
	 * @return a distinguishable name within the {@link Namespace} of the umlParent
	 */
	public String getNewUMLElementName(Element umlParent, String baseString) {

		this.setBaseString(baseString);
		String name = ""; //$NON-NLS-1$

		boolean found = false;
		// i <10000: avoid infinite loops
		for(int i = 0; i < 10001; i++) {
			found = false;
			name = getBaseString() + i;
			Iterator<?> it = umlParent.getOwnedElements().iterator();
			while(it.hasNext() && !found) {
				Object o = it.next();
				if(o instanceof NamedElement) {
					if(name.equals(((NamedElement)o).getName())) {
						found = true;
					}
				}
			}
			if(!found) {
				return name;
			}
		}
		return getBaseString() + "X"; //$NON-NLS-1$
	}

	/**
	 * set the base string for the name
	 * 
	 * @param baseString
	 *        a string that is the prefix
	 */
	public void setBaseString(String baseString) {
		this.baseString = baseString;
	}
}
