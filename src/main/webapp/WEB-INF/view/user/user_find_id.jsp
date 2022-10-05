<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_find_id</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_membership.js"> </script>
</head>
<body>
	<div id="user_find_id">
	<h1>아이디 찾기</h1>
	<form name="frm_user_find_id" class="frm_user_find_id" id="frm_user_find_id" method="post" onsubmit="return false">
		<div class="certify">
			<button type="button" class="btn_phone">휴대폰 인증</button>
			<button type="button" class="btn_email">이메일 인증</button> <br/>
		</div>
		<div class="label_group findIdName">
			<img src="./image/icon/id.png" width='25px'/>
			<input type="text" name="mName" class="find_id_mName" placeholder="이름을 입력해주세요."> <br/>
			<span id="mNameValidation"></span>
		</div>
		<div class="label_group findIdPhone">
			<img src="./image/icon/phone.png" width='25px'/>
			<input type="text" name="phone" class="find_id_phone" placeholder="휴대폰 번호를 입력해주세요." onkeyup="enterkey(btnFindId);"> <br/>
			<span id="phoneValidation"></span>
		</div>
		<div class="label_group findIdEmail">
			<img src="./image/icon/email.png" width='25px'/>
			<input type="text" name="email" class="find_id_email" placeholder="이메일 주소를 입력해주세요." onkeyup="enterkey(btnFindId);"> <br/>
			<span id="emailValidation"></span>
		</div>
		<div class="btns">
			<button type="button" id="btnFindId" onclick="user_membership.user_find_id_phone()">아이디 찾기</button> <br/>
			<button type="button" onclick="user_membership.toLoginPage()">취소</button>
		</div>
	</form>
</div>
</body>
<%-- 
<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>
 --%>
</html>