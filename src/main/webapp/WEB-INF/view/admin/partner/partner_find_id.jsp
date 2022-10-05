<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>partner_find_id</title>
		<link rel='stylesheet' type='text/css' href='./css/partner/partner.css'>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script defer src="./js/partner/partner_find.js"> </script>
	</head>
<body>
	<div id="partner_find_id">
	<h1>아이디 찾기</h1>
	<form name="frm_partner_find_id" class="frm_partner_find_id" method="post">
		<div class="certify">
			<button type="button" class="btn_pPhone">휴대폰으로 찾기</button>
			<button type="button" class="btn_pEmail">이메일로 찾기</button> <br/>
		</div>
		<div class="label_group findPidName">
			<img src="./image/icon/id_y.png" width='25px'/>
			<input type="text" name="mName" class="find_pId_pName" placeholder="이름을 입력해주세요."> <br/>
			<span id="pNameValidation"></span>
		</div>
		<div class="label_group findPidPhone">
			<img src="./image/icon/phone_y.png" width='25px'/>
			<input type="text" name="phone" class="find_pId_phone" placeholder="핸드폰 번호를 입력해주세요."> <br/>
			<span id="pPhoneValidation"></span>
		</div>
		<div class="label_group findPidEmail">
			<img src="./image/icon/email_y.png" width='25px'/>
			<input type="text" name="email" class="find_pId_email" placeholder="이메일을 입력해주세요."> <br/>
			<span id="pEmailValidation"></span>
		</div>
		<div class="btns">
			<button type="button"  id="btnFindPid" onclick='partner_find.findPhone(this.form)'>아이디 찾기</button> <br/>
			<button type="button"  id="btnFindEid" onclick='partner_find.findEmail(this.form)' style='display:none'>아이디 찾기</button><br/>
			<button type="button" onclick='location.href="admin"'>취소</button>
		</div>
	</form>
</div>
</body>
</html>