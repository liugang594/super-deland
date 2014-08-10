package org.superdeland.studio.ui.figures;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.swt.graphics.Color;

public class DiagramFigure extends ScalableFreeformLayeredPane {
	
	private static final Color BG = new Color(null, 196, 228, 255);

	public DiagramFigure(){
		setOpaque(true);
		setLayoutManager(new FreeformLayout());
		setBackgroundColor(BG);
	}

}
