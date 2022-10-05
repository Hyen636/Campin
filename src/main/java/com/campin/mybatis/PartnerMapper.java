//220628
package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.manager.NoticeVo;
import com.campin.partner.CuserVo;
import com.campin.partner.HistoryCuserVo;
import com.campin.partner.JoinAtt;
import com.campin.partner.RequestVo;


@Mapper
@Repository
public interface PartnerMapper {
	public String partner_find_pwd(CuserVo vo);
	public CuserVo clogin(CuserVo vo);
	
	public int insertR(CuserVo vo);
	
	public int inserttempR(HistoryCuserVo hvo);
	public String cIdValdation(String id);
	public String cPhoneValidation(String phone);
	public String cEmailValidation(String email);
	public String cBusiNumValidation(String busiNum);
	public int insertAtt(List<JoinAtt> attList);
	public int getcUserCode(CuserVo vo);

	
	public int getItemCode(int cUserCode);
	
	public int insertI(int cUserCode);
	public int getMaxcUserCode();
	public String getItemState(int cUserCode);
	
	public int insertbusiAtt(JoinAtt busiAtt);
	public int inserttravelAtt(JoinAtt travelatt);
	
	public int insertHistoryCuser(CuserVo vo);
	public CuserVo selectCOne(String id);
	
	public String cPwdValidation(String id);
	public void updateCPwd(CuserVo vo);
	public String findpCId(CuserVo vo);
	public String findeCId(CuserVo vo);
	public HistoryCuserVo updatejoinHisR(CuserVo vo);
	public int getHisSno(int cUserCode);
	public int searchcode(int hisSno);
	public int insertHbusiatt(JoinAtt busiAtt);
	public int insertHtravelatt(JoinAtt travelAtt);
	public int getMaxHisSno();
	public void insertCuserReq(RequestVo rVo);
	public int getMaxitemCode();
	
	public HistoryCuserVo selectCuserHisview(int hisUserSno);
	
	public int partner_account_modiHisCuser(CuserVo vo);
	public int partner_account_modiReq(RequestVo rVo);
	
	public int updateAccountHisR(HistoryCuserVo vo);
	public int updateCuserstate(CuserVo vo);
	
	public String getmNameCuser(int cUserCode);
	
	public int deleteAccountHisR(int hisSno);
	public int partner_account_modiDel(RequestVo rVo);
	public int updateCuserstateDel(CuserVo vo);
	
	
	public int partner_join_reqDel(CuserVo cVo);
	public int partner_join_reqDelitem(int cUserCode);
	
	public List<JoinAtt> attList(int hisUserSno);

	public NoticeVo notice(int ItemCode);
}
