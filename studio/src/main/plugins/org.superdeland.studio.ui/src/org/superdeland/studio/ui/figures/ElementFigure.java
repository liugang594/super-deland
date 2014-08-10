package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;

public class ElementFigure extends Label {
	public ElementFigure() {
//		setOpaque(true);
		setBackgroundColor(ColorConstants.yellow);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.pushState();
		graphics.setAntialias(SWT.ON);
		graphics.fillRoundRectangle(getBounds(), 10, 10);
		super.paintFigure(graphics);
		graphics.popState();
	}

}
