
package com.campin.manager;

import java.util.List;


public interface ManagerjoinInterface {

	//C : 화면의 내용이 Vo로 만들어져서, 매개변수로 전달되면,
	//해당 내용을 처리하고 그 결과를 논리값으로 반환하겠다.
	public boolean insertR(AuserVo vo);
	
	//R : 1) 검색어를 매개변수로 전달받아 
	// 그 결과를 List<vo>로 만들어 반환하겠다.
	public List<AuserVo> select(PageAccountList page);
	//검색어로 전달받은 내용을 select라하고 List<StudentVo>에 반환?
	
	
	//R : 2) 아이디를 매개변수로 전달받아 그 결과를 vo로 만들어 반환하겠다.
	//public MemberVo selectOne(String id);
	
	
	//U : 화면의 내용을 vo로 만들어 매개변수로 전달되면 처리를 한 후에 논리값으로 반환하겠다.
	//public boolean update(MemberVo vo);
	//update(StudentVo vo)를 boolean으로 전달해라~~~~?
	
	
	//D : 아이디와 암호를 전달받아 삭제하고 그 결과를 논리값으로 반환 하겠다.
	//public boolean delete(MemberVo vo);
		
}
