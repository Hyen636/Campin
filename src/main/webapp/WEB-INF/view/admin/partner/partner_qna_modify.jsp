<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_qna_modify</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_request.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
</head>
<body>
<div id='partner_qna_modify'>
	<form name='frm_partner_qna_modify' class='frm_partner_qna_modify' method='post'>
		<h2>문의사항 답변 수정/삭제</h2>
		<label>작성일</label>
		<input type='date' name='nal' value=''/><br/>
		<label>작성자</label>
		<input type='text' name='id' size='26' value='hong11' size='30'/><br/>
		<label>제목</label>
		<input type='text' name='subject' size='26' value='문의드립니다.' size='30'/><br/>
		<label>문의내용</label><br/>
		<label></label>
		<textarea name='doc'>문의사항 답변 드립니다.</textarea><br/>
		<div class='btns_modify'>
			<button type='button' class='btn_modify'>수정</button>
			<button type='button' class='btn_delete'>삭제</button>
			<button type='button' class='btn_cancel'>취소</button>
		</div>
	</form>

</div>
</body>
</html>