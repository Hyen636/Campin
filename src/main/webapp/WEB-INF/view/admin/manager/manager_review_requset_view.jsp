<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manager_review_requset_view</title>
<link rel="stylesheet" type="text/css" href="./css/manager/manager_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/manager/manager_request_input.js"></script>
</head>
<body>
<div id='manager_review_requset'>
		<form name='frm_manager_review_request' id='frm_manager_review_request' method='post'>
			<h2>요청사항 입력</h2>
			<div class="review_att">
				<div>
					<img src="./image/camping/${Revo.itemCode }/review/${Revo.sysFile}" onerror="this.src='./image/icon/error_img.png'">			
				</div>
				<div>
					<div class="info">
						<span>아이디 : ${Revo.id } </span>
						<span>이름 : ${Revo.mName }</span> <br/>
						<span>사이트 코드 : ${Revo.siteCode }</span>
						<span class="date">입실 날짜 : ${Revo.dateStr } ~ ${Revo.dateEnd } </span>
					</div>
					<span>리뷰내용</span> <br/>
					<textarea>${Revo.doc }</textarea>
				</div>
			</div>
			<label>작성일</label>
			<input type='date' name='nal' value='${vo.nal }'/><br/>
			<label>작성자</label>
			<input type='text' name="mName" size='26' value='${vo.mName }'/><br/>
			<label>요청사항</label>
			<input type="text" name="category" value="리뷰"><br/>
			<label>제목</label>
			<input type='text' name='title' value="리뷰본호 : ${vo.reviewSno } 블랙요청" size='30'/><br/>
			<label>요청내용</label><br/>
			<label></label>
			<textarea name='doc' placeholder="요청사항 입력">${vo.doc }</textarea><br/>
			
			<label>코멘트</label><br/>
			<textarea name='answer' placeholder="반려사유"></textarea><br/>
			
			<div class='btns_confirm'>
				<button type='button' class='btn_confirm' onclick=req_input.save(this.form)>승인</button>
				<button type='button' class='btn_reject' onclick=req_input.reject(this.form)>반려</button>
				<button type='button' class='btn_cancel' onclick=req_input.cancel(this.form)>취소</button>
			</div>
			<div class="hidden">
				<input type="hidden" name="seq" value="0"><br/>
				<input type="hidden" name="itemCode" value="${vo.itemCode }"><br/>
				<input type="hidden" name="state" value="${vo.state }"><br/>
				<input type="hidden" name="sno" value="${vo.sno }"><br/>
				<input type="hidden" name="reviewSno" value="${vo.reviewSno }"><br/>
				<input type="hidden" name="cUserCode" value="${vo.cUserCode }"><br/>
			</div>
	</form>

</div>
</body>
</html>