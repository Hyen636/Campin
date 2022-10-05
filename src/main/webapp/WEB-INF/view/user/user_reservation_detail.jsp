<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_reservation_detail</title>
<link rel="stylesheet" type="text/css" href="./css/user/user_detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
</head>
<body>
<div id="user_reservation_detail">
	<form name="frm_user_reservation_detail" class="frm_user_reservation_detail" id="frm_user_reservation_detail" method="post">
	<h2>예약 상세 페이지 입니다.</h2>
	<div class="reservation_detail_info">
		<label class='orderCode'>예약번호</label>
		<input type="text" name="orderCode" size="30" value="${vo.orderCode}" readonly /><br/>
		<h3>예약자 정보</h3> <input type="hidden" name="uid" size="30" value="" readonly />
		<input type="hidden" name='userCode' class='userCode' value='${param.userCode}'/> 
		<label>예약자명</label>
		<input type="text" name="irum" size="30" class='mName' value="${vo.mName}" readonly /><br/>
		<label>연락처</label>
		<input type="text" name="phone" size="30" class='phone' value="${vo.phone}" readonly /><br/>
		<label>이메일</label>
		<input type="text" name="email" size="30" class='email' value="${vo.email}" readonly /><br/>
		<label>환불계좌</label>
		<input type='text' name='return_bank'size='3' class='refBank' value='${vo.refBank}' readonly />
		<input type='text' name='return_account'size='20' class='refAct' value='${vo.refAct}' readonly /><br/><%--무통장입금시만 표기 --%>
		<br/>
		<label>차량번호</label>
		<input type="text" name="userCar" size="30" class='userCar' id='userCar' value='${vo.userCar}' readonly /><br/>
		<label>반려동물여부</label>
		<c:if test='${vo.pet == "t" }'>
		<label class='pet1'><input type="checkbox" class='petCO' checked disabled/> 같이갑니다🦮</label>
		</c:if>
		<c:if test='${vo.pet == "f" }'>
		<label class='pet1'><input type="checkbox" class='petCO' checked disabled/> N </label>
		</c:if>
		
		<label class='pet2'><input name='pet' type="radio" class='petC' value='t'/> 같이갑니다🦮</label>
		<label class='pet2'><input name='pet' type="radio" class='petC' value='f'/> N </label><br/>
		
		<label>요청사항</label><br/>
		<label></label>
		<textarea name="doc" class='doc' readonly disabled>${vo.doc}</textarea><br/>
		<c:if test='${vo.state == "예약대기" || vo.state == "입금대기"}'>
		<div class="btn_modi">
			<button type="button" class="btn_reservation_modify" onclick='user_mypage.reservationModify()'>예약정보수정</button> <%--readonly에서 수정하기 필요하면 활성화되고 저장되게 --%>
			<button type="button" class="btn_reservation_save" onclick='user_mypage.reservationSave(this.form)'>저장</button> <%--<disabled였다가 수정 누르면 활성화>--%>
		</div>
		</c:if>
	</div>

	<div class="camp_info">
		<h3>캠핑장 정보</h3>
		<div class='pre_img1'>
			<img class="preImage_image1" src="./image/camping/${vo.itemCode}/${vo.sysFile}" width="500px"/>
		</div><br/>
		<label>캠핑장</label>
		<input type="text" name="partner" size="30" value="${vo.iName}" readonly /><br/>
		<label>사이트</label>
		<input type='text' name='camp_site'size='30' value='${vo.siteName}' readonly /><br/>
		<label>금액</label>
		<input type='text' name='price'size='20' value='${vo.price}' readonly /><br/>
		<label>추가옵션</label> <%-- 값으로해서 그냥 여러건도 한건으로 출력되게 --%>
		<!--<input type='text' name='add_option'size='30' value='바베큐:고기 목살추가 20000원 / 장작추가 : 10000원' readonly /><br/> -->
		<div class="add_option">
			<span>${vo.extraOpt} ${vo.extraFee}원</span> <%-- 게시판 첨부파일처럼 여러건 한줄로 출력되게 --%>
		</div><br/>
		<label>체크인</label>
		<input type='date' name='check_in'size='20' value='${vo.dateStr}' readonly />
		<label>체크아웃</label>
		<input type='date' name='check_out'size='20' value='${vo.dateEnd}' readonly /><br/>
		<label>성인</label>
		<input type="text" name="count_ad" size="2" value="${vo.adult}명" readonly /><br/>
		<label>아동</label>
		<input type="text" name="count_ad" size="2" value="${vo.child}명" readonly />
		<label>인원</label><input type="text" name="count_tot" size="5" value="총 ${vo.adult+vo.child}명" readonly /><br/>
	</div>
	
	<div class='pay_info'>
		<h3>결제 내역</h3>
		<label>총결제금액</label>
		<input type='text' name='priceAmt'size='20' value='${vo.amt}원' readonly /><br/>
		<label>결제수단</label>
		<input type='text' name='payment'size='20' value='${vo.payment}' readonly /><br/>
		<label>예약상태</label>
		<input type='text' name='reservation_status'size='20' value='${vo.state}' readonly /><br/>
		
		<br/>
		<c:if test='${vo.payment == "무통장입금" && vo.state == "입금대기"}'>
		<h3>무통장 입금 안내</h3>
		<label>입금계좌</label>
		<input type='text' name='account_num'size='6' value='${vo.busiBank}' readonly />
		<input type='text' name='account_num'size='20' value='${vo.busiNum}' readonly /><br/>
		<label>예금주</label>
		<input type='text' name='account_irum'size='10' value='${vo.busiName}' readonly /><br/>
		
		<fieldset>
		예약날짜 기준 다음날 오후 2시까지 총결제금액이 입금되지 않으면 자동 취소 됩니다.<br/>
		(은행에 따라서, 밤 11시 30분 이후로는 온라인 입금이 제한될 수 있습니다.)<br/>
		</fieldset>
		</c:if>
	</div>
	
	<div class='btns_detail'>
		<button type='button' class='btn_mypage' onclick='user_mypage.user_mypage()'>확인/마이페이지</button>
		<c:if test='${vo.state == "예약대기" || vo.state == "입금대기" || vo.state == "예약완료"}'>
		<button type='button' class='btn_cancel' onclick='user_mypage.cancelR(this.form)'>예약취소요청</button>
		</c:if>
		<button type='button' class='btn_home' onclick='user_mypage.user_search()'>홈/메인페이지</button>
	</div>
	
	</form>
	
	<div class="info_field">
	<h4>유의사항</h4>		
		<fieldset>
		- 예약취소 시점과 해당 카드서의 환불 처리 기준에 따라 취소금액의 환급방법과 환급일은 다소 차이가 있을 수 있습니다.<br/>
		- 예약 취소 시, 최초 결제 수단으로 환불 처리 가능합니다.<br/>
		- 기타 문의사항은 캠핑장 혹은 문의 게시판을 이용하시기 바랍니다.<br/>
		- 예약 확정전까지는 예약자 정보 수정이 가능합니다.
		</fieldset>
	</div>
</div>
</body>
</html>