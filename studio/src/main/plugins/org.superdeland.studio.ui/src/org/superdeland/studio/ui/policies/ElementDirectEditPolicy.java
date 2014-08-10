package org.superdeland.studio.ui.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.commands.ElementDirectEditCommand;

public class ElementDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		ElementDirectEditCommand command = new ElementDirectEditCommand();
		command.setNewName(request.getCellEditor().getValue().toString());
		command.setElement((ElementModel) getHost().getModel());
		return command;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {

	}

}
