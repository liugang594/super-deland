package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;

public class RelationFigure extends PolylineConnection implements MouseMotionListener {

	public RelationFigure(){
		setForegroundColor(ColorConstants.blue);
		setTargetDecoration(new PolygonDecoration());
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintFigure(Graphics graphics) {
		graphics.pushState();
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
		graphics.popState();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		setLineWidth(2);
	}

	@Override
	public void mouseExited(MouseEvent me) {
		setLineWidth(1);
	}

	@Override
	public void mouseHover(MouseEvent me) {
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		
	}
	
}
