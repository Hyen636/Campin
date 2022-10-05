/**
 * 
 */
 
let review_request={};





review_request.save=function(frm){
	console.log("save");
	let param=$("#frm_partner_review_request").serialize();
	$.post("partner_review_request_inputR",param,function(){
			console.log("저장완료");
			frm.action='partner_review_list';
			frm.submit();
		})
	

}

review_request.cancel=function(frm){
	frm.action='partner_review_list';
	frm.submit();
}