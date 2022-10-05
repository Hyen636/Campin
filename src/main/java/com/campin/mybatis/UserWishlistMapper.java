package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

import com.campin.user.UserWishlistVo;

@Mapper
@Repository
@Alias("uwMapper")
public interface UserWishlistMapper {// xml을 위한 interface
	public List<UserWishlistVo> selectUser(int userCode);
	public List<UserWishlistVo> selectPrice(int userCode);
	public int deleteWish(UserWishlistVo vo);

}
