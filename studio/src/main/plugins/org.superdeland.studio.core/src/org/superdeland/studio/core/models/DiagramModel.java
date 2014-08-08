package org.superdeland.studio.core.models;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class DiagramModel extends AbstractModel {

	private List<NodeModel> children = new ArrayList<>();

	public void addNode(NodeModel node) {
		children.add(node);
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_ADD,
				null, node);
		firePropertyChangeEvent(event);
	}

	public void removeNode(NodeModel node) {
		children.remove(node);
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROP_REMOVE,
				node, null);
		firePropertyChangeEvent(event);
	}

}
