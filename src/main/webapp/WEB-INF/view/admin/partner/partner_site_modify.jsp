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
<div id="partner_site_modify">
	<span class="content_title">사이트 수정하기</span>
		<form id="frm_site_modify" method="post">
			<input type="hidden" name="itemCode" value="${vo.itemCode }">
			<input type="hidden" name="siteCode" value="${vo.siteCode }">
			<input type="hidden" name="state" value="수정요청">
			<div class="site"> 
				<div>
					<label>사이트 이름</label>
					<input type='text' name='siteName' value="${vo.siteName}" /> <br/>
					<label>사이즈</label>
					<input type='text' name='siteSize' value="${vo.siteSize }" /> <br/>
					<label>금액</label>
					<input type='text' name='price' value="${vo.price}" /> <br/>
					<label>기준 인원</label>
					<input type='text' name='sPeople' value="${vo.sPeople }" /> <br/>
					<label>최대 인원</label>
					<input type='text' name='mPeople' value="${vo.mPeople }" /> <br/>
					<label>기준 차량</label>
					<input type='text' name='cntCar' value="${vo.cntCar }" /> <br/>
					<label>사이트 소개글</label> <br/>
					<textarea rows="5" cols="65" name="infoText" >${vo.infoText }</textarea> <br/>
					<label>사이트 사진</label> <br/> 
					<label class="btn_site_upload" for="siteImg">사이트 사진 올리기</label> 
					<span class="caution">최소 2장 이상, 5장 이하의 사진을 올려주세요.</span><br/>
					<div class="preview_site">
						<c:forEach var="siteImg" items="${siteImgList }">
							<div>
								<img class="site_img" src="./image/camping/${vo.itemCode }/${vo.siteName}/${siteImg.sysFile}"/> <br/>
								<span>${siteImg.oriFile}</span>
							</div>
						</c:forEach>
					</div>				
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
			<input type="hidden" class="temp_siteType" value="${vo.siteType }">
			<input type="hidden" class="temp_category" value="${vo.category }">
		</form>		
		<form id="frm_att" method="post" enctype="multipart/form-data">
			<div>	
				<input type="hidden" name="itemCode" value="${vo.itemCode }" class="itemCode">
				<input type="hidden" name="siteCode" value="${vo.siteCode }">
				<input type="hidden" name="state" value="사이트수정요청">
				<input type="text" name="siteName2" >
				<input type="text" name="hisDetailSno">
				<input type="file" multiple="multiple" accept="image/*" id="siteImg" name="siteImg" onchange="site.siteImg(this)">
			</div>
		</form>
	<button type="button" class="btn_save" onclick="site.modify()">수정요청</button> <br/>
	<button type="button" class="btn_reset">취소</button>
</div>
</body>
</html>