<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_remove_account</title>
<link rel='stylesheet' type='text/css' href='./css/user/user_membership.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src='./js/user/user_membership.js'></script>
</head>
<body>
<div id='user_remove_account'>
		<form name='frm_user_remove_account' class='frm_user_remove_account' id='frm_user_remove_account' method='post'>
		<input type='hidden' name='id' value='${sessionScope.id}'>
			<h1>회원 탈퇴</h1>
				<label class='notice'>※ 회원탈퇴를 하기 전에 안내사항을 꼭 확인해 주세요.</label><br/>
				
				<h4 class='h4_first'>아이디 재사용 및 복구 불가</h4><br/>
				<span>사용하고 계신 아이디를 탈퇴하시면 본인과 타인 모두 재사용 및 복구가 불가합니다.</span><br/>
				
				<h4>게시물 및 답변 유지</h4><br/>
				<span>게시물은 유지되며 삭제를 원하는 게시글이 있다면 반드시 탈퇴 전 비공개 처리하거나 삭제 하시기 바랍니다.</span><br/>
				
				<h4>예약 내용 보관</h4><br/>
				<span>고객님의 예약 및 결제 내역은 고객사후관리를 위해 「국세기본법」에 의거하여 고객정보 보호정책에 따라 관리합니다.</span><br/>
				<span> 결제 및 환불 내역</span><br/>
				<span> 예약신청/완료/취소 내역</span>
				
				
				<h4 class='h4_password'>비밀번호 입력</h4>
				<input type='password' name='pwd' size='15' placeholder="비밀번호를 입력해주세요."/><br/>

				<div class='user_withdrawal_terms'>
					<h4>개인정보 보관 처리 동의 확인</h4>
					<textarea class='terms'>
캠핀은 아래와 같이 개인정보를 보관 및 이용하며, 이용 기간 후 수집된 정보를 안전하게 삭제하는데 최선을 다합니다.

1. 개인정보의 수집·이용목적: 회원 탈퇴 후 재가입 방지목적
2. 수집하려는 개인정보의 항목: 암호화된 동일인 식별정보(CI)
3. 개인정보의 보유 및 이용 기한: 회원 탈퇴 처리 후 3개월간 보유

이용자는 상기 개인정보 수집 및 이용 동의를 거부할 권리가 있습니다. 
다만, 동의 거부시 회원 탈퇴 진행이 불가합니다. 상세 내용은 ‘개인정보처리방침’을 참고 바랍니다.
					</textarea><br/>
					<label class='yes'><input type="checkbox" name="yes" id="yes" value="yes"/>*위 내용을 모두 확인하였으며, 이에 동의합니다.</label><br/>
				</div>
				<button type='button' name='btnUserRemoveAccount' class='btn_user_remove_account' onclick="user_membership.user_remove_accountR()">회원탈퇴</button>
		</form>
	</div>
</body>
</html>