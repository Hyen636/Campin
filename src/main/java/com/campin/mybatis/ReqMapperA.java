package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.manager.PageReq;
import com.campin.manager.RequestVo;
import com.campin.manager.ReviewVoA;




@Mapper
@Repository
public interface ReqMapperA {
	public int insert(RequestVo vo);
	public RequestVo selectView(Integer sno);
	public ReviewVoA selectOne(int reviewSno);
	public int selectReviewA(int reviewSno);
	public int selectAns(int orderCode);
	public int updateAnswer(RequestVo vo);
	public int updateReq(RequestVo vo);
	public int updatecUserState(RequestVo vo);
	public int updateitemState(RequestVo vo);
	public int updatereview(RequestVo vo);
	public int updatereviewans(RequestVo vo);
	public int totSize(PageReq page);
	public List<RequestVo> select(PageReq page);
	//public int getSno();
	//public RequestVo view(int sno);
	
	//public void hitUp(int sno);
	//public int  update(RequestVo vo);
	//public int  seqUp(RequestVo vo);
	//public int delete(int sno);
}
