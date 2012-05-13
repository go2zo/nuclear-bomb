package org.eclipse.papyrus.diagram.statemachine.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.command.wrappers.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.FinalStateEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateChoiceEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateDeepHistoryEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateEntryPointEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateExitPointEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateForkEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateInitialEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateJoinEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateJunctionEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateShallowHistoryEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.PseudostateTerminateEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.RegionCompartmentEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.StateEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.TransitionEditPart;
import org.eclipse.papyrus.diagram.statemachine.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.diagram.statemachine.providers.UMLElementTypes;

/**
 * @generated
 */
public class RegionItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public RegionItemSemanticEditPolicy() {
		super(UMLElementTypes.Region_3000);
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (UMLVisualIDRegistry.getVisualID(node)) {
			case RegionCompartmentEditPart.VISUAL_ID:
				for (Iterator<?> cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (UMLVisualIDRegistry.getVisualID(cnode)) {
					case PseudostateInitialEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateJoinEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateForkEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateChoiceEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateJunctionEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateShallowHistoryEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateDeepHistoryEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateTerminateEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case FinalStateEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case StateEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateEntryPointEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: false
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case PseudostateExitPointEditPart.VISUAL_ID:

						for (Iterator<?> it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(incomingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								break;
							}
						}

						for (Iterator<?> it = cnode.getSourceEdges().iterator(); it
								.hasNext();) {
							Edge outgoingLink = (Edge) it.next();
							switch (UMLVisualIDRegistry
									.getVisualID(outgoingLink)) {
							case TransitionEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(
										outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(),
										outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: false
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);

		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: true
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			List<EObject> todestroy = new ArrayList<EObject>();
			todestroy.add(req.getElementToDestroy());
			//cmd.add(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
			cmd.add(new EMFtoGMFCommandWrapper(
					new org.eclipse.emf.edit.command.DeleteCommand(
							getEditingDomain(), todestroy)));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

}
