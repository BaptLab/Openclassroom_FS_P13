package com.openclassrooms.Openclassrooms_FS_P13_POC.payloads;

public class MessageResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageResponse(String message) {
		this.message = message;
	}
	
}