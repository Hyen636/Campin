package com.campin.partner;

import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.PartnerOrderMapper;

@Service
@Transactional
public class PartnerOrderService {
	
	@Autowired
	PartnerOrderMapper mapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	//예약조회
	public List<PartnerOrderVo> selectCalendar(int itemCode, String dateStr) {
		List<PartnerOrderVo> orderList = null;
		PartnerOrderVo vo = new PartnerOrderVo();
		vo.setItemCode(itemCode);
		vo.setDateStr(dateStr);
		
		orderList = mapper.selectCalendar(vo);

		return orderList;
	}
	
	public List<PartnerOrderVo> selectDate(PartnerOrderPage page){
		List<PartnerOrderVo> dateList = null;
		int totSize = 0;
		
		try {
			totSize = mapper.totSize(page);
			page.setTotSize(totSize);
			page.compute();
			
			dateList = mapper.selectDate(page);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	public PartnerOrderVo selectOrderDetail(int orderCode) {
		PartnerOrderVo vo = null;
		vo = mapper.selectOrderDetail(orderCode);
		return vo;
	}
	
	public boolean orderConfirm(int orderCode) {
		boolean result = false;
		int cnt = 0;
		try {
			cnt = mapper.orderConfirm(orderCode);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			if(cnt > 0) {
				transaction.commit(status);
				result = true;
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public boolean orderCancel(int orderCode) {
		boolean result = false;
		int cnt = 0;
		try {
			cnt = mapper.orderCancel(orderCode);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt > 0) {
				transaction.commit(status);
				result = true;
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public int orderVisit(String nowDate) {	// 오늘 날짜 오후 12시에 예약완료인 사람 방문완료
		int cnt = 0;
		try {
			cnt = mapper.visitCompleted(nowDate);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			if(cnt > 0) {
				transaction.commit(status);
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
	public int autoCancel(String nowDate) {	// 오늘 날짜 오후 12시에 예약완료인 사람 방문완료
		int cnt = 0;
		try {
			cnt = mapper.autoCancel(nowDate);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			if(cnt > 0) {
				transaction.commit(status);
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
   	public void sendEmail(PartnerOrderVo vo, String flag) {
		String charSet = "UTF-8";
		String hostSMTP = "smtp.naver.com";       
		String hostSMTPid = "ksklaura@naver.com";   // 서버 이메일 주소(보내는 사람)
		String hostSMTPpwd = "XEL4UTYUR3HN";       // 서버 이메일 비번
		HtmlEmail email = new HtmlEmail();

		// 보내는 사람 이메일주소, 제목, 내용
		String fromEmail = "ksklaura@naver.com";    // 보내는 사람 이메일 주소(받는 사람 이메일에 표시됨)
		String fromName = "CAMPIN";              // 프로젝트 이름 또는 보내는 사람 이름
		String subject = "", msg = "";
		String mail = vo.getEmail(); // 받는 사람 메일 주소
		
		if(flag == "approve") {
			subject = "CAMPIN - 회원님의 예약이 확정되었습니다.";
			msg += "<h4>"+vo.getmName()+"님의 "+vo.getiName()+"캠핑장 예약이 확정되었습니다..</h4>";
			msg += "<span>마이 페이지에서 회원님의 예약 내역을 확인해주세요.</span><br/>";
			msg += "<span>CAMPIN을 이용해주셔서 감사합니다.</span> <br/>";
			msg += "<span>오늘도 즐거운 CAMPIN 되세요! :)<br/><br/>";
			msg += "<span>- CAMPIN -<br/>";
			msg += "<span style='font-weight: bold'>" + "</span>";	
		} else {
			subject = "CAMPIN - 회원님의 예약이 거절되었습니다.";
			msg += "<h4>"+vo.getmName()+"님의 "+vo.getiName()+"캠핑장 예약이 취소되었습니다..</h4>";
			msg += "<span>마이 페이지에서 회원님의 예약 내역을 확인해주세요.</span><br/>";
			msg += "<span>CAMPIN을 이용해주셔서 감사합니다.</span> <br/>";
			msg += "<span>오늘도 즐거운 CAMPIN 되세요! :)<br/><br/>";
			msg += "<span>- CAMPIN -<br/>";
			msg += "<span style='font-weight: bold'>" + "</span>";	
		}
	
		try {
			email.setDebug(true);
			email.setCharset(charSet); // 한글 세팅
            email.setHostName(hostSMTP);
            email.setSmtpPort(587);
            
            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send(); // 메일 발송!
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("이메일 발송 중 오류 발생함.");
        }
   	}
   	
   	// 매출 조회
   	public List<SalesVo> selectSales(SalesVo vo){
   		List<SalesVo> list = null;
   		list = mapper.selectSales(vo);
   		return list;
   	}
  	public List<SalesVo> selectMonthSales(SalesVo vo){
   		List<SalesVo> list = null;
   		list = mapper.selectMonthSales(vo);
   		return list;
   	}
}
