<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_account_modify</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_account.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/manager/manager_account.js"> </script>
</head>
<body>
	<div id='manager_account_modify'>
		<form name='frm_manager_modify' class='frm_manager_modify' method='post'>
			<span class="frm_title">관리자 정보 수정</span>
			<div class='manager_modify_inputs'>
				<label>아이디</label>
				<input type='text' name='id' value='${vo.id}' readonly/> <br/>
				<span></span><br/>
			
				<label>비밀번호</label>
				<button type='button' name='btn_manager_pwd_modify' class='btn_manager_pwd_modify' onclick='manager_account_modify.modiPwd(this.form)'>비밀번호 변경</button><br/>
				<!-- 버튼 클릭 시 비밀번호 변경 페이지로 이동 -->
				<span></span><br/>
			
				<label>이름</label>
				<input type='text' name='mName' value='${vo.mName}' /> <br/>
				<span></span><br/>
			
				<label>생년월일</label>
				<input type='date' name='birth' value='${vo.birth}'/> <br/>
				<span></span><br/>
			
				<label>연락처</label>
				<input type='text' name='phone' value='${vo.phone}'/> <br/>
				<span></span><br/>
			
				<label>이메일</label>
				<input type='text' name='email' value='${vo.email}'/> <br/>
				<span></span><br/>
						
				<label>성별</label>    
				<input type='radio' id="m" name='gender' value='m' ${(vo.gender == 'm')?'checked':''}/><label for="m" class='gender'>남자</label>
				<input type='radio' id="f" name='gender' value='f' ${(vo.gender == 'f')?'checked':''}/><label for="f" class='gender'>여자</label><br/>
				<span></span><br/>
				
				<div class='btns'>
					<button type='button' name='btn_manager_modify' class='btn_manager_modify' onclick='manager_account_modify.mmodifyR(this.form)'>수정</button>
					<button type='button' name='btn_cancel' class='btn_cancel' onclick="location.href='manager_account_list'" >취소</button>
				</div>
				
			</div>
		</form>
	</div>
</body>
</html>