package com.campin.user;

import org.apache.ibatis.type.Alias;

@Alias("searchPage")
public class PageSearch {
	int startNo; // 목록의 시작 위치
	int endNo; // 목록의 끝 위치
	int listSize=15; // 목록의 행수
	int totSize; // 검색된 전체 건수
	int blockSize=7; // 표시될 페이지의  수
	int totPage; // 전체 페이지 수
	int startPage; // 페이지의 시작 위치
	int endPage; // 페이지의 끝 위치
	int nowPage=1; // 사용자자가 보고 있는 현재 페이지
  
  
	//index -> search
	//메뉴바에 넣을 것
	String searchbar;			//검색어
	String region;				//지역
	String check_in;			//체크인
	String check_out;			//체크아웃
	String headcount;			//총인원
  
	//정보(Data) 가져올 것
	String checkIn;				//input 체크인
	String checkOut;			//input 체크아웃
	String adultCount;			//input 어른인원
	String childCount;			//input 아이인원
	int totalCount;		
	String input_search;		//user_search_temp의 검색어
	
	String checkfilter;			//체크한 filter의 문자열
	int checkfilterCnt;			//체크한 filter의 갯수
	String tagSave;				//클린한 tag의 value
	
	String tagSearch;			//index에서 tag 버튼 클릭 시 해당 tag 포함 캠핑장 view 
	String searchState;			//inc 대신 불러올 파일의 상태(파일이름)를 저장.
	String searchbar2;			//frm 검색어 저장
	String input_search2;		//frm user_search_temp의 검색어 저장
	String tagSave2;			//frm user_search_temp의 태그 저장
	
	//detail에 필요한 itemCode
	String detailItemCode;
	String checkInSave;
	String checkOutSave;
	String adultCountSave;
	String childCountSave;


	
	
	public PageSearch() {
		compute();
	}
	public PageSearch(int totSize, int nowPage) {
	  	this.totSize = totSize;
	  	this.nowPage = nowPage;
	  	compute();
	  }
	  public void compute() {
	  	totPage = (int)Math.ceil(totSize/(double)listSize);
	  	endNo = listSize * nowPage;
	  	startNo = endNo-listSize+1;
	  	if(endNo>totSize) endNo = totSize;
	  	
	  	endPage = (int)Math.ceil(nowPage/(double)blockSize)*blockSize;
	  	startPage = endPage-blockSize+1;
	  	if(endPage>totPage) endPage = totPage;
	  }
	  
	  public static void main(String[] args) {
			PageSearch p = new PageSearch();
			p.setTotSize(564);
			p.setNowPage(15);
			p.compute();
		}
	  
	  
	  
	  //getter and setter
  
		public int getStartNo() {
			return startNo;
		}
		public void setStartNo(int startNo) {
			this.startNo = startNo;
		}
		public int getEndNo() {
			return endNo;
		}
		public void setEndNo(int endNo) {
			this.endNo = endNo;
		}
		public int getListSize() {
			return listSize;
		}
		public void setListSize(int listSize) {
			this.listSize = listSize;
		}
		public int getTotSize() {
			return totSize;
		}
		public void setTotSize(int totSize) {
			this.totSize = totSize;
		}
		public int getBlockSize() {
			return blockSize;
		}
		public void setBlockSize(int blockSize) {
			this.blockSize = blockSize;
		}
		public int getTotPage() {
			return totPage;
		}
		public void setTotPage(int totPage) {
			this.totPage = totPage;
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
		public int getNowPage() {
			return nowPage;
		}
		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
		}
		public String getSearchbar() {
			return searchbar;
		}
		public void setSearchbar(String searchbar) {
			this.searchbar = searchbar;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public String getCheckIn() {
			return checkIn;
		}
		public void setCheckIn(String checkIn) {
			this.checkIn = checkIn;
		}
		public String getCheckOut() {
			return checkOut;
		}
		public void setCheckOut(String checkOut) {
			this.checkOut = checkOut;
		}
		public String getAdultCount() {
			return adultCount;
		}
		public void setAdultCount(String adultCount) {
			this.adultCount = adultCount;
		}
		public String getChildCount() {
			return childCount;
		}
		public void setChildCount(String childCount) {
			this.childCount = childCount;
		}
		public String getCheck_in() {
			return check_in;
		}
		public void setCheck_in(String check_in) {
			this.check_in = check_in;
		}
		public String getCheck_out() {
			return check_out;
		}
		public void setCheck_out(String check_out) {
			this.check_out = check_out;
		}
		public String getHeadcount() {
			return headcount;
		}
		public void setHeadcount(String headcount) {
			this.headcount = headcount;
		}
		public String getCheckfilter() {
			return checkfilter;
		}
		public void setCheckfilter(String checkfilter) {
			this.checkfilter = checkfilter;
		}
		public int getCheckfilterCnt() {
			return checkfilterCnt;
		}
		public void setCheckfilterCnt(int checkfilterCnt) {
			this.checkfilterCnt = checkfilterCnt;
		}
		
		
		
		
		
		
		
		public String getTagSave() {
			return tagSave;
		}
		public void setTagSave(String tagSave) {
			this.tagSave = tagSave;
		}
		public String getTagSearch() {
			return tagSearch;
		}
		public void setTagSearch(String tagSearch) {
			this.tagSearch = tagSearch;
		}
		public String getSearchState() {
			return searchState;
		}
		public void setSearchState(String searchState) {
			this.searchState = searchState;
		}
		public String getInput_search() {
			return input_search;
		}
		public void setInput_search(String input_search) {
			this.input_search = input_search;
		}
		public String getSearchbar2() {
			return searchbar2;
		}
		public void setSearchbar2(String searchbar2) {
			this.searchbar2 = searchbar2;
		}
		public String getInput_search2() {
			return input_search2;
		}
		public void setInput_search2(String input_search2) {
			this.input_search2 = input_search2;
		}
		public String getTagSave2() {
			return tagSave2;
		}
		public void setTagSave2(String tagSave2) {
			this.tagSave2 = tagSave2;
		}
		
		
		
		
		public String getDetailItemCode() {
			return detailItemCode;
		}
		public void setDetailItemCode(String detailItemCode) {
			this.detailItemCode = detailItemCode;
		}
		public String getCheckInSave() {
			return checkInSave;
		}
		public void setCheckInSave(String checkInSave) {
			this.checkInSave = checkInSave;
		}
		public String getCheckOutSave() {
			return checkOutSave;
		}
		public void setCheckOutSave(String checkOutSave) {
			this.checkOutSave = checkOutSave;
		}
		public String getAdultCountSave() {
			return adultCountSave;
		}
		public void setAdultCountSave(String adultCountSave) {
			this.adultCountSave = adultCountSave;
		}
		public String getChildCountSave() {
			return childCountSave;
		}
		public void setChildCountSave(String childCountSave) {
			this.childCountSave = childCountSave;
		}
		
		
		
		
		
		
		
}








