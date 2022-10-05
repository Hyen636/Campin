package com.campin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campin.manager.ManagerOrderService;
import com.campin.manager.ManagerjoinService;
import com.campin.manager.NoticeVo;
import com.campin.partner.CategoryVo;
import com.campin.partner.ItemAtt;
import com.campin.partner.ItemOptionVo;
import com.campin.partner.ItemService;
import com.campin.partner.ItemVo;
import com.campin.partner.RequestVo;
import com.campin.partner.SalesVo;
import com.campin.partner.SiteVo;

@RestController
public class HoonManagerController {
	@Autowired
	ItemService dao;
	HttpSession session;
	@Autowired
	ManagerOrderService orderDao;
	@Autowired
	ManagerjoinService mjDao;
	
	@RequestMapping("manager_main")
	public ModelAndView managerMain() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("./admin/manager/manager_main");
		return mv;
	}
	@RequestMapping("manager_notice")
	public ModelAndView managerNotice() {
		ModelAndView mv = new ModelAndView();
		NoticeVo vo = null;
		vo = mjDao.notice();
		
		mv.addObject("vo", vo);
		mv.setViewName("./admin/manager/manager_notice");
		return mv;
	}
	@RequestMapping("manager_sales")
	public ModelAndView managerSales() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./manager_sales.jsp");
		mv.setViewName("./admin/manager/manager_main");
		return mv;
	}
	
	@RequestMapping("manager_logout")
	public ModelAndView managerlogout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		session = req.getSession();
		session.setAttribute("id", null);
		session.setAttribute("job", null);
		mv.setViewName("./admin/admin_login");
		return mv;
	}
	
	@RequestMapping("manager_camp_view")
	public ModelAndView managerCampView(String hisItemSno, String stateR, String sno) throws NumberFormatException {
		ModelAndView mv = new ModelAndView();
		int hItemSno = Integer.parseInt(hisItemSno);
		int no = Integer.parseInt(sno);
		ItemVo vo = null;
		List<CategoryVo> categoryList = null;
		List<ItemOptionVo> itemOptionList = null;
		List<ItemAtt> itemAttList = null;
		List<ItemAtt> whList = new ArrayList<ItemAtt>();
		Map<String, Object> campImg = new HashMap<String, Object>();
		
		ItemVo hisVo = null;
		List<CategoryVo> hisCategoryList = null;
		List<ItemOptionVo> hisItemOptionList = null;
		List<ItemAtt> hisItemAttList = null;
		List<ItemAtt> hisWhList = new ArrayList<ItemAtt>();
		Map<String, Object> hisCampImg = new HashMap<String, Object>();
		
		// 히스토리
		hisVo = dao.selectHisItem(hItemSno);
		hisCategoryList = dao.selectHisCategory(hItemSno);
		hisItemOptionList = dao.selectHisItemOption(hItemSno);
		hisItemAttList = dao.selectHisItemAtt(hItemSno);
		
		if(hisItemAttList.size() > 0) {
			for(ItemAtt hisAttVo : hisItemAttList) {
				if(hisAttVo.getItemType().equals("map")) {
					hisCampImg.put("map", hisAttVo);
				} else if (hisAttVo.getItemType().equals("WH")) {
					hisWhList.add(hisAttVo);
				}
				hisCampImg.put("wh", hisWhList);
			}			
		}
		// 기존
		vo = dao.selectItem(hisVo.getItemCode());
		categoryList = dao.selectCategory(hisVo.getItemCode());
		itemOptionList = dao.selectItemOption(hisVo.getItemCode());
		itemAttList = dao.selectItemAtt(hisVo.getItemCode());
		
		for(ItemAtt attVo : itemAttList) {
			if(attVo.getItemType().equals("map")) {
				campImg.put("map", attVo);
			} else if (attVo.getItemType().equals("WH")) {
				whList.add(attVo);
			}
			campImg.put("wh", whList);
		}
		
		mv.addObject("hisVo", hisVo);
		mv.addObject("hisCategoryList", hisCategoryList);
		mv.addObject("hisItemOptionList", hisItemOptionList);
		mv.addObject("hisCampImg", hisCampImg);
		
		mv.addObject("vo", vo);
		mv.addObject("categoryList", categoryList);
		mv.addObject("itemOptionList", itemOptionList);
		mv.addObject("campImg", campImg);
		
		mv.addObject("sno", no);
		mv.addObject("state", stateR);
		mv.addObject("inc", "../partner/partner_camp_view.jsp");
		mv.setViewName("./admin/manager/manager_main");
		
		return mv;
	}
	
	@RequestMapping("manager_camp_confirm")
	public boolean managerCampConfirm(RequestVo vo) {
		boolean b = true;
		int cnt = 0;
		
		ItemVo hisVo = dao.selectHisItem(vo.getHisItemSno());
		cnt = dao.updateItem(vo, hisVo);
		if(cnt < 2) b = false;
		return b;
	}
	
	@RequestMapping("manager_camp_pop")
	public ModelAndView managerCampPop() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("./admin/manager/manager_camp_reject");
		return mv;
	}
	@RequestMapping("manager_site_pop")
	public ModelAndView managerSitePop() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("./admin/manager/manager_site_reject");
		return mv;
	}
	@RequestMapping("manager_camp_reject")
	public void managerCampReject(RequestVo rVo, String rejectMsg) {
		ItemVo hVo = new ItemVo();
		hVo.setHisItemSno(rVo.getHisItemSno());
		hVo.setState("요청반려");
		rVo.setState("요청반려");
		dao.rejectItem(rVo, hVo, rejectMsg);
	}
	
	@RequestMapping("manager_site_view")
	public ModelAndView managerSiteView(String hisDetailSno, String stateR, String sno) throws NumberFormatException {
		ModelAndView mv = new ModelAndView();
		int hDetailSno = Integer.parseInt(hisDetailSno);
		SiteVo sVo = null;
		SiteVo hVo = null;
		List<ItemAtt> sList = null;
		List<ItemAtt> hList = null;
		
		hVo = dao.selectSiteHistory(hDetailSno); 		// history
		sVo = dao.selectSite(hVo);						// 원본
		hList = dao.selectSiteAttHistory(hDetailSno);
		sList = dao.selectSiteImg(sVo);
		mv.addObject("hVo", hVo);
		mv.addObject("sVo", sVo);
		mv.addObject("hList", hList);
		mv.addObject("sList", sList);
		mv.addObject("sno", sno);
		mv.addObject("state", stateR);
		mv.addObject("inc", "../partner/partner_site_view.jsp");
		mv.setViewName("./admin/manager/manager_main");
		return mv;
	}
	
	@RequestMapping("manager_site_confirm")
	public boolean partnerSiteConfirm(RequestVo rVo) {
		boolean b = true;
		int cnt = 0;
		SiteVo hisVo = dao.selectSiteHistory(rVo.getHisDetailSno());
		cnt = dao.updateSite(rVo, hisVo);
		if(cnt < 1) b = false;
		return b;
	}
	
	@RequestMapping("manager_site_reject")
	public void managerSiteReject(RequestVo rVo, String rejectMsg) {
		SiteVo hVo = new SiteVo();
		hVo.setHisDetailSno(rVo.getHisDetailSno());
		hVo.setState("요청반려");
		rVo.setState("요청반려");
		dao.rejectSite(rVo, hVo, rejectMsg);
	} 

	
	@RequestMapping("manager_sales_month_graph")
	public Map<String, Object> managerSalesMonthGraph(SalesVo sVo){
		List<SalesVo> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		list = orderDao.selectMonthSales(sVo);
		for(SalesVo vo : list) {
			map.put(vo.getYearMonth()+vo.getiName(), vo);// map구조, 연월+캠핑장을 키
		}			
		return map;
	}
	
	@RequestMapping("manager_getItem")
	public List<String> managerGetItem(SalesVo sVo){
		List<String> itemCodeList = null;
		itemCodeList = orderDao.getOrderItemName(sVo);
		return itemCodeList;
	}
}
