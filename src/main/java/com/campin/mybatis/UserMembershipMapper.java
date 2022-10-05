package com.campin.mybatis;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

import com.campin.user.Kakao_Login_Profile;
import com.campin.user.Naver_Login_Profile_Response;
import com.campin.user.UserMembershipVo;

@Mapper
@Repository
@Alias("umMapper")
public interface UserMembershipMapper{ // xml을 위한 interface
	public String findIdByPhone(UserMembershipVo vo);
	public String findIdByEmail(UserMembershipVo vo);
	public String findPwd(UserMembershipVo vo);
	public void updateTempPwd(UserMembershipVo vo);
	public UserMembershipVo login(UserMembershipVo vo);
	public UserMembershipVo selectOneProfile(String id);
	public int findUserById(Kakao_Login_Profile kakaoProfile);
	public int join(UserMembershipVo vo);
	public int join_kakao1(UserMembershipVo vo);
	public int join_kakao2(UserMembershipVo vo);
	public UserMembershipVo login_kakao1(UserMembershipVo vo);
	public UserMembershipVo login_kakao2(String id);
	public int profile_modify(UserMembershipVo vo);
	public int pwd_modify(UserMembershipVo vo);
	public String idValidation(String id);
	public String emailValidation(String email);
	public String phoneValidation(String phone);
	public String pwdValidation(String id);
	public int remove_account(UserMembershipVo vo);
	public int remove_account_kakao(UserMembershipVo vo);
	public String emailCheck(UserMembershipVo vo);
	public String findOutIfSocial(UserMembershipVo vo);
	public int findUserById_naver(String id);
	public int join_naver(UserMembershipVo vo);
}
