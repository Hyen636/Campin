<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>user_login</title>
	<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
	<script src='./js/user/user_membership.js'></script>
</head>

<body>
	<div id="user_login">
		<form name="frm_user_login" class="frm_user_login" id="frm_user_login" method="post" onsubmit="return false">
			<h1>로그인</h1>
		
			<div class="login_id">
				<input type="image" src="./image/icon/id.png" width="25px"/>
				<input type="text" name="id" size="15" placeholder="아이디를 입력하세요." />
			</div>
		
			<div class="login_pwd">
				<input type="image" src="./image/icon/password.png" width="30px"/>
				<input type="password" name="pwd" size="15" placeholder="비밀번호를 입력하세요." onkeyup="enterkey(btn_login);"/>
			</div>
		
			<div class="find_id_pwd">
				<a onclick="user_membership.user_find_id()">아이디찾기 |</a>
				<a onclick="user_membership.user_find_pwd()">비밀번호찾기</a>
			</div>
		
			<button type="button" class="btn_login" name="btn_login" onclick="user_membership.user_loginR()">로그인</button><br/>
			<button type="button" class="btn_join" name="btn_join" onclick="search.user_join()">회원가입</button><br/><br/>
			
			
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=52a5ddfbec7726cdf5d3d2bd372751ad&redirect_uri=http://localhost:8282/user_login_kakao&response_type=code">
				<img class="btn_kakao_login" src="./image/icon/kakao_login.png">
			</a><br/>
			
			<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=4jrL65cX4XhqT3wJJUZF&redirect_uri=http://localhost:8282/user_login_naver&state=oauth_state">
				<img class="btn_naver_login" src="./image/icon/naver_login.png">
			</a>
		</form>
	</div>
</body>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>

<script>
	// SDK 초기화
	// + kauth.kakao.com
	Kakao.init("647a0b3df3e72c1dace2bb1ff14c00d4");
	console.log(Kakao.isInitialized());
	function kakaoLogin(){
		Kakao.Auth.authorize({
			redirectUri: "http://localhost:8282/user_login_kakao"
		})
		
	}
</script>

<script>
	// 맞는진 모르겠지만 일단 네이버도 똑같이
	Naver.init("4jrL65cX4XhqT3wJJUZF");
	console.log(Naver.isInitialized());
	function naverLogin(){
		Naver.Auth.authorize({
			redirectUri: "http://localhost:8282/user_login_naver"
		})
	}
</script>
 </html>