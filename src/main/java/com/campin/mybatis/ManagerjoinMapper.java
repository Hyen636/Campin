package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.manager.AuserVo;
import com.campin.manager.NoticeVo;
import com.campin.manager.PageAccountList;
import com.campin.manager.PageCampAccountList;
import com.campin.manager.PageManagerAccountList;
import com.campin.manager.RequestVo;
import com.campin.partner.CuserVo;
import com.campin.partner.HistoryCuserVo;
import com.campin.partner.ItemVo;
import com.campin.user.UserVo;


@Mapper
@Repository
public interface ManagerjoinMapper {
	public AuserVo manager_login(AuserVo vo); //login
	
	public int insertR(AuserVo vo);
	public HistoryCuserVo getHistoryCuser(int hisSno);
	public List<UserVo> selectUserList(PageAccountList alpage);
	public List<ItemVo> selectCapmingList(PageCampAccountList acpage);
	public List<AuserVo> selectAuserList(PageManagerAccountList ampage);
	
	public int totSize(PageAccountList alpage);
	public int totSizeC(PageCampAccountList acpage);
	public int totSizeA(PageManagerAccountList ampage);
	
	public AuserVo selectAOne(String id);

	public void updateAuser(AuserVo vo);
	public void deleteAuser(AuserVo vo);
	public void updateAPwd(AuserVo vo);

	public String mPwdValidation(String id);

	public HistoryCuserVo selectCuserHisviewM(int hisUserSno);

	public void updateCuserConfirmRequest(RequestVo rVo);
	public void updateCuserConfirmHistory(HistoryCuserVo vo);
	public void updateCuserConfirmCuser(CuserVo cVo);

	public void updateCuserModiConfirmCuser(HistoryCuserVo vo);
	
	public NoticeVo notice();

	
}
