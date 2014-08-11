package org.superdeland.studio.ui.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.Location;

public class CreateNodeCommand extends Command {

	private ElementModel element;

	private DiagramModel diagram;

	private Location location;

	public void setElement(ElementModel element) {
		this.element = element;
	}

	public void setDiagram(DiagramModel diagram) {
		this.diagram = diagram;
	}

	public ElementModel getElement() {
		return element;
	}

	public DiagramModel getDiagram() {
		return diagram;
	}

	@Override
	public void execute() {
		element.setLocation(location);
		diagram.addNode(element);
	}

	@Override
	public void redo() {
		super.redo();
	}

	@Override
	public void undo() {
		diagram.removeNode(element);
	}

	public void setLocation(Point location) {
		this.location = new Location(location.x, location.y);
	}

}
