package com.campin.partner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.ItemMapper;
import com.campin.mybatis.ReqMapperC;

@Service
@Transactional
public class ItemService implements ItemInterface{
	
	@Autowired
	ItemMapper mapper;
	@Autowired
	ReqMapperC reqMapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	String nowDate;
	
	public ItemService() {
		Date date = new Date();
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		nowDate = dtf.format(date);
	}
	
	public String getcUserName(int cUserCode) {
		String cUserName = "";
		cUserName = mapper.getcUserName(cUserCode);
		
		return cUserName;	
	}
	
	@Override
	public void insertItem(ItemVo vo) {	// 최초 등록
		int itemCnt = 0;
		try {
			itemCnt = mapper.updateItem(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			
			if(itemCnt > 0) {
				transaction.commit(status);
		
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void insertCategory(List<CategoryVo> list) {
		int cCnt = 0;
		
		try {
			cCnt = mapper.insertCategory(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void insertItemOption(List<ItemOptionVo> list) {
		int optionCnt = 0;
		
		try {
			optionCnt = mapper.insertItemOption(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(optionCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertItemAtt(List<ItemAtt> list) {
		int attCnt = 0;
		
		try {
			attCnt = mapper.insertItemAtt(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(attCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int insertItemHistory(ItemVo vo) {
		int hisCnt = 0;
		int hisItemSno = 0;
		int reqCnt = 0;
		try {			
			RequestVo rVo = new RequestVo();
			hisCnt = mapper.insertItemHistory(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			hisItemSno = mapper.getMaxHisItemSno();
			String mName = mapper.getcUserName(vo.getcUserCode());
			
			// 요청 생성
			rVo.setHisItemSno(hisItemSno);
			rVo.setTitle("캠핑장 : " + vo.getiName() + " 요청");
			rVo.setSeq(0);
			rVo.setcUserCode(vo.getcUserCode());
			rVo.setNal(nowDate);
			rVo.setmName(mName);
			rVo.setItemCode(vo.getItemCode());
			rVo.setState(vo.getState());
			rVo.setCategory("캠핑장");
			reqCnt = reqMapper.insert(rVo);
			
			if(hisCnt > 0 && reqCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hisItemSno;
	}
	
	@Override
	public void insertCategoryHistory(List<CategoryVo> list) {
		int cCnt = 0;
		
		try {
			cCnt = mapper.insertCategoryHistory(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void insertItemOptionHistory(List<ItemOptionVo> list) {
		int optionCnt = 0;
		
		try {
			optionCnt = mapper.insertItemOptionHistory(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(optionCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertItemAttHistory(List<ItemAtt> list) {
		int attCnt = 0;
		
		try {
			attCnt = mapper.insertItemAttHistory(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(attCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	// 사이트 리스트
	@Override
	public List<SiteVo> selectSiteList(int itemCode) {
		List<SiteVo> list = null;
		
		try {
			list = mapper.selectSiteList(itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 사이트 등록
	@Override
	public void insertSite(SiteVo vo) {
		int siteCnt = 0;
		
		try {
			siteCnt = mapper.insertSite(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());

			if(siteCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertSiteAtt(List<ItemAtt> list) {
		int siteAttCnt = 0;
		
		try {
			siteAttCnt = mapper.insertSiteAtt(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(siteAttCnt > 0) transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertSiteHistory(SiteVo vo) {
		int hisSiteCnt = 0;
		int hisDetailSno = 0;
		int reqCnt = 0;
		try {
			RequestVo rVo = new RequestVo();
			if(vo.getState().equals("등록요청")) {
				int siteCode = mapper.getMaxSiteCode(vo.getItemCode());
				vo.setSiteCode(siteCode);				
			}
			hisSiteCnt = mapper.insertSiteHistory(vo);
			hisDetailSno = mapper.getMaxHisDetailSno(vo.getItemCode());
			int cUserCode = mapper.getcUserCode(vo.getItemCode());
			String mName = getcUserName(cUserCode);
			
			// 요청 생성
			rVo.setHisDetailSno(hisDetailSno);
			rVo.setTitle(mName + "님의 사이트 : "+  vo.getSiteName() + " " + vo.getState());
			rVo.setSeq(0);
			rVo.setSiteCode(vo.getSiteCode());
			rVo.setSiteName(vo.getSiteName());
			rVo.setcUserCode(cUserCode);
			rVo.setNal(nowDate);
			rVo.setItemCode(vo.getItemCode());
			rVo.setState(vo.getState());
			rVo.setmName(mName);
			rVo.setCategory("사이트");
			reqCnt = reqMapper.insert(rVo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(hisSiteCnt > 0 && reqCnt > 0) {
				transaction.commit(status);
			} else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return hisDetailSno;
	}

	@Override
	public void insertSiteAttHistory(List<ItemAtt> list) {
		int hisAttCnt = 0;
		
		try {
			hisAttCnt = mapper.insertSiteAttHistory(list);
			status = transaction.getTransaction(new DefaultTransactionDefinition());

			if(hisAttCnt > 0)	transaction.commit(status);
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 사이트 뿌려주기
	@Override
	public SiteVo selectSite(SiteVo vo) {
		SiteVo sVo = new SiteVo();
		sVo = mapper.selectSite(vo);	
		return sVo;
	}

	@Override
	public List<ItemAtt> selectSiteImg(SiteVo vo) {
		List<ItemAtt> siteImgList = null;
		
		siteImgList = mapper.selectSiteImg(vo);		
		
		return siteImgList;
	}
	
	// 뷰, 수정 페이지에서 캠프 뿌려주기
	@Override
	public ItemVo selectItem(int itemCode) {
		ItemVo vo = null;
		vo = mapper.selectItem(itemCode);
		return vo;
	}

	@Override
	public List<CategoryVo> selectCategory(int itemCode) {
		List<CategoryVo> categoryList = null;
		categoryList = mapper.selectCategory(itemCode);
		return categoryList;
	}

	@Override
	public List<ItemOptionVo> selectItemOption(int itemCode) {
		List<ItemOptionVo> itemOptionList = null;
		itemOptionList = mapper.selectItemOption(itemCode);
		return itemOptionList;
	}

	@Override
	public List<ItemAtt> selectItemAtt(int itemCode) {
		List<ItemAtt> itemAttList = null;
		itemAttList = mapper.selectItemImg(itemCode);
		return itemAttList;
	}
	
	public ItemVo selectHisItem(int hisItemSno) {
		ItemVo hVo = null;
		hVo = mapper.selectHisItem(hisItemSno);

		return hVo;
	}
	public List<CategoryVo> selectHisCategory(int hisItemSno) {
		List<CategoryVo> hisCategoryList = null;
		hisCategoryList = mapper.selectHisCategory(hisItemSno);
		return hisCategoryList;
	}
	public List<ItemOptionVo> selectHisItemOption(int hisItemSno) {
		List<ItemOptionVo> hisItemOptionList = null;
		hisItemOptionList = mapper.selectHisItemOption(hisItemSno);
		return hisItemOptionList;
	}
	public List<ItemAtt> selectHisItemAtt(int hisItemSno) {
		List<ItemAtt> hisItemAttList = null;
		hisItemAttList = mapper.selectHisItemImg(hisItemSno);

		return hisItemAttList;
	}
	
	// 수정 요청 승인 시 히스토리 아이템 입력 받아, item에 업데이트
	// 사진, 카테고리, 옵션은 삭제 후 입력
	public int updateItem(RequestVo rVo, ItemVo hVo) {
		int reqCnt = 0;
		int voCnt = 0;
		List<CategoryVo> hisCategoryList = null;
		List<ItemOptionVo> hisItemOptionList = null;
		List<ItemAtt> hisItemAttList = null;
		try {
			hVo.setState("등록완료");
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			reqCnt = reqMapper.updateState(rVo);	// request 상태 등록 완료
			voCnt = mapper.updateItem(hVo);			// item 업데이트
			System.out.println("test his sno : " + hVo.getHisItemSno());
			
			// history 내용 가져오기
			hisCategoryList = mapper.selectHisCategory(hVo.getHisItemSno());
			hisItemOptionList = mapper.selectHisItemOption(hVo.getHisItemSno());
			System.out.println("리스트 크기"+hisItemOptionList.size());
			hisItemAttList = mapper.selectHisItemImg(hVo.getHisItemSno());
			// 기존 내용 삭제 후 입력
			if(hisCategoryList.size() > 0) {
				mapper.deleteCategory(hVo.getItemCode());
				mapper.insertCategory(hisCategoryList);				
			}
			if(hisItemOptionList.size() > 0) {
				mapper.deleteItemOption(hVo.getItemCode());
				mapper.insertItemOption(hisItemOptionList);				
			}
			// 수정 요청한 사진이, map인지 전체 사진인지 판단하여 삭제 후 넣기
			if(hisItemAttList.size() > 0) {
				for(ItemAtt att : hisItemAttList) {
					if(att.getItemType().equals("map"))
						mapper.deleteItemAttMap(hVo.getItemCode());
					if(att.getItemType().equals("WH")) {
						int check = mapper.deleteItemAttWH(hVo.getItemCode());
						if(check > 0) break;
					}
				}
				mapper.insertItemAtt(hisItemAttList);
			}
			
			if(voCnt > 0) {
				transaction.commit(status);
				String mail = mapper.getcUserEmail(hVo.getcUserCode());
				sendEmail(mail, hVo, "", "confirm");
			}
			else transaction.rollback(status);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return reqCnt+voCnt;
	}
	
	public SiteVo selectSiteHistory(int hisDetailSno) {
		SiteVo vo = null;
		vo = mapper.selectSiteHistory(hisDetailSno);
		return vo;
	}
	public List<ItemAtt> selectSiteAttHistory(int hisDetailSno){
		List<ItemAtt> list = null;
		list = mapper.selectSiteImgHistory(hisDetailSno);
		return list;
	}
	public void rejectItem(RequestVo rVo, ItemVo hVo, String rejectMsg) {
		int cnt = 0;
		String mail = "";
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());	
			cnt = reqMapper.updateState(rVo);
			if(cnt > 0) {
				hVo = mapper.selectHisItem(hVo.getHisItemSno());
				hVo.setState("요청반려");
				hVo.setRejectMsg(rejectMsg);
				mapper.rejectItem(hVo);
				mail = mapper.getcUserEmail(hVo.getcUserCode());

				transaction.commit(status);
				sendEmail(mail, hVo, rejectMsg, "reject");
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public int updateSite(RequestVo rVo, SiteVo hisVo) {
		int reqCnt = 0;
		String mail = "";
		ItemVo iVo = new ItemVo();
		List<ItemAtt> hisSiteAttList = null;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());	
			reqCnt = reqMapper.updateState(rVo);
			if(reqCnt > 0) {
				hisVo.setState("등록완료");
				// 사이트 정보 업데이트
				mapper.updateSite(hisVo);
				// 수정 요청한 사진이 있다면 실행
				hisSiteAttList = mapper.selectSiteImgHistory(hisVo.getHisDetailSno());
				if(hisSiteAttList.size() > 0) {
					mapper.deleteSiteAtt(hisVo);
					mapper.insertSiteAtt(hisSiteAttList);
				}
				// 메일 보내기
				iVo = mapper.selectItem(hisVo.getItemCode());
				mail = mapper.getcUserEmail(iVo.getcUserCode());
				sendEmail(mail, iVo, "", "confirm");
				transaction.commit(status);
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return reqCnt;
	}
	
	public void rejectSite(RequestVo rVo, SiteVo hVo, String rejectMsg) {
		int cnt = 0;
		String mail = "";
		ItemVo iVo = new ItemVo();
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());	
			cnt = reqMapper.updateState(rVo);
			if(cnt > 0) {
				hVo = mapper.selectSiteHistory(hVo.getHisDetailSno());
				hVo.setState("요청반려");
				hVo.setRejectMsg(rejectMsg);
				mapper.rejectSite(hVo);
				iVo = mapper.selectItem(hVo.getItemCode());
				mail = mapper.getcUserEmail(iVo.getcUserCode());

				transaction.commit(status);
				sendEmail(mail, iVo, rejectMsg, "reject");
			} else {
				transaction.rollback(status);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void sendEmail(String mail, ItemVo vo, String rejectMsg, String flag) {
		String charSet = "UTF-8";
		String hostSMTP = "smtp.naver.com";       
		String hostSMTPid = "ksklaura@naver.com";   // 서버 이메일 주소(보내는 사람)
		String hostSMTPpwd = "XEL4UTYUR3HN";       // 서버 이메일 비번
		HtmlEmail email = new HtmlEmail();

		// 보내는 사람 이메일주소, 제목, 내용
		String fromEmail = "ksklaura@naver.com";    // 보내는 사람 이메일 주소(받는 사람 이메일에 표시됨)
		String fromName = "CAMPIN";              // 프로젝트 이름 또는 보내는 사람 이름
		String subject = "", msg = "";
		
		if(flag == "confirm") {
			subject = "CAMPIN - 회원님의 요청이 승인되었습니다.";
			msg += "<h4>"+vo.getmName()+"님의 "+vo.getiName()+"요청이 승인되었습니다.</h4>";
			msg += "<span>캠지기 홈페이지에서 자세한 내용을 확인해주세요.</span><br/>";
			msg += "<span>CAMPIN을 이용해주셔서 감사합니다.</span> <br/>";
			msg += "<span>오늘도 즐거운 CAMPIN 되세요! :)<br/><br/>";
			msg += "<span style='font-weight: bold'>- CAMPIN - </span>";	
		} else {
			subject = "CAMPIN - 회원님의 요청이 거절되었습니다.";
			msg += "<h4>"+vo.getmName()+"님의 "+vo.getiName()+"요청이 거절되었습니다.</h4>";
			msg += "<span>캠지기 홈페이지에서 자세한 내용을 확인해주세요.</span><br/>";
			msg += "<span>CAMPIN을 이용해주셔서 감사합니다.</span> <br/>";
			msg += "거절 사유 : " + rejectMsg + "<br/>";
			msg += "<span>오늘도 즐거운 CAMPIN 되세요! :)<br/><br/>";
			msg += "<span style='font-weight: bold'>- CAMPIN - </span>";		
		}
	
		try {
			email.setDebug(true);
			email.setCharset(charSet); // 한글 세팅
            email.setHostName(hostSMTP);
            email.setSmtpPort(587);
            
            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send(); // 메일 발송!
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("이메일 발송 중 오류 발생함.");
        }
   	}
	
	
}
