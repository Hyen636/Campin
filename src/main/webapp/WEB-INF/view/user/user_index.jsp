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
<link rel='stylesheet' type='text/css' href='./css/user/user_index.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_index.js"></script>
<script>
	let sessionID = '${sessionScope.id}';
</script>
</head> 
<body>
<div id='user_index'>
	<form method="post" name='frm_index' class="frm_index" id="frm_index">
		<header class="header">
			<div class="logo">
				<img src="./image/icon/logo.png" class="logo_img"/>
			</div>
				
			<div class="search">
					<div onclick="main.region()" class="region_toggle ">
						<input type="text" name='region' class="region" placeholder="지역" value="전체">
						<img src="./image/icon/pin.png">
					</div>
					<div onclick="main.calendar()" class="cal_toggle">
						<input type="text" name='check_in' class="check_in" placeholder="체크인" readonly>
						<img src="./image/icon/pin.png">
					</div>
					<div onclick="main.calendar()" class="cal_toggle">
						<input type="text" name='check_out' class="check_out" placeholder="체크아웃" readonly>	
						<img src="./image/icon/pin.png">		
					</div>
					<div onclick="main.headCount()" class="head_count_toggle">	
						<input type="text" name='headcount' class="headcount" placeholder="인원">
						<img src="./image/icon/pin.png">
					</div>
					<div>	
						<input type="text" class="searchbar" name='searchbar' placeholder="지금 떠나고 싶은 캠핑장을 검색하세요!">
						<img src="./image/icon/search_green.png" class="search_icon" onclick='main.search_page()'>
					</div>
			</div>
		</header>
		
		<section class="section">
			<div>	<!-- 지역 -->
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
			
			<div>	<!-- 달력 -->
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
			
			<div><!-- 인원수 -->
				<div class="head_count head_count_toggle">
					<div class="adult">
						<div>
							<div>성인</div>
							<div>만 13세 이상</div>
						</div>
						<div>
							<img src="./image/icon/green_minus.png" class="minus adult_minus">
							<span class="adult_count">0</span>
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
							<span class="child_count">0</span>
							<img src="./image/icon/green_plus.png" class="plus child_plus">
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<!-- form을 다로 지정하여 클릭 시 search (단 한개의 Tag로 검색됨.) -->
		<div class="tag">
			<button type="button" value="반려견" onclick="main.tag('반려견')">#반려견</button>
			<button type="button" value="재미있는" onclick="main.tag('재미있는')">#재미있는</button>
			<button type="button" value="조용한" onclick="main.tag('조용한')" >#조용한</button>
			<button type="button" value="별" onclick="main.tag('별')">#별</button>
			<button type="button" value="커플" onclick="main.tag('커플')">#커플</button>
			<button type="button" value="휴식" onclick="main.tag('휴식')">#휴식</button>
			<button type="button" value="감성" onclick="main.tag('감성')">#감성</button>
			<button type="button" value="친구" onclick="main.tag('친구')">#친구</button>
			<button type="button" value="풍경" onclick="main.tag('풍경')">#풍경</button>
			<button type="button" value="레저" onclick="main.tag('레저')">#레저</button>
		</div>
		
		
		<input type="hidden" name="checkIn" id='checkIn' placeholder="입실일" value="0">
		<input type="hidden" name="checkOut" id='checkOut' placeholder="퇴실일" value="0">
		<input type="hidden" name="adultCount" id='adultCount' placeholder="성인" value="0">
		<input type="hidden" name="childCount" id='childCount' placeholder="어린이" value="0">
		
		<input type="hidden" name="tagSearch" id='tagSearch' placeholder="tag" value="">
	</form>
</div>

</body>
<!-- 수경 추가 -->
<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>
</html>