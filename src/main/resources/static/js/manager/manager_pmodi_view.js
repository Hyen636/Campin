manager_partner_account_modify_view={};
manager_partner_join_view = {};


let state = $("input[name=state]").val();
//console.log("state.." , state);

(function () {
	console.log("test")
    if (state == "수정요청") {
        $(".btn_permission").css("display", "inline-block");
        $(".btn_regject").css("display", "inline-block");
        $(".btn_Mlist").css("display", "inline-block");
    } else if (state == "요청반려"){
        $(".btn_Mlist").css("display", "inline-block");
        $(".btn_permission").css("display", "none");
        $(".btn_regject").css("display", "none");
    } else if(state == "수정완료"){
	    $(".btn_Mlist").css("display", "inline-block");
        $(".btn_permission").css("display", "none");
        $(".btn_regject").css("display", "none");
	}
})();

/*manager_partner_account_modify */
manager_partner_account_modify_view.confirm = function(frm){
	alert("캠지기 정보 수정이 승인되었습니다.");
	let confirm = "수정완료";
	frm.state.value = confirm;
	frm.action='manager_partner_modify_confirm';
	frm.submit();
}

manager_partner_account_modify_view.reject = function(frm){
	alert("캠지기 정보 수정이 반려되었습니다.");
	let confirm = "요청반려";
	frm.state.value = confirm;
	frm.action='manager_partner_modify_confirm';
	frm.submit();	
}

manager_partner_account_modify_view.list = function(frm){
	let url = 'manager_request_list';
	frm.action = url;
	frm.submit();
}

/*manager_partner_join_view */
manager_partner_join_view.join = function(){
	let frm = document.frm_partner_join;
	//console.log(frm);
	alert("캠지기 등록이 승인되었습니다.");
	let confirm = "등록완료";
	frm.state.value = confirm;
	frm.action='manager_partner_join_confirm';
	frm.submit();
}
manager_partner_join_view.reject = function(){
	let frm = document.frm_partner_join;
	alert("캠지기 등록이 반려되었습니다.");
	let confirm = "요청반려";
	frm.state.value = confirm;
	frm.action='manager_partner_join_confirm';
	frm.submit();
}


