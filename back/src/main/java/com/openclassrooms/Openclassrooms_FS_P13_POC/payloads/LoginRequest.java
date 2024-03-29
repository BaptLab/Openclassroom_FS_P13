package com.openclassrooms.Openclassrooms_FS_P13_POC.payloads;


public class LoginRequest {

	private String emailOrUsername;
	
	private String password;

	public String getEmailOrUsername() {
		return emailOrUsername;
	}

	public void setEmailOrUsername(String email) {
		this.emailOrUsername = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}