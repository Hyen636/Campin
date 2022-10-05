<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_join_view</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/partner/partner_join.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_join.js"> </script>
</head>
<body>
<div id="partner_join_view">
	<div id="partner_join">
		<form name="frm_partner_join" class="frm_partner_join" method="post">			
			<span class='modi1'>캠지기 회원가입 승인요청 상세 페이지</span><br/>
			<span class='modi2' style='display:none'>캠지기 회원가입 정보 수정 및 삭제</span><br/>
				<div class="partner_join_input">
						<input type="hidden" name="hisUserSno" id="hisUserSno"/>
						<input type="hidden" name="cUserCode" id="cUserCode" value="${vo.cUserCode}"/>
						<input type="hidden" name="hisSno" id="hisSno" value="${vo.hisSno}"/>
						<input type="hidden" name="state" value="${vo.state}"/>
					<div class="partner_info">
						<h3>기본 정보</h3>
						<span class="caution">*필수 항목은 모두 작성하여 회원가입 진행 부탁드립니다.</span><br/>
						
						<label>*아이디</label>
						<input type="text" name="id" id="cId" value="${vo.id}" placeholder="6~12자 사이의 영문 소문자와 숫자를 조합해주세요." disabled/><br/>
						<span></span><br/>
						
						<label>*비밀번호</label><input type="hidden" name="pwd">
						<span class="caution">비밀번호는 '비밀번호 변경' 메뉴에서 수정가능합니다.</span><br/>
						<span></span><br/>
						
						<label>*성명</label>
						<input type="text" name="mName" id="cmName" value="${vo.mName}" placeholder="2~6자의 한글로 입력해주세요." disabled/> <br/>
						<span id="cmNameValidation"></span><br/>
				
						<label>*연락처</label>
						<input type="text" name="phone" id="cPhone" value="${vo.phone}" placeholder="-를 제외한 숫자만 입력해주세요." disabled/> <br/>
						<span id="cPhoneValidation"></span><br/>
						
						<label>*이메일</label>
						<input type="text" name="email" id="cEmail" value="${vo.email}" placeholder="예시) campin@campin.co.kr" disabled/> <br/>
						<span id="cEmailValidation"></span><br/>
						
						<label>생년월일</label>
						<input type="date" name="birth" id='cbirth' value="${vo.birth}" disabled/><br/>
						<span></span><br>
						
						<label>성별</label>
						<input type='radio' id="m" name='gender' value='m' ${vo.gender =='m'?'checked':'' } disabled/><label for="m" class='gender'>남자</label>
						<input type='radio' id="f" name='gender' value='f' ${vo.gender == 'f'?'checked':'' } disabled/><label for="f" class='gender' >여자</label><br/>
						<span></span><br/>
					</div>
				
					<div class="account_info">
						<h3>정산 정보</h3>
						<label>예금주</label>
						<input type="text" name="busiBankName" id="cbusiBankName" value="${vo.busiBankName}" disabled><br/>
						<span></span><br>
						<label>계좌번호</label>
						<select class='bank' name='busiBank' id='cbusiBank' disabled>
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
						<input type="text" name="busiBankNum" id="cbusiBankNum" value="${vo.busiBankNum}" disabled><br/>
						<span></span><br>
					</div>
					
					<div class="business_info">
						<h3>사업자 정보</h3>
						<label>사업자명</label>
						<input type="text" name="busiName" id="cbusiName" value="${vo.busiName}" disabled><br/>
						<span></span><br>
						
						<label>대표자명</label>
						<input type="text" name="repName" id="crepName" value="${vo.repName}" disabled><br/>
						<span></span><br>
						
						<label>*사업자 등록번호</label>
						<input type="text" name="busiNum" id="cbusiNum" value="${vo.busiNum}" placeholder="-를 제외한 11~12자리의 숫자만 입력해주세요." disabled><br/>
						<span id="cBusiNumValidation"></span><br/>
						<span></span><br>
						
						<label>관광사업(야영장) 등록번호</label>
						<input type="text" name="travelBus" id="ctravelBus" value="${vo.travelBus}" disabled><br/>
						<span></span><br>
						
						<label>*세금계산서용 이메일</label>
						<input type="text" name="busiEmail" id="cbusiEmail" value="${vo.busiEmail}" placeholder="세금계산서용 이메일을 입력해주세요" disabled><br/>
						<span id="cBusiEmailValidation"></span><br/>
						
						<label>대표 전화번호</label>
						<input type="text" name="busiPhone" id="cbusiPhone" value="${vo.busiPhone}" disabled><br/>
					</div>	
					
					<%--hidden--%>
		  			<input type="hidden" name="regDate" id="regDate" value='${vo.regDate}'/><br/>
		  			<input type='hidden' name='delBusi' class='delFile1' value='${vo.busiImg}'/>
					<input type='hidden' name='delTravel' class='delFile2' value='${vo.travelImg}'/>
 					
					<input type='hidden' name='modiBusi' class='modifyFile1'/>
					<input type='hidden' name='modiTravel' class='modifyFile2'/>
		  	 </div>
		</form>
		
		<div class="partner_join_inputAtt">
			<form name="frm_partner_join_att" class="frm_partner_join_att" method="post" 
			  enctype="multipart/form-data" accept-charset="UTF-8">
		   		<div class="att_file">
				<h3>등록증 정보</h3>
				<label class="label_subject">사업자 등록증</label><br/>
					<input class="att_busiImg" placeholder="사업자 등록증 첨부파일" id="att_busiImg" value="${vo.busiImg}" disabled>
			    	<label for="busiImg">파일찾기</label>
			    	<input type="file" id="busiImg" name="busiImg" onchange="partner_join.busiImg(this)" disabled><br/>
		    				
				<label class="label_subject">관광사업(야영장) 등록증</label><br/>
					<input class="att_travelImg"  placeholder="관광사업 등록증 첨부파일" id="att_travelImg" value="${vo.travelImg}" disabled>
			    	<label for="travelImg">파일찾기</label>
			    	<input type="file" id="travelImg" name="travelImg" onchange="partner_join.travelImg(this)"  disabled>
					
					<input type="text" name="cUserCode" id="cUserCode" value="${vo.cUserCode}"/>
					<input type="text" name="hisSno" id="hisSno" value="${vo.hisSno}"/>
					
					<div class='btn_list'>
					<button type='button' name='btnlist' class='btn_join_list' onclick='partner_join_view.list(this.form)'>확 인</button><br/>
					<button type='button' name='btnmodify' class='btn_join_modify' onclick='partner_join_view.modify(this.form)' style='display:none'>수정하기</button>
					
					<button type='button' name='btnmlist' class='btn_modisave' onclick='partner_join_view.save()' style='display:none'>수정저장</button><br/>
					<button type='button' name='btnmodify' class='btn_modidelete' onclick='partner_join_view.delete(this.form)' style='display:none'>가입요청삭제</button><br/>
					<button type='button' name='btnmcancel' class='btn_mcancel' onclick='partner_join_view.cancel(this.form)' style='display:none'>취 소</button>
					
					</div>
				</div>
			</form>
		 </div>			
	</div>
	</div>
</body>
</html>