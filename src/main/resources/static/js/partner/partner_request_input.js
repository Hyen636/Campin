/**
 * 
 */
 
let req_input={};



req_input.select=function(val){
	console.log(val);
	let frm = document.getElementById("frm_partner_request_input");
	if(val=="탈퇴"){
		$("#doc").html("탈퇴 요청 || "+"캠핑장 코드 : "+frm.itemCode.value+" || 캠핑장명 : "+frm.mName.value);
	}else{
		$("#doc").html("일반 문의");
	}
}

req_input.save=function(frm){
	console.log("save");
	let param=$("#frm_partner_request_input").serialize();
	console.log("param",param);
	if(frm.category.value=="일반문의"){
			console.log("일반문의");
		$.post("partner_request_inputR",param,function(){
			console.log("저장완료");
			frm.action='partner_request_list';
			frm.submit();
		})
	}else{
		console.log("탈퇴");
		$.ajax({
			type:"post",
			url:"partner_request_inputR",
			data: param,
			cache:false,
			success:function(data){
			console.log("탈퇴", data);
				if(data>0){
					alert("예약정보 수정 필요( 예약 취소가 필요한 예약내역 : "+data+"건)");
				}else{
					frm.action='partner_request_list';
					frm.submit();
				}
			}
		})
		
	}
	
}

req_input.cancel=function(frm){
	frm.action='partner_request_list';
	frm.submit();
}