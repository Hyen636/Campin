<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_user_list</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_adlist.css'>
<script defer src="./js/manager/manager_adlist.js"></script>

</head>
<body>
<div id="manager_user_list">
	<span>회원 정보 조회</span>
	<div class="user_filter">
		<form name='frm_manager_user_list' class='frm_manager_user_list' method='post'>
			
     		
			<div class="sort_list">
				<input type="hidden" id="filter_name" name="sort_mName" onclick='manager_user_list.sortmName()'></input>
				<input type="hidden" id="filter_birth" name="sort_birth" onclick='manager_user_list.sortbirth()'></input>
     			<input type="hidden" id="filter_reg_date" name="sort_regDate" onclick='manager_user_list.sortregDate()'></input>
     		</div>
     					
			
			<div class="search">
				<input type="text" name='findStr' value='${alpage.findStr}'>
				<input type='hidden' name='userCode'/>
				<img src="./image/icon/search_yellow.png" onclick='manager_user_list.find()'>		
			</div>
			<input type='hidden' name='nowPage' value='${alpage.nowPage}'/>
		</form>
	</div>
	<div class="list">
		<div class="title">
			<span class='no'>no</span> 
			<span class='userCode'>usercode</span> 
			<span class="id">아이디</span>
			<span class="name">성명</span>
			<span class="phone">연락처</span>
			<span class="birth">생년월일</span>
			<span class="email">이메일</span>
			<span class="regDate">가입일</span>
			<span class="gender">성별</span>
			<span class="socialJoin">소셜가입</span>
			<!-- <span class="role">구분</span> -->
		</div>
		
		
		<div class="items">
		<c:set var='num' value='${alpage.startNo}'/>
		<c:forEach var='vo' items='${list}'>
			<div class="item">
				<span class='no'>${num}</span> 
				<span class='userCode'>${vo.userCode}</span> 
				<span class="id">${vo.id}</span>
				<span class="mName">${vo.mName}</span>
				<span class="phone">${vo.phone}</span>
				<span class="birth">${vo.birth}</span>
				<span class="email">${vo.email}</span>
				<span class="regDate">${vo.regDate}</span>
				<span class="gender">${vo.gender}</span>
				<span class="socialJoin">${vo.socialJoin}</span>
				<!-- <span class="role">캠지기</span> -->
			</div>
		<c:set var='num' value='${num=num+1}'/>
		</c:forEach>

		</div>
	</div>
	
	<div class="paging_btns">
	<c:if test='${alpage.startPage>1}'>
		<button type="button" class="btnFirst" onclick='manager_user_list.movePage(1)'>처음</button>
		<button type="button" class="btnPrev" onclick='manager_user_list.movePage(${alpage.startPage-1})'>이전</button>
	</c:if>	
		
	<c:forEach var='i' begin='${alpage.startPage}'  end='${alpage.endPage}'> 
		<button type="button" class="btnPage" onclick='manager_user_list.movePage(${i})'>${i}</button>
	</c:forEach>	

	<c:if test='${alpage.totPage>alpage.endPage}'>		
		<button type="button" class="btnNext" onclick='manager_user_list.movePage(${alpage.endPage+1})'>다음</button>
		<button type="button" class="btnLast" onclick='manager_user_list.movePage(${alpage.totPage})'>끝</button>		
	</c:if>
	</div>
	
</div>
</body>
</html>