package com.campin.user;

import javax.servlet.http.HttpServletRequest;



public interface UserMembershipInterface { // dao(service)를 위한 interface
	public boolean join(UserMembershipVo vo);
	public UserMembershipVo login(UserMembershipVo vo, HttpServletRequest req);
	public void logout(HttpServletRequest req);
	public UserMembershipVo selectOneProfile(String id);
	public boolean profile_modify(UserMembershipVo vo);
	public boolean pwd_modify(UserMembershipVo vo);
	public boolean remove_account(UserMembershipVo vo);
	public String findIdByPhone(UserMembershipVo vo);
	public String findIdByEmail(UserMembershipVo vo);
	public String tempPwd(UserMembershipVo vo);
	public String sendEmail(UserMembershipVo vo, String tempPwd);
	public boolean idValdation(String id);
	public boolean emailValidation(String email);
	public String emailValidation2(String email);
	public boolean phoneValidation(String phone);
	public String phoneValidation2(String phone);
	public boolean pwdValidation(String id, String pwd);
}
