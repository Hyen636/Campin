package com.campin.manager;

import java.util.List;



public interface RequestInterface {
	public List<RequestVo> select(PageReq page);
	public RequestVo selectView(Integer sno);
	public ReviewVoA selectOne(int reviewSno);
	public int updateAnswer(RequestVo vo);

	public int updatereview(RequestVo vo);
	//public int insert(RequestVo vo);
	//public boolean update(RequestVo vo);
	//public boolean delete(int sno);
	//public int repl(RequestVo vo);

}
