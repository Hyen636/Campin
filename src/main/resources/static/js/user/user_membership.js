/**
 *
 * USER MEMBERSHIP
 *
 */
 
let user_membership = [];

/* 로그인 */

//로그인폼 비번 입력하고 엔터
function enterkey(btn) {
	if (window.event.keyCode == 13) {
    	btn.click();
	}
}

// 로그인 폼에서 로그인 버튼 클릭시
user_membership.user_loginR = function(){
	let frm = document.getElementById('frm_user_login');
	frm.action = 'user_loginR';
	frm.submit();
	//alert("로그인 되었습니다. :)");
}

/* 카카오 로그인 */

// 소셜로그인 버튼 클릭시
user_membership.user_login_kakao = function(){
	let frm = document.getElementById('frm_user_login');
	frm.action = 'user_login_kakao';
	frm.submit();
}

// 카카오 회원가입시 추가정보 입력
user_membership.user_join_kakaoR = function(){
	let frm = document.getElementById("frm_user_join_kakao");
	frm.action = 'user_join_kakaoR';
	frm.submit();
}

// 추가정보 입력 취소
user_membership.user_join_kakaoRR = function(){
	let frm = document.getElementById("frm_user_join_kakao");
	frm.action = 'user_join_kakaoRR';
	frm.submit();
}

/* 네이버 로그인 */
user_membership.init = function (){
	location.href = 'callback'
}


/* 회원가입 */

// 회원가입 폼에서 회원가입 버튼 클릭시
user_membership.user_joinR = function(){
	
	let frm = document.getElementById('frm_user_join');
	let id = frm.id.value;
	let pwd = frm.pwd.value;
	let mName = frm.mName.value;
	let birth = frm.birth.value;
	let phone = frm.phone.value;
	let email = frm.email.value;
	let check_all = document.getElementById("check_all");
	
	if(!isId(id)) {
		alert("아이디를 확인 후 다시 입력해주세요.");
		return
	} else if(!isPwd(pwd)) {
		alert("비밀번호를 확인 후 다시 입력해주세요.");
		return
	} else if(!isMname(mName)) {
		alert("이름을 확인 후 다시 입력해주세요.");
		return
	} else if(!isBirth(birth)) {
		alert("생년월일을 확인 후 다시 입력해주세요.");
		return
	} else if(!isPhone(phone)) {
		alert("휴대폰 번호를 확인 후 다시 입력해주세요.");
		return
	} else if(!isEmail(email)) {
		alert("이메일 주소를 확인 후 다시 입력해주세요.");
		return
	} else if(!check_all.checked){
    	alert("이용약관에 동의를 하셔야만 가입이 가능합니다.");
    	$('.user_join_terms').focus();
    	return
	} else {
		alert("축하합니다! CAMPIN 회원가입이 완료되었습니다. :)");
		frm.action = 'user_joinR';
		frm.submit();
	}
}

// 재입력 요청
$("#id").on("blur", function(){
	let id = $(this).val();
	let param = $("#frm_user_join").serialize();

	if(!isId(id)){
		$("#idValidation").text("영어 소문자, 숫자를 사용하여 6~12자리로 입력해주세요.")
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else {
		// 중복 테스트
		$.ajax({
			url: "user_idValidation",
            type: "POST",
            cache: false,
            data: param,
            success: function(resp){
				if(resp=="validation"){
					$("#idValidation").text("이미 사용 중인 아이디입니다. 확인 후 다시 입력해주세요.");
					$("#idValidation").css("color", "#dd3115");
					document.getElementById('btn_join').disabled=true;
					$('#btn_join').css('cursor','not-allowed');
				} else {
					$("#idValidation").text("사용 가능한 아이디입니다.");
					$("#idValidation").css("color", "#00B700");
					document.getElementById('btn_join').disabled=false;
					$('#btn_join').css('cursor','pointer');
       			}
            }
		})
	}
})

$("#pwd").on("blur", function(){
	let pwd = $(this).val();
	
	if(!isPwd(pwd)){
		$("#pwdValidation").text("대소문자 영어, 숫자를 사용하여 8~16자리로 입력해주세요.")
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else{
		$("#pwdValidation").text("")
		document.getElementById('btn_join').disabled=false;
		$('#btn_join').css('cursor','pointer');
	}
})

$("#mName").on("blur", function(){
	let mName = $(this).val();	

	if(!isMname(mName)){
		$("#mNameValidation").text("한글 2~6자리의 이름을 입력해주세요.")
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else{
		$("#mNameValidation").text("")
		document.getElementById('btn_join').disabled=false;
		$('#btn_join').css('cursor','pointer');
	}
})
$("#email").on("blur", function(){
	let email = $(this).val();
	let param = $("#frm_user_join").serialize();
	
	if(!isEmail(email)){
		$("#emailValidation").text("이메일을 정확하게 입력해주세요.")
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else{
		// 중복 테스트
		$.ajax({
			url: "user_emailValidation",
            type: "POST",
            cache: false,
            data: param,
            success: function(resp){
				if(resp=="validation"){
					$("#emailValidation").text("이미 사용 중인 이메일 주소입니다. 확인 후 다시 입력해주세요.");
					$("#emailValidation").css("color", "#dd3115");
					document.getElementById('btn_join').disabled=true;
					$('#btn_join').css('cursor','not-allowed');
				} else {
					$("#emailValidation").text("사용 가능한 이메일 주소입니다.");
					$("#emailValidation").css("color", "#00B700")
					document.getElementById('btn_join').disabled=false;
					$('#btn_join').css('cursor','pointer');
       			}
            }
		})
	}
})

$("#phone").on("blur", function(){
	let phone = $(this).val();
	let param = $("#frm_user_join").serialize();

	if(!isPhone(phone)){
		$("#phoneValidation").text("휴대폰 번호를 다시 확인해주세요.")
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else {
		// 중복 테스트
		$.ajax({
			url: "user_phoneValidation",
            type: "POST",
            cache: false,
            data: param,
            success: function(resp){
				if(resp == "validation"){
					$("#phoneValidation").text("이미 사용 중인 휴대폰 번호입니다. 확인 후 다시 입력해주세요.");
					$("#phoneValidation").css("color", "#dd3115")
					document.getElementById('btn_join').disabled=true;
					$('#btn_join').css('cursor','not-allowed');
				} else {
					$("#phoneValidation").text("사용 가능한 휴대폰 번호입니다.");
					$("#phoneValidation").css("color", "#00B700")
					document.getElementById('btn_join').disabled=false;
					$('#btn_join').css('cursor','pointer');
       			}
            }
		})
	}
})

$("#birth").on("blur", function(){
	let birth = $(this).val();
	
	if(!isBirth(birth)){
		$("#birthValidation").text("생년월일을 다시 확인해주세요.");
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else {
		$("#birthValidation").text("");
		document.getElementById('btn_join').disabled=false;
		$('#btn_join').css('cursor','pointer');
	}
})

$("#pwdCheck").on("keyup", function(){
	if($("#pwd").val() != $("#pwdCheck").val()){
		$("#pwdCheckValidation").text("입력하신 비밀번호와 다릅니다. 비밀번호를 확인 후 다시 입력해주세요.");
		document.getElementById('btn_join').disabled=true;
		$('#btn_join').css('cursor','not-allowed');
	} else{
		$("#pwdCheckValidation").text("");
		document.getElementById('btn_join').disabled=false;
		$('#btn_join').css('cursor','pointer');
	}
})

// 정규식으로 유효성 검사
function isId(id){
	let idRegExp = /^[a-z0-9]{6,12}$/;	// a~z,0~9를 포함한 6~12자리
	return idRegExp.test(id);
}

function isPwd(pwd){
	let pwdRegExp = /^(?=.*\d)(?=.*[A-Za-z]).{8,16}$/; 	// 대소문자 영어,숫자 1자리 이상 8~16자리
	return pwdRegExp.test(pwd);
} 

function isMname(mName){
	let mNameRegExp = /^[가-힣]{2,6}$/;	// 이름 2~6자리
	return mNameRegExp.test(mName);
}

function isEmail(email){
	let emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;	
	return emailRegExp.test(email)
}

function isPhone(phone){
	let phoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; 	
	return phoneRegExp.test(phone)
}

// 생년월일 검사
function isBirth(birth) {
	var year = Number(birth.substr(0,4)); // 입력한 값의 0~4자리까지 (연)
	var month = Number(birth.substr(4,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
	var day = Number(birth.substr(6,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
	var today = new Date(); // 날짜 변수 선언
	var yearNow = today.getFullYear(); // 올해 연도 가져옴

	if (birth.length <=8) {
		if (1900 > year || year > yearNow){ // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
			return false;
		} else if (month < 1 || month > 12) {
			return false;
		} else if (day < 1 || day > 31) {
			return false;
		} else if ((month==4 || month==6 || month==9 || month==11) && day==31) {
			return false;
		} else if (month == 2) {
			var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); //윤년
			if (day>29 || (day==29 && !isleap)) {
				return false;
			} else {
				return true;
			} //end of if (day>29 || (day==29 && !isleap))
		} else {
			return true;
		}
	}
	else {
		return false; // 입력된 생년월일이 8자 초과
	}
}

// 체크박스 전체 선택
$(".user_join_terms").on("click", "#check_all", function () {
    $(this).parents(".user_join_terms").find("input").prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".check").on("change", function(){
	let check1 = $("#check_1").prop("checked");
	let check2 = $("#check_2").prop("checked");
	
	if(check1 && check2){
		$("#check_all").prop("checked", true);
	} else {
		$("#check_all").prop("checked", false);
	}
})



// 로그인 화면으로 이동
user_membership.toLoginPage = function(){
	location.href = 'user_login'
}
// 마이페이지 메인 화면으로 이동
user_membership.toMypage = function(){
	location.href = 'user_mypage'
}
// 아이디 찾기로 이동
user_membership.user_find_id = function(){
	let frm = document.getElementById('frm_user_login');
	frm.action = 'user_find_id';
	frm.submit();
}
// 비밀번호 찾기로 이동
user_membership.user_find_pwd = function(){
	let frm = document.getElementById('frm_user_login');
	frm.action = 'user_find_pwd';
	frm.submit();
}
// 휴대폰 번호로 아이디 찾기
user_membership.user_find_id_phone = function(){
	let frm = document.getElementById('frm_user_find_id');
	frm.action = "user_find_id_phone";
	frm.submit();
}
// 이메일 주소로 아이디 찾기
user_membership.user_find_id_email = function(){
	let frm = document.getElementById('frm_user_find_id');
	frm.action = "user_find_id_email";
	frm.submit();
}
// 비밀번호 찾기
user_membership.user_find_temp_pwd = function(){
	let frm = document.getElementById('frm_user_find_pwd');
	frm.action = 'user_find_temp_pwd';
	frm.submit();
}


/* 아이디 찾기 */

// 아이디찾기 버튼에 따라 항목 보여주기
$(".btn_phone").on("click", function(){
	$(".findIdPhone").css("display", "block");
	$(".findIdEmail").css("display", "none");
	$(this).css("border-bottom", "2px solid #4bc1b1");
	$(".btn_email").css("border-bottom", "none");
	$("#mName").val("")
	$("#email").val("")
	$("#mNameValidation").text("")
	$("#emailValidation").text("")
	$("#btnFindId").attr("onclick", "user_membership.user_find_id_phone()");
})
$(".btn_email").on("click", function(){
	$(".findIdPhone").css("display", "none");
	$(".findIdEmail").css("display", "block");
	$(this).css("border-bottom", "2px solid #4bc1b1");
	$(".btn_phone").css("border-bottom", "none");
	$("#mName").val("")
	$("#phone").val("")
	$("#mNameValidation").text("")
	$("#phoneValidation").text("")
	$("#btnFindId").attr("onclick", "user_membership.user_find_id_email()");
})

$(".find_id_mName").on("blur", function(){
	let mName = $(this).val();	

	if(!isMname(mName)){
		$("#mNameValidation").text("한글 2~6자리의 이름을 입력해주세요.");
		document.getElementById('btnFindPwd').disabled=true;
		$('#btnFindPwd').css('cursor','not-allowed');
	} else{
		$("#mNameValidation").text("");
		document.getElementById('btnFindPwd').disabled=false;
		$('#btnFindPwd').css('cursor','pointer');
	}
})

$(".find_id_email").on("blur", function(){
	let email = $(this).val();
	
	if(!isEmail(email)){
		$("#emailValidation").text("이메일을 정확하게 입력해주세요.");
		document.getElementById('btnFindPwd').disabled=true;
		$('#btnFindPwd').css('cursor','not-allowed');
	} else{
		$("#emailValidation").text("");
		document.getElementById('btnFindPwd').disabled=false;
		$('#btnFindPwd').css('cursor','pointer');
	}
})

$(".find_id_phone").on("blur", function(){
	let phone = $(this).val();
	
	if(!isPhone(phone)){
		$("#phoneValidation").text("휴대폰 번호를 다시 확인 후 입력해주세요.");
		document.getElementById('btnFindPwd').disabled=true;
		$('#btnFindPwd').css('cursor','not-allowed');
	} else{
		$("#phoneValidation").text("");
		document.getElementById('btnFindPwd').disabled=false;
		$('#btnFindPwd').css('cursor','pointer');
	}
})

/* 비밀번호 찾기 */

$(".find_pwd_id").on("blur", function(){
	let id = $(this).val();
	let param = $("#frm_user_find_pwd").serialize();

	if(!isId(id)){
		$("#idValidation").text("영어 소문자, 숫자를 사용하여 6~12자리로 입력해주세요.");
		document.getElementById('btnFindPwd').disabled=true;
		$('#btnFindPwd').css('cursor','not-allowed');
	} else {
		// 존재하는 아이디인지 확인
		$.ajax({
			url: "user_idValidation",
            type: "POST",
            cache: false,
            data: param,
            success: function(resp){
				if(resp == "validation"){
					$("#idValidation").text("");
					document.getElementById('btnFindPwd').disabled=false;
					$('#btnFindPwd').css('cursor','pointer');
				} else {
					$("#idValidation").text("※ 입력하신 아이디는 가입되어 있지 않은 아이디입니다.");
					$("#idValidation").css("color", "#dd3115");
					document.getElementById('btnFindPwd').disabled=true;
					$('#btnFindPwd').css('cursor','not-allowed');
       			}
            }
		})
	}
})

$(".find_pwd_email").on("blur", function(){
	let email = $(this).val();
	
	if(!isEmail(email)){
		$("#emailValidation").text("이메일 주소를 정확하게 입력해주세요.");
		document.getElementById('btnFindPwd').disabled=true;
		$('#btnFindPwd').css('cursor','not-allowed');
	} else{
		$("#emailValidation").text("");
		document.getElementById('btnFindPwd').disabled=false;
		$('#btnFindPwd').css('cursor','pointer');
	}
})

/* 프로필 수정 */

user_membership.user_modifyR = function(){
	let frm = document.getElementById('frm_user_modify');
	
	let birth = frm.birth.value;
	let phone = frm.phone.value;
	let email = frm.email.value;

	if(!isModifiedEmail(email)) {
		alert("이메일 주소 확인 후 다시 입력해주세요.");
		return;
	} else if(!isModifiedPhone(phone)) {
		alert("휴대폰 번호 확인 후 다시 입력해주세요.");
		return;
	} else if(!isBirth(birth)) {
		alert("생년월일 확인 후 다시 입력해주세요.");
		return;
//	} else if(mypage.phoneDuplication){	
//		alert("이미 사용 중인 휴대폰 번호입니다. 확인 후 다시 입력해주세요.");
//	} else if(mypage.emailDuplication){
//		alert("이미 사용 중인 이메일 주소입니다. 확인 후 다시 입력해주세요.");
	} else {
		alert("회원정보가 수정되었습니다.");
		frm.action = 'user_modifyR';
		frm.submit();
	}
}

// 재입력 요청
$("#modifiedEmail").on("blur", function(){
	let email = $(this).val();
	let param = $("#frm_user_modify").serialize();
	let tempEmail = $("#tempEmail").val();
	
	if(!isModifiedEmail(email)){
		$("#modifiedEmailValidation").text("이메일 주소를 다시 확인해주세요.")
		document.getElementById('btn_user_modify').disabled=true;
		$('#btn_user_modify').css('cursor','not-allowed');
	} else {
		$.ajax({
	        url: "user_emailValidation2",
	        type: "POST",
	        cache: false,
	        data: param,
	        success: function(resp){
				console.log(resp)
		        if(resp == tempEmail){
					$("#modifiedEmailValidation").text("");
					document.getElementById('btn_user_modify').disabled=false;
					$('#btn_user_modify').css('cursor','pointer');
				}else if(resp == 'null'){
		        	$("#modifiedEmailValidation").text("사용 가능한 이메일 주소입니다.");
		          	$("#modifiedEmailValidation").css("color", "#00B700");
		          	document.getElementById('btn_user_modify').disabled=false;
					$('#btn_user_modify').css('cursor','pointer');
		        } else {
		          	$("#modifiedEmailValidation").text("중복된 이메일 주소입니다.");
		        	$("#modifiedEmailValidation").css("color", "#dd3115");
		        	document.getElementById('btn_user_modify').disabled=true;
					$('#btn_user_modify').css('cursor','not-allowed');
	            }
   	 		}
      })
	}
})

$("#modifiedPhone").on("blur", function(){
	let phone = $(this).val();
	let param = $("#frm_user_modify").serialize();
	let tempPhone = $("#tempPhone").val();
	
	if(!isModifiedPhone(phone)){
		$("#modifiedPhoneValidation").text("휴대폰 번호를 다시 확인해주세요.")
		$("#modifiedPhoneValidation").css("color", "#dd3115");
		document.getElementById('btn_user_modify').disabled=true;
		$('#btn_user_modify').css('cursor','not-allowed');
	} else {
		$.ajax({
	        url: "user_phoneValidation2",
	        type: "POST",
	        cache: false,
	        data: param,
	        success: function(resp){
		        if(resp == tempPhone){
					$("#modifiedPhoneValidation").text("");
					document.getElementById('btn_user_modify').disabled=false;
					$('#btn_user_modify').css('cursor','pointer');
				}else if(resp == 'null'){
		        	$("#modifiedPhoneValidation").text("사용 가능한 휴대폰 번호입니다.");
		       		$("#modifiedPhoneValidation").css("color", "#00B700")
		       		document.getElementById('btn_user_modify').disabled=false;
					$('#btn_user_modify').css('cursor','pointer');
		        } else {
		         	$("#modifiedPhoneValidation").text("중복된 휴대폰 번호입니다.");
		        	$("#modifiedPhoneValidation").css("color", "#dd3115");
		        	document.getElementById('btn_user_modify').disabled=true;
					$('#btn_user_modify').css('cursor','not-allowed');
	            }
   	 		}
      })
	}
})

// 정규식으로 유효성 검사
function isModifiedEmail(email){
	let emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;	
	return emailRegExp.test(email)
}
function isModifiedPhone(phone){
	let phoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; 	
	return phoneRegExp.test(phone)
}

/* 비밀번호 수정 */

user_membership.user_pwd_modifyR = function(){
	let frm = document.getElementById('frm_user_pwd_modify');
	let pwd = frm.pwd.value;
	if(!isPwd(pwd)) {
		alert("비밀번호 확인 후 다시 입력해주세요.");
		return;
	} else if(isPwd(pwd)){
		alert("비밀번호가 수정되었습니다.");
		frm.action = 'user_pwd_modifyR';
		frm.submit();
	}
}

// 기존 비밀번호 확인
$("#oldPwd").on("blur", function(){
	let param = $("#frm_user_pwd_modify").serialize();
	$.ajax({
		url: "user_pwdValidation",
		type: "POST",
		cache: false,
		data: param,
		success: function(resp){
			console.log(resp)
			if(resp == "matched"){
				$("#checkOldPwd").text("비밀번호가 일치합니다. 하단에 새 비밀번호를 입력해주세요.");
				$("#checkOldPwd").css("color", "#00B700");
				$("#checkOldPwd").css("text-align", "left");
				$("#oldPwd").css("border", "1px solid #00B700");
				$("#oldPwd").css("box-shadow", "0 0 2px #00B700");
				$("#newPwd").attr("readonly",false);
				$("#newPwdCheck").attr("readonly",false);
				$("#newPwd").css("background-color", "#fff");
				$("#newPwdCheck").css("background-color", "#fff");
				$("#newPwd").css("color", "#000");
				$("#newPwdCheck").css("color", "#000");
			} else if(resp != "matched"){
				$("#checkOldPwd").text("비밀번호가 일치하지 않습니다. 비밀번호를 변경하실 수 없습니다.");
				$("#checkOldPwd").css("color", "#dd3115");
				$("#checkOldPwd").css("text-align", "left");
				$("#oldPwd").css("border", "1px solid #dd3115");
				$("#oldPwd").css("box-shadow", "0 0 2px #dd3115");
				$("#newPwd").attr("readonly",true);
				$("#newPwdCheck").attr("readonly",true);
				$("#newPwd").css("background-color", "#eee");
				$("#newPwd").css("color", "#fff");
				$("#newPwdCheck").css("background-color", "#eee");
				$("#newPwdCheck").css("color", "#fff");
			} else {
				$("#checkOldPwd").text("");
			}
		}
	})
})

// 정규식으로 유효성 검사
function isPwd(pwd){
	let pwdRegExp = /^[A-Za-z0-9]{8,16}$/; 	// 대소문자 영어,숫자 8~16자리
	return pwdRegExp.test(pwd);
}

// 새 비밀번호 재입력 요청
$("#newPwd").on("blur", function(){
	let pwd = $(this).val();
	
	if(!isPwd(pwd)){
		$("#pwdValidation").text("대소문자 영어, 숫자를 사용하여 8~16자리로 입력해주세요.")
		$("#pwdValidation").css("text-align", "left");
	} else{
		$("#pwdValidation").text("")
	}
})

// 새 비밀번호 확인 재입력 요청
$("#newPwdCheck").on("keyup", function(){
	if($("#newPwd").val() != $("#newPwdCheck").val()){
		$("#pwdCheckValidation").text("입력하신 비밀번호와 다릅니다. 비밀번호를 확인 후 다시 입력해주세요.");
		$("#pwdCheckValidation").css("text-align", "left");
		document.getElementById('btn_user_pwd_modify').disabled=true;
		$('#btn_user_pwd_modify').css('cursor','not-allowed');
	}else if($("#newPwd").val() == $("#pwdCheck").val()){
		$("#pwdCheckValidation").text("");
		document.getElementById('btn_user_pwd_modify').disabled=false;
		$('#btn_user_pwd_modify').css('cursor','pointer');
	}else {
		$("#pwdCheckValidation").text("");
		document.getElementById('btn_user_pwd_modify').disabled=false;
		$('#btn_user_pwd_modify').css('cursor','pointer');
	}
})

/* 회원탈퇴 */

user_membership.user_remove_accountR = function(){
	let frm = document.getElementById('frm_user_remove_account');
	let check = document.getElementById("yes");

	if(!check.checked){
    	alert("약관에 동의를 하셔야만 회원탈퇴가 가능합니다.");
    	$('.user_withdrawal_terms').focus();
    	return;
    }else{
		frm.action = 'user_remove_accountR';
		frm.submit();
	}
}

/* 카카오 회원탈퇴 */

user_membership.user_remove_account_kakaoR = function(){
	let frm = document.getElementById('frm_user_remove_account_kakao');
	let check = document.getElementById("yes");

	if(!check.checked){
    	alert("약관에 동의를 하셔야만 회원탈퇴가 가능합니다.");
    	$('.user_withdrawal_terms').focus();
    	return;
    }else{
		frm.action = 'user_remove_account_kakaoR';
		frm.submit();
	}
}