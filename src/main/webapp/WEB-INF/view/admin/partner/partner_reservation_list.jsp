<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_reservation_list</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/partner/partner_reservation_list.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/partner/partner_reservation.js"></script>
</head>
<body>
	<div id="partner_reservation_list">
		<div class="partner_reservation">
			<label class="list_title">예약 현황 조회</label>
		</div><br/>
		
		<div class="calendar">
		    <div class="cal_header"> </div>
	    	<div class="cal">	
		        <div class="days"> </div>
		        <div class="date date1"> </div>
	        </div>
		    <div class="cal_save"></div>
		</div>
			
		<div class="reservation_list">
			<form id="frm_reservation_list" method="post">
				<div class="reservation_search">
					<div class="reservation_sorting">
						<input type="date" name="start" class="find_date"> - 
						<input type="date" name="end" class="find_date">
					</div>
					<div class="reservation_find">
						<input type="text" name ="findStr" class="reservation_findStr"/>
						<input type="text" style="display:none" />	
						<img src="./image/icon/search_yellow.png" onclick="partnerReservation.search()"/>
					</div>
					<input type="hidden" name="itemCode" value="${sessionScope.itemCode }">
					<input type="hidden" name="orderCode" />
					<input type="hidden" name="nowPage" value="1"/>
				</div>
			</form>
			<div class="temp_list">
				
			</div>	
		</div>
	</div>	
</body>
</html>