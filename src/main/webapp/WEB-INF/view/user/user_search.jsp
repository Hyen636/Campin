<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Campin</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/user/user_search.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_search.js"></script>

<!-- 카카오맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7f69fea2d71ff6dc535beb76e046991"></script>

</head>
<body>
<div id="user_search">
	
		<header>
		<form name='frm' id='frm' method='post'>
			<input type="hidden" name='userId' id='userId' value='${sessionScope.id}'/><!--  -->
			<input type="hidden" name='userCode' id='userCode' value='${sessionScope.userCode}'  />
			<div>
				<div class="logo">
					<img src="./image/icon/logo.png" onclick='search.index_page()'>
				</div>
				<div class="option">
					<div onclick="search.region()" class="region_toggle ">
						<input type="text" name="region" class="input_region input_check" placeholder="지역" readonly value='${page.region}'>
						<img src="./image/icon/pin.png">
					</div>
					<div onclick="search.calendar()" class="cal_toggle">
						<input type="text" name="check_in" class="check_in input_check" placeholder="체크인" readonly value='${page.check_in}'>
						<img src="./image/icon/pin.png">
					</div>
					<div onclick="search.calendar()" class="cal_toggle">
						<input type="text" name="check_out" class="check_out input_check" placeholder="체크아웃" readonly value='${page.check_out}'>	
						<img src="./image/icon/pin.png">		
					</div>
					<div onclick="search.headCount()" class="head_count_toggle">	
						<input type="text" name="headcount" class="input_head_count input_check" placeholder="인원" readonly value='${page.headcount}'>
						<img src="./image/icon/pin.png">
					</div>
					<img src="./image/icon/go.png" class="go" onclick='search.searchGo()' />
					
				</div>
				<div class="user">
					<!-- <button type='button' id='tempChange' value='logout' onclick='search.tempChange()'>로그인 상태 = logout</button> -->
					<img src="./image/icon/user.png" class="user_icon"/>
					<ul class="user_menu">
					<!-- 수경 수정 -->
					<c:choose>
						<c:when test="${empty sessionScope.id}">
							<li><div><a href='#' class='login' id='login_a' onclick='javascript:document.frm.loginBtn.onclick()'>로그인</a></div></li>
							<li><div><a href='#' class='login' id='join_a' onclick='javascript:document.frm.joinBtn.onclick()'>회원가입</a></div></li>
						</c:when>
						<c:otherwise>
							<li><div><a href='#' class='login' id='logout_a' onclick='javascript:document.frm.logoutBtn.onclick()'>로그아웃</a></div></li>
							<li><div><a href='#' class='login' id='mypage_a' onclick='javascript:document.frm.mypageBtn.onclick()'>마이페이지</a></div></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</div>
			</div>
			<!-- 지역 -->
			<div>
				<div class="regions region_toggle">
					<ul>
						<li class="region">전체</li>
						<li class="region">서울</li>
						<li class="region">인천</li>
						<li class="region">경기도</li>
						<li class="region">강원도</li>
						<li class="region">충청도</li>
						<li class="region">경상도</li>
						<li class="region">전라도</li>
						<li class="region">제주도</li>
					</ul>
				</div>
			</div>
			<!-- 달력 -->
			<div>			
				<div class="calendar cal_toggle">
				    <div class="cal_header"> </div>
				    <div class="cal_division">
				    	<div class="cal">	
					        <div class="days"> </div>
					        <div class="date date1"> </div>
				        </div>
				        <div class="cal">
					        <div class="days"> </div>
					        <div class="date date2"> </div>
					    </div>
				    </div>
				    <div class="cal_save"></div>
				</div>
			</div>
			<!-- 인원수 -->
			<div>
				<div class="head_count head_count_toggle">
					<div class="adult">
						<div>
							<div>성인</div>
							<div>만 13세 이상</div>
						</div>
						<div>
							<img src="./image/icon/green_minus.png" class="minus adult_minus">
							<span class="adult_count">${page.adultCount}</span>
							<img src="./image/icon/green_plus.png" class="plus adult_plus">
						</div>
					</div>
					<div class="child">
						<div>
							<div>어린이</div>
							<div>만 12세 미만</div>
						</div>
						<div>
							<img src="./image/icon/green_minus.png" class="minus child_minus">
							<span class="child_count">${page.childCount}</span>
							<img src="./image/icon/green_plus.png" class="plus child_plus">
						</div>
					</div>
				</div>
			</div>
			
			
			
			
			<!-- <input type="text" name='region' class="region" placeholder="지역" value='${page.region}'>  -->
			<input type="hidden" name="checkIn" id='checkIn' placeholder="입실일" value='${page.checkIn}'>
			<input type="hidden" name="checkOut" id='checkOut' placeholder="퇴실일" value='${page.checkOut}'>
			<input type="hidden" name="adultCount" id='adultCount' placeholder="성인" value='${page.adultCount}'>
			<input type="hidden" name="childCount" id='childCount' placeholder="어린이" value='${page.childCount}'>
			
			<input type="hidden" name="tagSave2" class="tagSave2" id="tagSave2" placeholder="클릭된 tag String" value="${page.tagSearch}">
			<input type="hidden" name="searchState" id="searchState" value='${page.searchState}'>	<!-- index에서 선택한 tag -->
			<input type="hidden" name="searchbar2" id="searchbar2" value='${page.searchbar}'>
			<input type="hidden" name="input_search2" class="input_search2" id="input_search2" value='${page.input_search}'>
			
			<!-- detail을 위한 itemCode저장 -->
			<input type="hidden" name="detailItemCode" id="detailItemCode" value='${page.detailItemCode}'>
			<input type="hidden" name="checkInSave" id="checkInSave" value='${page.checkInSave}'>
			<input type="hidden" name="checkOutSave" id="checkOutSave" value='${page.checkOutSave}'>
			<input type="hidden" name="adultCountSave" id="adultCountSave" value='${page.adultCountSave}'>
			<input type="hidden" name="childCountSave" id="childCountSave" value='${page.childCountSave}'>
			
			<!-- 수경 수정 -->
			<c:choose>
				<c:when test="${empty sessionScope.id}">
					<button type='button' hidden='hidden' name="loginBtn" onclick='search.login_page()'>hidden_a태그 로그인</button>
					<button type='button' hidden='hidden' name="joinBtn" onclick='search.join_page()'>hidden_a태그 회원가입</button>
				</c:when>
				<c:otherwise>
					<button type='button' hidden='hidden' name="logoutBtn" onclick='search.logout_page()'>hidden_a태그 로그아웃</button>
					<button type='button' hidden='hidden' name="mypageBtn" onclick='search.mypage_page()'>hidden_a태그 마이페이지</button>
				</c:otherwise>
			</c:choose>
			
			<div class="text_hidden_area">
				
			</div>
		</form>
	</header>
	
	<div id='inc_div'>
		<jsp:include page="${inc}"/>
	</div>
	<footer>
		<div></div>
	</footer>
</div>
</body>
<!-- 수경 추가 -->
<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>
</html>