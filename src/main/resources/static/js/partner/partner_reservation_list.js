/**
 * 예약 현황 조회
 */
 
 $(function(){
	partner_reservation_list.calendar();
})
 let partner_reservation_list = {};
 
partner_reservation_list.calendarCnt = 0;
partner_reservation_list.year1;	
partner_reservation_list.year2;
partner_reservation_list.month1; // check-in에 들어갈 달, 월
partner_reservation_list.day1;
partner_reservation_list.month2; // check-out에 들어갈 달, 월
partner_reservation_list.day2;
partner_reservation_list.selected1	// 달력에 표시해줄 원
partner_reservation_list.selected2

partner_reservation_list.calendar = function() {
	//날짜 정보 가져오기
	let date = new Date();
	let utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; 
	let kstGap = 9 * 60 * 60 * 1000;
	let today = new Date(utc + kstGap);
	
	let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
	let currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
	let currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
	let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일
	
	$(".cal_header").html("<a href='javascript:;' class='prev'>prev</a>"
					+ "<div class='year-month'></div>"
					+ "<a href='javascript:;' class='next'>next</a>");
	
	$(".days").html("<div class='day'>일</div>" + 
		            "<div class='day'>월</div>" +
		            "<div class='day'>화</div>" +
		            "<div class='day'>수</div>" +
		            "<div class='day'>목</div>" +
		            "<div class='day'>금</div>" +
		            "<div class='day'>토</div>")
	rendercalendar(thisMonth);
	
	function rendercalendar(thisMonth) {
		currentYear = thisMonth.getFullYear();
	    currentMonth = thisMonth.getMonth();
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
	    $('.year-month').html("<span class='month'>" + currentYear + '.' + (currentMonth + 1) + "</span>"
							+ "<span class='month'>" + currentYear + '.' + (currentMonth + 2) + "</span>");
		$(".calendar").css("display", "block");
		$(".date1").html("");
		$(".date2").html("");	// 계속 눌러도 늘어나지 않게 초기화
		
	    //이번달
	    if(prevDay != 6) {
	 		for(var i = prevDate - prevDay; i <= prevDate; i++) {
	 			$('.date1').append('<div class="day prev disable">' + i + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= middleDate; i++) {
			$('.date1').append("<div class='day current '"+
									"onclick=partner_reservation_list.selectDate("+currentYear+','+(currentMonth+1)+','+i+",this"+")>" + i + "</div>");
	    }
	    for (var i = 1; i <7 - middleDay ; i++) {
	        $('.date1').append('<div class="day disable">' + i + '</div>');
	    }
		
		// 다음달
		if(middleDay != 6) {
	 		for(var i = middleDate - middleDay; i <= middleDate; i++) {
	 			$('.date2').append('<div class="day prev disable">' + i + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= nextDate; i++) {
	    	$('.date2').append("<div class='day current '"+
									"onclick=partner_reservation_list.selectDate("+currentYear+','+(currentMonth+2)+','+i+",this"+")>" + i + "</div>");  
		}
	    for (var i = 1; i <7 - nextDay ; i++) {
	        $('.date2').append('<div class="day disable">' + i + '</div>');
	    }
	}
	
	if (today.getMonth() == currentMonth) {
      todayDate = today.getDate();
      var currentMonthDate = document.querySelectorAll('.date .current');
      currentMonthDate[todayDate - 1].classList.add('today');
    }

	// 이전달로 이동
	$('.prev').on('click', function () {
		thisMonth = new Date(currentYear, currentMonth - 1, 1);
		rendercalendar(thisMonth);
	});
	
	// 다음달로 이동
	$('.next').on('click', function () {
		thisMonth = new Date(currentYear, currentMonth + 1, 1);
		rendercalendar(thisMonth);
	});	

}

partner_reservation_list.selectDate = function(selectedYear, selectedMonth, selectedDay, circle){
	partner_reservation_list.calendarCnt++;
		
	if(partner_reservation_list.calendarCnt % 2 == 1){	// 달력 홀수번째 클릭
		partner_reservation_list.year1 = selectedYear;
		partner_reservation_list.month1 = selectedMonth;
		partner_reservation_list.day1 = selectedDay;
		
		$(".check_in").val(partner_reservation_list.year1+"년 "+partner_reservation_list.month1+"월 "+partner_reservation_list.day1+"일");	
		$(partner_reservation_list.selected1).css({"background": "",
					   "border-radius": "none",
					   "color": "#000"});
		$(circle).css({"background": "#f8ca37",
					   "border-radius": "30px",
					   "color": "#fff"});
		partner_reservation_list.selected1 = circle;
	}

	if(partner_reservation_list.calendarCnt % 2 == 0){	// 달력 짝수번째 클릭
		if(partner_reservation_list.year1 > selectedYear){ // 입력 받은 연도가 기존 년도보다 작을 때
			partner_reservation_list.year2 = partner_reservation_list.year1;
			partner_reservation_list.month2 = partner_reservation_list.month1;
			partner_reservation_list.day2 = partner_reservation_list.day1;
			partner_reservation_list.year1 = selectedYear;
			partner_reservation_list.month1 = selectedMonth;
			partner_reservation_list.day1 = selectedDay;
		} else if(partner_reservation_list.year1 == selectedYear){	// 연도 같을 때
			partner_reservation_list.year2 = selectedYear
			if(partner_reservation_list.month1 < selectedMonth){	// 기존의 월보다 입력받은 월이 클 때
				partner_reservation_list.month2 =  selectedMonth;
				partner_reservation_list.day2 = selectedDay;
			} else if (partner_reservation_list.month1 == selectedMonth){	// 월이 같을 때
				partner_reservation_list.month2 = selectedMonth
				if(partner_reservation_list.day1 < selectedDay){
					partner_reservation_list.day2 = selectedDay;
				} else {
					partner_reservation_list.day2 = partner_reservation_list.day1;
					partner_reservation_list.day1 = selectedDay;
				}
			} else{	// 기존의 월보다 입력 받은 월이 작을 때
				partner_reservation_list.month2 = partner_reservation_list.month1;
				partner_reservation_list.day2 = partner_reservation_list.day1;
				partner_reservation_list.month1 = selectedMonth;
				partner_reservation_list.day1 = selectedDay;
			}
		} else {	// 입력받은 연도가 더 클 때
			partner_reservation_list.year2 = selectedYear;
			partner_reservation_list.month2 = selectedMonth;
			partner_reservation_list.day2 = selectedDay;
		}
		
		$(partner_reservation_list.selected2).css({"background": "",
					   "border-radius": "none",
					   "color": "#000"});
		$(circle).css({"background": "#f8ca37",
					   "border-radius": "30px",
					   "color": "#fff"});
		
		partner_reservation_list.selected2 = circle;
		
		$(".check_in").val(partner_reservation_list.year1+"년 "+partner_reservation_list.month1+"월 "+partner_reservation_list.day1+"일");		
		$(".check_out").val(partner_reservation_list.year2+"년 "+partner_reservation_list.month2+"월 "+partner_reservation_list.day2+"일");
	}
}

// 달력 지우기
//$('body').click(function(e){
//    if(!$('.cal_toggle').has(e.target).length ) {
//		$('.calendar').hide();
//	}
//});

//$(".search_icon").on("click", function(){
//	location.href="./user/search.jsp"
//})