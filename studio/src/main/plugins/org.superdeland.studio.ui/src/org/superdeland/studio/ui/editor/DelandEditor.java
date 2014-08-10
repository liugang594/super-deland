package org.superdeland.studio.ui.editor;

import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.SWT;
import org.eclipse.ui.actions.ActionFactory;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.Activator;
import org.superdeland.studio.ui.factories.DelandCreationFactory;
import org.superdeland.studio.ui.factories.DelandEditPartFactory;
import org.superdeland.studio.ui.util.SaveHelper;

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

		ToolEntry connectionToolEntry = new ConnectionCreationToolEntry(
				"Connection", "Create Connection", new DelandCreationFactory(
						RelationModel.class),
				Activator.getImageDescriptor(Activator.IMG_CONN),
				Activator.getImageDescriptor(Activator.IMG_CONN));
		connectionToolEntry.setToolProperty(
				AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, true);
		basicGroup.add(connectionToolEntry);

		PaletteDrawer paletteDrawer = new PaletteDrawer("Controls");
		paletteRoot.add(paletteDrawer);
		PaletteEntry entry = new CombinedTemplateCreationEntry("Item",
				"This is item model", new DelandCreationFactory(
						ElementModel.class),
				Activator.getImageDescriptor(Activator.IMG_NODE),
				Activator.getImageDescriptor(Activator.IMG_NODE));
		paletteDrawer.add(entry);
		return paletteRoot;
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(SaveHelper.loadModel((IFile) getEditorInput().getAdapter(IFile.class)));
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer()
				.setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new DelandEditPartFactory());
		getGraphicalViewer().setKeyHandler(new KeyHandler());
		getGraphicalViewer().getKeyHandler().put(KeyStroke.getPressed(SWT.DEL, (int)SWT.DEL, SWT.NONE), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		getGraphicalViewer().getKeyHandler().put(KeyStroke.getPressed((char)('y'-'a'+1), (int)'y', SWT.CTRL), getActionRegistry().getAction(ActionFactory.REDO.getId()));
		getGraphicalViewer().getKeyHandler().put(KeyStroke.getPressed((char)('z'-'a'+1), (int)'z', SWT.CTRL), getActionRegistry().getAction(ActionFactory.UNDO.getId()));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		SaveHelper.saveModel((DiagramModel) getGraphicalViewer().getContents()
				.getModel(), (IFile) getEditorInput().getAdapter(IResource.class));
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
