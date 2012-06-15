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
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.diagram.common.commands.PreserveAnchorsPositionCommand;
import org.eclipse.papyrus.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.diagram.sequence.part.Messages;
import org.eclipse.papyrus.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.diagram.sequence.util.ApexSequenceUtil;
import org.eclipse.papyrus.diagram.sequence.util.SequenceUtil;
import org.eclipse.papyrus.ui.toolbox.notification.builders.NotificationBuilder;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;

/**
 * The customn XYLayoutEditPolicy for InteractionCompartmentEditPart.
 */
public class InteractionCompartmentXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * Handle lifeline and combined fragment resize
	 */
	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand compoundCmd = new CompoundCommand();
		compoundCmd.setLabel("Move or Resize");

		for(Object o : request.getEditParts()) {
			GraphicalEditPart child = (GraphicalEditPart)o;
			Object constraintFor = getConstraintFor(request, child);
			if(constraintFor != null) {
				if(child instanceof LifelineEditPart) {
					addLifelineResizeChildrenCommand(compoundCmd, request, (LifelineEditPart)child, 1);
				} else if(child instanceof CombinedFragmentEditPart) {
					Command resizeChildrenCommand = getCombinedFragmentResizeChildrenCommand(request, (CombinedFragmentEditPart)child);
					if(resizeChildrenCommand != null && resizeChildrenCommand.canExecute()) {
						compoundCmd.add(resizeChildrenCommand);
					} else if(resizeChildrenCommand != null) {
						return UnexecutableCommand.INSTANCE;
					}
				}

				Command changeConstraintCommand = createChangeConstraintCommand(request, child, translateToModelConstraint(constraintFor));
				compoundCmd.add(changeConstraintCommand);
			}
		}
		return compoundCmd.unwrap();

	}

	/**
	 * Resize children of LifelineEditPart (Execution specification and lifeline)
	 * 
	 * @param compoundCmd
	 *        The command
	 * @param request
	 *        The request
	 * @param lifelineEditPart
	 *        The lifelineEditPart to resize
	 * @param number
	 *        The number of brother of the LifelineEditPart
	 */
	private static void addLifelineResizeChildrenCommand(CompoundCommand compoundCmd, ChangeBoundsRequest request, LifelineEditPart lifelineEditPart, int number) {
		// If the width increases or decreases, ExecutionSpecification elements need to
		// be moved
		int widthDelta;
		for(ShapeNodeEditPart executionSpecificationEP : lifelineEditPart.getChildShapeNodeEditPart()) {
			if(executionSpecificationEP.resolveSemanticElement() instanceof ExecutionSpecification) {
				// Lifeline's figure where the child is drawn
				Rectangle rDotLine = lifelineEditPart.getContentPane().getBounds();

				// The new bounds will be calculated from the current bounds
				Rectangle newBounds = executionSpecificationEP.getFigure().getBounds().getCopy();

				widthDelta = request.getSizeDelta().width;

				if(widthDelta != 0) {

					if(rDotLine.getSize().width + widthDelta < newBounds.width * 2) {
						compoundCmd.add(UnexecutableCommand.INSTANCE);
					}

					// Apply SizeDelta to the children
					widthDelta = Math.round(widthDelta / ((float)2 * number));

					newBounds.x += widthDelta;

					// Convert to relative
					newBounds.x -= rDotLine.x;
					newBounds.y -= rDotLine.y;

					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Re-location of a ExecutionSpecification due to a Lifeline movement", executionSpecificationEP, newBounds);
					compoundCmd.add(new ICommandProxy(setBoundsCmd));
				}

				// update the enclosing interaction of a moved execution specification
				compoundCmd.add(SequenceUtil.createUpdateEnclosingInteractionCommand(executionSpecificationEP, request.getMoveDelta(), new Dimension(widthDelta, 0)));
			}
		}

		List<LifelineEditPart> innerConnectableElementList = lifelineEditPart.getInnerConnectableElementList();
		for(LifelineEditPart lifelineEP : innerConnectableElementList) {
			addLifelineResizeChildrenCommand(compoundCmd, request, lifelineEP, number * innerConnectableElementList.size());
		}
	}

	/**
	 * abstractGraphicalEditPart보다 아래에 있는 item들 모두의 좌표를 request내의 delta만 이동
	 * 
	 * @param abstractGraphicalEditPart
	 * @param request
	 * @return 
	 */
	public static Command apexGetResizeOrMoveBelowItemsCommand(ChangeBoundsRequest request, AbstractGraphicalEditPart abstractGraphicalEditPart) {
		// Root 아래의 모든 EP 나열
		/*8
		System.out.println("###### Children List Start #####");
		SequenceUtil.omwShowChildrenEditPart(abstractGraphicalEditPart.getRoot(), 0);
		System.out.println("###### Children List End #####");
		*/
/*8
System.out.println("###### Translate Start #####");
Rectangle r1 = new Rectangle(300, 100, 500, 200);
System.out.println("  before translate(30, 10) : " + r1);
r1.translate(30, 10);
System.out.println("   after translate(30, 10) : " + r1);
System.out.println("###### Translate End #####");
*/
		// 넘겨받은 AbstractGraphicalEditPart 보다 아래에 있는 belowList 구성		
		List belowEditPartList = ApexSequenceUtil.apexGetBelowEditPartList(abstractGraphicalEditPart);
		
		CompoundCommand compoundCmd = new CompoundCommand();
		
		if ( belowEditPartList.size() > 0 ) {
			// 이동할 위치
			Point moveDelta = request.getMoveDelta();		

			IFigure thisFigure = abstractGraphicalEditPart.getFigure();
			Rectangle origCFBounds = thisFigure.getBounds().getCopy();

/*8
System.out.println("==========================================");		
System.out.println("before translateToAbsolute Bounds : " + origCFBounds);
*/
			thisFigure.getParent().translateToAbsolute(origCFBounds);

/*8
System.out.println("after translateToAbsolute Bounds  : " + origCFBounds);
*/
			
			origCFBounds.translate(thisFigure.getParent().getBounds().getLocation());
/*8
System.out.println("after translate Bounds            : " + origCFBounds);
System.out.println("==========================================");
*/
			
			// 넘겨받은 AbstractGraphicalEditPart 의 이동 후 위치
			int yAfterMove = thisFigure.getBounds().getBottom().y+moveDelta.y;

			// 넘겨받은 AbstractGraphicalEditPart 바로 아래의 EditPart 구성
			AbstractGraphicalEditPart beneathEditPart  = ApexSequenceUtil.apexGetBeneathEditPart(abstractGraphicalEditPart, belowEditPartList);

			// beneathEditPart 보다 아래로 내릴 경우			
			if (yAfterMove >= beneathEditPart.getFigure().getBounds().getTop().y) {

				Iterator it = belowEditPartList.iterator();
				while( it.hasNext()) {		
					
					EditPart ep = (EditPart)it.next();
/*8
System.out.println("### in omw below list start ###");
System.out.println("   " + ep.getClass().getSimpleName());
System.out.println("### in omw below list end ###");
*/
					
					// handle move of object graphically owned by the lifeline
					if(ep instanceof ShapeEditPart) {
						ShapeEditPart sep = (ShapeEditPart)ep;
						EObject elem = sep.getNotationView().getElement();

						if(elem instanceof InteractionFragment) {
							IFigure figure = sep.getFigure();

							Rectangle figureBounds = figure.getBounds().getCopy();
							figure.getParent().translateToAbsolute(figureBounds);
							
							EditPart parentEP = sep.getParent();

							if(parentEP instanceof LifelineEditPart) {
								ChangeBoundsRequest esRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
								esRequest.setEditParts(sep);
								esRequest.setMoveDelta(moveDelta);

								Command moveESCommand = LifelineXYLayoutEditPolicy.getResizeOrMoveChildrenCommand((LifelineEditPart)parentEP, esRequest, true, false, true);

								if(moveESCommand != null && !moveESCommand.canExecute()) {
									// forbid move if the es can't be moved correctly
									return UnexecutableCommand.INSTANCE;
								} else if(moveESCommand != null) {
									compoundCmd.add(moveESCommand);
								}
							}
							/* apex added start */
							// OMW 여기를 손봐야 함
							if(sep instanceof CombinedFragmentEditPart) {
								// The new bounds will be calculated from the current bounds
								Rectangle newBounds = sep.getFigure().getBounds().getCopy();

								// Convert to relative
								newBounds.x += moveDelta.x;
								newBounds.y += moveDelta.y;
								
								SetBoundsCommand setBoundsCmd = new SetBoundsCommand(sep.getEditingDomain(), "Move of a CombinedFragment due to a Upper CF movement", sep, newBounds);
								compoundCmd.add(new ICommandProxy(setBoundsCmd));						
							}
							/* apex added end */
						}
					}

					// handle move of messages directly attached to a lifeline
					// 보통 메세지가 아니라 Lifeline에 직접붙은 메세지
					// 즉, async 등의 경우
					// sync의 경우 아래의 로직에 의해 이동되는게 아니라
					// 딸린 ExecSpec이 위의 로직에 의해 이동되기 때문에 따라 이동
					
					if(ep instanceof ConnectionEditPart) {
/*8
System.out.println("??? in omw right after ConnectionEditPart ???");
*/
						ConnectionEditPart cep = (ConnectionEditPart)ep;
						Connection msgFigure = cep.getConnectionFigure();

						ConnectionAnchor sourceAnchor = msgFigure.getSourceAnchor();
						ConnectionAnchor targetAnchor = msgFigure.getTargetAnchor();

						Point sourcePoint = sourceAnchor.getReferencePoint();
						Point targetPoint = targetAnchor.getReferencePoint();

						Edge edge = (Edge)cep.getModel();
/*8
System.out.println("is Async? " + cep.getClass().getSimpleName());
System.out.println("Parent of Async : " + cep.getParent().getClass().getSimpleName());
System.out.println("Source of Async : " + cep.getSource().getClass().getSimpleName());
System.out.println("Target of Async : " + cep.getTarget().getClass().getSimpleName());
System.out.println("SourcePoint of Async : " + sourcePoint);
System.out.println("TargetPoint of Async : " + targetPoint);
System.out.println("Bounds before translate : " + thisFigure.getBounds().getCopy());
System.out.println("Bounds after translate : " + origCFBounds);
System.out.println("is Async Source contained: " + origCFBounds.contains(sourcePoint));
System.out.println("is Async Target contained: " + origCFBounds.contains(targetPoint));
*/

						/* apex improved start */
						if(cep.getSource() instanceof LifelineEditPart) {
/*8
System.out.println("??? in omw right after LifelineEditPart source after ConnectionEditPart ???");
*/
							IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getSourceAnchor();
							Rectangle figureBounds = sourceAnchor.getOwner().getBounds();
							compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
						}
						if(cep.getTarget() instanceof LifelineEditPart) {
/*8
System.out.println("??? in omw right after LifelineEditPart target after ConnectionEditPart ???");
*/
							IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getTargetAnchor();
							Rectangle figureBounds = targetAnchor.getOwner().getBounds();
							compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
						}
						/* apex improved end */
/* apex replaced
						if(origCFBounds.contains(sourcePoint) && cep.getSource() instanceof LifelineEditPart) {
System.out.println("??? in omw right after LifelineEditPart source after ConnectionEditPart ???");
							IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getSourceAnchor();
							Rectangle figureBounds = sourceAnchor.getOwner().getBounds();
							compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
						}
						if(origCFBounds.contains(targetPoint) && cep.getTarget() instanceof LifelineEditPart) {
System.out.println("??? in omw right after LifelineEditPart target after ConnectionEditPart ???");
							IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getTargetAnchor();
							Rectangle figureBounds = targetAnchor.getOwner().getBounds();
							compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
						}
*/
					}
				}
			}
		}
		
		return compoundCmd;
	}

	
	/**
	 * apex updated
	 * 
	 * Handle the owning of interaction fragments when moving or resizing a CF.
	 * 
	 * @param compoundCmd
	 *        The command
	 * @param moveDelta
	 *        The move delta (given by the request).
	 * @param sizeDelta
	 *        The size delta (given by the request).
	 * @param combinedFragmentEditPart
	 *        The CF edit part.
	 */
	@SuppressWarnings("unchecked")
	public static Command getCombinedFragmentResizeChildrenCommand(ChangeBoundsRequest request, CombinedFragmentEditPart combinedFragmentEditPart) {
		
		Point moveDelta = request.getMoveDelta();
/*8
System.out.println("***** before moveDelta : " + moveDelta);
moveDelta.x = 0;
*/
System.out.println("***********************************");
System.out.println("***** moveDelta : " + moveDelta);



		Dimension sizeDelta = request.getSizeDelta();

		IFigure cfFigure = combinedFragmentEditPart.getFigure();
		Rectangle origCFBounds = cfFigure.getBounds().getCopy();
System.out.println("***** orig Bounds : " + origCFBounds);
		// 아래 라인 없으면 children 움직이지 않음
System.out.println("***** cfFigure.getParent() : " + cfFigure.getParent());
		cfFigure.getParent().translateToAbsolute(origCFBounds);
		
		// 아래 라인 없으면 rcv 쪽 움직이지 않음
System.out.println("***** cfFigure.getParent().getBounds() : " + cfFigure.getParent().getBounds());
System.out.println("***** cfFigure.getParent().getBounds().getLocation() : " + cfFigure.getParent().getBounds().getLocation());
		origCFBounds.translate(cfFigure.getParent().getBounds().getLocation());
		

System.out.println("***** translated Bounds : " + origCFBounds);

		CompoundCommand compoundCmd = new CompoundCommand();
		// specific case for move :
		// we want the execution specifications graphically owned by the lifeline to move with the combined fragment, and the contained messages too
		if(sizeDelta.equals(0, 0)) {
			// 좌우 이동 불가 처리

//System.out.println("x == 0");
			// retrieve all the edit parts in the registry
			Set<Entry<Object, EditPart>> allEditPartEntries = combinedFragmentEditPart.getViewer().getEditPartRegistry().entrySet();

			for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
				EditPart ep = epEntry.getValue();


				
				// handle move of object graphically owned by the lifeline
				if(ep instanceof ShapeEditPart) {
					ShapeEditPart sep = (ShapeEditPart)ep;
					EObject elem = sep.getNotationView().getElement();

					if(elem instanceof InteractionFragment) {
						IFigure figure = sep.getFigure();

						Rectangle figureBounds = figure.getBounds().getCopy();
						figure.getParent().translateToAbsolute(figureBounds);

						if(origCFBounds.contains(figureBounds)) {
							EditPart parentEP = sep.getParent();

							if(parentEP instanceof LifelineEditPart) {
								ChangeBoundsRequest esRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
								esRequest.setEditParts(sep);
								esRequest.setMoveDelta(moveDelta);

								Command moveESCommand = LifelineXYLayoutEditPolicy.getResizeOrMoveChildrenCommand((LifelineEditPart)parentEP, esRequest, true, false, true);

								if(moveESCommand != null && !moveESCommand.canExecute()) {
									// forbid move if the es can't be moved correctly
									return UnexecutableCommand.INSTANCE;
								} else if(moveESCommand != null) {
									compoundCmd.add(moveESCommand);
								}
							}
						}
					}
				}

				// handle move of messages directly attached to a lifeline
				if(ep instanceof ConnectionEditPart) {
					ConnectionEditPart cep = (ConnectionEditPart)ep;

					Connection msgFigure = cep.getConnectionFigure();

					ConnectionAnchor sourceAnchor = msgFigure.getSourceAnchor();
					ConnectionAnchor targetAnchor = msgFigure.getTargetAnchor();

					Point sourcePoint = sourceAnchor.getReferencePoint();
					Point targetPoint = targetAnchor.getReferencePoint();

					Edge edge = (Edge)cep.getModel();

					if(origCFBounds.contains(sourcePoint) && cep.getSource() instanceof LifelineEditPart) {
						IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getSourceAnchor();
						Rectangle figureBounds = sourceAnchor.getOwner().getBounds();
						compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
					}
					if(origCFBounds.contains(targetPoint) && cep.getTarget() instanceof LifelineEditPart) {
						IdentityAnchor gmfAnchor = (IdentityAnchor)edge.getTargetAnchor();
						Rectangle figureBounds = targetAnchor.getOwner().getBounds();
						compoundCmd.add(new ICommandProxy(getMoveAnchorCommand(moveDelta.y, figureBounds, gmfAnchor)));
					}
				}
			}

			/* apex added start */
			// BeneathEditPart가 있는 경우 함께 이동 처리
			// belowEditPartList  
			List belowEditPartList = ApexSequenceUtil.apexGetBelowEditPartList(combinedFragmentEditPart);
			
			// BeneathEditPart가 있는 경우
			if ( belowEditPartList.size() > 0 ) {
				
/*8
System.out.println("***************** in getCombined~~ *****************");
System.out.println("**** Below List Start ****");

Iterator tmpIter = belowEditPartList.iterator();
int inx = 0;
while(tmpIter.hasNext()) {
	EditPart ep = (EditPart)tmpIter.next();
	System.out.println("["+inx++ +"]"+ep.getClass().getSimpleName());
}
System.out.println("**** Below List END ****");
*/			
				int yAfterMove = cfFigure.getBounds().getBottom().y+moveDelta.y;
				AbstractGraphicalEditPart beneathEditPart  = ApexSequenceUtil.apexGetBeneathEditPart(combinedFragmentEditPart, belowEditPartList);
			
				// beneathEditPart 보다 아래로 내릴 경우
				if (yAfterMove >= beneathEditPart.getFigure().getBounds().getTop().y) {
					CompoundCommand belowProcessedCmd = (CompoundCommand)apexGetResizeOrMoveBelowItemsCommand(request, combinedFragmentEditPart);
				
					// 반환 받은 CompoundCmd를 분해하여 compoundCmd에 추가
					List belowProcessedCmdList = belowProcessedCmd.getCommands();
					Iterator it = belowProcessedCmdList.iterator();
					while(it.hasNext()) {
						Command moveCommand = (Command)it.next();
						if(moveCommand != null && !moveCommand.canExecute()) {
							// forbid move if the es can't be moved correctly
							return UnexecutableCommand.INSTANCE;
						} else if(moveCommand != null) {
							compoundCmd.add(moveCommand);
						}
					}
				}	
			}
			/* apex added end */

			
			
// 이동 끝
		} else {
// Resize 시작
			// calculate the new CF bounds
			Rectangle newBoundsCF = origCFBounds.getCopy();
			newBoundsCF.translate(moveDelta);
			newBoundsCF.resize(sizeDelta);

			CombinedFragment cf = (CombinedFragment)((CombinedFragmentEditPart)combinedFragmentEditPart).resolveSemanticElement();

			
			// 아래 기존 로직은 InteractionOperand resize 처리
			if(combinedFragmentEditPart.getChildren().size() > 0 && combinedFragmentEditPart.getChildren().get(0) instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {

				CombinedFragmentCombinedFragmentCompartmentEditPart compartment = (CombinedFragmentCombinedFragmentCompartmentEditPart)combinedFragmentEditPart.getChildren().get(0);
				List<EditPart> combinedFragmentChildrenEditParts = compartment.getChildren();
				List<InteractionOperandEditPart> interactionOperandEditParts = new ArrayList<InteractionOperandEditPart>();

				InteractionOperand firstOperand = cf.getOperands().get(0);

				// interaction fragments which will not be covered by the operands
				Set<InteractionFragment> notCoveredAnymoreInteractionFragments = new HashSet<InteractionFragment>();
				int headerHeight = 0;

				for(EditPart ep : combinedFragmentChildrenEditParts) {
					if(ep instanceof InteractionOperandEditPart) {
						InteractionOperandEditPart ioEP = (InteractionOperandEditPart)ep;
						InteractionOperand io = (InteractionOperand)ioEP.resolveSemanticElement();

						if(cf.getOperands().contains(io)) {
							interactionOperandEditParts.add(ioEP);
							// fill with all current fragments (filter later)
							notCoveredAnymoreInteractionFragments.addAll(io.getFragments());

							if(firstOperand.equals(io)) {
								Rectangle boundsIO = ioEP.getFigure().getBounds().getCopy();
								ioEP.getFigure().getParent().translateToAbsolute(boundsIO);
								headerHeight = boundsIO.y - origCFBounds.y;
							}
						}
					}
				}

				double heightRatio = (double)(newBoundsCF.height - headerHeight) / (double)(origCFBounds.height - headerHeight);
				double widthRatio = (double)newBoundsCF.width / (double)origCFBounds.width;

				for(InteractionOperandEditPart ioEP : interactionOperandEditParts) {
					InteractionOperand io = (InteractionOperand)ioEP.resolveSemanticElement();

					Rectangle newBoundsIO = SequenceUtil.getAbsoluteBounds(ioEP);

					// apply the move delta which will impact all operands
					newBoundsIO.translate(moveDelta);

					// calculate the new bounds of the interaction operand
					// scale according to the ratio
					newBoundsIO.height = (int)(newBoundsIO.height * heightRatio);
					newBoundsIO.width = (int)(newBoundsIO.width * widthRatio);

					if(firstOperand.equals(io)) {
						// used to compensate the height of the "header" where the OperandKind is stored
						newBoundsIO.y -= headerHeight;
						newBoundsIO.height += headerHeight;
					}

					// ignore current CF and enclosed IO
					Set<InteractionFragment> ignoreSet = new HashSet<InteractionFragment>();
					ignoreSet.add(cf);
					ignoreSet.addAll(cf.getOperands());

					Set<InteractionFragment> coveredInteractionFragments = SequenceUtil.getCoveredInteractionFragments(newBoundsIO, combinedFragmentEditPart, ignoreSet);

					if(coveredInteractionFragments == null) {
						return UnexecutableCommand.INSTANCE;
					}

					// remove fragments that are covered by this operand from the notCovered set
					notCoveredAnymoreInteractionFragments.removeAll(coveredInteractionFragments);

					// set the enclosing operand to the moved/resized one if the current enclosing interaction is the enclosing interaction
					// of the moved/resized operand or of another.
					// => the interaction fragment that are inside an other container (like an enclosed CF) are not modified
					for(InteractionFragment ift : coveredInteractionFragments) {
						if(!cf.equals(ift)) {
							Interaction interactionOwner = ift.getEnclosingInteraction();
							InteractionOperand ioOwner = ift.getEnclosingOperand();

							if((ioOwner != null && (ioOwner.equals(cf.getEnclosingOperand()) || cf.equals(ioOwner.getOwner()))) || (interactionOwner != null && (interactionOwner.equals(cf.getEnclosingInteraction()) || cf.equals(interactionOwner.getOwner())))) {
								compoundCmd.add(new ICommandProxy(SequenceUtil.getSetEnclosingInteractionCommand(ioEP.getEditingDomain(), ift, io)));
							}
						}
					}
				}

				for(InteractionFragment ift : notCoveredAnymoreInteractionFragments) {
					if(cf.getEnclosingOperand() != null) {
						compoundCmd.add(new ICommandProxy(SequenceUtil.getSetEnclosingInteractionCommand(combinedFragmentEditPart.getEditingDomain(), ift, cf.getEnclosingOperand())));
					} else {
						compoundCmd.add(new ICommandProxy(SequenceUtil.getSetEnclosingInteractionCommand(combinedFragmentEditPart.getEditingDomain(), ift, cf.getEnclosingInteraction())));
					}
				}
			}
			
			/* apex added start */
// 여기에 중첩된 경우 Resize 처리
// newBounds.width 가 어느값이상으로 증가하지 않음(parentOp의 width와 관련 있음)
// 중첩된 CF의 좌우이동 금지 이후 작업 계속
//System.out.println("---------------------------------");			
System.out.println("origCFBounds : " + origCFBounds);
System.out.println("resizedCFBounds : " + newBoundsCF);
// parentOperand가 있고
EditPart ep = combinedFragmentEditPart.getParent();
if ( ep instanceof InteractionOperandEditPart ) {
	// Resize결과 parentOperand보다 크면 parentCF도 Resize 처리
	InteractionOperandEditPart ioep = (InteractionOperandEditPart)ep;
	Rectangle parentOperandBounds = ioep.getFigure().getBounds().getCopy();

System.out.println("newBounds.width : " + newBoundsCF.width);
System.out.println("parentOperandBounds.width : " + parentOperandBounds.width);
System.out.println("newBounds.height : " + newBoundsCF.height);
System.out.println("parentOperandBounds.height : " + parentOperandBounds.height);


	if (newBoundsCF.width > parentOperandBounds.width ||
		newBoundsCF.height > parentOperandBounds.height) {
System.out.println("newBounds is bigger than parentOperand");
		// this CF를 포함하는 CF List(a) 구성
		List parentCfList = ApexSequenceUtil.apexGetParentCombinedFragmentList(combinedFragmentEditPart);


		// a를 파라미터로 getCombined... 재귀호출
		Iterator it = parentCfList.iterator();

		while ( it.hasNext()) {
			System.out.println("재귀호출");
			getCombinedFragmentResizeChildrenCommand(request, (CombinedFragmentEditPart)it.next(), compoundCmd);
		}

		/*8
		EditPart opParent = combinedFragmentEditPart.getParent();
		if ( opParent != null)
		System.out.println("Parent of this CF : " + opParent);

		EditPart cfcfParent = opParent.getParent();
		if ( cfcfParent != null)
		System.out.println("Parent of Parent of this CF : " + cfcfParent);

		EditPart cfParent = cfcfParent.getParent();
		if ( cfParent != null)
		System.out.println("Parent of Parent of Parent of this CF : " + cfParent);

		EditPart iaiaParent = cfParent.getParent();
		if ( iaiaParent != null)
		System.out.println("Parent of Parent of Parent of Parent of this CF : " + iaiaParent);

		EditPart iaParent = iaiaParent.getParent();
		if ( iaParent != null)
		System.out.println("Parent of Parent of Parent of Parent of Parent of this CF : " + iaParent);

		EditPart nullParent = iaParent.getParent();
		if ( nullParent != null)
		System.out.println("Parent of Parent of Parent of Parent of Parent of Parent of this CF : " + nullParent);
		*/

					 
					/* apex added end */
	}

	
}


			
// Resize 끝
		}

		// Print a user notification when we are not sure the command is appropriated
		EObject combinedFragment = combinedFragmentEditPart.resolveSemanticElement();
		if(combinedFragment instanceof CombinedFragment && !sizeDelta.equals(0, 0)) {
			if(((CombinedFragment)combinedFragment).getOperands().size() > 1) {
				// append a command which notifies
				Command notifyCmd = new Command() {

					@Override
					public void execute() {
						NotificationBuilder warning = NotificationBuilder.createAsyncPopup(Messages.Warning_ResizeInteractionOperandTitle, NLS.bind(Messages.Warning_ResizeInteractionOperandTxt, System.getProperty("line.separator")));
						warning.run();
					}

					@Override
					public void undo() {
						execute();
					}
				};
				if(notifyCmd.canExecute()) {
					compoundCmd.add(notifyCmd);
				}
			}
		}
		// return null instead of unexecutable empty compound command
		if(compoundCmd.isEmpty()) {
			return null;
		}
		return compoundCmd;
	}		
	
	/**
	 * apex updated
	 * 
	 * 중첩CF처리 재귀호출을 위한 메서드
	 * 
	 * @param request
	 * @param combinedFragmentEditPart
	 * @param ccmd
	 * @return
	 */
	public static Command getCombinedFragmentResizeChildrenCommand(ChangeBoundsRequest request, CombinedFragmentEditPart combinedFragmentEditPart, CompoundCommand ccmd) {
		
		Command cpCmd = getCombinedFragmentResizeChildrenCommand(request, combinedFragmentEditPart);
		
		if ( cpCmd.equals(UnexecutableCommand.INSTANCE)) {
			return UnexecutableCommand.INSTANCE;
		} else if ( cpCmd instanceof CompoundCommand ) {			
			List cpCmdList = ((CompoundCommand)cpCmd).getCommands();
			Iterator itCpCmd = cpCmdList.iterator();
			while ( itCpCmd.hasNext() ) {
				Command aResizeCommand = (Command)itCpCmd.next();
				if ( aResizeCommand != null && !aResizeCommand.canExecute()) {
					return UnexecutableCommand.INSTANCE;
				} else if ( aResizeCommand != null ) {
					ccmd.add(aResizeCommand);
				}
			}	
		} else {
System.out.println("return of getCFResizeChildrenCommand is " + cpCmd);
		}
		return ccmd;
	}
	
	private static ICommand getMoveAnchorCommand(int yDelta, Rectangle figureBounds, IdentityAnchor gmfAnchor) {
		String oldTerminal = gmfAnchor.getId();
		PrecisionPoint pp = BaseSlidableAnchor.parseTerminalString(oldTerminal);

		int yPos = (int)Math.round(figureBounds.height * pp.preciseY);
		yPos += yDelta;

		pp.preciseY = (double)yPos / figureBounds.height;

		if(pp.preciseY > 1.0) {
			pp.preciseY = 1.0;
		} else if(pp.preciseY < 0.0) {
			pp.preciseY = 0.0;
		}

		String newTerminal = (new BaseSlidableAnchor(null, pp)).getTerminal();

		return new SetValueCommand(new SetRequest(gmfAnchor, NotationPackage.Literals.IDENTITY_ANCHOR__ID, newTerminal));
	}

	/**
	 * Change constraint for comportment by return null if the resize is lower than the minimun
	 * size.
	 */
	@Override
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		Rectangle rect = new PrecisionRectangle(child.getFigure().getBounds());
		child.getFigure().translateToAbsolute(rect);
		rect = request.getTransformedRectangle(rect);
		child.getFigure().translateToRelative(rect);
		rect.translate(getLayoutOrigin().getNegated());

		if(request.getSizeDelta().width == 0 && request.getSizeDelta().height == 0) {
			Rectangle cons = getCurrentConstraintFor(child);
			if(cons != null) {
				rect.setSize(cons.width, cons.height);
			}
		} else { // resize
			Dimension minSize = getMinimumSizeFor(child);
			if(rect.width < minSize.width) {
				return null;
			}
			if(rect.height < minSize.height) {
				return null;
			}
		}
		rect = (Rectangle)getConstraintFor(rect);

		Rectangle cons = getCurrentConstraintFor(child);
		if(request.getSizeDelta().width == 0) {
			rect.width = cons.width;
		}
		if(request.getSizeDelta().height == 0) {
			rect.height = cons.height;
		}

		return rect;
	}

	/**
	 * Handle mininum size for lifeline
	 */
	@Override
	protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
		Dimension minimunSize;
		if(child instanceof LifelineEditPart) {
			minimunSize = getMinimumSizeFor((LifelineEditPart)child);
		} else {
			minimunSize = super.getMinimumSizeFor(child);
		}
		return minimunSize;
	}

	/**
	 * Get minimun for a lifeline
	 * 
	 * @param child
	 *        The lifeline
	 * @return The minimun size
	 */
	private Dimension getMinimumSizeFor(LifelineEditPart child) {
		LifelineEditPart lifelineEditPart = child;
		Dimension minimunSize = lifelineEditPart.getFigure().getMinimumSize();
		for(LifelineEditPart lifelineEP : lifelineEditPart.getInnerConnectableElementList()) {
			minimunSize.union(getMinimumSizeFor(lifelineEP));
		}
		for(ShapeNodeEditPart executionSpecificationEP : lifelineEditPart.getChildShapeNodeEditPart()) {
			int minimunHeight = executionSpecificationEP.getFigure().getBounds().bottom();
			minimunSize.setSize(new Dimension(minimunSize.width, Math.max(minimunSize.height, minimunHeight)));
		}
		return minimunSize;
	}

	/**
	 * Block adding element by movement on Interaction
	 */
	@Override
	public Command getAddCommand(Request request) {
		if(request instanceof ChangeBoundsRequest) {
			return UnexecutableCommand.INSTANCE;
		}

		return super.getAddCommand(request);
	}


	/**
	 * Overrides to change the policy of connection anchors when resizing the lifeline.
	 * When resizing the lifeline, the connection must not move.
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Command getCommand(Request request) {
		if(request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest cbr = (ChangeBoundsRequest)request;

			int resizeDirection = cbr.getResizeDirection();

			CompoundCommand compoundCmd = new CompoundCommand("Resize of Interaction Compartment Elements");

			for(EditPart ep : (List<EditPart>)cbr.getEditParts()) {
				if(ep instanceof LifelineEditPart) {
					// Lifeline EditPart
					LifelineEditPart lifelineEP = (LifelineEditPart)ep;

					int preserveY = PreserveAnchorsPositionCommand.PRESERVE_Y;
					Dimension newSizeDelta = PreserveAnchorsPositionCommand.getSizeDeltaToFitAnchors(lifelineEP, cbr.getSizeDelta(), preserveY);

					// SetBounds command modifying the sizeDelta
					compoundCmd.add(getSetBoundsCommand(lifelineEP, cbr, newSizeDelta));

					// PreserveAnchors command
					compoundCmd.add(new ICommandProxy(new PreserveAnchorsPositionCommand(lifelineEP, newSizeDelta, preserveY, lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure(), resizeDirection)));
				}
			}

			if(compoundCmd.size() == 0) {
				return super.getCommand(request);
			} else {
				return compoundCmd;
			}
		}

		return super.getCommand(request);
	}

	/**
	 * It obtains an appropriate SetBoundsCommand for a LifelineEditPart. The
	 * newSizeDelta provided should be equal o less than the one contained in
	 * the request. The goal of this newDelta is to preserve the anchors'
	 * positions after the resize. It is recommended to obtain this newSizeDelta
	 * by means of calling
	 * PreserveAnchorsPositionCommand.getSizeDeltaToFitAnchors() operation
	 * 
	 * @param lifelineEP
	 *        The Lifeline that will be moved or resized
	 * @param cbr
	 *        The ChangeBoundsRequest for moving or resized the lifelineEP
	 * @param newSizeDelta
	 *        The sizeDelta to used instead of the one contained in the
	 *        request
	 * @return The SetBoundsCommand
	 */
	@SuppressWarnings("rawtypes")
	protected Command getSetBoundsCommand(LifelineEditPart lifelineEP, ChangeBoundsRequest cbr, Dimension newSizeDelta) {
		// Modify request
		List epList = cbr.getEditParts();
		Dimension oldSizeDelta = cbr.getSizeDelta();
		cbr.setEditParts(lifelineEP);
		cbr.setSizeDelta(newSizeDelta);

		// Obtain the command with the modified request
		Command cmd = super.getCommand(cbr);

		// Restore the request
		cbr.setEditParts(epList);
		cbr.setSizeDelta(oldSizeDelta);

		// Return the SetBoundsCommand only for the Lifeline and with the
		// sizeDelta modified in order to preserve the links' anchors positions
		return cmd;
	}


	/**
	 * apex updated
	 * 
	 * Lifeline 생성 시 상단 고정 정렬
	 */
	@Override
	protected Object getConstraintFor(CreateRequest request) {
/* apex added start*/
		CreateViewRequest req = (CreateViewRequest)request;
		Rectangle BOUNDS = (Rectangle)super.getConstraintFor(request);
		Iterator<? extends ViewDescriptor> iter = req.getViewDescriptors().iterator();
		while (iter.hasNext()) {
			ViewDescriptor descriptor = iter.next();
			IElementType elementType = (IElementType)descriptor.getElementAdapter().getAdapter(IElementType.class);
			if (UMLElementTypes.Lifeline_3001.equals(elementType)) {
				BOUNDS.y = 0;
			}
		}
		return BOUNDS;
/* apex added end */
	}


}
