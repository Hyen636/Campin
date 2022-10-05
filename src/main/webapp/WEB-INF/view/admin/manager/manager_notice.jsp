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
	<li><span onclick="manager.notice('회원')">캠지기 회원 등록 요청 ${vo.userCount }건</span></li>
	<li>|</li>
	<li><span onclick="manager.notice('캠핑장')">캠핑장 관련 요청 ${vo.campCount }건</span></li>
	<li>|</li>
	<li><span onclick="manager.notice('탈퇴')">탈퇴 요청 ${vo.dropCount }건</span></li>
	<li>|</li>
	<li><span onclick="manager.notice('리뷰')">리뷰 블랙 요청 ${vo.blackCount }건</span></li>
	<li>|</li>
	<li><span onclick="manager.notice('일반문의')">일반 문의 ${vo.requestCount }건</span></li>
</ul>
</body>
</html>