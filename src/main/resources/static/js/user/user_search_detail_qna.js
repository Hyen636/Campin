/**
 * 
 */
 let user_qna = {};
 
 $(function(){
	//userId
	let id = $(opener.document).find("#userId").val();
	$("#userid").val(id);
	
	//날짜
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);
	var dateString = year + '-' + month  + '-' + day;
	$('#inputDate').val(dateString);
	
	//userCode,itemCode
	let uCode = $(opener.document).find("#userCode").val();
	let iCode = $(opener.document).find("#detailItemCode").val();
	$('#qna_userCode').val(uCode);
	$('#qna_itemCode').val(iCode);
	
	//pwdcheck
	$('#pwdCheck').val();
})


funcClick = function(){
	/*
	var check = document.getElementById('Check');
	console.log(check);
	var bCheck = $(check).is('checked');
	console.log(bCheck);
	if(bCheck){
		$('#pwdCheck').val('t');
		console.log("test : " + $('#pwdCheck').val());
	}
	else{
		$('#pwdCheck').val('f');
		console.log("test2 : " + $('#pwdCheck').val());
	}
	*/
}

user_qna.qnaSave = function(frm){
	let chk = $("#Check").prop("checked");
	console.log(chk)
	if(chk){
		frm.pwdCheck.value = 't';
		console.log("test : " + $('#pwdCheck').val());
	}else{
		frm.pwdCheck.value = 'f';
		console.log("test2 : " + $('#pwdCheck').val());
	}
	console.log(frm.pwdCheck.value);
	//frm.action = "user_search_qna_input_save";
	//frm.submit();
	
	let param = $(frm).serialize();
	$.post("user_search_qna_input_save", param, function(){
		self.close();
	})
	
}

user_qna.qnaCancel = function(){
	self.close();
}