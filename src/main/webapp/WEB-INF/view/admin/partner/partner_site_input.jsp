<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner_input.css'>
<script defer src="./js/partner/partner_site.js"></script>
</head>
<body>
<div id='camping_site_input'>
	<div class="site_info">
		<span class="content_title">사이트 등록하기</span>
		<form id="frm_site_input" method="post">
			<input type="hidden" name="cUserCode" value="${sessionScope.cUserCode }">
			<input type="hidden" name="itemCode" value="${sessionScope.itemCode }">
			<input type="hidden" name="state" value="등록요청">
			<div class="site"> 
				<div>
					<label>사이트 이름</label>
					<input type='text' name='siteName' placeholder="\ : / ? * |  &quot; &lt; &gt; 를 제외한 10자리"/> <br/>
					<label>사이즈</label>
					<input type='text' name='siteSize' placeholder="ex) 3x3" /> <br/>
					<label>금액</label>
					<input type='text' name='price' placeholder="ex) 50000" /> <br/>
					<label>기준 인원</label>
					<input type='text' name='sPeople' placeholder="ex) 4"/> <br/>
					<label>최대 인원</label>
					<input type='text' name='mPeople' placeholder="ex) 6"/> <br/>
					<label>기준 차량</label>
					<input type='text' name='cntCar' placeholder="1"/> <br/>
					<label>사이트 소개글</label> <br/>
					<textarea  rows="5" cols="65" name="infoText" placeholder="사이트의 소개글을 작성해주세요."></textarea> <br/>
					<label>사이트 사진</label> <br/> 
					<label class="btn_site_upload" for="siteImg">사이트 사진 올리기</label> 
					<span class="caution">최소 2장 이상, 5장 이하의 사진을 올려주세요.</span><br/>
					<div class="preview_site"></div>				
				</div>
				
				<div class="filter">
					<span>입지 형태</span>
					<div class="filter_position">
						<input type="radio" name="siteType" id="position_radio1" value="파쇄석">
						<label for="position_radio1">파쇄석</label>
						<input type="radio" name="siteType" id="position_radio2" value="데크">
						<label for="position_radio2">데크</label>
						<input type="radio" name="siteType" id="position_radio3" value="잔디">
						<label for="position_radio3">잔디</label>
						<input type="radio" name="siteType" id="position_radio4" value="기타">
						<label for="position_radio4">기타</label>
					</div>
					<span>캠핑 유형</span>
					<div class="filter_stay">
						<input type="radio" name="category" id="stay_radio1" value="오토캠핑">
						<label for="stay_radio1">오토캠핑</label>
						<input type="radio" name="category" id="stay_radio2" value="글램핑">
						<label for="stay_radio2">글램핑</label>
						<input type="radio" name="category" id="stay_radio3" value="카라반">
						<label for="stay_radio3">카라반</label>
						<input type="radio" name="category" id="stay_radio4" value="차박">
						<label for="stay_radio4">차박</label>
					</div>
				</div>
			</div>
		</form>		
		<form id="frm_att" method="post" enctype="multipart/form-data">
			<div>	
				<input type="text" name="itemCode" value="${sessionScope.itemCode }" class="itemCode">
				<input type="text" name="siteName2">
				<input type="text" name="hisDetailSno">
				<input type="text" name="state" value="사이트등록요청">
				<input type="file" multiple="multiple" accept="image/*" id="siteImg" name="siteImg" onchange="site.siteImg(this)">
			</div>
		</form>
		<button type="button" class="btn_save" onclick="site.input()">저장하기</button> <br/>
		<button type="button" class="btn_reset">취소</button>
	</div>
	
	<div class="site_list">
		<div class="title">
			<span class="site_name">사이트명</span>
			<span class="site_stay">유형</span>
			<span class="site_price">가격</span>
			<span class="site_state">상태</span>			
		</div>
		<div class="items">
			<c:forEach var="sVo" items="${list }">
				<div class=item onclick="site.select(${sVo.siteCode})">
					<span class="site_name">${sVo.siteName}</span>
					<span class="site_stay">${sVo.category }</span>
					<span class="site_price">${sVo.price }원</span>
					<span class="site_state">${sVo.state }</span>
				</div>
			</c:forEach>
		</div>
	</div>
</div>	
</body>
</html>