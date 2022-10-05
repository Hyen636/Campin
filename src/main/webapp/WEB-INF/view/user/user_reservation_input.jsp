<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_reservation_input</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_reservation.css'>
<script defer src="./js/user/user_reservation.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

</head>
<body>
	<div id='user_reservation_input'>
		<div class='user_info'>
			<form name='frm_user_reservation_input' class='frm_user_reservation_input' id='frm_user_reservation_input' method='post'>
			
			<h1>예약 정보 입력</h1>
			
			<h2>예약자 정보</h2>
			<label class='setSame'><input type='checkbox' id='setSame' onclick='user.setSame()'>회원정보와 같음</label><br/>
			<div class='hidden'>
			<input type='text' name='userCode' class='userCode' value='${param.userCode}'>
			<input type='text' name='phoneS' value='${vo.phone}'>
			<input type='text' name='emailS' value='${vo.email}'>
			<input type='text' name='mNameS' value='${vo.mName}'>
			<!-- 희찬
			<input type='text' name='siteCode' value='${vo1.siteCode}'>
			 -->
			<input type='text' name='siteCode' value='${vo2.siteCode}'>
			
			<input type='text' name='dateOrder' class='dateO'><br/>
			<!-- 희찬
			<input type='text' name='itemCode' class='itemCode' value='${vo1.itemCode}'>
			 -->
			<input type='text' name='itemCode' class='itemCode' value='${vo2.itemCode}'>
			<input type='text' name='busiBank' value='${vo1.busiBank}'>
			<input type='text' name='busiName' value='${vo1.busiName}'>
			<input type='text' name='busiNum' value='${vo1.busiNum}'>
			<!-- 희찬 
			<input type='text' name='cUserCode' value='${vo1.cUserCode}'>
			-->
			<input type='text' name='cUserCode' value='${vo2.cUserCode}'>
			
			<input type='text' name='priceO' class='iPriceI' value='${vo1.iPrice}'>
			<input type='text' name='price' class='price'>
			<input type='text' name='amt' class='amt' value='0'>
			<input type='text' name='state' class='state'>
			<input type='text' name='orderCode2' class='orderCode2' >
			</div>
			<label>예약자명</label>
			<input type='text' name='mName' class='mName'><br/>
			<label>연락처</label>
			<input type='text' name='phone' class='phone'><br/>
			<label>이메일</label>
			<input type='text' name='email' class='eamil'><br/>
			<label>환불계좌</label>
			<select class='bank' id='bank' name='refBank'>
				<option hidden='hidden' value='0'>은행선택</option>
				<option>NH농협</option>
				<option>KB국민</option>
				<option>카카오뱅크</option>
				<option>신한</option>
				<option>우리</option>
				<option>IBK기업</option>
				<option>하나</option>
				<option>새마을</option>
				<option>토스뱅크</option>
				<option>부산</option>
				<option>대구</option>
				<option>케이뱅크</option>
				<option>신협</option>
				<option>우체국</option>
				<option>SC제일</option>
				<option>경남</option>
				<option>수협</option>
				<option>광주</option>
				<option>전북</option>
				<option>저축은행</option>
				<option>씨티</option>
				<option>제주</option>
				<option>KDB산업</option>
				<option>SBI저축은행</option>
				<option>산림조합</option>
				<option>BOA</option>
				<option>HSBC</option>
			</select><br/>
			<label></label>
			<input type='text' name='refAct' class='refAct'><br/>
			
			<div class='reservation_detail'>
			<h2>예약상품 정보</h2>
			<!-- 희찬
			<img src="./image/camping/${vo1.itemCode}/${vo.sysFile}"/><br/>
			 -->
			<img src="./image/camping/${vo2.itemCode}/${vo.sysFile}"/><br/>
			<input type='text' name='iName' value='${vo1.iName}' class='partner'/></br>
			<input type='text' name='siteName' value='${vo1.siteName}' class='site'/></br>
			<label>추가옵션</label><br/>
			<div class='selected_option'>
			<c:forEach var='vo' items="${list}" varStatus="status">
				<div class='Opt'>
				<span class='extraOpt'>${vo.optItem}</span>
				<button type='button' class='minus_${status.index}' onclick = 'user.optMinus(${status.index})'>-</button>
				<input type='number' name='ea' class='ea ea_${status.index}' value='0' />
				<button type='button' class='plus_${status.index}' onclick = 'user.optPlus(${status.index})'>+</button>
				<span class='optPrice optP_${status.index}'>0</span><span>원</span><br/>
				<input type='hidden' class='optPrice optP1_${status.index}' value='${vo.price}'>
				</div><br/>
			</c:forEach>
				<input type='text' name='extraOpt' class='extraOptTot' value=''>
				<input type='text' name='extraFee' class='extraFeeTot' value='0'>
			</div>
				<label>숙박기간</label>
				<label class='nalT'>체크인</label>
				<input type='text' name='dateStr' class='nal dateStr' value='${vo2.checkInSave}'><br/>
				<label></label>
				<label class='nalT'>체크아웃</label>
				<input type='text' name='dateEnd' class='nal dateEnd' value='${vo2.checkOutSave}'>
			</div>
			
			<div class='number_info'>
			<h2>전체 인원 정보</h2>
			<label>성인</label>
			<button type='button' class='minus' onclick='user.AdultMinus()' >-</button>
			<input type='number' name='adult'  class='ea eaA' value='${vo2.adultCountSave}'/>
			<button type='button' class='plus' onclick='user.AdultPlus()'>+</button><br/>
			<label>아동</label>
			<button type='button' class='minus' onclick='user.ChildMinus()'>-</button>
			<input type='number' name='child'  class='ea eaC' value='${vo2.childCountSave}'/>
			<button type='button' class='plus' onclick='user.ChildPlus()'>+</button><br/>
			<label>기준인원</label> 
			<span>${vo1.sPeople}명</span><br/>
			<label>추가 가능 인원</label>
			<span>${vo1.mPeople - vo1.sPeople}명 (성인 - ${vo1.extraAdult}원 / 아동 - ${vo1.extraChild}원)</span><br/>
			<label>총 인원 제한</label>
			<span>${vo1.mPeople}명</span><br/>
			<input type='hidden' class='extraAdult' value='${vo1.extraAdult}'>
			<input type='hidden' class='extraChild' value='${vo1.extraChild}'>
			<input type='hidden' class='mPeople' value='${vo1.mPeople}'>
			<input type='hidden' class='sPeople' value='${vo1.sPeople}'>
			<input type='text' class='cPeople'>
			
			
			</div>
			
			<div class='add_info'>
				<h2>추가 정보</h2>
				<label>차량번호</label>
				<input type='text' name='userCar'/> <br/>
				
				<label>반려동물</label>
				<label class='pet2'><input name='pet' type="radio" class='petC' value='t'/> 같이갑니다🦮</label>
				<label class='pet2'><input name='pet' type="radio" class='petC' value='f'checked/> N </label><br/>
				
				<label>요청 사항</label><br/>
				<textarea name='doc' class='doc'></textarea>
			</div>
			<div class='pay'>
			<h2>결제 방법</h2>
			<input type="radio" id="kakaopay" name="payment" value='카카오페이'><label for="kakaopay">카카오 페이 <img class='kakao' src='image/icon/kakaoPay.png' width='25%'></label>
		<!-- <input type="radio" id="card" name="payment" value='카드결제'><label for="card">카드 결제</label> -->	
			<input type="radio" id="cash" name="payment" value='무통장입금'><label for="cash">무통장 입금</label>
			
			<button type='button' id='btn_reservation' class='btn_reservation' onclick='user.user_reservation(this.form)'>예약하기</button>
			<button type='button' id='btn_kakao' onclick='user.payment(this.form)'>카카오페이</button>
			</div>
			
	
			
		</form>
		</div>
		
		<div>
			<div class='price_detail'>
				<h2 class='h2_price_detail'>요금 세부정보</h2>
				<span class='iPrice'>${vo1.iPrice}</span><span>원 x </span><span class='day'></span><span>박</span>
				<span class='price_before_tot'></span><span>원</span><br/>
				
				<span class='price_tot_k'>총 합계</span>
				<span class='price_tot'></span><span class='won'>원</span>
				<br/>
				<button type='button' class='btn_reservation' onclick='user.reservation()'>예약하기</button>
			</div>
		</div>
		
	
	</div>
	
</body>
</html>