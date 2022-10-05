package com.campin.partner;

public class ItemOptionVo {
	int sno;
	int itemCode;
	int cUserCode;
	int hisItemSno;
	String optItem;
	int price;
	
	//
	public int getSno() {
		return sno;
	}
	public int getcUserCode() {
		return cUserCode;
	}
	public void setcUserCode(int cUserCode) {
		this.cUserCode = cUserCode;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getOptItem() {
		return optItem;
	}
	public void setOptItem(String optItem) {
		this.optItem = optItem;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getHisItemSno() {
		return hisItemSno;
	}
	public void setHisItemSno(int hisItemSno) {
		this.hisItemSno = hisItemSno;
	}
	
}
