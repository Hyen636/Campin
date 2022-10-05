package com.campin.partner;

public class ItemAtt {
	int sno;
	int itemCode;
	int hisItemSno;
	int hisDetailSno;
	String itemType;
	String oriFile;
	String sysFile;
	
	//
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
	public int getHisItemSno() {
		return hisItemSno;
	}
	public void setHisItemSno(int hisItemSno) {
		this.hisItemSno = hisItemSno;
	}
	public int getHisDetailSno() {
		return hisDetailSno;
	}
	public void setHisDetailSno(int hisDetailSno) {
		this.hisDetailSno = hisDetailSno;
	}
}
