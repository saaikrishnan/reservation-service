package org.saai.reservation.api.services.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Login {
	private String emailId;
	private String password;

	public Login() {

	}

	public Login(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}
	
	

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [emailId=" + emailId + "]";
	}

}
