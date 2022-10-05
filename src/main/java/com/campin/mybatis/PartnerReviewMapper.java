package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import com.campin.partner.ReviewVo;




@Mapper
@Repository
public interface PartnerReviewMapper {
	public List<ReviewVo> selectReview(ReviewVo vo);
	public String selectReviewAns(ReviewVo vo);
	public ReviewVo selectOne(int sno);
	public int insertAnswer(ReviewVo vo);
	public int updateReview(ReviewVo vo);
	public int updateAnswer(ReviewVo vo);
	public int deleteAnswer(ReviewVo vo);
	//public int getSno();
	//public RequestVo view(int sno);
	
	//public void hitUp(int sno);
	//public int  update(RequestVo vo);
	//public int  seqUp(RequestVo vo);
	//public int delete(int sno);
}
