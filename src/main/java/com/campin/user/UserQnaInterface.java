package com.campin.user;

import java.util.List;

public interface UserQnaInterface {
	public List<UserQnaVo> selectQ(int userCode);
	public List<UserQnaVo> selectA(int userCode);
	public UserQnaVo selectOne(int sno);
	public boolean update(UserQnaVo vo);
	public boolean delete(UserQnaVo vo);
}
