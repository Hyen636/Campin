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
<script defer src="./js/partner/partner_qna.js"></script>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_qna_list.css'>
</head>
<body>
<div id="partner_qna_list">
	<span>문의 내역</span><br/>
	<div class="qna_filter">
		<form name="frm_qna_filter" id="frm_qna_filter" class="frm_qna_filter" method="post">
			<div class="filter" >
			<input type="radio" name="state" id="wait" value="답변대기" >
				<label for="wait">답변대기</label>
				<input type="radio" name="state" id="complete" value="답변완료">
				<label for="complete">답변완료</label>	
				<div class='nal'>
				<label>날짜 : </label>
				<input type="date" id="strdate" class="strdate" name="str" value="${page.str }">
				<span>~</span>
				<input type="date" id="enddate" class="enddate" name="end" value="${page.end }"><br/>
				</div>
			</div>
			<div class="search">
				<input type="text" name="findStr"  value='${page.findStr }' class='findStr'>
				<img src="./image/icon/search_yellow.png" onclick='qna.find()'>		
			</div>
			<div class='hidden'>
			<input type='text' name='nowPage' value='${page.nowPage }'/>
			<input type='text' name='sno'/>
			<input type='text' name='grp'/>
			<input type='text' name='stateR' value='${page.state}'/>
			<input type='text' name='userCode'/>
			<input type='text' name='mName'/>
			<input type='text' name='qtitle'/>
			<input type='text' name='title'/>
			<input type='text' name='pwd'/>
			<input type='text' name='nal'/>
			</div>
		</form>
	</div>
	<div class="list">
		<div class="title">
			<span class="date">날짜</span>
			<span class="writer">작성자</span>
			<span class="subject">제목</span>
			<span class="state">답변상태</span>
			<span class="userCode">userCode</span>
		</div>
		
		<div class="items">
			<c:forEach var="vo" items="${list }"> 
				<div class="item" onclick="qna.view('${vo.sno}','${vo.nal }','${vo.pwd }','${vo.grp }','${vo.state }','${vo.userCode }','${vo.mName }','${vo.title }')">
					<span class="date">${vo.nal }</span>
					<span class="writer">${vo.mName }</span>
					<span class="subject">${vo.title }</span>
					<span class="state">${vo.state }</span>
					<span class="userCode">${vo.userCode }</span>
					
					
				</div>
			</c:forEach>
		</div>
	</div>
		<div class="paging_btns">
		<c:if test="${page.startPage>=1}">
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