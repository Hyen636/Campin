<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_find_result</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_membership.js"> </script>
</head>
<body>
<c:if test="${not empty id}">
	<div id="user_find_id_result">
	<h4>회원님의 아이디는 아래와 같습니다.</h4>
	<span class="guide">아이디를 확인 후 로그인해주세요.</span>
		<div class="result">
			<span>아이디 : ${id} </span><br/>
			<button type="button" class="btnLoginPage" onclick="user_membership.toLoginPage()">로그인</button><br/>
		</div>
	</div>
</c:if>

<c:if test="${not empty isEmail}">
	<div id="user_find_pwd_result">
	<h4>임시 비밀번호를 회원님의 이메일 주소로 <br/>발송했습니다.</h4>
	<span class="guide">비밀번호 확인 후 복사하여 로그인해주세요.</span><br/>
	<span class="guide">로그인 후 비밀번호를 꼭 변경해주세요.</span>
	<div class="result">
		<button type="button" class="btnLoginPage" onclick="user_membership.toLoginPage()">로그인</button><br/>
	</div>
	</div>
</c:if>
</body>
</html>