/**
 * 
 */
let camp = {};
let geocoder = new kakao.maps.services.Geocoder();

camp.input = function(){
	let param = $("#frm_camp_input").serialize();
	// item에 저장 후 history 저장하기
	$.ajax({	// 캠핑장 저장
		data : param,
		url : "partner_input_camp",
		type : "post",
		cache : false,
		success : function(resp){	
			$("input[name=itemCode]").val(resp);
			frm = document.getElementById("frm_camp_input");
			param = $(frm).serialize();
			
			$.post("partner_input_filter", param, function(){ // 필터 저장
				let tempFrm = $("#frm_att")[0];
				let frmAtt = new FormData(tempFrm);
				// 이미지 파일 저장
				$.ajax({	
					data : frmAtt,
					url : "camp_input_att",
					type : "post",
					enctype : "multipart/form-data",
					cache : false,
					contentType : false,
					processData : false,
					success : function(resp){	
						// 캠핑장 히스토리 저장
						$.ajax({	
							data : param,
							url : "partner_input_camp_hitory",
							type : "post",
							cache : false,
							success : function(resp){
								$("input[name=hisItemSno]").val(resp);
								param = $("#frm_camp_input").serialize();
								$.post("partner_input_filter_hitory", param, function(){									
									let tempFrm = $("#frm_att")[0];
									let frmAtt = new FormData(tempFrm);
									$.ajax({
										data : frmAtt,
										url : "camp_input_att_history",
										type : "post",
										enctype : "multipart/form-data",
										cache : false,
										contentType : false,
										processData : false,
										success : function(resp){	// resp : 응답정보
											$(".camp_modify").css("display", "block");
											$(".camp_input").css("display", "none");
											let frm = $("#frm_partner_main");
											frm.action = partner_site_input;
											frm.submit();
										}
									})
								})
							}
						})
					}
				})
			})
		}
	})	
}


camp.addOption = function(){
	$(".option").append(`
		<div>
			<label>옵션 : </label><input type="text" name="optItem">
			<label>가격 : </label><input type="text" name="price"> 
			<button type="button" class="btn_remove_option" onclick="camp.removeOption(this)">X</button>
			<br/>
		</div>
	`)
}

camp.removeOption = function(btn){
	$(btn).parent().remove();
}

$(".environment").on("change", function(){
	let env  = $(".environment:checked");
	if(env.length > 2){
		alert("최대 2개까지 선택 가능합니다.")
		$(env[2]).prop("checked", false);
	}
})

$(".tag").on("change", function(){
	let tag  = $(".tag:checked");
	if(tag.length > 3){
		alert("최대 3개까지 선택 가능합니다.")
		$(tag[3]).prop("checked", false);
	}
})

$(".btn_find_zipCode").on("click", function(){	
	new daum.Postcode({
		oncomplete: function(data) {
			$("input[name=zipCode]").val(data.zonecode);
			$("input[name=address1]").val(data.address);
			switch(data.sido){
				case "부산":
				case "대구":
				case "울산":
				case "경남":
				case "경북":
					$("input[name=sido]").val("경상도");
					break;			
				case "대전":
				case "세종특별자치시":
				case "충북":
				case "충남":
					$("input[name=sido]").val("충청도");
					break;				
				case "광주":
				case "전북":
				case "전남":
					$("input[name=sido]").val("전라도");
					break;
				case "제주특별자치도":
					$("input[name=sido]").val("제주도");
					break;
				case "경기":
					$("input[name=sido]").val("경기도");
					break;
				case "서울":
					$("input[name=sido]").val("서울");
					break;
				case "인천":
					$("input[name=sido]").val("인천");
					break;
			}
	
			let juso =  $("input[name=address1]").val();
        	geocoder.addressSearch(juso, camp.geo);  
		}
   	}).open();
})

camp.geo = function(result, status) {
	if (status === kakao.maps.services.Status.OK) {
		$("input[name=longitude]").val(result[0].x);
		$("input[name=latitude]").val(result[0].y);
	}
};
// 배치도
camp.mapImg = function(f){
	let files = f.files;
	let file = Array.prototype.slice.call(files)[0];
	let str = '';
	let size = parseInt(file.size/(1024));
	let preview = new FileReader();
	let dt = new DataTransfer();

	str += `<div>
				<span class="file_size">${file.name} (${size}KB)</span>
			</div>`
			
	$('.preview_map').html(str);
	$('.preview_map').css("display", "block");
	
	dt.items.add(file);
		
	$('#mapImg')[0].files = dt.files;
	
	// 이미지 미리보기
	preview.onload = function(e){
		$("#pre_map").attr("src", e.target.result);
		$("#pre_map").css("display", "block")
	}
	preview.readAsDataURL(f.files[0]);	
}

// 캠핑장 이미지
camp.campImg = function(f){
	let files = f.files;
	let arr = Array.prototype.slice.call(files);
	if(arr.length != 5){
		alert("5장의 사진을 올려주세요!");
		return;
	} 
	let dt = new DataTransfer();
	
	$(".preview_photo").html("");
	arr.forEach(function(f){
	    let fileName = f.name;
	    if(fileName.length > 15){
	      fileName = fileName.substring(0,15)+"..."; //파일명이 길면 파일명...으로 처리
	    }
	    let size = parseInt(f.size/(1024));

	    let str = '<div style="display: inline-flex;"><li>';
    	let reader = new FileReader(); 
    	reader.onload = function (e) { 
	        str += '<img src="'+e.target.result+'" title="'+f.name+'" width=450 height=200 />';
			str += '<span>'+fileName+' ('+size+'KB)</span><br>';
	        str += '</li></div>';
	        $(str).appendTo('.preview_photo');
      	} 
      	reader.readAsDataURL(f);
	});
	
	for(file of arr){
		dt.items.add(file);		
	}
	
	$('#campImg')[0].files = dt.files;
}

