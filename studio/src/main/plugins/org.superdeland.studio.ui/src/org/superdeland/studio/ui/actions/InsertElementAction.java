package org.superdeland.studio.ui.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.action.Action;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.editor.DelandEditor;
import org.superdeland.studio.ui.factories.DelandCreationFactory;
import org.superdeland.studio.ui.parts.DiagramEditPart;

public class InsertElementAction extends Action {
	public static final String ID = "org.superdeland.studio.ui.actions.insert";

	private DelandEditor editor;

	public InsertElementAction(DelandEditor delandEditor) {
		this.editor = delandEditor;
		setId(ID);
	}

	@Override
	public boolean isEnabled() {
		return calculateEnabled();
	}

	private boolean calculateEnabled() {
		List<?> selectedEditParts = editor.getGraphicalViewer()
				.getSelectedEditParts();
		if (selectedEditParts.size() != 1)
			return false;
		if (!(selectedEditParts.get(0) instanceof DiagramEditPart)) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		DiagramEditPart diagramEditPart = (DiagramEditPart) editor
				.getGraphicalViewer().getSelectedEditParts().get(0);
		
		CreateRequest createRequest = new CreateRequest();
		createRequest.setLocation(editor.getLatestMouseLocation());
		createRequest.setFactory(new DelandCreationFactory(ElementModel.class));
		Command command = diagramEditPart.getCommand(createRequest);
		if(command != null && command.canExecute()){
			editor.getCommandStack().execute(command);
		}
	}

}
