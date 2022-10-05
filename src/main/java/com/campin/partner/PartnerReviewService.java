package com.campin.partner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.PartnerReviewMapper;


@Service
@Transactional
public class PartnerReviewService implements PartnerReviewInterface{

	@Autowired
	PartnerReviewMapper mapper;
	
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	
	
	@Override
	public List<ReviewVo> selectReview(ReviewVo vo) {
		List<ReviewVo> list=null;
		try {
			list=mapper.selectReview(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public String selectReviewAns(ReviewVo vo) {
		String doc=null;
		try {
			doc=mapper.selectReviewAns(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}



	@Override
	public int insertAnswer(ReviewVo vo) {
		int cnt=0;
		status = transaction.getTransaction(new DefaultTransactionDefinition());
		try {
			cnt=mapper.insertAnswer(vo);
			if(cnt>0) {
				try {
					cnt=mapper.updateReview(vo);
					if(cnt>0) {
						transaction.commit(status);
					}
				}catch (Exception e) {
					e.printStackTrace();
					transaction.rollback(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}



	@Override
	public int updateAnwser(ReviewVo vo) {
		int cnt=0;
		status = transaction.getTransaction(new DefaultTransactionDefinition());
		try {
			cnt=mapper.updateAnswer(vo);
			if(cnt>0) {
				transaction.commit(status);
			}else {
				transaction.rollback(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}



	@Override
	public int deleteAnswer(ReviewVo vo) {
		int cnt=0;
		try {
			cnt=mapper.deleteAnswer(vo);
			if(cnt>0) {
				try {
					cnt=mapper.updateReview(vo);
					transaction.commit(status);
				}catch (Exception e) {
					e.printStackTrace();
					transaction.commit(status);
				}
				if(cnt>0) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}



	@Override
	public ReviewVo selectOne(int sno) {
		ReviewVo vo= null;
		try {
			vo=mapper.selectOne(sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
}
