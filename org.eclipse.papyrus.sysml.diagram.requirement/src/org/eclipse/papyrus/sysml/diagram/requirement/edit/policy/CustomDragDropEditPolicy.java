package org.eclipse.papyrus.sysml.diagram.requirement.edit.policy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.clazz.custom.policies.ClassDiagramDragDropEditPolicy;
import org.eclipse.papyrus.sysml.diagram.requirement.provider.RequirementDiagramElementTypes;
import org.eclipse.papyrus.sysml.util.SysmlResource;
import org.eclipse.uml2.uml.Abstraction;

public class CustomDragDropEditPolicy extends ClassDiagramDragDropEditPolicy {

	@Override
	public int getLinkWithClassVisualID(EObject domainElement) {
		String semanticHint = "-1";

		if(domainElement instanceof Abstraction) {
			semanticHint = RequirementDiagramElementTypes.ABSTRACTION.getSemanticHint();
		}

		return new Integer(semanticHint);

	}

	@Override
	public int getNodeVisualID(View containerView, EObject domainElement) {
		String semanticHint = "-1";

		// Fill the semantic hint during the drop when the top level is the Requirement Diagram
		if(containerView instanceof Diagram) {
			if(domainElement instanceof org.eclipse.uml2.uml.Package) {
				semanticHint = RequirementDiagramElementTypes.PACKAGE.getSemanticHint();
			}
			if((domainElement instanceof org.eclipse.uml2.uml.Class)) {
				org.eclipse.uml2.uml.Class domainElementClass = (org.eclipse.uml2.uml.Class)domainElement;
				if(domainElementClass.getAppliedStereotype(SysmlResource.REQUIREMENT_ID) != null) {
					semanticHint = RequirementDiagramElementTypes.CLASS.getSemanticHint();
				}
			}
		}

		// Fill the semantic hint during the drop when the top level is the an element in the Requirement Diagram  (a Package for instance)
		else {
			if(domainElement instanceof org.eclipse.uml2.uml.Package) {
				semanticHint = RequirementDiagramElementTypes.PACKAGE_CN.getSemanticHint();
			}
			if((domainElement instanceof org.eclipse.uml2.uml.Class)) {
				org.eclipse.uml2.uml.Class domainElementClass = (org.eclipse.uml2.uml.Class)domainElement;
				if(domainElementClass.getAppliedStereotype(SysmlResource.REQUIREMENT_ID) != null) {
					semanticHint = RequirementDiagramElementTypes.CLASS_CN.getSemanticHint();
				}
			}
		}

		// In the case of we want to Drag and drop something else than a package or a Requirement, if this is a NamedElement, 
		// we can fill the semanticHint with the NamedElement dedicated Value
		if(semanticHint == "-1") {
			if((domainElement instanceof org.eclipse.uml2.uml.NamedElement)) {
				semanticHint = RequirementDiagramElementTypes.NAMED_ELEMENT.getSemanticHint();
			}
		}

		return new Integer(semanticHint);
	}
}
