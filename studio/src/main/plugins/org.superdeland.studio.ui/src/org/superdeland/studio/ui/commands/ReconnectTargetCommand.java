package org.superdeland.studio.ui.commands;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;

public class ReconnectTargetCommand extends Command {

	private ElementModel newTarget;
	
	private RelationModel relation;

	private ElementModel oldTarget;
	
	public void setNewTarget(ElementModel newTarget) {
		this.newTarget = newTarget;
	}
	
	public void setRelation(RelationModel relation) {
		this.relation = relation;
		oldTarget = relation.getTarget();
	}
	
	@Override
	public void execute() {
		relation.deattachTarget();
		relation.setTarget(newTarget);
		relation.attachTarget();
	}
	
	public void undo() {
		relation.deattachTarget();
		relation.setTarget(oldTarget);
		relation.attachTarget();
	};
	
}
