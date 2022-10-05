package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.user.UserReservationVo;

@Mapper
@Repository
public interface UserReservationMapper {
	public List<UserReservationVo> selectR(int userCode);
	public void insert(UserReservationVo vo);
	public void updateRS(int orderCode);
	public UserReservationVo viewDetail(int orderCode);
	public int update(UserReservationVo vo);
	public int updateR(int orderCode);
	public List<UserReservationVo> selectReview1(int userCode);
	public List<UserReservationVo> selectRAnswer(int userCode);
	public void modifyR(UserReservationVo vo);
	public void deleteR(int orderCode);
	public UserReservationVo selectReservation1(int userCode, int itemCode);
	public List<UserReservationVo> selectReservation2(int itemCode, int siteCode);
	public UserReservationVo selectReservation3(int itemCode, int siteCode, int cUserCode);
	public void insertOrders(UserReservationVo vo);
	public UserReservationVo nextReservation(String orderCode2);
	public UserReservationVo nextReservation2(int itemCode);
}
