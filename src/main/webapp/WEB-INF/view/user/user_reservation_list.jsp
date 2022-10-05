<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="user_reservation_list">
	<form name='frm_user_reservation_list' id='frm_user_reservation_list' method='post'>
		<span>예약 내역</span><br/>
	<c:if test='${listR.size() == 0 }'>
		<img src="image/icon/null.png" width='20%' class='null1'>
		<div class='null2'>예약 내역이 존재하지 않습니다.</div>
	</c:if>
	
	<c:if test='${listR.size() != 0 }'>
		<input type='text' name='userCode' value='${param.userCode}' hidden='hidden'>
		<div class="list">
			<c:forEach var='vo' items="${listR}" varStatus="status">
				<div class='reservation reservation_${status.index }'>
					<img src="./image/camping/${vo.itemCode}/${vo.sysFile}">
					<div class='first' onclick='user_mypage.viewDetail(${status.index})'>
						<span>${vo.iName}</span> <br/>
						<span class="address">${vo.address1}</span> <br/>
						<span>${vo.dateStr} ~ ${vo.dateEnd}</span>
					</div>
					<div class="second">
						<div>${vo.amt}원</div> <br/>	
					</div>
					<div class="last">
						<div>
							<span>${vo.state}</span> 
							
							<c:if test='${vo.state == "방문완료" }'>
								<button type="button" class='btn_review_input' onclick='user_mypage.showRInput(${status.index})'>리뷰 작성</button>
							</c:if>
							<c:if test='${vo.state == "리뷰완료" }'>
								<button type="button" class='btn_review_view' onclick='user_mypage.showRView()'>리뷰 보기</button>
							</c:if>
						</div>
					</div>
				</div>
		
				<div class='review_input revInput_${status.index}'>
					<img  class='review_photo revPhotoPreview_${status.index}' id='preview_${status.index}' src='' alt=''/>
					<span class="star star_${status.index}">
						★★★★★
  						<span class='star2 star2_${status.index}'>★★★★★</span>
  						<input type="range" class='drawStar_${status.index}' oninput="user_mypage.star(${status.index})" value="0.5" step="0.5" min="0" max="5">
					</span>
  					<input type='text' class='score score_${status.index}' value=''/>
  					
					<textarea class='doc doc_${status.index}'></textarea>	<br/>
					<button type='button' class='btn_photo_input' onclick='user_mypage.click(${status.index});user_mypage.index(${status.index})'>사진업로드</button>
					<button type='button' class='btn_save' onclick='user_mypage.reviewSave(${status.index})'>저장</button>
					<button type='button' class='btn_cancle' onclick='user_mypage.cancelReview(${status.index})'>취소</button>
					
					<input type='hidden' name='orderCode1' class='orderCode_${status.index}' value='${vo.orderCode}'>
					<input type='hidden' name='siteCode' class='siteCode_${status.index}' value='${vo.siteCode}'>
					<input type='hidden' name='siteName' class='siteName_${status.index}' value='${vo.siteName}'>
					<input type='hidden' name='itemCode' class='itemCode_${status.index}' value='${vo.itemCode}'>
				</div>
			</c:forEach>
		</div>
	</c:if>
	</form>
	
	<form name='reviewInput' class='frm_reviewInput' id='frm_reviewInput' method='post'>
		<input type='date' name='nal' class='nal_i'/>
		<input type='number' name='orderCode' class='orderCode_i'/>
		<input type='number' name='itemCode' class='itemCode_i'/>
		<input type='number' name='userCode' class='userCode_i' value='${param.userCode}'/>
		<input type='number' name='siteCode' class='siteCode_i'/>
		<input type='text' name='siteName' class='siteName_i'/>
		<input type='text' name='doc' class='doc_i'/>
		<input type="number" name='score' class='score_i'/>
		<input type='file' name="oriFile2" class='rev_photo_input revP_${status.index}' id='revP' accept="img/*"/>
	</form>
</div>

</body>
</html>