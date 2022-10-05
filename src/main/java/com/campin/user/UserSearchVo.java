package com.campin.user;

import java.util.List;

public class UserSearchVo {
	int itemCode;
	String iName;
	String sido;
	String infoText;
	
	//캠핑장 대표이미지
	String bestCampImg;
	//캠핑장 최소 가격
	int price;
	//필터
	List<String> cateList;
	//사이트 코드
	int siteCode;
	
	
	//검색어 유무 판별시 사용을 위해
	String searchbar;
	
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getInfoText() {
		return infoText;
	}
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	public String getBestCampImg() {
		return bestCampImg;
	}
	public void setBestCampImg(String bestCampImg) {
		this.bestCampImg = bestCampImg;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<String> getCateList() {
		return cateList;
	}
	public void setCateList(List<String> cateList) {
		this.cateList = cateList;
	}
	public int getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(int siteCode) {
		this.siteCode = siteCode;
	}
	
	
	
	
	
	
	
	
	
	
	public String getSearchbar() {
		return searchbar;
	}
	public void setSearchbar(String searchbar) {
		this.searchbar = searchbar;
	}
	
	
	
	
}
