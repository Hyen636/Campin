manager_user_list ={};
manager_camping_list ={};
manager_account_list={};

/*manager_user_list */
manager_user_list.movePage= function(alpage){			
	let frm = $('.frm_manager_user_list')[0];
	frm.nowPage.value = alpage;
	frm.action = 'manager_user_list';
	frm.submit();
}

manager_user_list.select = function(){
	let frm = $('.frm_manager_user_list')[0];
	frm.action = 'manager_user_list';
	frm.submit();
}

manager_user_list.find = function(){
	let frm = $('.frm_manager_user_list')[0];
	frm.nowPage.value = 1;
	//let findStr = frm.findStr.value;
	//console.log('frm js findStr', findStr);
	manager_user_list.select(frm);
}

/*manager_camping_list */
manager_camping_list.movePage= function(acpage){			
	let frm = $('.frm_manager_camping_list')[0];
	frm.nowPage.value = acpage;
	frm.action = 'manager_camping_list';
	frm.submit();
}

manager_camping_list.select = function(){
	let frm = $('.frm_manager_camping_list')[0];
	frm.action = 'manager_camping_list';
	frm.submit();
}

manager_camping_list.find = function(){
	let frm = $('.frm_manager_camping_list')[0];
	frm.nowPage.value = 1;
	manager_camping_list.select(frm);
}

/*manager_account_list */
manager_account_list.movePage= function(ampage){			
	let frm = $('.frm_manager_account_list')[0];
	frm.nowPage.value = ampage;
	frm.action = 'manager_account_list';
	frm.submit();
}

manager_account_list.select = function(){
	let frm = $('.frm_manager_account_list')[0];
	frm.action = 'manager_account_list';
	frm.submit();
}

manager_account_list.find = function(){
	let frm = $('.frm_manager_account_list')[0];
	frm.nowPage.value = 1;
	manager_account_list.select(frm);
}

manager_account_list.delete = function(aUserCode){
	
	let aJob = frm_manager_account_list.job.value;
	//console.log(aJob);

	if(aJob == "A"){
		let yn = confirm('[CAMPIN] 관리자의 계정을 정말 삭제하시겠습니까?');
		if(!yn) return;

		let frm = document.frm_manager_account_list;
		frm.aUserCode.value = aUserCode;
		frm.action = 'manager_account_listDel';
		frm.submit();
		
	}else if(aJob == "E"){
		alert("[CAMPIN] 관리자 계정 삭제는 최고 관리자만 가능합니다. 최고 관리자에게 문의 바랍니다.");
		return;
	}else{
		alert("[CAMPIN] 관리자 계정 삭제는 최고 관리자만 가능합니다. 최고 관리자에게 문의 바랍니다.");
		return;
	}
}
