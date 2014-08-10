package org.superdeland.studio.ui.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.commands.DeleteElementCommand;

public class ElementComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteElementCommand command = new DeleteElementCommand();
		command.setElement((ElementModel) getHost().getModel());
		return command;
	}
}
