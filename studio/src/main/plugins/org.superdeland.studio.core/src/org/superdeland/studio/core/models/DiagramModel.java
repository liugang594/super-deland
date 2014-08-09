package org.superdeland.studio.core.models;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class DiagramModel extends AbstractModel {

	private List<ElementModel> children = new ArrayList<>();

	public void addNode(ElementModel node) {
		children.add(node);
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_ADD,
				null, node);
		firePropertyChangeEvent(event);
	}

	public void removeNode(ElementModel node) {
		children.remove(node);
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_REMOVE,
				node, null);
		firePropertyChangeEvent(event);
	}
	
	public List<ElementModel> getChildren() {
		return children;
	}

	public void doSave() {
		
	}

}
