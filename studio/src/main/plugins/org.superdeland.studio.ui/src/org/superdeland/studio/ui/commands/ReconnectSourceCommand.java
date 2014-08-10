package org.superdeland.studio.ui.commands;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;

public class ReconnectSourceCommand extends Command {

	private ElementModel newSource;
	
	private RelationModel relation;

	private ElementModel oldSource;
	
	public void setNewSource(ElementModel newSource) {
		this.newSource = newSource;
	}
	
	public void setRelation(RelationModel relation) {
		this.relation = relation;
		oldSource = relation.getSource();
	}
	
	@Override
	public void execute() {
		relation.deattachSource();
		relation.setSource(newSource);
		relation.attachSource();
	}
	
	public void undo() {
		relation.deattachSource();
		relation.setSource(oldSource);
		relation.attachSource();
	};
	
}
