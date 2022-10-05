package com.campin.partner;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PartnerSchedule {
	
	@Autowired
	PartnerOrderService orderDao;
	String nowDate;

	public PartnerSchedule() {
		Date date = new Date();
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		nowDate = dtf.format(date);
	}
	
	// 매일 오후 12시마다 실행, 리뷰가능하게 변경
	@Scheduled(cron = "0 0 12 * * *") 
	public void orderVisit() {
		int cnt = orderDao.orderVisit(nowDate);
		System.out.println("방문한 사람 수 : " + cnt);
	}
	// 매일 오후 2시마다 실행, 전날 무통장입금으로 하고 입금하지 않는다면 취소
	@Scheduled(cron = "0 0 14 * * *") 
	public void orderCancel() {
		int cnt = orderDao.autoCancel(nowDate);
		System.out.println(cnt);
	}
}
