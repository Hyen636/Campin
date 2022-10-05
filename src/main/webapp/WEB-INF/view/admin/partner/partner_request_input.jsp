<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_request_input</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/partner/partner_request_input.js"></script>
</head>
<body>
<div id='partner_request_input'>
	<form name='frm_partner_request_input' id='frm_partner_request_input' method='post'>
		<h2>요청사항 입력</h2>
		<label>작성일</label>
		<input type='date' name='nal' value='${now }'readOnly/><br/>
		<label>작성자</label>
		<input type='text' name='mName' size='26' value='${name }' readOnly/><br/>
		
		<label>요청사항</label>
		<select name='category' id='count_qna' size='1' onchange=req_input.select(this.value)> 
			<option value='일반문의'>일반문의</option>
			<option value='탈퇴'>탈퇴</option>
		</select><br/>
		<label>제목</label>
		<input type='text' name='title' value='리뷰 관련 문의 드립니다.' size='30'/><br/>
		<label>요청내용</label><br/>
		<label></label>
		<textarea name='doc' id="doc">문의사항 드립니다.</textarea><br/>

		<div class='btns_request'>
			<button type='button' class='btn_request_saver' onclick=req_input.save(this.form)>저장</button>
			<button type='button' class='btn_cancel' onclick=req_input.cancel(this.form)>취소</button>
		</div>
		
		<label>작성자 코드</label>
		<input type='text' name='cUserCode' size='26' value='${cUserCode }' readOnly/><br/>
		<label>캠핑장 코드</label>
		<input type='text' name='itemCode' size='26' value='${itemCode }' readOnly/><br/>
		<%-- >
		<label>반려시 코멘트</label>
		<textarea name='doc2'>반려시 코멘트</textarea><br/>
		
		<div class='btns_confirm'>
			<button type='button' class='btn_confirm'>승인</button>
			<button type='button' class='btn_cancel'>이동</button>
			<button type='button' class='btn_reject'>반려</button>
		</div>--%>	
	</form>

</div>
</body>
</html>