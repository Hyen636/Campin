<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_pwd_modify</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src='./js/user/user_membership.js'></script>
</head>
<body>
	<div id='user_pwd_modify'>
		<form name='frm_user_pwd_modify' class='frm_user_pwd_modify' id='frm_user_pwd_modify' method='post' onsubmit="return false">
		<input type='hidden' name='id' value='${sessionScope.id}'>
			<h1>비밀번호 변경</h1>
			<div class='label_group'>
			<label>현재 비밀번호</label>
				<input type='password' name='oldPwd' id='oldPwd' placeholder='기존 비밀번호를 입력해주세요.'/><br/>
				<span id='checkOldPwd'></span><br/>
			</div>
			<div class='label_group'>
			<label>새 비밀번호</label>
				<input type='password' name='pwd' id='newPwd' class='readonly' placeholder='8~16자 사이로 영문과 숫자를 조합' readonly/><br/>
				<span id='pwdValidation'></span><br/>
			</div>
			<div class='label_group'>
				<label>새 비밀번호 확인</label>
				<input type='password' name='newPwdCheck' id='newPwdCheck' class='readonly' placeholder='비밀번호를 한 번 더 입력해주세요.' readonly onkeyup="enterkey(btn_user_pwd_modify);"/><br/>
				<span id='pwdCheckValidation'></span>	
			</div>
			<button type='button' class='btn_user_pwd_modify' id='btn_user_pwd_modify' onclick='user_membership.user_pwd_modifyR()'>완료</button>
		</form>
	</div>
</body>
</html>