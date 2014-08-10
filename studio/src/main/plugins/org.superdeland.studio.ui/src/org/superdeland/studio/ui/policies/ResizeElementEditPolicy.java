package org.superdeland.studio.ui.policies;

import java.util.List;

import org.eclipse.gef.editpolicies.ResizableEditPolicy;

public class ResizeElementEditPolicy extends ResizableEditPolicy {

	@Override
	protected List<?> createSelectionHandles() {
		return super.createSelectionHandles();
	}
	
	@Override
	protected void createDragHandle(List handles, int direction) {
		super.createDragHandle(handles, direction);
	}
}
