package org.superdeland.studio.core.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class AbstractModel {

	public static final String PROP_LOCATION = "p_location";
	public static final String PROP_SIZE = "p_size";
	public static final String PROP_NAME = "p_name";
	public static final String PROP_SOURCE = "p_source";
	public static final String PROP_TARGET = "p_target";
	public static final String PROP_ADD = "p_add";
	public static final String PROP_REMOVE = "p_remove";

	private List<PropertyChangeListener> listeners = new ArrayList<>();

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.remove(listener);
	}

	protected void firePropertyChangeEvent(PropertyChangeEvent event) {
		for (PropertyChangeListener listener : listeners) {
			listener.propertyChange(event);
		}
	}

}
