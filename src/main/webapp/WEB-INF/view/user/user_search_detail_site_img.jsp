<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_search_detail_site_img.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_search_detail_site_img.js"></script>

</head>
<body>
<br>
	<div class="slide" >
		<c:forEach var='imgItems' items='${list}'>
			<img src='./image/camping/${imgItems.itemCode}/${imgItems.itemType}/${imgItems.sysFile}' id='image${pos}' width='300px' height='200px'>
		</c:forEach>
	<div>
	<br>
		<%-- <br><br>
			<div class="slide_wrap2">
				<ul class="slide2">
					<c:set var='pos' value='1' /> 
					<c:forEach var='imgItems' items='${list}' varStatus="index">
						<li class="site__item">
							<div>
								<img src='./image/camping/${imgItems.itemCode}/${imgItems.itemType}/${imgItems.sysFile}' id='image${pos}' width='300px' height='200px'>
							</div>
						</li>
						<c:set var='pos' value='${pos=pos+1}'/><br>
					</c:forEach>
				</ul>
			</div>
			
			<div class="slide_nav">
				
				<img src='./image/icon/prev.png' width='3%' class="prev" onclick="userslide.slidePrev()">
				<img src='./image/icon/next.png' width='3%' class="next" onclick="userslide.slideNext()">
						
			</div>				
		</div> 
		
	</div> --%>
		

</body>
</html>