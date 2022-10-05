let sales = {};
sales.map;
let date = new Date();
let dateArr = [];
let itemNameArr = [];

$(function(){
	createOptionMonth(date);
	$(".option_month").change();
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
	
	for(let i = 0; i < 24; i++){	// 월간 조회는 최근 1년만 조회가능
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
			alert("월간 조회는 최대 1년까지 가능합니다.");
			$(".month2").val($(".month1").val());
			return;
		} 
		
	}
	// 두번째 달의 마지막 날짜
	lastDay = new Date($(".month2").val().slice(0,4), $(".month2").val().slice(5,7), 0).getDate();
	$("input[name=dateStr]").val($(".month1").val()+'-01');
	$("input[name=dateEnd]").val($(".month2").val()+'-'+lastDay);
	param = $("#frm_sales").serialize();
	
	$.ajax({
		data : param,
		url : "manager_getItem",
		type : "post",
		cache : false,
		success : function(itemCodeList){
			itemNameArr = itemCodeList;
			$.ajax({
				data : param,
				url : "manager_sales_month_graph",
				type : "post",
				cache : false,
				success : function(map){
					sales.map = map;	
					google.charts.load('current', {'packages':['corechart']});
					google.charts.setOnLoadCallback(drawMonthChart);
				}
			})
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

function drawMonthChart() {
	var data = new google.visualization.DataTable();
	let arr2 = makeArr2(dateArr.length, itemNameArr.length+1); // 조회하는 월의 수, 캠핑장 수
  	let amount = 0;
	data.addColumn('string', '월');	// x축

	// 각 캠핑장 데이터 추가
	for(let i = 0; i < itemNameArr.length; i++){
		data.addColumn('number', itemNameArr[i]);
	}
	
	// 2차원 배열 1열에 날짜 넣기
	for(let i = 0; i < dateArr.length; i++){
		arr2[i][0] = dateArr[i]
	}
	
	for(let i = 0; i < dateArr.length; i++){
		for(let j = 0; j < itemNameArr.length; j++){
		// 2차원 배열로, j값으로 열 0은 달 1부터 캠핑장 연월+캠핑장이름 매핑해서 값
		// amount 가져오기 아마 j는 1부터 아아이템 네임 길이 + 1 까지 	
			if(sales.map[dateArr[i]+itemNameArr[j]] == undefined){
				arr2[i][j+1] = 0
			} else {
				arr2[i][j+1] = sales.map[dateArr[i]+itemNameArr[j]].amount;
				amount += sales.map[dateArr[i]+itemNameArr[j]].amount;
			}
		}
		data.addRow(arr2[i]);
	}
	
	var options = {
		title: '매출 금액',
		subtitle: '단위 : 원',
		legend: { position: 'top' },
		bar: {groupWidth: "55%"},
	};
	
	var chart = new google.visualization.LineChart(document.getElementById('chart'));	
	chart.draw(data, options);
	$(".date1").text(dateArr[0]);
	$(".date2").text(dateArr[dateArr.length-1]);
	$(".amt").text(amount.toLocaleString() + "원");
}

	
function makeArr2(rows, columns){	// 조회하는 월 , 캠핑장 갯수
	let arr2 = new Array(rows);
	for(let i = 0; i < rows; i++){
		arr2[i] = new Array(columns);
	}
	return arr2;
}	
	
