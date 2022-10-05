/**
 * 
 */
//희찬

var user_search_detail_reservation = {};


$(function(){
	
})









$("#siteSelected").change(function(){
	var v = $("#siteSelected").val();
    $("#siteName").val(v);
})

// 인원수 조절
$("#adult_plus").on("click", function(){
	user_search_detail.adultCount++;
	$(".num_adult").val(user_search_detail.adultCounts);
	$("#adult_minus").css("cursor", "pointer");
	//희찬
	$('.adultCountSave2').val(user_search_detail.adultCount);
	console.log()
	//fixed 	
	let param = $("#frm_user_rough_reservation").serialize();
	$('.sticky_box').load('user_search_detail_fixed_reservation', param);
})

$("#adult_minus").on("click", function(){
	if(user_search_detail.adultCount == 0){
		$("#adult_minus").css("cursor", "not-allowed");
	} else {
		user_search_detail.adultCount--;
		$(".num_adult").val(user_search_detail.adultCount);
		//희찬
		$('.adultCountSave2').val(user_search_detail.adultCount);
		//fixed 	
		let param = $("#frm_user_rough_reservation").serialize();
		$('.sticky_box').load('user_search_detail_fixed_reservation', param);
	}
})

$("#child_plus").on("click", function(){
	user_search_detail.childCount++;
	$(".num_child").val(user_search_detail.childCount);
	$("#child_minus").css("cursor", "pointer");
	//희찬
	$('.childCountSave2').val(user_search_detail.childCount);
	//fixed 	
	let param = $("#frm_user_rough_reservation").serialize();
	$('.sticky_box').load('user_search_detail_fixed_reservation', param);
})

$("#child_minus").on("click", function(){
	if(user_search_detail.childCount == 0){
		$("#child_minus").css("cursor", "not-allowed");
	} else {
		user_search_detail.childCount--;
		$(".num_child").val(user_search_detail.childCount);
		//희찬
		$('.childCountSave2').val(user_search_detail.childCount);
		//fixed 	
		let param = $("#frm_user_rough_reservation").serialize();
		$('.sticky_box').load('user_search_detail_fixed_reservation', param);
	}
})




//예약 페이지 이동
user_search_detail_reservation.reservation_input_page = function(){
	if($('#userId').val() != ""){
		var frm = document.getElementById('frm_user_rough_reservation');
		if($("#siteName").val() == "-"){
			alert("사이트를 선택해주세요.");
		}
		else{
			frm.action='user_reservation_input';
			frm.submit();
		}
	}
	else{
		alert("로그인 후에 이용 가능한 컨텐츠입니다. \n로그인 화면으로 이동합니다.");
			location.href = 'user_login'
	}
}

/*
function checkout_onchange(obj){
	var temp = $('#saveCheckOut').val();
	console.log(obj.value);
	$('#saveCheckOut').change();
}
$('.reservaion_checkout').change(function(){
	console.log("Test희찬2");
		//fixed
		let param = $("#frm_user_rough_reservation").serialize();
		$('.sticky_box').load('user_search_detail_fixed_reservation', param);
})*/


