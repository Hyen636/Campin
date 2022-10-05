admin_login = {};

( function(){
	$(".noti").css("display","inline-block");
	$(".noti2").css("display", "none");
	$(".id").attr("disabled", false);
	$(".pwd").attr("disabled", false);
	$(".find_info").css("display", "block");
	$(".cuser_info").css("display", "block");
	$(".auser_info").css("display", "none");
	$(".btns").css("display", "inline-block");
	$(".btnClogin").css("display", "block");
	$(".btnClogin").attr("disabled", false);
	$(".btnCjoin").css("display", "block");
	$(".btnCjoin").attr("disabled", false);
	$(".btnCjoin").css("margin-top", "-15px");
	$(".btnMlogin").css("display","none");
	$('.btn_camp').css("border-bottom", "2px solid #f8ca37");
	$(".btn_manager").css("border-bottom", "none");
})();


/*admin_login */
/*캠지기 로그인 */
admin_login.clogin = function(frm){
	if (admin_login.loginCheck() == true) {
		let url = 'partner_loginR';
		frm.action = url;
		frm.submit();
	}
}

/* 캠지기 회원가입으로 이동 */
admin_login.cjoin = function(){
	location.href = 'partner_join';
}

/*매니저 로그인*/
admin_login.mlogin = function(frm){
	if (admin_login.loginCheck() == true) {
		let url = 'mloginR';
		frm.action = url;
		frm.submit(); 
	}
}

admin_login.loginCheck = function(){
	
	let id = frm_admin_login.id.value;
	let pwd = frm_admin_login.pwd.value;
	
	if( id == null || id == ""){
		alert("아이디를 입력해주세요.");
		return false;
	}else if( pwd == null || pwd == ""){
		alert("비밀번호를 입력해주세요.");
		return false;
	}else{
		return true;
	}
}

$(".btn_camp").on("click", function(){
	$(".noti").css("display","inline-block");
	$(".noti2").css("display", "none");
	$(".id").attr("disabled", false);
	$(".pwd").attr("disabled", false);
	$(".find_info").css("display", "block");
	$(".cuser_info").css("display", "block");
	$(".auser_info").css("display", "none");
	$(".btns").css("display", "inline-block");
	$(".btnClogin").css("display", "block");
	$(".btnClogin").attr("disabled", false);
	$(".btnCjoin").css("display", "block");
	$(".btnCjoin").attr("disabled", false);
	$(".btnCjoin").css("margin-top", "-15px");
	$(".btnMlogin").css("display","none");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_manager").css("border-bottom", "none");
});

$(".btn_manager").on("click", function(){
	$(".noti").css("display","inline-block");
	$(".noti2").css("display", "none");
	$(".id").attr("disabled", false);
	$(".pwd").attr("disabled", false);
	$(".find_info").css("display", "block");
	$(".cuser_info").css("display", "none");
	$(".auser_info").css("display", "block");
	$(".btns").css("display", "block");
	$(".btnMlogin").css("display", "block");
	$(".btnMlogin").css("margin-top", "-30px");
	$(".btnMlogin").css("margin-left", "150px");
	$(".btnClogin").css("display", "none");
	$(".btnCjoin").css("display", "none");
	$(this).css("border-bottom", "2px solid #f8ca37");
	$(".btn_camp").css("border-bottom", "none");
	$(".login_partner").css("display","none");
});

/*
if(btnFindId != null){
	btnFindId.onclick = function(){
		var frm = document.frm_find_id;  // 여기는 name으로 가져옴
		var url = 'index.jsp?inc=student/find_id_result.jsp';
		frm.action = url;
		frm.submit();
	}
}*/


/*admin_temp*/
