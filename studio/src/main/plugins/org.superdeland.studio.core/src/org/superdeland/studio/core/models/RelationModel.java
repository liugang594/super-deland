package org.superdeland.studio.core.models;

public class RelationModel extends AbstractModel{

	private ElementModel source;
	
	private ElementModel target;
	
	public void attachSource(ElementModel source) {
		this.source = source;
		source.addSource(this);
	}
	
	public void attachTarget(ElementModel target) {
		this.target = target;
		target.addTarget(this);
	}
	
	public ElementModel getSource() {
		return source;
	}
	
	public ElementModel getTarget() {
		return target;
	}

	public void deattachSource(ElementModel source) {
		source.removeSource(this);
		this.source = null;
	}
	
	public void deattachTarget(ElementModel target){
		target.removeTarget(this);
		this.target = null;
	}
	
}
