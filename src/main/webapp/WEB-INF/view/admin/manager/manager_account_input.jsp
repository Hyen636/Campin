<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_account_input</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_account.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/manager/manager_account.js"></script>
</head>
<body>
	<div id="manager_account_input">
		<form name="frm_manager_join" class="frm_manager_join" method="post">
			<span class="frm_title">관리자 등록</span>
			
			<div class="account_inputs">
				<label>아이디</label>
				<input type='text' name='id' id='id' value='eungb72' placeholder='아이디를 입력해주세요.' /><br/>
				<span id="aIdValidation"></span><br/>
			
				<label>비밀번호</label>
				<input type='password' name='pwd' value='' /><br/>
				<span></span><br/>
			
				<label>비밀번호 확인</label>
				<input type='password' name='mPwdCheck' value='1111' /><br/>
				<span id="aPwdCheckValidation"></span><br/>
				
				<label>이름</label>
				<input type='text' name='mName' value='이은빈'/><br/>
				<span></span><br/>
				
				<label>생년월일</label>
				<input type='date' name='birth' value='1999-12-12' /><br/>
				<span></span><br/>
			
				<label>연락처</label>
				<input type='text' name='phone' value='01011112345'/><br/>
				<span id="aPhoneValidation"></span><br/>
				
				<label>이메일</label>
				<input type='text' name='email' value='b887@campin.co.kr' /><br/>
				<span id="aEmailValidation"></span><br/>
				
				<label>성별</label>
				<input type='radio' id="m" name='gender' value='m'/><label for="m" class='gender'>남자</label>
				<input type='radio' id="f" name='gender' value='f'/><label for="f" class='gender'>여자</label><br/>
				<span></span><br/>
				
				<%--hidden--%>
		  		<input type='hidden' name='regDate' id='regDate'/>
		  		<input type='hidden' name='job' value='E'/>
			</div>
				
			<div class="btns">
				<button type='button' name='btn_join' class='btn_join' onclick="manager_account_input.mjoinR(this.form)">등록</button>
				<button type='button' name='btn_join' class='btn_join' onclick="manager_account_input.mdeleteR(this.form)" style='display:none'>삭제</button><%--보류--%>
				<button type='button' name='btn_cancel' class='btn_cancel'  onclick="location.href='manager_account_list'" >취소</button>
			</div>
			
		</form>
	</div>
</body>
</html>