package org.superdeland.studio.ui.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.superdeland.studio.core.models.AbstractModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.figures.ElementFigure;
import org.superdeland.studio.ui.policies.ResizeElementEditPolicy;

public class ElementEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

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
	protected void createEditPolicies() {
//		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ResizeElementEditPolicy());
	}

	
	@Override
	public void activate() {
		super.activate();
		((ElementModel)getModel()).addPropertyChangeListener(this);
	}
	
	@Override
	public void deactivate() {
		((ElementModel)getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}
	
	@Override
	protected void refreshVisuals() {
		IFigure figure = getFigure();
		ElementModel model = (ElementModel) getModel();
		((AbstractGraphicalEditPart)getParent()).setLayoutConstraint(this, figure, new Rectangle(model.getLocation(), model.getSize()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String propertyName = arg0.getPropertyName();
		if(AbstractModel.PROP_LOCATION.equals(propertyName)||AbstractModel.PROP_SIZE.equals(propertyName)){
			refreshVisuals();
		}
	}
}
