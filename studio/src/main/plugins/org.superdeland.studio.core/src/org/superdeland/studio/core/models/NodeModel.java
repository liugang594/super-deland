package org.superdeland.studio.core.models;

import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyChangeEvent;

public class NodeModel extends AbstractModel {

	private Point location = new Point(0, 0);

	private Dimension size = new Dimension(100, 50);

	private String name;

	public void setName(String name) {
		this.name = name;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_NAME,
				null, name);
		firePropertyChangeEvent(event);
	}

	public String getName() {
		return name;
	}

	public void setLocation(Point location) {
		Point old = this.location;
		this.location = location;
		PropertyChangeEvent event = new PropertyChangeEvent(this,
				PROP_LOCATION, old, location);
		firePropertyChangeEvent(event);
	}

	public Point getLocation() {
		return location;
	}

	public void setSize(Dimension size) {
		Dimension old = this.size;
		this.size = size;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_SIZE,
				old, size);
		firePropertyChangeEvent(event);
	}

	public Dimension getSize() {
		return size;
	}

}
