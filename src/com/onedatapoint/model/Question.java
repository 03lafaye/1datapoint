package com.onedatapoint.model;

import com.onedatapoint.config.Config;

//import com.onedatapoint.config.Config;

public abstract class Question {
	String id;
	String description;
	
	public Question() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getViewName() {
		return this.getClass().getName() + "View";
	}
	
	public void save() {
		Config.getInstance().getQuestionRepository().save(this);
	}
	
}
