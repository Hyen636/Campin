<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_view.css'>
<script defer src="./js/partner/partner_site.js"></script>
</head>
<body>
<div id="partner_site_view">
	<span class="content_title">사이트 요청</span>
	<form id="frm_site_view" method="post">
		<input type="hidden" name="itemCode" value="${sVo.itemCode }">
		<input type="hidden" name="siteCode" value="${sVo.siteCode }">
		<input type="hidden" name="state" value=${state }>
		<input type="hidden" name="sno" value="${sno }">
		<input type="hidden" name="hisDetailSno" value="${hVo.hisDetailSno }">			
		<input type="hidden" name="rejectMsg">
	</form>
	<c:if test="${hVo.rejectMsg != null }">
		<div class="reject">
			<span class="reject_title">반려사유</span> <br/>
			<textarea class="rejectMsg">${hVo.rejectMsg }</textarea>		
		</div>
	</c:if>
	<div class="flex_div">
		<c:if test="${state != '등록요청'}">
			<div>
				<span class="sub_title">기존 사이트</span>
				<div class="site"> 
					<label>사이트 이름</label>
					<input type='text' name='siteName' value="${sVo.siteName}" /> <br/>
					<label>사이즈</label>
					<input type='text' name='siteSize' value="${sVo.siteSize }" /> <br/>
					<label>금액</label>
					<input type='text' name='price' value="${sVo.price}" /> <br/>
					<label>기준 인원</label>
					<input type='text' name='sPeople' value="${sVo.sPeople }" /> <br/>
					<label>최대 인원</label>
					<input type='text' name='mPeople' value="${sVo.mPeople }" /> <br/>
					<label>기준 차량</label>
					<input type='text' name='cntCar' value="${sVo.cntCar }" /> <br/>
					<label>사이트 소개글</label> <br/>
					<textarea name="infoText" >${sVo.infoText }</textarea> <br/>
					<label>사이트 사진</label> <br/> 
					<div class="preview_site">
					<c:forEach var="siteImg" items="${sList }">
						<div>
							<img class="site_img" src="./image/camping/${sVo.itemCode }/${sVo.siteName}/${siteImg.sysFile}"/> <br/>
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
		</c:if>		

		<div>
			<span class="sub_title">요청 내용</span>
			<div class="site"> 
				<label>사이트 이름</label>
				<input type='text' name='siteName' value="${hVo.siteName}" /> <br/>
				<label>사이즈</label>
				<input type='text' name='siteSize' value="${hVo.siteSize }" /> <br/>
				<label>금액</label>
				<input type='text' name='price' value="${hVo.price}" /> <br/>
				<label>기준 인원</label>
				<input type='text' name='sPeople' value="${hVo.sPeople }" /> <br/>
				<label>최대 인원</label>
				<input type='text' name='mPeople' value="${hVo.mPeople }" /> <br/>
				<label>기준 차량</label>
				<input type='text' name='cntCar' value="${hVo.cntCar }" /> <br/>
				<label>사이트 소개글</label> <br/>
				<textarea name="infoText" >${hVo.infoText }</textarea> <br/>
				<label>사이트 사진</label> <br/> 
				<div class="preview_site">
				<c:forEach var="hisSiteImg" items="${hList }">
					<div>
						<img class="site_img" src="./image/camping/${hVo.itemCode }/${hVo.siteName}/${hisSiteImg.sysFile}"/> <br/>
						<span>${hisSiteImg.oriFile}</span>
					</div>
				</c:forEach>
				</div>
			</div>
				
			<div class="filter">
				<span>입지 형태</span>
				<div class="filter_position">
					<input type="radio" name="hisSiteType" id="position_radio1" value="파쇄석">
					<label for="position_radio1">파쇄석</label>
					<input type="radio" name="hisSiteType" id="position_radio2" value="데크">
					<label for="position_radio2">데크</label>
					<input type="radio" name="hisSiteType" id="position_radio3" value="잔디">
					<label for="position_radio3">잔디</label>
					<input type="radio" name="hisSiteType" id="position_radio4" value="기타">
					<label for="position_radio4">기타</label>
				</div>
				<span>캠핑 유형</span>
				<div class="filter_stay">
					<input type="radio" name="hisCategory" id="stay_radio1" value="오토캠핑">
					<label for="stay_radio1">오토캠핑</label>
					<input type="radio" name="hisCategory" id="stay_radio2" value="글램핑">
					<label for="stay_radio2">글램핑</label>
					<input type="radio" name="hisCategory" id="stay_radio3" value="카라반">
					<label for="stay_radio3">카라반</label>
					<input type="radio" name="hisCategory" id="stay_radio4" value="차박">
					<label for="stay_radio4">차박</label>
				</div>					
			</div>
		</div>
	</div>
	<input type="hidden" class="temp_siteType" value="${sVo.siteType }">
	<input type="hidden" class="temp_category" value="${sVo.category }">
	<input type="hidden" class="temp_his_siteType" value="${hVo.siteType }">
	<input type="hidden" class="temp_his_category" value="${hVo.category }">
	<c:if test="${(sessionScope.job == 'A' or sessionScope.job == 'E') 
				and (hVo.state == '등록요청' or hVo.state == '수정요청')}">
		<div class="btns">
			<button type="button" class="btn_confirm" onclick="site.confirm()">승인</button> <br/>
			<button type="button" class="btn_reject" onclick="site.reject()">반려</button>
		</div>	
	</c:if>
</div>



<script>
let tempSiteType = $(".temp_siteType").val();
let tempCategory = $(".temp_category").val();
let tempHisSiteType = $(".temp_his_siteType").val();
let tempHisCategory = $(".temp_his_category").val();

let siteType = $("input[name=siteType]")
let category = $("input[name=category]")
let hisSiteType = $("input[name=hisSiteType]")
let hisCategory = $("input[name=hisCategory]")
for(let i = 0; i < siteType.length; i++){
	if(tempSiteType == $(siteType[i]).val()){
		$(siteType[i]).prop("checked", true);
		break;
	}
}
for(let i = 0; i < category.length; i++){
	if(tempCategory == $(category[i]).val()){
		$(category[i]).prop("checked", true);
		break;
	}
}
for(let i = 0; i < hisSiteType.length; i++){
	if(tempHisSiteType == $(hisSiteType[i]).val()){
		$(hisSiteType[i]).prop("checked", true);
		break;
	}
}
for(let i = 0; i < hisCategory.length; i++){
	if(tempHisCategory == $(hisCategory[i]).val()){
		$(hisCategory[i]).prop("checked", true);
		break;
	}
}
</script>
</body>
</html>