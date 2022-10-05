/**
 * 
 */
 
let user_mypage = [];
let frm = document.getElementById('frm_user_mypage');


//이미지 클릭시 마이페이지 메인으로 이동 
let user_mypage_logo = document.getElementById('user_mypage_logo');
user_mypage_logo.onclick = function(){
	frm.action = 'user_reservation_list';	
	frm.submit();
}

user_mypage.user_search = function(){
	/*frm.action = 'user_search';
	frm.submit();*/
	/*희찬 */
	frm.action = 'user_search_NotData';
	frm.submit();
	
}
/*
user_mypage.user_mypage = function(){
	frm.action = 'user_reservation_list';
	frm.submit();
}
*/
// 수경 수정
user_mypage.user_modify = function(){
	var id = frm.id.value;
	//frm.id.value = id;
	frm.action = 'user_modify';
	frm.submit();
}

// 수경 수정
user_mypage.user_pwd_modify = function(){
	let id = frm.id.value;
	frm.action = 'user_pwd_modify';
	frm.submit();
}

// 수경 수정
user_mypage.user_remove_account = function(){
	let userCode = frm.userCode.value;
	frm.action = 'user_remove_account';
	frm.submit();
}

// 수경 수정
user_mypage.user_wishlist = function(){
	let userCode = frm.userCode.value;
	frm.action = 'user_wishlist';
	frm.submit();
}

user_mypage.user_reservation_list = function(){
	var userCode = frm.userCode.value;
	//console.log('userCode: ',userCode);
	frm.action = 'user_reservation_list';
	frm.submit()
}

user_mypage.user_qna_list = function(){
	var userCode = frm.userCode.value;
	console.log(userCode);
	frm.action = 'user_qna_list';
	frm.submit();
}

user_mypage.user_review_list = function(){
	var userCode = frm.userCode.value;
	frm.action = 'user_review_list';
	frm.submit();
}

//혠


//user_qna_list

//문의사항 클릭시 슬라이드
user_mypage.showDetail = function(index){
	$(".detail_"+index).slideToggle("fast");
}

//user_qna_list_textrea 높이 조절 

//문의수정 클릭시 textarea + 저장버튼 활성화 
user_mypage.modifyQna = function(sno){
	$(".uq_"+sno).attr("disabled",false);
	$(".uq_"+sno).focus();
	$(".btm_"+sno).css("display","none");
	$(".btd_"+sno).css("display","none");
	$(".bts_"+sno).css("display","inline-block");
	$(".btc_"+sno).css("display","inline-block");
}

//문의저장 클릭
user_mypage.saveQna = function(sno){
	var doc = $(".uq_"+sno).val();
	var param = "sno="+sno+"&doc="+doc;
	//console.log(param);
	$.post('user_save_qna',param,function(resp){
	})
	//console.log('ok');
	$(".uq_"+sno).attr("disabled",true);
	$(".btm_"+sno).css("display","inline-block");
	$(".btd_"+sno).css("display","inline-block");
	$(".bts_"+sno).css("display","none");
	$(".btc_"+sno).css("display","none");
	$('.doc_hidden_'+sno).val($('.uq_'+sno).val());
}

//수정취소 클릭
user_mypage.cancelQna = function(sno){
	$('.uq_'+sno).val($('.doc_hidden_'+sno).val());
	$(".uq_"+sno).attr("disabled",true);
	$(".btm_"+sno).css("display","inline-block");
	$(".btd_"+sno).css("display","inline-block");
	$(".bts_"+sno).css("display","none");
	$(".btc_"+sno).css("display","none");
}

//문의삭제 클릭
user_mypage.deleteQna = function(sno){
	var frm = $('#frm_user_qna_list');
	var param = "sno="+sno;
	let chk = confirm("문의를 삭제하시겠습니까?");
	if(!chk) return;
	
	$.post('user_delete_qna',param,function(resp){
		frm.action = 'user_qna_list';
		frm.submit();
	})
}


//user_reservation_list

// 리뷰작성 클릭시 슬라이드
user_mypage.showRInput = function(index){
	//$(".review_input").hide();
	$(".revInput_"+index).slideToggle("fast");
 }
 
 //리뷰작성 취소
 user_mypage.cancelReview = function(index){
	$(".revInput_"+index).slideToggle("fast");
	$(".doc_"+index).val($('doc_i').val());
	let drawStar = $('.drawStar_'+index);
	drawStar.val(0);
	let per = (drawStar.val()*20)+'%';
	$('.star2_'+index).css("width",per);
	$('.score_'+index).val(drawStar.val());
	$('.rev_photo_input').val("");
	$('.revPhotoPreview_'+index).css("display","none");
}

//리뷰보기 클릭
user_mypage.showRView = function(){
	frm.action = 'user_review_list';
	frm.submit();
}

//사진첨부클릭
user_mypage.click = function(index){
	//console.log("ok");
	$('#revP').click();
}

//첨부이미지 미리보기
user_mypage.index = function(index){
	var fileDOM = document.querySelector('#revP');
	var preview = document.querySelector('#preview_'+index);

fileDOM.addEventListener('change', () => {
  var imageSrc = URL.createObjectURL(fileDOM.files[0]);
  preview.src = imageSrc;
  $('.revPhotoPreview_'+index).css("display","inline-block");
});
}

//별점드래그
user_mypage.star = function(index){
	let drawStar = $('.drawStar_'+index);
	//console.log(drawStar.val());
	let per = (drawStar.val()*20)+'%';
	//console.log(per);
	$('.star2_'+index).css("width",per);
	$('.score_'+index).val(drawStar.val());
}

//리뷰등록 날짜 오늘로 설정
$('.nal_i').val(new Date().toISOString().substring(0, 10));

//리뷰저장 
user_mypage.reviewSave = function(index){
	var nal = $('.nal_i').val();
	$('.orderCode_i').val($('.orderCode_'+index).val());
	var orderCode = $('.orderCode_i').val();
	var userCode = $('.userCode_i').val();
	$('.siteCode_i').val($('.siteCode_'+index).val());
	var siteCode = $('.siteCode_i').val();
	$('.siteName_i').val($('.siteName_'+index).val());
	var siteName = $('.siteName_i').val();
	$('.doc_i').val($('.doc_'+index).val());
	var doc = $('.doc_i').val();
	$('.score_i').val($('.score_'+index).val());
	var score = $('.score_i').val();
	$('.itemCode_i').val($('.itemCode_'+index).val());
	var itemCode = $('.itemCode_i').val();
	
	let frm = $('.frm_reviewInput')[0];
	var formData = new FormData(frm);
	
	if(score == "" && doc == "")alert('점수와 내용을 입력해주세요.')
	else if(score == "") alert('점수를 입력해주세요');
	else if(doc == "") alert('내용을 입력해주세요');
	if(score != "" && doc != ""){
	$.ajax({
		type: 'POST',
		url: 'user_input_review',
		enctype : 'multipart/form-data',
		processData: false,
   		contentType: false,
   		data: formData,
   		success: function(){
			frm = $('.frm_reviewInput')[0];
			frm.action='user_review_list';
			frm.submit();
		}
	})
}
}

//예약내역 클릭시 예약 상세
user_mypage.viewDetail = function(index){
	$('.orderCode_i').val($('.orderCode_'+index).val());
	let frm = document.getElementById('frm_reviewInput');
	frm.action = 'user_reservation_detail2'
	frm.submit();
}


//user_reservation_detail
//예약정보  수정 클릭 
user_mypage.reservationModify = function(){
	$('.userCar').attr("readonly",false);
	$('.userCar').focus();
	$('.pet2').css("display","inline-block");
	$('.pet1').css("display","none");
	$('.doc').attr("readonly",false);
	$('.doc').attr("disabled",false);
	$('.btn_reservation_modify').css("display","none");
	$('.btn_reservation_save').css("display","block");
	$('.doc').css("border","solid 1px #aaa");
}

//예약정보 수정 저장 클릭
user_mypage.reservationSave = function(frm){
	var orderCode = frm.orderCode.value;
	var userCar = $('.userCar').val();
	var pet = $("input[name=pet]:checked").val();
	var doc = $('.doc').val();
	if(pet == null) {
		alert("반려동물 여부를 선택해주세요");
	}else{
		//console.log(userCar);
		//console.log(doc);
		frm.action = 'user_modify_reservation';
		frm.submit();
	}
}

//예약취소요청 클릭  
user_mypage.cancelR = function(frm){
	var orderCode = frm.orderCode.value;
	var userCode = frm.userCode.value;
	var param = "orderCode="+orderCode+"&userCode="+userCode;
	let chk = confirm("예약취소를 요청하시겠습니까?");
	if(!chk) return;
	
	$.post('user_cancel_reservation',param,function(resp){
		frm.action = 'user_reservation_list';
		frm.submit();
	})
}

//별점드래그
user_mypage.star2 = function(index){
	let drawStar = $('.drawStar3_'+index);
	//console.log(drawStar.val());
	let per = (drawStar.val()*20)+'%';
	//console.log(per);
	$('.star4_'+index).css("width",per);
	$('.score3_'+index).val(drawStar.val());
}

//리뷰 수정
user_mypage.reviewModify = function(index){
	console.log("수정클릭!")
	console.log($('.reviewC_'+index));
	$('.reviewC_'+index).attr("disabled",false);
	$('.reviewC_'+index).focus();
	$('.btn_Rmodify_'+index).css("display","none");
	$('.btns_'+index).css("display","inline-block");
	$('.drawStar3_'+index).attr("disabled",false);
}

//R사진첨부클릭
user_mypage.Rclick = function(index){
	//console.log("ok");
	$('#revP').click();
}

//R첨부이미지 미리보기
user_mypage.Rindex = function(index){
	var fileDOM = document.querySelector('#revP');
	var preview = document.querySelector('#preview_'+index);

fileDOM.addEventListener('change', () => {
  var imageSrc = URL.createObjectURL(fileDOM.files[0]);
  preview.src = imageSrc;
});
}

//R리뷰저장 
user_mypage.reviewSave2 = function(index){
	$('.orderCode_i').val($('.orderCode_'+index).val());
	var orderCode = $('.orderCode_i').val();
	var userCode = $('.userCode_i').val();
	$('.doc_i').val($('.reviewC_'+index).val());
	var doc = $('.doc_i').val();
	$('.score_i').val($('.score3_'+index).val());
	var score = $('.score_i').val();
	$('.itemCode_i').val($('.itemCode_'+index).val());
	var itemCode = $('.itemCode_i').val();
	$('.sysFile_i').val($('.sysFile_'+index).val());
	var sysFile = $('.sysFile_i').val();
	
	let frm = $('.frm_reviewModify')[0];
	var formData = new FormData(frm);
	console.log(formData);
	if(score == "" && doc == "")alert('점수와 내용을 입력해주세요.')
	else if(score == "") alert('점수를 입력해주세요');
	else if(doc == "") alert('내용을 입력해주세요');
	
	if(score != "" && doc != ""){
	$.ajax({
		type: 'POST',
		url: 'user_modify_review',
		enctype : 'multipart/form-data',
		processData: false,
   		contentType: false,
   		data: formData,
   		success: function(){
			$('.reviewC_'+index).attr("disabled",true);
			$('.btn_Rmodify_'+index).css("display","inline-block");
			$('.btns_'+index).css("display","none");
			$('.drawStar3_'+index).attr("disabled",true);
			$('.docS_'+index).val($('.reviewC_'+index).val()); 
		}
	})
}
	
}

//리뷰수정취소 
user_mypage.cancelReview2 = function(index){
	$('.reviewC_'+index).attr("disabled",true);
	$('.btn_Rmodify_'+index).css("display","inline-block");
	$('.btns_'+index).css("display","none");
	$('.drawStar3_'+index).attr("disabled",true);
	$('.reviewC_'+index).val($('.docS_'+index).val());
	var firstPhoto = $('.firstPhoto_'+index).val();
	console.log(firstPhoto)
	$('.revPhotoPreview_'+index).attr("src",firstPhoto);
}


//리뷰삭제
$(".deleteR").on("click",function(){
	let index = $(this).attr("class").split(" ")[1];
	console.log(index)
	var orderCode = $('.orderCode_'+index).val();
	var itemCode = $('.itemCode_'+index).val();
	var sysFile = $('.sysFile_'+index).val();
	var param = "orderCode=" + orderCode+"&itemCode="+itemCode+"&sysFile="+sysFile;
	let chk = confirm("리뷰를 삭제하시겠습니까?");
	if(!chk) return;
	$.post('user_delete_review',param,function(resp){
		frm.action = 'user_review_list';
		frm.submit();
	})
})