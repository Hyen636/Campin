package com.campin.partner;

import java.util.List;

public interface BoardInterface {
	public int selectcmapCode(int cuserCode);
	public int selectAnswer(BoardVo vo);
	public String selectDoc(int sno);
	public String selectqTitle(int sno);
	public List<BoardVo> select(PageBoard page);
	public int insert(BoardVo vo);
	public boolean update(BoardVo vo);
	public boolean delete(BoardVo vo);
}
