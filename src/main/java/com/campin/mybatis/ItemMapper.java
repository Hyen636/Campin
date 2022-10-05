package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.partner.CategoryVo;
import com.campin.partner.ItemAtt;
import com.campin.partner.ItemOptionVo;
import com.campin.partner.ItemVo;
import com.campin.partner.SiteVo;

@Mapper
@Repository
public interface ItemMapper {
	public int getMaxItemCode();
	public int getMaxHisItemSno();
	public int getItemCode(int cUserCode);
	public int getcUserCode(int itemCode);
	public String getcUserName(int cUserCode);
	// 캠핑장 입력
	public int insertItem(ItemVo vo);
	public int insertCategory(List<CategoryVo> list);
	public int insertItemOption(List<ItemOptionVo> list);
	public int insertItemAtt(List<ItemAtt> list);
	public int updateItem(ItemVo vo);
	// 캠핑장 조회
	public ItemVo selectItem(int itemCode);
	public List<ItemAtt> selectItemImg(int itemCode);
	public List<CategoryVo> selectCategory(int itemCode);
	public List<ItemOptionVo> selectItemOption(int itemCode);
	public ItemVo selectHisItem(int hisItemSno);
	public List<ItemAtt> selectHisItemImg(int hisItemSno);
	public List<CategoryVo> selectHisCategory(int hisItemSno);
	public List<ItemOptionVo> selectHisItemOption(int hisItemSno);
	
	// 캠핑장 삭제
	public int deleteItemOption(int itemCode);
	public int deleteCategory(int itemCode);
	public int deleteItemAttMap(int itemCode);
	public int deleteItemAttWH(int itemCode);
	public int rejectItem(ItemVo vo); // 반려

	// 히스토리 넣기
	public int insertItemHistory(ItemVo vo);
	public int insertCategoryHistory(List<CategoryVo> list);
	public int insertItemOptionHistory(List<ItemOptionVo> list);
	public int insertItemAttHistory(List<ItemAtt> list);
	
	// site
	public List<SiteVo> selectSiteList(int itemCode);
	public int getMaxSiteCode(int itemCode);
	public int getMaxHisDetailSno(int itemCode);
	public int insertSite(SiteVo vo);
	public int insertSiteAtt(List<ItemAtt> list);
	public int insertSiteHistory(SiteVo vo);
	public int insertSiteAttHistory(List<ItemAtt> list);
	public SiteVo selectSite(SiteVo vo);
	public List<ItemAtt> selectSiteImg(SiteVo vo);
	public SiteVo selectSiteHistory(int hisDetailSno);
	public List<ItemAtt> selectSiteImgHistory(int hisDetailSno);
	public int updateSite(SiteVo vo);
	public int deleteSiteAtt(SiteVo vo);
	public int rejectSite(SiteVo vo);
	
	public String getcUserEmail(int cUserCode);
}
