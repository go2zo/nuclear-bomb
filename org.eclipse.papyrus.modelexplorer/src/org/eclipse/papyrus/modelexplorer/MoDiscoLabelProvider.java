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
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.modelexplorer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizableModelLabelProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.core.editorsfactory.IPageIconsRegistry;
import org.eclipse.papyrus.core.editorsfactory.PageIconsRegistry;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.validation.ValidationTool;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * the label provider that inherits of modisco label provider
 * 
 */
public class MoDiscoLabelProvider extends CustomizableModelLabelProvider {
	
	/** icon registry */
	private IPageIconsRegistry editorRegistry;

	/** point corresponding to the size 16x16 */
	private static final Point size16  = new Point (16, 16);
	
	/** image registry */
	private static final ImageRegistry registry = new ImageRegistry ();
	
	/**
	 * Creates a new MoDiscoLabelProvider.
	 */
	public MoDiscoLabelProvider() {
		super(Activator.getDefault().getCustomizationManager());
	}

	/**
	 * Returns the message of the marker for the specified element
	 * @param element the element for which the marker message should be found
	 * @return the message of the marker for the specified element
	 */
	public String getMarkerMessage(Object element) {
		ValidationTool vt = new ValidationTool (element);
		return vt.getMarkerMessages();
	}
	
	/**
	 * return the image of an element in the model browser
	 * evaluates error markers.
	 */
	@Override
	public Image getImage(Object element) {
		ValidationTool vt = new ValidationTool (element);
		vt.tryChildIfEmpty();
		int severity = vt.getSeverity();
		if(element instanceof Diagram) {
			return getDecoratedImage (getEditorRegistry().getEditorIcon(element), severity);
		}
		return getDecoratedImage (super.getImage(element), severity);
	}

	/**
	 * Return a DecoratedImage, thanks to Torkild U. Resheim (Trondheim, Norway)
	 * @param baseImage the base image to decorate
	 * @param severity the severity of the error
	 * @return the decorated image
	 */
	private Image getDecoratedImage(Image baseImage, int severity) {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();		
		ImageDescriptor overlay = null;
		switch (severity) {
			case IMarker.SEVERITY_ERROR:
				overlay = sharedImages.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
				break;
			case IMarker.SEVERITY_WARNING:
				overlay = sharedImages.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
				break;
			default:
				return baseImage;
		}
		// Construct a new image identifier
		String decoratedImageId = baseImage.toString().concat(String.valueOf(severity));
		// Return the stored image if we have one
		if (registry.get(decoratedImageId) == null) {
			// Otherwise create a new image and store it
			DecorationOverlayIcon decoratedImage = new DecorationOverlayIcon(
				baseImage, new ImageDescriptor[] { null, null, null,
					overlay, null }, size16) {
			};
			registry.put(decoratedImageId, decoratedImage);
		}
		return registry.get(decoratedImageId);
	}
	
	/**
	 * Get the EditorRegistry used to create editor instances. This default
	 * implementation return the singleton eINSTANCE. This method can be
	 * subclassed to return another registry.
	 * 
	 * @return the singleton eINSTANCE of editor registry
	 * @throws ServiceException
	 */
	protected IPageIconsRegistry getEditorRegistry() {
		if(editorRegistry == null) {
			editorRegistry = createEditorRegistry();
		}
		return editorRegistry;
	}

	/**
	 * Return the EditorRegistry for nested editor descriptors. Subclass should
	 * implements this method in order to return the registry associated to the
	 * extension point namespace.
	 * 
	 * @return the EditorRegistry for nested editor descriptors
	 * @throws ServiceException
	 */
	protected IPageIconsRegistry createEditorRegistry() {
		try {
			return EditorUtils.getServiceRegistry().getService(IPageIconsRegistry.class);
		} catch (ServiceException e) {
			// Not found, return an empty one which return null for each
			// request.
			return new PageIconsRegistry();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(Object element) {
		String text = null;
		if(element instanceof Diagram) {
			Diagram diagram = (Diagram)element;
			text = diagram.getName();
		} else if(element instanceof IAdaptable) {
			EObject obj = (EObject)((IAdaptable)element).getAdapter(EObject.class);
			if(obj instanceof InternalEObject && obj.eIsProxy()) {
				InternalEObject internal = (InternalEObject)obj;
				text = NLS.bind(Messages.MoDiscoLabelProvider_ProxyLabel, obj.getClass().getSimpleName(), internal.eProxyURI().trimFragment()); 
					// Messages.MoDiscoLabelProvider_0 +  + Messages.MoDiscoLabelProvider_1 + ;;
			} else {
				text = super.getText(element);
			}
		} else {
			text = super.getText(element);
		}
		return text;
	}	
}
