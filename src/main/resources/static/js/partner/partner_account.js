partner_account_modify = {};
partner_pwd_modify = {};
partner_join = {};

/*partner_pwd_modify */
partner_pwd_modify.modiPwdR = function(frm){
	alert("수정이 완료되었습니다.");
	let pwd = frm.cpwd_new_check.value;
	let url = 'partner_pwd_modifyR';
	frm.action = url;
	frm.submit();
}

// 기존 비밀번호 확인
$("#cpwd_pre").on("blur", function(){
	let param = $(".frm_partner_pwd_modify").serialize();
	$.ajax({
		url : "cPwdValidation",
		type : 'POST',
		cache : false,
		data : param,
		success : function(resp){
			if(resp == "matched"){
			$("#cPwdValidation").text("비밀번호가 일치합니다. 하단에 새 비밀번호를 입력해주세요.");
			$("#cPwdValidation").css("color", "#f8ca37");
			$("#cpwd_new").attr("disabled",false); //계속 진행
			$("#cpwd_new_check").attr("disabled",false); //계속 진행
			$(".btn_partner_pwd_modify").attr("disabled",false);
			}else if(resp != "matched"){ 
				$("#cPwdValidation").text("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
				$("#cPwdValidation").css("color", "#dd3115");
				$("#cpwd_new").attr("disabled", true);
				$("#cpwd_new_check").attr("disabled",true); //진행X
				$(".btn_partner_pwd_modify").attr("disabled",true);
			}else{
				$("#cPwdValidation").text("");
			}
		}
	})	
})


// 새 비밀번호 확인 재입력 요청
$("#cpwd_new_check").on("keyup", function(){
	if($("#cpwd_new").val() != $("#cpwd_new_check").val()){
	   $("#cpwdCheckValidation").text("입력하신 비밀번호와 다릅니다. 비밀번호를 확인 후 다시 입력해주세요.");
	   $(".btn_partner_pwd_modify").attr("disabled", true);
	}else if($("#mpwd_new").val() == $("#cpwd_new_check").val()){
		$("#cpwdCheckValidation").text("");
		$(".btn_partner_pwd_modify").attr("disabled",false); //계속진행
	} else {
		$("#cpwdCheckValidation").text("");
		$(".btn_partner_pwd_modify").attr("disabled", true);
	}
})

 
/* partner_account_modify */
partner_account_modify.modiPwd = function(frm){
	let url = 'partner_pwd_modify';
	frm.action = url;
	frm.submit();
}

partner_account_modify.modiReq = function(frm){
	let url = 'partner_account_modiReq';
	frm.action = url;
	frm.submit();
	alert("수정 요청이 완료되었습니다.");
}

partner_account_modify.cancel = function(frm){
	let url = 'partner_main';
	frm.action = url;
	frm.submit();
}

// 유효성검사
$("#cmName").on("blur", function() {
   let cmName = $(this).val();
   if (!iscmName(cmName)) {
      $("#cmNameValidation").text("한글 2~6자리의 이름을 입력해주세요.");
      $("#cmNameValidation").css("color", "#dd3115");
   } else {
      $("#cmNameValidation").text("");
   }
})
$("#cPhone").on("blur", function(){
	let cPhone = $(this).val();
	let param = $(".frm_partner_join").serialize();
	if(!iscPhone(cPhone)){
		$("#cPhoneValidation").text("휴대폰 번호를 다시 확인해주세요.");
		$("#cPhnoeValidation").css("color", "#dd3115");
	} else {
		// 중복 테스트
		$.ajax({
			url: "cPhoneValidation",
            type: "POST",
            cache: false,
            data: param, // data에 바로 serialze한 데이터를 넣는다.
            success: function(resp){
				if(resp == 'validation'){
					$("#cPhoneValidation").text("중복된 휴대폰 번호입니다.");
					$("#cPhoneValidation").css("color", "#dd3115")
					partner_join.cPhoneDuplication = true;
				} else {
					$("#cPhoneValidation").text("사용가능한 휴대폰 번호입니다.");
					$("#cPhoneValidation").css("color", "#f8ca37")
					partner_join.cPhoneDuplication = false;
       			}
            }
		})
	}
})
$("#cEmail").on("blur", function(){
	let cEmail = $(this).val();
	let param = $(".frm_partner_join").serialize();
	if(!iscEmail(cEmail)){
		$("#cEmailValidation").text("이메일 주소를 다시 확인해주세요.")
	} else {
		// 중복 테스트
		$.ajax({
			url: "cEmailValidation",
            type: "POST",
            cache: false,
            data: param,
            success: function(resp){
				if(resp == 'validation'){ //vo에서 가져온 이메일과 동일하면 반응 X
					$("#cEmailValidation").text("중복된 이메일입니다.");
					$("#cEmailValidation").css("color", "#dd3115");
					partner_join.cEmailDuplication = true;
				} else {
					$("#cEmailValidation").text("사용가능한 이메일입니다.");
					$("#cEmailValidation").css("color", "#f8ca37");
					partner_join.cEmailDuplication = false;
       			}
            }
		})
	}
})
$("#cbusiNum").on("blur", function() {
   let cbusiNum = $(this).val();
   let param = $(".frm_partner_join").serialize();

   if (!iscbusiNum(cbusiNum)) {
      $("#cBusiNumValidation").text("-를 제외한 11-12자리의 사업자등록번호를 입력해주세요.");
      $("#cBusiNumValidation").css("color", "#f8ca37");
   } else {
      // 중복 테스트
      $.ajax({
         url: "cBusiNumValidation",
         type: "POST",
         cache: false,
         data: param,
         success: function(resp) {
            if (resp == 'validation') {
               $("#cBusiNumValidation").text("중복된 사업자 등록 번호입니다.");
               $("#cBusiNumValidation").css("color", "#dd3115");
               partner_join.cBusiNumDuplication = true;
            } else {
               $("#cBusiNumValidation").text("가입 가능한 사업자 등록번호입니다.");
               $("#cBusiNumValidation").css("color", "#f8ca37");
               partner_join.cBusiNumDuplication = false;
            }
         }
      })
   }
})
$("#cbusiEmail").on("blur", function(){
	let cbusiEmail = $(this).val();	
	if(!iscbusiEmail(cbusiEmail)){
		$("#cBusiEmailValidation").text("이메일을 확인후 작성해주세요.")
	} else{
		$("#cBusiEmailValidation").text("")
	}
})

// 정규식으로 유효성 검사
function iscmName(cmName){
	let cmNameRegExp = /^[가-힣]{2,6}$/;
	return cmNameRegExp.test(cmName);
}

function iscPhone(cPhone){
	let cphoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; 	
	return cphoneRegExp.test(cPhone);
}

function iscEmail(cEmail){
	let cEmailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;	
	return cEmailRegExp.test(cEmail);
}
function iscbusiNum(cbusiNum) {
   let cbusiNumRegExp = /^[0-9]{11,12}$/;  //0~9까지의 숫자만 11-12자리
   return cbusiNumRegExp.test(cbusiNum);
}
function iscbusiEmail(cbusiEmail){
	let cbusiEmailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;	
	return cbusiEmailRegExp.test(cbusiEmail);
}


