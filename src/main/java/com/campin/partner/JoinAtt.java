// 첨부파일을 한 건 저장하는 요소
package com.campin.partner;

public class JoinAtt {
	
	int cUserCode;
	String oriFile; // 사용자가 올린 파일
	String busiImg; // 시스템에 저장된 파일 -> uuid 로 발생한 넘버 와 원래 파일 이름 //db용량을 줄이기 위해 orifile 이름 없애고 나중에 그냥 합치는 방법도 있다
	String travelImg; 

	//getter&setter
	public int getcUserCode() {
		return cUserCode;
	}
	public void setcUserCode(int cUserCode) {
		this.cUserCode = cUserCode;
	}
	public String getOriFile() {
		return oriFile;
	}
	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}
	public String getBusiImg() {
		return busiImg;
	}
	public void setBusiImg(String busiImg) {
		this.busiImg = busiImg;
	}
	public String getTravelImg() {
		return travelImg;
	}
	public void setTravelImg(String travelImg) {
		this.travelImg = travelImg;
	}



}
