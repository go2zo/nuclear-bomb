package org.eclipse.papyrus.diagram.sequence.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

public class ApexPreserveAnchorsPositionCommand
		extends
		org.eclipse.papyrus.diagram.common.commands.PreserveAnchorsPositionCommand {
	
	private ApexSetBoundsForExecutionSpecificationCommand setBoundsCommand;

	public ApexPreserveAnchorsPositionCommand(ShapeNodeEditPart shapeEP,
			ApexSetBoundsForExecutionSpecificationCommand setBoundsCommand, int preserveAxis) {
		super(shapeEP, null, preserveAxis);
		this.setBoundsCommand = setBoundsCommand;
	}

	@Override
	public Dimension getSizeDelta() {
		if (setBoundsCommand != null)
			return setBoundsCommand.getSizeDelta();
		return super.getSizeDelta();
	}

	
}
