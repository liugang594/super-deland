package org.superdeland.backend.services.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "indexes")
@XmlAccessorType(XmlAccessType.FIELD)
public class IndexModel {

	@XmlElementRef(name = "index")
	private List<IndexItem> items = new ArrayList<>();

	private static final File FILE = new File("indexes/.indexes");

	private IndexModel() {
	}

	private static class IndexModelHolder {

		public final static IndexModel INSTANCE = initial();

		private static IndexModel initial() {
			try {
				JAXBContext context = JAXBContext.newInstance(IndexModel.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				IndexModel indexModel = (IndexModel) unmarshaller
						.unmarshal(FILE);
				System.out.println("hello");
				return indexModel;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("world");
				return new IndexModel();
			}

		}

	}

	public static IndexModel getInstance() {
		return IndexModelHolder.INSTANCE;
	}

	void addItem(IndexItem item) {
		items.add(item);
		saveIndexes();
	}

	private void saveIndexes() {
		try {
			JAXBContext context = JAXBContext.newInstance(IndexModel.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void removeItem(IndexItem item) {
		items.remove(item);
		saveIndexes();
	}

	public IndexItem[] getItems() {
		return items.toArray(new IndexItem[0]);
	}
}
