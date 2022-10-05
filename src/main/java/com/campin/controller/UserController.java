package com.campin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.campin.user.PageSearch;
import com.campin.user.PageSearchDetail;
import com.campin.user.UserSearchService;
import com.campin.user.UserSearchVo;
import com.campin.user.UserMembershipService;
import com.campin.user.UserMembershipVo;
import com.campin.user.UserSearchItemAttVo;
import com.campin.user.UserSearchItemDetailVo;
import com.campin.user.UserSearchItemVo;
import com.campin.user.UserSearchQnaVo;
import com.campin.user.UserSearchSendReservationVo;
import com.campin.user.UserQnaService;
import com.campin.user.UserQnaVo;
import com.campin.user.UserReservationService;
import com.campin.user.UserReservationVo;
import com.campin.user.UserSearchDetailQnaVo;
import com.campin.user.UserSearchDetailReviewVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campin.user.KakaoLoginAPI;
import com.campin.user.Kakao_Login_OAuthToken;
import com.campin.user.Kakao_Login_Profile;
//import com.campin.user.Naver_Login_BO;
import com.campin.user.Naver_Login_OAuthToken;
import com.campin.user.Naver_Login_Profile;
import com.campin.user.Naver_Login_Profile_Response;
import com.campin.user.UserWishlistService;
import com.campin.user.UserWishlistVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.scribejava.core.model.OAuth2AccessToken;



@RestController
public class UserController {
	@Autowired
	UserQnaService uqdao;
	@Autowired
	UserMembershipService memberdao;
	@Autowired
	UserSearchService searchdao;
	@Autowired
	UserReservationService urdao;
	// 수경 추가
	@Autowired
	UserWishlistService wishdao;
	// 수경 추가
	KakaoLoginAPI kakaologin = new KakaoLoginAPI();
//	// 수경 추가
//	@Autowired
//	private Naver_Login_BO naverlogin;
//	private String apiResult = null;
//	
//	// 수경 추가
//	@Autowired 
//	public void setNaver_Login_BO (Naver_Login_BO naverlogin) {
//		this.naverlogin = naverlogin; 
//	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("./user/user_index");
		
		return mv;
	}
	
	
	@RequestMapping("user_mypage")
	public ModelAndView user_mypage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_reservation_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	/*
	@RequestMapping("user_modify")
	public ModelAndView user_modify() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_modify.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}*/
	/*
	@RequestMapping("user_pwd_modify")
	public ModelAndView user_pwd_modify() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_pwd_modify.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}*/
	
	@RequestMapping("user_remove_account")
	public ModelAndView user_remove_account(UserMembershipVo vo) {
		ModelAndView mv = new ModelAndView();
		String socialJoin = memberdao.findOutIfSocial(vo);
		
		if(socialJoin.equals("n")) {
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_remove_account.jsp");
			mv.setViewName("user/user_search");
		} else if(socialJoin.equals("y")) {
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_remove_account_kakao.jsp");
			mv.setViewName("user/user_search");
		}
		return mv;
	}
	/*
	@RequestMapping("user_wishlist")
	public ModelAndView user_wishlist() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_wishlist.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}*/
	
	@RequestMapping("user_reservation_list")
	public ModelAndView user_reservation_list(int userCode) {
		ModelAndView mv = new ModelAndView();
		//System.out.println("유저코드"+userCode);
		List<UserReservationVo> listR = urdao.selectR(userCode);
		//List<UserReservationVo> listP = urdao.selectP(itemCode);
		
		//mv.addObject("listP",listP);
		mv.addObject("listR",listR);
		
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_reservation_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	@RequestMapping("user_qna_list")
	public ModelAndView user_qna_list(int userCode) {
		ModelAndView mv = new ModelAndView();
		
		List<UserQnaVo> listQ = uqdao.selectQ(userCode);
		List<UserQnaVo> listA = uqdao.selectA(userCode);
		
		for(UserQnaVo voQ : listQ) {
			for(UserQnaVo voA : listA) {
				if(voQ.getGrp() == voA.getGrp()) {
					voQ.setAnswer(voA.getDoc());
					break;
				}
			}
		}
		////System.out.println(userCode);
		mv.addObject("listQ",listQ);
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_qna_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	@RequestMapping("user_review_list")
	public ModelAndView user_review_list(int userCode) {
		ModelAndView mv = new ModelAndView();
		//System.out.println("유저코드"+userCode);
		List<UserReservationVo> listR = urdao.selectReview(userCode);
		List<UserReservationVo> listRA = urdao.selectRAnswer(userCode);
		
		for(UserReservationVo voR : listR) {
			for(UserReservationVo voRA : listRA) {
				if(voR.getOrderCode() == voRA.getOrderCode()) {
					voR.setRAnswer(voRA.getDoc());
					break;
				}
			}
		}
		
		mv.addObject("listR",listR);
		
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_review_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	//혠수정 
	//user_reservation
	@RequestMapping("user_reservation")
	public void user_reservation(UserReservationVo vo) {
		urdao.insertOrders(vo);
	}
	
	//혠추가 
	@RequestMapping("user_next_reservation")
	public ModelAndView user_reservation(String orderCode2, int itemCode) {
		ModelAndView mv = new ModelAndView();
		//System.out.println(itemCode);
		UserReservationVo vo = urdao.nextReservation(orderCode2);
		UserReservationVo vo1 = urdao.nextReservation2(itemCode);
		mv.addObject("vo1",vo1);
		mv.addObject("vo",vo);
		mv.addObject("inc","user_reservation.jsp");	
		mv.setViewName("user/user_search");
		return mv;
	}
	

	@RequestMapping("user_reservation_detail")
	public ModelAndView user_reservation_detail() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_reservation_detail.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_search")
	public ModelAndView user_search(PageSearch page) {
		
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		
		try {
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setSearchState("search");
			page.setInput_search("");
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			
			/*
			//검색 조건
			conditionList = searchdao.selectCondition(page);
			
			
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
			
			
			//대표이미지 랜덤 돌리기
			/*int minCampCnt = 5;	//campin 사진 등록 시 최소 갯수 
			double rnd = Math.random();
			int resultRnd = (int)(rnd * minCampCnt);
			//System.out.println("랜덤Max : " + resultRnd);
			//System.out.println("랜덤 : " + resultRnd);
			
			
			
			//사진 찾을때 sysFile로 찾을 것 -> 이름 = sysFile
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
			
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
				
				list.add(vo);
			}
			
			
			
			*/
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_search 조건,데이터삽입 예외처리___");
			ex.printStackTrace();
		}
	
		//페이지
		/*page = searchdao.getPage();*/
		
		//mv.addObject("list", list);
		mv.addObject("page", page);
		//************
		
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	
	
	@RequestMapping("user_searchOther")
	public ModelAndView user_searchOther(PageSearch page) {
		
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		
		try {
			System.out.println("page.getChildCount : " + page.getChildCount());
			System.out.println("page.getAdultCount : " + page.getAdultCount());
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setSearchState("searchOther");
			page.setInput_search("");
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchOther 조건,데이터삽입 예외처리___");
			ex.printStackTrace();
		}
		mv.addObject("page", page);
		
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_searchNotDataView")
	public ModelAndView user_searchNotDataView(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		
		try {
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setSearchState("search_temp_nodata");
			page.setInput_search(page.getSearchbar2());
			page.setSearchbar(page.getSearchbar2());
			page.setTagSearch(page.getTagSave2());
			page.setTagSave(page.getTagSave2());
			
			//데이터가 없다. 임의로 넣어서 보여주자!
			page.setRegion("전체");
			
			page.setCheck_in(getToday());
			page.setCheck_in(getNextday());
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//검색 조건
			conditionList = searchdao.selectConditionFirst(page);
			
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
						
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchNotDataView 예외처리___");
			ex.printStackTrace();
		}
	
		//페이지
		/*page = searchdao.getPage();*/
		mv.addObject("list", list);
		mv.addObject("page", page);
		//************
		
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search");
		
		return mv;
	}
	
	//희찬
	@RequestMapping("user_searchFirst")
	public ModelAndView user_searchFirst(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
			
		try {
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setSearchState("search_temp");
			page.setInput_search(page.getSearchbar2());
			page.setSearchbar(page.getSearchbar2());
			page.setTagSearch(page.getTagSave2());
			page.setTagSave(page.getTagSave2());
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//검색 조건
			conditionList = searchdao.selectConditionFirst(page);
			
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
			
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchFirst 예외처리___");
			ex.printStackTrace();
		}
			
		//페이지
		/*page = searchdao.getPage();*/
				
		mv.addObject("list", list);
		mv.addObject("page", page);
		//************
				
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search_temp");
		return mv;
	}
	
	//희찬
	@RequestMapping("user_searchOption_filter")
	public ModelAndView user_searchOption_filter(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
			
		try {
			////System.out.println("getTagSave : " + page.getTagSave());
			////System.out.println("getTagSave2 : " + page.getTagSave2());
			////System.out.println("getTagSearch : " + page.getTagSearch());
			////System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setSearchState("search_temp");
			page.setSearchbar(page.getInput_search());
			
			
			page.setTagSave2(page.getTagSearch());
			page.setTagSave(page.getTagSearch());
			
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//먼저 체크된 태그가 있을 때,
			//page.setTagSave(page.getTagSearch());
			//page.setTagSave2(page.getTagSearch());
			
			////System.out.println("getTagSearch : " + page.getTagSearch());
			////System.out.println("getTagSave : " + page.getTagSave());
			////System.out.println("getTagSave2 : " + page.getTagSave2());
			////System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			
			////System.out.println("getTagSearch() : " + page.getTagSearch());
			////System.out.println("checkfilter() : " + page.getCheckfilter());
			//검색 조건
			conditionList = searchdao.selectConditionOption_Filter(page);
			
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
					
					
					
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchOption_filter 예외처리___");
			ex.printStackTrace();
		}
				
		//페이지
		/*page = searchdao.getPage();*/
				
		mv.addObject("list", list);
		mv.addObject("page", page);
		//************
				
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search_temp");
		return mv;
	}
		
	//희찬
	@RequestMapping("user_searchOption_tag")
	public ModelAndView user_searchOption_tag(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
			
		try {
			////System.out.println("getTagSave : " + page.getTagSave());
			////System.out.println("getTagSave2 : " + page.getTagSave2());
			////System.out.println("getTagSearch : " + page.getTagSearch());
			////System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setSearchState("search_temp");
			page.setSearchbar(page.getInput_search());
			page.setTagSave(page.getTagSearch());
			page.setTagSave2(page.getTagSearch());
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			////System.out.println("checkfilter() : " + page.getCheckfilter());
			
			////System.out.println("getTagSave : " + page.getTagSave());
			////System.out.println("getTagSave2 : " + page.getTagSave2());
			////System.out.println("getTagSearch : " + page.getTagSearch());
			////System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			
			//검색 조건
			conditionList = searchdao.selectConditionOption_Tag(page);
			
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
					
			
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchOption_tag 예외처리___");
			ex.printStackTrace();
		}
			
		//페이지
		/*page = searchdao.getPage();*/
				
		mv.addObject("list", list);
		mv.addObject("page", page);
		//************
				
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search_temp");
		return mv;
	}

	//희찬
	@RequestMapping("user_search_IndexTagClick")
	public ModelAndView user_search_IndexTagClick(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		List<Integer> tagList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		try {
			page.setTagSave(page.getTagSearch());
			page.setSearchState("search");
			page.setInput_search("");
			
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//검색 조건
			tagList = searchdao.selectConditionTag(page);
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(tagList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(tagList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(tagList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(tagList);
					
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < tagList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(tagList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_search_IndexTagClick 예외처리___");
			ex.printStackTrace();
		}
		
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	//희찬
	@RequestMapping("user_search_tagClick")
	public ModelAndView user_search_tagClick(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		List<Integer> tagList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		try {
			page.setSearchState("search_temp");
			
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			
			//검색 조건
			tagList = searchdao.selectConditionTag(page);
			
			
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(tagList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(tagList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(tagList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(tagList);
			
			
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
			
			//가져온 정보 넣기
			for(int i = 0; i < tagList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(tagList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
				
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_search_tagClick 조건,데이터삽입 예외처리___");
			ex.printStackTrace();
		}
		
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search_temp");
		return mv;
	}

	//희찬
	@RequestMapping("user_searchOption_Go")
	public ModelAndView user_searchOption_Go(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
			
		try {
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setSearchState("search_temp");
			page.setSearchbar(page.getInput_search2());
			page.setInput_search(page.getInput_search2());
			
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			////System.out.println("getTagSearch() : " + page.getTagSearch());
			////System.out.println("checkfilter() : " + page.getCheckfilter());
			//검색 조건
			conditionList = searchdao.selectConditionOption_Tag(page);
			
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
					
					
					//대표이미지 랜덤 돌리기
					/*int minCampCnt = 5;	//campin 사진 등록 시 최소 갯수 
					double rnd = Math.random();
					int resultRnd = (int)(rnd * minCampCnt);
					//System.out.println("랜덤Max : " + resultRnd);
					//System.out.println("랜덤 : " + resultRnd);*/
					//사진 찾을때 sysFile로 찾을 것 -> 이름 = sysFile
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchOption_Go 예외처리___");
			ex.printStackTrace();
		}
			
		//페이지
		/*page = searchdao.getPage();*/
				
		mv.addObject("list", list);
		mv.addObject("page", page);
		//************
				
		//mv.addObject("inc", "./user_search_temp.jsp");
		mv.setViewName("user/user_search_temp");
		return mv;
		
	}
	
	//희찬 새창 open
	@RequestMapping("user_search_detail_qna_input")
	public ModelAndView user_search_detail_qna_input() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("user/user_qna_input");
		return mv;
	}
	//희찬 input Data Save
	@RequestMapping("user_search_qna_input_save")
	public void user_search_qna_input_save(UserSearchQnaVo vo) {
		//System.out.println("vo.getPwdCheck() : " + vo.getPwdCheck());
		try {
			searchdao.InsertQna(vo);
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_search_detail_qna_input 예외처리___");
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	//희찬 detailPage site load
	@RequestMapping("user_search_detail_site_list")
	public ModelAndView user_search_detail_site_list(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		List<UserSearchItemDetailVo> list = null;
		int sitecnt = 0;
		
		list = searchdao.selectDetailCampSite(page);
		
		String mapName = searchdao.selectItemAddMap(page.getDetailItemCode());
		
		sitecnt = searchdao.selectSiteCnt(page.getDetailItemCode());
		
		System.out.println("sitecnt : " + sitecnt);
		mv.addObject("sitecnt", sitecnt);
		mv.addObject("mapName", mapName);
		mv.addObject("page", page);
		mv.addObject("list", list);
		
		mv.setViewName("user/user_search_detail_site_list");
		return mv;
	}
	//희찬 detailPage review load
	@RequestMapping("user_search_detail_review_list")
	public ModelAndView user_search_detail_review_list(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		List<UserSearchDetailReviewVo> list = null;
		
		//DB에서 review 데이터(seq가 0인것만) 가져와서 뿌리기
		//클릭시 State의 상태에 따라 다르게 toggle 되어야 함.
		//1) state : 미답변 -> Q만 View
		//2) state : 답변완료 -> Q,A 모두 view
		
		list = searchdao.selectDetailReview(page);
		
		//별점 평균
		double star = searchdao.selectReviewStar(page);
		DecimalFormat form = new DecimalFormat("#.##");
	    String str = form.format(star);
		//리뷰 갯수 ()
		int cnt = searchdao.selectReviewCnt(page);
		
		mv.addObject("reviewStar", str);
		mv.addObject("reviewCnt", cnt);
		mv.addObject("page", page);
		mv.addObject("list", list);
		
		mv.setViewName("user/user_search_detail_review_list");
		return mv;
	}
	//희찬 detailPage qna load
	@RequestMapping("user_search_detail_qna_list")
	public ModelAndView user_search_detail_qna_list(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		List<UserSearchDetailQnaVo> list = null;
		
		
		//Page
		//게시글 10개로 한정
		//검색 - 제목만 가능하도록
		
		//view에 넣을 문의글(Q + A) 가져오기
		list = searchdao.selectDetailQna(page);
		
		
		//게시판형식
		//DB에서 board 데이터 가져와서 뿌리기
		//클릭시 State의 상태에 따라 다르게 toggle 되어야 함.
		//1) state : 답변대기 -> Q만 View
		//2) state : 답변완료 -> Q,A 모두 view
		
		mv.addObject("page", page);
		mv.addObject("list", list);
		mv.setViewName("user/user_search_detail_qna_list");
		return mv;
	}
	//희찬 detailPage fixed load
	@RequestMapping("user_search_detail_fixed_reservation")
	public ModelAndView user_search_detail_fixed_reservation(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		List<UserSearchItemDetailVo> list = null;
		
		//System.out.println("test1 : " + page.getCheckInSave());
		//System.out.println("test2 : " + page.getCheckOutSave());
		
		//인원, 예약날짜 비교해서 list 다시 추출
		list = searchdao.selectDetailSiteCondition(page);
		//System.out.println("list_size : " + list.size());
		
		
		mv.addObject("page", page);
		mv.addObject("list", list);
		
		mv.setViewName("user/user_search_detail_fixed_reservation");
		return mv;
	}
	//희찬 detailPage site Img load
	@RequestMapping("user_search_detail_site_img")
	public ModelAndView user_search_detail_site_img(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		List<UserSearchItemAttVo> list = null;
		
		//페이지 가져오기
		list = searchdao.selectSiteImage(page);
		for(int i=0;i<list.size();i++) {
			System.out.println("test : " + list.get(i).getItemCode());
			System.out.println("test1 : " + list.get(i).getItemType());
			System.out.println("test2 : " + list.get(i).getSysFile());
			
		}
		
		
		mv.addObject("page", page);
		mv.addObject("list", list);
	
		mv.setViewName("user/user_search_detail_site_img");
		return mv;
	}
	
	
	
	@RequestMapping("user_find_id")
	public ModelAndView user_find_id() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "user_find_id.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	@RequestMapping("user_find_pwd")
	public ModelAndView user_find_pwd() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("inc", "user_find_pwd.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	// 수경 수정
		@RequestMapping("user_wishlist")
		public ModelAndView user_wishlist(int userCode) {
			ModelAndView mv = new ModelAndView();
			
			List<UserWishlistVo> list = wishdao.selectUser(userCode);
			
			mv.addObject("list", list);
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_wishlist.jsp");
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_delete_wish")
		public void user_delete_wish(HttpServletRequest req, HttpServletResponse resp) {
			UserWishlistVo vo = new UserWishlistVo();
			
			int userCode = Integer.parseInt(req.getParameter("userCode"));
			int itemCode = Integer.parseInt(req.getParameter("itemCode"));
			
			vo.setUserCode(userCode);
			vo.setItemCode(itemCode);
			////System.out.println("controller vo.userCode : "+vo.getUserCode());
			////System.out.println("controller vo.itemCode : "+vo.getItemCode());
			
			wishdao.deleteWish(vo);
		}
		
		// 수경 추가
		@RequestMapping("user_to_search_detail")
		public ModelAndView user_to_search_detail(int userCode, UserWishlistVo vo, PageSearchDetail page) {
			ModelAndView mv = new ModelAndView();
			if(vo.getState().equals("탈퇴요청") || vo.getState().equals("탈퇴승인") || vo.getState().equals("탈퇴완료")) {
				mv.addObject("msg", "해당 캠핑장은 업체 사정으로 인하여 탈퇴한 캠핑장입니다.");
				List<UserWishlistVo> list = wishdao.selectUser(userCode);
				mv.addObject("list", list);
				mv.addObject("inc","user_mypage.jsp");	
				mv.addObject("myInc","user_wishlist.jsp");
				mv.setViewName("user/user_search");
			}else {
				//희
				
				
				PageSearch page2 = NotDataGetPageData2(vo.getItemCode());
				
				mv.addObject("page", page2);
				mv.setViewName("./user/user_search");
			}
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_joinR")
		public ModelAndView user_joinR(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			memberdao.join(vo);
			
			//희찬
			PageSearch page = NotDataGetPageData();
			List<UserSearchVo> list = NotDataGetList();
			mv.addObject("page", page);
			mv.addObject("list", list);
			
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 수정
		@RequestMapping("user_modify")
		public ModelAndView user_modify(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			UserMembershipVo rVo = memberdao.selectOneProfile(vo.getId());
			
			mv.addObject("vo", rVo);
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_modify.jsp");
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_modifyR")
		public ModelAndView user_modifyR(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			memberdao.profile_modify(vo);
			
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_reservation_list.jsp");
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 수정
		@RequestMapping("user_pwd_modify")
		public ModelAndView user_pwd_modify(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			UserMembershipVo rVo = memberdao.selectOneProfile(vo.getId());
			
			mv.addObject("vo", rVo);
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_pwd_modify.jsp");
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_pwd_modifyR")
		public ModelAndView user_pwd_modifyR(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			//System.out.println("컨트롤러 pwd : "+vo.getPwd());
			memberdao.pwd_modify(vo);
			
			mv.addObject("inc","user_mypage.jsp");	
			mv.addObject("myInc","user_reservation_list.jsp");
			mv.setViewName("user/user_search");
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_login_kakao")
		public @ResponseBody ModelAndView user_login_kakao(String code, UserMembershipVo vo, HttpServletRequest req) { // @ResponseBody = Data를 리턴해주는 컨트롤러 함수
			ModelAndView mv = new ModelAndView();
			
			// POST방식으로 key=value 데이터를 카카오측에 요청
			RestTemplate rt = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
			
			// HttpBody 오브젝트 생성
 			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
 			params.add("grant_type","authorization_code");
 			params.add("client_id", "52a5ddfbec7726cdf5d3d2bd372751ad");
 			params.add("redirect_uri", "http://localhost:8282/user_login_kakao");
 			params.add("code", code);
			
 			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
 			HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
 			
 			// Http 요청하기(POST 방식으로), 그리고 response 변수의 응답을 받음 
 			ResponseEntity<String> response = rt.exchange(
 				"https://kauth.kakao.com/oauth/token",
 				HttpMethod.POST,
 				kakaoTokenRequest,
 				String.class
 			);
 			
 			ObjectMapper objectMapper = new ObjectMapper();
 			Kakao_Login_OAuthToken oauthToken = null;
 			
 			try {
				oauthToken = objectMapper.readValue(response.getBody(), Kakao_Login_OAuthToken.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
 			
 			//System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());
 			
 			// 토큰 받기 끝! 이번엔 프로필 받기
 			
 			RestTemplate rt2 = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
			headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
			
 			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
 			HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
 			
 			// Http 요청하기(POST 방식으로), 그리고 response 변수의 응답을 받음 
 			ResponseEntity<String> response2 = rt2.exchange(
 				"https://kapi.kakao.com/v2/user/me",
 				HttpMethod.POST,
 				kakaoProfileRequest2,
 				String.class
 			);
 			
 			ObjectMapper objectMapper2 = new ObjectMapper();
 			Kakao_Login_Profile kakaoProfile = null;
 			
 			try {
				kakaoProfile = objectMapper2.readValue(response2.getBody(), Kakao_Login_Profile.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
 			
 			// 카카오에서 받은 회원정보 
 			// (그 외 id, mName, birth(생년포함), phone, regDate가 필요함. gender는 m,f 형식으로 변환 필요)
 			//System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
 			//System.out.println("카카오 이메일 : "+kakaoProfile.getkakao_account().getEmail());
 			//System.out.println("카카오 회원 생일 : "+kakaoProfile.getkakao_account().getBirthday());
 			//System.out.println("카카오 회원 성별 : "+kakaoProfile.getkakao_account().getGender());
 			
 			//System.out.println("직접 생성한 캠핀 아이디 : "+"user"+kakaoProfile.getId());
 			UUID notUsedPassword = UUID.randomUUID();
 			//System.out.println("직접 생성한 캠핀 비밀번호 : "+notUsedPassword);
 			
 			// 가입자, 비가입자 체크해서 비가입자는 가입 처리, 가입자는 로그인 처리
 			int userCode = memberdao.findUserForKakao(kakaoProfile);
			
			if(userCode == 0) {
				//System.out.println("controller - 신규화원임. 회원가입으로 감.");
				memberdao.switchInfo(kakaoProfile, vo);
				
				// 카카오에서 가져오지 못 한 회원정보 추가로 기입하는 폼으로 가기 (필수 아니고 선택사항)
				mv.addObject("id",vo.getId());
				mv.addObject("inc", "./user_join_kakao.jsp");
				mv.setViewName("./user/user_search");
				
			}else {
				//System.out.println("controller - 기존회원임. 로그인으로 가야 함.");
				String id = kakaoProfile.getId();
				//System.out.println("controller - 자동로그인 id : "+id);
				memberdao.login_kakao2(id,req);
				mv.addObject("msg", "로그인 되었습니다. :)");
				
				
				//희찬
				PageSearch page = NotDataGetPageData();
				List<UserSearchVo> list = NotDataGetList();
				mv.addObject("page", page);
				mv.addObject("list", list);
				
				mv.setViewName("user/user_search");
			}
			return mv;
		}
		
		// 수경 추가
		@RequestMapping("user_join_kakaoR")
		public ModelAndView user_join_kakaoR(UserMembershipVo vo, HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			boolean b = memberdao.profile_modify_kakao(vo);
			//System.out.println("추가 회원정보까지 입력 완료됨. 자동 로그인 전!");
			
			// 바로 자동 로그인
			if(b = true) {
			memberdao.login_kakao1(vo, req);
			//System.out.println("자동 로그인 완료!");
			
			mv.addObject("msg", "축하합니다! CAMPIN 회원가입이 완료되었습니다. :)");
			
			//희찬
			PageSearch page = NotDataGetPageData();
			List<UserSearchVo> list = NotDataGetList();
			mv.addObject("page", page);
			mv.addObject("list", list);
			
			mv.setViewName("user/user_search");
			}
			return mv;
		}
		
		// 수경 추가
		@RequestMapping("user_join_kakaoRR")
		public ModelAndView user_join_kakaoRR(UserMembershipVo vo, HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			
			// 바로 자동 로그인
			memberdao.login_kakao1(vo, req);
			//System.out.println("자동 로그인 완료!");
			
			mv.addObject("msg", "축하합니다! CAMPIN 회원가입이 완료되었습니다. :)");
			
			//희찬
			PageSearch page = NotDataGetPageData();
			List<UserSearchVo> list = NotDataGetList();
			mv.addObject("page", page);
			mv.addObject("list", list);
			
			mv.setViewName("user/user_search");
			
			return mv;
		}
		
		// 수경 추가
		@RequestMapping("user_login_naver")
		public @ResponseBody ModelAndView user_login_naver (String code, String state, UserMembershipVo vo, HttpServletRequest req) throws JsonProcessingException {
			ModelAndView mv = new ModelAndView();
			
			// POST 방식으로 key=value 데이터를 네이버에 요청
			RestTemplate rt = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// HttpBody 오브젝트 생성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			
			// parameter로 전달할 속성들 추가
			params.add("grant_type", "authorization_code");
			params.add("client_id", "4jrL65cX4XhqT3wJJUZF");
			params.add("client_secret", "HLZo7jQfrA");
			params.add("code", code);
			params.add("state", state);
			
			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
			
			// token request url로 Http 요청 전송, 그리고 response 변수의 응답을 받음
			ResponseEntity<String> response = rt.exchange(
					"https://nid.naver.com/oauth2.0/token",
					HttpMethod.POST,
					naverTokenRequest,
					String.class
			);
			
			// ObjectMapper를 통해 NaverOAuthToken 객체로 매핑
			ObjectMapper objectMapper = new ObjectMapper();
			Naver_Login_OAuthToken naverToken = null;
			
			try {
				naverToken = objectMapper.readValue(response.getBody(), Naver_Login_OAuthToken.class);
			}catch(JsonMappingException e) {
				e.printStackTrace();
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
			
			//System.out.println("네이버 엑세스 토큰 : "+naverToken.getAccess_token());
			
			// 토큰 받기 끝! 이번엔 프로필 받기
			
			RestTemplate rt2 = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+naverToken.getAccess_token());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String,String>> naverProfileRequest2 = new HttpEntity<>(headers2);
			
			// Http 요청하고 response 변수의 응답 받음
			ResponseEntity<String> response2 = rt2.exchange(
					"https://openapi.naver.com/v1/nid/me",
					HttpMethod.POST,
					naverProfileRequest2,
					String.class
			);
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			//Naver_Login_Profile naverProfile = null;
			Naver_Login_Profile_Response naverProfileResponse = null;
			
			try {
				naverProfileResponse = objectMapper2.readValue(response2.getBody(), Naver_Login_Profile_Response.class);
			}catch(JsonMappingException e) {
				e.printStackTrace();
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
			
			// 네이버에서 받은 회원정보
			//System.out.println("네이버 아이디 : "+naverProfileResponse.getResponse().getId());
			//System.out.println("네이버 이메일 : "+naverProfileResponse.getResponse().getEmail());
			//System.out.println("네이버 이름 : "+naverProfileResponse.getResponse().getName());
			//System.out.println("네이버 생일 : "+naverProfileResponse.getResponse().getBirthday());
			//System.out.println("네이버 생년 : "+naverProfileResponse.getResponse().getBirthyear());
			//System.out.println("네이버 성별 : "+naverProfileResponse.getResponse().getGender());
			//System.out.println("네이버 휴대폰 : "+naverProfileResponse.getResponse().getMobile());
			
			UUID notUsedPassword = UUID.randomUUID();
			//System.out.println("직접 생성한 캠핀 비밀번호 : "+notUsedPassword);
			
			// 가입자, 비가입자 체크해서 비가입자는 가입 처리, 가입자는 로그인 처리
			int userCode = memberdao.findUserForNaver(naverProfileResponse);
			
			if(userCode == 0) {
				//System.out.println("controller - 신규화원임. 회원가입으로 감.");
				memberdao.switchInfo2(naverProfileResponse, vo);
				
				// 회원가입 후 자동 로그인
				String id = naverProfileResponse.getResponse().getId();
				//System.out.println("controller - 자동로그인 id : "+id);
				memberdao.login_naver(id,req);
				
				mv.addObject("id",vo.getId());
				mv.addObject("msg", "축하합니다! CAMPIN 회원가입이 완료되었습니다. :)");
				
				//희찬
				PageSearch page = NotDataGetPageData();
				List<UserSearchVo> list = NotDataGetList();
				mv.addObject("page", page);
				mv.addObject("list", list);
				
				mv.setViewName("user/user_search");
				
			}else {
				//System.out.println("controller - 기존회원임. 로그인으로 가야 함.");
				String id = naverProfileResponse.getResponse().getId();
				//System.out.println("controller - 자동로그인 id : "+id);
				memberdao.login_kakao2(id,req);
				mv.addObject("msg", "로그인 되었습니다. :)");
				
				//희찬
				PageSearch page = NotDataGetPageData();
				List<UserSearchVo> list = NotDataGetList();
				mv.addObject("page", page);
				mv.addObject("list", list);
				
				mv.setViewName("user/user_search");
			}
			return mv;
		}
		// 수경 수정
		@RequestMapping("user_loginR")
		public ModelAndView user_loginR(UserMembershipVo vo, HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			UserMembershipVo rVo = memberdao.login(vo, req);
			
			//String id = vo.getId();
			////System.out.println("로그인 된 아이디 : "+rVo.getId());
			
			if(rVo == null) { 
				mv.addObject("msg", "입력하신 정보는 올바르지 않은 회원 정보입니다. 아이디 또는 비밀번호를 확인 후 다시 입력해주시기 바랍니다.");
				mv.addObject("inc", "user_login.jsp");
				mv.setViewName("user/user_search");
			}else {
				mv.addObject("msg", "로그인 되었습니다. :)");
				//mv.addObject("inc", "user_search_temp.jsp");
				
				
				//희찬
				PageSearch page = NotDataGetPageData();
				List<UserSearchVo> list = NotDataGetList();
				mv.addObject("page", page);
				mv.addObject("list", list);
				
				mv.setViewName("user/user_search");
			}
			return mv;
		}
		// 수경 추가
			@RequestMapping("user_logout")
			public ModelAndView user_logout(HttpServletRequest req, HttpSession session) {
				ModelAndView mv = new ModelAndView();
				memberdao.logout(req);
				
				kakaologin.kakaoLogout((String)session.getAttribute("accessToken"));
				
				session.removeAttribute("accessToken");
				session.removeAttribute("id");
				session.removeAttribute("userCode");
				session.removeAttribute("mName");
				
				
				mv.setViewName("user/user_index");
				return mv;
			}
		// 수경 추가
		@RequestMapping("user_remove_accountR")
		public ModelAndView user_remove_accountR(UserMembershipVo vo, HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			
			String pwd = req.getParameter("pwd");
			String id = req.getParameter("id");
			boolean chkPwd = memberdao.pwdValidation(id, pwd);
			
			if(chkPwd) {
				mv.addObject("msg", "비밀번호가 일치하지 않아 탈퇴가 불가능합니다. 다시 입력해주시기 바랍니다.");
				mv.addObject("inc","user_mypage.jsp");	
				mv.addObject("myInc","user_remove_account.jsp");
				mv.setViewName("user/user_search");
			}else {
				//System.out.println("vo id : "+vo.getId());
				memberdao.remove_account(vo);
				//System.out.println("vo id : "+vo.getId());
				mv.addObject("msg", "회원탈퇴 처리가 완료되었습니다. 그동안 감사했습니다. :)");
				mv.setViewName("user/user_index");
			}
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_remove_account_kakaoR")
		public ModelAndView user_remove_account_kakaoR(UserMembershipVo vo, HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			
				memberdao.remove_account_kakao(vo);
				//System.out.println("vo id : "+vo.getId());
				mv.addObject("msg", "회원탈퇴 처리가 완료되었습니다. 그동안 감사했습니다. :)");
				mv.setViewName("user/user_index");
				
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_find_id_phone")
		public ModelAndView user_find_id_phone(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			String id = memberdao.findIdByPhone(vo);
			
			if(id == "" || id == null) {
				mv.addObject("msg", "입력하신 정보는 올바르지 않은 회원 정보입니다. 이름 또는 휴대폰 번호를 다시 확인 후 입력해주시기 바랍니다.");
				mv.addObject("inc", "user_find_id.jsp");
				mv.setViewName("user/user_search");
			}else {
				mv.addObject("id", id);
				mv.addObject("inc", "user_find_result.jsp");
				mv.setViewName("user/user_search");
			}
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_find_id_email")
		public ModelAndView user_find_id_email(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			String id = memberdao.findIdByEmail(vo);
			
			if(id == "" || id == null) {
				mv.addObject("msg", "입력하신 정보는 올바르지 않은 회원 정보입니다. 이름 또는 이메일 주소를 다시 확인 후 입력해주시기 바랍니다.");
				mv.addObject("inc", "user_find_id.jsp");
				mv.setViewName("user/user_search");
			}else {
				mv.addObject("id", id);
				mv.addObject("inc", "user_find_result.jsp");
				mv.setViewName("user/user_search");
			}
			return mv;
		}
		// 수경 추가
		@RequestMapping("user_find_temp_pwd")
		public ModelAndView user_find_temp_pwd(UserMembershipVo vo) {
			ModelAndView mv = new ModelAndView();
			String tempPwd = memberdao.tempPwd(vo);
			String alert = "";
			//System.out.println("일단 임시비번 생성 및 저장 성공 / 이메일 발송 not yet");
			alert = memberdao.sendEmail(vo, tempPwd);
			
//			mv.addObject("id", "");
//			mv.addObject("isEmail", "send");
//			mv.addObject("inc", "user_find_result.jsp");
//			mv.setViewName("user/user_search");
			
			if(alert == "email not sent") {
				mv.addObject("msg", "입력하신 정보는 올바르지 않은 회원 정보입니다. 아이디 또는 이메일 주소를 다시 확인 후 입력해주시기 바랍니다.");
				mv.addObject("inc", "user_find_pwd.jsp");
				mv.setViewName("user/user_search");
			} else if (alert == "email sent"){
				mv.addObject("id", "");
				mv.addObject("isEmail", "send");
				mv.addObject("inc", "user_find_result.jsp");
				mv.setViewName("user/user_search");
			} else {
				mv.addObject("msg", "이메일 발송 중 오류가 발생했습니다. 잠시 후 다시 시도해주시기 바랍니다.");
				mv.addObject("inc", "user_find_pwd.jsp");
				mv.setViewName("user/user_search");
			}
			return mv;
		}

		// 수경 추가
		@RequestMapping("user_idValidation")
		public void user_idValidation(HttpServletRequest req, HttpServletResponse resp) {
			String id = req.getParameter("id");		
			boolean idValidation = memberdao.idValdation(id);
			
			resp.setContentType("text/plain;charset=euc-kr");
			PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(idValidation) {
				out.print("validation");
			}else {
				out.print("good to go!");
			}
		}
		// 수경 추가
	@RequestMapping("user_emailValidation") // 회원가입 시
	public void user_emailValidation(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");		
		boolean emailValidation = memberdao.emailValidation(email);
		resp.setContentType("text/plain;charset=euc-kr");
		
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(emailValidation) {
			out.print("validation");
		}else {
			out.print("good to go!");
		}
	}
	// 수경 추가
	@RequestMapping("user_emailValidation2") // 프로필 수정 시
	public void user_emailValidation2(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");		
		resp.setContentType("text/plain;charset=euc-kr");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String chkEmail = memberdao.emailValidation2(email); 
			
		out.print(chkEmail);
	}
	// 수경 추가
	@RequestMapping("user_phoneValidation") // 회원가입 시
	public void user_phoneValidation(HttpServletRequest req, HttpServletResponse resp) {
		String phone = req.getParameter("phone");		
		boolean phoneValidation = memberdao.phoneValidation(phone);
		
		resp.setContentType("text/plain;charset=euc-kr");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		if(phoneValidation) {
			out.print("validation");
		}else {
			out.print("good to go!");
		}
	}
	// 수경 추가
	@RequestMapping("user_phoneValidation2") // 프로필 수정 시
	public void user_phoneValidation2(HttpServletRequest req, HttpServletResponse resp) {
		String phone = req.getParameter("phone");		
		resp.setContentType("text/plain;charset=euc-kr");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String chkPhone = memberdao.phoneValidation2(phone);
		out.print(chkPhone);
	}
	// 수경 추가
	@RequestMapping("user_pwdValidation")
	public void user_pwdValidation(HttpServletRequest req, HttpServletResponse resp) {
		String pwd = req.getParameter("oldPwd");
		String id = req.getParameter("id");
		boolean pwdValidation = memberdao.pwdValidation(id, pwd);
		resp.setContentType("text/plain;charset=euc-kr"); // 문자화
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(pwdValidation) {
			out.print("pwd not matched");
		}else {
			out.print("matched");
		}
	}
	
	
	
	
	
	
	@RequestMapping("user_index")
	public ModelAndView user_index() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("./user/user_index");
		return mv;
	}
	
	
	
	
	//희찬
	@RequestMapping("user_search_detail")
	public ModelAndView user_search_detail(PageSearchDetail page) {
		ModelAndView mv = new ModelAndView();
		UserSearchItemVo vo = new UserSearchItemVo();
		List<String> calist = null;
		List<String> resultlist = new ArrayList<String>();
		
		List<UserSearchItemAttVo> camplist = new ArrayList<UserSearchItemAttVo>();
		
		//캠핑장 HW 이미지 가져오기
		camplist = searchdao.selectCampHWImg(page);
		
		
		//itemCode로 해당 캠핑 정보 불러오기
		vo = searchdao.selectCampDetailInfo(page);
		
		//itemCode로 해당 캠핑 category 가져오기
		calist = searchdao.getCategory(page);
		
		for(int i=0;i<calist.size();i++) {
			if(!calist.get(i).isEmpty()) {
				if(calist.get(i).equals("화장실") || calist.get(i).equals("샤워실") ||
						calist.get(i).equals("매점") || calist.get(i).equals("카페") ||
						calist.get(i).equals("개수대") || calist.get(i).equals("와이파이") ||
						calist.get(i).equals("체험활동") || calist.get(i).equals("수영장") ||
						calist.get(i).equals("트램펄린") || calist.get(i).equals("산책로") ||
						calist.get(i).equals("장비대여") || calist.get(i).equals("반려동물") ||
						calist.get(i).equals("트레일러진입") || calist.get(i).equals("카라반진입") ||
						calist.get(i).equals("놀이시설")) {
					resultlist.add(calist.get(i));
				}
			}
		}
		
		
		
		mv.addObject("camplist", camplist);
		mv.addObject("list", resultlist);
		mv.addObject("vo", vo);
		mv.addObject("page", page);
		
		mv.setViewName("./user/user_search_detail");
		return mv;
	}
	
	@RequestMapping("user_searchOther_temp")
	public ModelAndView user_searchOther_temp(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		//희찬
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
			
		try {
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setSearchState("search_temp");
			page.setInput_search(page.getSearchbar2());
			page.setSearchbar(page.getSearchbar2());
			page.setTagSearch(page.getTagSave2());
			page.setTagSave(page.getTagSave2());
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//검색 조건
			conditionList = searchdao.selectCondition_OtherTemp(page);
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
						
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_user_searchFirst 예외처리___");
			ex.printStackTrace();
		}
				
		mv.addObject("list", list);
		mv.addObject("page", page);
		
		mv.setViewName("user/user_search_temp");
		return mv;
	}
	
	
	
	//detail page 이동
	//희찬
	@RequestMapping("user_search_detail_page")
	public ModelAndView user_search_detail_Page(PageSearch page) {
		ModelAndView mv = new ModelAndView();
		page.setSearchState("search_detail");
		
		
		
		
		
		mv.addObject("page", page);
		//mv.addObject("inc", "./user_search_detail.jsp");
		
		mv.setViewName("./user/user_search");
		return mv;
	}
	
	
	@RequestMapping("user_login")
	public ModelAndView user_login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./user_login.jsp");
		mv.setViewName("./user/user_search");
		
		return mv;
	}
	
	@RequestMapping("user_join")
	public ModelAndView user_join() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inc", "./user_join.jsp");
		mv.setViewName("./user/user_search");
		
		return mv;
	}
	
	//희찬
	@RequestMapping("user_reservation_input")
	public ModelAndView user_reservation_input(int userCode, int itemCode, int siteCode, int cUserCode, UserSearchSendReservationVo vo2) {
		ModelAndView mv = new ModelAndView();
		String siteCode2 = "";
		String cUserCode2 = "";
		
		siteCode2 = searchdao.selectFindSiteCode(vo2);
		vo2.setSiteCode(siteCode2);
		cUserCode2 = searchdao.selectFindcUserCode(vo2);
		vo2.setcUserCode(cUserCode2);
		String strItemCode = Integer.toString(itemCode);
		vo2.setItemCode(strItemCode);
		
		int icUserCode = Integer.parseInt(vo2.getcUserCode());
		int iSiteCode = Integer.parseInt(vo2.getSiteCode());
		
		UserReservationVo vo = urdao.selectReservation(userCode, itemCode);
		List<UserReservationVo> list = urdao.selectReservation2(itemCode, iSiteCode);
		UserReservationVo vo1 = urdao.selectReservation3(itemCode,iSiteCode,icUserCode);
		
		//System.out.println("userCode : " + userCode);
		//System.out.println("cUserCode : " + cUserCode);
		//System.out.println("itemCode : " + itemCode);
		//System.out.println("siteCode : " + siteCode);
		//System.out.println("vo1.ItemCode : " + vo1.getItemCode());
		//System.out.println("vo2.cUserCode : " + vo2.getcUserCode());
		//System.out.println("vo2.siteCode : " + vo2.getSiteCode());
		
		mv.addObject("vo1",vo1);
		mv.addObject("list",list);
		mv.addObject("vo",vo);
		
		mv.addObject("vo2",vo2);
		mv.addObject("inc", "./user_reservation_input.jsp");
		mv.setViewName("./user/user_search");
		return mv;
	}
	
	//혠
	@RequestMapping("user_save_qna")
	public void user_save_qna(String sno, String doc) throws NumberFormatException{
		UserQnaVo vo = new UserQnaVo();
		int no = Integer.parseInt(sno);
		vo.setSno(no);
		vo.setDoc(doc);
		
		uqdao.update(vo);
	}
	
	@RequestMapping("user_delete_qna")
	public ModelAndView user_delete_qna(int sno) throws NumberFormatException{
		ModelAndView mv = new ModelAndView();
		UserQnaVo vo = new UserQnaVo();
		int no = sno;
		vo.setSno(no);
		uqdao.delete(vo);
		
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_qna_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_input_review")
	public ModelAndView user_input_review(@RequestParam(value="oriFile2", required=false) List<MultipartFile> file, UserReservationVo vo) {
		ModelAndView mv = new ModelAndView();
		String uploadPath = "C:\\2022_02\\Campin\\src\\main\\resources\\static\\image\\camping\\"+vo.getItemCode()+"\\review\\";
		File Folder = null;
		UUID uuid = UUID.randomUUID();
		//System.out.println("test : " + vo.getItemCode());
		
		try {
			String oriFile = file.get(0).getOriginalFilename();
			String sysFile = String.format("%s-%s", uuid.getLeastSignificantBits(), oriFile);
			
			Folder = new File(uploadPath);
			if(!Folder.exists()) {
				try {
					Folder.mkdir();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			File f = new File(uploadPath + sysFile);
			file.get(0).transferTo(f);
			
			vo.setOriFile(oriFile);
			vo.setSysFile(sysFile);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		int orderCode = vo.getOrderCode();
		urdao.insert(vo);
		urdao.updateRS(orderCode);
		
		mv.addObject("vo",vo);
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_review_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_reservation_detail2")
	public ModelAndView user_reservation_detail(int orderCode) {
		ModelAndView mv = new ModelAndView();
		UserReservationVo vo = new UserReservationVo();
		//System.out.println("오더코드" + orderCode);
		vo = urdao.viewDetail(orderCode);
		mv.addObject("vo",vo);
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_reservation_detail.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_modify_reservation")
	public ModelAndView user_modify_reservation(int orderCode, String userCar, String pet, String doc) {
		ModelAndView mv = new ModelAndView();
		UserReservationVo vo = new UserReservationVo();
		UserReservationVo vo1 = new UserReservationVo();
		////System.out.println("오더코드" + orderCode);
		vo.setOrderCode(orderCode);
		vo.setUserCar(userCar);
		vo.setPet(pet);
		vo.setDoc(doc);
		////System.out.println(pet);
		////System.out.println("차량번호"+userCar);
		urdao.update(vo);
		vo1 = urdao.viewDetail(orderCode);
		mv.addObject("vo",vo1);
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_reservation_detail.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	@RequestMapping("user_cancel_reservation")
	public ModelAndView user_cancel_reservation(int orderCode, int userCode) {
	ModelAndView mv = new ModelAndView();
	UserReservationVo vo = new UserReservationVo();
	urdao.updateR(orderCode);
	mv.addObject("inc","user_mypage.jsp");	
	mv.addObject("myInc","user_reservation_list.jsp");
	mv.setViewName("user/user_search");
	return mv;
}
	
	@RequestMapping("user_modify_review")
	public void user_modify_review(@RequestParam(value="oriFile2", required=false) List<MultipartFile> file, UserReservationVo vo) {
		String uploadPath = "C:\\2022_02\\Campin\\src\\main\\resources\\static\\image\\camping\\"+vo.getItemCode()+"\\review\\";
		//String uploadPath = "/Users/hyen/My_workspace/Campin/src/main/resources/static/image/camping/"+vo.getItemCode()+"/review/";
		UUID uuid = UUID.randomUUID();
		//System.out.println("test : " +uploadPath+ vo.getSysFile());
		//System.out.println("item"+vo.getItemCode());
		try {
			String oriFile = file.get(0).getOriginalFilename();
			String sysFile = String.format("%s-%s", uuid.getLeastSignificantBits(), oriFile);
			
			File f1 = new File(uploadPath + vo.getSysFile());  //추후 경로 수정 
			if(f1.exists()) f1.delete();
			
			File f = new File(uploadPath + sysFile);
			file.get(0).transferTo(f);
			
			vo.setOriFile(oriFile);
			vo.setSysFile(sysFile);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		int orderCode = vo.getOrderCode();
		urdao.modifyR(vo);
	}
	
	@RequestMapping("user_delete_review")
	public ModelAndView user_delete_reveiw(int orderCode, int itemCode, String sysFile) {
		ModelAndView mv = new ModelAndView();
		String uploadPath = "C:\\2022-02\\Campin\\src\\main\\resources\\static\\image\\camping\\"+itemCode+"\\review\\";
		//String uploadPath = "/Users/hyen/My_workspace/Campin/src/main/resources/static/image/camping/"+itemCode+"/review/";
		
		File f1 = new File(uploadPath + sysFile);  //추후 경로 수정 
		if(f1.exists()) f1.delete();
		urdao.deleteR(orderCode);
		mv.addObject("inc","user_mypage.jsp");	
		mv.addObject("myInc","user_review_list.jsp");
		mv.setViewName("user/user_search");
		return mv;
	}
	
	
	
	//희찬
	@RequestMapping("user_search_wishlist_check_add")
	public void user_search_wishlist_check_add(int userCode, int detailItemCode) {
		UserWishlistVo vo = new UserWishlistVo();
		System.out.println("userCode : " + userCode);
		System.out.println("detailItemCode : " + detailItemCode);
		
		vo.setUserCode(userCode);
		vo.setItemCode(detailItemCode);
		
		searchdao.wishlistAdd(vo);
		//urdao.deleteR(orderCode);
		//mv.addObject("inc","user_mypage.jsp");	
		//mv.addObject("myInc","user_review_list.jsp");
		//mv.setViewName("user/user_search");
		//return mv;
	}
	
	//희찬
	@RequestMapping("user_search_wishlist_check_delete")
	public void user_search_wishlist_check_delete(int userCode, int detailItemCode) {		
		UserWishlistVo vo = new UserWishlistVo();
		vo.setUserCode(userCode);
		vo.setItemCode(detailItemCode);
		
		searchdao.wishlistDelete(vo);
	}
	
	
	//희찬
	public List<UserSearchVo> NotDataGetList(){
		PageSearch page = new PageSearch();
		List<Integer> conditionList = null;
		List<UserSearchItemVo> itemList = null;
		List<Integer> itemdetailList = null;
		List<List<UserSearchItemAttVo>> itemAttListArray = null;
		List<List<String>> categoryListArray = null;
		List<UserSearchItemAttVo> itemAttList = new ArrayList<UserSearchItemAttVo>();	//랜덤으로 돌린 이미지를 골라 list에 넣기
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();						//모든 데이터 넣은 후 .jsp에 넘겨줄 list
		
		try {
			//데이터가 없다. 임의로 넣어서 보여주자!
			String strIn = getToday();
			String strOut = getNextday();
			page.setRegion("전체");
			page.setCheck_in(strIn);
			page.setCheck_in(strOut);
			page.setCheckIn(strIn);
			page.setCheckOut(strOut);
			page.setAdultCount("0");
			page.setChildCount("0");
			
			
			page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
			page.setTagSearch("");
			page.setInput_search(page.getSearchbar2());
			page.setSearchbar(page.getSearchbar2());
			page.setTagSearch("");
			page.setTagSave("");
			
			//System.out.println("test : " + page.getCheckIn());
			
			//detail을 위한itemCode
			page.setDetailItemCode("");
			
			//검색 조건
			conditionList = searchdao.selectConditionFirst(page);
			
					
			//캠핑장 이름, 위치, 설명
			itemList = searchdao.selectSearchItem(conditionList);
			//가격
			itemdetailList = searchdao.selectSearchItemDetail(conditionList);
			//아이콘
			categoryListArray = searchdao.selectSearchCategory(conditionList);
			//대표이미지
			itemAttListArray = searchdao.selectSearchItemAtt(conditionList);
						
			for(int i = 0; i < itemAttListArray.size(); i ++) {
				UserSearchItemAttVo vo = new UserSearchItemAttVo();
				vo.setSysFile(itemAttListArray.get(i).get(0).getSysFile());			//무조건 첫번째 이미지를 대표 이미지로
				//vo.setItemType("WH");
				itemAttList.add(vo);
			}
					
			//가져온 정보 넣기
			for(int i = 0; i < conditionList.size(); i++) {
				UserSearchVo vo = new UserSearchVo();
				//캠핑장 이름, 위치, 설명
				vo.setiName(itemList.get(i).getiName());
				vo.setSido(itemList.get(i).getSido());
				vo.setInfoText(itemList.get(i).getInfoText());
				//가격
				vo.setPrice(itemdetailList.get(i));
				//필터 - 아이콘
				vo.setCateList(categoryListArray.get(i));
				//사이트이름
				//vo.setSiteCode(Integer.parseInt(itemAttList.get(i).getItemType()));
				//캠핑장코드
				vo.setItemCode(conditionList.get(i));
				//대표이미지
				vo.setBestCampImg("./image/camping/" + vo.getItemCode() + "/" + itemAttList.get(i).getSysFile());
						
				list.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserController_NotDataGetList 조건,데이터삽입 예외처리___");
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public PageSearch NotDataGetPageData() {
		PageSearch page = new PageSearch();
		
		//데이터가 없다. 임의로 넣어서 보여주자!
		String strIn = getToday();
		String strOut = getNextday();
		
		page.setRegion("전체");
		page.setCheck_in(strIn);
		page.setCheck_out(strOut);
		page.setAdultCount("0");
		page.setChildCount("0");
		page.setCheckIn(strIn);
		page.setCheckOut(strOut);
		
		page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
		page.setTagSearch("");
		
		page.setSearchState("search");
		
		page.setInput_search("");
		//detail을 위한itemCode
		page.setDetailItemCode("");
		
		
		
		return page;
	}
	
	public PageSearch NotDataGetPageData2(int itemCode) {
		PageSearch page = new PageSearch();
		
		//데이터가 없다. 임의로 넣어서 보여주자!
		String strIn = getToday();
		String strOut = getNextday();
		
		page.setRegion("전체");
		page.setCheck_in(strIn);
		page.setCheck_out(strOut);
		page.setAdultCount("0");
		page.setChildCount("0");
		page.setCheckIn(strIn);
		page.setCheckOut(strOut);
		
		page.setTotalCount(Integer.parseInt(page.getChildCount()) + Integer.parseInt(page.getAdultCount()));
		page.setTagSearch("");
		
		page.setSearchState("search_detail");
		
		page.setInput_search("");
		//detail을 위한itemCode
		page.setDetailItemCode(Integer.toString(itemCode));
		
		
		
		return page;
	}
	
	//희찬
	@RequestMapping("user_search_findwishlist")
	public List<Integer> user_search_findwishlist(String userCode) {
		List<Integer> list = null;
		
		if(!userCode.isEmpty()){
			int ucode = Integer.parseInt(userCode);
			list = searchdao.findwishlist(ucode);
		}
		
		return list;
	}
	//희찬
	@RequestMapping("user_search_searchwishlist")
	public boolean user_search_searchwishlist(String userCode) {
		boolean b = false;
		
		if(!userCode.isEmpty()){
			int ucode = Integer.parseInt(userCode);
			b = searchdao.searchwishlist(ucode);
		}
		return b;
	}
	
	//희찬
	@RequestMapping("user_search_NotData")
	public ModelAndView user_search_NotData() {
		ModelAndView mv = new ModelAndView();
		
		//희찬
		PageSearch page = NotDataGetPageData();
		List<UserSearchVo> list = NotDataGetList();
		mv.addObject("page", page);
		mv.addObject("list", list);
		
		mv.setViewName("user/user_search");

		return mv;
	}
	//희찬
		public String getToday() {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar c1 = Calendar.getInstance();
			 String strToday = sdf.format(c1.getTime());
			return strToday;
		}
		public String getNextday() {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar c1 = Calendar.getInstance();
			 c1.add(Calendar.DATE,1);
			 String strNextday = sdf.format(c1.getTime());
			return strNextday;
		}
		
}
