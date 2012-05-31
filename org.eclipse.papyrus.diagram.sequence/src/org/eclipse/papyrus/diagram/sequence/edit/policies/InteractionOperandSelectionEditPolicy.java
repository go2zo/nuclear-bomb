package org.eclipse.papyrus.diagram.sequence.edit.policies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

/**
 * OMW
 * Operand 선택 시 테두리 보여주는 EditPolicy
 * NonResizableEditPolicy.showSelection()을 활용하여 테두리 표시
 * 기본적인 기능은 NonResizableEditPolicy와 같으며
 * Drag가 발생하지 않도록 isDragAllowed() 만 return false 처리 
 */
public class InteractionOperandSelectionEditPolicy extends NonResizableEditPolicy {
	
	private IFigure focusRect;
	
	/**
	 * drag 발생하지 않도록 override
	 */
	@Override
	public boolean isDragAllowed() {
		return false;
	}	
}
