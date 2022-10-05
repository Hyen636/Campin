<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반려</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_pop.js"></script>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_view.css'>
</head>
<body>
<div id="camp_reject">
	<span>반려 사유 작성</span>
	<form id="frm_camp_reject">
		<textarea name="rejectMsg" class="rejectMsg"></textarea>
	</form>
	<button type="button" onclick="pop.siteWrite()">작성완료</button>
	<button type="button" class="close" onclick="pop.close()">닫기</button>
</div>
</body>
</html>