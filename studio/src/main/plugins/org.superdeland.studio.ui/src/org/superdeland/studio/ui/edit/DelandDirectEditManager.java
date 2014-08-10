package org.superdeland.studio.ui.edit;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Text;
import org.superdeland.studio.core.models.ElementModel;

public class DelandDirectEditManager extends DirectEditManager {

	public DelandDirectEditManager(GraphicalEditPart source) {
		super(source, TextCellEditor.class, new DelandCellEditorLocator(source));
	}

	@Override
	protected void initCellEditor() {
		CellEditor cellEditor = getCellEditor();
		((Text) cellEditor.getControl()).setText(((ElementModel) getEditPart()
				.getModel()).getName());
	}

}
