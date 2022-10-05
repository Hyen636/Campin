package com.campin.partner;

public class RequestVo {

	int hisDetailSno;
	int hisUserSno;
	int hisItemSno;
	int siteCode;
	String siteName;
	
	int sno;
	int grp;
	int seq;
	
	String category; //업체등록 //회원
	String nal;
	int cUserCode;
	String mName;
	int itemCode;
	String iName;
	String title;
	
	String doc;
	String answer;
	String state; //등록요청 //등록반려
	
	int reviewSno;
	int orderCode;
	
	public RequestVo() {}

	
	//getter & setter
	
	
	
	public int getSno() {
		return sno;
	}

	

	public int getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}


	public int getcUserCode() {
		return cUserCode;
	}


	public void setcUserCode(int cUserCode) {
		this.cUserCode = cUserCode;
	}


	public int getItemCode() {
		return itemCode;
	}


	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}


	public int getHisDetailSno() {
		return hisDetailSno;
	}


	public void setHisDetailSno(int hisDetailSno) {
		this.hisDetailSno = hisDetailSno;
	}


	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getGrp() {
		return grp;
	}

	public void setGrp(int grp) {
		this.grp = grp;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNal() {
		return nal;
	}

	public void setNal(String nal) {
		this.nal = nal;
	}

	

	public int getReviewSno() {
		return reviewSno;
	}


	public void setReviewSno(int reviewSno) {
		this.reviewSno = reviewSno;
	}


	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	


	public int getHisUserSno() {
		return hisUserSno;
	}


	public void setHisUserSno(int hisUserSno) {
		this.hisUserSno = hisUserSno;
	}


	public int getHisItemSno() {
		return hisItemSno;
	}


	public void setHisItemSno(int hisItemSno) {
		this.hisItemSno = hisItemSno;
	}


	public int getSiteCode() {
		return siteCode;
	}


	public void setSiteCode(int siteCode) {
		this.siteCode = siteCode;
	}


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


}
