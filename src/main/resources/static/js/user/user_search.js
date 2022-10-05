let search = {}
// 달력 생성
search.calendarCnt = 0;
search.inYear;	
search.outYear;
search.inMonth; // check-in에 들어갈 달
search.inDay;
search.outMonth; // check-out에 들어갈 달
search.outDay;
search.adultCount = 0;	// 인원 수 조절
search.childCount = 0;

//gmlcks
/*

*/

// 상단 검색창 띄우기
/*
search.showSearchBtn = function(){
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

//희찬
var link =  document.location.href;
var frm2 = document.getElementById('frm');
var frmState = document.getElementById('searchState');
if(frmState.value == "search"){
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchFirst', param);
}
else if(frmState.value == "searchOther"){
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOther_temp', param);
}
else if(frmState.value == "search_detail"){
	let param = $("#frm").serialize();
	$('#inc_div').load('user_search_detail', param);
}
	
			
			
		


// 유저
$(".user_icon").on("click", function(){
	$(".user_menu").show();
})
$('body').on("click", function(e){
    if(!$('.user').has(e.target).length ) {
		$('.user_menu').hide();
	}
});
// 지도
search.region = function(){
	$(".regions").slideDown(600);
}
$('body').on("click", function(e){
    if(!$('.region_toggle').has(e.target).length ) {
		$('.regions').slideUp(600);
	}
});
$(".region").on("click", function(){
	let region = $(this).text();
	$(".input_region").val(region);
	//search.showSearchBtn();
})

// 필터
$('.btn_filter').on('click', function(){
	$('.filter').toggle();
});

//희찬
/*
$(".tag").on("click", function(e){
	if($(e.target).val()!= "checked"){
		$(e.target).css({"border": "2px solid #f8ca37",
						 "color": "#f8ca37"});
		$(e.target).val("checked");		
	} else {
		$(e.target).css({"border": "2px solid #fff",
						 "color": "#fff"});
		$(e.target).val("");	
	}
})*/

$('.btn_filter_close').on('click', function(){
	$('.filter').toggle();
});
 
// 인원
search.headCount = function(){
	$(".head_count").slideDown(600);
}
$('body').on("click", function(e){
    if(!$('.head_count_toggle').has(e.target).length ) {
		$('.head_count').slideUp(600);
	}
});

// 인원수 조절
$(".adult_plus").on("click", function(){
	search.adultCount++;
	$(".adult_count").text(search.adultCount);
	$(".adult_minus").css("cursor", "pointer");
	search.headCompute();
	//희찬
	$('#adultCount').val(search.adultCount);
})

$(".adult_minus").on("click", function(){
	if(search.adultCount == 0){
		$(".adult_minus").css("cursor", "not-allowed");
	} else {
		search.adultCount--;
		search.headCompute();
		//희찬
		$('#adultCount').val(search.adultCount);
	}
	$(".adult_count").text(search.adultCount);
})

$(".child_plus").on("click", function(){
	search.childCount++;
	$(".child_count").text(search.childCount);
	$(".child_minus").css("cursor", "pointer");
	search.headCompute();
	//희찬
	$('#childCount').val(search.childCount);
})

$(".child_minus").on("click", function(){
	if(search.childCount == 0){
		$(".child_minus").css("cursor", "not-allowed");
	} else {
		search.childCount--;
		search.headCompute();
		//희찬
		$('#childCount').val(search.childCount);
	}
	$(".child_count").text(search.childCount);
})

search.headCompute = function(){
	let headCount = search.childCount + search.adultCount;
	if(headCount == 0) $(".input_head_count").val("");
	else $(".input_head_count").val("총 인원 " + headCount + "명");	
	
	//search.showSearchBtn();	
}


// 찜
search.wish = function(e){
	if($(e).attr("class") == "not_wish"){
		$(e).attr("class", "wish");
		$(e).attr("src", "./image/icon/wish.png");
	} else{
		$(e).attr("class", "not_wish");
		$(e).attr("src", "./image/icon/not_wish.png");
	}
}


// 달력
$('body').on("click", function(e){
    if(!$('.cal_toggle').has(e.target).length ) {
		$('.calendar').hide();
	}
});

search.calendar = function() {
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
		search.thisDate = middleDate;
		search.thisMonth = currentMonth+1;
		
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
									"onclick=search.selectDate("+currentYear+','+(currentMonth+1)+','+i+",this"+")>" + i + "</div>");
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
									"onclick=search.selectDate("+currentYear+','+(currentMonth+2)+','+i+",this"+")>" + i + "</div>");  
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
		if(currentMonth+2 == search.inMonth){
		currentMonthDate = document.querySelectorAll('.date .current');
		currentMonthDate[middleDate+search.inDay-1].classList.add("circle");
		} else if(currentMonth+1 == search.inMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[search.inDay-1].classList.add("circle");
		} 
		
		if(currentMonth+2 == search.outMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[middleDate+search.outDay-1].classList.add("circle");
		} else if(currentMonth+1 == search.outMonth){
			currentMonthDate = document.querySelectorAll('.date .current');
			currentMonthDate[search.outDay-1].classList.add("circle");
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
	
	//search.showSearchBtn();	
}

// input tag에 값 넣어주기
search.setText = function(check, year, month, day){
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
		search.inYear = year;
		search.inMonth = month;
		search.inDay = day
		$(".check_in").val(search.inYear+"년 "+search.inMonth+"월 "+search.inDay+"일");
		//희찬
		$("#checkIn").val(year + "-" + tempMonth + "-" + tempDay);
	} else {
		search.outYear = year;
		search.outMonth = month;
		search.outDay = day;
		$(".check_out").val(search.outYear+"년 "+search.outMonth+"월 "+search.outDay+"일")
		//희찬
		$("#checkOut").val(year + "-" + tempMonth + "-" + tempDay);
	}
}

// 기존 원 삭제
search.removeCircle = function(target){
	currentDate = document.querySelectorAll('.date .current');	// 현재 달력의 날짜 모두 가져오기
	
	for(let i = 0; i < currentDate.length; i++){		
		if($(currentDate[i]).attr("class").split(" ")[3] == target){	// 타겟과 달력의 class의 날짜가 같으면 삭제
			currentDate[i].classList.remove("circle");
			break;
		}
	}
}

search.selectDate = function(selectedYear, selectedMonth, selectedDay, selectedCalendar){
	if($(selectedCalendar).hasClass("disable") == true){	// 현재보다 이전 날짜 클릭 시 X
		return;
	}
	
	search.calendarCnt++;
	
	if(search.calendarCnt == 1){// 달력 첫번째 클릭
		search.removeCircle(search.inYear+''+search.inMonth+''+search.inDay);
		search.setText("in", selectedYear, selectedMonth, selectedDay);
		$(selectedCalendar).addClass("circle");
	} else {
		if(search.inYear == selectedYear){		// 연도 같을 때
			if(search.inMonth < selectedMonth){	// 체크인 월보다 입력받은 월이 클 때
				search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
				search.setText("out", selectedYear, selectedMonth, selectedDay);				
				
				$(selectedCalendar).addClass("circle");
			} else if (search.inMonth == selectedMonth){// 체크인 월과 같을 때
				if(search.inDay < selectedDay){			// 체크인 날짜보다 클 때
					search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
					search.setText("out", selectedYear, selectedMonth, selectedDay);
					
					$(selectedCalendar).addClass("circle");
				} else if(search.inDay == selectedDay){	// 체크인 날짜와 같을 때
					search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
					search.setText("out", selectedYear, selectedMonth, selectedDay);
				} else {								// 체크인 날짜보다 작을 때
					search.removeCircle(search.inYear+''+search.inMonth+''+search.inDay);
					search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
					
					search.inDay = selectedDay;
					$(".check_out").val("");
					$(selectedCalendar).addClass("circle");
				}
			} else {	// 기존의 월보다 입력 받은 월이 작을 때
				search.removeCircle(search.inYear+''+search.inMonth+''+search.inDay);
				search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
				
				search.inMonth = selectedMonth;
				search.inDay = selectedDay;
				$(".check_out").val("");				
				$(selectedCalendar).addClass("circle");
			}
		} else {	// 입력받은 연도가 더 클 때
			search.removeCircle(search.outYear+''+search.outMonth+''+search.outDay);
			search.setText("out", selectedYear, selectedMonth, selectedDay);
			
			$(selectedCalendar).addClass("circle");
		}
		$(".check_in").val(search.inYear+"년 "+search.inMonth+"월 "+search.inDay+"일");
	}
	
	//search.showSearchBtn();	
}

/*
//gmlcks
search.tempChange = function(){
	var checklogin = $('#tempChange').val();
	if(checklogin == "login"){
		$('#tempChange').html('로그인 상태 = logout');
		$('#tempChange').val('logout');
		$('#login_a').html('로그인');
		$('#join_a').html('회원가입');
	}
	//logout
	else{
		$('#tempChange').html('로그인 상태 = login');
		$('#tempChange').val("login");
		$('#login_a').html('로그아웃');
		$('#join_a').html('마이페이지');
	}
}
*/	

/*
//로그인 이동
search.login_page = function(){
	var loginStr = $('#login_a').html();
	var frm = document.getElementById('frm');
	console.log("frm : " + frm);
	
	if(loginStr == '로그인'){
		frm.action='user_login';
		frm.submit();
	}
	else{
		//로그아웃 == html
		alert("로그아웃 되었습니다.");
		
		$('#tempChange').html('로그인 상태 = logout');
		$('#tempChange').val('logout');		
		
		$('#login_a').html('로그인');
		$('#join_a').html('회원가입');
		//frm.action='user_login';
		//frm.submit();
	}
	
}*/
/*
//회원가입 이동
search.join_page = function(){
	var joinStr = $('#join_a').html();
	var frm = document.getElementById('frm');
	
	if(joinStr == '회원가입'){
		frm.action='user_join';
		frm.submit();
	}
	else{
		//마이페이지 == html
		frm.action='user_mypage';
		frm.submit();
	}
	
}*/


// 수경 수정 (찐 로그인/로그아웃 기능 구현) ★★★★★★
// 로그인 이동
search.login_page = function(){
	let frm = document.getElementById('frm');
	frm.action='user_login';
	frm.submit();
}

// 수경 수정 (찐 로그인/로그아웃 기능 구현) ★★★★★★
// 회원가입 이동
search.join_page = function(){
	let frm = document.getElementById('frm');
	frm.action='user_join';
	frm.submit();
}

// 수경 수정 (찐 로그인/로그아웃 기능 구현) ★★★★★★
// 로그아웃 이동
search.logout_page = function(){
	let frm = document.getElementById('frm');
	alert("로그아웃 되었습니다. 다음에 또 찾아주세요. :)");
	frm.action='user_logout';
	frm.submit();
}
// 수경 수정 (찐 로그인/로그아웃 기능 구현) ★★★★★★
// 마이페이지 이동
search.mypage_page = function(){
	let frm2 = document.getElementById('frm');
	frm.action='user_reservation_list';
	frm.submit();
}





//메인 index로 이동
search.index_page = function(){
	var frm = document.getElementById('frm');
	frm.action='user_index';
	frm.submit();
}
search.wishlist = function(frm){
	//로그인을 하고 난 뒤 해당 유저코드를 가져와 찜 list Add
	//비로그인 시 로그인하라고 arlet 후 로그인 페이지로 이동시킬 것.
	frm.action='user_wishlist';
	frm.submit();
}





//user_search - Go버튼

search.searchGo = function(){
	var link =  document.location.href;
	var frm = document.getElementById('frm');
	if( link == "http://localhost:8282/user_search"){
			
			let element = document.getElementById('tagSave');
			let element2 = document.getElementById('input_search');
			let eleinputSearch2 = document.getElementById('input_search2');
			let elesearchbar2 = document.getElementById('searchbar2');
			elesearchbar2.value = element2.value;
			eleinputSearch2.value = element2.value;
			filterCreate1();
			tagCreate1(element.value);
			
			let param = $("#frm").serialize();
			$('#inc_div').load('user_searchOption_Go', param);
			//frm.action='user_search2Option';
			//frm.submit();
	}else{
		if($('.input_region').val() == ""){
			alert("지역을 선택해 주세요.");
		}
		else if($('.check_in').val() == "" || $('.check_out').val() == ""){
			alert("예약 날짜를 선택해 주세요.");
		}
		else if($('.input_head_count').val() == ""){
			alert("인원을 선택해 주세요.");
		}
		else if($('#adultCount').val() == ""){
			alert("성인 인원을 선택해 주세요.");
		}
		else if($('#childCount').val() == ""){
			alert("아이 인원을 선택해 주세요.");
		}
		else{
			frm.action='user_searchOther';
			frm.submit();
		}
	}
}
/*
//filter check 부분
$('input[type=checkbox][name=filtercheck]').change(function(){
	var frm = document.getElementById('frm');
	let element = document.getElementById('tagSave');
	filterCreate();
	tagCreate(element.value);
	
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOption', param);
	
	//frm.action='user_search2Option';
	//frm.submit();
});

//tag click 부분
search.tagBtn = function(tagBtn){
	let tagValue = tagBtn.value;
	console.log("tagValue : " + tagValue);
	
	filterCreate();
	tagCreate(tagValue);
	
	//var frm = document.getElementById('frm');
	let param = $("#frm").serialize();
	$('#inc_div').load('user_searchOption', param);
	//frm.action='user_searchOption';
	//frm.submit();
}
*/

function tagCreate1(tagValue){
	let element = document.getElementById('tagSearch');
	if(element != null){
		element.parentNode.removeChild(element);
	}
	$('.text_hidden_area').append(
			"<input type='hidden' name='tagSearch' id='tagSearch'" +
			" value='" + tagValue + "' >"
	)
}
function filterCreate1(){
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
		element.parentNode.removeChild(element);
		element.parentNode.removeChild(element2);
	}
	if(temp != ""){
		
		$('.text_hidden_area').append(
			"<input type='hidden' name='checkfilter' id='checkfilter'" +
			" value='" + temp + "' />" + 
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




//수경
search.user_login = function(){
	location.href = 'user_login'
}
search.user_join = function(){
	location.href = 'user_join'
}