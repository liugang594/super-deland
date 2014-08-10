package org.superdeland.studio.ui.drag;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.SelectionRequest;
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
			CreateConnectionRequest connectionRequest = new CreateConnectionRequest();
			connectionRequest.setType(RequestConstants.REQ_CONNECTION_START);
			connectionRequest.setFactory(new DelandCreationFactory(RelationModel.class));
			connectionRequest.setTargetEditPart(getSourceEditPart());
			viewer.getEditDomain().getActiveTool();
		} else {
			super.mouseDrag(me, viewer);
		}
	}

}
