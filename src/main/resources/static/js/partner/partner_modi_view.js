partner_account_modify_view = {};

/* partner_account_modify_view */
let state = frm_partner_modify.state.value;
//console.log("state.." , state);

(function () {
    if (state == "수정요청") {
        $(".btn_caccount_modify").css("display", "inline-block");
    } else if (state == "요청반려"){
        $(".btn_caccount_modify").css("display", "none");
    } else if(state == "수정완료"){
		$(".btn_caccount_modify").css("display", "none");
	}
})();

partner_account_modify_view.list = function(frm){
	let url = 'partner_request_list';
	frm.action = url;
	frm.submit();
}

partner_account_modify_view.modify = function(frm){
	
	window.scrollTo(0,0);
	
	$(".modi2").css("display", "inline-block");
	$(".modi1").css("display", "none");
	
	$(".btn_modisave").css("display", "inline-block");
	$(".btn_modidelete").css("display", "inline-block");
	$(".btn_mcancel").css("display", "inline-block");
	
	$(".btn_clist").css("display", "none");
	$(".btn_caccount_modify").css("display", "none");
	
	$("#cId").attr("disabled",false);
	$("#cId").attr("readonly",true);
	$("#cPwd").attr("disabled",false);
	$("#cmName").attr("disabled",false);
	$("#cPhone").attr("disabled",false);
	$("#cEmail").attr("disabled",false);
	$("#cbirth").attr("disabled",false);
	$("#m").attr("disabled",false);
	$("#f").attr("disabled",false);
	$("#cbusiBankName").attr("disabled",false);
	$("#cbusiBank").attr("disabled",false);
	$("#cbusiBankNum").attr("disabled",false);
	$("#cbusiName").attr("disabled",false);
	$("#crepName").attr("disabled",false);
	$("#cbusiNum").attr("disabled",false);
	$("#ctravelBus").attr("disabled",false);
	$("#cbusiEmail").attr("disabled",false);
	$("#cbusiPhone").attr("disabled",false);
	
}

partner_account_modify_view.save = function(frm){
	//console.log(frm);
	frm.action = 'partner_account_hisModi';
	frm.submit();
}

partner_account_modify_view.delete = function(frm){
	let yn = confirm('[CAMPIN] 해당 수정 요청건을 정말 삭제하시겠습니까?');
	if(!yn) return;
	
	let url = 'partner_account_modiDel';
	frm.action = url;
	frm.submit();
	alert("요청건이 삭제되었습니다.");
}

partner_account_modify_view.cancel = function(frm){
	let url = 'partner_request_list';
	frm.action = url;
	frm.submit();
}

partner_account_modify_view.modiPwd = function(frm){
	let url = 'partner_pwd_modify';
	frm.action = url;
	frm.submit();
}


