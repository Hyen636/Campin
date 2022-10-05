<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 글쓰기</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_detail.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_search_detail_qna.js"></script>
</head>
<body>
<div id='user_qna_input'>
	<form name='frm_user_qna_input' class='frm_user_qna_input' method='post'>
		<h2>문의사항</h2>
		<label for="pwdCheck">비밀글 Check</label>
		<input type="checkbox" name="Check" id="Check" value="f" onclick="funcClick();"><br>
		<label>작성일</label>
		<input type='date' name='nal' id='inputDate' readOnly/><br/>
		<label>작성자</label>
		<input type='text' name='id' id="userid" size='26' value='' size='30' readOnly/><br/>
		<label>제목</label>
		<input type='text' name='title' size='26' value='문의드립니다.' size='30'/><br/>
		<label>문의내용</label><br/>
		<label></label>
		<textarea name='doc'>문의사항 드립니다.</textarea><br/>
		<div class='btns_input'>
			<button type='button' class='btn_save' onclick="user_qna.qnaSave(this.form)">저장</button>
			<button type='button' class='btn_cancel' onclick="user_qna.qnaCancel()">취소</button>
		</div>
		
		<input type="hidden" name="userCode" id='qna_userCode' value=''>
		<input type="hidden" name="itemCode" id='qna_itemCode' value=''>
		<input type="hidden" name="pwdCheck" id='pwdCheck' value='f'>
		
	</form>

</div>
</body>
</html>