<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_wishlist</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/user/user_wishlist.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_wishlist.js"></script>
</head>
<body>
<div id="user_wishlist">
	<div class="camp_name">
		<label>찜해둔 캠핑장</label>
	</div><br/><br/>
	<c:if test='${list.size() == 0 }'>
		<img src="image/icon/null.png" width='20%' class='null1'>
		<div class='null2'> 찜 내역이 존재하지 않습니다.</div>
	</c:if>
	<c:if test='${list.size() != 0 }'>
		<c:forEach var="vo" items="${list}">
			<div class="content" style="display:none">
				<form method="post" name="frm_wishlist_content" class="frm_wishlist_content">
				<input type='hidden' name='userCode' value='${sessionScope.userCode}'/>
				<input type='hidden' name='itemCode' value='${vo.itemCode}'/>
				<input type='hidden' name='state' value='${vo.state}'/>
					
					<img src="./image/icon/heart.png" class="not_wish" onclick="user_wishlist.wish('${sessionScope.userCode}', '${vo.itemCode}', this)">
					<img src="./image/camping/${vo.itemCode}/${vo.sysFile}" class="best_img" onclick="btnDetailPage.onclick()">
					<span>1박 ${vo.price}원~</span>
					<div>
						<p class="titleText">${vo.iName} (${vo.sido})</p>
						<p class="infoText">${vo.infoText}</p>
					</div>
					<button type="button" hidden="hidden" name="btnDetailPage" onclick="user_wishlist.search_detail_page(this.form, '${vo.state}')">hidden 상세페이지</button>
				</form>
			</div>
		</c:forEach>
	
		<br/><br/>
		<button class="btn_more_wishes" id="btn_more_wishes">찜해놓은 캠핑장 모두 보기</button>
	</c:if>
</div>
</body>
</html>