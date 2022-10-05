/*partner_find_id */
partner_find = {};

//아이디찾기
//아이디 찾기 버튼에 따라 항목 보여주기
$(".btn_pPhone").on("click", function(){
	$(".findPidPhone").css("display", "block");
	$(".findPidEmail").css("display", "none");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_pEmail").css("border-bottom", "none");
	$(".find_pId_pName").val("")
	$(".find_pId_email").val("")
	$("#pNameValidation").text("")
	$("#pEmailValidation").text("")
	$("#btnFindPid").css("display", "inline-block");
	$("#btnFindEid").css("display", "none");
})
$(".btn_pEmail").on("click", function(){
	$(".findPidPhone").css("display", "none");
	$(".findPidEmail").css("display", "block");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_pPhone").css("border-bottom", "none");
	$(".find_pId_value").val("")
	$(".find_pId_email").val("")
	$("#pNameValidation").text("")
	$("#pPhoneValidation").text("")
	$("#btnFindEid").css("display", "inline-block");
	$("#btnFindPid").css("display", "none");
})

partner_find.findPhone = function(frm){
	frm.action = 'findpCidR';
	frm.submit();
}


partner_find.findEmail = function(frm){
	frm.action = 'findeCidR';
	frm.submit();
}