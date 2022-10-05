package com.campin.manager;

public class AuserVo {

	int aUserCode;
	String id;
	String pwd;
	String mName;
	String birth;
	String phone;
	String email;
	String gender;
	String regDate;
	String job;
	
	//String newpwd; //새로운패스워드
	//기존패스워드
	
	public AuserVo() {
		
	}

	//getter&setter
	public int getaUserCode() {
		return aUserCode;
	}

	public void setaUserCode(int aUserCode) {
		this.aUserCode = aUserCode;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
}
