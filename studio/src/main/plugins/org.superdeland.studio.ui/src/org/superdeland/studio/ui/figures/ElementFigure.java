package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class ElementFigure extends Label implements MouseMotionListener {
	private static final Color backgroundColor = new Color(null, 128, 128, 0);
	private static final Color mouseOveredCOlor = new Color(null, 196, 196, 0);
	private static final Color selectedColor = ColorConstants.yellow;
	
	private boolean isSelected = false;
	private boolean isMouseOvered = false;
	
	public ElementFigure() {
		setBackgroundColor(backgroundColor);
		addMouseMotionListener(this);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.pushState();
		graphics.setAntialias(SWT.ON);
		graphics.fillRoundRectangle(getBounds(), 10, 10);
		super.paintFigure(graphics);
		graphics.popState();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		isMouseOvered = true;
		updateBackground();
	}

	@Override
	public void mouseExited(MouseEvent me) {
		isMouseOvered = false;
		updateBackground();
	}

	@Override
	public void mouseHover(MouseEvent me) {
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}
	
	public void setSelected(boolean isHighlight){
		this.isSelected = isHighlight;
		updateBackground();
	}

	private void updateBackground() {
		if(isMouseOvered){
			setBackgroundColor(mouseOveredCOlor);
		}else if(isSelected){
			setBackgroundColor(selectedColor);
		}else{
			setBackgroundColor(backgroundColor);
		}
		
	}

}
