package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.partner.BoardVo;
import com.campin.partner.PageBoard;


@Mapper
@Repository
public interface BoardMapperC {
	public int insert(BoardVo vo);
	public int insertUpdate(BoardVo vo);
	public int selectAnswer(BoardVo vo);
	public String selectDoc(int sno);
	public String selectqTitle(int sno);
	public int selectcmapCode(int cuserCode);
	public int totSize(PageBoard page);
	public List<BoardVo> select(PageBoard page);
	public int update(BoardVo vo);
	public int delete(int sno);
}
