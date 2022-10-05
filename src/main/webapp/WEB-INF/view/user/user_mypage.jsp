<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/user/user_mypage.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_mypage.js"> </script>
<script>
	let sessionID = '${sessionScope.id}';
	let sessionUserCode = '${sessionScope.userCode}';
	let sessionmName = '${sessionScope.mName}';
</script>
</head>
<body>
<div id="user_mypage">
<form name='frm_user_mypage' id='frm_user_mypage' method='post'>
	<nav class="nav">
		<div class="user">
			<img src="./image/icon/traveller.png" id='user_mypage_logo'> <br/>
			<span>${sessionScope.mName} 회원님</span>
		</div>
		<ul>
			<li onclick='user_mypage.user_modify()'>프로필 수정</li>
			<li onclick='user_mypage.user_pwd_modify()'>비밀번호 변경</li>
			<li onclick='user_mypage.user_remove_account()'>회원 탈퇴</li>
			<li onclick='user_mypage.user_wishlist()'>찜</li>
			<li onclick='user_mypage.user_reservation_list()'>예약 내역</li>
			<li onclick='user_mypage.user_qna_list()'>문의 내역</li>
			<li onclick='user_mypage.user_review_list()'>리뷰 보기</li>
		</ul>
	</nav>
	<input type='hidden' name='id' value='${sessionScope.id}'>
	<input type='hidden' name='userCode' value='${sessionScope.userCode}'>
	<input type='hidden' name='mName' value='${sessionScope.mName}'>
	</form>
	<section class="section">
		<jsp:include page="${myInc}" />
	</section>
</div>
</body>
</html>	