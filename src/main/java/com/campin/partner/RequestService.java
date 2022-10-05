package com.campin.partner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.ReqMapperC;



@Service
@Transactional
public class RequestService implements RequestInterface{

	@Autowired
	ReqMapperC mapperC;
	PageReq page;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	@Override
	public List<RequestVo> select(PageReq page) {
		List<RequestVo> list=null;
		
		try {
			int totSize=mapperC.totSize(page);
			page.setTotSize(totSize);
			page.compute();

			list=mapperC.select(page);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.page=page;
		return list;
	}

	@Override
	public String selectiName(Integer itemCode) {
		String iName=null;
		try {
			iName=mapperC.selectiName(itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iName;
	}

	
	@Override
	public int insertReq(RequestVo vo) {
		int cnt=0;
		int sno=0;
		try {
			cnt=mapperC.insertReq(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				if(cnt>0) {
					transaction.commit(status);
					sno=mapperC.selectSearchSno(vo);
					cnt=mapperC.updategrp(sno);
					if(vo.getCategory().equals("일반문의")) {
					}else if(vo.getCategory().equals("탈퇴")){
						vo.setState("탈퇴요청");
						cnt=mapperC.updatecUserState(vo);
						cnt=mapperC.updateitemState(vo);
					}else if(vo.getCategory().equals("리뷰")){
						sno=vo.getReviewSno();
						int orderCode=mapperC.selectReviewC(sno);
						vo.setOrderCode(orderCode);
						cnt=mapperC.updatereview(vo);
						cnt=mapperC.updatereviewans(vo);
					}
				}else {
					transaction.rollback(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public String selectmName(Integer cUserCode) {
		String mName=null;
		try {
			mName=mapperC.selectmName(cUserCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mName;
	}
	
	@Override
	public RequestVo selectView(Integer sno) {
		RequestVo vo=null;
		try {
			vo=mapperC.selectView(sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int updateDoc(RequestVo vo) {
		int cnt=0;
		try {
			cnt=mapperC.updateDoc(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
		} catch (Exception e) {
			transaction.rollback(status);
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteReq(RequestVo vo) {
		int sno=vo.getSno();
		int cUserCode=vo.getcUserCode();
		int itemCode=vo.getItemCode();
		
		
		int cnt=0;
		status = transaction.getTransaction(new DefaultTransactionDefinition());
		try {
			cnt=mapperC.deleteReq(sno);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(vo.getState().equals("답변")) {
			}else if(vo.getState().equals("탈퇴")){
				vo.setState("수정완료");
				cnt=mapperC.updatecUserState(vo);
				cnt=mapperC.updateitemState(vo);
			}else if(vo.getState().equals("블랙")){
				int reviewSno=vo.getReviewSno();
				int orderCode=mapperC.selectReviewC(reviewSno);
				Integer no=mapperC.selectAns(orderCode);
				if(no>1) {
					vo.setState("답변완료");
					vo.setOrderCode(orderCode);
					cnt=mapperC.updatereviewans(vo);
				}else {
					vo.setState("미답변");
					vo.setOrderCode(orderCode);
					cnt=mapperC.updatereviewans(vo);
				}
			}
			
		} catch (Exception e) {
			transaction.rollback(status);
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int selectOrders(Integer itemCode) {
		int cnt=0;
		try {
			cnt=mapperC.selectOrders(itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public ReviewVo selectOne(Integer reviewSno) {
		ReviewVo vo=null;
		try {
			vo=mapperC.selectOne(reviewSno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

}
