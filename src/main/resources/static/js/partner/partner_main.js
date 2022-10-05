// partner_main

let partner = {};

$(function(){
	if($("input[name=cUserCode]").val() == ""){
		location.href = "partner_logout"
	}
	
	let state = $("input[name=state]").val();
	let itemCode = $('#itemCode').val();
	switch(state){
		case "":
			$(".camp_modify").css("display", "none");
			break;
		case "등록요청":
		case "등록완료":
			$(".camp_input").css("display", "none");
	}
	
	$(".today_notice").load("partner_notice", 'itemCode='+itemCode);
})

partner.notice = function(type){
	switch(type){
		case "예약":
			partner.reservationList();
			break;
		case "문의":
			partner.qnaList()
			break;
		case "리뷰":
			partner.reviewList()
			break;	
	}
}

partner.mainPage = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_main";
	frm.submit();
}
partner.sales = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_sales";
	frm.submit();
}
partner.qnaList = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_qna_list";
	frm.submit();
}
partner.reviewList = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_review_list";
	frm.submit();
}
partner.reservationList = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_reservation_list";
	frm.submit();
}
partner.modify = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_account_modify";
	frm.submit();
}
partner.pwdModify = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_pwd_modify";
	frm.submit();
}
partner.requestList = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_request_list";
	frm.submit();
}
partner.campInput = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_camp_input";
	frm.submit();
}
partner.campModify = function(){
	let state = $("input[name=state]").val();
	/* 나중에 상태값에 따라 수정 클릭했을 때 안된다고 알림 한 번 더 유효성 검사
	if(state == "캠핑장등록요청"){}
	*/ 
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_camp_modify"
	frm.submit();
}
partner.siteInput = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_site_input";
	frm.submit();
}

partner.logout = function(){
	let frm = document.getElementById("frm_partner_main");
	frm.action = "partner_logout";
	frm.submit();
}