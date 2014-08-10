package org.superdeland.studio.ui.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.superdeland.studio.core.models.AbstractModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.edit.DelandDirectEditManager;
import org.superdeland.studio.ui.figures.ElementFigure;
import org.superdeland.studio.ui.policies.ElementComponentEditPolicy;
import org.superdeland.studio.ui.policies.ElementDirectEditPolicy;
import org.superdeland.studio.ui.policies.ElementGraphicalNodeEditPolicy;

public class ElementEditPart extends AbstractGraphicalEditPart implements
		NodeEditPart, PropertyChangeListener {

	@Override
	protected IFigure createFigure() {
		ElementFigure elementFigure = new ElementFigure();
		ElementModel model = (ElementModel) getModel();
		elementFigure.setText(model.getName());
		elementFigure.setLocation(model.getLocation());
		elementFigure.setSize(model.getSize());
		return elementFigure;
	}

	@Override
	public void performRequest(Request req) {
		if(req.getType() == RequestConstants.REQ_DIRECT_EDIT){
			DelandDirectEditManager editManager = new DelandDirectEditManager(this);
			editManager.show();
		}else{
			super.performRequest(req);
		}
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ElementGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new ElementComponentEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ElementDirectEditPolicy());
	}

	@Override
	public void activate() {
		super.activate();
		((ElementModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		((ElementModel) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	@Override
	protected void refreshVisuals() {
		IFigure figure = getFigure();
		ElementModel model = (ElementModel) getModel();

		Animation.markBegin();
		((AbstractGraphicalEditPart) getParent()).setLayoutConstraint(
				ElementEditPart.this, figure, new Rectangle(
						model.getLocation(), model.getSize()));
		Animation.run(200);
	}
	
	protected void refreshName(){
		((Label)getFigure()).setText(((ElementModel)getModel()).getName());
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String propertyName = arg0.getPropertyName();
		if (AbstractModel.PROP_LOCATION.equals(propertyName)
				|| AbstractModel.PROP_SIZE.equals(propertyName)) {
			refreshVisuals();
		} else if (AbstractModel.PROP_SOURCE.equals(propertyName)) {
			refreshSourceConnections();
		} else if (AbstractModel.PROP_TARGET.equals(propertyName)) {
			refreshTargetConnections();
		} else if (AbstractModel.PROP_NAME.equals(propertyName)) {
			refreshName();
		}
	}

	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		if (value == SELECTED || value == SELECTED_PRIMARY) {
			((ElementFigure) getFigure()).setSelected(true);
		} else {
			((ElementFigure) getFigure()).setSelected(false);
		}
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	protected List<RelationModel> getModelSourceConnections() {
		return ((ElementModel) getModel()).getSources();
	}

	@Override
	protected List<RelationModel> getModelTargetConnections() {
		return ((ElementModel) getModel()).getTargets();
	}
}
