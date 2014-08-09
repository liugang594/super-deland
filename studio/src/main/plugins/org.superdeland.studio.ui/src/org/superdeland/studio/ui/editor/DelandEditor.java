package org.superdeland.studio.ui.editor;

import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.Activator;
import org.superdeland.studio.ui.factories.DelandCreationFactory;
import org.superdeland.studio.ui.factories.DelandEditPartFactory;

public class DelandEditor extends GraphicalEditorWithFlyoutPalette {

	public DelandEditor() {
		setEditDomain(new DelandEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot paletteRoot = new PaletteRoot();
		
		PaletteGroup basicGroup = new PaletteGroup("Basic");
		paletteRoot.add(basicGroup);
		
		basicGroup.add(new SelectionToolEntry());
		
		PaletteDrawer paletteDrawer = new PaletteDrawer("Controls");
		paletteRoot.add(paletteDrawer);
		PaletteEntry entry = new CombinedTemplateCreationEntry("Item", "This is item model",
				new DelandCreationFactory(ElementModel.class),
				Activator.getImageDescriptor(Activator.IMG_NODE),
				Activator.getImageDescriptor(Activator.IMG_NODE));
		paletteDrawer.add(entry);
		return paletteRoot;
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(new DiagramModel());
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new DelandEditPartFactory());
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		((DiagramModel)getGraphicalViewer().getContents().getModel()).doSave();
		getCommandStack().markSaveLocation();
	}
	
	@Override
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		firePropertyChange(PROP_DIRTY);
	}
	
}
