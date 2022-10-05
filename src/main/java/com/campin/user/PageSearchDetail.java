package com.campin.user;

public class PageSearchDetail {
	int startNo;			//목록의 시작 위치
	int endNo;				//목록의 끝 위치
	int listSize = 15;		//목록의 행수
	int totSize;			//검색된 검색 전체 건수
	int blockSize = 7;		//표시될 페이지의 수
	int totPage;			//전체 페이지 수
	int startPage;			//페이지의 시작 위치
	int endPage;			//페이지의 끝 위치
	int nowPage = 1;		//사용자가 보고있는 현재 페이지
	
	String searchState;		//user_search에서 detail과 search_결과의 구분점
	String userId;			//접속중인 userId;
	
	//detail에 필요한 itemCode
	String detailItemCode;
	
	//fixed에서 필요한 변수
	String checkInSave;
	String checkOutSave;
	String adultCountSave;
	String childCountSave;
	
	//fixed - selected 변환시 필요한 변수
	int totalPeople;
	
	String siteNameSave = "";
	
	public PageSearchDetail() {
		compute();
	}
	
	public PageSearchDetail(int totSize, int nowPage) {
		this.totSize = totSize;
		this.nowPage = nowPage;
		compute();
	}
	
	public void compute() {
		totPage = (int)Math.ceil(totSize/(double)listSize);
		endNo = listSize * nowPage;
		startNo = endNo - listSize + 1;
		if(endNo > totSize) endNo=totSize;
		
		endPage = (int)Math.ceil(nowPage/(double)blockSize)*blockSize;
		startPage = endPage - blockSize +1;
		if(endPage>totPage) endPage = totPage;
	}
	
	public static void main(String[] args) {
		PageSearchDetail p = new PageSearchDetail();
		p.setTotSize(564);
		p.setNowPage(15);
		p.compute();
		System.out.println("totPage : " + p.getTotPage());
		System.out.println("startNo : " + p.getStartNo());
		System.out.println("endNo : " + p.getEndNo());
		System.out.println("startPage : " + p.getStartPage());
		System.out.println("endPage : " + p.getEndPage());
	}
	
	
	
	

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

	public String getSearchState() {
		return searchState;
	}

	public void setSearchState(String searchState) {
		this.searchState = searchState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public String getSiteNameSave() {
		return siteNameSave;
	}

	public void setSiteNameSave(String siteNameSave) {
		this.siteNameSave = siteNameSave;
	}
	
	

	
	
	
	
	
	

	
	
	
}
