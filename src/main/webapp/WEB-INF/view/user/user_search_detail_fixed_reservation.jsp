<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_fixed_load</title>
<script defer src="./js/user/user_search_detail_fixed_reservation.js"></script>
</head>
<body>
	<div class="reservation">
		<label class="reservation_title">가격/날짜</label><br/><br/>
		<div class="reservation_inputs">
			<label class="reservation_subtitle">날짜 선택</label><br/>
			<label>입실일</label><input type="date" class="reservaion_checkin" value="${page.checkInSave}" /><br/>
			<label>퇴실일</label><input type="date" class="reservaion_checkout" value="${page.checkOutSave}" />
			<input type="hidden" id="saveBeforeCheckOut" value="${page.checkOutSave}" />
			<br/><br/>
			<label class="reservation_subtitle">인원 선택</label><br/>
			<div class="ea_btns item_css">
				<label>성인 </label><img src="./image/icon/green_minus.png" class="minus_icon" id="adult_minus" width="16px"/>
				<input class="num_adult" type="text" size="1" value="${page.adultCountSave}">
				<img src="./image/icon/green_plus.png" class="plus_icon" id="adult_plus" width="16px"/><br/>
				<label>아동 </label><img src="./image/icon/green_minus.png" class="minus_icon" id="child_minus" width="16px"/>
				<input class="num_child" type="text" size="1" value="${page.childCountSave}">
				<img src="./image/icon/green_plus.png" class="plus_icon" id="child_plus" width="16px"/><br/>
			</div><br/>
		
			<label class="reservation_subtitle">사이트 선택</label>
			<select id="siteSelected">
				<option val="0">-</option>
				<c:forEach var='site_items' items='${list}'>
					<option val="${site_items.siteCode}">${site_items.siteName}</option>
				</c:forEach>
			</select>
			<br/><br/><hr/><br/>
			<!-- 
			<label class="reservation_subtitle">총 합계</label><input type="text" name="tot_amt"/><br/><br/> -->
		</div>
		<button class="btn_reservation" onclick='user_search_detail_reservation.reservation_input_page()'>예약하기</button>
	</div>
			
	<!-- <button type="button" class="btn_top">TOP</button> -->
</body>
</html>