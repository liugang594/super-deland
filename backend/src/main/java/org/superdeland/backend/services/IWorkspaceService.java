package org.superdeland.backend.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.superdeland.backend.services.index.IndexItem;

@Path("/")
public interface IWorkspaceService {

	@DELETE
	@Path("delete/{id}")
	boolean deleteResource(@PathParam("id") String id);

	@PUT
	@Path("update/{id}")
	boolean updateContent(@PathParam("id")String id, String content);

	@Path("create")
	@PUT
	IndexItem createResource(@FormParam("name") String name,
			@FormParam("parentId") String parentId,
			@FormParam("directory") boolean isDirectory);

	@GET
	@Path("list")
	IndexItem[] listResources(@QueryParam("parentId") String parentId);

}
