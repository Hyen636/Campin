package com.campin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campin.mybatis.UserQnaMapper;

@Service
public class UserQnaService implements UserQnaInterface{
	
	@Autowired
	UserQnaMapper mapper;
	

	@Override
	public List<UserQnaVo> selectQ(int userCode) {
		List<UserQnaVo> list = null;
		System.out.println("userCode = " + userCode);
		try {
			list = mapper.selectQ(userCode);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<UserQnaVo> selectA(int userCode) {
		List<UserQnaVo> list = null;
		System.out.println("userCode = " + userCode);
		try {
			list = mapper.selectA(userCode);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public UserQnaVo selectOne(int sno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserQnaVo vo) {
		System.out.println(vo.sno);
		System.out.println(vo.doc);
		boolean b = true;
		mapper.update(vo);
		return b;
	}

	@Override
	public boolean delete(UserQnaVo vo) {
		boolean b = true;
		mapper.delete(vo);
		return b;
	}

}
