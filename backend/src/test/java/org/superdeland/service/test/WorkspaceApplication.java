package org.superdeland.service.test;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.superdeland.backend.services.IWorkspaceService;
import org.superdeland.backend.services.impl.WorkspaceServiceImpl;

public class WorkspaceApplication extends Application {

	private static Set<Object> services = new HashSet<>();
	static{
		IWorkspaceService service = new WorkspaceServiceImpl();
		services.add(service);
	}
	
	@Override
	public Set<Object> getSingletons() {
		return services;
	}
}
