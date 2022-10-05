<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="partner_reservation_temp_list">
	<div class="list">
		<div class="title">
			<span class="no">예약번호</span>
			<span class="siteName">사이트</span>
			<span class="check_in_date">입실일</span>
			<span class="check_out_date">퇴실일</span>
			<span class="tot">인원</span>
			<span class="mName">이름</span>
			<span class="phone">연락처</span>
			<span class="car">차량번호</span>
			<span class="status">예약상태</span>
		</div>
		<div class="items">
			<c:forEach var="vo" items="${dateList }" varStatus="index">
				<div class="item" onclick="partnerReservation.detail(this)">
					<span class="no">${vo.orderCode }</span>
					<span class="siteName">${vo.siteName }</span>
					<span class="check_in_date">${vo.dateStr }</span>
					<span class="check_out_date">${vo.dateEnd }</span>
					<span class="tot">${vo.adult+vo.child }</span>
					<span class="mName">${vo.mName }</span>
					<span class="phone">${vo.phone }</span>
					<span class="car">${vo.userCar }</span>
					<span class="status">${vo.state }</span>
				</div>			
			</c:forEach>
		</div>
	</div>	
	<div class="paging">
		<c:if test="${page.startPage > 1 }" >
			<button type="button" id="btnFirst" onclick="partnerReservation.movePage(1)">처음</button>
			<button type="button" id="btnPrev" onclick="partnerReservation.movePage(${page.startPage-1})">이전</button>
		</c:if>
		<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
			<button type="button" class="btnPage" onclick="partnerReservation.movePage(${i})">${i }</button>
		</c:forEach>
		<c:if test="${page.endPage < page.totPage }">
			<button type="button" id="btnNext" onclick="partnerReservation.movePage(${page.endPage+1})">다음</button>
			<button type="button" id="btnLast" onclick="partnerReservation.movePage(${page.totPage})">끝</button>
		</c:if>	
	</div>
</div>
</body>
</html>