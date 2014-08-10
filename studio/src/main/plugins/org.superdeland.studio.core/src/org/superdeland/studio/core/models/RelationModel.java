package org.superdeland.studio.core.models;

public class RelationModel extends AbstractModel{

	private ElementModel source;
	
	private ElementModel target;
	
	public void attachSource() {
		source.addSource(this);
	}
	
	public void attachTarget() {
		target.addTarget(this);
	}
	
	public void setSource(ElementModel source) {
		this.source = source;
	}
	
	public void setTarget(ElementModel target) {
		this.target = target;
	}
	
	public ElementModel getSource() {
		return source;
	}
	
	public ElementModel getTarget() {
		return target;
	}

	public void deattachSource() {
		source.removeSource(this);
	}
	
	public void deattachTarget(){
		target.removeTarget(this);
	}
	
}
