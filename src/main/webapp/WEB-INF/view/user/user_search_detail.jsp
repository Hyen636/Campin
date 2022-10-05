<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_search_detail</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='./css/user/user_search_detail.css'>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer src="./js/user/user_search_detail.js"></script>


</head>
<body>
		<div id="user_search_detail">
			<div class="imgs" >
				<div><br><br>
					<div class="detail_slide_wrap">
						<ul class="camp_slide">
							<c:set var='pos1' value='1' /> 
							<c:forEach var='campimgItems' items='${camplist}'>
								<li class="camp_Image">
									<div>
										<img src='./image/camping/${page.detailItemCode}/${campimgItems.sysFile}' id='img${pos1}' width="430px" height="270px">
									</div>
								</li>
								<c:set var='pos1' value='${pos1=pos1+1}'/><br>
							</c:forEach>
						</ul>
					</div>
					
					<div class="slide_nav1">
						<img src='./image/icon/prev.png' width='3%' class="prev" onclick="user_search_detail.slidePrev()">
						<img src='./image/icon/next.png' width='3%' class="next" onclick="user_search_detail.slideNext()">		
					</div>	
				</div>
			</div>
			
						<!-- <span class="img"><img src="./image/img/camping1.jpg" width="430px" height="270px"/></span>
						<span class="img"><img src="./image/img/camping2.jpg" width="430px" height="270px"/></span>
						<span class="img"><img src="./image/img/camping3.webp" width="435px" height="270px"/></span -->
					
			<br/>
			<div class="camp_detail">
				<label class="camp_name">${vo.iName}</label><br/>
			</div>
			<div class="search_detail_box">
				<div class="desc_category">
					<label>캠핑장 소개  |  사이트 배치도  |  리뷰  |  문의</label>
					<!-- 찜 여부 -->
					<c:choose>
						<c:when test="${empty sessionScope.id}">
							<img src="./image/icon/empty_heart.png" class="not_wish" onclick="user_search_detail.wish(this)"><br/>
						</c:when>
						<c:otherwise>
							<img src="./image/icon/empty_heart.png" class="not_wish" onclick="user_search_detail.wish(this)"><br/>
						</c:otherwise>
					</c:choose>
				</div>
				
				<!-- 달력 -->
				<div class="calendar_detail">
				    <div class="cal_header_detail"> </div>
				    <div class="cal_division_detail">
				    	<div class="cal_detail">	
					        <div class="days_detail"> </div>
					        <div class="date_detail date1_detail"> </div>
				        </div>
				        <div class="cal_detail">
					        <div class="days_detail"> </div>
					        <div class="date_detail date2_detail"> </div>
					    </div>
				    </div>
				    <div class="cal_save_detail"></div>
				</div>
				<br/><br/><br/><br/>
				
				 
				<!-- 캠핑장 소개 -->
				<div class="camp_in_detail">
					<label class="camp_info_title">캠핑장 소개</label>
					
					<div class="camp_info">
						<div>${vo.title}</div><br>
						<textarea disabled>${vo.infoText}</textarea>
					</div><br/>
					<div class="camp_time">
						<h3>매너타임</h3>
						<div class="manner">
							<div>
								<div>${vo.mStr}</div>
								<label class="camp_info_title">매너타임 시작</label>
							</div>
							<div>
								<div>${vo.mEnd}</div>
								<label class="camp_info_title">매너타임 종료</label>
							</div>
						</div><br>
						<h3>입실시간 | 퇴실시간</h3>
						<div class="checkInOut">
							<div>
								<div>${vo.checkIn}</div>
								<label class="camp_info_title">입실시간</label>
							</div>
							<div>
								<div>${vo.checkOut}</div>
								<label class="camp_info_title">퇴실시간</label>
							</div>
						</div>
					</div><br><br>
					
					
					<div class="camping_map">
						<div id="map" style="width:800px;height:450px;"></div>
					</div><br/>
					<label class="facilities_title">시설 안내</label>
					<div class="facilities">
						<div class="basic_facilities">
							<c:forEach var='icon_list' items='${list}'>
								<div class="icon_show">
									<img src="./image/icon/test/${icon_list}.PNG" width="55px" height="60"/><br>
									<span class="facility_1">${icon_list}</span>
								</div>
							</c:forEach>
						</div>
					</div>
					<br/><br/>
				</div>
				
				<label class="camp_policy_title">캠핑장 운영정책 소개</label>
				<div class="policy">
					<textarea disabled>${vo.policy}</textarea>
				</div>
				
				<label class="camp_refund_title">캠핑장 환불정책 소개</label>
				<div class="refund">
					<textarea disabled>${vo.refund}</textarea>
				</div>
				
				<div class="site_detail"></div>
				
				<div class="reviews"></div><br/><br/>
				
				<div class="qna"></div>	
			</div>
			
			<div class="sticky_box"></div>
			<br/>
			
			
			
			<!-- 값을 전달하기 위한 hidden form -->
			<form name="frm_user_rough_reservation" class="frm_user_rough_reservation" id="frm_user_rough_reservation" method="post">
				<input type="hidden" name="checkInSave" id="checkInSave2" value='${page.checkInSave}'/><br/>
				<input type="hidden" name="checkOutSave" id="checkOutSave2" value='${page.checkOutSave}'/><br/><br/>
				<input type="hidden" name="adultCountSave" class="adultCountSave2" size="1" value='${page.adultCountSave}' ><br/>
				<input type="hidden" name="childCountSave" class="childCountSave2" size="1" value='${page.childCountSave}' ><br/>
				
				
				<!-- <label class="reservation_subtitle">총 합계</label><input type="text" name="tot_amt"/> -->
				
				<input type="hidden" name="iName" id='iName' value='${vo.iName}'>
				<input type="hidden" name="address1" id='address1' value='${vo.address1}'>
				<input type="hidden" name="detailItemCode" id='detailItemCode' value='${page.detailItemCode}'>
				<input type="hidden" name="latitude" id='latitude' value='${vo.latitude}'>
				<input type="hidden" name="longitude" id='longitude' value='${vo.longitude}'>
				<!-- 혜인추가 --> 
				<input type='hidden' name='userCode' id='userCode3' value='${param.userCode}'>
				<input type='hidden' name='itemCode' value='${param.detailItemCode}'>
				<input type='hidden' name='siteCode' id="siteCode" value='0' placeholder="controll에서 예약으로 값 넘겨줌">
				<input type='hidden' name='siteName' id="siteName" value='-'>
				<input type='hidden' name='cUserCode' value='0' placeholder="controll에서 예약으로 값 넘겨줌">
				
				<!-- 희찬추가 -->
				<input type='hidden' name='siteNameSave' id='siteNameSave' value=''>
			</form>
		</div>
</body>
</html>