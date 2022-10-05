package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.user.UserQnaVo;

@Mapper
@Repository
public interface UserQnaMapper {
	public List<UserQnaVo> selectQ(int userCode); 
	public List<UserQnaVo> selectA(int userCode); 
	public int update(UserQnaVo vo);
	public int delete(UserQnaVo vo);
}
