<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_account_list</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/manager/manager_adlist.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/manager/manager_adlist.js"></script>
</head>
<body>
	<div id="manager_account_list">
		<span>관리자 계정 정보 조회 및 관리</span>
		<form name='frm_manager_account_list' class='frm_manager_account_list' method='post'>
			<div class="account_search">
			<input type="text" class="account_findStr" name='findStr' value='${ampage.findStr}'/>
			<img src="./image/icon/search_yellow.png" width="25px" onclick='manager_account_list.find()'/>
			</div>
			<input type='hidden' name='nowPage' value='${ampage.nowPage}'/>
			<input type='hidden' name='aUserCode'/> 
			<input type='hidden' name='id'/>
			<input type='hidden' name='job' value='${sessionScope.job}'>
		</form>
		
		<div class="list">
			<div class="title">
				<span class="no">no</span>
				<span class="aUserCode">ausercode</span>
				<span class="id">아이디</span>
				<span class="mName">성명</span>
				<span class="birth">생년월일</span>
				<span class="phone">연락처</span>
				<span class="email">이메일</span>
				<span class="gender">성별</span>
				<span class="regDate">가입일</span>
				<span class="job">역할</span>
				<span class="delete_account">삭제</span>
			</div>
			<div class="items">
			<c:set var='num' value='${ampage.startNo}'/>
			<c:forEach var='vo' items='${list}'>
				 <div class="item" >
					<span class="no">${num}</span>		
					<span class='aUserCode'>${vo.aUserCode}</span>
					<span class="id">${vo.id}</span>
					<span class="mName">${vo.mName}</span>
					<span class="birth">${vo.birth}</span>
					<span class="phone">${vo.phone}</span>
					<span class="email">${vo.email}</span>
					<span class="gender">${vo.gender}</span>
					<span class="regDate">${vo.regDate}</span>
					<span class="job">${vo.job}</span>
					<span class="delete_account"><img src="./image/icon/trash.png" width="20px" 
					class="btn_delete_account" onclick="manager_account_list.delete('${vo.aUserCode}')"></span>
				</div>
			<c:set var='num' value='${num=num+1}'/>
			</c:forEach>	
			
			</div>
		</div>
		<div class="paging">
		<c:if test='${ampage.startPage>1}'>
			<button type="button" onclick='manager_account_list.movePage(1)'>처음</button>
			<button type="button" onclick='manager_account_list.movePage(${ampage.startPage-1})'>이전</button>
		</c:if>	
		
		<c:forEach var='i' begin='${ampage.startPage}'  end='${ampage.endPage}'> 	
			<button type="button" onclick='manager_account_list.movePage(${i})'>${i}</button>
		</c:forEach>
		
		<c:if test='${ampage.totPage>ampage.endPage}'>	
			<button type="button" onclick='manager_account_list.movePage(${ampage.endPage+1})'>다음</button>
			<button type="button" onclick='manager_account_list.movePage(${ampage.totPage})'>끝</button>
		</c:if>
		</div>

	</div>	
</body>
</html>