<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_site_load</title>
<script defer src="./js/user/user_search_detail_site_list.js"></script>
</head>
<body>
	<label class="site_detail_title">사이트 배치도</label><br/>
	
	<img src="./image/camping/${page.detailItemCode}/map/${mapName}" width="800px" height="550px" /><br/><br/>
	<c:set var='pos' value='1' />
	<c:forEach var='site_items' items='${list}'>
		<label class="site_detail_info_title" onclick="user_search_detail_site_list.show(${pos})">${site_items.siteName}</label><br>
		<div class="site_hidden site_items_${pos}" >
			
			<div class="site_info">
				<div id="siteImg${pos}"></div>
			
				<div class="site_info_detail">
					<h3>사이트 세부정보</h3><br>
					<div>
						<div>기본인원 : ${site_items.sPeople}</div>
						<div>최대인원 : ${site_items.mPeople}</div>
						<div>기본주차 : ${site_items.cntCar}</div>
					</div>
				</div><br>
				
				<div class="site_info_option">
					<div>
						<label>유형</label>
						<div>${site_items.category}</div>
					</div>
					<div>
						<label>기본 크기</label>
						<div>${site_items.siteSize}</div>
					</div>
					<div>
						<label>바닥</label>
						<div>${site_items.siteType}</div>
					</div>
				</div><br>
					
				<div class="div_infoText">
					<textarea id="site_infoText" disabled> ${site_items.infoText}</textarea>
				</div>
				
			</div><br/>
		</div>
		<input type="hidden" name="imgSiteName" id='imgSiteName${pos}' value='${site_items.siteName}'>
	<c:set var='pos' value='${pos=pos+1}'/><br>
	
	
	</c:forEach>
	
	<input type="hidden" name="sitecnt" id="sitecnt" value='${sitecnt}'><br>
</body>
</html>