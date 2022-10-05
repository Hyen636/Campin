/**
 * 
 */
 
qna_input={};

qna_input.return=function(frm){
	console.log(frm);
	console.log(frm.state.value);
	
	frm.action='partner_qna_list';
	frm.submit();
}


qna_input.save=function(frm){
	console.log(frm.state.value); 
	var st=frm.state.value;
	let param=$(".frm_partner_qna_input").serialize();
	if(st=="답변대기"){
		$.post("partner_qna_inputR",param,function(){
		console.log("save");
		frm.action='partner_qna_list';
		frm.submit();
	})
	}else{
		let yn=confirm("수정하시겠습닏까?");
		if(!yn)return ;
		console.log("답변완료 업데이트");
		$.post("partner_qna_inputR",param,function(){
		console.log("update");
		frm.action='partner_qna_list';
		frm.submit();
	})
	}

}

qna_input.delete=function(frm){
	console.log(frm.state.value);
	var st=frm.state.value;
	var s=frm.sno.value;
	var g=frm.grp.value;
	let param=$(".frm_partner_qna_input").serialize();
	
	if(st=="답변대기"){
		alert("삭제할 답변이 없습니다.");
	}else{
		let yn=confirm("답변을 삭제하시겠습니까?");
		if(!yn)return ;
		console.log("삭제 시작");
		$.post("partner_qna_deleteR",param,function(){
			console.log("삭제완료");
			frm.action='partner_qna_list';
			frm.submit();
		})
	}

}