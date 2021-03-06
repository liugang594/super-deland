package org.superdeland.studio.core.models;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class ElementModel extends AbstractModel {

	private Location location = new Location(0, 0);

	private Size size = new Size(100, 50);
	
	private List<RelationModel> sources = new ArrayList<>();
	
	private List<RelationModel> targets = new ArrayList<>();

	private String name = "<unnamed>";

	private DiagramModel diagramModel;
	
	public void setName(String name) {
		this.name = name;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_NAME,
				null, name);
		firePropertyChangeEvent(event);
	}

	public String getName() {
		return name;
	}

	public void setLocation(Location location) {
		Location old = this.location;
		this.location = location;
		PropertyChangeEvent event = new PropertyChangeEvent(this,
				PROP_LOCATION, old, location);
		firePropertyChangeEvent(event);
	}

	public Location getLocation() {
		return location;
	}

	public void setSize(Size size) {
		Size old = this.size;
		this.size = size;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_SIZE,
				old, size);
		firePropertyChangeEvent(event);
	}

	public Size getSize() {
		return size;
	}
	
	public void addSource(RelationModel source){
		sources.add(source);
		firePropertyChangeEvent(new PropertyChangeEvent(this, PROP_SOURCE, null, source));
	}
	
	public void addTarget(RelationModel target){
		targets.add(target);
		firePropertyChangeEvent(new PropertyChangeEvent(this, PROP_TARGET, null, target));
	}

	public void removeSource(RelationModel source){
		sources.remove(source);
		firePropertyChangeEvent(new PropertyChangeEvent(this, PROP_SOURCE, source, null));
	}
	
	public void removeTarget(RelationModel target){
		targets.remove(target);
		firePropertyChangeEvent(new PropertyChangeEvent(this, PROP_TARGET, target, null));
	}
	
	public List<RelationModel> getSources() {
		return sources;
	}
	
	public List<RelationModel> getTargets() {
		return targets;
	}

	public void setParent(DiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}
	
	public DiagramModel getDiagramModel() {
		return diagramModel;
	}
}
