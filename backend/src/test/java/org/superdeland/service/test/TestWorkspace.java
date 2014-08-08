package org.superdeland.service.test;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.utils.ResourceUtils;

public class TestWorkspace {

	public static void main(String[] args) {
		// WorkspaceServiceImpl workspaceServiceImpl = new
		// WorkspaceServiceImpl();
		// String createResource = workspaceServiceImpl.createResource("Hello",
		// WorkspaceModel.getInstance().getId(), false);
		// System.out.println(createResource);
		JAXRSServerFactoryBean serverFactoryBean = ResourceUtils
				.createApplication(new WorkspaceApplication(), false);
		serverFactoryBean.setAddress("http://localhost:8181/workspace");
		Server server = serverFactoryBean.create();
		server.start();
	}
}
