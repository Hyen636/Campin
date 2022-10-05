package com.campin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.ReqMapperA;




@Service
@Transactional
public class RequestServiceA implements RequestInterface{

	@Autowired
	ReqMapperA mapperA;
	PageReq page;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	@Override
	public List<RequestVo> select(PageReq page) {

		List<RequestVo> list=null;
		
		try {
			int totSize=mapperA.totSize(page);
			page.setTotSize(totSize);
			page.compute();
		
			list=mapperA.select(page);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.page=page;
		return list;
	}

	@Override
	public RequestVo selectView(Integer sno) {
		RequestVo vo=null;
		try {
			vo=mapperA.selectView(sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	   public int updateAnswer(RequestVo vo) {
	      System.out.println("dao updateAnswer sno : "+vo);
	      System.out.println("dao updateAnswer sno : "+vo.getState());
	      int cnt=0;
	      status = transaction.getTransaction(new DefaultTransactionDefinition());
	      try {
	         if(vo.getState().equals("답변완료")) {
	            cnt=mapperA.updateAnswer(vo);
	         }else {
	            System.out.println("dao updateAnswer sno : "+cnt);
	            if(vo.getState().equals("탈퇴반려")) {
	               System.out.println("dao updateAnswer 탈퇴반려 sno : "+vo.getSno());
	               System.out.println("dao updateAnswer 탈퇴반려 getState : "+vo.getState());
	               cnt=mapperA.updateAnswer(vo);
	               System.out.println("dao updateAnswer sno : "+vo.getState());
	               vo.setState("수정완료");
	               System.out.println("dao updateAnswer sno : "+vo.getState());
	            }   
	            cnt=mapperA.updatecUserState(vo);
	            System.out.println("dao updateAnswer updatecUserState : "+cnt);
	            cnt=mapperA.updateitemState(vo);
	            System.out.println("dao updateAnswer updatecUserState : "+cnt);
	            System.out.println("dao updateAnswer sno : "+vo.getState());
	         }
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
	public ReviewVoA selectOne(int reviewSno) {
		ReviewVoA vo=null;
		try {
			vo=mapperA.selectOne(reviewSno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	   public int updatereview(RequestVo vo) {
	      int cnt=0;
	      cnt=mapperA.updateAnswer(vo);
	      if(vo.getState()=="블랙반려") {
	         int reviewSno=vo.getReviewSno();
	         int orderCode=mapperA.selectReviewA(reviewSno);
	         System.out.println("dao RequestVo cUserCode orderCode : "+orderCode);
	         Integer no=mapperA.selectAns(orderCode);
	         System.out.println("dao RequestVo selectAns no : "+no);
	         if(no>1) {
	            System.out.println("dao RequestVo selectAns no>1: "+no);
	            vo.setState("답변완료");
	            vo.setOrderCode(orderCode);
	            cnt=mapperA.updatereviewans(vo);
	            System.out.println("dao RequestVo selectAns cnt>1: "+cnt);
	         }else {
	            System.out.println("dao RequestVo selectAns else no>1: "+no);
	            vo.setState("미답변");
	            vo.setOrderCode(orderCode);
	            cnt=mapperA.updatereviewans(vo);
	         }
	      }else {
	         System.out.println("dao RequestVo 리뷰 : ");
	         int sno=vo.getReviewSno();
	         int orderCode=mapperA.selectReviewA(sno);
	         vo.setOrderCode(orderCode);
	         System.out.println("dao RequestVo 리뷰 getReviewSno : "+vo.getReviewSno());
	         System.out.println("dao RequestVo 리뷰 getOrderCode : "+vo.getOrderCode());
	         System.out.println("dao RequestVo 리뷰 getState : "+vo.getState());
	         cnt=mapperA.updatereview(vo);
	         System.out.println("dao RequestVo 리뷰 ucnt : "+cnt);
	         cnt=mapperA.updatereviewans(vo);
	         System.out.println("dao RequestVo itemCode ucnt : "+cnt);
	         
	      }
	      return cnt;
	   }
	
	
	
	

}
