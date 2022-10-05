package com.campin.controller;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campin.manager.AuserVo;
import com.campin.manager.ManagerAdlistService;
import com.campin.manager.ManagerjoinService;
import com.campin.manager.PageAccountList;
import com.campin.manager.PageCampAccountList;
import com.campin.manager.PageManagerAccountList;
import com.campin.manager.PageReq;
import com.campin.manager.RequestServiceA;
import com.campin.manager.RequestVo;
import com.campin.partner.HistoryCuserVo;
import com.campin.partner.ItemVo;
import com.campin.partner.PageBoard;
import com.campin.user.UserVo;


@RestController
public class BinManagerController {

   @Autowired 
   ManagerjoinService mjservice;
   
   @Autowired
   ManagerAdlistService mlservice;
   
   @Autowired
   RequestServiceA dao;
      
   // 관리자 로그인
   @RequestMapping("mloginR")
   public ModelAndView mloginR(AuserVo vo, HttpServletRequest req) {

	  ModelAndView mv = new ModelAndView();
      AuserVo rVo = mjservice.loginR(vo, req);
      
      if( rVo == null) {
    	  mv.addObject("msg", "[CAMPIN] 관리자의 아이디와 비밀번호를 확인해주세요.");
    	  mv.setViewName("./admin/admin_login");
      }else {
    	  mv.addObject("inc","manager_sales.jsp");
          mv.setViewName("./admin/manager/manager_main");
      }
      
      return mv;
   }
   
   //회원정보조회
   @RequestMapping("manager_user_list")
   public ModelAndView manager_user_list(PageAccountList alpage) {
      ModelAndView mv = new ModelAndView();

      if(alpage.getFindStr() == null) {
         alpage = new PageAccountList();
         alpage.setNowPage(1);
         alpage.setFindStr("");
      }
      
      List<UserVo> list = mlservice.selectUser(alpage);
      alpage = mlservice.getPage();
      
      mv.addObject("list", list);
      mv.addObject("alpage", alpage);
      
      mv.addObject("inc","manager_user_list.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   //캠핌장정보조회
   @RequestMapping("manager_camping_list")
   public ModelAndView manager_camping_list(PageCampAccountList acpage) {
      ModelAndView mv = new ModelAndView();
      
      if(acpage.getFindStr() == null) {
         acpage = new PageCampAccountList();
         acpage.setNowPage(1);
         acpage.setFindStr("");
      }
      
      List<ItemVo> list = mlservice.selectCamping(acpage);
      acpage = mlservice.getPageC();
      
      mv.addObject("list", list);
      mv.addObject("acpage", acpage);
      
      mv.addObject("inc","manager_camping_list.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   // 관리자 수정 페이지 이동
   @RequestMapping("manager_account_modify")
   public ModelAndView manager_account_modify(AuserVo vo) {
      ModelAndView mv = new ModelAndView();

      AuserVo rVo = mlservice.selectAOne(vo.getId());
	  mv.addObject("vo", rVo);
      mv.addObject("inc","manager_account_modify.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   // 관리자 정보 수정
   @RequestMapping("manager_account_modifyR")
   public void manager_account_modifyR(AuserVo vo) {
      mlservice.updateAuser(vo);
   }
   
   //관리자 리스트
   @RequestMapping("manager_account_list")
   public ModelAndView manager_account_list(PageManagerAccountList ampage) {
      ModelAndView mv = new ModelAndView();
     
      
      if(ampage.getFindStr() == null) {
         ampage = new PageManagerAccountList();
         ampage.setNowPage(1);
         ampage.setFindStr("");
      }
      List<AuserVo> list = mlservice.selectAuser(ampage);
      ampage = mlservice.getPageA();
      
      mv.addObject("list", list);
      mv.addObject("ampage", ampage);
      
      mv.addObject("inc","manager_account_list.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   // 관리자 계정 등록 페이지로 이동
   @RequestMapping("manager_account_input")
   public ModelAndView manager_account_input(HttpServletRequest req, PageManagerAccountList ampage) {
      ModelAndView mv = new ModelAndView();
      String job = req.getParameter("job");
      
      if( job.equals("A") ) {
   	   	  mv.addObject("inc","manager_account_input.jsp");
   	      mv.setViewName("./admin/manager/manager_main");
      }else{
    	  List<AuserVo> list = mlservice.selectAuser(ampage);
          ampage = mlservice.getPageA();
          
          mv.addObject("list", list);
          mv.addObject("ampage", ampage);
      	  mv.addObject("inc","manager_account_list.jsp");
      	  mv.setViewName("./admin/manager/manager_main");
      }
     
      return mv;
   }
   
   // 관리자 계정 등록
   @RequestMapping("manager_account_inputR")
   public ModelAndView manager_account_inputR(AuserVo vo, PageManagerAccountList ampage) {
      ModelAndView mv = new ModelAndView();
      mjservice.insertR(vo);
      
      if(ampage.getFindStr() == null) {
          ampage = new PageManagerAccountList();
          ampage.setNowPage(1);
          ampage.setFindStr("");
       }
      
      List<AuserVo> list = mlservice.selectAuser(ampage);
      ampage = mlservice.getPageA();
      
      mv.addObject("list", list);
      mv.addObject("ampage", ampage);
      mv.addObject("inc","manager_account_list.jsp");
      mv.setViewName("./admin/manager/manager_main");
    
      return mv;
   }
   
    // 관리자 계정 삭제
	@RequestMapping("manager_account_listDel")
	public ModelAndView manager_account_listDel(int aUserCode, AuserVo vo, PageManagerAccountList ampage) {
		ModelAndView mv = new ModelAndView();
		
		mlservice.auserdelete(vo);
		
		if(ampage.getFindStr() == null) {
	          ampage = new PageManagerAccountList();
	          ampage.setNowPage(1);
	          ampage.setFindStr("");
	       }
	      
	      List<AuserVo> list = mlservice.selectAuser(ampage);
	      ampage = mlservice.getPageA();
		
	    mv.addObject("list", list);
		mv.addObject("ampage", ampage);
	    mv.addObject("inc","manager_account_list.jsp");
	    mv.setViewName("./admin/manager/manager_main");
	    
		return mv;
	}
   
   //관리자 비밀번호 수정 페이지로 이동
   @RequestMapping("manager_pwd_modify")
   public ModelAndView manager_pwd_modify(AuserVo vo) {
      ModelAndView mv = new ModelAndView();
      AuserVo rVo = mlservice.selectAOne(vo.getId());
      mv.addObject("vo", rVo);
      mv.addObject("inc","manager_pwd_modify.jsp");
      mv.setViewName("./admin/manager/manager_main");
      return mv;
   }
   
   //관리자 비밀번호 변경처리
   @RequestMapping("manager_pwd_modifyR")
   public void manager_pwd_modifyR(AuserVo vo, HttpServletRequest req) {
      
      vo.setId(req.getParameter("id"));
      vo.setPwd(req.getParameter("mpwd_new_check"));
		
      mlservice.updateAPwd(vo);

   }
 
   
   @RequestMapping("mPwdValidation")
	public void mPwdValidation(HttpServletRequest req, HttpServletResponse resp) {
	   String pwd = req.getParameter("mpwd");
	   String id = req.getParameter("id");
   	
	   boolean mPwdValidation = mlservice.mPwdValidation(id, pwd);
   	   resp.setContentType("text/plain;charset=euc-kr"); //문자화
	   PrintWriter out = null;
		try {
			out = resp.getWriter();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(mPwdValidation) {
		    out.print("not matched");
		} else {
		    out.print("matched"); //일치시 진행가능
			}
   		}

   
	//관리자 - 문의에서 partner_join 게시물 클릭할 경우
	@RequestMapping("manager_request_join_view")
	public ModelAndView partner_request_join_view(HttpServletRequest req, PageReq page) {
		   
		   ModelAndView mv = new ModelAndView();
		   HistoryCuserVo vo = null;
		   int hisUserSno = Integer.parseInt(req.getParameter("hisUserSno"));
		   
		   vo = mlservice.selectCuserHisviewM(hisUserSno);
		   
		   mv.addObject("vo", vo);
		   mv.addObject("page", page);
		   mv.addObject("inc","manager_partner_join_view.jsp");
		   mv.setViewName("./admin/manager/manager_main"); 
		   
		   return mv;
	}
	
	//관리자 - 문의에서 partner_modify 게시물 클릭할 경우
	@RequestMapping("manager_request_modify_view")
	public ModelAndView manager_request_modify_view(HttpServletRequest req, PageReq page) {
		   
		   ModelAndView mv = new ModelAndView();
		   HistoryCuserVo vo = null;
		   int hisUserSno = Integer.parseInt(req.getParameter("hisUserSno"));
		   
		   vo = mlservice.selectCuserHisviewM(hisUserSno);
		   
		   mv.addObject("vo", vo);
		   mv.addObject("page", page);
		   mv.addObject("inc","manager_partner_account_modify_view.jsp");
		   mv.setViewName("./admin/manager/manager_main"); 
		   
		   return mv;
	}
	
	// 관리자 문의 - 캠지기 회원가입 승인
	@RequestMapping("manager_partner_join_confirm")
	public ModelAndView manager_partner_join_confirm(HistoryCuserVo vo, HttpServletRequest req, PageReq page) {

		   ModelAndView mv = new ModelAndView();
		   mlservice.updateCuserConfirm(vo, req);

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
	
	//관리자 - 캠지기 정보 수정요청
	@RequestMapping("manager_partner_modify_confirm")
	public ModelAndView manager_partner_modify_confirm(HistoryCuserVo vo, HttpServletRequest req, PageReq page) {
		  
		   ModelAndView mv = new ModelAndView();
		   mlservice.updateCuserModiConfirm(vo, req);
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
}