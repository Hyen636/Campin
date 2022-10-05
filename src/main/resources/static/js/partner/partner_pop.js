pop = {}

pop.campWrite = function(){
	let msg = $(".rejectMsg").val();
	$(opener.document).find("input[name=rejectMsg]").val(msg);

	let param = $(window.opener.document).find("#frm_camp_view").serialize();
	$.post("manager_camp_reject", param,  function(){
		window.opener.location.href="manager_request_list";
		window.close();
	})
	
}

pop.siteWrite = function(){
	let msg = $(".rejectMsg").val();
	$(opener.document).find("input[name=rejectMsg]").val(msg);

	let param = $(window.opener.document).find("#frm_site_view").serialize();
	$.post("manager_site_reject", param,  function(){
		window.opener.location.href="manager_request_list";
		window.close();
	})
	
}

pop.close = function(){ 
	window.close();
}
