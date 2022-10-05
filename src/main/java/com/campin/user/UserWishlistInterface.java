package com.campin.user;

import java.util.List;

public interface UserWishlistInterface { // dao(service)를 위한 interface
	public List<UserWishlistVo> selectUser(int userCode);
	public void deleteWish(UserWishlistVo vo);
}
