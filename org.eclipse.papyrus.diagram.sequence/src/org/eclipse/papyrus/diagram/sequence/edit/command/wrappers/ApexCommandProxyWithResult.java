package org.eclipse.papyrus.diagram.sequence.edit.command.wrappers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;

public class ApexCommandProxyWithResult extends CommandProxy {

	public ApexCommandProxyWithResult(Command command, Object result) {
		super(command);
		setResult(CommandResult.newOKCommandResult(result));
	}

	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		super.doExecuteWithResult(progressMonitor, info);
		if (getCommand() instanceof ICommandProxy) {
			ICommand iCmd = ((ICommandProxy)getCommand()).getICommand();
			setResult(iCmd.getCommandResult());
		}
		return getCommandResult();
	}

	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		super.doRedoWithResult(progressMonitor, info);
		if (getCommand() instanceof ICommandProxy) {
			ICommand iCmd = ((ICommandProxy)getCommand()).getICommand();
			setResult(iCmd.getCommandResult());
		}
		return getCommandResult();
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		super.doUndoWithResult(progressMonitor, info);
		if (getCommand() instanceof ICommandProxy) {
			ICommand iCmd = ((ICommandProxy)getCommand()).getICommand();
			setResult(iCmd.getCommandResult());
		}
		return getCommandResult();
	}

}
