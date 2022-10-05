package com.campin.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.ManagerjoinMapper;

@Service
@Transactional
public class ManagerjoinService implements ManagerjoinInterface{

	PageAccountList page;
	AES aes;
	
	@Autowired
	ManagerjoinMapper mjmapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public ManagerjoinService() {
		aes = new AES();
	}
	
	//로그인
	public AuserVo loginR(AuserVo vo, HttpServletRequest req) {
		
		AuserVo rVo = null;
		HttpSession session = req.getSession();
				   
		try {
			String pwd = aes.enc(vo.getPwd() );
			vo.setPwd(pwd);
			rVo = mjmapper.manager_login(vo);
	
			if(rVo == null) {
				session.setAttribute("id", null);
				session.setAttribute("job", null);
			}else {
				session.setAttribute("id", rVo.getId());
				session.setAttribute("job", rVo.getJob());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			return rVo;
		}
	
	//회원가입
	@Override
	public boolean insertR(AuserVo vo) {
		boolean b = true;
		try {
			//비밀번호 암호화
			String pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mjmapper.insertR(vo);
			transaction.commit(status);
		}catch(Exception ex){
			ex.printStackTrace();
			b=false;
		}
		return b;
	}

	@Override
	public List<AuserVo> select(PageAccountList page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public NoticeVo notice() {
	      NoticeVo vo = null;
	      vo = mjmapper.notice();
	      
	      return vo;
	   }

}
