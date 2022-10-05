
manager_account_input = {};
manager_account_modify = {};

/* manager_account_input */
manager_account_input.mjoinR = function(frm){
	let url = 'manager_account_inputR';
	frm.action = url;
	frm.submit();
}

/* manager_account_modify */
manager_account_modify.mmodifyR = function(frm){
	let param = $("frm_manager_modify").serialize();
	$.post("manager_account_modifyR", param, function(){
		let url = 'manager_main';
		frm.action = url;
		frm.submit();
	})

}
/* manager_pwd_modify */
manager_account_modify.modiPwd = function(frm){
	let url = 'manager_pwd_modify';
	frm.action = url;
	frm.submit();
}

manager_account_modify.modiPwdR = function(frm){
	alert("수정이 완료되었습니다.");
	let param = $(".frm_manager_pwd_modify").serialize();

	$.post("manager_pwd_modifyR", param, function(){
		let url = 'manager_main';
		frm.action = url;
		frm.submit();
	})
}

// 기존 비밀번호 확인
$("#mpwd").on("blur", function(){
	let param = $(".frm_manager_pwd_modify").serialize();
	$.ajax({
		url : "mPwdValidation",
		type : 'POST',
		cache : false,
		data : param,
		success : function(resp){
			if(resp == "matched"){
			$("#mPwdValidation").text("비밀번호가 일치합니다. 하단에 새 비밀번호를 입력해주세요.");
			$("#mPwdValidation").css("color", "#f8ca37");
			$("#mpwd_new").attr("disabled",false); //계속 진행
			$("#mpwd_new_check").attr("disabled",false); //계속 진행
			}else if(resp != "matched"){ 
				$("#mPwdValidation").text("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
				$("#mPwdValidation").css("color", "#dd3115");
				$("#mpwd_new").attr("disabled", true);
				$("#mpwd_new_check").attr("disabled",true); //진행X
			}else{
				$("#mPwdValidation").text("");
			}
		}
	})	
})


// 새 비밀번호 확인 재입력 요청
$("#mpwd_new_check").on("keyup", function(){
	if($("#mpwd_new").val() != $("#mpwd_new_check").val()){
	   $("#mpwdCheckValidation").text("입력하신 비밀번호와 다릅니다. 비밀번호를 확인 후 다시 입력해주세요.");
	   $(".btn_manager_pwd_modify").attr("disabled", true);
	}else if($("#mpwd_new").val() == $("#mpwd_new_check").val()){
		$("#mpwdCheckValidation").text("");
		$(".btn_manager_pwd_modify").attr("disabled", false);
	} else {
		$("#mpwdCheckValidation").text("");
		$(".btn_manager_pwd_modify").attr("disabled", true);
	}
})