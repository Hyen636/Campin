let sales = {};
sales.map;
let date = new Date();
let dateCnt = 0; 
let dateArr = [];
let dataColor = ['#f6c9cc', '#a8c0c0', '#FEBF36', '#FF9288', '#64C5A0', 
				 '#acc7bf', '#5e5f67', '#c37070', '#eae160', '#bf7aa3', 
				 '#27d967', '#1985d9', '#88f1c5', '#d155a2']

$(function(){
	let before_6 = new Date(Date.parse(date) - 6 * 1000 * 60 * 60 * 24); // 6일전
	let today = dateFormatting(date)
	$("input[name=dStart]").val(dateFormatting(before_6));
	$("input[name=dEnd]").val(today);
	$("input[name=dEnd]").change();
	
	createOptionMonth(date);
})

$(".option").on("change", function(){
	let option = $(this).val();
	if(option == "일별 조회"){
		$(".filter_month").css("display", "none");
		$(".filter_date").css("display", "block");
	} else {
		$(".filter_month").css("display", "block");
		$(".filter_date").css("display", "none");
	}
})

$(".find_date").on("change", function(){	// 시작날짜보다 끝날짜가 더 작으면 바꿔주기
	let start = $("input[name=dStart]");
	let end = $("input[name=dEnd]");
	let temp;
	if($(end).val() != ""){
		if($(start).val() > $(end).val()){
			temp = $(start).val();
			$(start).val($(end).val())
			$(end).val(temp)
		}		
	}
	let sdt = new Date($(start).val());
	let edt = new Date($(end).val());
	// 날짜 차이 계산
	let dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24));
	let param;
	dateArr = []; // 배열 초기화
	
	if(dateDiff > 13){
		$(end).val("");
		return;
	} else if(isNaN(dateDiff)){
		// 입력값이 잘못 되었을 때 처리, 시작날짜만 있을 때
	} else {		
		for(let i = 0; i <= dateDiff; i++){
			let td = new Date(Date.parse($(start).val()) + i * 1000 * 60 * 60 * 24);
			let date = dateFormatting(td);
			dateArr.push(date)
		}
		$("input[name=dateStr]").val($(start).val());
		$("input[name=dateEnd]").val($(end).val());
		param = $("#frm_sales").serialize();
		$.ajax({
			data : param,
			url : "partner_sales_graph",
			type : "post",
			cache : false,
			success : function(map){
				sales.map = map;	// 차트 함수에 map을 매개변수로 넣으면 error
				google.charts.load('current', {'packages':['corechart']});
				google.charts.setOnLoadCallback(drawChart);
			}
		})
	}
})

$(".option_month").on("change", function(){
	let start = $(".month1").val();
	let end = $(".month2").val();
	let year;
	let month;
	let monthDiff = 0;
	dateArr = [];
	
	if(start > end){
		$(".month1").val(end);
		$(".month2").val(start);
	}
	year = $(".month1").val().slice(0, 4);
	month = $(".month1").val().slice(5, 7);
	for(let i = 0; i < 24; i++){	// 월간 조회는 최근 2년만 조회가능
		temp = year+"-"+month
		dateArr.push(temp);
		month++;
		
		if(month < 10) {
			month = '0'+month;			
		} else if(month == 13){
			year++;
			month = '01';
		} 	
		if(temp == $(".month2").val()) {
			break;
		}
		monthDiff++;
		if(monthDiff>11) { // 월간 조회는 최대 1년까지 조회가능
			$(".month2").val($(".month1").val());
			return;
		} 
		
	}
	lastDay = new Date($(".month2").val().slice(0,4), $(".month2").val().slice(5,7), 0).getDate();
	$("input[name=dateStr]").val($(".month1").val()+'-01');
	$("input[name=dateEnd]").val($(".month2").val()+'-'+lastDay);
	param = $("#frm_sales").serialize();
	
	$.ajax({
		data : param,
		url : "partner_sales_month_graph",
		type : "post",
		cache : false,
		success : function(map){
			sales.map = map;	// 차트 함수에 map을 매개변수로 넣으면 error
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawMonthChart);
		}
	})
	
})

function dateFormatting(tempDate){
	let tempYear = tempDate.getFullYear();
	let tempMonth = tempDate.getMonth()+1;
	let temptDay = tempDate.getDate();
	if(tempMonth < 10) tempMonth = '0' + tempMonth;
	if(temptDay < 10) temptDay = '0' + temptDay;
	let date = tempYear+"-"+tempMonth+"-"+temptDay;
	
	return date;
}
function createOptionMonth(today){
	let year = dateFormatting(today).slice(0,4);
	let month = dateFormatting(today).slice(5,7);
	
	for(let i = 0; i < 24; i++){
		if(month == 0){
			month = 12;
			year--;
		}
		$(".option_month").append(
			`<option>${year}-${month} </option>`
		)
		month--;
		if(month < 10) month = '0' + month;
	}
}

function drawChart() {
	let amount = 0;
	var data = new google.visualization.DataTable();
	data.addColumn('string', '날짜');		// x축
    data.addColumn('number', '매출액');	// y축
 	data.addColumn({type: 'string', role: 'style' });
	
  	// 데이터 추가
	for (var i = 0; i < dateArr.length; i++) {
		if(sales.map[dateArr[i]] == undefined){	// 매출액이 없는 날
			arr = [dateArr[i].slice(5), 0, 'color : #fff'];
		} else {
			arr = [dateArr[i].slice(5), sales.map[dateArr[i]].amount, 'color:'+dataColor[i]];
			amount += sales.map[dateArr[i]].amount;
		}
   		
    	data.addRow(arr);
    }

	var options = {
		title: '매출 금액',
		subtitle: '단위 : 원',
		legend: { position: 'top' },
		bar: {groupWidth: "55%"},
	};
	
	var chart = new google.visualization.ColumnChart(document.getElementById('chart'));	
	chart.draw(data, options);
	$(".date1").text(dateArr[0]);
	$(".date2").text(dateArr[dateArr.length-1]);
	$(".amt").text(amount.toLocaleString() + "원");
}

function drawMonthChart() {
	let amount = 0;
	
	var data = new google.visualization.DataTable();
	data.addColumn('string', '월');		// x축
    data.addColumn('number', '매출액');	// y축
 	data.addColumn({type: 'string', role: 'style' });
  	// 데이터 추가
	for (var i = 0; i < dateArr.length; i++) {
		if(sales.map[dateArr[i]] == undefined){	// 매출액이 없는 월
			arr = [dateArr[i].slice(5), 0, 'color : #fff'];
		} else{
			arr = [dateArr[i].slice(5), sales.map[dateArr[i]].amount, 'color:'+dataColor[i]];
			amount += sales.map[dateArr[i]].amount;
		}
    	data.addRow(arr);
    }

	var options = {
		title: '매출 금액',
		subtitle: '단위 : 원',
		legend: { position: 'top' },
		bar: {groupWidth: "55%"},
	};
	
	var chart = new google.visualization.ColumnChart(document.getElementById('chart'));	
	chart.draw(data, options);
	$(".date1").text(dateArr[0]);
	$(".date2").text(dateArr[dateArr.length-1]);
	$(".amt").text(amount.toLocaleString() + "원");
}
