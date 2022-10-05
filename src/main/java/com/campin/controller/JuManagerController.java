package com.campin.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campin.manager.PageReq;
import com.campin.manager.RequestServiceA;
import com.campin.manager.RequestVo;
import com.campin.manager.ReviewVoA;

@RestController
public class JuManagerController {

	@Autowired
	RequestServiceA dao;
	
   @RequestMapping("manager_request_list")
   public ModelAndView managerRequestList(PageReq page) {
	   ModelAndView mv = new ModelAndView();
	   //월시작, 월말 일자 구하기
	   LocalDate now = LocalDate.now();
	   LocalDate strmd=now.withDayOfMonth(1);
	   LocalDate endmd=now.withDayOfMonth(strmd.lengthOfMonth());
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   String str = strmd.format(formatter);
	   String end= endmd.format(formatter);
	   
	   if(page.getFindStr()==null){
		   page=new PageReq();
		   page.setNowPage(page.getNowPage());
		   page.setFindStr("");
	   }
	   if(page.getState()==null) {
		   page.setState("요청대기");
	   }else {
		   page.setState(page.getState());
	   }
	   if(page.getStr()==null&&page.getEnd()==null) {
			page.setStr(str);
			page.setEnd(end);
		}
	   List<RequestVo> list = dao.select(page); 
	 
	   mv.addObject("list", list);
	   mv.addObject("page", page);
	  	
      mv.addObject("inc", "./manager_request_list.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   @RequestMapping("item_request")
   public ModelAndView item_request(PageReq page) {
	  ModelAndView mv = new ModelAndView();
	  
	  mv.addObject("page", page);
	  
      mv.addObject("inc", "./item_request.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   @RequestMapping("manager_general_request_view")
   public ModelAndView request_view(RequestVo vo,String Category) {
	   	String btn=null;
	   	vo.setCategory(Category);
	   		ModelAndView mv = new ModelAndView();
		Integer sno=vo.getSno();
		vo=dao.selectView(sno);
		String temp=vo.getCategory();
		if(temp.equals("일반문의")) {
			btn="저장";
		}else {
			btn="승인";
		}
		 
		mv.addObject("btn",btn);
		mv.addObject("vo", vo);
		mv.addObject("inc", "./manager_request_view.jsp");
		mv.setViewName("./admin/manager/manager_main");
		return mv;
   }
   @RequestMapping("manager_general_request_answer")
   public ModelAndView request_answer(RequestVo vo ) {
         System.out.println("request_answer");
         System.out.println("=================@request_answer================");
      System.out.println("request_answer : "+vo);
      System.out.println("request_answer getCategory : "+vo.getCategory());
      System.out.println("request_answer getAnswer : "+vo.getAnswer());
      System.out.println("request_answer getNal : "+vo.getNal());
      System.out.println("request_answer getcUserCode : "+vo.getcUserCode());
      System.out.println("request_answer getItemCode : "+vo.getItemCode());
      System.out.println("request_answer getmName : "+vo.getmName());
      System.out.println("request_answer getTitle : "+vo.getTitle());
      System.out.println("request_answer getState : "+vo.getState());
      System.out.println("request_answer getSno : "+vo.getSno());
      
      ModelAndView mv = new ModelAndView();
      if(vo.getCategory().equals("일반문의")) {
         vo.setState("답변완료");
         System.out.println("request_answer getState : "+vo.getState());
         int sno=vo.getSno();
         int cnt=dao.updateAnswer(vo);
      }else {
         if(vo.getState().equals("탈퇴반려")) {
            System.out.println("request_answer getState 탈퇴반려 : "+vo.getState());
            int sno=vo.getSno();
            int cnt=dao.updateAnswer(vo);
         }else {
            vo.setState("탈퇴승인");
            System.out.println("request_answer getState 탈퇴승인 : "+vo.getState());
            int sno=vo.getSno();
            int cnt=dao.updateAnswer(vo);
         }
         
      }
      
      mv.addObject("vo", vo);
      mv.addObject("inc", "./manager_request_view.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   @RequestMapping("manager_request_review_answer")
   public ModelAndView request_review_answer(RequestVo vo ) {
	 
		ModelAndView mv = new ModelAndView();
			int sno=vo.getSno();
			int cnt=0;
			if(vo.getState().equals("블랙승인")) {
				sno=vo.getSno();
				cnt=dao.updatereview(vo);
			}else {
				vo.setState("블랙반려");
				sno=vo.getSno();
				cnt=dao.updatereview(vo);
			}
			
		
		mv.addObject("vo", vo);
		mv.addObject("inc", "./manager_request_view.jsp");
		mv.setViewName("./admin/manager/manager_main");
		return mv;
   }
   
   
   @RequestMapping("manager_review_request_view")
   public ModelAndView review_request_view(RequestVo vo,String Category) {
	   	vo.setCategory(Category);
	   	ModelAndView mv = new ModelAndView();
		Integer sno=vo.getSno();
		vo=dao.selectView(sno);
		int reviewSno=vo.getReviewSno();
		ReviewVoA Revo=dao.selectOne(reviewSno);
	
		mv.addObject("Revo", Revo);
		mv.addObject("vo", vo);
		mv.addObject("inc", "./manager_review_requset_view.jsp");
		mv.setViewName("./admin/manager/manager_main");
		return mv;
		
   }
   
   
}
