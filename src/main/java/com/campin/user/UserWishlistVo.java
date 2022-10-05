package com.campin.user;


public class UserWishlistVo {
	int userCode;
	int itemCode;
	
	int price;
	String iName;    // 캠핑장 이름
	String sido;	 // 시,도
	String infoText; // 캠핑장 설명
	String state;	 // 캠핑장 상태 (탈퇴 여부 확인하기 위함)
	
	String itemType; // 대표 이미지 type 가져오기 위함
	String oriFile;
	String sysFile;
	
	public UserWishlistVo() {}
	public UserWishlistVo(int userCode, int itemCode, int price, String iName, String sido, String infoText, String state, String itemType, String oriFile, String sysFile) {
		this.userCode = userCode;
		this.itemCode = itemCode;
		this.price = price;
		this.iName = iName;
		this.sido = sido;
		this.infoText = infoText;
		this.state = state;
		this.itemType = itemType;
		this.oriFile = oriFile;
		this.sysFile = sysFile;
	}
	
	// GETTERS & SETTERS
	
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getOriFile() {
		return oriFile;
	}
	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}
	public String getSysFile() {
		return sysFile;
	}
	public void setSysFile(String sysFile) {
		this.sysFile = sysFile;
	}

	
}
