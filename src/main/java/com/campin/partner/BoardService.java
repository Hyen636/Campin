package com.campin.partner;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractPropertyResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.BoardMapperC;;

@Service
@Transactional
public class BoardService implements BoardInterface{
	
	@Autowired
	BoardMapperC mapper;
	PageBoard page;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	
	
	@Override
	public int selectcmapCode(int cUserCode) {
		int itemCode=0;
		try {
			itemCode=mapper.selectcmapCode(cUserCode);
			System.out.println("Boarddao selectcmapCode itemCode : "+ itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemCode;
	}


	@Override
	public List<BoardVo> select(PageBoard page) {
		List<BoardVo> list=null;
		
		try {
			int totSize=mapper.totSize(page);
			page.setTotSize(totSize);
			page.compute();
	
			list=mapper.select(page);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.page=page;
		return list;
	}


	@Override
	public int selectAnswer(BoardVo vo) {
		int grp=0;
		try {
			grp=mapper.selectAnswer(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grp;
	}


	@Override
	public String selectDoc(int sno) {
		String doc=null;
		try {
			doc=mapper.selectDoc(sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	


	@Override
	public String selectqTitle(int sno) {
		String qTitle=null;
		try {
			qTitle=mapper.selectqTitle(sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qTitle;
	}


	@Override
	public int insert(BoardVo vo) {
		int cnt=0;
		
		try {
			cnt = mapper.insert(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				cnt=mapper.insertUpdate(vo);
				if(cnt>0) {
					transaction.commit(status);
				}else {
					transaction.rollback(status);
				}
			}else {
				transaction.rollback(status);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cnt;
	}


	@Override
	public boolean update(BoardVo vo) {
		boolean b=false;
		
		try {
			// 본문 내용을 수정
			int cnt = mapper.update(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				transaction.commit(status);
				b=true;
			}else {
				transaction.rollback(status);
				b=false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}


	@Override
	public boolean delete(BoardVo vo) {
		boolean b=false;
		int sno=vo.getSno();
		int grp=vo.getGrp();
		int cnt=0;
		try {
			cnt=mapper.delete(sno);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				vo.setSno(grp);
				vo.setState("답변대기");
				cnt=mapper.insertUpdate(vo);
				if(cnt>0) {
					transaction.commit(status);
				}else {
					transaction.rollback(status);
				}
			}else {
				transaction.rollback(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
