package com.campin.partner;

public class CategoryVo {
	int sno;
	int hisItemSno;
	int itemCode;
	int cUserCode;
	String cTag;
	String cFilter;
	
	// gettet setter
	public int getcUserCode() {
		return cUserCode;
	}
	public void setcUserCode(int cUserCode) {
		this.cUserCode = cUserCode;
	}
	public int getSno() {
		return sno;
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
	public String getcTag() {
		return cTag;
	}
	public void setcTag(String cTag) {
		this.cTag = cTag;
	}
	public String getcFilter() {
		return cFilter;
	}
	public void setcFilter(String cFilter) {
		this.cFilter = cFilter;
	}
	public int getHisItemSno() {
		return hisItemSno;
	}
	public void setHisItemSno(int hisItemSno) {
		this.hisItemSno = hisItemSno;
	}
	
}
