package org.superdeland.studio.ui.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.commands.DeleteRelationCommand;

public class RelationComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteRelationCommand command = new DeleteRelationCommand();
		command.setRelationModel((RelationModel) getHost().getModel());
		return command;
	}
}
