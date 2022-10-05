package com.campin.partner;

import java.util.List;

public interface ItemInterface {
	public void insertItem(ItemVo vo);
	public void insertCategory(List<CategoryVo> list);
	public void insertItemOption(List<ItemOptionVo> list);
	public void insertItemAtt(List<ItemAtt> list);
	
	public int insertItemHistory(ItemVo vo);
	public void insertCategoryHistory(List<CategoryVo> list);
	public void insertItemOptionHistory(List<ItemOptionVo> list);
	public void insertItemAttHistory(List<ItemAtt> list);
	public ItemVo selectItem(int itemCode);
	public List<CategoryVo> selectCategory(int itemCode);
	public List<ItemOptionVo> selectItemOption(int itemCode);
	public List<ItemAtt> selectItemAtt(int itemCode);
	
	
	public List<SiteVo> selectSiteList(int itemCode);
	public void insertSite(SiteVo vo);
	public void insertSiteAtt(List<ItemAtt> list);
	public int insertSiteHistory(SiteVo vo);
	public void insertSiteAttHistory(List<ItemAtt> list);
	public SiteVo selectSite(SiteVo vo);
	public List<ItemAtt> selectSiteImg(SiteVo vo);
	 
}
