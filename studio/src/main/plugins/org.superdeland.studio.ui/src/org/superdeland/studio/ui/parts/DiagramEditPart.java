package org.superdeland.studio.ui.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.superdeland.studio.core.models.AbstractModel;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.ui.figures.DiagramFigure;
import org.superdeland.studio.ui.policies.DiagramLayoutPolicy;
import org.superdeland.studio.ui.policies.ElementGraphicalNodeEditPolicy;

public class DiagramEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	@Override
	protected IFigure createFigure() {
		return new DiagramFigure();
	}
	
	@Override
	public void activate() {
		super.activate();
		((DiagramModel)getModel()).addPropertyChangeListener(this);
		getFigure().addLayoutListener(LayoutAnimator.getDefault());
	}
	
	@Override
	public void deactivate() {
		((DiagramModel)getModel()).removePropertyChangeListener(this);
		getFigure().removeLayoutListener(LayoutAnimator.getDefault());
		super.deactivate();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ElementGraphicalNodeEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evn) {
		String propertyName = evn.getPropertyName();
		if(AbstractModel.PROP_REMOVE.equals(propertyName) || AbstractModel.PROP_ADD.equals(propertyName)){
			refreshChildren();
		}
	}
	

	@Override
	protected List<ElementModel> getModelChildren() {
		return ((DiagramModel)getModel()).getChildren();
	}
	
}
