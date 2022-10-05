/**
 * 찜 리스트
 */

let user_wishlist = {};

// 빨간하트 누르면 찜 취소 (찜 목록에서 즉시 삭제)
user_wishlist.wish = function(userCode, itemCode, content){
	let chk = confirm("찜해둔 캠핑장 목록에서 삭제하시겠습니까?");
	if(!chk) return;
	
	let camp = $(content).parent();
	$(camp).remove();
	
	// DB에서 삭제
	$.post('user_delete_wish', "userCode="+userCode+"&itemCode="+itemCode);
	
	location.reload();
	history.go(0);
}

// 상세페이지 이동
user_wishlist.search_detail_page = function(frm){
	//var frm = document.getElementById('frm_wishlist_content');
	console.log("frm : " + frm);
	frm.action='user_to_search_detail';
	frm.submit();
	//희찬!
}

// 더보기 버튼
$(".content").slice(0, 6).show(); // 컨텐츠 6개 우선 로드
$(".btn_more_wishes").click(function(e){ 
    e.preventDefault();
    $(".content:hidden").slice(0, 6).show(); // 숨겨진 컨텐츠 6개 더 로드
    if($(".content:hidden").length == 0){ 
	$(".content:hidden").show(); // 남은 컨텐츠 있다면 모두 로드
        $('.btn_more_wishes').css('display','none'); // 모든 컨텐츠 로드한 후 버튼 숨기기
    }
});
