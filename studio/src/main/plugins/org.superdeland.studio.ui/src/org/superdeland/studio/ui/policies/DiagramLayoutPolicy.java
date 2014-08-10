package org.superdeland.studio.ui.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.commands.CreateNodeCommand;
import org.superdeland.studio.ui.commands.ResizeElementCommand;
import org.superdeland.studio.ui.parts.ElementEditPart;

public class DiagramLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		CreateNodeCommand command = new CreateNodeCommand();
		command.setDiagram((DiagramModel) getHost().getModel());
		command.setElement((ElementModel) request.getNewObject());
		command.setLocation(request.getLocation());
		return command;
	}

	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand compoundCommand = new CompoundCommand();
		List<?> editParts = request.getEditParts();
		for (Object o : editParts) {
			if(!(o instanceof ElementEditPart)){
				continue;
			}
			ResizeElementCommand command = new ResizeElementCommand();
			command.setModel((ElementModel) ((ElementEditPart)o).getModel());
			command.setSizeDelta(request.getSizeDelta());
			command.setMoveDelta(request.getMoveDelta());
			compoundCommand.add(command);
		}
		return compoundCommand;
	}
	
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new ResizeElementEditPolicy();
	}
}
