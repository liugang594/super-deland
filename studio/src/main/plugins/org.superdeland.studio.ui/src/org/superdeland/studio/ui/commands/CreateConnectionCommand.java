package org.superdeland.studio.ui.commands;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;

public class CreateConnectionCommand extends Command {
	
	private RelationModel relation;
	private ElementModel source;
	private ElementModel target;
	
	public void setSource(ElementModel source) {
		this.source = source;
	}
	
	public void setTarget(ElementModel target) {
		this.target = target;
	}
	
	public void setRelation(RelationModel relation) {
		this.relation = relation;
	}
	
	@Override
	public void execute() {
		relation.attachSource(source);
		relation.attachTarget(target);
	}
	
	@Override
	public void undo() {
		relation.deattachSource(source);
		relation.deattachTarget(target);
	}

}
