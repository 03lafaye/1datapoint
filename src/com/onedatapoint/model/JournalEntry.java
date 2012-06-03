package com.onedatapoint.model;

import java.util.Date;

public class JournalEntry {
	private String id;
	private Date timeStamp;
	
	public JournalEntry(String id, Date timeStamp) {
		this.setId(id);
		this.setTimeStamp(timeStamp);
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
