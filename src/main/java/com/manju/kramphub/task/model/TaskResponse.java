package com.manju.kramphub.task.model;

/**
 * Holds custom response like title, author/artist name and type whether book or album.
 * 
 * @author manju
 * @version 1.0
 *
 */
public class TaskResponse {
	
	private String title;
	
	private String personName;
	
	private Type type;
	
	public TaskResponse() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
