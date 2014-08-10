package org.superdeland.studio.ui.commands;

import org.eclipse.gef.commands.Command;
import org.superdeland.studio.core.models.ElementModel;

public class ElementDirectEditCommand extends Command {

	private ElementModel element;
	private String newName;
	private String oldName;
	
	public void setElement(ElementModel element) {
		this.element = element;
		oldName = element.getName();
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	@Override
	public void execute() {
		element.setName(newName);
	}
	
	@Override
	public void undo() {
		element.setName(oldName);
	}
	
}
