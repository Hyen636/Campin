<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_temp</title> 
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel='stylesheet' type='text/css' href='./css/partner/admin_login.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/partner/admin_login.js"></script>
</head>
<body>

<div id="admin_temp">
	<header class="header">
	
		<div class="admin_logo">
			<a href="admin"><img class="admin_img" src="./image/icon/logo_white.png" width="170px"/></a>
		</div>
	
		<div class="admin_home">
			<a href="admin"><img class="admin_home_img" src="./image/icon/admin_home.png" width="30px"/></a>
		</div>
		
	</header>
	
	<div class="admin_content">
	 	<jsp:include page="${inc}"/>
	</div>
	
</div>

</body>
</html>