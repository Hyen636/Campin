<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eaf6faf02085d97aac609f993a56fe26&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eaf6faf02085d97aac609f993a56fe26"></script>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_view.css'>
<script defer src="./js/partner/partner_camp.js"></script>
</head>
<body>
<div id='camping_view'>
	<span class="content_title">캠핑장 요청</span>
	<form id="frm_camp_view" method="post" name="frm_camp_view">
		<input type="hidden" name="cUserCode" value="${vo.cUserCode }">
		<input type="hidden" name="itemCode" value="${vo.itemCode }">
		<input type="hidden" name="state" value=${state }>
		<input type="hidden" name="sno" value="${sno }">
		<input type="hidden" name="rejectMsg">
		<input type="hidden" name="hisItemSno" value="${hisVo.hisItemSno }">			
	</form>
	<c:if test="${hisVo.rejectMsg != null }">
		<div class="reject">
			<span class="reject_title">반려사유</span> <br/>
			<textarea class="rejectMsg">${hisVo.rejectMsg }</textarea>		
		</div>
	</c:if>
	<div class="flex_div">
		<c:if test="${state != '등록요청'}">
			<div>	
			<span class="sub_title">기존 캠핑장</span> <br/>
			<label>캠핑장명</label>
			<input type='text' name='iName' value="${vo.iName }"/> <br/>
			<label>캠지기 성함</label>
			<input type='text' name='mName' value="${vo.mName }"/> <br/>
			<label>캠핑장 대표 번호</label>
			<input type='text' name='phone' value="${vo.phone }"/> <br/>
			<label>우편 번호</label>
			<input type='text' name='zipCode' value="${vo.zipCode }" readonly/> <br/>
			<label>주소</label>
			<input type='text' name='address1' value="${vo.address1}"/> <br/>
			<label>상세주소</label>
			<input type='text' name='address2' value="${vo.address2 }"/> <br/>
			<label>캠핑장 소개 제목</label><br/>
			<input type="text" name="title" value="${vo.title }"><br/>
			<label>캠핑장 소개 본문</label> <br/>
			<textarea rows="10" cols="65" name="infoText">${vo.infoText }</textarea> <br/>
			<label>매너 타임</label>
			<input type="time" name="mStr" value="${vo.mStr }"> - <input type="time" name="mEnd" value="${vo.mEnd}">  <br/>
			<label>입실 시간</label>
			<input type="time" name="checkIn" value="${vo.checkIn }"> - <input type="time" name="checkOut" value="${vo.checkOut }">  <br/>
			<label>캠핑장 운영 정책</label> <br/>
			<textarea name="policy"  rows="10" cols="65" >${vo.policy }</textarea> <br/>
			<label>캠핑장 환불 정책</label> <br/>
			<textarea name="refund" rows="10" cols="65">${vo.refund }</textarea> <br/>
			
			<input type="hidden" name="sido" value="${vo.sido }">
			<input type="hidden" name="latitude" value="${vo.latitude }">
			<input type="hidden" name="longitude" value="${vo.longitude }">
			
			<div class="filter">
				<span>숙박형태</span>
				<div class="filter_stay">
					<input type="checkbox" name="cFilter" id="stay_radio1" value="오토캠핑">
					<label for="stay_radio1">오토캠핑</label>
					<input type="checkbox" name="cFilter" id="stay_radio2" value="글램핑">
					<label for="stay_radio2">글램핑</label>
					<input type="checkbox" name="cFilter" id="stay_radio3" value="카라반">
					<label for="stay_radio3">카라반</label>
					<input type="checkbox" name="cFilter" id="stay_radio4" value="펜션">
					<label for="stay_radio4">펜션</label>
					<input type="checkbox" name="cFilter" id="stay_radio5" value="방갈로">
					<label for="stay_radio5">방갈로</label>
					<input type="checkbox" name="cFilter" id="stay_radio6" value="차박">
					<label for="stay_radio6">차박</label>
				</div>
				<span>환경</span> 
				<div class="filter_environment">
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio1" value="바다">
					<label for="environment_radio1">바다</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio2" value="산">
					<label for="environment_radio2">산</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio3" value="강">
					<label for="environment_radio3">강</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio4" value="호수">
					<label for="environment_radio4">호수</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio5" value="계곡">
					<label for="environment_radio5">계곡</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio6" value="섬">
					<label for="environment_radio6">섬</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio7" value="평야">
					<label for="environment_radio7">평야</label>
					<input type="checkbox" name="cFilter" class="environment" id="environment_radio8" value="기타">
					<label for="environment_radio8">기타</label>
				</div>
				<span>입지 형태</span>
				<div class="filter_position">
					<input type="checkbox" name="cFilter" id="position_radio1" value="파쇄석">
					<label for="position_radio1">파쇄석</label>
					<input type="checkbox" name="cFilter" id="position_radio2" value="데크">
					<label for="position_radio2">데크</label>
					<input type="checkbox" name="cFilter" id="position_radio3" value="잔디">
					<label for="position_radio3">잔디</label>
					<input type="checkbox" name="cFilter" id="position_radio4" value="모래">
					<label for="position_radio4">모래</label>
					<input type="checkbox" name="cFilter" id="position_radio5" value="자갈">
					<label for="position_radio5">자갈</label>
					<input type="checkbox" name="cFilter" id="position_radio6" value="혼합">
					<label for="position_radio6">혼합</label>
					<input type="checkbox" name="cFilter" id="position_radio7" value="기타">
					<label for="position_radio7">기타</label>
				</div>
				<span>기본 시설</span>
				<div class="filter_facility">
					<input type="checkbox" name="cFilter" id="facility_radio1" value="화장실">
					<label for="facility_radio1">화장실</label>
					<input type="checkbox" name="cFilter" id="facility_radio2" value="샤워대">
					<label for="facility_radio2">샤워대</label>
					<input type="checkbox" name="cFilter" id="facility_radio3" value="개수대">
					<label for="facility_radio3">개수대</label>
					<input type="checkbox" name="cFilter" id="facility_radio4" value="매점">
					<label for="facility_radio4">매점</label>
					<input type="checkbox" name="cFilter" id="facility_radio5" value="카페">
					<label for="facility_radio5">카페</label>
					<input type="checkbox" name="cFilter" id="facility_radio6" value="와이파이">
					<label for="facility_radio6">와이파이</label>
				</div>
				<span>부가 시설</span>
				<div class="filter_option">
					<input type="checkbox" name="cFilter" id="option_radio1" value="놀이시설">
					<label for="option_radio1">놀이시설</label>
					<input type="checkbox" name="cFilter" id="option_radio2" value="체험활동">
					<label for="option_radio2">체험활동</label>
					<input type="checkbox" name="cFilter" id="option_radio3" value="수영장">
					<label for="option_radio3">수영장</label>
					<input type="checkbox" name="cFilter" id="option_radio4" value="트램펄린">
					<label for="option_radio4">트램펄린</label>
					<input type="checkbox" name="cFilter" id="option_radio5" value="산책로">
					<label for="option_radio5">산책로</label>
					<input type="checkbox" name="cFilter" id="option_radio6" value="장비대여">
					<label for="option_radio6">장비대여</label>
					<input type="checkbox" name="cFilter" id="option_radio7" value="반려동물">
					<label for="option_radio7">반려동물</label>
					<input type="checkbox" name="cFilter" id="option_radio8" value="트레일러 진입">
					<label for="option_radio8">트레일러 진입</label>
					<input type="checkbox" name="cFilter" id="option_radio9" value="카라반 진입">
					<label for="option_radio9">카라반 진입</label>
				</div>
				<span>레저</span>
				<div class="filter_leisure">
					<input type="checkbox" name="cFilter" id="leisure_radio1" value="물놀이">
					<label for="leisure_radio1">물놀이</label>
					<input type="checkbox" name="cFilter" id="leisure_radio2" value="해수욕장">
					<label for="leisure_radio2">해수욕장</label>
					<input type="checkbox" name="cFilter" id="leisure_radio3" value="낚시">
					<label for="leisure_radio3">낚시</label>
					<input type="checkbox" name="cFilter" id="leisure_radio4" value="MTB">
					<label for="leisure_radio4">MTB</label>
					<input type="checkbox" name="cFilter" id="leisure_radio5" value="등산">
					<label for="leisure_radio5">등산</label>
					<input type="checkbox" name="cFilter" id="leisure_radio6" value="스키">
					<label for="leisure_radio6">스키</label>
					<input type="checkbox" name="cFilter" id="leisure_radio7" value="수상레저">
					<label for="leisure_radio7">수상레저</label>
					<input type="checkbox" name="cFilter" id="leisure_radio8" value="기타">
					<label for="leisure_radio8">기타</label>
				</div>
				
				<span>해시태그</span>  
               	<div class="filter_hashtag">
	                <input type="checkbox" name="cTag" id="hashtag_radio1" class="tag" value="반려견">
	                <label for="hashtag_radio1">반려견</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio2" class="tag" value="그늘">
	                <label for="hashtag_radio2">그늘</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio3" class="tag" value="봄">
	                <label for="hashtag_radio3">봄</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio4" class="tag" value="여름">
	                <label for="hashtag_radio4">여름</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio5" class="tag" value="가을">
	                <label for="hashtag_radio5">가을</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio6" class="tag" value="겨울">
	                <label for="hashtag_radio6">겨울</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio7" class="tag" value="재미있는">
	                <label for="hashtag_radio7">재미있는</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio8" class="tag" value="조용한"> 
	                <label for="hashtag_radio8">조용한</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio9" class="tag" value="깨끗한">
            	    <label for="hashtag_radio9">깨끗한</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio10" class="tag" value="별">
	                <label for="hashtag_radio10">별</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio11" class="tag" value="커플">
	                <label for="hashtag_radio11">커플</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio12" class="tag" value="가족">
	                <label for="hashtag_radio12">가족</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio13" class="tag" value="바다">
	                <label for="hashtag_radio13">바다</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio14" class="tag" value="산">
	                <label for="hashtag_radio14">산</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio15" class="tag" value="자연">
	                <label for="hashtag_radio15">자연</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio16" class="tag" value="수영장">
	                <label for="hashtag_radio16">수영장</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio17" class="tag" value="여유">
	                <label for="hashtag_radio17">여유</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio18" class="tag" value="휴식">
	                <label for="hashtag_radio18">휴식</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio19" class="tag" value="쉼">
	                <label for="hashtag_radio19">쉼</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio20" class="tag" value="풍경">
	                <label for="hashtag_radio20">풍경</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio21" class="tag" value="감성">
	                <label for="hashtag_radio21">감성</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio22" class="tag" value="감성캠">
	                <label for="hashtag_radio22">감성캠</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio23" class="tag" value="솔캠">
	                <label for="hashtag_radio23">솔캠</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio24" class="tag" value="친구">
	                <label for="hashtag_radio24">친구</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio25" class="tag" value="액티비티">
	                <label for="hashtag_radio25">액티비티</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio26" class="tag" value="레저">
	                <label for="hashtag_radio26">레저</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio27" class="tag" value="노을">
	                <label for="hashtag_radio27">노을</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio28" class="tag" value="맛집">
	                <label for="hashtag_radio28">맛집</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio29" class="tag" value="카페">
	                <label for="hashtag_radio29">카페</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio30" class="tag" value="디저트">
	                <label for="hashtag_radio30">디저트</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio31" class="tag" value="피톤치드">
	                <label for="hashtag_radio31">피톤치드</label>
	                <input type="checkbox" name="cTag" id="hashtag_radio32" class="tag" value="축제">
	                <label for="hashtag_radio32">축제</label>
	            </div>
	            <span>기준 인원 초과시 추가 요금</span>
				<div class="extra_fee">
					<label>어른 : </label><input type="text" name="extraAdult" value="${vo.extraAdult }"placeholder="예) 30000"> <br/>
					<label>아이 : </label><input type="text" name="extraChild" value="${vo.extraChild }">
				</div>
	            <span>추가 옵션</span> <br/>
	            <div class="option">
            		<c:forEach var="oVo" items="${itemOptionList }">
            			<div>
							<label>옵션 : </label><input type="text" name="optItem" value="${oVo.optItem }">
							<label>가격 : </label><input type="text" name="price" value="${oVo.price }"> 
						</div>
            		</c:forEach>
	            </div>
			</div>
			<label>배치도</label> <br/>
			<img class='pre_map' alt='' src="./image/camping/${vo.itemCode}/map/${campImg.map.sysFile}"/>
			<span class="map_name">${campImg.map.oriFile}</span><br/>
			<label>캠핑장 사진</label>
			<div class="preview_photo">
				<c:forEach var="camp" items="${campImg.wh }">
					<div>
						<img class="" src="./image/camping/${vo.itemCode}/${camp.sysFile}"/>
						<span>${camp.oriFile }</span>
					</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
		
		
		<div>
			<span class="sub_title">요청 내용</span> <br/>
			<label>캠핑장명</label>
			<input type='text' name='iName' value="${hisVo.iName }"/> <br/>
			<label>캠지기 성함</label>
			<input type='text' name='mName' value="${hisVo.mName }"/> <br/>
			<label>캠핑장 대표 번호</label>
			<input type='text' name='phone' value="${hisVo.phone }"/> <br/>
			<label>우편 번호</label>
			<input type='text' name='zipCode' value="${hisVo.zipCode }" readonly/> <br/>
			<label>주소</label>
			<input type='text' name='address1' value="${hisVo.address1}"/> <br/>
			<label>상세주소</label>
			<input type='text' name='address2' value="${hisVo.address2 }"/> <br/>
			<label>캠핑장 소개 제목</label><br/>
			<input type="text" name="title" value="${hisVo.title }"><br/>
			<label>캠핑장 소개 본문</label> <br/>
			<textarea rows="10" cols="65" name="infoText">${hisVo.infoText }</textarea> <br/>
			<label>매너 타임</label>
			<input type="time" name="mStr" value="${hisVo.mStr }"> - <input type="time" name="mEnd" value="${hisVo.mEnd}">  <br/>
			<label>입실 시간</label>
			<input type="time" name="checkIn" value="${hisVo.checkIn }"> - <input type="time" name="checkOut" value="${hisVo.checkOut }">  <br/>
			<label>캠핑장 운영 정책</label> <br/>
			<textarea name="policy"  rows="10" cols="65" >${hisVo.policy }</textarea> <br/>
			<label>캠핑장 환불 정책</label> <br/>
			<textarea name="refund" rows="10" cols="65" >${hisVo.refund }</textarea> <br/>
			
			<input type="hidden" name="sido" value="${hisVo.sido }">
			<input type="hidden" name="latitude" value="${hisVo.latitude }">
			<input type="hidden" name="longitude" value="${hisVo.longitude }">
			
			<div class="filter">
				<span>숙박형태</span>
				<div class="filter_stay">
					<input type="checkbox" name="hiscFilter" id="stay_radio1" value="오토캠핑">
					<label for="stay_radio1">오토캠핑</label>
					<input type="checkbox" name="hiscFilter" id="stay_radio2" value="글램핑">
					<label for="stay_radio2">글램핑</label>
					<input type="checkbox" name="hiscFilter" id="stay_radio3" value="카라반">
					<label for="stay_radio3">카라반</label>
					<input type="checkbox" name="hiscFilter" id="stay_radio4" value="펜션">
					<label for="stay_radio4">펜션</label>
					<input type="checkbox" name="hiscFilter" id="stay_radio5" value="방갈로">
					<label for="stay_radio5">방갈로</label>
					<input type="checkbox" name="hiscFilter" id="stay_radio6" value="차박">
					<label for="stay_radio6">차박</label>
				</div>
				<span>환경</span>
				<div class="filter_environment">
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio1" value="바다">
					<label for="environment_radio1">바다</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio2" value="산">
					<label for="environment_radio2">산</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio3" value="강">
					<label for="environment_radio3">강</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio4" value="호수">
					<label for="environment_radio4">호수</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio5" value="계곡">
					<label for="environment_radio5">계곡</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio6" value="섬">
					<label for="environment_radio6">섬</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio7" value="평야">
					<label for="environment_radio7">평야</label>
					<input type="checkbox" name="hiscFilter" class="environment" id="environment_radio8" value="기타">
					<label for="environment_radio8">기타</label>
				</div>
				<span>입지 형태</span>
				<div class="filter_position">
					<input type="checkbox" name="hiscFilter" id="position_radio1" value="파쇄석">
					<label for="position_radio1">파쇄석</label>
					<input type="checkbox" name="hiscFilter" id="position_radio2" value="데크">
					<label for="position_radio2">데크</label>
					<input type="checkbox" name="hiscFilter" id="position_radio3" value="잔디">
					<label for="position_radio3">잔디</label>
					<input type="checkbox" name="hiscFilter" id="position_radio4" value="모래">
					<label for="position_radio4">모래</label>
					<input type="checkbox" name="hiscFilter" id="position_radio5" value="자갈">
					<label for="position_radio5">자갈</label>
					<input type="checkbox" name="hiscFilter" id="position_radio6" value="혼합">
					<label for="position_radio6">혼합</label>
					<input type="checkbox" name="hiscFilter" id="position_radio7" value="기타">
					<label for="position_radio7">기타</label>
				</div>
				<span>기본 시설</span>
				<div class="filter_facility">
					<input type="checkbox" name="hiscFilter" id="facility_radio1" value="화장실">
					<label for="facility_radio1">화장실</label>
					<input type="checkbox" name="hiscFilter" id="facility_radio2" value="샤워대">
					<label for="facility_radio2">샤워대</label>
					<input type="checkbox" name="hiscFilter" id="facility_radio3" value="개수대">
					<label for="facility_radio3">개수대</label>
					<input type="checkbox" name="hiscFilter" id="facility_radio4" value="매점">
					<label for="facility_radio4">매점</label>
					<input type="checkbox" name="hiscFilter" id="facility_radio5" value="카페">
					<label for="facility_radio5">카페</label>
					<input type="checkbox" name="hiscFilter" id="facility_radio6" value="와이파이">
					<label for="facility_radio6">와이파이</label>
				</div>
				<span>부가 시설</span>
				<div class="filter_option">
					<input type="checkbox" name="hiscFilter" id="option_radio1" value="놀이시설">
					<label for="option_radio1">놀이시설</label>
					<input type="checkbox" name="hiscFilter" id="option_radio2" value="체험활동">
					<label for="option_radio2">체험활동</label>
					<input type="checkbox" name="hiscFilter" id="option_radio3" value="수영장">
					<label for="option_radio3">수영장</label>
					<input type="checkbox" name="hiscFilter" id="option_radio4" value="트램펄린">
					<label for="option_radio4">트램펄린</label>
					<input type="checkbox" name="hiscFilter" id="option_radio5" value="산책로">
					<label for="option_radio5">산책로</label>
					<input type="checkbox" name="hiscFilter" id="option_radio6" value="장비대여">
					<label for="option_radio6">장비대여</label>
					<input type="checkbox" name="hiscFilter" id="option_radio7" value="반려동물">
					<label for="option_radio7">반려동물</label>
					<input type="checkbox" name="hiscFilter" id="option_radio8" value="트레일러 진입">
					<label for="option_radio8">트레일러 진입</label>
					<input type="checkbox" name="hiscFilter" id="option_radio9" value="카라반 진입">
					<label for="option_radio9">카라반 진입</label>
				</div>
				<span>레저</span>
				<div class="filter_leisure">
					<input type="checkbox" name="hiscFilter" id="leisure_radio1" value="물놀이">
					<label for="leisure_radio1">물놀이</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio2" value="해수욕장">
					<label for="leisure_radio2">해수욕장</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio3" value="낚시">
					<label for="leisure_radio3">낚시</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio4" value="MTB">
					<label for="leisure_radio4">MTB</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio5" value="등산">
					<label for="leisure_radio5">등산</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio6" value="스키">
					<label for="leisure_radio6">스키</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio7" value="수상레저">
					<label for="leisure_radio7">수상레저</label>
					<input type="checkbox" name="hiscFilter" id="leisure_radio8" value="기타">
					<label for="leisure_radio8">기타</label>
				</div>
				
				<span>해시태그</span>  
               	<div class="filter_hashtag">
	                <input type="checkbox" name="hiscTag" id="hashtag_radio1" class="tag" value="반려견">
	                <label for="hashtag_radio1">반려견</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio2" class="tag" value="그늘">
	                <label for="hashtag_radio2">그늘</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio3" class="tag" value="봄">
	                <label for="hashtag_radio3">봄</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio4" class="tag" value="여름">
	                <label for="hashtag_radio4">여름</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio5" class="tag" value="가을">
	                <label for="hashtag_radio5">가을</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio6" class="tag" value="겨울">
	                <label for="hashtag_radio6">겨울</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio7" class="tag" value="재미있는">
	                <label for="hashtag_radio7">재미있는</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio8" class="tag" value="조용한"> 
	                <label for="hashtag_radio8">조용한</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio9" class="tag" value="깨끗한">
            	    <label for="hashtag_radio9">깨끗한</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio10" class="tag" value="별">
	                <label for="hashtag_radio10">별</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio11" class="tag" value="커플">
	                <label for="hashtag_radio11">커플</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio12" class="tag" value="가족">
	                <label for="hashtag_radio12">가족</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio13" class="tag" value="바다">
	                <label for="hashtag_radio13">바다</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio14" class="tag" value="산">
	                <label for="hashtag_radio14">산</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio15" class="tag" value="자연">
	                <label for="hashtag_radio15">자연</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio16" class="tag" value="수영장">
	                <label for="hashtag_radio16">수영장</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio17" class="tag" value="여유">
	                <label for="hashtag_radio17">여유</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio18" class="tag" value="휴식">
	                <label for="hashtag_radio18">휴식</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio19" class="tag" value="쉼">
	                <label for="hashtag_radio19">쉼</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio20" class="tag" value="풍경">
	                <label for="hashtag_radio20">풍경</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio21" class="tag" value="감성">
	                <label for="hashtag_radio21">감성</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio22" class="tag" value="감성캠">
	                <label for="hashtag_radio22">감성캠</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio23" class="tag" value="솔캠">
	                <label for="hashtag_radio23">솔캠</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio24" class="tag" value="친구">
	                <label for="hashtag_radio24">친구</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio25" class="tag" value="액티비티">
	                <label for="hashtag_radio25">액티비티</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio26" class="tag" value="레저">
	                <label for="hashtag_radio26">레저</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio27" class="tag" value="노을">
	                <label for="hashtag_radio27">노을</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio28" class="tag" value="맛집">
	                <label for="hashtag_radio28">맛집</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio29" class="tag" value="카페">
	                <label for="hashtag_radio29">카페</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio30" class="tag" value="디저트">
	                <label for="hashtag_radio30">디저트</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio31" class="tag" value="피톤치드">
	                <label for="hashtag_radio31">피톤치드</label>
	                <input type="checkbox" name="hiscTag" id="hashtag_radio32" class="tag" value="축제">
	                <label for="hashtag_radio32">축제</label>
	            </div>
	            <span>기준 인원 초과시 추가 요금</span>
				<div class="extra_fee">
					<label>어른 : </label><input type="text" name="extraAdult" value="${hisVo.extraAdult }"placeholder="예) 30000"> <br/>
					<label>아이 : </label><input type="text" name="extraChild" value="${hisVo.extraChild }">
				</div>
	            <span>추가 옵션</span> <br/>
	            <div class="option">
            		<c:forEach var="ohVo" items="${hisItemOptionList }">
            			<div>
							<label>옵션 : </label><input type="text" name="optItem" value="${ohVo.optItem }">
							<label>가격 : </label><input type="text" name="price" value="${ohVo.price }"> 
						</div>
            		</c:forEach>
	            </div>
			</div>
			<label>배치도</label> <br/>
			<c:if test="${hisCamp.map.sysFile != ''}">
				<img class='pre_map' src="./image/camping/${hisVo.itemCode}/map/${hisCampImg.map.sysFile}"/>			
			</c:if>
			<span class="map_name">${hisCampImg.map.oriFile}</span><br/>
			<label>캠핑장 사진</label>
			<div class="preview_photo">
				<c:forEach var="camp" items="${hisCampImg.wh }">
					<div>
						<img class="" src="./image/camping/${hisVo.itemCode}/${camp.sysFile}"/>
						<span>${camp.oriFile }</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<c:if test="${(sessionScope.job == 'A' or sessionScope.job == 'E') 
				and (hisVo.state == '등록요청' or hisVo.state == '수정요청')}">
		<div class="btns">
			<button type="button" class="btn_confirm" onclick="camp.confirm()">승인</button> <br/>
			<button type="button" class="btn_reject" onclick="camp.reject()">반려</button>
		</div>	
	</c:if>
	
	<div class="temp_category">
		<c:forEach var="cVo" items="${categoryList }">
			<span class="temp_filter">${cVo.cFilter}</span>
			<span class="temp_tag">${cVo.cTag}</span>
		</c:forEach>
	</div>
	<div class="temp_his_category">
		<c:forEach var="chVo" items="${hisCategoryList }">
			<span class="temp_his_filter">${chVo.cFilter}</span>
			<span class="temp_his_tag">${chVo.cTag}</span>
		</c:forEach>
	</div>
</div>	
</body>

<script>
	$(function(){
		let tempFilter = $(".temp_filter");
		let tempTag = $(".temp_tag");
		let filter = $("input[name=cFilter]");
		let tag = $("input[name=cTag]");
		
		
		let tempHisFilter = $(".temp_his_filter");
		let tempHisTag = $(".temp_his_tag");
		let hisFilter = $("input[name=hiscFilter]");
		let hisTag = $("input[name=hiscTag]");
		
		for(let i = 0; i < tempFilter.length; i++){
			if($(tempFilter[i]).text() != ""){
				for(let j = 0; j < filter.length; j++){
					if($(tempFilter[i]).text() == $(filter[j]).val()){
						$(filter[j]).prop("checked", true);
						break;						
					}
				}
			}
		}
			
		for(let i = 0; i < tempTag.length; i++){
			if($(tempTag[i]).text() != ""){
				for(let j = 0; j < tag.length; j++){
					if($(tempTag[i]).text() == $(tag[j]).val()){
						$(tag[j]).prop("checked", true);
						break;						
					}
				}
			}
		}	
		
		for(let i = 0; i < tempHisFilter.length; i++){
			if($(tempHisFilter[i]).text() != ""){
				for(let j = 0; j < hisFilter.length; j++){
					if($(tempHisFilter[i]).text() == $(hisFilter[j]).val()){
						$(hisFilter[j]).prop("checked", true);
						break;						
					}
				}
			}
		}
			
		for(let i = 0; i < tempHisTag.length; i++){
			if($(tempHisTag[i]).text() != ""){
				for(let j = 0; j < hisTag.length; j++){
					if($(tempHisTag[i]).text() == $(hisTag[j]).val()){
						$(hisTag[j]).prop("checked", true);
						break;						
					}
				}
			}
		}		
	})
</script>
</html>