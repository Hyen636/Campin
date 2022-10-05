<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_pwd_modify</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_account.js"></script>
</head>
<body>
	<div id='partner_pwd_modify'>
		<form name='frm_partner_pwd_modify' class='frm_partner_pwd_modify' method='post'>
		<input type='hidden' name='id' value='${sessionScope.id}'>
			<h1>비밀번호 변경</h1>
			
			<label>현재 비밀번호</label>
			<input type='password' name='cpwd_pre' id='cpwd_pre' placeholder='기존 비밀번호를 입력해주세요.'/><br/>
			<span id='cPwdValidation'></span><br/>
			<label>새 비밀번호</label>
			<input type='password' name='cpwd_new' id='cpwd_new' placeholder='새로운 비밀번호를 입력해주세요.' disabled/><br/>
			<span></span><br/>
			<label>새 비밀번호 확인</label>
			<input type='password' name='cpwd_new_check' id='cpwd_new_check' placeholder='비밀번호를 한 번 더 입력해주세요.' disabled/><br/>
			<span id='cpwdCheckValidation'></span><br/>
			<button type='button' class='btn_partner_pwd_modify' onclick='partner_pwd_modify.modiPwdR(this.form)' disabled>완료</button>
		</form>
	</div>
</body>
</html>