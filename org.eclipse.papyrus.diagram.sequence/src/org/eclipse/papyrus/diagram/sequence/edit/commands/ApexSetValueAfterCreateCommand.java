package org.eclipse.papyrus.diagram.sequence.edit.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

public class ApexSetValueAfterCreateCommand extends EditElementCommand {

	/**
	 * The feature whose value should be set.
	 */
	private final EStructuralFeature feature;

	/**
	 * The new value.
	 */
	private Object value;
	
	private ICommand command;
	private ICommand valueCommand;
	private EObject elementToEdit;
	
	public ApexSetValueAfterCreateCommand(SetRequest request, ICommand command) {
		this(request, command, null);
	}

	public ApexSetValueAfterCreateCommand(SetRequest request, ICommand command, ICommand valueCommand) {
		super(request.getLabel(), request.getElementToEdit(), request);
		this.feature = request.getFeature();
		this.value = request.getValue();
		this.command = command;
		this.valueCommand = valueCommand;
	}

	@Override
	protected EObject getElementToEdit() {
		if (elementToEdit == null) {
			if (command != null && command.getCommandResult() != null) {
				Object returnValue = command.getCommandResult().getReturnValue();
				if (returnValue instanceof List && ((List)returnValue).size() > 0) {
					returnValue = ((List)returnValue).get(0);
				}
				if (returnValue instanceof EObject) {
					elementToEdit = (EObject) returnValue;
				}
			}
		}
		return elementToEdit;
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		int size = 1;
		if (command != null) {
			size += 1;
		}
		if (valueCommand != null) {
			size += 1;
		}
		
		List result = new ArrayList(size);
		monitor.beginTask(getLabel(), size);
		
		try {
			if (command != null) {
				IStatus status = command.execute(new SubProgressMonitor(monitor, 1), info);
				result.add(status);
				monitor.worked(1);
			}
			
			if (valueCommand != null) {
				IStatus status = valueCommand.execute(new SubProgressMonitor(monitor, 1), info);
				result.add(status);
				if (valueCommand.getCommandResult() != null) {
					value = valueCommand.getCommandResult().getReturnValue();
				}
				monitor.worked(1);
			}
			
	        EObject elementToEdit = getElementToEdit();
	        setResult(CommandResult.newOKCommandResult(elementToEdit));
	        boolean many = FeatureMapUtil.isMany(elementToEdit,feature);
	        if (many) {
	            Collection collection = ((Collection)elementToEdit.eGet(feature));
	            if (value instanceof List){
	                List values = (List)value;
	                collection.clear();
	                collection.addAll(values);
	            }else {
	                collection.add(value);
	            }
	        } else {
	            getElementToEdit().eSet(feature, value);
	        }
	        monitor.worked(1);
		} finally {
			monitor.done();
		}
		return getCommandResult();
	}

	@Override
	public boolean canExecute() {
		if (command != null && command.canExecute()) {
			return true;
		}
		return super.canExecute();
	}

	@Override
	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return super.doUndo(monitor, info);
	}

	@Override
	protected IStatus doRedo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return super.doRedo(monitor, info);
	}

}
