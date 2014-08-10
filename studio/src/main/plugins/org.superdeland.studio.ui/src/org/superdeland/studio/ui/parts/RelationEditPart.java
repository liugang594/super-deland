package org.superdeland.studio.ui.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.superdeland.studio.ui.figures.RelationFigure;
import org.superdeland.studio.ui.policies.RelationComponentEditPolicy;

public class RelationEditPart extends AbstractConnectionEditPart {

	@Override
	protected IFigure createFigure() {
		return new RelationFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RelationComponentEditPolicy());
	}

}
