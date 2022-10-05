package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.campin.user.PageSearch;
import com.campin.user.PageSearchDetail;
import com.campin.user.UserSearchCategoryVo;
import com.campin.user.UserSearchDetailBoardVo;
import com.campin.user.UserSearchDetailReviewVo;
import com.campin.user.UserSearchItemAttVo;
import com.campin.user.UserSearchItemDetailVo;
import com.campin.user.UserSearchItemVo;
import com.campin.user.UserSearchOrdersVo;
import com.campin.user.UserSearchQnaVo;
import com.campin.user.UserSearchSendReservationVo;
import com.campin.user.UserSearchVo;
import com.campin.user.UserWishlistVo;


@Mapper
@Repository
@Qualifier("userSearchMapper")
public interface UserSearchMapper {
	public int totSize(PageSearch page);
	
	public List<Integer> selectListIntAll(int totalCount);
	public List<Integer> selectListInt(PageSearch page);
	public Integer selectListSearchBar(UserSearchVo vo);
	public Integer selectListFilter(UserSearchCategoryVo vo);
	public Integer selectListTag(UserSearchCategoryVo vo);
	public List<Integer> selectListTagAll(String tagSearch);
	public Integer selectSiteCount(int itemCode);
	public Integer selectOrdersCount(UserSearchOrdersVo orderVo);
	public UserSearchItemVo selectItem(int itemCode);
	public Integer selectItemDetail(int itemCode);
	public List<UserSearchItemAttVo> selectItemAtt(int itemCode);
	public List<String> selectCategory(int itemCode);
	
	public void insertQna(UserSearchQnaVo vo);
	public Integer selectSnoMax();
	public void updateGrp(UserSearchQnaVo vo);
	
	
	public UserSearchItemVo selectCampDetailInfo(PageSearchDetail page);
	public List<UserSearchDetailBoardVo> selectDetailQna(PageSearchDetail page);
	public String selectUserName(int itemCode);
	public List<UserSearchDetailBoardVo> selectDetailQnaAnswer(PageSearchDetail page);
	
	public List<UserSearchDetailReviewVo> selectReviewStar(PageSearchDetail page);
	public Integer selectReviewCnt(PageSearchDetail page);
	public List<UserSearchDetailReviewVo> selectDetailReview(PageSearchDetail page);
	public List<UserSearchDetailReviewVo> selectDetailReviewAnswer(PageSearchDetail page);
	
	public List<UserSearchItemDetailVo> selectDetailCampSite(String itemCode);
	public String selectItemAddMap(String itemCode);
	public List<UserSearchItemDetailVo> selectItemDetailPeopleCheck(PageSearchDetail page);
	public Integer selectOrdersSiteCount(UserSearchOrdersVo orderVo);
	
	public Integer selectFindSiteCode(UserSearchSendReservationVo vo);
	public Integer selectFindcUserCode(UserSearchSendReservationVo vo);
	public List<UserSearchItemAttVo> selectSiteImage(PageSearchDetail page);
	
	public void insertWishList(UserWishlistVo vo);
	public void deleteWishList(UserWishlistVo vo);
	
	public List<Integer> findwishlist(int userCode);
	public List<String> getCategory(String itemCode);
	public Integer searchwishlist(int userCode);
	
	public List<UserSearchItemAttVo> selectCampHWImg(PageSearchDetail page);
	public List<UserSearchItemAttVo> selectSiteCnt(String itemCode);
	
}


