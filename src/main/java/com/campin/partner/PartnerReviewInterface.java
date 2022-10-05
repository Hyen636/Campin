package com.campin.partner;

import java.util.List;

public interface PartnerReviewInterface {
	public List<ReviewVo> selectReview(ReviewVo vo);
	public String selectReviewAns(ReviewVo vo);
	public ReviewVo selectOne(int sno);
	public int insertAnswer(ReviewVo vo);
	public int updateAnwser(ReviewVo vo);
	public int deleteAnswer(ReviewVo vo);

}
