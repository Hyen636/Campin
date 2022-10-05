<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- 결제완료페이지--%>
<title>user_reservation</title>
<link rel="stylesheet" type="text/css" href="./css/user/user_detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/user/user_reservation.js"></script>
</head>
<body>
<div id="user_reservation">
	<form name="frm_user_reservation" class="frm_user_reservation" method="post">
	<h2>고객님의 결제가 정상적으로 완료되었습니다.</h2>
	
	<div class="pre_img">
		<img class="preImage_image11" src="./image/camping/${vo.itemCode}/${vo1.sysFile}"/>
	</div>
	
	<div class="reservation_info">
	
		<div class="reservation_info1">
			<label>예약번호</label>
			<input type="text" name="orderCode" size="30" value='${vo.orderCode}' readonly /><br/>
			<label>예약자명</label>
			<input type="text" name="irum" size="30" value="${vo.mName}" readonly /><br/>
			<label>연락처</label>
			<input type="text" name="phone" size='30' value='${vo.phone}' readonly /><br/>
		</div>

		<div class="reservation_info1">
			<label>사이트</label>
			<input type='text' name='camp_site'size='30' value='${vo.siteName}' readonly /><br/>
			<label>캠핑장</label>
			<input type="text" name="partner" size="30" value="${vo.iName}" readonly /><br/>
		</div>
		<div class="reservation_info1">
			<label>총 결제금액</label>
			<input type='text' name='priceAmt'size='20' value='${vo.amt}원' readonly /><br/>
			<!-- <div class="reservation_info3">
			<input type="date" name="cancelDate" value="">
				<p>취소기한 : 2022년 0월 0일 오후 23시 59분</p>
				<p>취소수수료 : 없음</p>
			</div> -->
			<label>결제수단</label>
			<input type='text' name='payment'size='20' value='${vo.payment}' readonly /><br/>
		</div>
				<!--<button type='button' class='btn_reservation_modify'>수정하기</button>  상세에서 수정가능 -->
		</div>
		
		<div class="btn_reservation">
			<button type='button' class='btn_mypage' onclick='user.user_reservation_detail(this.form)'>주문상세 보기</button>
			<button type='button' class='btn_user_reservation_detail' onclick='user.user_search(this.form)'>홈으로</button>
		</div>
	</form>
</div>
</body>
</html>