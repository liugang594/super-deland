package org.superdeland.backend.services.index;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "index")
@XmlAccessorType(XmlAccessType.FIELD)
public class IndexItem {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String path;

	public String getId() {
		return id;
	}

	public IndexType getType() {
		return IndexType.valueOf(type);
	}

	public String getName() {
		return name;
	}
	
	void setId(String id) {
		this.id = id;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setType(IndexType type) {
		this.type = type.name();
	}
	
	void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

}
