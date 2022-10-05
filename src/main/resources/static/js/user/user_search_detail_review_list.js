/**
 * 
 */
let user_search_detail_review_list = {};
 
 
user_search_detail_review_list.show = function(sno){
	$(".review_detail_"+sno).slideToggle("fast");
}

/*

// 더보기 버튼
$(".reviewcontent").slice(0, 4).show(); // 컨텐츠 6개 우선 로드
$(".btn_more_reviews").click(function(e){ 
    e.preventDefault();
    $(".reviewcontent:hidden").slice(0, 4).show(); // 숨겨진 컨텐츠 6개 더 로드
    if($(".reviewcontent:hidden").length == 0){ 
	$(".reviewcontent:hidden").show(); // 남은 컨텐츠 있다면 모두 로드
        $('.btn_more_reviews').css('display','none'); // 모든 컨텐츠 로드한 후 버튼 숨기기
    }
});
*/