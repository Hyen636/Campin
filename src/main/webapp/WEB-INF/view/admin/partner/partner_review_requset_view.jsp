<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_review_requset_view</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_review_request_view.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/partner/partner_request_view.js"></script>
</head>
<body>
<div id='partner_review_requset'>
		<form name='frm_partner_review_request' id='frm_partner_review_request' method='post'>
			<h2>요청사항 입력</h2>
			<div class="review_att">
				<div>
					<img src="./image/camping/${Revo.itemCode }/review/${Revo.sysFile}" onerror="this.src='./image/icon/error_img.png'">			
				</div>
				<div>
					<div class="info">
						<span class="id">${Revo.id } : ${Revo.mName }</span>
						<span class="site">${Revo.siteCode } </span>
						<span class="date">${Revo.dateStr } ~ ${Revo.dateEnd } </span>
						<%-- <span class="rank"><img src="./image/icon/star.png" >${vo.score}</span> --%>
					</div>
					<div class="content">
						${Revo.doc }
					</div>
				</div>
			</div>
			<div id="info">
				<div>
					<label>작성일</label>
					<input type='text' name='nal' value='${vo.nal }' readOnly/><br/>
					<label>작성자</label>
					<input type='text' name="mName" size='26' value='${vo.mName }'readOnly/><br/>
					<label>요청사항</label>
					<input type="text" name="category" value="리뷰"readOnly><br/>
					<label>상태값</label>
					<input type="text" value="${vo.state }"readOnly><br/>
					<label class="stitle">제목</label>
					<input type='text' name='title' id='title'  value="리뷰본호 : ${vo.reviewSno } 블랙요청" size='30'/><br/>
					<label class="title">요청내용</label><br/>
					<textarea name='doc' class="doc"  placeholder="요청사항 입력">${vo.doc }</textarea><br/>
					</div>
			</div>
			
			
			
			<div id="comment_section">
				<div>
					<label class="title">코멘트</label><br>
					<textarea name='answer' class="answer" placeholder="반려사유"readOnly></textarea><br/>
				</div>
			</div>
			<div class='btns_confirm'>
				<button type='button' class='btn' onclick="req_view.modify(this.form)">수정</button>
				<button type='button' class='btn' onclick="req_view.delete(this.form)">삭제</button>
				<button type='button' class='btn' onclick="req_view.cancel(this.form)">취소</button>
			</div>
			<input type="hidden" name="seq" value="0"><br/>
			<input type="hidden" name="cUserCode" value="${sessionScope.cUserCode }"><br/>
			<input type="hidden" name="itemCode" value="${vo.itemCode }"><br/>
			<input type="hidden" name="state" value="${vo.state }"><br/>
			<input type="hidden" name="sno" value="${vo.sno }"><br/>
			<input type="hidden" name="reviewSno" value="${vo.reviewSno }"><br/>
	</form>

</div>
</body>
</html>