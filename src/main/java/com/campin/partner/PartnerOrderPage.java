package com.campin.partner;

public class PartnerOrderPage {
	int startNo;     // 목록의 시작위치
	int endNo;		 // 목록의 끝 위치
	int listSize=20; // 목록의 행수
	int totSize;	 // 검색된 전체 건수
	int blockSize=5; // 표시될 페이지의 수
	int totPage;     // 전체 페이지 수
	int startPage;   // 페이지의 시작 위치
	int endPage; 	 // 페이지의 끝 위치
	int nowPage=1;     // 사용자가 보고 있는 현재 페이지
	
	int itemCode;		// 캠핑장 코드
	String start;		// 날짜 시작
	String end;			// 끝
	String findStr;		// 검색어
	
	public PartnerOrderPage() {
		compute();
	}
	
	public PartnerOrderPage(int totSize, int nowPage) {
		this.totSize = totSize;
		this.nowPage = nowPage;
		compute();
	}
	
	public void compute() {
		totPage = (int)Math.ceil(totSize/(double)listSize);
		endNo = listSize*nowPage;
		startNo = endNo-listSize +1;
		if(endNo>totSize) endNo = totSize;
		
		endPage = (int)Math.ceil(nowPage/(double)blockSize)*blockSize;
		startPage = endPage-blockSize +1;
		if(endPage>totPage)endPage = totPage;
	}

	//
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

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getFindStr() {
		return findStr;
	}

	public void setFindStr(String findStr) {
		this.findStr = findStr;
	}
	
	
	
	
}
