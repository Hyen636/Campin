site={}

site.input = function(){
	let param = $("#frm_site_input").serialize();
	console.log(param);
	$.post("partner_input_site", param, function(){
		$("input[name=siteName2]").val($("input[name=siteName]").val());
		let tempFrm = $("#frm_att")[0];
		let frmAtt = new FormData(tempFrm);

		// 이미지 파일 저장
		$.ajax({	
			data : frmAtt,
			url : "site_input_att",
			type : "post",
			enctype : "multipart/form-data",
			cache : false,
			contentType : false,
			processData : false,
			success : function(resp){
				// 사이트 히스토리 저장
				let param = $("#frm_site_input").serialize();
				$.ajax({
					data: param,
					url : "partner_input_site_history",
					type: "post",
					cache: false,
					success: function(resp){
						$("input[name=hisDetailSno]").val(resp);
						let tempFrm = $("#frm_att")[0];
						let frmAtt = new FormData(tempFrm);
						$.ajax({
							data : frmAtt,
							url : "site_input_att_history",
							type : "post",
							enctype : "multipart/form-data",
							cache : false,
							contentType : false,
							processData : false,
							success : function(resp){	// resp : 응답정보
								let frm = $("#frm_att");
								frm.action = "partner_site_input";
								frm.submit();
							}
						})
					}
				})
			}
		})
	})
}

site.modify = function(){
	let param = $("#frm_site_modify").serialize();
	console.log(param);
	
	$.ajax({
		data: param,
		url : "partner_input_site_history",
		type: "post",
		cache: false,
		success: function(resp){
			$("input[name=siteName2]").val($("input[name=siteName]").val());
			$("input[name=hisDetailSno]").val(resp);
			let tempFrm = $("#frm_att")[0];
			let frmAtt = new FormData(tempFrm);
			$.ajax({
				data : frmAtt,
				url : "site_input_att_history",
				type : "post",
				enctype : "multipart/form-data",
				cache : false,
				contentType : false,
				processData : false,
				success : function(resp){	// resp : 응답정보
					alert("수정 요청 되었습니다.")
					let frm = $("#frm_att");
					frm.action = "partner_site_input";
					frm.submit();
				}
			})
		}
	})
	
}
site.confirm = function(){
	$("input[name=state]").val("등록완료");
	let frm = document.getElementById("frm_site_view")
	let param = $(frm).serialize();
	$.ajax({	
		data : param,
		url : "manager_site_confirm",
		type : "post",
		cache : false,
		success : function(result){
			if(result == true){
				alert("요청이 승인되었습니다.")
				frm.action = "manager_request_list"
				frm.submit();			
			} else {
				alert("승인 중 오류가 발생했습니다.")
			}
		}
	})
}

site.reject = function(){
	$("input[name=state]").val("등록반려");
	window.open("manager_site_pop", 'window', 
				'width= 500px, height=400px, top=250, left=500')
}
site.siteImg = function(f){
	let files = f.files;
	let arr = Array.prototype.slice.call(files);
	if(arr.length < 2 || arr.length > 5){
		alert("2장의 이상, 5장 이하의 사진을 올려주세요!");
		return;
	} 
	let dt = new DataTransfer();
	
	$(".preview_site").html("");
	arr.forEach(function(f){
	    let fileName = f.name;
	    let size = parseInt(f.size/(1024));

	    let str = '<div class="site_img_list">';
    	let reader = new FileReader(); 
    	reader.onload = function (e) { 
	        str += '<img src="'+e.target.result+'" title="'+f.name+'" class="site_img" /><br/>';
			str += '<span>'+fileName+' ('+size+'KB)</span><br>';
	        str += '</div>';
	        $(str).appendTo('.preview_site');
      	} 
      	reader.readAsDataURL(f);
	});
	
	for(file of arr){
		dt.items.add(file);		
	}
	
	$('#siteImg')[0].files = dt.files;
}

site.select = function(siteCode){
	let itemCode = $("input[name=itemCode]").val();
	let param = "siteCode="+siteCode+"&itemCode="+itemCode;

	$(".site_info").load("site_select", param, function(){
		let tempSiteType = $(".temp_siteType").val();
		let tempCategory = $(".temp_category").val();
		let position = $("input[name=siteType]")
		let category = $("input[name=category]")
		
		// 사이트 타입, 카테고리 표시
		for(let i = 0; i < position.length; i++){
			if(tempSiteType == position[i].value){
				$(position[i]).prop("checked", true);
				break;	
			}
		}
		for(let i = 0; i < category.length; i++){
			if(tempCategory == category[i].value){
				$(category[i]).prop("checked", true);
				break;	
			}
		}

	});
	
}






