/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.sequence.edit.policies;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.diagram.sequence.edit.parts.ActionExecutionSpecificationEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.BehaviorExecutionSpecificationEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ActionExecutionSpecification;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;

/**
 * This creation policy is used to move covered interaction fragments into the interaction operand
 * when creating a new combined fragment.
 * 
 * @author mvelten
 * 
 */
public class CombinedFragmentCreationEditPolicy extends CreationEditPolicy {

	/**
	 * CombinedFragment 생성 시 ExecSpec외에 다른 Element와 교차하는 경우 생성되지 null을 return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Command getCreateElementAndViewCommand(CreateViewAndElementRequest request) {

		/* apex improved start */
		if(isDerivedCombinedFragment(request.getViewAndElementDescriptor().getSemanticHint())) {

			Rectangle selectionRect = getSelectionRectangle(request);

			// CombinedFragment 생성 시 ExecSpec과의 교차는 무시되도록 처리 
			if ( selectionRect.width > 0 && selectionRect.height > 0) {
				Set<InteractionFragment> ignoreInteractionFragments = new HashSet<InteractionFragment>();
				
                EditPart targetEditPart = getTargetEditPart(request);   
				
				// 중첩 CombinedFragment 생성, 즉 다른 CF의 Operand내에서 CF 생성하는 경우
                // 직접 InteractionOperandEditPart에서 ExecSpec을 추출하여 ignoreInteractionFragments 직접 추가
				if ( targetEditPart instanceof InteractionOperandEditPart ) {
					EObject eObj = ((InteractionOperandEditPart) targetEditPart).getNotationView().getElement();
					if ( eObj instanceof InteractionOperand ) {
						InteractionOperand io = (InteractionOperand)eObj;
						List fragList = io.getFragments();
						Iterator fragIter = fragList.iterator();
						while ( fragIter.hasNext() ) {
							Object obj = fragIter.next();
							if ( obj instanceof ActionExecutionSpecification ||
								 obj instanceof BehaviorExecutionSpecification )
							{
								ignoreInteractionFragments.add((InteractionFragment)obj);
							}
						}
						
					}
				// 중첩 생성이 아닌 경우 InteractionCompartmentEditPart에서 children 추출하여 ignoreInteractionFragments 생성
				} else {
					List childrenList = targetEditPart.getChildren(); 
	                Iterator iter = childrenList.iterator();
	                while (iter.hasNext()) {
	                	ShapeEditPart ep = (ShapeEditPart)iter.next();
	                	EObject eObj = ep.getNotationView().getElement();
	                	
						if ( eObj instanceof Lifeline ) {

							Iterator iter1 = ep.getChildren().iterator();
							while ( iter1.hasNext() ) {
								EditPart lifelineChild = (EditPart)iter1.next();							
								
								if ( lifelineChild instanceof ActionExecutionSpecificationEditPart ||
									 lifelineChild instanceof BehaviorExecutionSpecificationEditPart ) {
			                		ShapeEditPart sep = (ShapeEditPart)lifelineChild;
			                		EObject eObj2 = sep.getNotationView().getElement();
			                		ignoreInteractionFragments.add((InteractionFragment)eObj2);
				                }		
							}						
						}                	
	                }					
				}

				// 생성 할 CombinedFragment가 ignoreFragments 외에 다른 요소와 겹치면 null을 return 받음
				Set<InteractionFragment> coveredInteractionFragments = SequenceUtil.getCoveredInteractionFragments(selectionRect, getHost(), ignoreInteractionFragments);

				request.getExtendedData().put(SequenceRequestConstant.COVERED_INTERACTIONFRAGMENTS, coveredInteractionFragments);					

				if (coveredInteractionFragments == null) {
					return UnexecutableCommand.INSTANCE;
				}								
			}
		}		
		/* apex improved end */
		
		/* apex replaced
		if(isDerivedCombinedFragment(request.getViewAndElementDescriptor().getSemanticHint())) {

			Rectangle selectionRect = getSelectionRectangle(request);			
			
			Set<InteractionFragment> coveredInteractionFragments = SequenceUtil.getCoveredInteractionFragments(selectionRect, getHost(), null);

			request.getExtendedData().put(SequenceRequestConstant.COVERED_INTERACTIONFRAGMENTS, coveredInteractionFragments);

		}		
		*/
		return super.getCreateElementAndViewCommand(request);
	}

	/**
	 * Retrieve the selection rectangle associated with the request.
	 * 
	 * @param request
	 *        the request
	 * @return
	 */
	private Rectangle getSelectionRectangle(CreateViewAndElementRequest request) {
		Rectangle selectionRect = new Rectangle();

		Point location = request.getLocation();
		Dimension size = request.getSize();

		if(location != null) {
			selectionRect.x = location.x;
			selectionRect.y = location.y;
		} else {
			// default values (should not be triggered)
			selectionRect.x = 100;
			selectionRect.y = 100;
		}

		if(size != null) {
			selectionRect.height = size.height;
			selectionRect.width = size.width;
		} else {
			/* apex improved start */
			// hardcode 값으로 rect가 생성되어 포함 관련 문제 발생하여 0 처리
			selectionRect.height = 0;
			selectionRect.width = 0;
			/* apex improved end */
			/* apex replaced
			// default values hardcoded...
			selectionRect.height = 61;
			selectionRect.width = 112;
            */
 
		}

		return selectionRect;
	}

	/**
	 * Check if it is a combined fragment or something similar which needs this policy to move ift in the correct container.
	 * 
	 * @param hint
	 *        the semantic hint
	 * @return
	 */
	private static boolean isDerivedCombinedFragment(String hint) {
		if(((IHintedType)UMLElementTypes.InteractionOperand_3005).getSemanticHint().equals(hint)) {
			return true;
		}
		if(((IHintedType)UMLElementTypes.CombinedFragment_3004).getSemanticHint().equals(hint)) {
			return true;
		}
		if(((IHintedType)UMLElementTypes.ConsiderIgnoreFragment_3007).getSemanticHint().equals(hint)) {
			return true;
		}
		return false;
	}
}
