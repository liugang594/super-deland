package org.superdeland.studio.ui.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.commands.CreateConnectionCommand;
import org.superdeland.studio.ui.commands.ReconnectSourceCommand;
import org.superdeland.studio.ui.commands.ReconnectTargetCommand;

public class ElementGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
		command.setTarget((ElementModel) request.getTargetEditPart().getModel());
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateConnectionCommand command = new CreateConnectionCommand();
		command.setSource((ElementModel) request.getTargetEditPart().getModel());
		command.setRelation((RelationModel) request.getNewObject());
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		ReconnectTargetCommand command = new ReconnectTargetCommand();
		command.setNewTarget((ElementModel) request.getTarget().getModel());
		command.setRelation((RelationModel) request.getConnectionEditPart().getModel());
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand command = new ReconnectSourceCommand();
		command.setNewSource((ElementModel) request.getTarget().getModel());
		command.setRelation((RelationModel) request.getConnectionEditPart().getModel());
		return command;
	}

}
