package org.superdeland.studio.ui.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;

public class ResizeElementCommand extends Command {

	private ElementModel model;
	
	private Dimension sizeDelta;
	private Dimension oldSize;
	private Point oldLocation;

	private Point moveDelta;
	
	public void setModel(ElementModel model) {
		this.model = model;
		this.oldSize = model.getSize();
		this.oldLocation = model.getLocation();
	}
	
	public void setSizeDelta(Dimension sizeDelta) {
		this.sizeDelta = sizeDelta;
	}
	
	@Override
	public void execute() {
		model.setSize(new Dimension(oldSize.width+sizeDelta.width, oldSize.height+sizeDelta.height));
		
		model.setLocation(new Point(oldLocation.x+moveDelta.x, oldLocation.y+moveDelta.y));
	}
	
	@Override
	public void undo() {
		model.setSize(oldSize);
		model.setLocation(oldLocation);
	}

	public void setMoveDelta(Point moveDelta) {
		this.moveDelta = moveDelta;
	}
	
}
