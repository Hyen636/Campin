<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_request_input</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
</head>
<body>
<div id='partner_review_requset'>
		<form name='frm_partner_review_request' id='frm_partner_review_request' method='post'>
			<h2>요청사항 입력</h2>
			<div class="review_att">
				<img src="./image/img/partner_review2.jpg" width="100%">
			</div>
			<label>작성일</label>
			<input type='date' name='nal' value='2022-07-22'/><br/>
			<label>작성자</label>
			<input type='text' name='pid' size='26' value='캠지기'/><br/>
			<label>요청사항</label>
			<select name='count_qna' id='count_qna' size='1'> 
				<option value='1'>사이트관련</option>
				<option value='2'>리뷰관련</option>
				<option value='3'>문의관련</option>
				<option value='4'>기타문의</option>
			</select><br/>
			<label>제목</label>
			<input type='text' name='subject' value='리뷰 관련 문의 드립니다.' size='30'/><br/>
			<label>요청내용</label><br/>
			<label></label>
			<textarea name='doc'>문의사항 드립니다.</textarea><br/>
	
			<div class='btns_request'>
				<button type='button' class='btn_request_saver'>저장</button>
				<button type='button' class='btn_cancel'>취소</button>
			</div>
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