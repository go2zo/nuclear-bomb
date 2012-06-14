package org.eclipse.papyrus.diagram.sequence.edit.policies;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;

public class ApexResizableShapeEditPolicy extends ResizableShapeEditPolicy {

	private int moveDirection;
	
	public ApexResizableShapeEditPolicy(int moveDirection) {
		super();
		setMoveDirection(moveDirection);
	}

	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		Point moveDelta = request.getMoveDelta();
		if ((moveDirection & PositionConstants.EAST) == 0 && moveDelta.x < 0)
			moveDelta.x = 0;
		if ((moveDirection & PositionConstants.WEST) == 0 && moveDelta.x > 0)
			moveDelta.x = 0;
		if ((moveDirection & PositionConstants.SOUTH) == 0 && moveDelta.y < 0)
			moveDelta.y = 0;
		if ((moveDirection & PositionConstants.NORTH) == 0 && moveDelta.y > 0)
			moveDelta.y = 0;
		request.setMoveDelta(moveDelta);
		super.showChangeBoundsFeedback(request);
	}

	public int getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}
	
}
