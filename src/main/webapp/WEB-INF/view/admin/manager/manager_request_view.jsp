<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_request_view</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/manager/manager_request_input.js"></script>
</head>
<body>
<div id='manager_request_input'>
	<form name='frm_manager_request_input' id='frm_manager_request_input' method='post'>
		<h2>요청사항 입력</h2>
		<label>작성일</label>
		<span>${vo.nal }</span><br/>
		<label>작성자</label>
		<span>${vo.mName }</span><br/>
		
		<label>요청사항</label>
		<span>${vo.category }</span><br/>
		<label>제목</label>
		<span>${vo.title }</span><br/>
		<label>요청내용</label><br/>
		<label></label>
		<textarea name='doc' id="doc">${vo.doc }</textarea><br/>
		<br/>
		<hr>
		<br/>
		
		<label>코멘트</label>
		<textarea name='answer' placeholder="코멘트를 입력하세요">${vo.answer }</textarea><br/>
		
		<div class='btns_confirm'>
			<button type='button' class='btn_confirm' onclick=req_input.save(this.form)>${btn }</button>
			<button type='button' class='btn_reject' onclick=req_input.reject(this.form)>반려</button>
			<button type='button' class='btn_cancel' onclick=req_input.cancel(this.form)>취소</button>
		</div>
		
		<label>작성자 코드</label>
		<input type='text' name='cUserCode' size='26' value='${vo.cUserCode }' readOnly/><br/>
		<label>캠핑장 코드</label>
		<input type='text' name='itemCode' size='26' value='${vo.itemCode }' readOnly/><br/>
		<label>sno</label>
		<input type='text' name='sno' size='26' value='${vo.sno }' readOnly/><br/>
		<label>상태</label>
		<input type='text' name='state' size='26' value='${vo.state }' readOnly/><br/>
		<label>카테고리</label>
		<input type='text' name='category' size='26' value='${vo.category }' readOnly/><br/>
	</form>

</div>
</body>
</html>