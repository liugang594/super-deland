package org.superdeland.studio.ui.factories;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.superdeland.studio.core.models.DiagramModel;
import org.superdeland.studio.core.models.ElementModel;
import org.superdeland.studio.core.models.RelationModel;
import org.superdeland.studio.ui.parts.DiagramEditPart;
import org.superdeland.studio.ui.parts.ElementEditPart;
import org.superdeland.studio.ui.parts.RelationEditPart;

public class DelandEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if(model instanceof DiagramModel){
			part = new DiagramEditPart();
		}else if(model instanceof ElementModel){
			part = new ElementEditPart();
		}else if(model instanceof RelationModel){
			part = new RelationEditPart();
		}
		if(part != null){
			part.setModel(model);
		}
		return part;
	}

}
