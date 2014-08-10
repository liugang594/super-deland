package org.superdeland.studio.ui.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;

public class DeleteElementCommand extends Command {

	private ElementModel element;
	private DiagramModel diagramModel;
	private List<RelationModel> sources;
	private List<RelationModel> targets;
	
	public void setElement(ElementModel element) {
		this.element = element;
		diagramModel = element.getDiagramModel();
		sources = new ArrayList<RelationModel>(element.getSources());
		targets = new ArrayList<RelationModel>(element.getTargets());
	}
	
	public void execute() {
		for(RelationModel source: sources){
			source.deattachSource();
			source.deattachTarget();
		}
		for(RelationModel target: targets){
			target.deattachTarget();
			target.deattachSource();
		}
		diagramModel.removeNode(element);
	};
	
	@Override
	public void undo() {
		diagramModel.addNode(element);
		for(RelationModel target: targets){
			target.attachSource();
			target.attachTarget();
		}
		for(RelationModel source: sources){
			source.attachSource();
			source.attachTarget();
		}
	}
	
}
