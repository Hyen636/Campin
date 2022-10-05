package com.campin.partner;

import java.util.List;




public interface RequestInterface {
	public List<RequestVo> select(PageReq page);
	public String selectiName(Integer itemCode);
	public String selectmName(Integer cUserCode);
	public int selectOrders(Integer itemCode);
	public RequestVo selectView(Integer sno);
	public ReviewVo selectOne(Integer reviewSno);
	public int insertReq(RequestVo vo);
	public int updateDoc(RequestVo vo);
	public int deleteReq(RequestVo vo);
}
