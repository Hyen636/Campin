package com.campin.manager;

public class PageCampAccountList {
	
	// 필드
	int startNo;     // 목록의 시작위치
	int endNo;		 // 목록의 끝 위치
	int listSize=10; // 목록의 행수
	int totSizeC;	 // 검색된 전체 건수
	int blockSize=5; // 표시될 페이지의 수
	int totPage;     // 전체 페이지 수
	int startPage;   // 페이지의 시작 위치
	int endPage; 	 // 페이지의 끝 위치
	int nowPage=1;     // 사용자가 보고 있는 현재 페이지
	String findStr;
	
	// 매개변수 없는 생성자
	public PageCampAccountList() {
		compute();
	}
	
	//
	public PageCampAccountList(int totSizeC, int nowPage) {
		this.totSizeC = totSizeC;
		this.nowPage = nowPage;
		compute();
	}
	
	//
	public void compute() {
		totPage = (int)Math.ceil(totSizeC/(double)listSize);
		endNo = listSize*nowPage;
		startNo = endNo-listSize +1;
		if(endNo>totSizeC) endNo = totSizeC;
		
		endPage = (int)Math.ceil(nowPage/(double)blockSize)*blockSize;
		startPage = endPage-blockSize +1;
		if(endPage>totPage)endPage = totPage;
	}
	
	//
	public static void main(String[] args) {
		PageCampAccountList p = new PageCampAccountList();
		p.setTotSizeC(500);
		p.setNowPage(1);
		p.compute();

	}
	
	
	// getter and setter
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

	public int getTotSizeC() {
		return totSizeC;
	}

	public void setTotSizeC(int totSizeC) {
		this.totSizeC = totSizeC;
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

	public String getFindStr() {
		return findStr;
	}

	public void setFindStr(String findStr) {
		this.findStr = findStr;
	}
	
}
