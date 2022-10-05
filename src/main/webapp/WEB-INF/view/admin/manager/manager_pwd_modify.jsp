<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_pwd_modify</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_account.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/manager/manager_account.js"></script>
</head>
<body>
	<div id='manager_pwd_modify'>
		<form name='frm_manager_pwd_modify' class='frm_manager_pwd_modify' method='post'>
		<input type='hidden' name='id' value='${sessionScope.id}'>			
			<label class="frm_title">비밀번호 변경</label><br/>
			
			<label>현재 비밀번호</label>
			<input type='password' name='mpwd' id='mpwd' placeholder='기존 비밀번호를 입력해주세요.'/><br/>
			<span id='mPwdValidation'></span><br/>
			<label>새 비밀번호</label>
			<input type='password' name='mpwd_new' id='mpwd_new' placeholder='새로운 비밀번호를 입력해주세요.' disabled/><br/>
			<span></span><br/>
			<label>새 비밀번호 확인</label>
			<input type='password' name='mpwd_new_check' id='mpwd_new_check' placeholder='비밀번호를 한 번 더 입력해주세요.' disabled/><br/>
			<span id='mpwdCheckValidation'></span><br/>
			
			<button type='button' class='btn_manager_pwd_modify' onclick='manager_account_modify.modiPwdR(this.form)' disabled>완료</button>
			<button type='button' name='btn_cancel' class='btn_cancel' onclick="location.href='manager_account_list'" >취소</button>
		</form>
	</div>
</body>
</html>