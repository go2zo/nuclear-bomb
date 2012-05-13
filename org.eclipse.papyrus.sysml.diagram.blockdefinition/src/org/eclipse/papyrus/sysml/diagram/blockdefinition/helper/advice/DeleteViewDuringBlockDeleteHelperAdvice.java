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
package org.eclipse.papyrus.sysml.diagram.blockdefinition.helper.advice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyDependentsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.util.CrossReferencerUtil;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.ElementTypes;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <pre>
 * Edit helper advice that delete Part or Reference views from diagram when its type (Block) is deleted.
 * @unused - The Part or Ref are deleted when their Block type is deleted (done in Service Edit).
 * </pre>
 */
public class DeleteViewDuringBlockDeleteHelperAdvice extends AbstractEditHelperAdvice {

	@Override
	protected ICommand getBeforeDestroyDependentsCommand(DestroyDependentsRequest request) {

		
		EObject elementToEdit = request.getElementToDestroy();
		Set<View> viewsToDestroy = new HashSet<View>();

		// Get all Part or Reference typed by this deleted Block
		Iterator<?> references = EMFCoreUtil.getReferencers(elementToEdit, new EReference[]{UMLPackage.eINSTANCE.getTypedElement_Type()}).iterator();
		while(references.hasNext()) {
			Object current = references.next();
			
			if (! (current instanceof EObject)) {
				continue;
			}
			
			EObject property = (EObject) current;
			
			// Search inconsistent views if the reference is a Part or a Reference
			if (((ISpecializationType) SysMLElementTypes.PART_PROPERTY).getMatcher().matches(property)) {
				viewsToDestroy.addAll(getPartViewsToDestroy(property));
			}
			if (((ISpecializationType) SysMLElementTypes.REFERENCE_PROPERTY).getMatcher().matches(property)) {
				viewsToDestroy.addAll(getReferenceViewsToDestroy(property));
			}
		}

		if(!(viewsToDestroy.isEmpty())) {
			DestroyDependentsRequest req = new DestroyDependentsRequest(request.getEditingDomain(), elementToEdit, false);
			return req.getDestroyDependentsCommand(viewsToDestroy);
		}
		
		return super.getBeforeDestroyDependentsCommand(request);
	}

	/**
	 * This methods looks for inconsistent Part views to delete.
	 * 
	 * @param modifiedObject
	 *        the modified {@link EObject}
	 * @return the list of {@link View} to delete
	 */
	private Set<View> getPartViewsToDestroy(EObject modifiedObject) {
		Set<View> viewsToDestroy = new HashSet<View>();

		Iterator<View> viewIt = CrossReferencerUtil.getCrossReferencingViews(modifiedObject, ElementTypes.DIAGRAM_ID).iterator();
		while(viewIt.hasNext()) {
			View view = (View)viewIt.next();

			String containerType = ViewUtil.getViewContainer(view) != null ? ViewUtil.getViewContainer(view).getType() : null;

			if(SysMLGraphicalTypes.COMPARTMENT_SYSML_PART_AS_LIST_ID.equals(containerType)) {
				viewsToDestroy.add(view);
			}
		}

		return viewsToDestroy;
	}
	
	/**
	 * This methods looks for inconsistent Reference views to delete.
	 * 
	 * @param modifiedObject
	 *        the modified {@link EObject}
	 * @return the list of {@link View} to delete
	 */
	private Set<View> getReferenceViewsToDestroy(EObject modifiedObject) {
		Set<View> viewsToDestroy = new HashSet<View>();

		Iterator<View> viewIt = CrossReferencerUtil.getCrossReferencingViews(modifiedObject, ElementTypes.DIAGRAM_ID).iterator();
		while(viewIt.hasNext()) {
			View view = (View)viewIt.next();

			String containerType = ViewUtil.getViewContainer(view) != null ? ViewUtil.getViewContainer(view).getType() : null;

			if(SysMLGraphicalTypes.COMPARTMENT_SYSML_REFERENCE_AS_LIST_ID.equals(containerType)) {
				viewsToDestroy.add(view);
			}
		}

		return viewsToDestroy;
	}
}
