
 /* manager_account_input */
mjoinR = function(){
	//alert("manager join");
	//let url = 'manager_account_inputR';
	//frm.action = url;
	//frm.submit;	
	
	let param = $('.frm_manager_join').serialize();
	//$.post('manager_account_inputR', param);
	$.ajax({
		data : param,
		url : 'manager_account_inputR',
		type : 'POST',
		cache : false,
		sucess : function(){
			frm.action = 'manager_account_list';
			frm.submit();
			location.href='manager_account_list';
			console.log('manager 저장 완료');
		}
	})
	//console.log(param);
}

