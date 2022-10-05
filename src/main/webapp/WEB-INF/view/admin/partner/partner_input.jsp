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
<link rel='stylesheet' type='text/css' href='./css/partner/partner_input.css'>
</head>
<body>
<div id='camping_input'>
		<span>캠핑장 등록하기</span>
		<form>
			<div>
				<label>캠핑장명</label>
				<input type='text' name='iName' /> <br/>
				<label>캠지기 성함</label>
				<input type='text' name='mName' /> <br/>
				<label>캠핑장 대표 번호</label>
				<input type='text' name='phone' /> <br/>
				<label>우편 번호</label>
				<input type='text' name='zipcode' /> 
				<button type="button" class="btn_find_zipcode">우편번호 검색</button><br/>
				<label>주소</label>
				<input type='text' name='address1'/> <br/>
				<label>상세주소</label>
				<input type='text' name='address2'/> <br/>
				<label>캠핑장 소개 제목</label><br/>
				<input type="text" name="title" placeholder="캠핑장을 소개할 짧은 한 문장을 적어주세요."> <br/>
				<label>캠핑장 소개 본문</label> <br/>
				<textarea rows="10" cols="65" placeholder="전체적인 캠핑장 컨셉을 적어주세요."></textarea> <br/>
				<label>배치도</label> <label class="btn_map_upload" for="map_file">배치도 올리기</label>
				<span class="caution">캠핑장 배치도를 올려주세요.</span><br/>				
				<div class="preview_map"></div>
				<label>캠핑장 사진</label> <label class="btn_photo_upload" for="photo_file">캠핑장 사진 올리기</label> 
				<span class="caution">캠핑장 5장의 사진을 올려주세요.</span><br/>
				<div class="preview_photo"></div>
				<label>매너 타임</label>
				<input type="time"> - <input type="time">  <br/>
				<label>입실 시간</label>
				<input type="time"> - <input type="time">  <br/>
				<label>캠핑장 운영 정책</label> <br/>
				<textarea  rows="10" cols="65" placeholder="캠핑장의 운영 정책을 적어주세요."></textarea> <br/>
				<label>캠핑장 환불 정책</label> <br/>
				<textarea  rows="10" cols="65" placeholder="캠핑장을 환불 정책을 적어주세요."></textarea> <br/>
								
				<label class="filter_label">캠핑장의 시설 및 레저를 체크해주세요.</label> <br/>
				<div class="filter">
					<span>숙박형태</span>
					<div class="filter_stay">
						<input type="checkbox" name="stay" id="stay_radio1">
						<label for="stay_radio1">오토캠핑</label>
						<input type="checkbox" name="stay" id="stay_radio2">
						<label for="stay_radio2">글램핑</label>
						<input type="checkbox" name="stay" id="stay_radio3">
						<label for="stay_radio3">카라반</label>
						<input type="checkbox" name="stay" id="stay_radio4">
						<label for="stay_radio4">펜션</label>
						<input type="checkbox" name="stay" id="stay_radio5">
						<label for="stay_radio5">방갈로</label>
						<input type="checkbox" name="stay" id="stay_radio6">
						<label for="stay_radio6">차박</label>
					</div>
					<span>환경</span> <span class="caution">최대 2개의 환경을 선택해주세요.</span><br/>
					<div class="filter_environment">
						<input type="checkbox" name="environment" id="environment_radio1">
						<label for="environment_radio1">바다</label>
						<input type="checkbox" name="environment" id="environment_radio2">
						<label for="environment_radio2">산</label>
						<input type="checkbox" name="environment" id="environment_radio3">
						<label for="environment_radio3">강</label>
						<input type="checkbox" name="environment" id="environment_radio4">
						<label for="environment_radio4">호수</label>
						<input type="checkbox" name="environment" id="environment_radio5">
						<label for="environment_radio5">계곡</label>
						<input type="checkbox" name="environment" id="environment_radio6">
						<label for="environment_radio6">섬</label>
						<input type="checkbox" name="environment" id="environment_radio7">
						<label for="environment_radio7">평야</label>
						<input type="checkbox" name="environment" id="environment_radio8">
						<label for="environment_radio8">기타</label>
					</div>
					<span>입지 형태</span>
					<div class="filter_position">
						<input type="checkbox" name="position" id="position_radio1">
						<label for="position_radio1">파쇄석</label>
						<input type="checkbox" name="position" id="position_radio2">
						<label for="position_radio2">데크</label>
						<input type="checkbox" name="position" id="position_radio3">
						<label for="position_radio3">잔디</label>
						<input type="checkbox" name="position" id="position_radio4">
						<label for="position_radio4">모래</label>
						<input type="checkbox" name="position" id="position_radio5">
						<label for="position_radio5">자갈</label>
						<input type="checkbox" name="position" id="position_radio6">
						<label for="position_radio6">혼합</label>
						<input type="checkbox" name="position" id="position_radio7">
						<label for="position_radio7">기타</label>
					</div>
					<span>기본 시설</span>
					<div class="filter_facility">
						<input type="checkbox" name="facility" id="facility_radio1">
						<label for="facility_radio1">화장실</label>
						<input type="checkbox" name="facility" id="facility_radio2">
						<label for="facility_radio2">샤워대</label>
						<input type="checkbox" name="facility" id="facility_radio3">
						<label for="facility_radio3">개수대</label>
						<input type="checkbox" name="facility" id="facility_radio4">
						<label for="facility_radio4">매점</label>
						<input type="checkbox" name="facility" id="facility_radio5">
						<label for="facility_radio5">카페</label>
						<input type="checkbox" name="facility" id="facility_radio6">
						<label for="facility_radio6">와이파이</label>
					</div>
					<span>부가 시설</span>
					<div class="filter_option">
						<input type="checkbox" name="option" id="option_radio1">
						<label for="option_radio1">놀이시설</label>
						<input type="checkbox" name="option" id="option_radio2">
						<label for="option_radio2">체험활동</label>
						<input type="checkbox" name="option" id="option_radio3">
						<label for="option_radio3">수영장</label>
						<input type="checkbox" name="option" id="option_radio4">
						<label for="option_radio4">트램펄린</label>
						<input type="checkbox" name="option" id="option_radio5">
						<label for="option_radio5">산책로</label>
						<input type="checkbox" name="option" id="option_radio6">
						<label for="option_radio6">장비대여</label>
						<input type="checkbox" name="option" id="option_radio7">
						<label for="option_radio7">반려동물</label>
						<input type="checkbox" name="option" id="option_radio8">
						<label for="option_radio8">트레일러 진입</label>
						<input type="checkbox" name="option" id="option_radio9">
						<label for="option_radio9">카라반 진입</label>
					</div>
					<span>레저</span>
					<div class="filter_leisure">
						<input type="checkbox" name="leisure" id="leisure_radio1">
						<label for="leisure_radio1">물놀이</label>
						<input type="checkbox" name="leisure" id="leisure_radio2">
						<label for="leisure_radio2">해수욕장</label>
						<input type="checkbox" name="leisure" id="leisure_radio3">
						<label for="leisure_radio3">낚시</label>
						<input type="checkbox" name="leisure" id="leisure_radio4">
						<label for="leisure_radio4">MTB</label>
						<input type="checkbox" name="leisure" id="leisure_radio5">
						<label for="leisure_radio5">등산</label>
						<input type="checkbox" name="leisure" id="leisure_radio6">
						<label for="leisure_radio6">스키</label>
						<input type="checkbox" name="leisure" id="leisure_radio7">
						<label for="leisure_radio7">수상레저</label>
						<input type="checkbox" name="leisure" id="leisure_radio8">
						<label for="leisure_radio8">기타</label>
					</div>
					
					<span class="caution">사실과 다를 시 불이익을 받으실 수 있으며, 캠핑장 등록의 반려 사유가 될 수 있습니다.</span>
				</div>
				<!-- 저장하면, 서버에 저장 후 사이트 입력 페이지로 이동 -->
				<button type="button" class="btn_save">저장하기</button> <br/>
				<button type="button" class="btn_reset">취소</button>
			</div>
		</form>
		<form class="frm_att">
			<div>
				<input type="file" id="map_file" name="map_file">
				<input type="file" id="photo_file" name="photo_file">
			</div>
		</form>
	</div>	
</body>
</html>