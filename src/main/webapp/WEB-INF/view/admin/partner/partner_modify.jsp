<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_modify</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner.css'>
</head>
<body>
	<div id='partner_modify'>
		<form name='frm_partner_modify' class='frm_partner_modify' method='post'>
			<h1>캠지기 정보수정</h1>
			<div class='partner_modify_input'>
					<div class='partner_info'>
						<h3>기본 정보</h3>
						<label>아이디</label>
						<input type='text' name='pId' /> <br/>
			
						<label>비밀번호</label>
						<button type='button' name='btn_partner_pwd_modify' class='btn_partner_pwd_modify'>비밀번호 변경</button><br/>
						
						<label>성명</label>
						<input type='text' name='pName' /> <br/>
				
						<label>연락처</label>
						<input type='text' name='pPhone'/> <br/>
				
						<label>이메일</label>
						<input type='text' name='pEmail'/> <br/>
						
						<label>생년월일</label>
						<input type='text' name='pBirth'/> <br/>
						
						<label>성별</label>
						<label class='gender'><input type='radio' name='pGender' value='m'/>남자</label>
						<label class='gender'><input type='radio' name='pGender' value='f'/>여자</label><br/>
					</div>
				
					<div class='account_info'>
						<h3>정산 정보</h3>
						<label>예금주</label>
						<input type='text'><br/>
						<label>계좌번호</label>
						<select class='bank'>
							<option hidden>은행 선택</option>
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
						<input type='text' name='refund'><br/>
					</div>
					<div class='business_info'>
						<h3>사업자 정보</h3>
						<label>사업자명</label>
						<input type='text'><br/>
						
						<label>대표자명</label>
						<input type='text'><br/>
												
						<label>세금계산서용 이메일</label>
						<input type='text'><br/>
						
						<label>대표 전화번호</label>
						<input type='text'><br/>
					</div>
					</div><br/>
					<button type='button' name='btnmodify' class='btn_modify'>수정요청</button>
					<div class='admin_permission'>
						<textarea></textarea><br/>
						<button type='button' name='btn_yes' class='btn_permission'>승인</button>
						<button type='button' name='btn_no' class='btn_permission'>반려</button>
						</div>
					
					</form>
	</div>
</body>
</html>