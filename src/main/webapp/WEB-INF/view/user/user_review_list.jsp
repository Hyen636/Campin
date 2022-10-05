<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_review_list</title>
</head>
<body>
<div id="user_review_list">
	<form>
	<h1>나의 리뷰</h1><br>
	<c:if test='${listR.size() == 0 }'>
		<img src="image/icon/null.png" width='20%' class='null1'>
		<div class='null2'>리뷰 내역이 존재하지 않습니다.</div>
	</c:if>
	<c:if test='${listR.size() != 0 }'>
		<div class="list">
			<c:forEach var='vo' items="${listR}" varStatus="status">
			<div class="review">
				<div class="top">
					<img src="./image/camping/${vo.itemCode}/${vo.sysFileC}">
					<div class='first'>
						<span>${vo.iName}</span> <br/>
						<span class="address">${vo.address1}</span> <br/>
					</div>
					<div>${vo.dateStr} ~ ${vo.dateEnd}</div>
					<div class='amt'>${vo.amt}원</div>
					<div class="btns">
					<c:if test='${vo.state == "미답변"}'>
						<button type="button" class='btn_Rmodify_${status.index }' onclick='user_mypage.reviewModify(${status.index})'>수정</button>
					</c:if>	
						<img class='deleteR ${status.index }' src="./image/icon/close_black.png">
					</div>
				</div>
				<div class="bottom">
					<div>
					<div class=dStar>
						<span class="star3 star3_${status.index}">
							★★★★★
	  						<span class='star4 star4_${status.index}'>★★★★★</span>
	  						<input type="range" class='drawStar3_${status.index}' oninput="user_mypage.star2(${status.index})" value="0.5" step="0.5" min="0" max="5" disabled>
						</span>
	  					<input type='text' class='score3 score3_${status.index}' value='${vo.score}' disabled/>
	  					<div class='btns btns_${status.index}'>
							<button type='button' class='btn_photo_input' onclick='user_mypage.Rclick(${status.index});user_mypage.Rindex(${status.index})'>사진업로드</button>
							<button type='button' class='btn_save' onclick='user_mypage.reviewSave2(${status.index})'>저장</button>
							<button type='button' class='btn_cancle' onclick='user_mypage.cancelReview2(${status.index})'>취소</button>
	  					</div>
						<textarea class="review_content reviewC_${status.index}" disabled >${vo.doc}</textarea>
						<input type='hidden' class='docS_${status.index}' value='${vo.doc}'>
						</div>
					<div class="review_photo">
					<img  class='review_photo revPhotoPreview_${status.index}' id='preview_${status.index}' src="./image/camping/${vo.itemCode}/review/${vo.sysFile}" alt=''/>
					<input type="hidden" class='firstPhoto_${status.index}' value="./image/camping/${vo.itemCode}/review/${vo.sysFile}">
					</div>
				<c:if test='${vo.state == "답변완료" }'>
				<div class='reviewR'>
					<span class='iName'> ㄴ ${vo.iName}</span><br/>
					<span class='doc'>${vo.RAnswer}</span>
				</div>
				</c:if>
					</div>
				</div>
				<input type='hidden' class='orderCode_${status.index}' value='${vo.orderCode}'>
				<input type='hidden' class='itemCode_${status.index}' value='${vo.itemCode}'>
				<input type='hidden' class='sysFile_${status.index}' value='${vo.sysFile}'>
			</div>
			</c:forEach>
		</div>
	</c:if>
	</form>
	
	<form name='reviewModify' class='frm_reviewModify' id='frm_reviewModify' method='post'>
		<input type='number' name='orderCode' class='orderCode_i'/>
		<input type='text' name='sysFile' class='sysFile_i'/>
		<input type='number' name='itemCode' class='itemCode_i'/>
		<input type='number' name='userCode' class='userCode_i' value='${param.userCode}'/>
		<input type='text' name='doc' class='doc_i'/>
		<input type="number" name='score' class='score_i'/>
		<input type='file' name="oriFile2" class='rev_photo_input revP_${status.index}' id='revP' accept="img/*"/>
		
	</form>
</div>
</body>
<script>
	let score = $(".score3");
	for(let i =0; i < score.length; i++){
		console.log($(score[i]).val());
		let per = ($(score[i]).val()*20)+'%';
		$('.star4_'+[i]).css("width",per);
		
	}

</script>
</html>