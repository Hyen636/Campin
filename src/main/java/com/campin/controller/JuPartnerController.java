package com.campin.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campin.partner.BoardService;
import com.campin.partner.BoardVo;
import com.campin.partner.PageBoard;
import com.campin.partner.PageReq;
import com.campin.partner.PartnerReviewService;
import com.campin.partner.RequestService;
import com.campin.partner.RequestVo;
import com.campin.partner.ReviewVo;

@RestController
public class JuPartnerController {
	
	@Autowired
	RequestService dao;
	
	@Autowired
	BoardService Boarddao;
	
	@Autowired
	PartnerReviewService Redao;
	
	//테스트를 위한 세션값
	@Autowired
	HttpSession session;
	
	@RequestMapping("partner_qna_list")
	public ModelAndView partnerQnaList(PageBoard page) {
		LocalDate now = LocalDate.now();
		LocalDate strmd=now.withDayOfMonth(1);
		LocalDate endmd=now.withDayOfMonth(strmd.lengthOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String str = strmd.format(formatter);
		String end= endmd.format(formatter);
		
		String cuc=String.valueOf(session.getAttribute("cUserCode"));
		int cUserCode=Integer.parseInt(cuc);
		int itemCode=0;
		try {
			itemCode=Boarddao.selectcmapCode(cUserCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(page.getNowPage()==0) {
			page.setNowPage(1);
		}
		ModelAndView mv = new ModelAndView();
		if(page.getFindStr()==null){
			page=new PageBoard();
			page.setNowPage(page.getNowPage());
			page.setFindStr("");
		}
		if(page.getState()==null) {
		   page.setState("답변대기");
		}else {
		   page.setState(page.getState());	   
		}
		if(page.getStr()==null||page.getStr()==""&&page.getEnd()==null||page.getEnd()=="") {
			page.setStr(str);
			page.setEnd(end);
		}
		page.setItemCode(itemCode);
		
		
		
		List<BoardVo>list=Boarddao.select(page); 
		
	  	mv.addObject("list", list);
		mv.addObject("page", page);
	  	
		mv.addObject("inc", "./partner_qna_list.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_qna_input")
	public ModelAndView partnerQnaInput(BoardVo vo) {
		String question=null;
		String answer=null;
		String qTitle=null;
		int sno=0;
		int anssno=Boarddao.selectAnswer(vo);
		qTitle=Boarddao.selectqTitle(vo.getGrp());
		vo.setQtitle(qTitle);
		vo.setTitle(Boarddao.selectqTitle(anssno));
		
		String cuc=String.valueOf(session.getAttribute("cUserCode"));
		int cUserCode=Integer.parseInt(cuc);
		int itemCode=0;
		try {
			itemCode=Boarddao.selectcmapCode(cUserCode);
			vo.setItemCode(itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView();
		if(vo.getState().equals("답변완료")) {
			vo.setSno(anssno);
			
			question=Boarddao.selectDoc(vo.getGrp());
			answer=Boarddao.selectDoc(anssno);
		}else {
			question=Boarddao.selectDoc(vo.getGrp());
			answer="답변을 입력해 주세요";
		}
		
		mv.addObject("que", question);
		mv.addObject("ans", answer);
		mv.addObject("vo", vo);
		mv.addObject("inc", "./partner_qna_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_qna_inputR")
	public ModelAndView partnerQnaInputR(BoardVo vo) {
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String wday= now.format(formatter);
		
		ModelAndView mv = new ModelAndView();
		
		if(vo.getState().equals("답변대기")) {
			try {
				vo.setGrp(vo.getSno());
				vo.setState("답변완료");
				int cnt=Boarddao.insert(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(vo.getState().equals("답변완료")) {
			boolean b=Boarddao.update(vo);
		}
		mv.addObject("inc", "./partner_qna_list.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	
	@RequestMapping("partner_qna_deleteR")
	public ModelAndView partnerQnaDeleteR(BoardVo vo) {
		
		ModelAndView mv = new ModelAndView();
		
		try {
			boolean b=Boarddao.delete(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("inc", "./partner_qna_list.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}

	
	
	@RequestMapping("partner_request_list")
	public ModelAndView partnerRequestList(PageReq page) {
		LocalDate now = LocalDate.now();
		LocalDate strmd=now.withDayOfMonth(1);
		LocalDate endmd=now.withDayOfMonth(strmd.lengthOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String str = strmd.format(formatter);
		String end= endmd.format(formatter);
		
		
		String itemCode=String.valueOf(session.getAttribute("itemCode"));
		
		
		//System.out.println("session"+ itemCode);
		ModelAndView mv = new ModelAndView();
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
		
		if(page.getItemCode()==null)page.setItemCode(itemCode);
		
		
		List<RequestVo>	list=dao.select(page); 
	  
	  	mv.addObject("list", list);
		mv.addObject("page", page);
	  	
		 
	  	mv.addObject("inc", "./partner_request_list.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_request_input")
	public ModelAndView partnerRequestInput() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String now = today.format(formatter);
		String iName=null;
		String mName=null;
		Integer cUserCode=(Integer)session.getAttribute("cUserCode");
		Integer itemCode=(Integer)session.getAttribute("itemCode");
		
		ModelAndView mv = new ModelAndView();
		iName=dao.selectiName(itemCode);
		mName=dao.selectmName(cUserCode);
		if(iName.equals(null) || iName.equals("")) {
			mv.addObject("name", mName+"(미등록)");
		}else {
			mv.addObject("name", iName);
		}

		mv.addObject("cUserCode", cUserCode);
		mv.addObject("itemCode", itemCode);
		mv.addObject("now", now);
		mv.addObject("inc", "./partner_request_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}

	 @RequestMapping("partner_request_inputR")
	   public int partnerRequestInputR(RequestVo vo) {
		  int cnt=0;
			  
		  if(vo.getCategory().equals("일반문의")) {
			  vo.setState("답변대기");
			  cnt=dao.insertReq(vo);
			  cnt=0;
		      return cnt;
		  }else {
			  cnt=dao.selectOrders(vo.getItemCode());
			  if(cnt<=0) {
				  vo.setState("탈퇴요청");
				  cnt=dao.insertReq(vo);
				  cnt=0;
			      return cnt;
			  }else {
			      return cnt;
			  }
			  
		  }
		  
	   }
	 
	 @RequestMapping("partner_request_view")
	   public ModelAndView request_view(RequestVo vo,String Category) {
		   	String btn=null;
		   	vo.setCategory(Category);
			ModelAndView mv = new ModelAndView();
			Integer sno=vo.getSno();
			vo=dao.selectView(sno);
			if(vo.getCategory().equals("리뷰")) {
				int reviewSno=vo.getReviewSno();
				System.out.println("partner_request_view getReviewSno : "+vo.getReviewSno());
				ReviewVo Revo=dao.selectOne(reviewSno);
				mv.addObject("Revo", Revo);
				mv.addObject("vo", vo);
				mv.addObject("inc", "./partner_review_requset_view.jsp");
				mv.setViewName("./admin/partner/partner_main");
				return mv;
			}
				
			
			mv.addObject("vo", vo);
			mv.addObject("inc", "./partner_request_view.jsp");
			mv.setViewName("./admin/partner/partner_main");
			return mv;
	   }
	 
	 @RequestMapping("partner_request_modify")
	   public ModelAndView request_modify(RequestVo vo) {
		 	String temp=vo.getState();
		 	vo.setState(temp.substring(0, 2));
		 	LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String now = today.format(formatter);
			vo.setNal(now);
		  	ModelAndView mv = new ModelAndView();
			int cnt=0;
			Integer sno=vo.getSno();
			String category=vo.getCategory();
			if(category.equals("삭제")) {
				cnt=dao.deleteReq(vo);
			}else {
				cnt=dao.updateDoc(vo);
			}
			 
			mv.addObject("vo", vo);
			mv.addObject("inc", "./partner_request_view.jsp");
			mv.setViewName("./admin/partner/partner_main");
			return mv;
	   }
	 
		
	 @RequestMapping("partner_review_list")
     public ModelAndView partnerReviewList(ReviewVo vo) {
        String sort=null;
        Integer itemCode=(Integer)session.getAttribute("itemCode");
        vo.setItemCode(itemCode);
        String temp=vo.getState();
        if(temp!="미답변"&& temp!="답변완료") {
           vo.setState(null);
        }
        List<ReviewVo> list=null;
        System.out.println("=========rev list==============");
        System.out.println("partnerReviewList getItemCode : "+vo.getItemCode());
        System.out.println("partnerReviewList getSeq : "+vo.getSeq());
        System.out.println("partnerReviewList getNal : "+vo.getNal());
        System.out.println("partnerReviewList getSort : "+vo.getSort());
        System.out.println("partnerReviewList getState : "+vo.getState());
        System.out.println("partnerReviewList getType : "+vo.getType());
        System.out.println("=========rev list==============");
        ModelAndView mv = new ModelAndView();
        if(vo.getSort()==null) {
           vo.setSort("desc");
           System.out.println("partnerReviewList getSort if : "+vo.getSort());
        }
        if(vo.getType()==null) {
           vo.setType("state");
           System.out.println("partnerReviewList state if : "+vo.getState());
        }
        if(vo.getSort().equals("desc")) {
           sort="↓ 내림차순으로 검색";
           System.out.println("partnerReviewList 내림차순 : "+vo.getSort());
        }else {
           sort="↑ 오름차순으로 검색";
           System.out.println("partnerReviewList 오름차순 : "+vo.getSort());
        }

        System.out.println("partnerReviewList getItemCode : "+vo.getItemCode());
        System.out.println("partnerReviewList getSeq : "+vo.getSeq());
        System.out.println("partnerReviewList getNal : "+vo.getNal());
        System.out.println("partnerReviewList getSort : "+vo.getSort());
        list=Redao.selectReview(vo);

        
        mv.addObject("sort", sort);
        mv.addObject("vo", vo);
        mv.addObject("list", list);
        mv.addObject("inc", "./partner_review_list.jsp");
        mv.setViewName("./admin/partner/partner_main");
        return mv;
     }
	

		@RequestMapping("partner_review_list_answer")
		public String partnerReviewListAnswer(ReviewVo vo) {
			String doc=null;
			doc=Redao.selectReviewAns(vo);
			return doc;
		}
		
		@RequestMapping("partner_review_list_answerR")
		public int partnerReviewListAnswerR(ReviewVo vo) {
			System.out.println(vo.getUserCode());
			int cnt=0;
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String now = today.format(formatter);
			vo.setNal(now);
			if(vo.getState().equals("미답변")) {
				vo.setState("답변완료");
				cnt=Redao.insertAnswer(vo);
			}else {
				cnt=Redao.updateAnwser(vo);
			}
			return cnt;
		}
		
		@RequestMapping("partner_review_list_delete")
		public int partnerReviewListdelete(ReviewVo vo) {
			int cnt=0;
			cnt=Redao.deleteAnswer(vo);
			
			return cnt;
		}
		
		
		@RequestMapping("partner_review_request")
		public ModelAndView partnerReviewRequest(ReviewVo vo) {
			int sno=vo.getSno();
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String now = today.format(formatter);
			
			ModelAndView mv = new ModelAndView();
			vo=Redao.selectOne(sno);
			int itemCode=vo.getItemCode();
			String iName=dao.selectiName(vo.getItemCode());
		
			mv.addObject("iName", iName);
			mv.addObject("now", now);
			mv.addObject("vo", vo);
			mv.addObject("inc", "./partner_review_requset.jsp");
			mv.setViewName("./admin/partner/partner_main");
			return mv;
		}
		
		@RequestMapping("partner_review_request_inputR")
		   public ModelAndView partnerReviewRequestInputR(RequestVo vo) {
			  int cnt=0;
		
			  ModelAndView mv = new ModelAndView();
			  cnt=dao.insertReq(vo);
			  cnt=0;
			  mv.addObject("inc", "./partner_review_list.jsp");
			  mv.setViewName("./admin/partner/partner_main");
			  return mv;
			 
			  
		   }
}
