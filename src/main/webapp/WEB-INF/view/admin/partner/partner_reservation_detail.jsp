<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_reservation_detail</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_reservation_detail.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_reservation.js"></script>
</head>
<body>
<div id='partner_reservation_detail'>
	<span class="title">정보 확인 후 예약 확정/취소 진행 가능합니다.</span>
	<form name='frm_partner_reservation_detail' id='frm_partner_reservation_detail' method='post'>
		<input type="hidden" name="orderCode" value="${vo.orderCode }" />
		<input type="hidden" name="email" value="${vo.email }" />
		<input type="hidden" name="mName" value="${vo.mName }" />
		<input type="hidden" name="iName" value="${vo.iName }" />
		<input type="hidden" name="itemCode" value="${vo.itemCode }" />
		<input type="hidden" name="state" />
	</form>
	<div class='reservation_detail_info'>
		<h4>예약자 정보</h4>
		<span class="item">예약자명</span> <span>${vo.mName }</span> <br/>
		<span class="item">예약번호</span> <span>${vo.orderCode }</span> <br/>
		<span class="item">연락처</span> <span>${vo.phone }</span> <br/>
		<span class="item">이메일</span> <span>${vo.email }</span> <br/>
		<c:if test="${vo.payment == '무통장입금' }">
			<span class="item">환불계좌</span>	<span>${vo.refBank } ${vo.refAct }</span> <br/>
		</c:if>
		<span class="item">차량번호</span> <span>${vo.userCar }</span> <br/>
		<span class="item">반려동물</span> <span>${(vo.pet=='t')? 'O' : 'X' }</span> <br/>
		<span class="item">요청사항</span> <br/>
		<textarea class="comment" disabled>${vo.doc }</textarea>
	</div>

	<div class='camp_info'>
		<h4>예약 정보</h4>
		<div class='pre_img1'>
			<img class="preImage_image1" src="./image/img/partner_22070311.jpg"/>
		</div>
		<span class="item">캠핑장</span> <span>${vo.iName }</span> <br/>
		<span class="item">사이트</span> <span>${vo.siteName }</span> <br/>
		<span class="item">추가옵션</span> <span>${vo.extraOpt}</span> <br/>
		<span class="item">체크인</span> <span class="dateStr">${vo.dateStr }</span> <br/>
		<span class="item">체크아웃</span> <span>${vo.dateEnd }</span> <br/>
		<span class="item">인원</span> <span>${vo.adult + vo.child }</span> <br/>
		<span class="item">성인</span> <span>${vo.adult }</span> <br/>
		<span class="item">아동</span> <span>${vo.child }</span> <br/>
	</div>
	
	<div class='pay_info'>
		<h4>결제내역</h4>
		<span class="item">결제수단</span> <span>${vo.payment }</span> <br/>
		<span class="item">예약상태</span> <span>${vo.state }</span> <br/>
		<span class="item">예약 금액</span>
		<span><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.price }" />원</span> <br/>
		<span class="item">옵션 금액</span> 
		<span><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.extraFee }" />원</span> <br/>
		<span class="item">결제금액</span>
		<span><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.amt}" />원</span> <br/>
		<span class="item">캠핀 수수료(예약금 10%)</span> 
		<span><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.price/10 }" />원</span> <br/> 
		<span class="item ">수수료를 제외한 금액</span> 
		<span><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.price* 9/10 + vo.extraFee }" />원</span> 
	</div>
	
	<div class="btns_detail">
		<c:if test="${vo.state ==  '예약대기'}">
			<button type='button' class='btn_confirm' onclick="partnerReservation.confirm()">예약 확정</button> <br/>		
		</c:if>
		<c:if test="${vo.payment == '무통장입금' && vo.state == '입금대기'}">
			<button type='button' class="btn_deposit" onclick="partnerReservation.confirm()">입금확인, 예약 확정</button> <br/>
		</c:if>
		<c:if test="${vo.state != '예약취소'}">
			<button type='button' class='btn_cancel' onclick="partnerReservation.cancel()">예약취소</button>		
		</c:if>
		<button type='button' class='btn_back' onclick="partner.reservationList() ">돌아가기</button>
	</div>
</div>
</body>
</html>