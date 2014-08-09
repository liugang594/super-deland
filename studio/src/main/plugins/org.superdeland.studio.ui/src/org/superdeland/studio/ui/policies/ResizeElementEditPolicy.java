package org.superdeland.studio.ui.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.commands.ResizeElementCommand;

public class ResizeElementEditPolicy extends ResizableEditPolicy {

	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		ResizeElementCommand command = new ResizeElementCommand();
		command.setModel((ElementModel) getHost().getModel());
		command.setSizeDelta(request.getSizeDelta());
//		return command;
		return super.getResizeCommand(request);
	}
	
}
