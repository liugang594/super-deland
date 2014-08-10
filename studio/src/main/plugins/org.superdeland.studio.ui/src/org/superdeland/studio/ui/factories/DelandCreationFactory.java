package org.superdeland.studio.ui.factories;

import java.util.UUID;

import org.eclipse.gef.requests.SimpleFactory;
import org.superdeland.studio.core.models.AbstractModel;

public class DelandCreationFactory extends SimpleFactory {

	public DelandCreationFactory(Class<?> aClass) {
		super(aClass);
	}
	
	@Override
	public Object getNewObject() {
		Object newObject = super.getNewObject();
		((AbstractModel)newObject).setId(UUID.randomUUID().toString());
		return newObject;
	}

}
