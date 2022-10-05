<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner_list.css'>
<script defer src="./js/partner/partner_request.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<div id="partner_request_list">
	<span>요청 내역</span>
	<div class="request_filter">
		<form name="frm_request_filter" method="post" id="frm_request_filter">
			<div class="filter" >
				<label>카테고리 : </label>
				<select id="category" name="category" class="category" onchange="request.category(this.value)" >
					<option value="전체">전체</option>
					<option value="회원">회원</option>
					<option value="캠핑장">캠핑장</option>
					<option value="사이트">사이트</option>
					<option value="탈퇴">탈퇴</option>
					<option value="리뷰">리뷰</option>
					<option value="일반문의">일반문의</option>
				</select>
				<br>
				<label>상태 : </label>
				<select id="state" name="state" class="state">
					<option value="요청대기">요청대기</option>
					<option value="요청반려">요청반려</option>
					<option value="요청완료">요청완료</option>
					
				</select>
				<br>
				<label>날짜 : </label>
				<input type="date" id="strdate" class="strdate" name="str" value="${page.str }">
				<span>~</span>
				<input type="date" id="enddate" class="enddate" name="end" value="${page.end }">		
			</div>
			<div class="search">
				<input type="text" name="findStr"  value='${page.findStr }'>
				<img src="./image/icon/search_yellow.png" onclick='request.find()'>			
			</div>
			<input type='hidden' name='nowPage' value='${page.nowPage }'/>
			<input type='hidden' name='sno'/>
			<input type='hidden' name='stateR' value='${page.state}'/>
			<input type='hidden' name='categoryR' value='${page.category}'/>
			<input type='hidden' name='mName' value="${sessionScope.mName}"/>
			<input type='hidden' name='nal'/>
			<input type='hidden' name='hisDetailSno'/>
			<input type='hidden' name='hisUserSno' />
			<input type='hidden' name='hisItemSno' />
		</form>
	</div>
	<div class="list">
		<div class="list_title">
			<span class="ReqNo">요청번호</span>
			<span class="category">분류</span>
			<span class="mName">작성자</span>
			<span class="title">제목</span>
			<span class="iName">요청 캠핑장</span>
			<span class="siteName">요청 사이트</span>
			<span class="date">날짜</span>
			<span class="state">상태</span>
		</div>
		
		<div class="items">
			<c:forEach var="vo" items="${list }"> 
				<c:choose>
					<c:when test="${vo.hisDetailSno=='0' && vo.hisUserSno=='0' && vo.hisItemSno=='0' }">
						<c:set var="max" value="C${vo.sno}"/>
					</c:when>
					<c:when test="${vo.hisDetailSno >= vo.hisUserSno && vo.hisDetailSno >= vo.hisItemSno }">
						<c:set var="max" value="G${vo.hisDetailSno}"/>
					</c:when>
					<c:when test="${vo.hisUserSno >= vo.hisDetailSno && vo.hisUserSno >= vo.hisItemSno }">
						<c:set var="max" value="U${vo.hisUserSno}"/>
					</c:when>
					<c:otherwise>
						<c:set var="max" value="I${vo.hisItemSno}"/>
					</c:otherwise>
				</c:choose>
				<div class="item" onclick="request.view('${max }','${vo.category }','${vo.state}','${vo.sno }','${vo.title}')">
					<span class="ReqNo">${max }</span>
					<span class="category">${vo.category }</span>
					<span class="mName">${vo.mName }</span>
					<span class="title">${vo.title }</span>
					<span class="iName">${vo.iName }</span>
					<span class="siteName">${vo.siteName }</span>
					<span class="nal">${vo.nal }</span>
					<span class="state">${vo.state }</span>
				</div>
			</c:forEach>
			
		</div>
		<button type="button" class="write" onclick="request.write()">작성하기</button>
	</div>
	
	<div class="paging_btns">
		<c:if test="${page.startPage>1}">
			<button type="button" class="btnFirst" onclick="request.movePage(1)">맨처음</button>
			<button type="button" class="btnPrev" onclick="request.movePage(${page.nowPage-1})">이전</button>
		</c:if>
			
		<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
				<button type="button" onclick="request.movePage(${i })">${i }</button>
		</c:forEach>
		<c:if test="${page.totPage>page.endPage}">
			<button type="button" onclick="request.movePage(${page.nowPage+1})">다음</button>
			<button type="button" onclick="request.movePage(${page.totPage})">맨끝</button>
		</c:if>
	</div>
</div>
</body>
</html>