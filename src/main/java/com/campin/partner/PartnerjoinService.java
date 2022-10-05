package com.campin.partner;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.controller.CjoinFileUploadController;
import com.campin.manager.NoticeVo;
import com.campin.mybatis.PartnerMapper;

@Service
@Transactional
public class PartnerjoinService implements PartnerjoinInterface {
   
   Page page;
   AES aes;
   
   @Autowired
   PartnerMapper mapper;
   
      @Autowired
      DataSourceTransactionManager transaction;
      TransactionStatus status;
   
   public PartnerjoinService() {
      aes = new AES();
      //System.out.println("PartnerjoinService...");
   }
   

   //휴대폰번호로 아이디찾기
   public String findpCidR(CuserVo vo) {
      String id = "";
      
      try {
         id = mapper.findpCId(vo);
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      return id;
   }
   //이메일로 아이디찾기
   public String findeCidR(CuserVo vo) {
      String id = "";
      
      try {
         id = mapper.findeCId(vo);
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      return id;
   }
   
   //암호 찾기 //진행중
   @Override
   public String findPwd(CuserVo vo) {
         String pwd = "";
         try {
            String temp = mapper.partner_find_pwd(vo);
            //복호화(디코드)된 암호전달
            pwd = aes.dec(temp);
            
         }catch(Exception ex) {
            ex.printStackTrace();;
         }
         return pwd;
      }
      
   //로그인
   @Override
   public CuserVo loginR(CuserVo vo, HttpServletRequest req) {    
      
      CuserVo rVo = null;
      HttpSession session = req.getSession();
      int itemCode = 0;
      String state = ""; //itemstate
      String cUserstate = "";
            
      try {
         String pwd = aes.enc(vo.getPwd() );
         vo.setPwd(pwd);
         rVo = mapper.clogin(vo);

         if(rVo == null) {
        	 session.setAttribute("id", null);
        	 session.setAttribute("cUserCode", null);
			 session.setAttribute("itemCode", null); 
			 session.setAttribute("state", null);
			 session.setAttribute("cUserstate", null);
         }else {
        	itemCode = mapper.getItemCode(rVo.getCuserCode()); 
        	state = mapper.getItemState(rVo.getCuserCode());
        	
        	session.setAttribute("id", rVo.getId()); 
        	session.setAttribute("cUserCode", rVo.getCuserCode());
        	session.setAttribute("itemCode", itemCode);
        	session.setAttribute("state", state); 
        	session.setAttribute("cUserstate", rVo.getState());
        	
        	//System.out.println("cusercode" + rVo.getCuserCode());
			//System.out.println("itemcode : " + itemCode);
			//System.out.println("item state : "+ state);
			//System.out.println("cUserstate : " + rVo.getState());
         }
         
      }catch(Exception ex) {
         ex.printStackTrace();
      }
         return rVo;
   }
   
   //비밀번호 수정시 값체크
   @Override
   public boolean cPwdValidation(String id, String pwd) {
      boolean b = true;
      String cpwd = "";
      cpwd = aes.enc(pwd); // 입력하여 가져온값 암호화

      //비밀번호 일치 여부 체크
      String r = mapper.cPwdValidation(id);
      
      if(r.equals(cpwd)) {
         b = false;
      }
      return b;
   }
   
   //id 중복 체크
   @Override
   public boolean cIdValdation(String id) {
      boolean b = true;
      String result = mapper.cIdValdation(id);
      if(result == "" || result == null) {
         b = false;
      }
      return b;
   }
   
   //연락처 중복 체크
   @Override
   public boolean cPhoneValidation(String phone) {
      boolean b = true;
      String result = mapper.cPhoneValidation(phone);
      if(result == "" || result == null) {
         b = false;
      }
      return b;
   }
   
   //이메일 중복 체크
   @Override
   public boolean cEmailValidation(String email) {
      boolean b = true;
      String result = mapper.cEmailValidation(email);
      if(result == "" || result == null) {
         b = false;
      }
      return b;
   }
   
   //사업자등록번호 중복 체크
   @Override
   public boolean cBusiNumValidation(String busiNum) {
      boolean b = true;
      String result = mapper.cBusiNumValidation(busiNum);
      if(result == "" || result == null) {
         b = false;
      }
      return b;
   }
   
   //등록 // 캠지기 회원가입 - 등록요청
   @Override            
   public boolean insertR(CuserVo vo) {      
      boolean b = true;
      try {
         //비밀번호 암호화
         String pwd = aes.enc(vo.getPwd());
         vo.setPwd(pwd);
         status = transaction.getTransaction(new DefaultTransactionDefinition());
         mapper.insertR(vo);
         
         int cUserCode = mapper.getMaxcUserCode();
         mapper.insertI(cUserCode);
   
         vo.setCuserCode(cUserCode);
         mapper.insertHistoryCuser(vo);
         
         int hisSno = mapper.getMaxHisSno();
         //System.out.println("joinservice : " + hisSno);
         int itemCode = mapper.getMaxitemCode();
         //System.out.println("joinService에서 가져온 로그인 기준 itemCode: " + itemCode);
         
         RequestVo rVo = new RequestVo();
         rVo.setHisUserSno(hisSno);
         rVo.setcUserCode(cUserCode);
         rVo.setmName(vo.getmName());
         rVo.setState(vo.getState());
         rVo.setItemCode(itemCode);
         
         mapper.insertCuserReq(rVo);
         transaction.commit(status);
         
      }catch(Exception ex){
         ex.printStackTrace();
         b=false;
      }
      return b;
   }
   
   //이미지파일
   @Override
   public void insertbusiAtt(JoinAtt busiAtt) {
      int cnt = 0;
      try {
         cnt = mapper.insertbusiAtt(busiAtt);
         //System.out.println("insertbusiAtt.. : ok...");
         status = transaction.getTransaction(new DefaultTransactionDefinition() );
         if(cnt>0) {
            mapper.insertHbusiatt(busiAtt);
            transaction.commit(status);
            //System.out.println("busiAtt저장..");
         }else {
            transaction.rollback(status);
         }
      }catch(Exception ex) {
         ex.printStackTrace();
      }

   }
   
   //이미지파일
   @Override
   public void inserttravelAtt(JoinAtt travelAtt) {
      int cnt = 0;
      try {
         cnt = mapper.inserttravelAtt(travelAtt);
         //System.out.println("inserttravelatt.. : ok.. ");
         status = transaction.getTransaction(new DefaultTransactionDefinition() );
         if(cnt>0) {
            mapper.insertHtravelatt(travelAtt);
            transaction.commit(status);
            //System.out.println("travelAtt저장..");
         }else {
            transaction.rollback(status);
         }
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }


   //비밀번호 업데이트
   @Override
   public boolean updateCPwd(CuserVo vo) {
      boolean b = true;
      String pwd="";
      try {
         //비밀번호 암호화
         pwd = aes.enc(vo.getPwd());
         vo.setPwd(pwd);
         status = transaction.getTransaction(new DefaultTransactionDefinition());
         mapper.updateCPwd(vo);
         transaction.commit(status);
      }catch(Exception ex){
         ex.printStackTrace();
      }
      return b;
      
   }
   
   @Override
   public int getcUserCode(CuserVo vo) {
      int cUserCode = 0;
      cUserCode = mapper.getcUserCode(vo);
      return cUserCode;
   }

   public int getHisSno(int cUserCode) {   
      int hisSno = 0;
      hisSno = mapper.getHisSno(cUserCode);
      return hisSno;
   }
   
   @Override
   public CuserVo selectCOne(String id) {
      CuserVo vo = null;
      try {
         vo = mapper.selectCOne(id);
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      return vo;
   }

   @Override
   public List<CuserVo> select(Page page) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String findCid(CuserVo vo) {
      String id = "";
      return id;
   }

   //관리자문의에서 게시글 선택
   @Override
   public HistoryCuserVo selectCuserHisview(int hisUserSno) {
	   
      HistoryCuserVo vo = null;
      
      try {
    	 status = transaction.getTransaction(new DefaultTransactionDefinition());
    	 vo = mapper.selectCuserHisview(hisUserSno);
    	 transaction.commit(status);
         
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      return vo;
   }

    //파트너 정보 수정 요청
    @Override
    public boolean partner_account_modiReq(CuserVo vo, HttpServletRequest req) {
      boolean b = true;
      try {

    	 status = transaction.getTransaction(new DefaultTransactionDefinition());
         mapper.partner_account_modiHisCuser(vo);

         int cUserCode = vo.getCuserCode();
         int hisSno = mapper.getMaxHisSno();
         int itemCode = Integer.parseInt(req.getParameter("itemCode"));
         String state = req.getParameter("state");
         
         vo.setCuserCode(cUserCode);
         vo.setState(state);
         mapper.updateCuserstate(vo);
               
         String mName = mapper.getmNameCuser(cUserCode);
         
         RequestVo rVo = new RequestVo();
         rVo.setHisUserSno(hisSno);
         rVo.setcUserCode(cUserCode);
         rVo.setmName(mName);
         rVo.setState(state);
         rVo.setItemCode(itemCode);
         
         mapper.partner_account_modiReq(rVo);
         transaction.commit(status);
         
      }catch(Exception ex){
         ex.printStackTrace();
         b=false;
      }
      return b;
   }

    // 캠지기 - 계정 수정 히스토리, 요청 수정
	public void partner_account_hisModi(HistoryCuserVo vo, HttpServletRequest req) {
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			mapper.updateAccountHisR(vo);
			transaction.commit(status);
			
		}catch(Exception ex){
	        ex.printStackTrace();
	     }
		
	}

	// 캠지기 - 계정 수정 히스토리,요청 삭제
	public void partner_account_modiDel(HistoryCuserVo vo, HttpServletRequest req) {
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			int hisSno = vo.getHisSno();
			int cUserCode = vo.getCuserCode();
			mapper.deleteAccountHisR(hisSno);
			
			RequestVo rVo = new RequestVo();
			rVo.setHisUserSno(hisSno);
			mapper.partner_account_modiDel(rVo);
			
			CuserVo cVo = new CuserVo();
			cVo.setState("등록완료");
			cVo.setCuserCode(cUserCode);
		
			mapper.updateCuserstateDel(cVo);
			transaction.commit(status);
			
		}catch(Exception ex){
	        ex.printStackTrace();
	     }
		
	}

	// 캠지기 회원가입 - 승인요청 - 수정 (히스토리 수정) - 글씨랑 추가는 수정 OK
	public void partner_join_reqModi(HistoryCuserVo vo, HttpServletRequest req) {
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());			
			mapper.updateAccountHisR(vo);

			//doc 폴더의 이미지 바꿀 경우 삭제
			if(!vo.getModiBusi().equals(vo.getDelBusi() )) {
				File f = new File(CjoinFileUploadController.uploadPath + vo.getDelBusi());
				if(f.exists()) f.delete();
			}
			if(!vo.getModiTravel().equals(vo.getDelTravel() )) {
				File f = new File(CjoinFileUploadController.uploadPath + vo.getDelTravel());
				if(f.exists()) f.delete();
			}
			
			transaction.commit(status);
			
		}catch(Exception ex){
	        ex.printStackTrace();
	     }
		
	}
	
	// 캠지기 회원가입 - 승인요청 - 삭제
	public void partner_join_reqDel(HistoryCuserVo vo, HttpServletRequest req) {
		
		String pwd="";
		int hisSno = 0;
		int cUserCode = 0;
		
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			pwd = aes.enc(vo.getPwd());
			hisSno = vo.getHisSno();
			cUserCode = vo.getCuserCode();
			
			System.out.println("비즈니스" + vo.getDelBusi());
			System.out.println("여행" + vo.getDelTravel());
			
			//doc 폴더의 이미지 삭제
			File f = new File(CjoinFileUploadController.uploadPath + vo.getDelBusi());
			if(f.exists()) f.delete();
				
			f = new File(CjoinFileUploadController.uploadPath + vo.getDelTravel());
			if(f.exists()) f.delete();
			
			mapper.deleteAccountHisR(hisSno);
			
			RequestVo rVo = new RequestVo();
			rVo.setHisUserSno(hisSno);
			mapper.partner_account_modiDel(rVo);
			mapper.partner_join_reqDelitem(cUserCode); //자동 생성된 item 테이블 삭제
				
			CuserVo cVo = new CuserVo();
			cVo.setPwd(pwd);
			cVo.setCuserCode(cUserCode);
			
			mapper.partner_join_reqDel(cVo);
			transaction.commit(status);
		
		}catch(Exception ex){
	        ex.printStackTrace();
	     }
	}
	
	
	
	
	
	public NoticeVo notice(int itemCode) {
	      NoticeVo vo = null;
	      vo = mapper.notice(itemCode);
	      return vo;
	   }
}