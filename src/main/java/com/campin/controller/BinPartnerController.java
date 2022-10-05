package com.campin.controller;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.campin.partner.CuserVo;
import com.campin.partner.HistoryCuserVo;
import com.campin.partner.JoinAtt;
import com.campin.partner.PageReq;
import com.campin.partner.PartnerOrderService;
import com.campin.partner.PartnerjoinService;
import com.campin.partner.RequestService;
import com.campin.partner.RequestVo;

@RestController
public class BinPartnerController {

   @Autowired
   PartnerjoinService pjservice;
   
   @Autowired
   CjoinFileUploadController cf;
   
   @Autowired
   RequestService dao;
   @Autowired 
   PartnerOrderService orderDao;
   
   @Autowired
	HttpSession session;

   /*user와 분리하여 admin mapping 설정*/
   @RequestMapping("admin") 
   public ModelAndView admin_login() {
      ModelAndView mv = new ModelAndView();
      mv.setViewName("./admin/admin_login");
      return mv;
   }
   
   @RequestMapping("partner_loginR")
   public ModelAndView partner_loginR(CuserVo Cvo, HttpServletRequest req, String orderCode, PageReq page) {
	   
	   ModelAndView mv = new ModelAndView();
	   CuserVo rVo = pjservice.loginR(Cvo, req);
	   
       if( rVo == null) {
    	   mv.addObject("msg", "[CAMPIN] 캠지기의 아이디와 비밀번호를 확인해주세요.");
    	   mv.setViewName("./admin/admin_login");
       }else if(rVo.getState().equals(null) ) {
    	   mv.addObject("inc", "./partner_sales.jsp");
   		   mv.setViewName("./admin/partner/partner_main");
       }else if( rVo.getState().equals("탈퇴승인") ) {
    	   mv.addObject("msg", "[CAMPIN] 탈퇴한 캠지기회원입니다. 확인후 다시 로그인해주세요.");
    	   mv.setViewName("./admin/admin_login"); 
       }else if ( rVo.getState().equals("등록요청") || rVo.getState().equals("수정요청") ){
    	   mv = PageRequest(req,page);
       }else {
    	   mv.addObject("inc", "./partner_sales.jsp");
   		   mv.setViewName("./admin/partner/partner_main");
       }
      return mv;
   }
   
   @RequestMapping("findCid")
   public ModelAndView findCid() {
      ModelAndView mv = new ModelAndView();
      mv.addObject("inc","./partner/partner_find_id.jsp");
      mv.setViewName("./admin/admin_temp");
      return mv;
   }
   //휴대폰번호로 아이디찾기
   @RequestMapping("findpCidR")
   public ModelAndView findpCidR(CuserVo vo) {
		ModelAndView mv = new ModelAndView();
		String id = pjservice.findpCidR(vo);
		
		mv.addObject("msg", id);
		mv.addObject("inc","partner/partner_find_id_result.jsp");
		 mv.setViewName("./admin/admin_temp");
		return mv;
   }
   //이메일로 아이디 찾기
   @RequestMapping("findeCidR")
   public ModelAndView findeCidR(CuserVo vo) {
		ModelAndView mv = new ModelAndView();
		String id = pjservice.findeCidR(vo);
		
		mv.addObject("msg", id);
		mv.addObject("inc","partner/partner_find_id_result.jsp");
		 mv.setViewName("./admin/admin_temp");
		return mv;
   }
   
   @RequestMapping("findCpwd") //패스워드 찾기 해야함!
   public ModelAndView findCpwd() {
      ModelAndView mv = new ModelAndView();

      mv.addObject("inc","./partner/partner_find_pwd.jsp");
      mv.setViewName("./admin/admin_temp");
      return mv;
   }
   
   //메인 - 파트너 회원가입으로 이동
   @RequestMapping("partner_join")
   public ModelAndView partner_join() {
      ModelAndView mv = new ModelAndView();
      mv.addObject("inc","./partner/partner_join.jsp");
      mv.setViewName("./admin/admin_temp");
      return mv;
   }
   
   // 파트너 회원가입 제출 
   @RequestMapping("partner_joinR")
   public int partner_joinR(CuserVo vo) {
      int cUserCode = 0;
      if(vo.getBirth() == "") {
    	  vo.setBirth(null);
      }
      
      pjservice.insertR(vo);
      cUserCode = pjservice.getcUserCode(vo);
      return cUserCode;
   }
   
   // 파일업로드
   @RequestMapping("joinFileUpload")                   //false해야 파일업로드 안해도 회원가입가능
   public int joinFileUpload(@RequestParam(value="busiImg", required=false) List<MultipartFile> busiImg, 
                          @RequestParam(value="travelImg",  required=false) List<MultipartFile> travelImg, 
                          HttpServletRequest req) {
	  int cUserCode = 0;
	  int hisSno = 0;

      try {
	      cUserCode = Integer.parseInt(req.getParameter("cUserCode"));
	      
	      if(busiImg.get(0).getSize() !=0) {
		      JoinAtt busiatt = cf.uploadCuser(busiImg, "busiImg", cUserCode);
		      pjservice.insertbusiAtt(busiatt);
	      }
	      if(travelImg.get(0).getSize() != 0) {	// 0이면 파일이 들어있지 않음
		      JoinAtt travelatt = cf.uploadCuser(travelImg, "travelImg", cUserCode);
		      pjservice.inserttravelAtt(travelatt);
	      }
	      
	      hisSno = pjservice.getHisSno(cUserCode);
      }catch(Exception ex) {
    	  ex.printStackTrace();
      }
      return hisSno;
      
   }
   
   // 파트너 - 관리자문의에서 join 게시물 클릭할 경우
   @RequestMapping("partner_request_join_view")
   public ModelAndView partner_request_join_view(HttpServletRequest req, PageReq page) {
	   ModelAndView mv = new ModelAndView();
	   HistoryCuserVo vo = null;
	   int hisUserSno = Integer.parseInt(req.getParameter("hisUserSno"));
	   
	   vo = pjservice.selectCuserHisview(hisUserSno); //attList
	   
	   mv.addObject("vo", vo);
	   mv.addObject("page", page);
	   mv.addObject("inc","partner_join_view.jsp");
	   mv.setViewName("./admin/partner/partner_main"); 
	   
	   return mv;
   }
   
   // 파트너 - 관리자문의에서 modify 게시물 클릭할 경우
   @RequestMapping("partner_request_modify_view")
   public ModelAndView partner_request_modify_view(HttpServletRequest req, PageReq page) {
	   ModelAndView mv = new ModelAndView();
	   HistoryCuserVo vo = null;
	   int hisUserSno = Integer.parseInt(req.getParameter("hisUserSno"));
	   
	   vo = pjservice.selectCuserHisview(hisUserSno);
	   
	   mv.addObject("vo", vo);
	   mv.addObject("page", page);
	   mv.addObject("inc","partner_account_modify_view.jsp");
	   mv.setViewName("./admin/partner/partner_main"); 
	   
	   return mv;
   }

   // 파트너 정보 수정 요청 페이지로 이동
   @RequestMapping("partner_account_modify")
   public ModelAndView partner_account_modify(CuserVo vo) {
      ModelAndView mv = new ModelAndView();
      CuserVo rVo = pjservice.selectCOne(vo.getId());
      
      mv.addObject("vo", rVo);
      mv.addObject("inc","partner_account_modify.jsp");
      mv.setViewName("./admin/partner/partner_main");
      return mv;
   }
   
   // 파트너 정보 수정 요청 - temp, req에도 요청
   @RequestMapping("partner_account_modiReq")
   public ModelAndView partner_account_modiReq(CuserVo vo, HttpServletRequest req, PageReq page) {
	  ModelAndView mv = new ModelAndView();
      int cUserCode = Integer.parseInt(req.getParameter("cUserCode"));
      
      if(vo.getBirth() == "") {
    	  vo.setBirth(null);  
      }
      
      vo.setCuserCode(cUserCode);
      pjservice.partner_account_modiReq(vo,req);
      
      mv = PageRequest(req,page);
	  return mv;
   }
   
   // 파트너 정보 수정 요청 - 수정
   @RequestMapping("partner_account_hisModi")
   public ModelAndView partner_account_hisModi(HistoryCuserVo vo, HttpServletRequest req, PageReq page) {
      ModelAndView mv = new ModelAndView();
      
      if(vo.getBirth() == "") {
    	  vo.setBirth(null);  
      }
      
      pjservice.partner_account_hisModi(vo,req);
      
      mv = PageRequest(req,page);
	  return mv;
   }
   
   // 파트너 정보 수정 요청 - 삭제
   @RequestMapping("partner_account_modiDel")
   public ModelAndView partner_account_modiDel(HistoryCuserVo vo, HttpServletRequest req, PageReq page) {
	  ModelAndView mv = new ModelAndView();
	  
      if(vo.getBirth() == "") {
    	  vo.setBirth(null);  
      }
      
      pjservice.partner_account_modiDel(vo,req);
      
      mv = PageRequest(req,page);
	  return mv;
   }
    
   // 파트너 가입 요청건 수정
   @RequestMapping("partner_join_reqModi")
   public void partner_join_reqModi(HistoryCuserVo vo, HttpServletRequest req, PageReq page) {
	  
      if(vo.getBirth() == "") {
    	  vo.setBirth(null);
      }
      
      pjservice.partner_join_reqModi(vo, req);
   }
   
   // 파트너 가입 요청건 삭제
   @RequestMapping("partner_join_reqDel") 
   public void partner_join_reqDel(HistoryCuserVo vo, HttpServletRequest req) {
	   
   if(vo.getBirth() == "") { 
	   vo.setBirth(null); 
	   }
   
   pjservice.partner_join_reqDel(vo, req);	
   
   }

   
   // request list 갱신
   public ModelAndView PageRequest(HttpServletRequest req, PageReq page) {
	      ModelAndView mv = new ModelAndView();
	      
	      LocalDate now = LocalDate.now();
		  LocalDate strmd=now.withDayOfMonth(1);
		  LocalDate endmd=now.withDayOfMonth(strmd.lengthOfMonth());
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  String str = strmd.format(formatter);
		  String end= endmd.format(formatter);
			
		  String itemCode=String.valueOf(session.getAttribute("itemCode"));
			
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
 
   @RequestMapping("partner_pwd_modify")
   public ModelAndView partner_pwd_modify(CuserVo vo) {
      ModelAndView mv = new ModelAndView();
      CuserVo rVo = pjservice.selectCOne(vo.getId());
      mv.addObject("vo", rVo);
      mv.addObject("inc","partner_pwd_modify.jsp");
      mv.setViewName("./admin/partner/partner_main");
      return mv;
   }
   
   // 적용 안되고 있음 / 다시 체크
   @RequestMapping("partner_pwd_modifyR")
   public ModelAndView partner_pwd_modifyR(CuserVo vo, HttpServletRequest req) {
	      ModelAndView mv = new ModelAndView();
	      
	      vo.setId(req.getParameter("id"));
	      vo.setPwd(req.getParameter("cpwd_new_check"));
	      
	      pjservice.updateCPwd(vo);
		
	      mv.addObject("inc","partner_reservation_list.jsp");
	      mv.setViewName("./admin/partner/partner_main");
	      return mv;
   }
   
   @RequestMapping("cPwdValidation")
   public void cPwdValidation(HttpServletRequest req, HttpServletResponse resp) {
	   String id = req.getParameter("id");
	   String pwd = req.getParameter("cpwd_pre");

	   boolean cPwdValidation = pjservice.cPwdValidation(id, pwd);
   	   resp.setContentType("text/plain;charset=euc-kr"); //문자화
	   PrintWriter out = null;
		try {
			out = resp.getWriter();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(cPwdValidation) {
		    out.print("not matched");
		} else {
		    out.print("matched"); //일치시 진행가능
			}
   		}
   
	//중복체크
   @RequestMapping("cIdValidation")
   public void cIdValidation(HttpServletRequest req, HttpServletResponse resp){
      String id = req.getParameter("id");      
      boolean cIdValidation = pjservice.cIdValdation(id);
      
      resp.setContentType("text/plain;charset=euc-kr");
      PrintWriter out = null;
      try {
         out = resp.getWriter();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   
      if(cIdValidation) {
         out.print("validation"); //중복시
      } else {
         out.print("ok");
      }
   }
   
	@RequestMapping("cPhoneValidation")
	public void cPhoneValidation(HttpServletRequest req, HttpServletResponse resp){
	   String phone = req.getParameter("phone");
	   boolean cPhoneValidation = pjservice.cPhoneValidation(phone);
	   resp.setContentType("text/plain;charset=euc-kr");
	   PrintWriter out = null;
	   try {
	      out = resp.getWriter();
	   } catch (Exception ex) {
	      ex.printStackTrace();
	   }
	
	   if(cPhoneValidation) {
	      out.print("validation"); //중복시
	   } else {
	      out.print("ok");
	   }
	
	}
	
	@RequestMapping("cEmailValidation")
	public void cEmailValidation(HttpServletRequest req, HttpServletResponse resp){
	   String email = req.getParameter("email");      
	   boolean cEmailValidation = pjservice.cEmailValidation(email);
	   
	   resp.setContentType("text/plain;charset=euc-kr");
	   PrintWriter out = null;
	   try {
	      out = resp.getWriter();
	   } catch (Exception ex) {
	      ex.printStackTrace();
	   }
	
	   if(cEmailValidation) {
	      out.print("validation"); //중복시
	   } else {
	      out.print("ok");
	   }
	
	}
	
	@RequestMapping("cBusiNumValidation")
	public void cBusiNumValidation(HttpServletRequest req, HttpServletResponse resp){
	   String busiNum = req.getParameter("busiNum");      
	   boolean cBusiNumValidation = pjservice.cBusiNumValidation(busiNum);
	   resp.setContentType("text/plain;charset=euc-kr");
	   PrintWriter out = null;
	   try {
	      out = resp.getWriter();
	   } catch (Exception ex) {
	      ex.printStackTrace();
	   }
	
	   if(cBusiNumValidation) {
	      out.print("validation"); //중복시
	   } else {
	      out.print("ok");
	   }
	
	}

}