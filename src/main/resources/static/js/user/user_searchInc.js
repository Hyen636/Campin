/**
 * 
 */
 (search2=function(){
	search2.calendarCnt = 0;
	search2.inYear;	
	search2.outYear;
	search2.inMonth; // check-in에 들어갈 달
	search2.inDay;
	search2.outMonth; // check-out에 들어갈 달
	search2.outDay;
	search2.adultCount = 0;	// 인원 수 조절
	search2.childCount = 0;
	
	
	
	//ajax
	
	$.ajax({
		data : 'userCode='+$('#userCode2').val(),
		url : "user_search_findwishlist",
		type : "post",
		cache : false,
		success : function(list){
			var itemCodelist = $('.detailItemCode2');
			console.log("item : " + itemCodelist);
			for(var i = 0; i < itemCodelist.length; i++){
				console.log("item2 : " + itemCodelist[i]);
				for(var k = 0; k < list.length; k++){
					console.log("item3 : " + list[k]);
					if(itemCodelist[i].value == list[k]){
						let frm = $(itemCodelist[i]).parent()
						let heart = $(frm).children('.not_wish');
						 
						$(heart).attr("class", "wish");
						$(heart).attr("src", "./image/icon/wish.png");
					}
				}
			}
		}
	})
	
	
	//희찬
	//var link =  document.location.href;
	var frm = document.getElementById('frm');
	var frmState = document.getElementById('searchState2');
	if(frmState.value == "search_temp"){
		//마지막 체크 뿌려주는 부분
		let checkElement = document.getElementById('filterSave');
		let checkElementCnt = document.getElementById('filterSaveCnt');
		if(checkElement.value != ""){
			var array = checkElementCnt.value;			//체크한 갯수
			var splitTemp = new Array(array);			//체크한 갯수만큼 배열생성
			splitTemp = checkElement.value.split(',');
			for(let i = 0; i < array; i++){
				//항목을 찾아서 css 적용하기.
				$("input[name=filtercheck]").each(function(){
					if($(this).val() == splitTemp[i]){
						$(this).prop('checked', true);
					}
				})
			}
		}
			
		let checkElementTag = document.getElementById('tagSave');
		if(checkElementTag.value != ""){
			let tagList = document.getElementsByClassName('tag');
			for(let i = 0; i < tagList.length; i++){
				
				if(tagList[i].value == checkElementTag.value){
					//tagList[i].css({"border": "2px solid #f8ca37", "color": "#f8ca37"});
				}
				else{
					//tagList[i].style({"border": "2px solid #fff", "color": "#fff"});
					//tagList[i].css({"border": "2px solid #fff", "color": "#fff"});
				}
			}
		}
	}
})()
//let search2 = {}
// 달력 생성
/*
search2.calendarCnt = 0;
search2.inYear;	
search2.outYear;
search2.inMonth; // check-in에 들어갈 달
search2.inDay;
search2.outMonth; // check-out에 들어갈 달
search2.outDay;
search2.adultCount = 0;	// 인원 수 조절
search2.childCount = 0;
*/



// 상단 검색창 띄우기
/*
search2.showsearch2Btn = function(){
	let region = $(".input_region").val();
	let checkIn = $(".check_in").val();
	let checkOut = $(".check_out").val();
	let headCount = $(".input_head_count").val();
	
	if(region != "" && checkIn != "" && checkOut != "" && headCount != ""){
		$(".go").css("display", "inline-block");
	} else{
		$(".go").css("display", "none");
	}
}*/


	
	

// 필터
$('.btn_filter').on('click', function(){
	$('.filter').toggle();
});
$('.btn_filter_close').on('click', function(){
	$('.filter').toggle();
});





//filter check 부분
//@@@@@@@@@@@@@@@@@@@@@@@
$('input[type=checkbox][name=filtercheck]').change(function(){
	var frm = document.getElementById('frm');
	let element = document.getElementById('tagSave');
	filterCreate();
	tagCreate(element.value);
	
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOption_filter', param);
	
	//frm.action='user_search2Option';
	//frm.submit();
});

//tag click 부분
search2.tagBtn = function(tagBtn){
	let tagValue = tagBtn.value;
	
	filterCreate();
	tagCreate(tagValue);
	
	//var frm = document.getElementById('frm');
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOption_tag', param);
	//frm.action='user_searchOption';
	//frm.submit();
}


function tagCreate(tagValue){
	let element = document.getElementById('tagSearch');
	if(element != null){
		element.parentNode.removeChild(element);
	}
	if(tagValue != null){
		$('.text_hidden_area').append(
			"<input type='hidden' name='tagSearch' id='tagSearch'" +
			" value='" + tagValue + "' >"
	)
	}
}

function filterCreate(){
	var temp = "";
	var tempCnt = 0;
	$("input[name=filtercheck]:checked").each(function(){
		if(temp == ""){
			temp = $(this).val();
			tempCnt++;
		}else{
			temp = temp + "," + $(this).val();
			tempCnt++;
		}
	})
	
	let element = document.getElementById('checkfilter');
	let element2 = document.getElementById('checkfilterCnt');
	if(element != null && element2 != null){
		element.parentNode.removeChild(element2);
		element.parentNode.removeChild(element);
		
	}
	if(temp != ""){
		$('.text_hidden_area').append(
			"<input type='hidden' name='checkfilter' id='checkfilter'" +
			" value='" + temp + "' >" + 
			"<input type='hidden' name='checkfilterCnt' id='checkfilterCnt'" +
			" value='" + tempCnt + "' />"
		)
	}
	
	//Str user_search2_temp에 저장
	let elementSave = document.getElementById('filterSave').value;
	let elementSaveCnt = document.getElementById('filterSaveCnt').value;
	elementSave = temp;
	elementSaveCnt = tempCnt;
}


//바로가기 아이콘
search2.ShortCutCategory = function(category){
	let categoryValue = category.alt;
	var frm = document.getElementById('frm');
	filterCheckCreate(categoryValue);
	tagCreate("");
	
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOption_filter', param);
	
	//frm.action='user_search2Option';
	//frm.submit();
}

function filterCheckCreate(categoryValue){
	var temp = categoryValue;
	var tempCnt = 1;
	
	
	let element = document.getElementById('checkfilter');
	let element2 = document.getElementById('checkfilterCnt');
	if(element != null && element2 != null){
		element.parentNode.removeChild(element2);
		element.parentNode.removeChild(element);
		
	}
	if(temp != ""){
		$('#.text_hidden_area').append(
			"<input type='hidden' name='checkfilter' id='checkfilter'" +
			" value='" + temp + "' >" + 
			"<input type='hidden' name='checkfilterCnt' id='checkfilterCnt'" +
			" value='" + tempCnt + "' />"
		)
	}
	
	//Str user_search2_temp에 저장
	let elementSave = document.getElementById('filterSave').value;
	let elementSaveCnt = document.getElementById('filterSaveCnt').value;
	elementSave = temp;
	elementSaveCnt = tempCnt;
}

// 찜
search2.wish = function(e){
	let frm = $(e).parent();	//img의 부모 form
	
	if($(e).attr("class") == "not_wish"){
		if($('#userId').val() != ""){
			$(e).attr("class", "wish");
			$(e).attr("src", "./image/icon/wish.png");
			alert("찜 목록에 추가되었습니다.");
			
			//로그인을 하고 난 뒤 해당 유저코드를 가져와 찜 list Add
			let param = $(frm).serialize();
			$.post("user_search_wishlist_check_add",param);
		}
		else{
			alert("로그인 후에 이용 가능한 컨텐츠입니다. \n로그인 화면으로 이동합니다.");
			location.href = 'user_login'
		}
	} else{
		if($('#userId').val() != ""){
			$(e).attr("class", "not_wish");
			$(e).attr("src", "./image/icon/not_wish.png");
			alert("찜 목록에서 삭제되었습니다.");
			
			let param = $(frm).serialize();
			$.post("user_search_wishlist_check_delete",param);
		}
	}
}


//상세페이지 이동
search2.search_detail_page = function(frm){
	//var frm = document.getElementById('frm');
	//let param = frm.serialize();
	//$('#inc_div').load('user_searchFirst', param);
		
	frm.action='user_search_detail_page';
	frm.submit();
}

