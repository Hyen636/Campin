package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.partner.PageReq;
import com.campin.partner.RequestVo;
import com.campin.partner.ReviewVo;


@Mapper
@Repository
public interface ReqMapperC {
	public int insert(RequestVo vo);
	public int insertReq(RequestVo vo);
	public int selectSearchSno(RequestVo vo);
	public int selectReviewC(Integer sno);
	public ReviewVo selectOne(Integer review);
	public Integer selectAns(Integer orderCode);
	public int updategrp(int sno);
	public int updateReq(int sno);
	public int updatecUserState(RequestVo vo);
	public int updateitemState(RequestVo vo);
	public int updateDoc(RequestVo vo);
	public int deleteReq(int sno);
	public String selectiName(int itemCode);
	public String selectmName(int cUserCode);
	public RequestVo selectView(int sno);
	public int selectOrders(Integer itemCode);
	public int updateState(RequestVo vo);
	public int updatereview(RequestVo vo);
	public int updatereviewans(RequestVo vo);
	public int totSize(PageReq page);
	public List<RequestVo> select(PageReq page);
}
