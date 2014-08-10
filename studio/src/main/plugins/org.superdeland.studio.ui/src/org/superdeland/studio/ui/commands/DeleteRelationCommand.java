package org.superdeland.studio.ui.commands;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.RelationModel;

public class DeleteRelationCommand extends Command {

	private RelationModel relationModel;
	
	public void setRelationModel(RelationModel relationModel) {
		this.relationModel = relationModel;
	}
	
	@Override
	public void execute() {
		relationModel.deattachSource();
		relationModel.deattachTarget();
	}
	
	@Override
	public void undo() {
		relationModel.attachSource();
		relationModel.attachTarget();
	}
	
}
