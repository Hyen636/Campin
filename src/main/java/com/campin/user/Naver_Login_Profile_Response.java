package com.campin.user;

public class Naver_Login_Profile_Response {
	public String resultcode;
	public String message;
	public Naver_Login_Profile response;
	
	
	public Naver_Login_Profile_Response() {}

	// GETTERS AND SETTERS
	
	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Naver_Login_Profile getResponse() {
		return response;
	}

	public void setResponse(Naver_Login_Profile response) {
		this.response = response;
	}

}
