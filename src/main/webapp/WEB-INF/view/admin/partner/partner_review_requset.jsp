<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_review_requset</title>
<link rel="stylesheet" type="text/css" href="./css/partner/partner_review_request.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/partner/partner_review_request.js"></script>
</head>
<body>
<div id='partner_review_requset'>
		<form name='frm_partner_review_request' id='frm_partner_review_request' method='post'>
			<h2>요청사항 입력</h2>
			<div class="review_att">
				<div>
					<img src="./image/camping/${vo.itemCode }/review/${vo.sysFile}" onerror="this.src='./image/icon/error_img.png'">			
				</div>
				<div>
					<div class="info">
						<span class="id">${vo.id } : ${vo.mName }</span>
						<span class="site">${vo.siteCode } </span>
						<span class="date">${vo.dateStr } ~ ${vo.dateEnd } </span>
						<%-- <span class="rank"><img src="./image/icon/star.png" >${vo.score}</span> --%>
					</div>
					<div class="content">
						${vo.doc }
					</div>
				</div>
			</div>
			<div id="info">
				<div>
				<label>작성일</label>
				<input type='text' name='nal' value='${now }'/><br/>
				<label>작성자</label>
				<input type='text' name="mName" size='26' value='${iName }'/><br/>
				<label>요청사항</label>
				<input type="text" name="category" value="리뷰"><br/>
				<label>제목</label>
				<input type='text' name='title' class="title" value="리뷰본호 : ${vo.sno } 블랙요청" size='30'/><br/>
				<label>요청내용</label><br/>
				<textarea name='doc' class="doc" placeholder="요청사항 입력"></textarea><br/>
		
				<div class='btns_request'>
					<button type='button' class='btn' onclick="review_request.save(this.form)">저장</button>
					<button type='button' class='btn' onclick="review_request.cancel(this.form)">취소</button>
				</div>
				</div>
			</div>
			<input type="hidden" name="seq" value="0"><br/>
			<input type="hidden" name="cUserCode" value="${sessionScope.cUserCode }"><br/>
			<input type="hidden" name="itemCode" value="${vo.itemCode }"><br/>
			<input type="hidden" name="state" value="블랙요청"><br/>
			<input type="hidden" name="reviewSno" value="${vo.sno }"><br/>
			<input type="hidden" name="orderCode" value="${vo.orderCode }"><br/>
			
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