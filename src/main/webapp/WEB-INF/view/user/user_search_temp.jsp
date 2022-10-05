<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<nav class="nav">
		<div>
			<img src="./image/icon/menu_green.png" class="btn_filter">
		</div>
		
		<div class="category">
			<div class="category_icon">
				<img src="./image/icon/camping_green.png" alt="오토캠핑" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>오토캠핑</span>
			</div>
			<div>
				<img src="./image/icon/glamping_green.png" alt="글램핑" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>글램핑</span>
			</div>
			<div>
				<img src="./image/icon/caravan_green.png" alt="카라반" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>카라반</span>
			</div>
			<div>
				<img src="./image/icon/car_green.png" alt="차박" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>차박</span>
			</div>
			<div>
				<img src="./image/icon/picnic_green.png" alt="피크닉" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>피크닉</span>
			</div>
			<div>
				<img src="./image/icon/pet_green.png" alt="반려동물" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>반려동물</span>
			</div>
			<div>
				<img src="./image/icon/baby_green.png" alt="키즈" onclick="search2.ShortCutCategory(this)"> <br/>
				<span>키즈</span>
			</div>
		</div>
		
		<!-- form -->
		
			<div class="search-box">
				<button class="btn-search" type="button"><img src="./image/icon/search_green.png"></button>
				<input type="hidden" name="nowPage">
				<input type="text" name="input_search" class="input_search" id="input_search" placeholder="캠핑장을 검색하세요!" value='${page.searchbar}'>
			</div>
			<!-- <form method="post" name="frm_search">
		</form> -->
	</nav>

	<aside class="filter">
		<img src="./image/icon/close.png" class="btn_filter_close"/>
		<div class="filter_warp">
			<div class="tags">
				<button class="tag" value="차박" onclick='search2.tagBtn(this)'>#차박</button>
				<button class="tag" value="조용한" onclick='search2.tagBtn(this)'>#조용한</button>
				<button class="tag" value="깨끗한" onclick='search2.tagBtn(this)'>#깨끗한</button>
				<button class="tag" value="가족" onclick='search2.tagBtn(this)'>#가족</button>
				<button class="tag" value="휴식" onclick='search2.tagBtn(this)'>#휴식</button>
				<button class="tag" value="재미있는" onclick='search2.tagBtn(this)'>#재미있는</button>
			</div>
			<div>
				<span>숙박형태</span>
				<div>
					<input type="checkbox" name="filtercheck" id="stay_radio1" value="오토캠핑">
					<label for="stay_radio1">오토캠핑</label>
					<input type="checkbox" name="filtercheck" id="stay_radio2" value="글램핑">
					<label for="stay_radio2">글램핑</label>
					<input type="checkbox" name="filtercheck" id="stay_radio3" value="카라반">
					<label for="stay_radio3">카라반</label>
					<input type="checkbox" name="filtercheck" id="stay_radio4" value="펜션">
					<label for="stay_radio4">펜션</label>
					<input type="checkbox" name="filtercheck" id="stay_radio5" value="방갈로">
					<label for="stay_radio5">방갈로</label>
					<input type="checkbox" name="filtercheck" id="stay_radio6" value="차박">
					<label for="stay_radio6">차박</label>
					<input type="checkbox" name="filtercheck" id="stay_radio7" value="피크닉">
					<label for="stay_radio7">피크닉</label>
					<input type="checkbox" name="filtercheck" id="stay_radio8" value="키즈">
					<label for="stay_radio8">키즈</label>
				</div>
			</div>
			<div>
				<span>환경</span>
				<div>
					<input type="checkbox" name="filtercheck" id="environment_radio1" value="바다">
					<label for="environment_radio1">바다</label>
					<input type="checkbox" name="filtercheck" id="environment_radio2" value="산">
					<label for="environment_radio2">산</label>
					<input type="checkbox" name="filtercheck" id="environment_radio3" value="강">
					<label for="environment_radio3">강</label>
					<input type="checkbox" name="filtercheck" id="environment_radio4" value="호수">
					<label for="environment_radio4">호수</label>
					<input type="checkbox" name="filtercheck" id="environment_radio5" value="계곡">
					<label for="environment_radio5">계곡</label>
					<input type="checkbox" name="filtercheck" id="environment_radio6" value="섬">
					<label for="environment_radio6">섬</label>
					<input type="checkbox" name="filtercheck" id="environment_radio7" value="평야">
					<label for="environment_radio7">평야</label>
				</div>
			</div>	
			<div>
				<span>입지 형태</span>
				<div>
					<input type="checkbox" name="filtercheck" id="position_radio1" value="파쇄석">
					<label for="position_radio1">파쇄석</label>
					<input type="checkbox" name="filtercheck" id="position_radio2" value="데크">
					<label for="position_radio2">데크</label>
					<input type="checkbox" name="filtercheck" id="position_radio3" value="잔디">
					<label for="position_radio3">잔디</label>
					<input type="checkbox" name="filtercheck" id="position_radio4" value="모래">
					<label for="position_radio4">모래</label>
					<input type="checkbox" name="filtercheck" id="position_radio5" value="자갈">
					<label for="position_radio5">자갈</label>
					<input type="checkbox" name="filtercheck" id="position_radio6" value="혼합">
					<label for="position_radio6">혼합</label>
				</div>
			</div>	
			<div>
				<span>기본 시설</span>
				<div>
					<input type="checkbox" name="filtercheck" id="facility_radio1" value="화장실">
					<label for="facility_radio1">화장실</label>
					<input type="checkbox" name="filtercheck" id="facility_radio2" value="샤워대">
					<label for="facility_radio2">샤워대</label>
					<input type="checkbox" name="filtercheck" id="facility_radio3" value="개수대">
					<label for="facility_radio3">개수대</label>
					<input type="checkbox" name="filtercheck" id="facility_radio4" value="매점">
					<label for="facility_radio4">매점</label>
					<input type="checkbox" name="filtercheck" id="facility_radio5" value="카페">
					<label for="facility_radio5">카페</label>
					<input type="checkbox" name="filtercheck" id="facility_radio6" value="와이파이">
					<label for="facility_radio6">와이파이</label>
				</div>
			</div>
			<div>
				<span>부가 시설</span>
				<div>
					<input type="checkbox" name="filtercheck" id="option_radio1" value="놀이시설">
					<label for="option_radio1">놀이시설</label>
					<input type="checkbox" name="filtercheck" id="option_radio2" value="체험활동">
					<label for="option_radio2">체험활동</label>
					<input type="checkbox" name="filtercheck" id="option_radio3" value="수영장">
					<label for="option_radio3">수영장</label>
					<input type="checkbox" name="filtercheck" id="option_radio4" value="트램펄린">
					<label for="option_radio4">트램펄린</label>
					<input type="checkbox" name="filtercheck" id="option_radio5" value="산책로">
					<label for="option_radio5">산책로</label>
					<input type="checkbox" name="filtercheck" id="option_radio6" value="장비대여">
					<label for="option_radio6">장비대여</label>
					<input type="checkbox" name="filtercheck" id="option_radio7" value="반려동물">
					<label for="option_radio7">반려동물</label>
					<input type="checkbox" name="filtercheck" id="option_radio8" value="트레일러">
					<label for="option_radio8">트레일러</label>
					<input type="checkbox" name="filtercheck" id="option_radio9" value="카라반 진입">
					<label for="option_radio9">카라반 진입</label>
				</div>
			</div>
			<div>
				<span>레저</span>
				<div>
					<input type="checkbox" name="filtercheck" id="leisure_radio1" value="물놀이">
					<label for="leisure_radio1">물놀이</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio2" value="해수욕장">
					<label for="leisure_radio2">해수욕장</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio3" value="낚시">
					<label for="leisure_radio3">낚시</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio4" value="MTB">
					<label for="leisure_radio4">MTB</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio5" value="등산">
					<label for="leisure_radio5">등산</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio6" value="스키">
					<label for="leisure_radio6">스키</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio7" value="수상레저">
					<label for="leisure_radio7">수상레저</label>
					<input type="checkbox" name="filtercheck" id="leisure_radio8" value="기타3">
					<label for="leisure_radio8">기타</label>
				</div>
			</div>
		</div>
		
		<!-- 필터 click 시 저장되어야 하는 Str -->
		<input type="hidden" class="filterSave" id="filterSave" placeholder="클릭된 filter String" value='${page.checkfilter}'>
		<input type="hidden" class="filterSaveCnt" id="filterSaveCnt" placeholder="클릭된 filterCnt Integer" value='${page.checkfilterCnt}'>
		
		<input type="hidden" id="searchState2" value='${page.searchState}'>	<!-- 선택한 type -->
		<input type="hidden" name="tagSave" class="tagSave" id="tagSave" value="${page.tagSearch}">
	</aside>
	
	
	<section>
		<div>
			<c:forEach var='items' items='${list}'>
				<div class="content">
					<form method='post' name='frm_content' class='frm_content' id='frm_content'>
						<c:choose>
							<c:when test="${empty sessionScope.id}">
								<img src="./image/icon/not_wish.png" class="not_wish" onclick="search2.wish(this)">
							</c:when>
							<c:otherwise>
								<img src="./image/icon/not_wish.png" class="not_wish" onclick="search2.wish(this)">
							</c:otherwise>
						</c:choose>
						<img src="${items.bestCampImg}" onclick="btnDetailPage.onclick()">
						<span>1박 ${items.price}원~~</span>
						<div>
							<p>${items.iName} | ${items.sido}</p>
							<div id='items_info'>${items.infoText}</div>
							<c:forEach var='cateItems' items='${items.cateList}'>
								<img src="./image/icon/test/${cateItems}.png">
							</c:forEach>			
						</div>
						
						<button type='button' hidden='hidden' name="btnDetailPage" onclick='search2.search_detail_page(this.form)'>hidden 상세페이지</button>
						<input type="hidden" name="checkInSave" id='checkInSave' placeholder="입실일" value='${page.checkIn}'>
						<input type="hidden" name="checkOutSave" id='checkOutSave' placeholder="퇴실일" value='${page.checkOut}'>
						<input type="hidden" name="adultCountSave" id='adultCountSave' placeholder="성인" value='${page.adultCount}'>
						<input type="hidden" name="childCountSave" id='childCountSave' placeholder="어린이" value='${page.childCount}'>	
								
						<!-- detail을 위한 itemCode -->
						<input type="hidden" name="detailItemCode" id="detailItemCode" class="detailItemCode2" value='${items.itemCode}'>
						<input type="hidden" name='userCode' id='userCode2' value='${sessionScope.userCode}' />
					</form>
				</div>
			</c:forEach>
		</div>
	</section>
	
		
<script defer src="./js/user/user_searchInc.js"></script>
</body>
</html>