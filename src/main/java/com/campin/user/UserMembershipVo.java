package com.campin.user;

public class UserMembershipVo {
	// user table
	int userCode;
	String id;
	String pwd;
	String mName;
	String birth;
	String phone;
	String email;
	String gender;
	String regDate;
	String socialJoin;
	
	public UserMembershipVo() {}
	public UserMembershipVo(int userCode, String id, String pwd, String mName, String birth, String phone, String email, String gender, String regDate, String socialJoin) {
		this.userCode = userCode;
		this.id = id;
		this.pwd = pwd;
		this.mName = mName;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.regDate = regDate;
		this.socialJoin = socialJoin;
	}
	
	// GETTERS & SETTERS
	
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getSocialJoin() {
		return socialJoin;
	}
	public void setSocialJoin(String socialJoin) {
		this.socialJoin = socialJoin;
	}
	
	
}
