package com.campin.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.campin.mybatis.UserSearchMapper;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;




@Service
public class UserSearchService {
	PageSearch page;
	
	@Autowired
	@Qualifier("userSearchMapper")
	UserSearchMapper searchMapper;
	
	
	public UserSearchService() {}
	
	//index -> search 검색어 포함 Click 시
	//검색 조건(지역, 인원, 예약할 날짜)
	public List<Integer> selectCondition(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//지역, 인원을 검색 조건에 맞는 캠핑장을 list에 출력
			if(page.getRegion().equals("전체")) {
				list = searchMapper.selectListIntAll(page.getTotalCount());
			}else {
				list = searchMapper.selectListInt(page);
			}
			
			//검색어 유무 확인 후 캠핑장 list 선별
			if(page.getSearchbar() != "") {
				List<Integer> tempList = new ArrayList<Integer>();
				for(int i = 0; i < list.size(); i++) {
					UserSearchVo vo = new UserSearchVo();
					try {
						vo.setSearchbar(page.getSearchbar());
						vo.setItemCode(list.get(i));
						int temp = searchMapper.selectListSearchBar(vo);
						tempList.add(temp);
					}catch(Exception ex){
						//System.out.println("---ChangeClear:UserSearchService_selectCondition_selectListSearchBar [null == 0] ---");
						//ex.printStackTrace();
					}
				}
				list = tempList;		//검색어 적용된 list를 원래 list에 데이터 덮기
			}
			
			//필터
			//함수화 할것.
			
			
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			//**************조건 정리 완료*****************
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectCondition 예외처리___");
			ex.printStackTrace();
		}
		
		//System.out.println("list.size() : " + list3.size());
		return list3;
	}
	
	//index -> search tag Click 시 
	//검색 조건(태그 + 예약할 날짜)
	public List<Integer> selectConditionTag(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//태그 있는 캠핑장 찾기
			list = searchMapper.selectListTagAll(page.getTagSearch());
			
			
			//System.out.println("list : " + list);
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
			cal.add(Calendar.DATE, 1);
			}
		
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectConditionTag 예외처리___");
			ex.printStackTrace();
		}
		
		return list3;
	}
	
	//index -> search -> search_temp load 시
	//검색 조건(지역, 인원, 예약할 날짜) + 태그
	public List<Integer> selectConditionFirst(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//지역, 인원을 검색 조건에 맞는 캠핑장을 list에 출력
			if(page.getRegion().equals("전체")) {
				list = searchMapper.selectListIntAll(page.getTotalCount());
			}else {
				list = searchMapper.selectListInt(page);
			}
			
			//검색어 유무 확인 후 캠핑장 list 선별
			if(page.getInput_search() != "") {
				List<Integer> tempList = new ArrayList<Integer>();
				for(int i = 0; i < list.size(); i++) {
					UserSearchVo vo = new UserSearchVo();
					try {
						vo.setSearchbar(page.getInput_search());
						vo.setItemCode(list.get(i));
						int temp = searchMapper.selectListSearchBar(vo);
						tempList.add(temp);
					}catch(Exception ex){
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListSearchBar [null == 0] ---");
						//ex.printStackTrace();
					}
				}
				list = tempList;		//검색어 적용된 list를 원래 list에 데이터 덮기
			}
			
			//태그 적용
			//System.out.println("page.tagSearch() : " + page.getTagSearch());
			if( !page.getTagSave().equals(null) || !page.getTagSave().equals("") ||
					page.getTagSave() != null || page.getTagSave() != ("")) {
				List<Integer> templist = new ArrayList<Integer>();
				//System.out.println("list.size() : " + list.size());
				for(int i = 0; i < list.size(); i++) {
					UserSearchCategoryVo vo = new UserSearchCategoryVo();
					int cnt = 0;
					vo.setItemCode(list.get(i));
					vo.setcTag(page.getTagSave());
					try {
						cnt = searchMapper.selectListTag(vo);
					}catch(Exception ex) {
						//System.out.println(i);
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListTag [null == 0] ---");
						//ex.printStackTrace();
						cnt = 0;
					}
					if(cnt > 0) {
						templist.add(cnt);
					}
				}
				
				list = templist;
			}
			
				
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//System.out.println("Days : " + Days);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				//System.out.println("list.size() : " + list.size());
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					
					//System.out.println("ordersVo.getItemCode() : " + ordersVo.getItemCode());
					//System.out.println("ordersVo.getDateStr() : " + ordersVo.getDateStr());
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					//System.out.println("ordersCnt : " + ordersCnt);
					//System.out.println("siteList : " + siteList.get(j));
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			//**************조건 정리 완료*****************
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectConditionFirst 예외처리___");
			ex.printStackTrace();
		}
		return list3;
	}
	
	//다른 Page에서 search -> search_temp load 시
	//검색 조건(지역, 인원, 예약할 날짜) + 태그
	public List<Integer> selectCondition_OtherTemp(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//지역, 인원을 검색 조건에 맞는 캠핑장을 list에 출력
			if(page.getRegion().equals("전체")) {
				list = searchMapper.selectListIntAll(page.getTotalCount());
			}else {
				list = searchMapper.selectListInt(page);
			}
			
				
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
								
			cal.setTime(d1);
			//System.out.println("Days : " + Days);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				//System.out.println("list.size() : " + list.size());
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);					
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			//**************조건 정리 완료*****************
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectConditionFirst 예외처리___");
			ex.printStackTrace();
		}
		return list3;
	}
	
	
	//user_search_temp에서 필터, 태그 click 시
	//검색 조건(지역, 인원, 예약할 날짜) + 필터,태그(tagSave)
	public List<Integer> selectConditionOption_Filter(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> listTemp = new ArrayList<Integer>();	//필터 적용시 만족하는 것만 추가할 list
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//지역, 인원을 검색 조건에 맞는 캠핑장을 list에 출력
			if(page.getRegion().equals("전체")) {
				list = searchMapper.selectListIntAll(page.getTotalCount());
			}else {
				list = searchMapper.selectListInt(page);
			}
			
			//검색어 유무 확인 후 캠핑장 list 선별
			if(page.getInput_search() != "") {
				List<Integer> tempList = new ArrayList<Integer>();
				for(int i = 0; i < list.size(); i++) {
					UserSearchVo vo = new UserSearchVo();
					try {
						vo.setSearchbar(page.getInput_search());
						vo.setItemCode(list.get(i));
						int temp = searchMapper.selectListSearchBar(vo);
						tempList.add(temp);
					}catch(Exception ex){
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListSearchBar [null == 0] ---");
						//ex.printStackTrace();
					}
				}
				list = tempList;		//검색어 적용된 list를 원래 list에 데이터 덮기
			}
			
			//필터
			//System.out.println("page.getCheckfilter() : " + page.getCheckfilter());
			if(page.getCheckfilter() != null) {
				//System.out.println("tttttttttt");
				List<String> tempStringList = new ArrayList<String>();
				//split
				String[] splitTemp = new String[page.getCheckfilterCnt()];
				splitTemp = page.getCheckfilter().split(",");
				for(int i = 0; i < splitTemp.length; i++) {
					tempStringList.add(splitTemp[i]);
				}
				
				
				for(int i = 0; i < list.size(); i++) {					//검토중의 list의 갯수만큼
					int temp = -1;
					List<Integer> tempList = new ArrayList<Integer>();
					for(int j = 0; j < tempStringList.size(); j++) {	//검토
						UserSearchCategoryVo vo = new UserSearchCategoryVo();
						
						
						vo.setItemCode(list.get(i));
						vo.setFilterStr(splitTemp[j]);
						try {
							temp = searchMapper.selectListFilter(vo);
							tempList.add(temp);
						}catch(Exception ex){
							//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListFilter [list.size : list.get(i) + " | FilterStr : " + tempStringList + "] ---");
						//ex.printStackTrace();
						}
						
						
					}
					
					//모든 필터가 들어가 있는 경우만 
					if(tempStringList.size() == tempList.size()) {
						listTemp.add(temp);
					}
					
				}
				list = listTemp;				
			}
			
			
			//태그 적용
			//System.out.println("page.getTagSave() : " + page.getTagSave());
			//!page.getTagSearch().equals(null) || !page.getTagSearch().equals("") ||
			if( page.getTagSave() != null || page.getTagSave() != ("")) {
				//System.out.println("aaaaaaaaaaaaa");
				List<Integer> templist = new ArrayList<Integer>();
				//System.out.println("list.size() : " + list.size());
				for(int i = 0; i < list.size(); i++) {
					//System.out.println("i : " + i);
					UserSearchCategoryVo vo = new UserSearchCategoryVo();
					int cnt = 0;
					vo.setItemCode(list.get(i));
					vo.setcTag(page.getTagSave());
					try {
						cnt = searchMapper.selectListTag(vo);
						//System.out.println("cnt : " + cnt);
					}catch(Exception ex) {
						//System.out.println(i);
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListTag [null == 0] ---");
						//ex.printStackTrace();
						cnt = 0;
					}
					if(cnt > 0) {
						templist.add(cnt);
					}
				}
				
				list = templist;
			}
			


			
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			//**************조건 정리 완료*****************
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectConditionOption_Filter 예외처리___");
			ex.printStackTrace();
		}

		//System.out.println("list.size() : " + list3.size());
		return list3;
	}
	
	//user_search_temp에서 필터, 태그 click 시
	//검색 조건(지역, 인원, 예약할 날짜) + 필터,태그(tagSearch)
	public List<Integer> selectConditionOption_Tag(PageSearch page){
		List<Integer> list = null;
		List<Integer> list2 = new ArrayList<Integer>();		//list중 예약 조건에서 걸러질 list (중복되어 들어감)
		List<Integer> list3 = new ArrayList<Integer>();		//최종 리턴 값
		List<Integer> listTemp = new ArrayList<Integer>();	//필터 적용시 만족하는 것만 추가할 list
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//지역, 인원을 검색 조건에 맞는 캠핑장을 list에 출력
			if(page.getRegion().equals("전체")) {
				list = searchMapper.selectListIntAll(page.getTotalCount());
			}else {
				list = searchMapper.selectListInt(page);
			}
			
			//검색어 유무 확인 후 캠핑장 list 선별
			////System.out.println("page.getInput-search() : " + page.getInput_search());
			if(page.getInput_search() != "") {
				List<Integer> tempList = new ArrayList<Integer>();
				for(int i = 0; i < list.size(); i++) {
					UserSearchVo vo = new UserSearchVo();
					try {
						vo.setSearchbar(page.getInput_search());
						vo.setItemCode(list.get(i));
						int temp = searchMapper.selectListSearchBar(vo);
						tempList.add(temp);
					}catch(Exception ex){
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListSearchBar [null == 0] ---");
						//ex.printStackTrace();
					}
				}
				list = tempList;		//검색어 적용된 list를 원래 list에 데이터 덮기
			}
			
			//필터
			//함수화 할것.
			////System.out.println("page.getCheckfilter() : " + page.getCheckfilter());
			if(page.getCheckfilter() != null) {
				List<String> tempStringList = new ArrayList<String>();
				//split
				String[] splitTemp = new String[page.getCheckfilterCnt()];
				splitTemp = page.getCheckfilter().split(",");
				for(int i = 0; i < splitTemp.length; i++) {
					tempStringList.add(splitTemp[i]);
				}
				
				
				for(int i = 0; i < list.size(); i++) {					//검토중의 list의 갯수만큼
					int temp = -1;
					List<Integer> tempList = new ArrayList<Integer>();
					for(int j = 0; j < tempStringList.size(); j++) {	//검토
					UserSearchCategoryVo vo = new UserSearchCategoryVo();
						
						vo.setItemCode(list.get(i));
						vo.setFilterStr(splitTemp[j]);
						try {
							temp = searchMapper.selectListFilter(vo);
							tempList.add(temp);
						}catch(Exception ex){
							//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListFilter [list.size : list.get(i) + " | FilterStr : " + tempStringList + "] ---");
						//ex.printStackTrace();
						}
					}
					
					//모든 필터가 들어가 있는 경우만 
					if(tempStringList.size() == tempList.size()) {
						listTemp.add(temp);
					}
					
				}
				list = listTemp;				
			}
			
			
			//태그 적용
			//System.out.println("page.tagSearch() : " + page.getTagSearch());
			//!page.getTagSearch().equals(null) || !page.getTagSearch().equals("") ||
			if( page.getTagSearch() != null || page.getTagSearch() != ("")) {
				List<Integer> templist = new ArrayList<Integer>();
				//System.out.println("list.size() : " + list.size());
				for(int i = 0; i < list.size(); i++) {
					UserSearchCategoryVo vo = new UserSearchCategoryVo();
					int cnt = 0;
					vo.setItemCode(list.get(i));
					vo.setcTag(page.getTagSearch());
					try {
						cnt = searchMapper.selectListTag(vo);
					}catch(Exception ex) {
						//System.out.println(i);
						//System.out.println("---ChangeClear:UserSearchService_selectConditionOption_selectListTag [null == 0] ---");
						//ex.printStackTrace();
						cnt = 0;
					}
					if(cnt > 0) {
						templist.add(cnt);
					}
				}
				
				list = templist;
			}
			

			
			//출력된 캠핑장들의 각 사이트 갯수 출력
			for(int i=0; i<list.size(); i++) {
				int cnt = searchMapper.selectSiteCount(list.get(i));
				siteList.add(cnt);
			}
			
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckIn());
			Date d2 = df.parse(page.getCheckOut());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					ordersVo.setItemCode(list.get(j));
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					
					int ordersCnt = searchMapper.selectOrdersCount(ordersVo);
					if(ordersCnt == siteList.get(j)) {
						//list 삭제
						//list2에 추가되지 않음.
					}else {
						//추가
						list2.add(list.get(j));
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			//**************조건 정리 완료*****************
			}catch(Exception ex) {
				//System.out.println("___Error:UserSearchService_selectConditionOption_Tag 예외처리___");
				ex.printStackTrace();
			}
		
		
		return list3;
	}
	
	
	
	public List<UserSearchItemVo> selectSearchItem(List<Integer> list){
		List<UserSearchItemVo> volist = new ArrayList<UserSearchItemVo>();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				UserSearchItemVo vo = null;
				vo = searchMapper.selectItem(list.get(i));
				volist.add(vo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectSearchItem 예외처리___");
			ex.printStackTrace();
		}
		
		
		
		return volist;
	}
	
	public List<Integer> selectSearchItemDetail(List<Integer> list){
		List<Integer> priceList = new ArrayList<Integer>();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				int datePrice = searchMapper.selectItemDetail(list.get(i));
				priceList.add(datePrice);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectSearchItemDetail 예외처리___");
			ex.printStackTrace();
		}

		return priceList;
	}
	
	public List<List<UserSearchItemAttVo>> selectSearchItemAtt(List<Integer> list){
		List<List<UserSearchItemAttVo>> listArray = new ArrayList<List<UserSearchItemAttVo>>();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				List<UserSearchItemAttVo> volist = new ArrayList<UserSearchItemAttVo>();
				volist = searchMapper.selectItemAtt(list.get(i));
				listArray.add(volist);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectSearchItemAtt 예외처리___");
			ex.printStackTrace();
		}

		return listArray;
	}
	
	public List<List<String>> selectSearchCategory(List<Integer> list){
		List<List<String>> listArray = new ArrayList<List<String>>();;
		
		try {
			for(int i = 0; i < list.size(); i++) {
				List<String> filterList = new ArrayList<String>();
				List<String> tempList = new ArrayList<String>();
				tempList = searchMapper.selectCategory(list.get(i));
				
				for(int k = 0; k < tempList.size(); k++) {
					if(tempList.get(k).equals("화장실") || tempList.get(k).equals("샤워실") ||
							tempList.get(k).equals("매점") || tempList.get(k).equals("카페") ||
							tempList.get(k).equals("개수대") || tempList.get(k).equals("와이파이") ||
							tempList.get(k).equals("체험활동") || tempList.get(k).equals("수영장") ||
							tempList.get(k).equals("트램펄린") || tempList.get(k).equals("산책로") ||
							tempList.get(k).equals("장비대여") || tempList.get(k).equals("반려동물") ||
							tempList.get(k).equals("트레일러진입") || tempList.get(k).equals("카라반진입") ||
							tempList.get(k).equals("놀이시설")) {
						filterList.add(tempList.get(k));
					}
				}
				
				
				
				listArray.add(filterList);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectSearchCategory 예외처리___");
			ex.printStackTrace();
		}

		return listArray;
	}
	
	
	public void InsertQna(UserSearchQnaVo vo) {
		try {
			//추가
			searchMapper.insertQna(vo);
			//sno_max값 가져오기
			int max = searchMapper.selectSnoMax();
			//grp update
			vo.setGrp(max);
			searchMapper.updateGrp(vo);
			
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_InsertQna 예외처리___");
			ex.printStackTrace();
		}
	}
	
	
	public UserSearchItemVo selectCampDetailInfo(PageSearchDetail page) {
		UserSearchItemVo vo2 = null;
		try{
			vo2 = searchMapper.selectCampDetailInfo(page);
			
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectCampDetailInfo 예외처리___");
			ex.printStackTrace();
		}
		return vo2;
	}
	
	
	public List<UserSearchDetailQnaVo> selectDetailQna(PageSearchDetail page) {
		List<UserSearchDetailQnaVo> list = new ArrayList<UserSearchDetailQnaVo>();
		List<UserSearchDetailBoardVo> vo = null;
		List<UserSearchDetailBoardVo> vo2 = null;
		List<String> nameList = new ArrayList<String>();
		try{
			//질문만 넣은 list
			vo = searchMapper.selectDetailQna(page);
			
			//list와 동일한 size의 유저 코드 list2
			for(int i = 0; i < vo.size(); i++) {
				nameList.add(searchMapper.selectUserName(vo.get(i).userCode));
			}
			
			//답변만 넣은 list
			vo2 = searchMapper.selectDetailQnaAnswer(page);
			
			//해당 질문에 답변 넣기
			for(int i = 0; i < vo.size(); i++) {
				UserSearchDetailQnaVo QnaVo = new UserSearchDetailQnaVo();
				QnaVo.setItemCode(page.getDetailItemCode());
				QnaVo.setNal(vo.get(i).nal);
				QnaVo.setTitle(vo.get(i).title);
				QnaVo.setDoc(vo.get(i).doc);
				QnaVo.setState(vo.get(i).state);
				QnaVo.setPwd(vo.get(i).pwd);
				QnaVo.setWriter(nameList.get(i));
				
				//답변
				for(int k = 0; k < vo2.size(); k++) {
					if(vo.get(i).grp == vo2.get(k).grp) {
						QnaVo.setaTitle(vo2.get(k).title);
						QnaVo.setaDoc(vo2.get(k).doc);
						break;
					}
				}
				
				list.add(QnaVo);
			}
			
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectDetailQna 예외처리___");
			ex.printStackTrace();
		}
		return list;
	} 
	
	
	
	
	public double selectReviewStar(PageSearchDetail page) {
		double dtemp = 0;
		double result = 0;
		List<UserSearchDetailReviewVo> vo = null;
		
		try {
			//별점만 넣은 list
			vo = searchMapper.selectReviewStar(page);
			for(int i=0; i<vo.size(); i++) {
				dtemp += vo.get(i).score;
			}
			
			result = (dtemp / vo.size());
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectReviewStar 예외처리___");
			ex.printStackTrace();
		}
		
		return result;
	}
	
	
	public int selectReviewCnt(PageSearchDetail page) {
		int itemp = 0;		
		try {
			//질문 갯수 list
			itemp = searchMapper.selectReviewCnt(page);
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectReviewCnt 예외처리___");
			ex.printStackTrace();
		}
		
		return itemp;
	}
	public List<UserSearchDetailReviewVo> selectDetailReview(PageSearchDetail page) {
		List<UserSearchDetailReviewVo> list = new ArrayList<UserSearchDetailReviewVo>();
		List<UserSearchDetailReviewVo> vo = null;
		List<UserSearchDetailReviewVo> vo2 = null;
		List<String> nameList = new ArrayList<String>();
		try{
			//질문만 넣은 list
			vo = searchMapper.selectDetailReview(page);
			
			//list와 동일한 size의 유저 코드 list2
			for(int i = 0; i < vo.size(); i++) {
				nameList.add(searchMapper.selectUserName(vo.get(i).userCode));
			}
			
			//답변만 넣은 list
			vo2 = searchMapper.selectDetailReviewAnswer(page);
			
			//해당 질문에 답변 넣기
			for(int i = 0; i < vo.size(); i++) {
				UserSearchDetailReviewVo ReviewVo = new UserSearchDetailReviewVo();
				ReviewVo.setItemCode(page.getDetailItemCode());
				ReviewVo.setNal(vo.get(i).nal);
				ReviewVo.setDoc(vo.get(i).doc);
				ReviewVo.setState(vo.get(i).state);
				ReviewVo.setScore(vo.get(i).score);
				ReviewVo.setSysFile(vo.get(i).sysFile);
				ReviewVo.setSiteName(vo.get(i).siteName);
				ReviewVo.setWriter(nameList.get(i));
				
				//답변
				for(int k = 0; k < vo2.size(); k++) {
					if(vo.get(i).orderCode == vo2.get(k).orderCode) {
						ReviewVo.setaDoc(vo2.get(k).doc);
						break;
					}
				}
				list.add(ReviewVo);
			}
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectDetailReview 예외처리___");
			ex.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public List<UserSearchItemDetailVo> selectDetailCampSite(PageSearchDetail page) {
		List<UserSearchItemDetailVo> list = new ArrayList<UserSearchItemDetailVo>();
		try{
			//itemCode에 따른 site list
			list = searchMapper.selectDetailCampSite(page.getDetailItemCode());
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectDetailCampSite 예외처리___");
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public String selectItemAddMap(String itemCode) {
		String mapStr = "";
		try{
			mapStr = searchMapper.selectItemAddMap(itemCode);
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectItemAddMap 예외처리___");
			ex.printStackTrace();
		}
		return mapStr;
	}
	
	
	public List<UserSearchItemDetailVo> selectDetailSiteCondition(PageSearchDetail page){
		List<UserSearchItemDetailVo> list = new ArrayList<UserSearchItemDetailVo>();	//인원 분류 한 사이트 list
		List<Integer> list2 = new ArrayList<Integer>();		//임시 temp 값
		List<Integer> list3 = new ArrayList<Integer>();		//임시 분류한 사이트코드 리턴 값
		List<UserSearchItemDetailVo> list4= new ArrayList<UserSearchItemDetailVo>();	//최종 list
		
		List<Integer> siteList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			int totalPeople = Integer.parseInt(page.getAdultCountSave()) + Integer.parseInt(page.getChildCountSave());
			page.setTotalPeople(totalPeople);
			
			//itemCode, state, 인원에 따른 site list
			list = searchMapper.selectItemDetailPeopleCheck(page);
			//System.out.println("list.size() : " + list.size());
			for(int i=0; i<list.size(); i++) {
				//System.out.println("list.get() : " + list.get(i).siteName);
				//System.out.println("list.get() : " + list.get(i).siteCode);
			}
			
			//System.out.println("page.getCheckInSave() : " + page.getCheckInSave());
			//System.out.println("page.getCheckOutSave() : " + page.getCheckOutSave());
			//예약 확인
			//두 날짜 사이값 구한뒤 for문 돌리기
			Date d1 = df.parse(page.getCheckInSave());
			Date d2 = df.parse(page.getCheckOutSave());
			long Sec = (d2.getTime() - d1.getTime()) / 1000;
			long Days = Sec / (24*60*60);
			
			
			cal.setTime(d1);
			//System.out.println("Days : " + Days);
			//예약하려는 날짜
			for(int i = 0; i < (int)Days; i++) {
				
				//날짜별로 예약리스트(orders)중 해당 캠핑장의 사이트 갯수와 예약의 갯수가 같은때만 list에서 해당 사이트 제거
				for(int j = 0; j < list.size(); j++) {
					UserSearchOrdersVo ordersVo = new UserSearchOrdersVo();
					
					
					ordersVo.setSiteCode(list.get(j).getSiteCode());
					String tempDate = df.format(cal.getTime());
					ordersVo.setDateStr(tempDate);
					int ordersCnt = searchMapper.selectOrdersSiteCount(ordersVo);
					if(ordersCnt > 0) {
						//list 삭제
						//list2에 추가되지 않음.
						list2.add(list.get(j).getSiteCode());
					}else {
						//추가되기 위해 list2에 추가하지 않음.
					}
				}
				//날짜 하루 ++
				cal.add(Calendar.DATE, 1);
			}
			
			
			//예약 가능한 list2, list2_2 중복 제거
			//Set은 중복을 허용하지 않는 객체
			Set<Integer> set = new HashSet<Integer>(list2);
			list3 = new ArrayList<Integer>(set);
			
			
			for(int i=0; i<list3.size(); i++) {
				//System.out.println("list4 : " + list3.get(i));
			}
			
			//인원list에서 예약이 없는 사이트코드만 추출
			for(int i=0; i<list.size(); i++) {
				int count = 0;
				for(int k = 0; k < list3.size(); k++) {
					if(list.get(i).siteCode == list3.get(k)) {
						count++;
						break;
					}
				}
				
				if(count > 0) {
					//예약이 있으니 추가 x
				}else {
					list4.add(list.get(i));
				}
			}
			
			for(int i=0; i<list4.size(); i++) {
				//System.out.println("list5 : " + list4.get(i));
			}
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectDetailSiteCondition 예외처리___");
			ex.printStackTrace();
		}
		
		return list4;
	}
	
	public String selectFindSiteCode(UserSearchSendReservationVo vo) {
		String temp = "";
		try {
			int iTemp = searchMapper.selectFindSiteCode(vo);
			temp = Integer.toString(iTemp);
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectFindSiteCode 예외처리___");
			ex.printStackTrace();
		}
		
		return temp;
	}
	
	public String selectFindcUserCode(UserSearchSendReservationVo vo) {
		String temp = "";
		try {
			int cTemp = searchMapper.selectFindcUserCode(vo);
			temp = Integer.toString(cTemp);
		}catch(Exception ex) {
		System.out.println("___Error:UserSearchService_selectFindcUserCode 예외처리___");
			ex.printStackTrace();
		}
		
		return temp;
	}
	
	
	public List<UserSearchItemAttVo> selectSiteImage(PageSearchDetail page) {
		List<UserSearchItemAttVo> temp = null;
		try {
			
			temp = searchMapper.selectSiteImage(page);
			
		}catch(Exception ex) {
			//System.out.println("___Error:UserSearchService_selectSiteImage 예외처리___");
			ex.printStackTrace();
		}
		
		return temp;
	}
	
	public void wishlistAdd(UserWishlistVo vo) {
		try {
			searchMapper.insertWishList(vo);
		}catch(Exception ex){
			System.out.println("___Error:UserSearchService_wishlistAdd 예외처리___");
			ex.printStackTrace();
		}
		
	}
	
	public void wishlistDelete(UserWishlistVo vo) {
		try {
			searchMapper.deleteWishList(vo);
		}catch(Exception ex){
			System.out.println("___Error:UserSearchService_deleteWishList 예외처리___");
			ex.printStackTrace();
		}
		
	}
	
	public List<Integer> findwishlist(int userCode){
		List<Integer> list = null;
		try {
			list = searchMapper.findwishlist(userCode);
		}catch(Exception ex) {
			System.out.println("___Error:UserSearchService_findwishlist 예외처리___");
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public List<String> getCategory(PageSearchDetail page){
		List<String> list = null;
		try {
			list = searchMapper.getCategory(page.getDetailItemCode());
		}catch(Exception ex) {
			System.out.println("___Error:UserSearchService_getCategory 예외처리___");
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public boolean searchwishlist(int userCode){
		int cnt = 0;
		boolean b = false;
		try {
			cnt = searchMapper.searchwishlist(userCode);
			if(cnt > 0) {
				b = true;
			}
		}catch(Exception ex) {
			System.out.println("___Error:UserSearchService_searchwishlist 예외처리___");
			ex.printStackTrace();
		}
		
		return b;
	}
	
	
	public List<UserSearchItemAttVo> selectCampHWImg(PageSearchDetail page){
		List<UserSearchItemAttVo> list = null;
		try {
			list = searchMapper.selectCampHWImg(page);
		}catch(Exception ex) {
			System.out.println("___Error:UserSearchService_selectCampHWImg 예외처리___");
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public Integer selectSiteCnt(String itemCode){
		List<UserSearchItemAttVo> cnt = null;
		try {
			cnt = searchMapper.selectSiteCnt(itemCode);
			
		}catch(Exception ex) {
			System.out.println("___Error:UserSearchService_selectCampHWImg 예외처리___");
			ex.printStackTrace();
		}
		
		return cnt.size();
	}
	
	
	
	
	public PageSearch getPage() {
		return page;
	}
	
	
}
