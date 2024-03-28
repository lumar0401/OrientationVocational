package com.co.orientationVocational.app.business;

public class FileMessages {
	private String message;
	
	public FileMessages() {}

	public FileMessages(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
