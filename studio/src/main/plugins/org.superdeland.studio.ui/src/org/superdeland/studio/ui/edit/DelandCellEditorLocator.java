package org.superdeland.studio.ui.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

public class DelandCellEditorLocator implements CellEditorLocator {

	private GraphicalEditPart source;

	public DelandCellEditorLocator(GraphicalEditPart source) {
		this.source = source;
	}

	@Override
	public void relocate(CellEditor celleditor) {
		IFigure figure = source.getFigure();
		Point location = figure.getBounds().getLocation();
		Text control = (Text) celleditor.getControl();
		control.setLocation(location.x, location.y);
		control.setSize(figure.getBounds().width, figure.getBounds().height);
	}

}
