package com.campin.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.ManagerjoinMapper;
import com.campin.partner.CuserVo;
import com.campin.partner.HistoryCuserVo;
import com.campin.partner.ItemVo;
import com.campin.user.UserVo;

@Service
@Transactional
public class ManagerAdlistService{

	PageAccountList alpage;
	PageCampAccountList acpage;
	PageManagerAccountList ampage;
	AES aes;
	
	@Autowired
	ManagerjoinMapper mjmapper;
	
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public ManagerAdlistService() {
		aes = new AES();
	}
	
	
	public List<UserVo> selectUser(PageAccountList alpage) {
		List<UserVo> list = null;
		
		try {
			int totSize = mjmapper.totSize(alpage);
			
			alpage.setTotSize(totSize);
			alpage.compute();
			
			list = mjmapper.selectUserList(alpage);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		this.alpage = alpage;
		return list;
	}
	
	public List<ItemVo> selectCamping(PageCampAccountList acpage) {
		List<ItemVo> list = null;
		
		try {
			int totSizeC = mjmapper.totSizeC(acpage);
			
			acpage.setTotSizeC(totSizeC);
			acpage.compute();
			
			list = mjmapper.selectCapmingList(acpage);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		this.acpage = acpage;
		return list;
	}
	
	public List<AuserVo> selectAuser(PageManagerAccountList ampage) {
		List<AuserVo> list = null;
		
		try {
			int totSizeA = mjmapper.totSizeA(ampage);
			
			ampage.setTotSizeA(totSizeA);
			ampage.compute();
			
			list = mjmapper.selectAuserList(ampage);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		this.ampage = ampage;
		return list;
	}
	
	public boolean auserdelete(AuserVo vo) {
		boolean b = true;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mjmapper.deleteAuser(vo);
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return b;
		
	}
	
	public AuserVo selectAOne(String id) {
		AuserVo vo = null;
		try {
			vo = mjmapper.selectAOne(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}
	
	
	public boolean updateAuser(AuserVo vo) {
		boolean b = true;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mjmapper.updateAuser(vo);
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return b;
		
	}
	
	public boolean mPwdValidation(String id, String pwd) {
		boolean b = true;
		String cpwd = "";
		cpwd = aes.enc(pwd); // ???????????? ???????????? ?????????

		//???????????? ?????? ?????? ??????
		String r = mjmapper.mPwdValidation(id);
		
		if(r.equals(cpwd)) {
			b = false;
		}
		return b;
	}
	

	public boolean updateAPwd(AuserVo vo) {
		boolean b = true;
		String pwd="";
		try {
			//???????????? ?????????
			pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mjmapper.updateAPwd(vo);
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return b;
	}
	
	//????????? ?????????
	public HistoryCuserVo selectCuserHisviewM(int hisUserSno) {
	    
	     HistoryCuserVo vo = null;
	     try {
	         vo = mjmapper.selectCuserHisviewM(hisUserSno);
	     }catch(Exception ex) {
	         ex.printStackTrace();
	     }
	      return vo;
	}
	
	//?????? ?????????
	public boolean updateCuserConfirm(HistoryCuserVo vo, HttpServletRequest req) {
		boolean b = true;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			String state = req.getParameter("state");
			
			RequestVo rVo = new RequestVo();
			rVo.setState(state);
			rVo.setHisUserSno(vo.getHisSno());
			mjmapper.updateCuserConfirmRequest(rVo);
			
			
			vo.setState(state);
			mjmapper.updateCuserConfirmHistory(vo);
			CuserVo cVo = new CuserVo();
			cVo.setCuserCode(vo.getcUserCode());
			cVo.setState(state);
			mjmapper.updateCuserConfirmCuser(cVo);
			
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return b;
		
	}
	
	// ?????? ?????? ?????????
	public boolean updateCuserModiConfirm(HistoryCuserVo vo, HttpServletRequest req) {
		boolean b = true;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			String state = req.getParameter("state");
			
			RequestVo rVo = new RequestVo();
			rVo.setState(state);
			rVo.setHisUserSno(vo.getHisSno());
			mjmapper.updateCuserConfirmRequest(rVo); // state ??????
			
			vo.setState(state);
			mjmapper.updateCuserConfirmHistory(vo);  // state ??????
			vo = mjmapper.getHistoryCuser(vo.getHisSno());
			
			System.out.println("test " + vo.getmName());
			System.out.println("test " + vo.getPhone());
			System.out.println("test : " + vo.getEmail());
			if(state.equals("????????????")) {
				mjmapper.updateCuserModiConfirmCuser(vo);
			}else if(state.equals("????????????")) {
				CuserVo cVo = new CuserVo();
				cVo.setCuserCode(vo.getcUserCode());
				cVo.setState(state);
				mjmapper.updateCuserConfirmCuser(cVo);
			}
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return b;
		
	}

	
	//page
	public PageAccountList getPage() {
		return this.alpage;
	}
	
	public PageCampAccountList getPageC() {
		return this.acpage;
	}
	
	public PageManagerAccountList getPageA() {
		return this.ampage;
	}

}
