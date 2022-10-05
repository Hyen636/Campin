/**
 * 
 */
 
let req_input={};


(
	req_input=function(){
		let frm = document.getElementById("frm_manager_request_input");
		if(frm!=null){
			if(frm.category.value=="일반문의"){
		console.log("frm",frm.category.value);
			$(btn).hide();
		}	
		}
		
	}
)()


req_input.save=function(frm){
	console.log("save",frm.category.value);
	let param=$("#frm_manager_request_input").serialize();
	console.log("param",param); 
	switch(frm.category.value){
		//일반문의
	    case"일반문의":
        	if(frm.state.value=="답변대기"){
				$.post("manager_general_request_answer",param,function(){
					console.log("저장완료");
					frm.action='manager_request_list';
		 			frm.submit();
				})
			}else if(frm.state.value=="답변완료"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				$.post("manager_general_request_answer",param,function(){
					console.log("업뎃");
					frm.action='manager_request_list';
			 		frm.submit();
				})
			}
        break;
        case"탈퇴":
        	if(frm.state.value=="탈퇴요청"){
				$.post("manager_general_request_answer",param,function(){
					console.log("저장완료");
					frm.action='manager_request_list';
		 			frm.submit();
				})
			}else if(frm.state.value=="탈퇴반려"||frm.state.value=="탈퇴승인"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				$.post("manager_general_request_answer",param,function(){
					console.log("업뎃");
					frm.action='manager_request_list';
			 		frm.submit();
				})
			}
        break;
        case"리뷰":
        	if(frm.state.value=="블랙요청"){
				console.log("블랙승인")
				console.log("cu ", frm.cUserCode.value);
				frm.state.value="블랙승인";
				console.log("블랙승인", frm.state.value)
				let param=$("#frm_manager_review_request").serialize();
				$.post("manager_request_review_answer",param,function(){
					console.log("저장완료");
					frm.action='manager_request_review_answer';
		 			frm.submit();
				})
			}else if(frm.state.value=="블랙반려"||frm.state.value=="블랙승인"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				let param=$("#frm_manager_review_request").serialize();
				$.post("manager_request_review_answer",param,function(){
					console.log("업뎃");
					frm.action='manager_request_review_answer';
			 		frm.submit();
				})
			}
        break;
    }
}

req_input.reject=function(frm){
	console.log("reject",frm.category.value);
	if(frm.answer.value==null||frm.answer.value==""){
		alert("반려사유를 입력해주세요");
	}else{
		if(frm.category.value=="탈퇴"){
			frm.state.value="탈퇴반려";
			console.log("reject ㅅㄷㄴㅅ",frm.category.value);
			let param=$("#frm_manager_request_input").serialize();
			let yn=confirm("반려 하시겠습니까");
			if(!yn)return ;
			
			console.log("reject ㅅㄷㄴㅅ",frm.category.value);
			$.post("manager_general_request_answer",param,function(){
				console.log("반려");
				//frm.action='manager_request_list';
				//frm.submit();
			})
		}else{
			let param=$("#frm_manager_review_request").serialize();
			let yn=confirm("반려 하시겠습니까");
			if(!yn)return ;
			$.post("manager_request_review_answer",param,function(){
				console.log("반려");
				frm.action='manager_request_list';
				frm.submit();
			})
		}
		
	}
}

req_input.cancel=function(frm){
	frm.action='manager_request_list';
	frm.submit();
}