<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 리스트</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner_list.css'>
<script defer src="./js/partner/partner_review.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<div id="partner_review_list">
	<span>캠핑장 리뷰</span>
	<!-- 사장님의 캠핑장의 간단한 정보 -->
	<div class="camp">
		<div class="review_filter">
			<form name="frm_review_list" id="frm_review_list" class="frm_review_list" method="post">
				<div class="filter">
					<input type="radio" id="filter_state" name="filter" value="state" >
					<label for="filter_state" onclick="review.type('state')" >답변 상태</label>
					<input type="radio" id="filter_state1" name="filter" value="score">
					<label for="filter_state1" onclick="review.type('score')" >별점</label>
					<input type="radio" id="filter_state2" name="filter" value="nal">
					<label for="filter_state2" onclick="review.type('nal')">날짜</label>
					<input type="button" id="sortType" name="sortType" >
					<label for="sortType" onclick="review.sort('${vo.sort}')">${sort }</label>	
				</div>
				<input type="hidden" name="type" value="${vo.type }">
				<input type="hidden" name="sort" value="${vo.sort }">
				<input type="hidden" name="orderCode" value="1">
				<input type="hidden" name="doc" value="doc">
				<input type="hidden" name="state" value="미답변">
				<input type="hidden" name="userCode" value="1">
				<input type="hidden" name="sno" value="1">
				<input type="hidden" name="itemCode" value="${vo.itemCode }">
			</form>
		</div>
	</div>
	<c:forEach var="vo" items="${list }">
	<!-- 캠핑장 리뷰 리스트 -->
	<div class="review_list">
		<div class="review">
			<div>
				<img src="./image/camping/${vo.itemCode }/review/${vo.sysFile }" onerror="this.src='./image/icon/error_img.png'">			
			</div>
			<div>
				<div class="info">
					<span class="id">${vo.id } : ${vo.mName }</span>
					<span class="site">${vo.siteCode } : ${vo.userCode} </span>
					<span class="date">${vo.dateStr } ~ ${vo.dateEnd } </span>
					<span class="rank"><img src="./image/icon/star.png" >${vo.score}</span>
					<button type="button" class="black" onclick= "review.black('${vo.sno }')">가리기 요청</button>
				</div>
				<div class="content">
					${vo.doc }
				</div>
				<button type="button" onclick="review.answer('${vo.orderCode }','${vo.state }')">${vo.state }</button>
			</div>
			
		</div>
		<div id="${vo.orderCode }answer_section" class="answer_section" >
				<textarea id="answer" class="${vo.orderCode }" placeholder="답변을 입력해 주세요"></textarea><br>
				<button type="button" id="${vo.orderCode}btn_save" class="btn" onclick="review.save('${vo.orderCode}','${vo.state }', '${vo.userCode}')">저장</button>
				<button type="button" id="${vo.orderCode}btn_delete" class="btn" onclick="review.delete('${vo.orderCode}')">삭제</button>
		</div>	
	</div>
	</c:forEach>
	

</div>
</body>
</html>