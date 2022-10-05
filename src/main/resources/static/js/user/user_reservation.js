/**
 * 
 */
 
let user = {};


//user_reservationo에서 상세보기 클릭
user.user_reservation_detail = function(frm){
	frm.action = 'user_reservation_detail2';
	frm.submit();
}


//user_reservation에서 홈으로 클릭
user.user_search = function(frm){
	frm.action = 'user_search_NotData';
	frm.submit();
}

//회원정보 체크박스 선택
user.setSame = function(){
	var frm = document.getElementById('frm_user_reservation_input');
	if($('#setSame').is(':checked')==true){
		frm.mName.value = frm.mNameS.value;
		frm.phone.value = frm.phoneS.value;
		frm.email.value = frm.emailS.value;
	}else{
		frm.mName.value = "";
		frm.phone.value = "";
		frm.email.value = "";
	}
}

//옵션 -
user.optMinus = function(index){
	var ea = $('.ea_'+index).val();
	var ea1 = ea-1;
	if(ea1<0){ea1=0};
	$('.ea_'+index).val(ea1);
	var price = $('.optP1_'+index).val();
	var price1 = price * ea1;
	$('.optP_'+index).text(price1);
	var tot = 0;
	let amt = document.getElementsByClassName("optPrice");
	for(let i = 0; i < amt.length; i++){
		tot += Number($(amt[i]).html());	
	}
	$('.extraFeeTot').val(tot);
	var totOpt = "";
	let eOpt = document.getElementsByClassName("extraOpt");
	let eEa = document.getElementsByClassName("ea");
	for (let i=0; i<eOpt.length; i++){
		totOpt += $(eOpt[i]).html() + $(eEa[i]).val();
	}
	$('.extraOptTot').val(totOpt);
	$('.price_tot').text(tot+iPrice*day);
	var cPrice2 = parseInt($('.price').val());
	$('.amt').val(tot+cPrice2);
	var amtF = $('.amt').val();
	$('.price_tot').text(amtF);
	
}



//옵션 + 
user.optPlus = function(index){
	var ea = $('.ea_'+index).val();
	var ea1 = parseInt(ea)+1
	$('.ea_'+index).val(ea1);
	var price = $('.optP1_'+index).val();
	var price1 = price * ea1;
	$('.optP_'+index).text(price1);
	var tot = 0;
	let amt = document.getElementsByClassName("optPrice");
	for(let i = 0; i < amt.length; i++){
		tot += Number($(amt[i]).html());	
	}
	$('.extraFeeTot').val(tot);
	var totOpt = "";
	let eOpt = document.getElementsByClassName("extraOpt");
	let eEa = document.getElementsByClassName("ea");
	for (let i=0; i<eOpt.length; i++){
		totOpt += $(eOpt[i]).html() + $(eEa[i]).val();
	}
	$('.extraOptTot').val(totOpt);
	$('.price_tot').text(tot+iPrice*day);
	var cPrice2 = parseInt($('.price').val());
	$('.amt').val(tot+cPrice2);
	var amtF = $('.amt').val();
	$('.price_tot').text(amtF);
}

//성인인원+
user.AdultPlus = function(){
	var eaA = $('.eaA').val();
	var eaC = parseInt($('.eaC').val());
	var plus = parseInt(eaA)+1
	if(plus > $('.mPeople').val()) plus = $('.mPeople').val();
	$('.eaA').val(plus);
	$('.cPeople').val(plus+eaC);
	
}

//성인인원 -
user.AdultMinus = function(){
	var ea = $('.eaA').val();
	var eaC = parseInt($('.eaC').val());
	var minus = parseInt(ea)-1
	if(minus<=0) minus = 0;
	$('.eaA').val(minus);
	$('.cPeople').val(minus+eaC);
}

//아동인원+
user.ChildPlus = function(){
	var ea = $('.eaC').val();
	var eaA = parseInt($('.eaA').val());
	var plus = parseInt(ea)+1
	if(plus > $('.mPeople').val()) plus = $('.mPeople').val();
	$('.eaC').val(plus);
	$('.cPeople').val(plus+eaA);
	
}

//아동인원-
user.ChildMinus = function(){
	var ea = $('.eaC').val();
	var eaA = parseInt($('.eaA').val());
	var minus = parseInt(ea)-1
	if(minus<=0) minus = 0;
	$('.eaC').val(minus);
	$('.cPeople').val(minus+eaA);
	
}

//총인원제한 + 추가인원 계산
var $cPeople = $(".cPeople");  
$(".cPeople").on('input', function() {
    // Do this when value changes
    var cPeople = parseInt($('.cPeople').val());
	var mPeople = parseInt($('.mPeople').val());
	var eaA = parseInt($('.eaA').val());
	var eaC = parseInt($('.eaC').val());
	var exA = parseInt($('.extraAdult').val());
	var exC = parseInt($('.extraChild').val());
	var sPeople = parseInt($('.sPeople').val());
	var dPrice = iPrice * day;
	var exOpt = ($('.extraFeeTot').val());
	console.log(exOpt);
	if (exOpt == ""){
		exOpt =0;
	}else{
		exOpt = parseInt($('.extraFeeTot').val());
	}
	if(cPeople > mPeople) alert("선택가능 인원을 초과하였습니다.")
	if (sPeople <= eaA){
		var cPrice = dPrice + ((eaA-sPeople)*exA) + (eaC*exC) + exOpt;
		var cPrice2 = dPrice + ((eaA-sPeople)*exA) + (eaC*exC);
		$('.amt').val(cPrice);
		$('.price_tot').text(cPrice);
		$('.price').val(cPrice2)
	}
	if(sPeople > eaA){
		var cPrice = dPrice + ((cPeople-sPeople)*exC) + exOpt;
		var cPrice2 = dPrice + ((cPeople-sPeople)*exC);
		$('.amt').val(cPrice);
		$('.price_tot').text(cPrice);
		$('.price').val(cPrice2);
	}
});



//인원수 
var eaA = Number($('.eaA').val());
var eaC = Number($('.eaC').val());
$('.cPeople').val(eaA + eaC);

//값변화가 입력되는 것처럼...
(function ($) {
    var originalVal = $.fn.val;
    $.fn.val = function (value) {
        var res = originalVal.apply(this, arguments);
 
        if (this.is('input:text') && arguments.length >= 1) {
            // this is input type=text setter
            this.trigger("input");
        }
 
        return res;
    };
})(jQuery);


//주문날짜 오늘로 설정 
$('.dateO').val(new Date().toISOString().substring(0, 10));


//요금세부정보
// 날짜 계산 
var dateStr = $('.dateStr').val();
var dateEnd = $('.dateEnd').val();

var strArray = dateStr.split('-');	        
var endArray = dateEnd.split('-');   

var start = new Date(strArray[0],strArray[1],strArray[2]);
var end = new Date(endArray[0],endArray[1],endArray[2])

var btMs = end.getTime() - start.getTime();
var day = btMs / (1000*60*60*24);
//console.log(day);
$('.day').html(day);

//금액 계산
var iPrice = $('.iPriceI').val();
$('.price_before_tot').text(iPrice * day);
$('.price').val(iPrice * day);
$('.price_tot').text(iPrice * day);


//은행 선택값 전달
$('.bank').on('change', function() {
   let selected = $(".bank option:checked").text();
   $('.bank').val(selected);
});

//user_reservation_input에서 예약하기 클릭
user.user_reservation = function(frm){
	var payment= $('input[name=payment]:checked').val();
	//console.log(payment);
	if(payment == "무통장입금"){
		$('.state').val("입금대기");
	}else{
		$('.state').val("예약대기");
	}
	var cPeople = parseInt($('.cPeople').val());
	var mPeople = parseInt($('.mPeople').val());
	var orderCode0 = new Date().getTime();
	var orderCode1 = $('.userCode').val();
	var orderCode2 = orderCode0 + orderCode1;
	$('.orderCode2').val(orderCode2);
	if(cPeople <= mPeople){
		var param = $(frm).serialize();
		$.post('user_reservation',param,function(resp){
		frm.action = 'user_next_reservation';
		frm.submit();
	})
	}else{
		 alert("선택가능 인원을 초과하였습니다.");
	}
}


//카카오페이테스트결제

user.payment = function(data){
	var payment= $('input[name=payment]:checked').val();
	if(payment == "무통장입금"){
		$('.state').val("입금대기");
	}else{
		$('.state').val("예약대기");
	}
		var cPeople = parseInt($('.cPeople').val());
		var mPeople = parseInt($('.mPeople').val());
		var orderCode0 = new Date().getTime();
		var orderCode1 = $('.userCode').val();
		var orderCode2 = orderCode0 + orderCode1;
		var amt = $('.amt').val();
		var email = $('.email').val();
		var mName = $('.mName').val();
		var phone = $('.phone').val();
		$('.orderCode2').val(orderCode2);
		if(cPeople <= mPeople){
				var IMP = window.IMP; 
				IMP.init('imp63544483'); 
    			IMP.request_pay({
    				pg : "kakaopay", 
        			pay_method : 'card',
		        	merchant_uid : 'merchant_' + new Date().getTime(),
		        	name : 'Campin',
		        	amount : amt,
		       	 	buyer_email : email,
		        	buyer_name : mName,
		       		buyer_tel : phone,
		        	buyer_addr : '구매자 주소',
		        	buyer_postcode : '구매자 주소',
		       	 	m_redirect_url : 'redirect url'
    			}, function(rsp) {
       				if ( rsp.success ) {
           				var msg = '결제가 완료되었습니다.';
           				var frm = document.getElementById('frm_user_reservation_input');
           				var param = $(frm).serialize();
           				console.log(param);
           				$.post('user_reservation',param,function(resp){
						var frm = document.getElementById('frm_user_reservation_input');
						frm.action = 'user_next_reservation';
						frm.submit();
						});
       		 		} else {
          				var msg = '결제에 실패하였습니다.';
           				rsp.error_msg;
        			 }
   				 });
			
			}else{
			 alert("선택가능 인원을 초과하였습니다.");
			}
}

user.reservation = function(){
	if($('.mName').val() == ""){ 
		alert("예약자명을 입력해주세요");
		$('.mName').focus();
	}else if($('.phone').val() == ""){
		alert("연락처를 입력해주세요");
		$('.phone').focus();
	}else if($('.email').val() == ""){
		alert("이메일을 입력해주세요");
		$('.email').focus();
	}else if($('select option:selected').val() == '0') {
    	alert("환불계좌를 선택해주세요.");
    	$('.bank').focus();
	}else if($('.refAct').val() == ""){
		alert("환불계좌를 입력해주세요.")
		$('.refAct').focus();
	}else if($('input[name=payment]:checked').length < 1){
		alert("결제방법을 선택해주세요");
	}
	var payment= $('input[name=payment]:checked').val();
	if(payment =="카카오페이"){
		document.getElementById('btn_kakao').click();
	}else if(payment =="카드결제" || payment =="무통장입금"){
		document.getElementById('btn_reservation').click();
	}
}