/**
 * 
 */
var state=["등록요청","요청반려","등록완료","수정요청","수정완료"];
var review=["블랙요청","블랙반려","블랙승인"];

var del=["탈퇴요청","탈퇴승인"];
var board=["답변대기","답변완료"];
var all=["요청대기","요청반려","요청완료"];

(
	request=function(){
		var date = new Date();
		var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
		var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
		request.date=function(date){
			var year =date.getFullYear();
			var month=(1+date.getMonth());
			month = month>=10?month:'0'+month;
		
			var day=date.getDate();
			day=day>=10?day:'0'+day;
			return year+"-"+month+"-"+day;
		}
		let frm = document.getElementById("frm_request_filter");
		var std=request.date(firstDay);
		var end=request.date(lastDay);
		if(frm.str.value==""){frm.str.value=std};
		if(frm.end.value==""){frm.end.value=end};
		if(frm.categoryR.value!=""){
			$(".category").val(frm.categoryR.value).prop("selected,ture");
		}
		if(frm.stateR.value!=""){
			console.log("category ",frm.categoryR.value);
			if(frm.categoryR.value==""){
				var c="전체"
			}else{
				c=frm.categoryR.value;	
			}
			console.log("category ",frm.categoryR.value);
			var target=document.getElementById("state");
			console.log(target);
			if(c=="회원")var s=state;
			else if(c=="전체")s=all;
			else if(c=="탈퇴")s=del;
			else if(c=="캠핑장")s=state;
			else if(c=="사이트")s=state;
			else if(c=="리뷰")s=review;
			else if(c=="일반문의")s=board;
			
			target.options.length=0;
			
			for(var x in s){
				var opt=document.createElement("option");
				opt.value=s[x];
				opt.innerHTML=s[x];
				target.appendChild(opt);
			}
					
			
			console.log(frm.stateR.value);
			$(".state").val(frm.stateR.value).prop("selected,ture");
		}
	}
)()



request.write = function(){
	let frm = document.getElementById("frm_request_filter");
	frm.action = "partner_request_input";
	frm.submit();
}


request.movePage = function(page){
	let frm = document.getElementById("frm_request_filter");
	frm.nowPage.value = page;
	console.log(page);
	frm.action='partner_request_list';
	frm.submit();
}


request.find = function(){
	let frm = document.getElementById("frm_request_filter");
	let param=$("#frm_request_filter").serialize();
	console.log("==request.find category : ", frm.category.value);
	console.log("==request.find state : ", frm.state.value);
	console.log("==request.find str : ", frm.str.value);
	console.log("==request.find end : ", frm.end.value);
	console.log("==request.find findStr : ", frm.findStr.value);
	frm.nowPage.value=1;
	frm.action='partner_request_list';
	frm.submit();
	
	
	  
}

request.view = function(max, category, state, sno, title){
	var num=max.substring(1);	
	console.log("==request.view num : ", num);
	let frm = document.getElementById("frm_request_filter");
	 switch(category){
      	//회원
	    case"회원":
        var s =title.substring(0,1); //첫글자체크
        console.log("s : ", s);
        if(s == ("R")){
           frm.hisUserSno.value=num;
           frm.action="partner_request_join_view";
        }else if(s == ("M")){
           frm.hisUserSno.value=num;
           frm.action="partner_request_modify_view";
        }else{
           alert("다시 확인하여 클릭해주세요.");
        }
        break;
		//캠핑장
		case"캠핑장":
			frm.hisItemSno.value=num;
			frm.stateR.value=state;
			frm.sno.value=sno;
			frm.action='partner_camp_view';
		break;
		//사이트
		case"사이트":
			frm.hisDetailSno.value=num;
			frm.stateR.value=state;
			frm.sno.value=sno;
			frm.action='partner_site_view';
		break;
		//탈퇴
		case"탈퇴":
			console.log("==request 탈퇴 : ", 1);
			frm.sno.value=num;
			frm.categoryR.value=category;
			frm.hisDetailSno.value=num;
			frm.hisUserSno.value=num;
			frm.hisItemSno.value=num;
			frm.action='partner_request_view';
		break;
		//일반문의
		case"일반문의":
			console.log("==request 일반 : ", 1);
			frm.sno.value=num;
			frm.categoryR.value=category;
			frm.hisDetailSno.value=num;
			frm.hisUserSno.value=num;
			frm.hisItemSno.value=num;
			frm.action='partner_request_view';
		break;
		//리뷰
		case"리뷰":
			console.log("==request 리뷰 : ", 1);
			frm.sno.value=num;
			frm.categoryR.value=category;
			frm.hisDetailSno.value=num;
			frm.hisUserSno.value=num;
			frm.hisItemSno.value=num;
			frm.action='partner_request_view';
		break;
		
	}
	frm.submit();
	
}



//드롭다운 세분화
request.category=function(c){
	console.log(c);
	var target=document.getElementById("state");
	console.log(target);
	if(c=="회원")var s=state;
	else if(c=="전체")s=all;
	else if(c=="탈퇴")s=del;
	else if(c=="캠핑장")s=state;
	else if(c=="사이트")s=state;
	else if(c=="리뷰")s=review;
	else if(c=="일반문의")s=board;
	
	target.options.length=0;
	
	for(var x in s){
		var opt=document.createElement("option");
		opt.value=s[x];
		opt.innerHTML=s[x];
		target.appendChild(opt);
	}
	
	request.find();
}





