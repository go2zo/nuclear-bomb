/*******************************************************************************
 * Copyright (c) 2008 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.tabbedproperties.core.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;

/**
 * This class handles title label for tabbed properties.
 * 
 * @author Jerome Benois
 */
public class EMFObjectLabelProvider extends AdapterFactoryLabelProvider implements ILabelProvider {

	private static final Class<?> IItemLabelProviderClass = IItemLabelProvider.class;

	private static Map<String, AdapterFactory> factories = new HashMap<String, AdapterFactory>();

	private static final String EXT_FACTORIES = "org.eclipse.emf.edit.itemProviderAdapterFactories"; //$NON-NLS-1$

	public EMFObjectLabelProvider() {
		super(new ReflectiveItemProviderAdapterFactory());
	}

	@Override
	public String getText(Object element) {
		String title = ""; //$NON-NLS-1$
		EObject eObject = getModel(element);
		IItemLabelProvider itemLabelProvider = getItemLabelProvider(eObject);
		if(itemLabelProvider != null) {
			title = itemLabelProvider.getText(eObject);
		}

		if("".equals(title)) { //$NON-NLS-1$
			title = super.getText(eObject);
		}

		return title;
	}

	@Override
	public Image getImage(Object element) {
		Image result = null;
		EObject eObject = getModel(element);
		IItemLabelProvider itemLabelProvider = getItemLabelProvider(eObject);
		if(itemLabelProvider != null) {
			result = getImageFromObject(itemLabelProvider.getImage(eObject));
		}

		return result;
	}

	private EObject getModel(Object element) {
		EObject eObject = null;
		if(element != null && element instanceof StructuredSelection) {
			StructuredSelection selection = (StructuredSelection)element;
			Object o = selection.getFirstElement();
			eObject = resolveSemanticObject(o);
			if(o instanceof IGraphicalEditPart) {
				IGraphicalEditPart editPart = (IGraphicalEditPart)o;
				eObject = editPart.resolveSemanticElement();
			}
		}
		return eObject;
	}

	/**
	 * Resolve semantic element
	 * 
	 * @param object
	 *        the object to resolve
	 * @return <code>null</code> or the semantic element associated to the specified object
	 */
	private EObject resolveSemanticObject(Object object) {
		if(object instanceof EObject) {
			return (EObject)object;
		} else if(object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable)object;
			if(adaptable.getAdapter(EObject.class) != null) {
				return (EObject)adaptable.getAdapter(EObject.class);
			}
		}
		return null;
	}

	private IItemLabelProvider getItemLabelProvider(EObject eObject) {
		IItemLabelProvider itemLabelProvider = null;
		if(eObject != null) {
			AdapterFactory adapterFactory = getEditFactory(eObject);
			if(adapterFactory != null) {
				return (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProviderClass);
			}
		}
		return itemLabelProvider;
	}

	/**
	 * Gets the edit factory.
	 * 
	 * @param eobject
	 *        the eobject
	 * 
	 * @return the edits the factory
	 */
	public static AdapterFactory getEditFactory(EObject eobject) {
		String uri = eobject.eClass().getEPackage().getNsURI();
		return getFactory(uri);
	}

	/**
	 * Gets the factory from uri.
	 * 
	 * @param uri
	 *        the uri
	 * 
	 * @return the factory
	 */
	public static AdapterFactory getFactory(String uri) {
		AdapterFactory factory = factories.get(uri);
		if(factory == null) {
			IConfigurationElement[] extensions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXT_FACTORIES);
			for(IConfigurationElement e : extensions) {
				if(uri.equals(e.getAttribute("uri"))) { //$NON-NLS-1$
					try {
						factory = (AdapterFactory)e.createExecutableExtension("class"); //$NON-NLS-1$
						if(factory != null) {
							factories.put(uri, factory);
						}
					} catch (CoreException e1) {
						// do nothing
					}
				}
			}
		}
		return factory;
	}

}
