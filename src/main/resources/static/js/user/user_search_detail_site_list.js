/**
 * 
 */
let user_search_detail_site_list = {};

$(function(){
	var siteCnt = $('#sitecnt').val();
	
	for(var i = 1; i<=siteCnt; i++){
		var strName2 = "#imgSiteName" + i;
		
		$('#siteNameSave').val($(strName2).val());
		var param = $("#frm_user_rough_reservation").serialize();
		var strName = "#siteImg" + i;
		//사이트
		$(strName).load('user_search_detail_site_img', param);
	}
	
})




user_search_detail_site_list.show = function(sno){
	$(".site_items_"+sno).slideToggle("fast");
}




