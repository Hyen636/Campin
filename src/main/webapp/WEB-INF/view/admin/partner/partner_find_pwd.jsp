<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_find_pwd</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner.js"> </script>
</head>
<body>
<div id="partner_find_pwd">
	<h1>비밀번호 찾기</h1>
	<form name="frm_partner_findPwd" class='frm_partner_find_pwd' method="post">
		<div class="certify">
			이메일 인증
		</div>
		<div class="label_group">
			<img src="./image/icon/id_y.png" width='25px'/>
			<input type="text" name="uId" class="find_pwd_uId" placeholder="아이디를 입력해주세요."> <br/>
			<span id="uIdValidation"></span>
		</div>
		<div class="label_group findPwdEmail">
			<img src="./image/icon/email_y.png" width='25px'/>
			<input type="text" name="email" class="find_pwd_email" placeholder="이메일을 입력해주세요."> <br/>
			<span id="emailValidation"></span>
		</div>
		<div class="btns">
			<button type="button" id="btnFindPwd">확인</button> <br/>
			<button type="button" onclick='location.href="admin"'>취소</button>
		</div>
	</form>
</div>
</body>
</html>