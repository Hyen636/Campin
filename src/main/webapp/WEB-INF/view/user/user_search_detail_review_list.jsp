<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_review_load</title>
<script defer src="./js/user/user_search_detail_review_list.js"></script>
</head>
<body>
	<div class="review_title">
		<div class="review_total">
			<span class="star"><img src="./image/icon/star.png"
				width="18px"></span> <label> ${reviewStar} </label> <label>· 후기 ${reviewCnt}개</label>
		</div>
		<div class="review_sorting">
			<!-- <input type="radio"/><label>최신 순</label>
			<input type="radio"/><label>별점 낮은 순</label>
			<input type="radio"/><label>별점 높은 순</label> -->
		</div>
	</div>
	<c:set var='pos' value='1' />
	<c:forEach var='review_items' items='${list}'>
		
			<c:if test='${review_items.state eq "미답변" || review_items.state eq "답변완료"}'>
				<div class="real_review" onclick="user_search_detail_review_list.show(${pos})">
					<label class="reviewer_name">${review_items.writer}</label>
					<span class="star2"><img src="./image/icon/star.png" width="16px"> ${review_items.score}</span><br/>
					<label class="reviewer_site">사이트: ${review_items.siteName}</label>
					<label class="reviewer_date">${review_items.nal}</label><br/>
					<img src="./image/camping/${review_items.itemCode}/review/${review_items.sysFile}" width="370px" height="230px"/><br/>
					<div class="reviewer_content">
						<textarea disabled>${review_items.doc}</textarea>
					</div>
				</div>
				<div class='items_detail_review review_detail_${pos}'>
					<div>
						<c:if test='${review_items.aDoc == null }'>
						</c:if>
						<c:if test='${review_items.aDoc != null }'>						
							<h3>A</h3>
							<textarea disabled>${review_items.aDoc}</textarea>
						</c:if>
					</div>
				</div>	
				<c:set var='pos' value='${pos=pos+1}'/>
			</c:if>
		
	</c:forEach>
	
	<br/>
	<!-- <button class="btn_more_reviews" id="btn_more_reviews">후기 ${reviewCnt}개 모두 보기</button> -->
</body>
</html>