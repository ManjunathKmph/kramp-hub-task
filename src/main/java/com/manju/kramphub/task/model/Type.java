package com.manju.kramphub.task.model;

/**
 * Class represents whether the object is book or album type.
 * 
 * @author manju
 * @version 1.0
 */
public enum Type {
	
	BOOK("book"), ALBUM("title");
	
	private Type(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
}
