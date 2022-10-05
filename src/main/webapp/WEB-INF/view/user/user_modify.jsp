<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_modify</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src='./js/user/user_membership.js'></script>
</head>
<body>
	<div id='user_modify'>
		<form name='frm_user_modify' class='frm_user_modify' id='frm_user_modify' method='post'>
		<br/>
			<h1>회원정보 수정</h1>
				<div class='user_modify_input'>
					<div class='label_group'>
						<label>아이디</label>
						<input type='text' name='id' id='id' class='readonly' value='${vo.id}' readonly/><br/>
						<span>※아이디는 변경이 불가합니다.</span>
					</div>
					
					<div class='label_group'>
						<label>비밀번호</label>
						<button type='button' name='btn_to_pwd_modify' class='btn_user_pwd_modify' onclick='user_mypage.user_pwd_modify()'>비밀번호 변경</button><br/>
					</div>
					
					<div class='label_group'>			
						<label>이름</label>
						<input type='text' name='mName' id='mName' class='readonly' value='${vo.mName}' readonly/><br/>
						<span>※이름 변경이 필요하신 경우, 고객센터로 문의하여 주세요.</span><br/>
					</div>
					
					<div class='label_group'>
						<label>생년월일</label>
						<input type='text' name='birth' id='birth' placeholder='예) 19930919' value='${vo.birth}'/> <br/>
						<span id="birthValidation"></span>
					</div>
					
					<div class='label_group'>
						<label>휴대폰 번호</label>
						<input type='text' name='phone' id='modifiedPhone' placeholder='- 를 제외한 숫자만 입력해주세요.' value='${vo.phone}'/> <br/>
						<span id='modifiedPhoneValidation'></span>
						<input type='hidden' name='tempPhone' id='tempPhone' value='${vo.phone}'/>
					</div>
				
					<div class='label_group'>
						<label>이메일 주소</label>
						<input type='text' name='email' id='modifiedEmail' placeholder='예) campin@naver.com' value='${vo.email}'/> <br/>
						<span id='modifiedEmailValidation'></span>
						<input type='hidden' name='tempEmail' id='tempEmail' value='${vo.email}'/>
					</div>
					
					<div class='label_group'>
						<label>성별</label>
						<label class='gender'><input type='radio' name='gender' value='m' ${(vo.gender=='m')?'checked':''}/>남자</label>
						<label class='gender'><input type='radio' name='gender' value='f' ${(vo.gender=='f')?'checked':''}/>여자</label><br/>
					</div>
					
					<div class='btns'>
						<button type='button' name='btnUserModify' id='btn_user_modify' class='btn_user_modify' onclick='user_membership.user_modifyR()'>수정</button>
						<button type='button' name='btnCancel' class='btn_cancel' onclick='user_membership.toMypage()'>취소</button>
						<button type='button' name='btnUserRemoveAccount' class='btn_user_remove_account' onclick='user_mypage.user_remove_account()'>회원탈퇴</button>
					</div>
				</div>
		</form>
	</div>

</body>
</html>