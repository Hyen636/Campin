/**
 * 예약 현황 조회
 */
let partnerReservation = {};
let itemCode = $("input[name=itemCode]").val();
let reservationColor = ['#f6c9cc', '#a8c0c0', '#FEBF36', '#FF9288', '#64C5A0', '#acc7bf', '#5e5f67', '#c37070', '#eae160', '#bf7aa3', '#d7d967'];
let colorCnt = 0;
//날짜 정보 가져오기
let date = new Date();
let utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; 
let kstGap = 9 * 60 * 60 * 1000;
let today = new Date(utc + kstGap);

$(function(){
	let link = document.location.href;
	// 달력 출력하는 페이지에서만 출력
	if(link == "http://localhost:8282/partner_reservation_list"){
		partnerReservation.calendar(); 	// 달력 생성		
	} else {
		let dateStr = $(".dateStr").text();	// 입실날
		let year = date.getFullYear();
		let month = date.getMonth()+1;
		let day = date.getDate();
		if(month < 10) month = '0' + month;
		if(day < 10) day = '0' + day;
		let nowDate = year+"-"+month+"-"+day;
		// 입실날과 현재 날짜 비교하여 예약 취소, 승인 버튼 없애기
		if(nowDate > dateStr) $(".btn_confirm").css("display", "none");
		if(nowDate >= dateStr) $(".btn_cancel").css("display", "none");
	}
})

function backColor(site){			// 같은 사이트 이름의 색상 변경
	if(colorCnt == reservationColor.length) colorCnt = 0;
	$(site).css("background", reservationColor[colorCnt]);
}

$(".find_date").on("change", function(){	// 시작날짜보다 끝날짜가 더 작으면 바꿔주기
	let start = $("input[name=start]");
	let end = $("input[name=end]");
	let temp;
	if($(end).val() != ""){
		if($(start).val() > $(end).val()){
			temp = $(start).val();
			$(start).val($(end).val())
			$(end).val(temp)
		}		
	}
})

function reservationCalendar(itemCode, dateStr){	// 달력에 예약 내역 출력
	let param = "itemCode="+itemCode+"&dateStr="+dateStr;
	colorCnt = 0;
	
  	$.ajax({	
		data : param,
		url : "partner_order_calendar",
		type : "post",
		cache : false,
		success : function(orderList){
			let day = document.getElementsByClassName("current");
			// 달력의 날짜를 가져와 비교 입실날짜, 퇴실날짜 비교
			for(let key in orderList){		 // 예약 리스트 for문
				for(let i = 0; i < day.length; i++){ // 달력의 날짜 for문
					let date = $(day[i]).attr("class").split(" ")[2];
					if(orderList[key].dateStr == date){			// 달력에 입실일이 포함되어 있으면
						for(let j = i; j < day.length; j++){	// 입실일부터 달력 끝까지
							let dateEnd = $(day[j]).attr("class").split(" ")[2]
							if(orderList[key].dateEnd == dateEnd) break; // 퇴실일과 같으면 break
							let siteName = orderList[key].siteName;
							$(day[j]).children(".schedule").append(`
								<div class="site ${siteName}">${siteName}</div>
							`)
							// 같은 사이트 이름의 배경색 같게
							let site = $(day[j]).children(".schedule").children("."+siteName);
							backColor(site);		
						}
						colorCnt++;
						break;
					} else if(orderList[key].dateEnd == date){	// 달력에 퇴실일이 포함되어 있으면
						for(let j = 0; j < day.length; j++){	// 입실일부터 달력 끝까지
							let dateEnd = $(day[j]).attr("class").split(" ")[2]
							if(orderList[key].dateEnd == dateEnd) break; // 퇴실일과 같으면 break
							let siteName = orderList[key].siteName;
							$(day[j]).children(".schedule").append(`
								<div class="site ${siteName}">${siteName}</div>
							`)
							// 같은 사이트 이름의 배경색 같게
							let site = $(day[j]).children(".schedule").children("."+siteName);
							backColor(site);		
						}
						colorCnt++;
						break;
					}
				}
			}		
		}
	})
	
}

// 달력 내의 날짜 클릭시 or 달력 이동 시 예약 내역 리스트 출력 
partnerReservation.showList = function(startDay, endDay){
	$("input[name=start]").val(startDay);
	if(endDay == null){
		$("input[name=end]").val(startDay);	
	} else{
		$("input[name=end]").val(endDay);		
	}
	$("input[name=findStr]").val("");
	
	let param = $("#frm_reservation_list").serialize();
	$(".temp_list").load("partner_show_reservation_list", param);
}
// 엔터 눌렀을 때 검색
$("input[name=findStr]").on("keyup", function(e){
	if(e.keyCode == 13){
		partnerReservation.search();
	}
})
// 검색 버튼 눌러서 검색 
partnerReservation.search = function(){
	if($("input[name=start]").val() == "" || $("input[name=end]").val() == ""){
		alert("날짜를 입력해주세요.");
		return;
	}
	let param = $("#frm_reservation_list").serialize();
	$(".temp_list").load("partner_show_reservation_list", param);
}

// 리스트 페이지 이동
partnerReservation.movePage = function(nowPage){
	$("input[name=nowPage]").val(nowPage);
	let param = $("#frm_reservation_list").serialize();
	$(".temp_list").load("partner_show_reservation_list", param);
}

// 상세보기
partnerReservation.detail = function(item){
	let orderCode = $(item).children(".no").text();
	$("input[name=orderCode]").val(orderCode);
	let frm = document.getElementById("frm_reservation_list");
	frm.action = "partner_reservation_detail";
	frm.submit();
}

// 달력 출력
partnerReservation.calendar = function() {
	let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
	let currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
	let currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
	let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일
	
	$(".cal_header").html("<a href='javascript:;' class='prev'>prev</a>"
					+ "<div class='year-month'></div>"
					+ "<a href='javascript:;' class='next'>next</a>");
	
	$(".days").html("<div class='day week'>일</div>" + 
		            "<div class='day week'>월</div>" +
		            "<div class='day week'>화</div>" +
		            "<div class='day week'>수</div>" +
		            "<div class='day week'>목</div>" +
		            "<div class='day week'>금</div>" +
		            "<div class='day week'>토</div>")
	rendercalendar(thisMonth);
	
	function rendercalendar(thisMonth) {
		currentYear = thisMonth.getFullYear();
	    currentMonth = thisMonth.getMonth();
		//if(currentMonth < 10) "0"+currentMonth;
	    currentDate = thisMonth.getDate();
	    var startDay = new Date(currentYear, currentMonth, 0); //저번달 날짜
	    var prevDate = startDay.getDate();//저번달 마지막날 날짜
	    var prevDay = startDay.getDay(); //저번달 마지막 요일
	
	    var middleDay = new Date(currentYear, currentMonth + 1, 0);
	    var middleDate = middleDay.getDate(); //이번달 마지막날 날짜
	    var middleDay = middleDay.getDay(); //이번달 마지막요일
		
		var endDay = new Date(currentYear, currentMonth + 2, 0);
	    var nextDate = endDay.getDate(); //다음달 마지막날 날짜
	    var nextDay = endDay.getDay(); //다음달 마지막요일
	    //연, 월 출력
	    $('.year-month').html("<span class='month'>" + currentYear + '.' + (currentMonth + 1) + "</span>");
		$(".calendar").css("display", "block");
		$(".date1").html("");
		$(".date2").html("");	// 계속 눌러도 늘어나지 않게 초기화
	    //이번달
	    if(prevDay != 6) {
	 		for(var i = prevDate - prevDay; i <= prevDate; i++) {
	 			$('.date1').append('<div class="day prev disable"><span class="nal">' + i  + '</span></div>');
	   		}
	  	}
		(currentMonth < 9)? currentMonth = "0"+(currentMonth+1) : currentMonth = currentMonth+1
	    
		for (var i = 1; i <= middleDate; i++) {			
			$('.date1').append("<div class='day current "+ currentYear+"-"+(currentMonth)+"-"+((i<10)?i="0"+i:i)+"'"+
								 "onclick=partnerReservation.showList("+"'"+currentYear+'-'+(currentMonth)+'-'+i+"'"+")>"
									+"<span class='nal'>" + ((i<10)?i=i%10:i) + "</span>"
									+"<div class='schedule'></div>"+
								"</div>");
	    }
	    for (var i = 1; i <7 - middleDay ; i++) {
	        $('.date1').append('<div class="day disable"><span class="nal">' + i + '</span></div>');
	    }

		//달력에 예약내역, 리스트 출력
		dateStr = currentYear+"-"+currentMonth;
		reservationCalendar(itemCode, dateStr);
		partnerReservation.showList(dateStr+"-01", dateStr+"-"+middleDate);
	}
	
	if (today.getMonth() == currentMonth) {
      todayDate = today.getDate();
      var currentMonthDate = document.querySelectorAll('.date .current');
      currentMonthDate[todayDate - 1].classList.add('today');
    }

	// 이전달로 이동
	$('.prev').on('click', function () {
		(currentMonth < 10) ? currentMonth = currentMonth%10 : currentMonth;
		thisMonth = new Date(currentYear, currentMonth - 2, 1);
		rendercalendar(thisMonth);
	});
	
	// 다음달로 이동
	$('.next').on('click', function () {
		(currentMonth < 10) ? currentMonth = currentMonth%10 : currentMonth;
		thisMonth = new Date(currentYear, currentMonth, 1);
		rendercalendar(thisMonth);
	});		
}

// 예약 승인
partnerReservation.confirm = function(){
	$("input[name=state]").val("예약완료");
	let param = $("#frm_partner_reservation_detail").serialize();

	$.ajax({
		data : param,
		url : "partner_reservation_confirm",
		type : "post",
		cache : false,
		success : function(result){
			if(result) alert("예약이 승인되었습니다.")
			else alert("예약 승인 중 오류가 발생했습니다.")
			
			let frm = document.getElementById("frm_partner_main");
			frm.action = "partner_reservation_list";
			frm.submit();
		}
	})
}
// 예약 취소
partnerReservation.cancel = function(){
	$("input[name=state]").val("예약취소");
	let param = $("#frm_partner_reservation_detail").serialize();
	$.ajax({	
		data : param,
		url : "partner_reservation_cancel",
		type : "post",
		cache : false,
		success : function(result){
			if(result) alert("예약이 취소되었습니다.")
			else alert("예약 승인 중 오류가 발생했습니다.")
			
			let frm = document.getElementById("frm_partner_main");
			frm.action = "partner_reservation_list";
			frm.submit();
		}
	})
}
