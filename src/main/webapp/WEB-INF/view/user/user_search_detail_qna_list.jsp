<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_qna_load</title>
<script defer src="./js/user/user_search_detail_qna_list.js"></script>
</head>
<body>
	<label class="qna_title">문의</label>
	<div class="qna_list">
		<div class="title">
			<span class="no">No.</span>
			<span class="subject">제목</span>
			<span class="mName">작성자</span>
			<span class="date">작성일</span>
		</div>
		<div class="items">
			<c:set var='pos' value='1' />
				<c:forEach var='qna_items' items='${list}'>
					<c:if test='${qna_items.pwd eq "f"}'>
						<div class="item" onclick="user_search_detail_qna_list.show(${pos})">
							<span class="no">${pos}</span> <!-- 무식하지만 한줄한줄 한땀한땀 적음.. -->
							<span class="subject">${qna_items.title}</span>
							<span class="mName">${qna_items.writer}</span>
							<span class="date">${qna_items.nal}</span>
						</div>
						<div class='item_detail_qna item_detail_${pos}'>
							<h3>Q) ${qna_items.title}</h3><br/>
							<textarea disabled>${qna_items.doc}</textarea>
							<c:if test='${qna_items.aDoc == null }'>
							</c:if>
							<c:if test='${qna_items.aDoc != null }'>
								<h3>A) ${qna_items.aTitle}</h3><br/>
								<textarea disabled>${qna_items.aDoc}</textarea>
							</c:if>
						</div>	
						<c:set var='pos' value='${pos=pos+1}'/>
					</c:if>
					<!-- 비공개 체크했을때 -->
					<c:if test='${qna_items.pwd eq "t"}'>
						<div class="item" onclick="user_search_detail_qna_list.show(${pos})">
							<span class="no">${pos}</span> <!-- 무식하지만 한줄한줄 한땀한땀 적음.. -->
							<span class="subject">비밀글입니다.</span>
							<span class="mName">${qna_items.writer}</span>
							<span class="date">${qna_items.nal}</span>
						</div>
						<div class='item_detail_qna item_detail_${pos}'>
							<h3>Q) 비밀글 적용중입니다...</h3><br/>
							<textarea disabled>비밀글 적용중입니다...</textarea>
							<c:if test='${qna_items.aDoc == null }'>
							</c:if>
							<c:if test='${qna_items.aDoc != null }'>
								<h3>A) 비밀글 적용중입니다...</h3><br/>
								<textarea disabled>비밀글 적용중입니다...</textarea>
							</c:if>
						</div>	
						<c:set var='pos' value='${pos=pos+1}'/>
					</c:if>
			</c:forEach>
		</div>
	</div>
	<div class="paging">
		<button type="button">처음</button>
		<button type="button">이전</button>
					
		<button type="button">1</button>
		<button type="button">2</button>
		<button type="button">3</button>
					
		<button type="button">다음</button>
		<button type="button">끝</button>
	</div>
	<input type="text" class="qna_findStr"/>
	<img src="./image/icon/search.png" width="30px"/>
	<c:choose>
		<c:when test="${empty sessionScope.id}">
			<!-- 글쓰기 x -->
		</c:when>
		<c:otherwise>
			<button type="button" class="btn_qna_input" onclick="user_search_detail.btn_qna_input()">글쓰기</button>
		</c:otherwise>
	</c:choose>
	
	<input type="hidden" id="textTestSave" />
</body>
</html>