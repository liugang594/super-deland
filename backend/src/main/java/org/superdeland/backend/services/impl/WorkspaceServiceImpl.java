package org.superdeland.backend.services.impl;

import org.superdeland.backend.services.IWorkspaceService;
import org.superdeland.backend.services.index.IndexItem;
import org.superdeland.backend.services.resources.ResourcesUtil;

public class WorkspaceServiceImpl implements IWorkspaceService {

	@Override
	public IndexItem createResource(String name, String parentId, boolean isDirectory) {
		return ResourcesUtil.createResource(name, parentId, isDirectory);
	}

	@Override
	public boolean deleteResource(String id) {
		return ResourcesUtil.deleteResource(id);
	}

	@Override
	public boolean updateContent(String id, String content) {
		return ResourcesUtil.updateContent(id, content);
	}

	@Override
	public IndexItem[] listResources(String parentId) {
		return ResourcesUtil.getResources(parentId);
	}
}
