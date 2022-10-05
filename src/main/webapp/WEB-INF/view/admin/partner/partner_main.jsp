<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캠핀 파트너</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_main.js"> </script>
</head>
<body>
	<div id="partner_main">
		<div class="partner_sub">
			<form id="frm_partner_main" method="post">
				<input type="hidden" name="id" value=${sessionScope.id }>
				<input type="hidden" name="cUserCode" value="${sessionScope.cUserCode }">
				<input type="hidden" id="itemCode" name="itemCode" value="${sessionScope.itemCode }">
				<input type="hidden" name="state" value="${sessionScope.state }">	
			</form>
			<div class="logo">
				<img src="./image/icon/logo_white.png" width="150px">
			</div>
			
			<div class="icon">
				<img class="icon_img" src="./image/icon/partner_tent.png">
			</div>
			
			<div class="partner_menu">
				<div class="partner_id">
				<%--파트너와 관리자 구분 아이콘 넣기 --%>
					<span>[${sessionScope.id }님]</span><button onclick="partner.logout()">로그아웃</button>
				</div>
			
				<ul>
					<li onclick="partner.sales()">매출 조회</li>
					<li onclick="partner.qnaList()">문의 관리</li>
					<li onclick="partner.reviewList()">리뷰 관리</li>
					<li onclick="partner.reservationList()">예약 관리</li>
					<li onclick="partner.modify()">프로필 수정</li>
					<li onclick="partner.pwdModify()">비밀번호 변경</li>
					<li onclick="partner.requestList()">관리자 문의</li>	
		 			<li onclick="partner.campInput()" class="state camp_input">캠핑장 등록</li>
		 			<li onclick="partner.campModify()" class="state camp_modify">캠핑장 수정</li>
		 			<li onclick="partner.siteInput()">사이트 등록 / 수정</li>
				</ul>
				
			</div>
		</div>
		
		<div>
			<div class="today_notice">
				
			</div>
				
			<div class='partner_content'>
				<jsp:include page="${inc }"/>
			</div>
			
		</div>	
			
	</div>
</body>
</html>