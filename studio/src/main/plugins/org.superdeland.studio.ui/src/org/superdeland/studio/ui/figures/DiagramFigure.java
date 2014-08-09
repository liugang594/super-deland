package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;

public class DiagramFigure extends ScalableFreeformLayeredPane {
	
	public DiagramFigure(){
		setOpaque(true);
		setLayoutManager(new FreeformLayout());
		setBackgroundColor(ColorConstants.lightBlue);
	}

}
