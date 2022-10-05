<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>오늘의 알림</h3>
	<ul>
		<li><span onclick="partner.notice('예약')">신규예약요청 ${vo.orderCount }건</span></li>
		<li>|</li>
		<li><span onclick="partner.notice('문의')">문의내역 ${vo.boardCount }건</span></li>
		<li>|</li>
		<li><span onclick="partner.notice('리뷰')">리뷰내역 ${vo.reviewCount }건</span></li>
	</ul>
</body>
</html>