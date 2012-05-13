/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.usecase.edit.policies;

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
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.command.wrappers.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.diagram.usecase.edit.commands.AbstractionCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.AbstractionReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.AssociationCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.AssociationReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.CommentAnnotatedElementCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.CommentAnnotatedElementReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.ConstraintConstrainedElementCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.ConstraintConstrainedElementReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.DependencyCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.DependencyReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.GeneralizationCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.GeneralizationReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.PackageImportCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.PackageImportReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.RealizationCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.RealizationReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.UsageCreateCommand;
import org.eclipse.papyrus.diagram.usecase.edit.commands.UsageReorientCommand;
import org.eclipse.papyrus.diagram.usecase.edit.parts.AbstractionEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ActorInComponentEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.AssociationEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.CommentEditPartCN;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ComponentInComponentEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ComponentUsecases3EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ConstraintInComponentEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ExtendEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.IncludeEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.PackageImportEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.RealizationEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UsageEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UseCaseInComponentEditPart;
import org.eclipse.papyrus.diagram.usecase.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.diagram.usecase.providers.UMLElementTypes;

/**
 * @generated
 */
public class ComponentInPackageItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ComponentInPackageItemSemanticEditPolicy() {
		super(UMLElementTypes.Component_3013);
	}


	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View)getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);

		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if(annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			List<EObject> todestroy = new ArrayList<EObject>();
			todestroy.add(req.getElementToDestroy());
			//cmd.add(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
			cmd.add(new EMFtoGMFCommandWrapper(new org.eclipse.emf.edit.command.DeleteCommand(getEditingDomain(), todestroy)));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View)getHost().getModel();
		for(Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node)nit.next();
			switch(UMLVisualIDRegistry.getVisualID(node)) {
			case ComponentUsecases3EditPart.VISUAL_ID:
				for(Iterator<?> cit = node.getChildren().iterator(); cit.hasNext();) {
					Node cnode = (Node)cit.next();
					switch(UMLVisualIDRegistry.getVisualID(cnode)) {
					case UseCaseInComponentEditPart.VISUAL_ID:


						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case IncludeEditPart.VISUAL_ID:
							case ExtendEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case IncludeEditPart.VISUAL_ID:
							case ExtendEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
							case PackageImportEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case ComponentInComponentEditPart.VISUAL_ID:


						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
							case PackageImportEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case CommentEditPartCN.VISUAL_ID:


						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(outgoingLink.getSource().getElement(), null, outgoingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case ConstraintInComponentEditPart.VISUAL_ID:


						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(outgoingLink.getSource().getElement(), null, outgoingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case ActorInComponentEditPart.VISUAL_ID:


						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
							case CommentAnnotatedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case GeneralizationEditPart.VISUAL_ID:
							case AssociationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
							case AbstractionEditPart.VISUAL_ID:
							case UsageEditPart.VISUAL_ID:
							case RealizationEditPart.VISUAL_ID:
							case PackageImportEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
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
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Generalization_4010 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Association_4011 == req.getElementType()) {
			return getGEFWrapper(new AssociationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4012 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.Dependency_4013 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.CommentAnnotatedElement_4014 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.Abstraction_4015 == req.getElementType()) {
			return getGEFWrapper(new AbstractionCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Usage_4016 == req.getElementType()) {
			return getGEFWrapper(new UsageCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Realization_4017 == req.getElementType()) {
			return getGEFWrapper(new RealizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.PackageImport_4019 == req.getElementType()) {
			return getGEFWrapper(new PackageImportCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Generalization_4010 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Association_4011 == req.getElementType()) {
			return getGEFWrapper(new AssociationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4012 == req.getElementType()) {
			return getGEFWrapper(new ConstraintConstrainedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4013 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.CommentAnnotatedElement_4014 == req.getElementType()) {
			return getGEFWrapper(new CommentAnnotatedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Abstraction_4015 == req.getElementType()) {
			return getGEFWrapper(new AbstractionCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Usage_4016 == req.getElementType()) {
			return getGEFWrapper(new UsageCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Realization_4017 == req.getElementType()) {
			return getGEFWrapper(new RealizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.PackageImport_4019 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch(getVisualID(req)) {
		case GeneralizationEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralizationReorientCommand(req));
		case AssociationEditPart.VISUAL_ID:
			return getGEFWrapper(new AssociationReorientCommand(req));
		case DependencyEditPart.VISUAL_ID:
			return getGEFWrapper(new DependencyReorientCommand(req));
		case AbstractionEditPart.VISUAL_ID:
			return getGEFWrapper(new AbstractionReorientCommand(req));
		case UsageEditPart.VISUAL_ID:
			return getGEFWrapper(new UsageReorientCommand(req));
		case RealizationEditPart.VISUAL_ID:
			return getGEFWrapper(new RealizationReorientCommand(req));
		case PackageImportEditPart.VISUAL_ID:
			return getGEFWrapper(new PackageImportReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch(getVisualID(req)) {
		case ConstraintConstrainedElementEditPart.VISUAL_ID:
			return getGEFWrapper(new ConstraintConstrainedElementReorientCommand(req));
		case CommentAnnotatedElementEditPart.VISUAL_ID:
			return getGEFWrapper(new CommentAnnotatedElementReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
