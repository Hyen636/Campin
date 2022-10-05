/**
 * 
 */

let review={};


(
	review=function(){
		let frmtest = document.getElementById("frm_review_list");
		if(frmtest.type.value=="nal"){
		   	$("input:radio[name='filter'][value='nal']").attr('checked', true);
		}else if(frmtest.type.value=="score"){
		   	$("input:radio[name='filter'][value='score']").attr('checked', true);
		}else if(frmtest.type.value=="state"){
		   	$("input:radio[name='filter'][value='state']").attr('checked', true);
		}
	}
	
)()		




review.type=function(type){
	let frm = document.getElementById("frm_review_list");
	frm.type.value=type;
	let param=$("#frm_review_list").serialize();
	$.ajax({
			type:"post",
			url:"partner_review_list",
			data: param,
			cache:false,
			success:function(){
				frm = document.getElementById("frm_review_list");
				frm.action='partner_review_list';
				frm.submit();
				
			}
		}
	)
	
}

review.sort=function(sort){
	console.log("sort",sort);
	let frm = document.getElementById("frm_review_list");
	if(sort=="desc"){
		console.log("desc");
		frm.sort.value="asc";
		console.log("frm.sort.value",frm.sort.value);
		frm.action='partner_review_list';
		frm.submit();
	}else{
		console.log("asc")
		frm.sort.value="desc";
		console.log("frm.sort.value",frm.sort.value);
		frm.action='partner_review_list';
		frm.submit();
	}
}

review.answer=function(orderCode,state){
	let frm = document.getElementById("frm_review_list");
	frm.orderCode.value=orderCode;
	let param=$("#frm_review_list").serialize();
	var answer="."+orderCode;
	var btnSave="#"+orderCode+"btn_save";
	var btnDelete="#"+orderCode+"btn_delete";
	if(state!="미답변"){
		if(state=="블랙요청"||state=="블랙승인"){
            $(btnSave).hide();
            $(btnDelete).hide();
		}
		$.ajax({
			type:"post",
			url:"partner_review_list_answer",
			data: param,
			cache:false,
			success:function(doc){
				console.log("doc", doc,);
				$(answer).val(doc)
				if(doc==null){
					$(answer).val("미답변")
				}
			}
		})
		
	}else{
		console.log("미답변")
	}
	
	var id="#"+orderCode +"answer_section";
	if($(id).first().is(":hidden")){
		$(id).slideDown(300);
		
		$(id).visi();	
	}else{
		$(id).slideUp(300);
	}
}


review.save=function(orderCode,state,userCode){
	let frm = document.getElementById("frm_review_list");
	var textArea="."+orderCode;
	var doc = $(textArea).val();
	console.log(orderCode);
	console.log(doc);
	console.log(state);
	console.log(userCode);
	
	frm.orderCode.value=orderCode;
	frm.state.value=state;
	frm.userCode.value=userCode;
	frm.doc.value=doc;
	if(state=="답변완료"){
		let param=$("#frm_review_list").serialize();
		$.ajax({
			type:"post",
			url:"partner_review_list_answerR",
			data: param,
			cache:false,
			success:function(cnt){
				console.log("cnt", cnt,);
				frm.action='partner_review_list';
				frm.submit();
			}
		})
	}else{
		let param=$("#frm_review_list").serialize();
		$.ajax({
			type:"post",
			url:"partner_review_list_answerR",
			data: param,
			cache:false,
			success:function(doc){
				console.log("doc", doc,);
				frm.action='partner_review_list';
				frm.submit();
			}
		})
	}
}


review.delete=function(orderCode){
	let frm = document.getElementById("frm_review_list");
	frm.orderCode.value=orderCode;
	let param=$("#frm_review_list").serialize();
	$.ajax({
		type:"post",
		url:"partner_review_list_delete",
		data: param,
		cache:false,
		success:function(cnt){
			console.log("cnt", cnt,);
			frm.action='partner_review_list';
			frm.submit();
		}
	})
	
}
	
review.black=function(sno){
	let frm = document.getElementById("frm_review_list");
	frm.sno.value=sno;
	frm.action='partner_review_request';
	frm.submit();	
}

review.bookslide=function(list){
	if($(list).first().is(":hidden")){
		$(list).slideDown(300);
	
		if(list==".mgt_book_list"){
			$("#mgt_book_list_icon").css("transform","rotate(90deg)")
		}else{
			$("#mgt_info_list_icon").css("transform","rotate(90deg)")
		}
	}else{
		$(list).slideUp(300);
		if(list==".mgt_book_list"){
			$("#mgt_book_list_icon").css("transform","rotate(0deg)")
		}else{
			$("#mgt_info_list_icon").css("transform","rotate(0deg)")
		}
	}
}