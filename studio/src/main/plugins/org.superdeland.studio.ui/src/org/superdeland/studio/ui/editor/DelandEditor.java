package org.superdeland.studio.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;

public class DelandEditor extends GraphicalEditorWithFlyoutPalette {

	public DelandEditor(){
		setEditDomain(new DelandEditDomain(this));
	}
	
	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot paletteRoot = new PaletteRoot();
		return paletteRoot;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

}
