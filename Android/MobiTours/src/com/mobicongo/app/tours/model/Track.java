package com.mobicongo.app.tours.model;

public class Track  extends MyObject{
	private long millis;
	private String file;
	
	public long getMillis() {
		return millis;
	}
	
	public void setMillis(long millis) {
		this.millis = millis;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
}

