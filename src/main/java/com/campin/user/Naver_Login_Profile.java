package com.campin.user;


public class Naver_Login_Profile {
	public String id;
	public String name;
	public String email;
	public String gender;
	public String birthday;
	public String birthyear;
	public String mobile;
	public String mobile_e164; // mobile for abroad
	
	public Naver_Login_Profile() {}
	
	// GETTERS AND SETTERS
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getBirthyear() {
		return birthyear;
	}
	
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile_e164() {
		return mobile_e164;
	}

	public void setMobile_e164(String mobile_e164) {
		this.mobile_e164 = mobile_e164;
	}
}
