package org.superdeland.studio.core.models;

public class Size {

	private int width;
	private int height;

	public Size(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
