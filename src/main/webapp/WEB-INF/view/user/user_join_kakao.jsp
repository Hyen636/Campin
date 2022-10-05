<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_join</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src='./js/user/user_membership.js'></script>
</head>
<body>
	<div id='user_join_kakao'>
		<form name='frm_user_join_kakao' class='frm_user_join_kakao' id='frm_user_join_kakao' method='post' onsubmit="return false">
		<input type='hidden' name='id' value='${id}'/>
			<h1>추가 정보 입력</h1>
			
			<div class='user_join_input'>
				<div class='label_group'>
					<label>이름</label>
					<input type='text' name='mName' id='mName' placeholder='예) 김캠핀'/><br/>
					<span id="mNameValidation"></span>
				</div>
				<div class='label_group'>
					<label>생년월일</label>
					<input type='text' name='birth' id='birth' placeholder='예) 19930919'/><br/>
					<span id="birthValidation"></span>
				</div>
				<div class='label_group'>
					<label>휴대폰 번호</label>
					<input type='text' name='phone' id='phone' placeholder='- 를 제외한 숫자만 입력해주세요.' onkeyup="enterkey(btn_join_kakao);"/><br/>
					<span id="phoneValidation"></span>
				</div>
			</div>
			<span class='reminder'>※희망하지 않으신 경우 추가 정보를 입력하지 않더라도, 추후 [마이페이지] > [프로필 수정] 에서 입력 및 수정이 가능합니다.</span><br/>

			<button type='button' name='btnJoin' class='btn_join_kakao' id='btn_join_kakao' onclick='user_membership.user_join_kakaoR()'>추가 정보 입력 완료</button><br/>
			<button type='button' name='btnJoin' class='btn_join_kakao2' id='btn_join_kakao2' onclick='user_membership.user_join_kakaoRR()'>다음에 할게요</button>
		</form>
	</div>
</body>
</html>