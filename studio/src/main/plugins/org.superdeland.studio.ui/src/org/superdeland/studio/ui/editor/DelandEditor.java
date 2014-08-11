package org.superdeland.studio.ui.editor;

import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.ui.actions.ActionFactory;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.Activator;
import org.superdeland.studio.ui.actions.InsertElementAction;
import org.superdeland.studio.ui.factories.DelandCreationFactory;
import org.superdeland.studio.ui.factories.DelandEditPartFactory;
import org.superdeland.studio.ui.util.SaveHelper;

public class DelandEditor extends GraphicalEditorWithFlyoutPalette implements MouseMoveListener {

	private Point latestMouseLocation = new Point(0, 0);

	public DelandEditor() {
		setEditDomain(new DelandEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot paletteRoot = new PaletteRoot();

		PaletteGroup basicGroup = new PaletteGroup("Basic");
		paletteRoot.add(basicGroup);

		basicGroup.add(new SelectionToolEntry());

		MarqueeToolEntry marqueEntry = new MarqueeToolEntry();
		marqueEntry
				.setToolProperty(
						MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
						MarqueeSelectionTool.BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS);
		marqueEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED,
				true);
		basicGroup.add(marqueEntry);

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
		getGraphicalViewer().setContents(
				SaveHelper.loadModel((IFile) getEditorInput().getAdapter(
						IFile.class)));
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer()
				.setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new DelandEditPartFactory());
		registerShortcuts();
		
		getGraphicalControl().addMouseMoveListener(this);
		
	}

	private void registerShortcuts() {
		getGraphicalViewer().setKeyHandler(new KeyHandler());
		getGraphicalViewer().getKeyHandler().put(
				KeyStroke.getPressed(SWT.DEL, (int) SWT.DEL, SWT.NONE),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		getGraphicalViewer().getKeyHandler().put(
				KeyStroke.getPressed((char) ('y' - 'a' + 1), (int) 'y',
						SWT.CTRL),
				getActionRegistry().getAction(ActionFactory.REDO.getId()));
		getGraphicalViewer().getKeyHandler().put(
				KeyStroke.getPressed((char) ('z' - 'a' + 1), (int) 'z',
						SWT.CTRL),
				getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		getGraphicalViewer().getKeyHandler().put(
				KeyStroke.getPressed((char)0, SWT.INSERT, SWT.NONE),
				getActionRegistry().getAction(InsertElementAction.ID));
		getGraphicalViewer().getKeyHandler()
				.put(KeyStroke.getPressed((char) 1, (int) 'a', SWT.CTRL),
						getActionRegistry().getAction(
								ActionFactory.SELECT_ALL.getId()));
		getGraphicalViewer().getKeyHandler()
				.put(KeyStroke.getPressed((char) 0, SWT.F2, SWT.NONE),
						getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
	}

	@Override
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}

	@Override
	public CommandStack getCommandStack() {
		return super.getCommandStack();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		super.createActions();

		IAction action = new InsertElementAction(this);
		getActionRegistry().registerAction(action);
		getStackActions().add(action.getId());
		
		action = new DirectEditAction(this);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		SaveHelper.saveModel((DiagramModel) getGraphicalViewer().getContents()
				.getModel(),
				(IFile) getEditorInput().getAdapter(IResource.class));
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

	@Override
	public void mouseMove(MouseEvent e) {
		this.latestMouseLocation.x = e.x;
		this.latestMouseLocation.y = e.y;
	}
	
	public Point getLatestMouseLocation() {
		return latestMouseLocation;
	}

}
