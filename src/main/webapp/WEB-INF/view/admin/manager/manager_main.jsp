<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_main</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/manager/manager.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/manager/manager_main.js"> </script>

</head>
<body>
	<div id="manager_main">
		<div class="manager_sub">
			<form id="frm_manager_main" method="post">
				<input type="hidden" name="id" value=${sessionScope.id }>	
				<input type="hidden" name="job" value=${sessionScope.job }>			
			</form>
			<div class="logo">
				<img src="./image/icon/logo_white.png" width="150px">
			</div>
			<div class="icon">
				<img src="./image/icon/manager_settings.png">
			</div>
			<div class="manager_menu">
				<div class="manager_id">
					<span>[${sessionScope.id }]님</span> <button onclick="manager.logout()">로그아웃</button>
				</div>
				<ul>
					<li onclick="manager.userList()">회원 정보 조회</li>
					<li onclick="manager.campingList()">캠핑 정보 조회</li>
					<li onclick="manager.sales()">매출 조회</li>						
					<li onclick="manager.requestList()">문의 관리</li>
					<li onclick="manager.accountModify()">프로필 수정</li>
					<li onclick="manager.pwdModify()">비밀번호 변경</li>
					<li class="admin_c" onclick="manager.accountInput()">관리자 생성/삭제</li>
					<li class="admin_c" onclick="manager.accountList()">관리자 리스트</li>
				</ul>
			</div>
		</div>
		
		<div>
			<div class="today_notice">
				
			</div>
			<div class='partner_content'>
				<jsp:include page="${inc }"/>
			</div>		
		</div>
	</div>	
</body>
</html>