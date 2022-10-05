/**
 * 
 */
 
 
 //첨부파일 이름 뜨게 
 $("#file1").on('change',function(){
  var fileName = $("#file1").val();
  $(".upload-name1").val(fileName);
});

 $("#file2").on('change',function(){
  var fileName = $("#file2").val();
  $(".upload-name2").val(fileName);
});


//아이디찾기
//아이디 찾기 버튼에 따라 항목 보여주기
$(".btn_pPhone").on("click", function(){
	$(".findPidPhone").css("display", "block");
	$(".findPidEmail").css("display", "none");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_pEmail").css("border-bottom", "none");
	$("#pName").val("")
	$("#pemail").val("")
	$("#pNameValidation").text("")
	$("#pEmailValidation").text("")
	$("#btnFindPid").attr("onclick", "member.findPhone(this.form)");
})
$(".btn_pEmail").on("click", function(){
	$(".findPidPhone").css("display", "none");
	$(".findPidEmail").css("display", "block");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_pPhone").css("border-bottom", "none");
	$("#pName").val("")
	$("#phone").val("")
	$("#pNameValidation").text("")
	$("#PhoneValidation").text("")
	$("#btnFindPid").attr("onclick", "member.findEmail(this.form)");
})