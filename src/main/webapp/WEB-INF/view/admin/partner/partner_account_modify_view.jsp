<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_account_modify_view</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_modi_view.js"> </script>
</head>
<body>
	<div id='partner_account_modify'>
		<form name='frm_partner_modify' class='frm_partner_modify' method='post'>
			<input type='hidden' name='cUserCode' value='${vo.cUserCode}'/>
			<input type='hidden' name='itemCode' value='${sessionScope.itemCode}'/> 
			<input type="hidden" name="state" value='${vo.state}'/>
			<input type='hidden' name='hisSno' value='${vo.hisSno}'/><br/>
			<input type='hidden' name='hisUserSno'/>
			
			<span class='modi1'>캠지기 정보 수정 요청 상세 페이지</span><br/>
			<span class='modi2' style='display:none'>캠지기 정보 수정 요청 수정 및 삭제</span><br/>
			<div class='partner_modify_input'>
				<div class='partner_info'>
					<h3>기본 정보</h3>			
					<label>*아이디</label> 
					<input type='text' name='id' id='cId' value='${vo.id}' disabled /> <br/>
					<span id="cIdValidation"></span><br/>
					
					<label>*비밀번호</label>
					<button type='button' name='btn_partner_pwd_modify' id='cPwd' class='btn_partner_pwd_modify' onclick='partner_account_modify_view.modiPwd(this.form)' disabled style='cursor:default'>비밀번호 변경</button><br/>
					<span></span><br/><!-- 버튼 클릭 시 비밀번호 변경 페이지로 이동 -->
					
					<label>*성명</label>
					<input type='text' name='mName' id='cmName' value='${vo.mName}' disabled/> <br/>
					<label></label><span id="cmNameValidation"></span><br/>
			
					<label>*연락처</label>
					<input type='text' name='phone' id='cPhone' value='${vo.phone}' disabled/> <br/>
					<label></label><span id="cPhoneValidation"></span><br/>
			
					<label>*이메일</label>
					<input type='text' name='email' id='cEmail' value='${vo.email}' disabled/> <br/>
					<label></label><span id="cEmailValidation"></span><br/>
					
					<label>생년월일</label>
					<input type='date' name='birth' id='cbirth' value='${vo.birth}' disabled/> <br/>
					<span></span><br/>
					
					<label>성별</label>
					<input type='radio' id="m" name='gender' value='m' ${(vo.gender == 'm')?'checked':''} disabled style='cursor:default'/><label for="m" class='gender'>남자</label>
					<input type='radio' id="f" name='gender' value='f' ${(vo.gender == 'f')?'checked':''} disabled style='cursor:default'/><label for="f" class='gender'>여자</label><br/>
					<span></span><br/>
					</div>
				
				<div class='account_info'>
					<h3>정산 정보</h3>
					<label>예금주</label>
					<input type='text' name='busiBankName' id='cbusiBankName' value='${vo.busiBankName}' disabled><br/>
					<label>계좌번호</label>
					<select class='bank' name='busiBank' id='cbusiBank' disabled >
						<option value="은행 선택" <c:if test="${vo.busiBank == '은행 선택'}">selected</c:if>>은행 선택</option>
						<option value='NH농협' <c:if test="${vo.busiBank == 'NH농협'}">selected</c:if>>NH농협</option>
						<option value='KB국민' <c:if test="${vo.busiBank == 'KB국민'}">selected</c:if>>KB국민</option>
						<option value='카카오뱅크' <c:if test="${vo.busiBank == '카카오뱅크'}">selected</c:if>>카카오뱅크</option>
						<option value='신한' <c:if test="${vo.busiBank == '신한'}">selected</c:if>>신한</option>
						<option value='우리' <c:if test="${vo.busiBank == '우리'}">selected</c:if>>우리</option>
						<option value='IBK기업' <c:if test="${vo.busiBank == 'IBK기업'}">selected</c:if>>IBK기업</option>
						<option value='하나' <c:if test="${vo.busiBank == '하나'}">selected</c:if>>하나</option>
						<option value='새마을' <c:if test="${vo.busiBank == '새마을'}">selected</c:if>>새마을</option>
						<option value='토스뱅크' <c:if test="${vo.busiBank == '토스뱅크'}">selected</c:if>>토스뱅크</option>
						<option value='부산' <c:if test="${vo.busiBank == '부산'}">selected</c:if>>부산</option>
						<option value='대구' <c:if test="${vo.busiBank == '대구'}">selected</c:if>>대구</option>
						<option value='케이뱅크' <c:if test="${vo.busiBank == '케이뱅크'}">selected</c:if>>케이뱅크</option>
						<option value='신협' <c:if test="${vo.busiBank == '신협'}">selected</c:if>>신협</option>
						<option value='우체국' <c:if test="${vo.busiBank == '우체국'}">selected</c:if>>우체국</option>
						<option value='SC제일' <c:if test="${vo.busiBank == 'SC제일'}">selected</c:if>>SC제일</option>
						<option value='경남' <c:if test="${vo.busiBank == '경남'}">selected</c:if>>경남</option>
						<option value='수협' <c:if test="${vo.busiBank == '수협'}">selected</c:if>>수협</option>
						<option value='광주' <c:if test="${vo.busiBank == '광주'}">selected</c:if>>광주</option>
						<option value='전북' <c:if test="${vo.busiBank == '전북'}">selected</c:if>>전북</option>
						<option value='저축은행' <c:if test="${vo.busiBank == '저축은행'}">selected</c:if>>저축은행</option>
						<option value='씨티' <c:if test="${vo.busiBank == '씨티'}">selected</c:if>>씨티</option>
						<option value='제주' <c:if test="${vo.busiBank == '제주'}">selected</c:if>>제주</option>
						<option value='KDB산업' <c:if test="${vo.busiBank == 'KDB산업'}">selected</c:if>>KDB산업</option>
						<option value='SBI저축은행' <c:if test="${vo.busiBank == 'SBI저축은행'}">selected</c:if>>SBI저축은행</option>
						<option value='산림조합' <c:if test="${vo.busiBank == '산림조합'}">selected</c:if>>산림조합</option>
						<option value='BOA' <c:if test="${vo.busiBank == 'BOA'}">selected</c:if>>BOA</option>
						<option value='HSBC' <c:if test="${vo.busiBank == 'HSBC'}">selected</c:if>>HSBC</option>
					</select><br/>
					<label></label>
					<input type='text' name='busiBankNum' id='cbusiBankNum' value='${vo.busiBankNum}' disabled><br/>
					<span></span><br/>
				</div>
				<div class='business_info'>
					<h3>사업자 정보</h3>
					<label>사업자명</label>
					<input type='text' name='busiName' id='cbusiName' value='${vo.busiName}' disabled><br/>
					<span></span><br/>
					
					<label>대표자명</label>
					<input type='text' name='repName' id='crepName' value='${vo.repName}' disabled><br/>
					<span></span><br/>
					
					<label>*사업자 등록번호</label><!-- 중복성 봐서 체크.. -->
					<input type="text" name="busiNum" id="cbusiNum" value="${vo.busiNum}" disabled/><br/>
					<label></label><span id="cBusiNumValidation"></span><br>
						
					<label>관광사업(야영장) 등록번호</label>
					<input type="text" name="travelBus" id="ctravelBus" value="${vo.travelBus}" disabled/><br/>
					<span></span><br>
											
					<label>*세금계산서용 이메일</label>
					<input type='text' name='busiEmail' id='cbusiEmail' value='${vo.busiEmail}' disabled><br/>
					<span id="cBusiEmailValidation"></span><br/>
					
					<label>대표 전화번호</label>
					<input type='text' name='busiPhone' id='cbusiPhone' value='${vo.busiPhone}' disabled><br/>
					<span></span><br/>
					
					<%--hidden--%>
		  			<input type="hidden" name="regDate" id="regDate"/><br/>
				</div>
			</div><br/>
			
			<div class='btn_list'>
			<button type='button' name='btnmlist' class='btn_clist' onclick='partner_account_modify_view.list(this.form)'>확인</button><br/>
			<button type='button' name='btnmodify' class='btn_caccount_modify' onclick='partner_account_modify_view.modify(this.form)'>수정하기</button>
			
			<button type='button' name='btnmlist' class='btn_modisave' onclick='partner_account_modify_view.save(this.form)' style='display:none'>수정저장</button><br/>
			<button type='button' name='btnmodify' class='btn_modidelete' onclick='partner_account_modify_view.delete(this.form)' style='display:none'>요청삭제</button><br/>
			<button type='button' name='btnmcancel' class='btn_mcancel' onclick='partner_account_modify_view.cancel(this.form)' style='display:none'>취소</button>
			</div>
		</form>
	</div>
</body>
</html>