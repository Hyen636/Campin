// manager_main

$(function(){
	if($("input[name=id]").val() == ""){
		location.href = "manager_logout"
	}
	
	$(".today_notice").load("manager_notice")
	let job = $("input[name=job]").val();
	if(job == "E"){
		$(".admin_c").css("display", "none");
	}
})


let manager = {};

// 나중에 카테고리에 맞게 이동하도록
manager.notice = function(type){
	console.log(type)
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_request_list";
	frm.submit();
}
manager.mainPage = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_main";
	frm.submit();
}

manager.userList = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_user_list";
	frm.submit();
}
manager.campingList = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_camping_list";
	frm.submit();
}
manager.sales = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_sales";
	frm.submit();
}
manager.requestList = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_request_list";
	frm.submit();
}
manager.accountModify = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_account_modify";
	frm.submit();
}
manager.pwdModify = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_pwd_modify";
	frm.submit();
}
manager.accountInput = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_account_input";
	frm.submit();
}
manager.accountList = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_account_list";
	frm.submit();
}

manager.logout = function(){
	let frm = document.getElementById("frm_manager_main");
	frm.action = "manager_logout"
	frm.submit();
}