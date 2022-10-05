<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_main</title> 
<link rel='stylesheet' type='text/css' href='./css/partner/admin_login.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/admin_login.js"></script>
</head>
<body>
<div id="admin_login">
	<header class="header">
		<div class="admin_home">
			<a href="admin"><img class="admin_home_img" src="./image/icon/admin_home.png" width="35px"/></a>  <%--user/index.jsp 로 이동--%>
		</div>
		
		<div class="admin_logo">
			<a href="admin"><img class="admin_img" src="./image/icon/logo_white.png"/></a>
		</div>
	</header>

	<div class="admin_login">
		<form name="frm_admin_login" class="frm_admin_login" method="post">
			<h2>로그인</h2>
			<div class="noti">
				<span class='noti2'>캠지기와 관리자 중 선택하여 로그인해주세요.</span>
			</div>
			<div class="certify">
				<button type="button" class="btn_camp" id='btn_camp'>캠지기</button>
				<button type="button" class="btn_manager">관리자</button><br/>
			</div>
				<div class="login_id">
					<input type="image"src="./image/icon/admin_id.png" width="20px">
					<input type="text" name="id" class="id" size="20" placeholder="아이디를 입력해주세요."><br/>
				</div>
				
				<div class="login_pwd">
					<input type="image"src="./image/icon/admin_padlock.png" width="25px">
					<input type="password" name="pwd" class="pwd" size="20" placeholder="비밀번호를 입력해주세요."><br/>
				</div>
				
				<div class="find_info">
					<div class="cuser_info">
						<span><a href="findCid">아이디 찾기</a></span>
						<span>|</span>
						<span><a href="findCpwd">비밀번호 찾기</a></span>
					</div>
					<span class='auser_info' style='display:none'>관리자 로그인 관련 문의는 최고 관리자에게 문의바랍니다.</span>
				</div>
				
				<div class="btns">
					<button type="button" class="btnClogin" onclick="admin_login.clogin(this.form)" disabled>로그인</button><br/>
					<button type="button" class="btnCjoin" onclick="admin_login.cjoin()" disabled>회원가입</button><br/>
					<button type="button" class="btnMlogin" onclick="admin_login.mlogin(this.form)" style='display:none'>로그인</button><br/> 
				</div>
		</form>
	</div>
</div>
</body>
<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>

</html>