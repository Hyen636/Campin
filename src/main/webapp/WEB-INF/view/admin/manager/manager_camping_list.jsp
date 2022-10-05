<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_camping_list</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_adlist.css'>
<script defer src="./js/manager/manager_adlist.js"></script>
</head>
<body>
<div id="manager_camping_list">
	<span>캠핑장 정보 조회</span>
	<div class="camping_filter">
		<form name='frm_manager_camping_list' class='frm_manager_camping_list' method='post'>
		
			<!-- <div class="filter">
				<input type="radio" id="filter_camp" name="filter">
				<label for="filter_camp">캠핑장</label>
				<input type="radio" id="filter_classification" name="filter">
				<label for="filter_classification">캠지기</label>
     			<input type="radio" id="filter_address" name="filter">
     			<label for="filter_address">주소</label>	
     			<input type="radio" id="filter_state" name="filter">
     			<label for="filter_state">상태</label>			
			</div> -->
			
			<div class="search">
				<input type="text" name='findStr' value='${acpage.findStr}'>
				<img src="./image/icon/search_yellow.png" onclick='manager_camping_list.find()'>			
			</div>
			<input type='hidden' name='nowPage' value='${acpage.nowPage}'/>
		</form>
	</div>
	<div class="list">
		<div class="title">
			<span class='no'>no</span> 
			<span class='cUserCode'>cusercode</span> 
			<span class="iName">캠핑장</span>
			<span class="mName">캠지기</span>
			<span class="phone">연락처</span>
			<span class="address1">주소</span>
			<span class="address2"> </span>
			<!-- <span class="state">상태</span> -->
			<span class="sido">도시</span> <!-- display none 이지만 필터용 -->
		</div>
		
		<div class="items">
		<c:set var='num' value='${acpage.startNo}'/>
		<c:forEach var='vo' items='${list}'>
			<div class="item">
				<span class='no'>${num}</span>
				<span class='cUserCode'>${vo.cUserCode}</span> 
				<span class="iName">${vo.iName}</span>
				<span class="mName">${vo.mName}</span>
				<span class="phone">${vo.phone}</span>
				<span class="address1">${vo.address1}</span>
				<span class="address2">${vo.address2}</span>
				<!-- <span class="state"></span> -->
				<span class="sido">${vo.sido}</span>
			</div>
		<c:set var='num' value='${num=num+1}'/>
		</c:forEach>	
		</div>
	</div>
	
	<div class="paging_btns">
	<c:if test='${acpage.startPage>1}'>
		<button type="button" class="btnFirst" onclick="manager_camping_list.movePage(1)">처음</button>
		<button type="button" class="btnPrev" onclick="manager_camping_list.movePage(${acpage.startPage-1})">이전</button>
	</c:if>	
	
	<c:forEach var='i' begin='${acpage.startPage}'  end='${acpage.endPage}'>
		<button type="button" class="btnPage" onclick="manager_camping_list.movePage(${i})">${i}</button>
	</c:forEach>
	
	<c:if test='${acpage.totPage>acpage.endPage}'>	
		<button type="button" class="btnNext" onclick="manager_camping_list.movePage(${acpage.endPage+1})">다음</button>
		<button type="button" class="btnLast"  onclick="manager_camping_list.movePage(${acpage.totPage})">끝</button>		
	</c:if>
	
	</div>
</div>
</body>
</html>