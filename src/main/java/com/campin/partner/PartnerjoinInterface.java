
package com.campin.partner;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface PartnerjoinInterface {

   public List<CuserVo> select(Page page);
   public boolean insertR(CuserVo vo);
   
   public CuserVo selectCOne(String id);

   public boolean updateCPwd(CuserVo vo);
   
   public void insertbusiAtt(JoinAtt busiAtt);
   public void inserttravelAtt(JoinAtt travelAtt);
   
   public String findCid(CuserVo vo);
   public String findPwd(CuserVo vo);
   public CuserVo loginR(CuserVo vo, HttpServletRequest req);
   
   public boolean cPwdValidation(String id, String pwd);
   public boolean cIdValdation(String id);
   public boolean cPhoneValidation(String phone);
   public boolean cEmailValidation(String email);
   public boolean cBusiNumValidation(String busiNum);
   
   public int getcUserCode(CuserVo vo);
   
   public HistoryCuserVo selectCuserHisview(int hisUserSno);
   public boolean partner_account_modiReq(CuserVo vo, HttpServletRequest req);
   
}