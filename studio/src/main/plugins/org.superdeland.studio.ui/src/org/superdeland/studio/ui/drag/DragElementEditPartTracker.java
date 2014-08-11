package org.superdeland.studio.ui.drag;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.swt.events.MouseEvent;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.factories.DelandCreationFactory;

public class DragElementEditPartTracker extends DragEditPartsTracker {

	private Request request;

	public DragElementEditPartTracker(EditPart sourceEditPart, Request request) {
		super(sourceEditPart);
		this.request = request;
	}

	@Override
	public void mouseDrag(MouseEvent me, EditPartViewer viewer) {
		if (request instanceof SelectionRequest
				&& ((SelectionRequest) request).getLastButtonPressed() == 3) {
			final CreateConnectionRequest connectionRequest = new CreateConnectionRequest();
			connectionRequest.setType(RequestConstants.REQ_CONNECTION_START);
			connectionRequest.setFactory(new DelandCreationFactory(RelationModel.class));
			connectionRequest.setTargetEditPart(getSourceEditPart());
			ConnectionCreationTool connectionCreationTool = new ConnectionCreationTool(){
				@Override
				protected Request getTargetRequest() {
					return connectionRequest;
				}
			};
			connectionCreationTool.setUnloadWhenFinished(true);
			viewer.getEditDomain().setActiveTool(connectionCreationTool);
			me.button = 1;
			connectionCreationTool.mouseDown(me, viewer);
		} else {
			super.mouseDrag(me, viewer);
		}
	}

}
