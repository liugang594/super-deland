package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;

public class RelationFigure extends PolylineConnection {

	public RelationFigure(){
		setForegroundColor(ColorConstants.blue);
		setTargetDecoration(new PolygonDecoration());
	}
	
	@Override
	public void paintFigure(Graphics graphics) {
		graphics.pushState();
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
		graphics.popState();
	}
	
}
