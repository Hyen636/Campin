<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_find_pwd</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_membership.js"> </script>
</head>
<body>
<div id="user_find_pwd">
	<h1>비밀번호 찾기</h1>
	<form name="frm_user_find_pwd" class='frm_user_find_pwd' id='frm_user_find_pwd' method="post" onsubmit="return false">
		<div class="certify">
			이메일 인증
		</div>
		<div class="label_group">
			<img src="./image/icon/id.png" width='25px'/>
			<input type="text" name="id" class="find_pwd_id" placeholder="아이디를 입력해주세요."> <br/>
			<span id="idValidation"></span>
		</div>
		<div class="label_group findPwdEmail">
			<img src="./image/icon/email.png" width='25px'/>
			<input type="text" name="email" class="find_pwd_email" placeholder="이메일 주소를 입력해주세요." onkeyup="enterkey(btnFindPwd);"> <br/>
			<span id="emailValidation"></span>
		</div>
		<div class="btns">
			<button type="button" name="btnFindPwd" id="btnFindPwd" onclick="user_membership.user_find_temp_pwd(this.form)">비밀번호 찾기</button> <br/>
			<button type="button" onclick="user_membership.toLoginPage()">취소</button>
		</div>
	</form>
</div>
</body>
</html>