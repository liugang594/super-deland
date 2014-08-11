package org.superdeland.studio.ui.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.Location;
import org.superdeland.studio.core.models.Size;

public class ResizeElementCommand extends Command {

	private ElementModel model;
	
	private Dimension sizeDelta;
	private Size oldSize;
	private Location oldLocation;

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
		model.setSize(new Size(oldSize.getWidth()+sizeDelta.width, oldSize.getHeight()+sizeDelta.height));
		model.setLocation(new Location(oldLocation.getX()+moveDelta.x, oldLocation.getY()+moveDelta.y));
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
