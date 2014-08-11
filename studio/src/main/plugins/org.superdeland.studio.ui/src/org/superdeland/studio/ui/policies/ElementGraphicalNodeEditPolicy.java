package org.superdeland.studio.ui.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.commands.CreateConnectionCommand;
import org.superdeland.studio.ui.commands.CreateNodeCommand;
import org.superdeland.studio.ui.commands.ReconnectSourceCommand;
import org.superdeland.studio.ui.commands.ReconnectTargetCommand;
import org.superdeland.studio.ui.factories.DelandCreationFactory;
import org.superdeland.studio.ui.parts.DiagramEditPart;
import org.superdeland.studio.ui.parts.ElementEditPart;

public class ElementGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
		EditPart targetEditPart = request.getTargetEditPart();
		if(targetEditPart instanceof ElementEditPart){
			command.setTarget((ElementModel) request.getTargetEditPart().getModel());
			return command;
		}else if(targetEditPart instanceof DiagramEditPart){
			ElementModel newObject = (ElementModel) new DelandCreationFactory(ElementModel.class).getNewObject();
			command.setTarget(newObject);
			
			CreateNodeCommand newNodeCommand = new CreateNodeCommand();
			newNodeCommand.setDiagram((DiagramModel) targetEditPart.getModel());
			newNodeCommand.setElement(newObject);
			newNodeCommand.setLocation(request.getLocation());
			
			
			CompoundCommand compoundCommand = new CompoundCommand();
			compoundCommand.add(newNodeCommand);
			compoundCommand.add(command);
			return compoundCommand;
		}
		return null;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if(!(request.getTargetEditPart() instanceof ElementEditPart)){
			return null;
		}
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
