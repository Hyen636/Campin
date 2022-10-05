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
<link rel='stylesheet' type='text/css' href='./css/manager/manager_sales.css'>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script defer src="./js/manager/manager_sales.js"></script>
</head>
<body>
<div id="manager_sales">
<span>매출 조회</span>
   <div class="today">
      <span class="date1"></span> ~ <span class="date2"></span>
      <span>의 매출 : </span>
      <span class="amt"></span>
   </div>
   
   <div class="sales_filter">
      <form id="frm_sales" method="post">
         <input type="hidden" name="dateStr">
         <input type="hidden" name="dateEnd">
         <span>월별 조회</span>
         <div class="filter_month">
            <span class="caution">월별 조회는 한 번에 최대 1년까지 볼 수 있습니다.</span><br>
            <select class="option_month month1"> </select> -
            <select class="option_month month2"> </select>
         </div>
      </form>
   </div>
   <div id="chart">
   </div>
   <div class="list">
   
   </div>
</div>
</body>
</html>