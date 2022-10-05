let main = {}

main.calendarCnt = 0;
main.inYear;	
main.outYear;
main.inMonth; // check-in에 들어갈 달
main.inDay;
main.outMonth; // check-out에 들어갈 달
main.outDay;

main.adultCount = 0;	// 인원 수 조절
main.childCount = 0;


$(function(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();
	month += 1;
	if (month <= 9){
	    month = "0" + month;
	}
	var day = date.getDate();
	if (day <= 9){
	    day = "0" + day;
	}
	//var today = year + '-' + month + '-' + day;
	$(".check_in").val(year+"년 "+month+"월 "+day+"일");
	$("#checkIn").val(year + "-" + month + "-" + day);
	
	var tomorrow = new Date(date.setDate(date.getDate() + 1));
	var year2 = tomorrow.getFullYear();
	var month2 = tomorrow.getMonth();
	month2 += 1;
	if (month2 <= 9){
	    month2 = "0" + month2;
	}
	var day2 = tomorrow.getDate();
	if (day2 <= 9){
	    day2 = "0" + day2;
	}
	//var tomorrow2 = year2 + '-' + month2 + '-' + day2;
	$(".check_out").val(year2+"년 "+month2+"월 "+day2+"일")
	$("#checkOut").val(year + "-" + month2 + "-" + day2);
})

// 지도
main.region = function(){
	$(".regions").slideDown(600);
}
$('body').on("click", function(e){
    if(!$('.region_toggle').has(e.target).length ) {
		$('.regions').slideUp(600);
	}
});
$(".region").on("click", function(){
	let region = $(this).text();
	$(".region").val(region);
})

// 인원
main.headCount = function(){
	$(".head_count").slideDown(600);
}

$('body').on("click", function(e){
    if(!$('.head_count_toggle').has(e.target).length ) {
		$('.head_count').slideUp(600);
	}
});

// 인원수 조절
$(".adult_plus").on("click", function(){
	main.adultCount++;
	$(".adult_count").text(main.adultCount);
	$(".adult_minus").css("cursor", "pointer");
	main.headCompute();
	//희찬
	$('#adultCount').val(main.adultCount);
	
})

$(".adult_minus").on("click", function(){
	if(main.adultCount == 0){
		$(".adult_minus").css("cursor", "not-allowed");
	} else {
		main.adultCount--;
		main.headCompute();
		//희찬
		$('#adultCount').val(main.adultCount);
	}
	$(".adult_count").text(main.adultCount);
})

$(".child_plus").on("click", function(){
	main.childCount++;
	$(".child_count").text(main.childCount);
	$(".child_minus").css("cursor", "pointer");
	main.headCompute();
	//희찬
	$('#childCount').val(main.childCount);
})

$(".child_minus").on("click", function(){
	if(main.childCount == 0){
		$(".child_minus").css("cursor", "not-allowed");
	} else {
		main.childCount--;
		main.headCompute();
		//희찬
		$('#childCount').val(main.childCount);
	}
	$(".child_count").text(main.childCount);
})

main.headCompute = function(){
	let headCount = main.childCount + main.adultCount;
	if(headCount == 0) $(".headcount").val("");
	else $(".headcount").val("총 인원 " + headCount + "명");	
	
}

main.calendar = function() {
	//날짜 정보 가져오기
	let date = new Date();
	let utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; 
	let kstGap = 9 * 60 * 60 * 1000;
	let today = new Date(utc + kstGap);
	var todayDate = today.getDate();
	
	let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
	let currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
	let currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
	let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일
	
	$(".cal_header").html("<a href='javascript:;' class='prev'>prev</a>"
					+ "<div class='year-month'></div>"
					+ "<a href='javascript:;' class='next'>next</a>");
	
	$(".days").html("<div class='day'>Sun</div>" + 
		            "<div class='day'>Mon</div>" +
		            "<div class='day'>Tue</div>" +
		            "<div class='day'>Wed</div>" +
		            "<div class='day'>Thr</div>" +
		            "<div class='day'>Fri</div>" +
		            "<div class='day'>Sat</div>")
	
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
		main.thisDate = middleDate;
		main.thisMonth = currentMonth+1;
		
		var endDay = new Date(currentYear, currentMonth + 2, 0);
	    var nextDate = endDay.getDate(); //다음달 마지막날 날짜
	    var nextDay = endDay.getDay(); //다음달 마지막요일
	    //연, 월 출력
	    $('.year-month').html("<span class='month'>" + currentYear + '.' + (currentMonth + 1) + "</span>"
							+ "<span class='month'>" + ((currentMonth + 2)==13? currentYear+1 : currentYear) + 
							   '.' + ((currentMonth + 2)==13? 1 : (currentMonth + 2)) + "</span>");
		$(".calendar").css("display", "block");
		$(".date1").html("");
		$(".date2").html("");	// 계속 눌러도 늘어나지 않게 초기화
		
	    //왼쪽 달력
	    if(prevDay != 6) {
	 		for(var i = prevDate - prevDay; i <= prevDate; i++) {
	 			$('.date1').append('<div class="day disable">' + " " + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= middleDate; i++) {
			$('.date1').append("<div class='day current left "+ currentYear+(currentMonth+1)+i+"'"+
									"onclick=main.selectDate("+currentYear+','+(currentMonth+1)+','+i+",this"+")>" + i + "</div>");
	    }
	    for (var i = 1; i <7 - middleDay ; i++) {
	        $('.date1').append('<div class="day disable">' + " " + '</div>');
	    }
		
	
		// 오른쪽 달력
		if(middleDay != 6) {
	 		for(var i = middleDate - middleDay; i <= middleDate; i++) {
	 			$('.date2').append('<div class="day disable">' + " " + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= nextDate; i++) {
	    	$('.date2').append("<div class='day current right "+ currentYear+(currentMonth+2)+i+"'"+
									"onclick=main.selectDate("+currentYear+','+(currentMonth+2)+','+i+",this"+")>" + i + "</div>");  
		}
	    for (var i = 1; i <7 - nextDay ; i++) {
	        $('.date2').append('<div class="day disable">' + " " + '</div>');
	    }
		
		
		// 오늘보다 이전 날짜 disable
		if(today.getFullYear() > currentYear){	// 현재 연도보다 작을 때
			currentMonthDate = document.querySelectorAll('.date .current');// 현재 표시한 달력의 날짜 가져오기
			for(let i = 0; i < currentMonthDate.length; i++){
				currentMonthDate[i].classList.add('disable');
			}
		} else if(today.getFullYear() == currentYear){ // 같은 연도
			// 화면의 왼쪽 달력이 현재 월-2 보다 작을때 ex) 현재 7월이면 왼쪽 달력이 5월 이하
			if(today.getMonth() > currentMonth+1){	   
				currentMonthDate = document.querySelectorAll('.date .current');
				for(let i = 0; i < currentMonthDate.length; i++){
					currentMonthDate[i].classList.add('disable');
				}
			} else if(today.getMonth() == currentMonth+1) {	// 7월이면 왼쪽 달력이 6월
				currentMonthDate = document.querySelectorAll('.date .current');
				for(let i = 0; i < middleDate + today.getDate() - 1; i++){// 왼쪽 달력 + 어제까지 클릭불가
					currentMonthDate[i].classList.add('disable');
				}
			} else if(today.getMonth() == currentMonth){	// 이번달
				currentMonthDate = document.querySelectorAll('.date .current');
				for(let i = 0; i < today.getDate() - 1; i++){     // 어제까지 클릭불가
					currentMonthDate[i].classList.add('disable');
				}
			}
		}
		
		// 기존의 원 표시
		if(currentMonth+2 == main.inMonth){
		currentMonthDate = document.querySelectorAll('.date .current');
		currentMonthDate[middleDate+main.inDay-1].classList.add("circle");
		} else if(currentMonth+1 == main.inMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[main.inDay-1].classList.add("circle");
		} 
		
		if(currentMonth+2 == main.outMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[middleDate+main.outDay-1].classList.add("circle");
		} else if(currentMonth+1 == main.outMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[main.outDay-1].classList.add("circle");
		}
	
	
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

// input tag에 값 넣어주기
main.setText = function(check, year, month, day){
	//희찬
	var tempMonth;
	var tempDay;
	if(month < 10){
		tempMonth = "0" + month;
	}
	else{
		tempMonth = month;
	}
	if(day < 10){
		tempDay = "0" + day;
	}
	else{
		tempDay = day;
	}
	/************************/	
	if(check == 'in'){
		main.inYear = year;
		main.inMonth = month;
		main.inDay = day
		$(".check_in").val(main.inYear+"년 "+main.inMonth+"월 "+main.inDay+"일");
		//희찬
		$("#checkIn").val(year + "-" + tempMonth + "-" + tempDay);
	} else {
		main.outYear = year;
		main.outMonth = month;
		main.outDay = day;
		$(".check_out").val(main.outYear+"년 "+main.outMonth+"월 "+main.outDay+"일")
		//희찬
		$("#checkOut").val(year + "-" + tempMonth + "-" + tempDay);
	}
}

// 기존 원 삭제
main.removeCircle = function(target){
	currentDate = document.querySelectorAll('.date .current');	// 현재 달력의 날짜 모두 가져오기
	
	for(let i = 0; i < currentDate.length; i++){		
		if($(currentDate[i]).attr("class").split(" ")[3] == target){	// 타겟과 달력의 class의 날짜가 같으면 삭제
			currentDate[i].classList.remove("circle");
			break;
		}
	}
}

main.selectDate = function(selectedYear, selectedMonth, selectedDay, selectedCalendar){
	if($(selectedCalendar).hasClass("disable") == true){	// 현재보다 이전 날짜 클릭 시 X
		return;
	}
	
	main.calendarCnt++;
	
	if(main.calendarCnt == 1){// 달력 첫번째 클릭
		main.removeCircle(main.inYear+''+main.inMonth+''+main.inDay);
		main.setText("in", selectedYear, selectedMonth, selectedDay);
		$(selectedCalendar).addClass("circle");
	} else {
		if(main.inYear == selectedYear){		// 연도 같을 때
			if(main.inMonth < selectedMonth){	// 체크인 월보다 입력받은 월이 클 때
				main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
				main.setText("out", selectedYear, selectedMonth, selectedDay);				
				
				$(selectedCalendar).addClass("circle");
			} else if (main.inMonth == selectedMonth){// 체크인 월과 같을 때
				if(main.inDay < selectedDay){			// 체크인 날짜보다 클 때
					main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
					main.setText("out", selectedYear, selectedMonth, selectedDay);
					
					$(selectedCalendar).addClass("circle");
				} else if(main.inDay == selectedDay){	// 체크인 날짜와 같을 때
					main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
					main.setText("out", selectedYear, selectedMonth, selectedDay);
				} else {								// 체크인 날짜보다 작을 때
					main.removeCircle(main.inYear+''+main.inMonth+''+main.inDay);
					main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
					
					main.inDay = selectedDay;
					$(".check_out").val("");
					$(selectedCalendar).addClass("circle");
				}
			} else {	// 기존의 월보다 입력 받은 월이 작을 때
				main.removeCircle(main.inYear+''+main.inMonth+''+main.inDay);
				main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
				
				main.inMonth = selectedMonth;
				main.inDay = selectedDay;
				$(".check_out").val("");				
				$(selectedCalendar).addClass("circle");
			}
		} else {	// 입력받은 연도가 더 클 때
			main.removeCircle(main.outYear+''+main.outMonth+''+main.outDay);
			main.setText("out", selectedYear, selectedMonth, selectedDay);
			
			$(selectedCalendar).addClass("circle");
		}
		$(".check_in").val(main.inYear+"년 "+main.inMonth+"월 "+main.inDay+"일");
	}
	
}

// 달력
$('body').on("click", function(e){
    if(!$('.cal_toggle').has(e.target).length ) {
		$('.calendar').hide();
	}
});




//희찬
main.search_page = function(){
	var frm = document.getElementById('frm_index');
	if($('#checkIn').val() == 0 || $('#checkout').val() == 0){
		alert("예약 날짜를 선택해 주세요.");
	}else{
		frm.action = 'user_search';
		frm.submit();	
	}
}

main.tag = function(value){
	var frm = document.getElementById('frm_index');
	var tagTemp = document.getElementById('tagSearch');
	tagTemp.value = value;
	if($('#checkIn').val() == 0 || $('#checkout').val() == 0){
		alert("예약 날짜를 선택해 주세요.");
	}else{
		frm.action = 'user_search_IndexTagClick';
		frm.submit();	
	}
}