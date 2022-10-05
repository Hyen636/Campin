package com.campin.user;

import java.util.List;

public class UserSearchCategoryVo {
	int sno;
	int itemCode;
	String cTag;
	String cFilter;
	
	//카테고리 필터 클릭시 모두 해당되는 캠핑장만 보이게 하기위해
	String filterStr;
	
	
	/*
	public UserSearchCategoryVo() {}
	
	public UserSearchCategoryVo(int sno, int itemCode, String cTag, String cFilter) {
		this.sno = sno;
		this.itemCode = itemCode;
		this.cTag = cTag;
		this.cFilter = cFilter;
	}*/

	
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

	
	public String getFilterStr() {
		return filterStr;
	}

	public void setFilterStr(String filterStr) {
		this.filterStr = filterStr;
	}
	
	

}	