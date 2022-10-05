/**
 * 
 */
 
let req_view={};


req_view.modify=function(frm){
	var temp=frm.category.value;
	console.log("state ", frm.state.value);
	console.log("save",frm.category.value);
	console.log("save",frm.category.value);
	console.log("save",frm.category.value);
	let param=$("#frm_partner_request_view").serialize();
	console.log("param",param); 
	switch(frm.category.value){
		//일반문의
	    case"일반문의":
        	if(frm.state.value=="답변대기"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
				
			}else if(frm.state.value=="답변완료"){
				alert("답변완료 상태는 수정 할 수 없습니다.")
				
			}
        break;
        case"탈퇴":
        	if(frm.state.value=="탈퇴요청"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
			}else if(frm.state.value=="탈퇴반려"||frm.state.value=="탈퇴승인"){
				alert("반려/승인 상태는 수정 할 수 없습니다.")
			}
        break;
        case"리뷰":
        
			param=$("#frm_partner_review_request").serialize();
        	if(frm.state.value=="블랙요청"){
				let yn=confirm("수정하시겠습니까");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
			}else if(frm.state.value=="탈퇴반려"||frm.state.value=="탈퇴승인"){
				alert("반려/승인 상태는 수정 할 수 없습니다.")
			}
        break;
    }
}



req_view.delete=function(frm){
	console.log("save",frm.category.value);
	switch(frm.category.value){
		//일반문의
	    case"일반문의":
        	if(frm.state.value=="답변대기"){
				frm.category.value="삭제";
				let param=$("#frm_partner_request_view").serialize();
				console.log("param",param); 
				let yn=confirm("삭제하시겠습니까?");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
			}else if(frm.state.value=="답변완료"){
				alert("답변완료 상태는 삭제 할 수 없습니다.")
				frm.category.value=temp;
			}
        break;
        case"탈퇴":
        	if(frm.state.value=="탈퇴요청"){
				frm.category.value="삭제";
				let param=$("#frm_partner_request_view").serialize();
				console.log("param",param); 
				let yn=confirm("삭제하시겠습니까?");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
			}else if(frm.state.value=="탈퇴반려"||frm.state.value=="탈퇴승인"){
				alert("반려/승인 상태는 삭제 할 수 없습니다.")
				frm.category.value=temp;
			}
        break;
        case"리뷰":
        	if(frm.state.value=="블랙요청"){
				frm.category.value="삭제";
				let param=$("#frm_partner_review_request").serialize();
				console.log("param",param); 
				let yn=confirm("삭제하시겠습니까?");
				if(!yn)return ;
				$.post("partner_request_modify",param,function(){
					console.log("업뎃");
					frm.action='partner_request_list';
			 		frm.submit();
				})
			}else if(frm.state.value=="블랙반려"||frm.state.value=="블랙승인"){
				alert("반려/승인 상태는 삭제 할 수 없습니다.")
				frm.category.value=temp;
			}
        break;
        }
}

req_view.cancel=function(frm){
	frm.action='partner_request_list';
	frm.submit();
}