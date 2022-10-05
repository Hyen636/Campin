package com.campin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.campin.manager.NoticeVo;
import com.campin.partner.CategoryVo;
import com.campin.partner.ItemAtt;
import com.campin.partner.ItemOptionVo;
import com.campin.partner.ItemService;
import com.campin.partner.ItemVo;
import com.campin.partner.PartnerOrderPage;
import com.campin.partner.PartnerOrderService;
import com.campin.partner.PartnerOrderVo;
import com.campin.partner.PartnerjoinService;
import com.campin.partner.SalesVo;
import com.campin.partner.SiteVo;

@RestController
public class HoonPartnerController {

	@Autowired
	ItemService dao;
	@Autowired 
	PartnerOrderService orderDao;
	@Autowired
	PartnerjoinService pjDao;
	@Autowired
	FileUploadController fc;
	HttpSession session;
	
	@RequestMapping("partner_main")
	public ModelAndView partnerMain() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./partner_sales.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	@RequestMapping("partner_notice")
	public ModelAndView partnerNotice(String itemCode) throws NumberFormatException {
		ModelAndView mv = new ModelAndView();
		NoticeVo vo = null;
		int iCode = Integer.parseInt(itemCode);
		vo = pjDao.notice(iCode);
		
		mv.addObject("vo", vo);
		mv.setViewName("./admin/partner/partner_notice");
		return mv;
	}
	@RequestMapping("partner_logout")
	public ModelAndView partnerLogout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		session = req.getSession();
		session.setAttribute("id", null);
		session.setAttribute("cUserCode", null);
		session.setAttribute("itemCode", null);
		session.setAttribute("state", null);
			
		mv.setViewName("admin/admin_login");
		return mv;
	}
	@RequestMapping("partner_sales")
	public ModelAndView partnerSales() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./partner_sales.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}

	@RequestMapping("partner_reservation_list")
	public ModelAndView partnerReservationList() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./partner_reservation_list.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	// 캠핑
	@RequestMapping("partner_camp_input")
	public ModelAndView partnerInput() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./partner_camp_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	@RequestMapping("partner_camp_modify")
	public ModelAndView partnerModify(@RequestParam(value="itemCode", required=false) String itemCode) throws NumberFormatException{
		ModelAndView mv = new ModelAndView();
		int iCode = Integer.parseInt(itemCode);
		ItemVo vo = null;
		List<CategoryVo> categoryList = null;
		List<ItemOptionVo> itemOptionList = null;
		List<ItemAtt> itemAttList = null;
		List<ItemAtt> whList = new ArrayList<ItemAtt>();
		Map<String, Object> campImg = new HashMap<String, Object>();
		
		vo = dao.selectItem(iCode);
		categoryList = dao.selectCategory(iCode);
		itemOptionList = dao.selectItemOption(iCode);
		itemAttList = dao.selectItemAtt(iCode);
		
		for(ItemAtt attVo : itemAttList) {
			if(attVo.getItemType().equals("map")) {
				campImg.put("map", attVo);
			} else if (attVo.getItemType().equals("WH")) {
				whList.add(attVo);
			}
			campImg.put("wh", whList);
		}
		
		mv.addObject("vo", vo);
		mv.addObject("campImg", campImg);
		mv.addObject("categoryList", categoryList);
		mv.addObject("itemOptionList", itemOptionList);
		mv.addObject("inc", "./partner_camp_modify.jsp");
		mv.setViewName("./admin/partner/partner_main");
		
		return mv;
	}
	@RequestMapping("partner_input_camp")
	public ModelAndView partnerInputR(ItemVo vo) {
		ModelAndView mv = new ModelAndView();
		dao.insertItem(vo);
		
		mv.addObject("inc", "./partner_site_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_input_filter")
	public void partnerInputFilter(HttpServletRequest req,
								   @RequestParam(value="cFilter", required=false) String[] filterArr,
							   	   @RequestParam(value="cTag", required=false) String[] tagArr,
								   @RequestParam(value="optItem", required=false) String[] optItemArr,
								   @RequestParam(value="price", required=false) String[] priceArr) throws NumberFormatException {
		List<CategoryVo> filterList = new ArrayList<CategoryVo>();
		List<CategoryVo> tagList = new ArrayList<CategoryVo>();
		List<ItemOptionVo> optionList = new ArrayList<ItemOptionVo>();
		
		int itemCode = Integer.parseInt(req.getParameter("itemCode"));
		
		if(filterArr != null) {
			for(String f : filterArr) {
				CategoryVo cVo = new CategoryVo();
				cVo.setItemCode(itemCode);
				cVo.setcFilter(f);
				cVo.setcTag("");
				filterList.add(cVo);
			}
			dao.insertCategory(filterList);
		}
		if(tagArr != null) {
			for(String f : tagArr) {
				CategoryVo cVo = new CategoryVo();
				cVo.setItemCode(itemCode);
				cVo.setcTag(f);
				cVo.setcFilter("");
				tagList.add(cVo);
			}
			dao.insertCategory(tagList);
		}
		if(optItemArr != null && priceArr != null) {
			for(int i = 0; i < optItemArr.length; i++) {				
				ItemOptionVo iVo = new ItemOptionVo();
				iVo.setItemCode(itemCode);
				iVo.setOptItem(optItemArr[i]);
				iVo.setPrice(Integer.parseInt(priceArr[i]));
				
				optionList.add(iVo);
			}
			dao.insertItemOption(optionList);
		}
	
		
		
						
	}
	
	@RequestMapping("camp_input_att")
	public void partnerInputCampAtt(@RequestParam(value="mapImg", required=false) List<MultipartFile> mapImg,
									@RequestParam(value="campImg", required=false) List<MultipartFile> campImgList,
									HttpServletRequest req) throws NumberFormatException {		
		int itemCode = Integer.parseInt(req.getParameter("itemCode"));
		
		List<ItemAtt> mapAtt = fc.uploadCamp(mapImg, itemCode, 0, "map");
		List<ItemAtt> campAtt = fc.uploadCamp(campImgList, itemCode, 0, "camp");
		
		dao.insertItemAtt(mapAtt);
		dao.insertItemAtt(campAtt);
	}
	
	@RequestMapping("partner_input_camp_hitory")
	public int partnerInputCampHistory(ItemVo vo) {
		int hisItemSno = 0;
		hisItemSno = dao.insertItemHistory(vo);		
		
		return hisItemSno;
	}
	
	@RequestMapping("partner_input_filter_hitory")
	public void partnerInputFilterHistory(HttpServletRequest req,
			   @RequestParam(value="cFilter", required=false) String[] filterArr,
		   	   @RequestParam(value="cTag", required=false) String[] tagArr,
			   @RequestParam(value="optItem", required=false) String[] optItemArr,
			   @RequestParam(value="price", required=false) String[] priceArr) throws NumberFormatException {
		List<CategoryVo> filterList = new ArrayList<CategoryVo>();
		List<CategoryVo> tagList = new ArrayList<CategoryVo>();
		List<ItemOptionVo> optionList = new ArrayList<ItemOptionVo>();
		int itemCode = Integer.parseInt(req.getParameter("itemCode"));
		int hisItemSno = Integer.parseInt(req.getParameter("hisItemSno"));
		
		if(filterArr != null) {
			for(String f : filterArr) {
				CategoryVo cVo = new CategoryVo();
				cVo.setItemCode(itemCode);
				cVo.setHisItemSno(hisItemSno);
				cVo.setcFilter(f);
				cVo.setcTag("");
				filterList.add(cVo);
			}
			dao.insertCategoryHistory(filterList);
		}
		if(tagArr != null) {
			for(String f : tagArr) {
				CategoryVo cVo = new CategoryVo();
				cVo.setItemCode(itemCode);
				cVo.setHisItemSno(hisItemSno);
				cVo.setcTag(f);
				cVo.setcFilter("");
				tagList.add(cVo);
			}
			dao.insertCategoryHistory(tagList);
		}
		if(optItemArr != null && priceArr != null) {
			for(int i = 0; i < optItemArr.length; i++) {				
				ItemOptionVo iVo = new ItemOptionVo();
				iVo.setItemCode(itemCode);
				iVo.setHisItemSno(hisItemSno);
				iVo.setOptItem(optItemArr[i]);
				iVo.setPrice(Integer.parseInt(priceArr[i]));
				optionList.add(iVo);
			}
			dao.insertItemOptionHistory(optionList);
		}
		
		
		
						
	}
			
	@RequestMapping("camp_input_att_history")
	public ModelAndView partnerInputCampAttHistory(@RequestParam(value="mapImg", required=false) List<MultipartFile> mapImg,
												   @RequestParam(value="campImg", required=false) List<MultipartFile> campImgList,
												   HttpServletRequest req) throws NumberFormatException {		
		ModelAndView mv = new ModelAndView();
		int itemCode = Integer.parseInt(req.getParameter("itemCode"));
		int hisItemSno = Integer.parseInt(req.getParameter("hisItemSno"));
		if(mapImg.get(0).getSize() != 0) {
			List<ItemAtt> mapAtt = fc.uploadCamp(mapImg, itemCode, hisItemSno, "map");
			dao.insertItemAttHistory(mapAtt);
		}
		if(campImgList.get(0).getSize() != 0) {
			List<ItemAtt> campAtt = fc.uploadCamp(campImgList, itemCode, hisItemSno, "camp");
			dao.insertItemAttHistory(campAtt);			
		}

		mv.addObject("inc", "./partner_site_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	// 사이트
	@RequestMapping("partner_site_input")
	public ModelAndView partnerSiteInput(String itemCode) throws NumberFormatException{
		ModelAndView mv = new ModelAndView();
		int code = 0;
		if(itemCode != null) {
			code = Integer.parseInt(itemCode);
		} else {
			code = 0;
		}
		List<SiteVo> list = dao.selectSiteList(code);
		
		mv.addObject("list", list);
		mv.addObject("inc", "./partner_site_input.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_input_site")
	public void partnerSiteInputR(SiteVo vo) {
		dao.insertSite(vo);
	}
	@RequestMapping("partner_input_site_history")
	public int partnerSiteInputHistory(SiteVo vo) {
		int hisDetailSno = dao.insertSiteHistory(vo);
		return hisDetailSno;
	}
	@RequestMapping("site_input_att")
	public void partnerInputCampAtt(@RequestParam(value="siteImg", required=false) List<MultipartFile> siteImg,
									@RequestParam(value="itemCode") String iCode,
									@RequestParam(value="siteName2") String siteName) throws NumberFormatException {		
		int itemCode = Integer.parseInt(iCode);
		List<ItemAtt> mapAtt = fc.uploadCamp(siteImg, itemCode, 0, siteName);
		
		dao.insertSiteAtt(mapAtt);
	}
	@RequestMapping("site_input_att_history")
	public void partnerInputCampAttHistory(@RequestParam(value="siteImg", required=false) List<MultipartFile> siteImg,
									@RequestParam(value="itemCode") String iCode,
									@RequestParam(value="siteName2") String siteName,
									@RequestParam(value="hisDetailSno") String hisSno) throws NumberFormatException {		
		int itemCode = Integer.parseInt(iCode);
		int HisDetailSno = Integer.parseInt(hisSno);
		if(siteImg.get(0).getSize() != 0) {
			List<ItemAtt> mapAtt = fc.uploadCamp(siteImg, itemCode, HisDetailSno, siteName);
			dao.insertSiteAttHistory(mapAtt);			
		}
	}
	
	@RequestMapping("site_select")
	public ModelAndView siteSelect(String siteCode, String itemCode) {
		ModelAndView mv = new ModelAndView();
		int sCode = Integer.parseInt(siteCode);
		int iCode = Integer.parseInt(itemCode);
		SiteVo vo = new SiteVo();
		List<ItemAtt> siteImgList = null;
		
		vo.setSiteCode(sCode);
		vo.setItemCode(iCode);
		
		SiteVo sVo = dao.selectSite(vo);
		siteImgList = dao.selectSiteImg(sVo);
		
		mv.addObject("vo", sVo);
		mv.addObject("siteImgList", siteImgList);
		mv.setViewName("./admin/partner/partner_site_modify");
		return mv;
	}
	
	@RequestMapping("partner_camp_view")
	public ModelAndView partnerCampView(String hisItemSno, String stateR, String sno) throws NumberFormatException {
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
		mv.addObject("inc", "./partner_camp_view.jsp");
		mv.setViewName("./admin/partner/partner_main");
		
		return mv;
	}

	@RequestMapping("partner_site_view")
	public ModelAndView partnerSiteView(String hisDetailSno, String stateR, String sno) throws NumberFormatException {
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
		mv.addObject("state", stateR);
		mv.addObject("inc", "./partner_site_view.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	// 예약내역
	@RequestMapping("partner_order_calendar")
	public Map<String, Object> partnerOrderCalendar(String itemCode, String dateStr ) {
		Map<String, Object> map = new HashMap<String, Object>();
		int iCode = Integer.parseInt(itemCode);
		List<PartnerOrderVo> orderList = new ArrayList<PartnerOrderVo>();
		orderList = orderDao.selectCalendar(iCode, dateStr);
		
		for(PartnerOrderVo vo : orderList) {
			map.put(String.valueOf(vo.getOrderCode()), vo);
		}
		
		return map;
	}
	
	@RequestMapping("partner_show_reservation_list")
	public ModelAndView partnerShowReservationList(PartnerOrderPage page) {
		ModelAndView mv = new ModelAndView();
		List<PartnerOrderVo> dateList = new ArrayList<PartnerOrderVo>();
		dateList = orderDao.selectDate(page);
		mv.addObject("page", page);
		mv.addObject("dateList", dateList);
		mv.setViewName("./admin/partner/partner_reservation_temp_list");
		return mv;
	}
	
	@RequestMapping("partner_reservation_detail")
	public ModelAndView partnerReservationDetail(String orderCode) throws NumberFormatException{
		ModelAndView mv = new ModelAndView();
		int oCode = Integer.parseInt(orderCode);
		PartnerOrderVo vo = orderDao.selectOrderDetail(oCode);
		mv.addObject("vo", vo);
		mv.addObject("inc", "./partner_reservation_detail.jsp");
		mv.setViewName("./admin/partner/partner_main");
		return mv;
	}
	
	@RequestMapping("partner_reservation_confirm")
	public boolean partnerReservationConfirm(PartnerOrderVo vo) throws NumberFormatException{
		boolean result = false;
		result = orderDao.orderConfirm(vo.getOrderCode());
		orderDao.sendEmail(vo, "approve"); // 예약 확정 메일 
		
		return result;
	}
	
	@RequestMapping("partner_reservation_cancel")
	public boolean partnerReservationCancel(PartnerOrderVo vo) throws NumberFormatException{
		boolean result = false;
		result = orderDao.orderCancel(vo.getOrderCode());
		orderDao.sendEmail(vo, "reject"); // 예약 취소 메일 
		
		return result;
	}
	
	@RequestMapping("partner_sales_graph")
	public Map<String, Object> partnerSalesGraph(SalesVo sVo){
		List<SalesVo> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		list = orderDao.selectSales(sVo);
			
		for(SalesVo vo : list) {
			map.put(vo.getDateOrder(), vo);	// map구조, 키를 날짜로 넣음
		}			

		return map;
	}
	
	@RequestMapping("partner_sales_month_graph")
	public Map<String, Object> partnerSalesMonthGraph(SalesVo sVo){
		List<SalesVo> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		list = orderDao.selectMonthSales(sVo);
		
		for(SalesVo vo : list) {
			map.put(vo.getYearMonth(), vo);	// map구조, 연월를 키로 넣음
		}			
		return map;
	}
}
