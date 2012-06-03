package com.onedatapoint.model;

import java.util.Date;

import com.onedatapoint.config.Config;

public class Response {
	private String id;
	private String questionId;
	private String responseKey;
	private String value;
	private Date timeStamp;
	
	public Response(String id, String questionId, String responseKey, String value, Date timeStamp) {
		this.id = id;
		this.questionId = questionId;
		this.responseKey = responseKey;
		this.value = value;
		this.timeStamp = timeStamp;
	}

	public static Iterable<Response> getAll() {
		return Config.getInstance().getResponseRepository().getResponses();
	}
	
	public void save() {
		Config.getInstance().getResponseRepository().save(this);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getResponseKey() {
		return responseKey;
	}

	public void setResponseKey(String responseKey) {
		this.responseKey = responseKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
