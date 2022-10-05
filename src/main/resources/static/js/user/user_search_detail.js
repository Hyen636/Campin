/**
 * 검색 상세 페이지
 */
 let user_search_detail = {};
	
$(function(){
	var latitude = $('#latitude').val();
	var longitude = $('#longitude').val();
	var address = $('#address1').val();
	var iName = $('#iName').val();
	
	//달력
	user_search_detail.calendar_detail();
	
	
	//지도
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	   center : new window.kakao.maps.LatLng(longitude, latitude), // 지도의 중심좌표
	   level : 3 // 지도의 확대 레벨
	};
	//지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new window.kakao.maps.Map(mapContainer, mapOption);
	// 지도에 마커 표시
	var marker = new window.kakao.maps.Marker({
	    map: map, 
	    position: new window.kakao.maps.LatLng(longitude, latitude)
	});
	// 마커가 지도 위에 표시되도록 설정
	marker.setMap(map);
	// info윈도우
	var iwContent = '<div style="padding:5px;">' +
					iName + ' <br>' +
					address + '</div>',
    				iwPosition = new window.kakao.maps.LatLng(longitude, latitude); //인포윈도우 표시 위치
	// info윈도우 생성
	var infowindow = new window.kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	// 마커 위에 info윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 
	
		
	//찜 상태
	console.log("$('#userCode3').val() : " + $('#userCode3').val());
	$.ajax({
		data : 'userCode='+$('#userCode3').val(),
		url : "user_search_searchwishlist",
		type : "post",
		cache : false,
		success : function(b){
			if(b){
				let mid = $('.desc_category');
				let heart = $(mid).children('.not_wish');
				$(heart).attr("class", "wish");
				$(heart).attr("src", "./image/icon/heart.png");
			}
		}
	})
	
	
			
	let param = $("#frm_user_rough_reservation").serialize();
	
	//사이트
	$('.site_detail').load('user_search_detail_site_list', param);
	//리뷰
	$('.reviews').load('user_search_detail_review_list', param);
	//문의
	$('.qna').load('user_search_detail_qna_list', param);
	//fixed
	$('.sticky_box').load('user_search_detail_fixed_reservation', param);
	
	
	
})
	

 
user_search_detail.calendarCnt = 0;
user_search_detail.year1;	
user_search_detail.year2;
user_search_detail.month1; // check-in에 들어갈 달, 월
user_search_detail.day1;
user_search_detail.month2; // check-out에 들어갈 달, 월
user_search_detail.day2;
//희찬
user_search_detail.adultCount = 0;	// 인원 수 조절
user_search_detail.childCount = 0;
//user_search_detail.selected1	// 달력에 표시해줄 원
//user_search_detail.selected2


user_search_detail.calendar_detail = function() {
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
	
	$(".cal_header_detail").html("<a href='javascript:;' class='prev_detail'>prev</a>"
					+ "<div class='year-month_detail'></div>"
					+ "<a href='javascript:;' class='next_detail'>next</a>");
	
	$(".days_detail").html("<div class='day_detail'>Sun</div>" + 
		            "<div class='day_detail'>Mon</div>" +
		            "<div class='day_detail'>Tue</div>" +
		            "<div class='day_detail'>Wed</div>" +
		            "<div class='day_detail'>Thr</div>" +
		            "<div class='day_detail'>Fri</div>" +
		            "<div class='day_detail'>Sat</div>")
	
	rendercalendar2(thisMonth);
	function rendercalendar2(thisMonth) {
		currentYear = thisMonth.getFullYear();
	    currentMonth = thisMonth.getMonth();
	    currentDate = thisMonth.getDate();

	    var startDay = new Date(currentYear, currentMonth, 0); //저번달 날짜
	    var prevDate = startDay.getDate();//저번달 마지막날 날짜
	    var prevDay = startDay.getDay(); //저번달 마지막 요일
	
	    var middleDay = new Date(currentYear, currentMonth + 1, 0);
	    var middleDate = middleDay.getDate(); //이번달 마지막날 날짜
	    var middleDay = middleDay.getDay(); //이번달 마지막요일
		user_search_detail.thisDate = middleDate;
		user_search_detail.thisMonth = currentMonth+1;
		
		var endDay = new Date(currentYear, currentMonth + 2, 0);
	    var nextDate = endDay.getDate(); //다음달 마지막날 날짜
	    var nextDay = endDay.getDay(); //다음달 마지막요일
	    //연, 월 출력
	    $('.year-month_detail').html("<span class='month_detail'>" + currentYear + '.' + (currentMonth + 1) + "</span>"
							+ "<span class='month_detail'>" + ((currentMonth + 2)==13? currentYear+1 : currentYear) + 
							   '.' + ((currentMonth + 2)==13? 1 : (currentMonth + 2)) + "</span>");
		$(".calendar_detail").css("display", "block");
		$(".date1_detail").html("");
		$(".date2_detail").html("");	// 계속 눌러도 늘어나지 않게 초기화
		
	    //왼쪽 달력
	    if(prevDay != 6) {
	 		for(var i = prevDate - prevDay; i <= prevDate; i++) {
	 			$('.date1_detail').append('<div class="day_detail disable">' + " " + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= middleDate; i++) {
			$('.date1_detail').append("<div class='day_detail current_detail left "+ currentYear+(currentMonth+1)+i+"'"+
									"onclick=user_search_detail.selectDate("+currentYear+','+(currentMonth+1)+','+i+",this"+")>" + i + "</div>");
	    }
	    for (var i = 1; i <7 - middleDay ; i++) {
	        $('.date1_detail').append('<div class="day_detail disable">' + " " + '</div>');
	    }
		
	
		// 오른쪽 달력
		if(middleDay != 6) {
	 		for(var i = middleDate - middleDay; i <= middleDate; i++) {
	 			$('.date2_detail').append('<div class="day_detail disable">' + " " + '</div>');
	   		}
	  	}
	    for (var i = 1; i <= nextDate; i++) {
	    	$('.date2_detail').append("<div class='day_detail current_detail right "+ currentYear+(currentMonth+2)+i+"'"+
									"onclick=user_search_detail.selectDate("+currentYear+','+(currentMonth+2)+','+i+",this"+")>" + i + "</div>");  
		}
	    for (var i = 1; i <7 - nextDay ; i++) {
	        $('.date2_detail').append('<div class="day_detail disable">' + " " + '</div>');
	    }
		
		
		// 오늘보다 이전 날짜 disable
		if(today.getFullYear() > currentYear){	// 현재 연도보다 작을 때
			currentMonthDate = document.querySelectorAll('.date_detail .current_detail');// 현재 표시한 달력의 날짜 가져오기
			for(let i = 0; i < currentMonthDate.length; i++){
				currentMonthDate[i].classList.add('disable');
			}
		} else if(today.getFullYear() == currentYear){ // 같은 연도
			// 화면의 왼쪽 달력이 현재 월-2 보다 작을때 ex) 현재 7월이면 왼쪽 달력이 5월 이하
			if(today.getMonth() > currentMonth+1){	   
				currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
				for(let i = 0; i < currentMonthDate.length; i++){
					currentMonthDate[i].classList.add('disable');
				}
			} else if(today.getMonth() == currentMonth+1) {	// 7월이면 왼쪽 달력이 6월
				currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
				for(let i = 0; i < middleDate + today.getDate() - 1; i++){// 왼쪽 달력 + 어제까지 클릭불가
					currentMonthDate[i].classList.add('disable');
				}
			} else if(today.getMonth() == currentMonth){	// 이번달
				currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
				for(let i = 0; i < today.getDate() - 1; i++){     // 어제까지 클릭불가
					currentMonthDate[i].classList.add('disable');
				}
			}
		}
		
		// 기존의 원 표시
		if(currentMonth+2 == user_search_detail.inMonth){
		currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
		currentMonthDate[middleDate+user_search_detail.inDay-1].classList.add("circle");
		} else if(currentMonth+1 == user_search_detail.inMonth){
			currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
			currentMonthDate[user_search_detail.inDay-1].classList.add("circle");
		} 
		
		if(currentMonth+2 == user_search_detail.outMonth){
			currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
			currentMonthDate[middleDate+user_search_detail.outDay-1].classList.add("circle");
		} else if(currentMonth+1 == user_search_detail.outMonth){
			currentMonthDate = document.querySelectorAll('.date_detail .current_detail');
			currentMonthDate[user_search_detail.outDay-1].classList.add("circle");
		}
	
	
	}

	// 이전달로 이동
	$('.prev_detail').on('click', function () {
		thisMonth = new Date(currentYear, currentMonth - 1, 1);
		rendercalendar2(thisMonth);
	});
	
	// 다음달로 이동
	$('.next_detail').on('click', function () {
		thisMonth = new Date(currentYear, currentMonth + 1, 1);
		rendercalendar2(thisMonth);
	});	
	
}

// input tag에 값 넣어주기
user_search_detail.setText = function(check, year, month, day){
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
	
	if(check == 'in'){
		user_search_detail.inYear = year;
		user_search_detail.inMonth = month;
		user_search_detail.inDay = day
		//$(".reservaion_checkin").val(user_search_detail.inYear+"년 "+user_search_detail.inMonth+"월 "+user_search_detail.inDay+"일");
		$(".reservaion_checkin").val(user_search_detail.inYear+"-"+tempMonth+"-"+tempDay);
		//희찬
		$("#reservaion_checkin").val(user_search_detail.inYear + "-" + tempMonth + "-" + tempDay);		//input save
		//console.log("reservaion_checkin : " + user_search_detail.inYear + "-" + tempMonth + "-" + tempDay);
		
		//희찬
		$("#checkInSave2").val(user_search_detail.inYear+"-"+tempMonth+"-"+tempDay);
	} else {
		user_search_detail.outYear = year;
		user_search_detail.outMonth = month;
		user_search_detail.outDay = day;
		//$(".reservaion_checkout").val(user_search_detail.outYear+"년 "+user_search_detail.outMonth+"월 "+user_search_detail.outDay+"일");
		$(".reservaion_checkout").val(user_search_detail.outYear+"-"+tempMonth+"-"+tempDay);
		//희찬
		$("#reservaion_checkout").val(user_search_detail.outYear + "-" + tempMonth + "-" + tempDay);		//input save
		//console.log("reservaion_checkout : " + user_search_detail.outYear + "-" + tempMonth + "-" + tempDay);
		
		//희찬
		$("#checkOutSave2").val(user_search_detail.outYear+"-"+tempMonth+"-"+tempDay);
		
		
		
		//fixed 	
		let param = $("#frm_user_rough_reservation").serialize();
		$('.sticky_box').load('user_search_detail_fixed_reservation', param);
	}
}

// 기존 원 삭제
user_search_detail.removeCircle = function(target){
	currentDate = document.querySelectorAll('.date_detail .current_detail');	// 현재 달력의 날짜 모두 가져오기
	
	for(let i = 0; i < currentDate.length; i++){		
		if($(currentDate[i]).attr("class").split(" ")[3] == target){	// 타겟과 달력의 class의 날짜가 같으면 삭제
			currentDate[i].classList.remove("circle");
			break;
		}
	}
}

user_search_detail.selectDate = function(selectedYear, selectedMonth, selectedDay, selectedCalendar){
	if($(selectedCalendar).hasClass("disable") == true){	// 현재보다 이전 날짜 클릭 시 X
		return;
	}
	
	user_search_detail.calendarCnt++;
	
	if(user_search_detail.calendarCnt == 1){// 달력 첫번째 클릭
		user_search_detail.removeCircle(user_search_detail.inYear+''+user_search_detail.inMonth+''+user_search_detail.inDay);
		user_search_detail.setText("in", selectedYear, selectedMonth, selectedDay);
		$(selectedCalendar).addClass("circle");
	} else {
		if(user_search_detail.inYear == selectedYear){		// 연도 같을 때
			if(user_search_detail.inMonth < selectedMonth){	// 체크인 월보다 입력받은 월이 클 때
				user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
				user_search_detail.setText("out", selectedYear, selectedMonth, selectedDay);				
				
				$(selectedCalendar).addClass("circle");
			} else if (user_search_detail.inMonth == selectedMonth){// 체크인 월과 같을 때
				if(user_search_detail.inDay < selectedDay){			// 체크인 날짜보다 클 때
					user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
					user_search_detail.setText("out", selectedYear, selectedMonth, selectedDay);
					
					$(selectedCalendar).addClass("circle");
				} else if(user_search_detail.inDay == selectedDay){	// 체크인 날짜와 같을 때
					user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
					user_search_detail.setText("out", selectedYear, selectedMonth, selectedDay);
				} else {								// 체크인 날짜보다 작을 때
					user_search_detail.removeCircle(user_search_detail.inYear+''+user_search_detail.inMonth+''+user_search_detail.inDay);
					user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
					
					user_search_detail.inDay = selectedDay;
					$(".reservaion_checkout").val("");
					$(selectedCalendar).addClass("circle");
				}
			} else {	// 기존의 월보다 입력 받은 월이 작을 때
				user_search_detail.removeCircle(user_search_detail.inYear+''+user_search_detail.inMonth+''+user_search_detail.inDay);
				user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
				
				user_search_detail.inMonth = selectedMonth;
				user_search_detail.inDay = selectedDay;
				$(".reservaion_checkout").val("");				
				$(selectedCalendar).addClass("circle");
			}
		} else {	// 입력받은 연도가 더 클 때
			user_search_detail.removeCircle(user_search_detail.outYear+''+user_search_detail.outMonth+''+user_search_detail.outDay);
			user_search_detail.setText("out", selectedYear, selectedMonth, selectedDay);
			
			$(selectedCalendar).addClass("circle");
		}
		
		//희찬
		var tempMonth2;
		var tempDay2;
		if(user_search_detail.inMonth < 10){
			tempMonth2 = "0" + user_search_detail.inMonth;
		}
		else{
			tempMonth2 = user_search_detail.inMonth;
		}
		if(user_search_detail.inDay < 10){
			tempDay2 = "0" + user_search_detail.inDay;
		}
		else{
			tempDay2 = user_search_detail.inDay;
		}
	
		//$(".reservaion_checkin").val(user_search_detail.inYear+"년 "+user_search_detail.inMonth+"월 "+user_search_detail.inDay+"일");
		$(".reservaion_checkin").val(user_search_detail.inYear+"-"+tempMonth2+"-"+tempDay2);
		$("#reservaion_checkin").val(user_search_detail.inYear + "-" + tempMonth2 + "-" + tempDay2); //input save
	}
		
}


user_search_detail.wish = function(e){   
	let frm = $('#frm_user_rough_reservation');	//img의 부모 form
	
	if($(e).attr("class") == "not_wish"){
		if($('#userId').val() != ""){
			$(e).attr("class", "wish");
			$(e).attr("src", "./image/icon/heart.png");
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
			$(e).attr("src", "./image/icon/empty_heart.png");
			alert("찜 목록에서 삭제되었습니다.");
			
			let param = $(frm).serialize();
			$.post("user_search_wishlist_check_delete",param);
		}
	}
	/*
   if($(e).attr("class") == "not_wish"){
      $(e).attr("class", "wish");
      $(e).attr("src", "./image/icon/heart.png");
   } else{
      $(e).attr("class", "not_wish");
      $(e).attr("src", "./image/icon/empty_heart.png");
   }*/
   
}



var newPage;
user_search_detail.btn_qna_input = function(){
	newPage = window.open("user_search_detail_qna_input","qna_input",
				"width=1000, height=600");	
}






user_search_detail.adultCount = 0;	// 인원 수 조절
user_search_detail.childCount = 0;



// 슬라이드
let recommend_slide1 = document.querySelector(".camp_slide");
let slideImg1 = document.querySelectorAll(".camp_Image");
let slideWidth1 = 0;
let slideIndex1 = 0;
let slideMove1 = true;
let movement1 = true;
let slideBtns1 = document.querySelectorAll(".slide_btn");

// 슬라이드 사이즈 구하기
user_search_detail.slideSize = function(){
	for(let i = 0; i < slideImg1.length; i++){
		slideWidth1 += slideImg1[i].offsetWidth;
		recommend_slide1.style.transition = 'transform 1.5s ease-out';
	}
}

// 슬라이드 재생, 일시정지
if(recommend_slide1 != null){   // inc에 슬라이드가 있을 때
   
   user_search_detail.slideSize();
}

// 슬라이드 움직임 이벤트
user_search_detail.slidePrev = function(){
	slideIndex1--;
	if(slideIndex1 < 0) {
		slideIndex1 = slideImg1.length-1;
	}
	recommend_slide1.style.transform = "translateX(" + -430 * slideIndex1 +"px)";
	user_search_detail.btnColorChange(slideIndex1);
}
user_search_detail.slideNext = function(){
	slideIndex1++;
	if(slideIndex1 == slideImg1.length-2) {
		slideIndex1 = 0;
	}
	recommend_slide1.style.transform = "translateX(" + -430 * slideIndex1 +"px)";
	userslide.btnColorChange(slideIndex1);
}
user_search_detail.slideControl = function(btn){
	let control = btn.innerHTML;
	userslide.slideMove1Control(slideMove1)
	
	if(control == "Stop") btn.innerHTML = "Play";
	else btn.innerHTML = "Stop";
}
user_search_detail.slideMove1 = function(moveIndex){
	recommend_slide1.style.transform = "translateX(" + -430 * moveIndex +"px)";
	slideIndex1 = moveIndex;
	userslide.btnColorChange(slideIndex1);
}

// 슬라이드 움직이면 버튼 색상 변경
user_search_detail.btnColorChange = function(slideIndex1){
	for(let i = 0; i < slideBtns1.length; i++){
		slideBtns1[i].style.background = "#ddd";	
	}
	slideBtns1[slideIndex1].style.background = "#fff";
}


