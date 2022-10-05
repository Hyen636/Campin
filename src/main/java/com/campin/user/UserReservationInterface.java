package com.campin.user;

import java.util.List;

public interface UserReservationInterface {
	public List<UserReservationVo> selectR(int orderCode);
	public boolean insert(UserReservationVo vo);
	public boolean updateRS(int orderCode);
	public UserReservationVo viewDetail(int orderCode);
	public boolean update(UserReservationVo vo);
	public boolean updateR(int orderCode);
	public List<UserReservationVo> selectReview(int userCode);
	public List<UserReservationVo> selectRAnswer(int userCode);
	public boolean modifyR(UserReservationVo vo);
	public boolean deleteR(int orderCode);
	public UserReservationVo selectReservation(int userCode, int itemCode);
	public List<UserReservationVo> selectReservation2(int itemCode, int siteCode);
	public UserReservationVo selectReservation3(int itemCode, int siteCode, int cUserCode);
	public boolean insertOrders(UserReservationVo vo);
	public UserReservationVo nextReservation(String orderCode2);
	public UserReservationVo nextReservation2(int itemCode);
}
