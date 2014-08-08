package org.superdeland.backend.services.index;

import javax.xml.bind.JAXBException;

public class IndexUtil {

	public static IndexItem addIndex(String id, String name, IndexType type,
			String path) throws JAXBException {
		IndexItem indexItem = new IndexItem();
		indexItem.setId(id);
		indexItem.setName(name);
		indexItem.setType(type);
		indexItem.setPath(path);
		IndexModel.getInstance().addItem(indexItem);
		return indexItem;
	}

	public static void removeIndex(String id) throws JAXBException {
		IndexItem[] items = IndexModel.getInstance().getItems();
		for (IndexItem ii : items) {
			if (id.equals(ii.getId())) {
				IndexModel.getInstance().removeItem(ii);
				break;
			}
		}
	}

	public static IndexItem[] getIndexes() {
		return IndexModel.getInstance().getItems();
	}

	public static IndexItem getIndexById(String id) {
		if(id == null || "".equals(id))
			return null;
		IndexItem[] items = IndexModel.getInstance().getItems();
		for (IndexItem ii : items) {
			if (id.equals(ii.getId())) {
				return ii;
			}
		}
		return null;
	}
}
