/**
 * 
 */

qna={};


qna.view = function(sno, nal, pwd, grp, state, userCode, mName, title){
	
	console.log("view : ", sno,pwd, grp, state, userCode, mName, title);
	let frm=$("#frm_qna_filter")[0];
	frm.sno.value=sno;
	frm.grp.value=grp;
	frm.stateR.value=state;
	frm.userCode.value=userCode;
	frm.mName.value=mName;
	frm.pwd.value=pwd;
	frm.nal.value=nal;
	frm.title.value=title;
	
	frm.action='partner_qna_input';
	frm.submit();
}

qna.find=function(){
	let frm=$("#frm_qna_filter")[0];
	console.log("find", frm);
	console.log("find", frm.findStr.value);
	console.log("find", frm.state.value);
	console.log("find", frm.stateR.value);
	frm.action='partner_qna_list';
	frm.submit();
}


//Partner_qna_input.jsp
qna.return=function(frm){
	console.log("cancel");
	frm.action='partner_qna_list';
	frm.submit();
}


let frm=$("#frm_qna_filter")[0];
console.log(frm.stateR.value);

if(frm.stateR.value=="답변대기"){
	$("#wait").prop("checked", true);
	console.log("답변대기 if");
}else{
	$("#complete").prop("checked",true);
	console.log("답변완료 if");
}




