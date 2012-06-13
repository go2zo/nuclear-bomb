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
 *  Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.sequence.edit.policies;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.sequence.command.ChangeEdgeTargetCommand;
import org.eclipse.papyrus.diagram.sequence.command.CreateElementAndNodeCommand;
import org.eclipse.papyrus.diagram.sequence.command.ApexPreserveAnchorsPositionCommand;
import org.eclipse.papyrus.diagram.sequence.command.ApexSetBoundsForExecutionSpecificationCommand;
import org.eclipse.papyrus.diagram.sequence.edit.parts.ActionExecutionSpecificationEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.BehaviorExecutionSpecificationEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;

/**
 * Edit Policy to create an element on a lifeline associated with the creation of a message.
 * For example it can be used to create a Destruction Event with a Message Delete
 * or the target Execution Specification with a Message Sync.
 * 
 * @author Mathieu Velten
 * 
 */
public class ElementCreationWithMessageEditPolicy extends LifelineChildGraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CompoundCommand compound = new CompoundCommand();

		Command command = super.getConnectionCompleteCommand(request);

		if(command != null && command.canExecute()) {
			compound.add(command);

			if(request instanceof CreateConnectionViewAndElementRequest) {
				CreateConnectionViewAndElementRequest viewRequest = (CreateConnectionViewAndElementRequest)request;
				EditPart targetEP = getTargetEditPart(viewRequest);
				EObject target = ViewUtil.resolveSemanticElement((View)targetEP.getModel());
				EditPart sourceEP = viewRequest.getSourceEditPart();
				EObject source = ViewUtil.resolveSemanticElement((View)sourceEP.getModel());

				if(getSyncMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint())
						|| getReplyMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint())
						/* apex added start */
						/** jiho - AsyncMessage 생성 시에도 ExecSpec 생성하는 로직 작동 */
						|| apexGetAsyncMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint())
						/* apex added end */
						) {
					if(target instanceof Lifeline ||
					// handle reflexive synch message by creating a new ES
					(target instanceof ExecutionSpecification && target.equals(source))) {
						InteractionFragment ift = SequenceUtil.findInteractionFragmentContainerAt(viewRequest.getLocation(), getHost());

						// retrieve the good execution specification type using the source of the message
						IHintedType elementType = null;
						if(request.getSourceEditPart() instanceof ActionExecutionSpecificationEditPart) {
							elementType = (IHintedType)UMLElementTypes.ActionExecutionSpecification_3006;
						} else if(request.getSourceEditPart() instanceof BehaviorExecutionSpecificationEditPart) {
							elementType = (IHintedType)UMLElementTypes.BehaviorExecutionSpecification_3003;
						}
						/* apex added start */
						/** jiho - Source가 Lifeline일 경우 BehvExecSpec 생성 */
						else if (request.getSourceEditPart() instanceof LifelineEditPart) {
							elementType =(IHintedType)UMLElementTypes.BehaviorExecutionSpecification_3003;
						}
						/* apex added end */

						if(target instanceof ExecutionSpecification) {
							// retrieve its associated lifeline
							targetEP = targetEP.getParent();
							target = ViewUtil.resolveSemanticElement((View)targetEP.getModel());
						}

						if(elementType != null) {
							CreateElementAndNodeCommand createExecutionSpecificationCommand = new CreateElementAndNodeCommand(getEditingDomain(), (ShapeNodeEditPart)targetEP, target, elementType, request.getLocation());
							createExecutionSpecificationCommand.putCreateElementRequestParameter(SequenceRequestConstant.INTERACTIONFRAGMENT_CONTAINER, ift);
							compound.add(createExecutionSpecificationCommand);

							// put the anchor at the top of the figure
							ChangeEdgeTargetCommand changeTargetCommand = new ChangeEdgeTargetCommand(getEditingDomain(), createExecutionSpecificationCommand, viewRequest.getConnectionViewDescriptor(), "(0.5, 0.0)");
							compound.add(new ICommandProxy(changeTargetCommand));
							
							/* apex added start */
							/** jiho - Source인 ExecSpec의 Bounds, Connection의 Anchor을 자동변경하는 생성 */
							if (source instanceof ExecutionSpecification) {
//								ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
//								changeBoundsRequest.setEditParts(sourceEP);
//								changeBoundsRequest.setLocation(request.getLocation());
//								changeBoundsRequest.setExtendedData(request.getExtendedData());
//								changeBoundsRequest.setMoveDelta(new Point(0, 0));
//								changeBoundsRequest.setResizeDirection(PositionConstants.SOUTH);
//								
//								IFigure figure = ((ShapeNodeEditPart)sourceEP).getFigure();
//								Rectangle bounds = figure.getBounds().getCopy();
//								figure.getParent().translateToAbsolute(bounds);
//								Point sourcePoint = (Point)request.getExtendedData().get(SequenceRequestConstant.SOURCE_LOCATION_DATA);
//								int bottom = Math.max(bounds.bottom(), sourcePoint.y + 60);
//								Dimension resizeDelta = new Dimension(0, bottom - bounds.bottom());
//								changeBoundsRequest.setSizeDelta(resizeDelta);
//								
//								Command changeBoundsCommand = sourceEP.getCommand(changeBoundsRequest);
//								if (changeBoundsCommand != null) {
//									compound.add(changeBoundsCommand);
//								}
								
								View view = (View)sourceEP.getModel();
								ApexSetBoundsForExecutionSpecificationCommand setBoundsCommand = new ApexSetBoundsForExecutionSpecificationCommand(
										getEditingDomain(), createExecutionSpecificationCommand, new EObjectAdapter(view));
								compound.add(new ICommandProxy(setBoundsCommand));
								
								compound.add(new ICommandProxy(new ApexPreserveAnchorsPositionCommand(
										(ShapeNodeEditPart)sourceEP, setBoundsCommand, ApexPreserveAnchorsPositionCommand.PRESERVE_Y)));
							}
							/* apex added end */
						}
					}
				}
			}
		}

		return compound;
	}

	private static String getSyncMessageHint() {
		IHintedType message = (IHintedType)UMLElementTypes.Message_4003;
		return message.getSemanticHint();
	}

	private static String getReplyMessageHint() {
		IHintedType message = (IHintedType)UMLElementTypes.Message_4005;
		return message.getSemanticHint();
	}

	/* apex added start - jiho */
	private static String apexGetAsyncMessageHint() {
		IHintedType message = (IHintedType)UMLElementTypes.Message_4004;
		return message.getSemanticHint();
	}
	/* apex added end */
	
	private TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart)getHost()).getEditingDomain();
	}

	@Override
	protected ConnectionAnchor getSourceConnectionAnchor(
			CreateConnectionRequest request) {
		ConnectionAnchor ca = super.getSourceConnectionAnchor(request);
		Point ref = ca.getReferencePoint();
		System.out.println("source: " + ca.getLocation(ref));
		return ca;
	}

	@Override
	protected ConnectionAnchor getTargetConnectionAnchor(
			CreateConnectionRequest request) {
		ConnectionAnchor ca = super.getTargetConnectionAnchor(request);
		Point ref = ca.getReferencePoint();
		System.out.println("target: " + ca.getLocation(ref));
		return ca;
	}

	@Override
	protected ConnectionAnchor getConnectionTargetAnchor(Request request) {
		// TODO Auto-generated method stub
		return super.getConnectionTargetAnchor(request);
	}
	
	
}
