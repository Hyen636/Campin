<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="user_qna_list">
		<form name='frm_user_qna_list' id='frm_user_qna_list' method='post'>
			<input type='text' name='userCode' value='${param.userCode}'
				hidden='hidden'>

			<h1>문의 내역</h1><br>
			<c:if test='${listQ.size() == 0 }'>
				<img src="image/icon/null.png" width='20%' class='null1'>
				<div class='null2'>문의 내역이 존재하지 않습니다.</div>
			</c:if>
			<c:if test='${listQ.size() != 0 }'>
				<div class="list">
					<div class="title">
						<span class="state">답변상태</span> <span class="subject">제목</span> <span
							class="iName">캠핑장</span> <span class="nal">날짜</span>
					</div>
					<div class="items">
						<c:forEach var='vo' items="${listQ}" varStatus="status">
							<div class="item">
								<div class="item_qna item_${status.index }"
									onclick="user_mypage.showDetail(${status.index})">
									<span class="state">${vo.state}</span> <span class="subject">${vo.title}</span>
									<span class="iName">${vo.iName}</span> <span class="nal">${vo.nal}</span>
									<input type='hidden' name='sno' value='${vo.sno}' />
								</div>
								<div class='item_qna_detail detail_${status.index }'>
									<h3>Q</h3>
									<br />
									<textarea class='user_qna uq_${vo.sno}' disabled>${vo.doc}</textarea>
									<input type='hidden' class='doc_hidden_${vo.sno}'
										value='${vo.doc}'>
									<c:if test='${vo.answer == null }'>
										<button type='button' class='btn_modify btm_${vo.sno}'
											onclick='user_mypage.modifyQna(${vo.sno})'>수정</button>
										<button type='button' class='btn_delete btd_${vo.sno}'
											onclick='user_mypage.deleteQna(${vo.sno})'>삭제</button>
										<button type='button' class='btn_save bts_${vo.sno}'
											onclick='user_mypage.saveQna(${vo.sno})'>저장</button>
										<button type='button' class='btn_cancel btc_${vo.sno}'
											onclick='user_mypage.cancelQna(${vo.sno})'>취소</button>
									</c:if>
									<c:if test='${vo.answer != null }'>
										<h3>A</h3>
										<br />
										<textarea class='qna_re' disabled>${vo.answer}</textarea>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>

				</div>
			</c:if>
			<!-- <div class="paging_btns">
		<button type="button" class="btnFirst">처음</button>
		<button type="button" class="btnPrev">이전</button>
		<button type="button" class="btnPage">1</button>
		<button type="button" class="btnPage">2</button>
		<button type="button" class="btnPage">3</button>
		<button type="button" class="btnNext">다음</button>
		<button type="button" class="btnLast">끝</button>		
	</div> -->
		</form>
	</div>
</body>
</html>