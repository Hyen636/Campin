<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner_sales.css'>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script defer src="./js/partner/partner_sales.js"></script>
</head>
<body>
<div id="partner_sales">
	<span>매출 조회</span>
	<div class="today">
		<span class="date1"></span> ~ <span class="date2"></span>
		<span>의 매출 : </span>
		<span class="amt"></span>
	</div>
	
	<div class="sales_filter">
		<form id="frm_sales" method="post">
			<input type="hidden" name="itemCode" value="${sessionScope.itemCode }">		
			<input type="hidden" name="dateStr">
			<input type="hidden" name="dateEnd">
			<select class="option">
				<option>일별 조회</option>
				<option>월별 조회</option>
			</select>
			<div class="filter_month">
				<span class="caution">월별 조회는 한 번에 최대 1년까지 볼 수 있습니다.</span><br>
				<select class="option_month month1"> </select> -
				<select class="option_month month2"> </select>
			</div>
			<div class="filter_date">
				<span class="caution">일별 조회는 한 번에 최대 14일까지 볼 수 있습니다.</span><br/>
				<input type="date" name="dStart" class="find_date"> -
				<input type="date" name="dEnd" class="find_date">
			</div>
		</form>
	</div>
	<div id="chart">
	</div>
</div>
</body>
</html>