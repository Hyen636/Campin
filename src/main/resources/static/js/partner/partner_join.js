partner_join = {};
partner_join_view = {};

partner_join.cIdDuplication = true; //중복
partner_join.cPhoneDuplication = true;
partner_join.cEmailDuplication = true;
partner_join.cBusiNumDuplication = true;

/*partner_join */
//은행 선택값 전달
$('.bank').on('change', function() {
   let selected = $(".bank option:checked").text();
   $('.bank').val(selected);
});

// 체크박스 전체 선택
$(".partner_join_terms").on("click", "#check_all", function() {
   $(this).parents(".partner_join_terms").find('input').prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".check").on("change", function() {
   let check1 = $("#check_1").prop("checked");
   let check2 = $("#check_2").prop("checked");

   if (check1 && check2) {
      $("#check_all").prop("checked", true);
   } else {
      $("#check_all").prop("checked", false);
   }
})

//사업자등록증
partner_join.busiImg = function(f) {
   let files = f.files;
   let file = Array.prototype.slice.call(files)[0];
   let str = '';
   let size = parseInt(file.size / 10 * 10 * 20);
   let dt = new DataTransfer();

   str += `${file.name}(${size}KB)`

   $('.att_busiImg').val(str);

   dt.items.add(file);
   $('#busiImg')[0].files = dt.files;
}

//관광사업 등록증
partner_join.travelImg = function(f) {

   let files = f.files;
   let file = Array.prototype.slice.call(files)[0];
   let str = '';
   let size = parseInt(file.size / 10 * 10 * 20);
   let dt = new DataTransfer();

   str += `${file.name}(${size}KB)`
   $('.att_travelImg').val(str);

   dt.items.add(file);
   $('#travelImg')[0].files = dt.files;
}

//체크박스 동의체크 되어야 버튼 눌림 
//필수 항목 유효성체크 후 회원가입
partner_join.cjoinR = function() {

   if (partner_join.cjoinR.joinCheck() == true) {
      //console.log("joincheck ok..");
      let param = $('.frm_partner_join').serialize();
      //console.log(param);
      $.ajax({
         data: param,
         url: 'partner_joinR',  //히스토리, 리퀘스트 저장도 같이
         type: 'POST',
         cache: false,
         success: function(resp) {
            console.log(resp);
            $("input[name=cUserCode]").val(resp);
            //console.log( 'resp해온 값 : ', $("input[name=cUserCode]").val(resp) );   
            let temp = $('.frm_partner_join_att')[0];
            let formData = new FormData(temp);
            //첨부파일 저장
            $.ajax({
               data: formData,
               url: 'joinFileUpload',  //히스토리 업데이트도 같이
               type: 'POST',
               enctype: 'multipart/form-data',
               cache: false,
               processData: false,
               contentType: false,
               success: function(resp) {
                  alert('[CAMPIN] 캠지기로 회원가입 되었습니다. 회원가입 승인 이후 캠핑장 등록이 가능합니다. 캠핀을 선택해주셔서 감사합니다!');
                  $("input[name=hisSno]").val(resp);
                  location.href='admin';
                     },
                     error: function(ex) {
                        console.log("error", ex);
                     }
                  })
               }, error: function(ex) {
                  console.log("error", ex);
               }
            })

         }
}

partner_join.cjoinR.joinCheck = function() {

   let cId = frm_partner_join.cId.value;
   let cPwd = frm_partner_join.cPwd.value;
   let cmName = frm_partner_join.cmName.value;
   let cPhone = frm_partner_join.cPhone.value;
   let cEmail = frm_partner_join.cEmail.value;
   let cbusiNum = frm_partner_join.cbusiNum.value;
   let cbusiEmail = frm_partner_join.cbusiEmail.value;
   let check_all = document.getElementById("check_all");

   if (!iscId(cId)) {
      alert("아이디를 확인해주세요.");
      return false;
   } else if (!iscPwd(cPwd)) {
      alert("비밀번호를 확인해주세요.");
      return false;
   } else if (!iscmName(cmName)) {
      alert("성명을 확인해주세요.");
      return false;
   } else if (!iscEmail(cEmail)) {
      alert("이메일을 확인해주세요.");
      return false;
   } else if (!iscPhone(cPhone)) {
      alert("휴대전화 번호를 확인해주세요.");
      return false;
   } else if (!iscbusiNum(cbusiNum)) {
      alert("사업자 등록번호를 확인해주세요.");
      return false;
   } else if (!iscbusiEmail(cbusiEmail)) {
      alert("세금계산서용 이메일 주소를 확인해주세요.");
      return false;
   } else if (partner_join.cIdDuplication || partner_join.cEmailDuplication ||
      partner_join.cPhoneDuplication || partner_join.cbusiNumDuplication) {
      alert("중복된 입력 사항이 있습니다. 확인해주세요");
      return false;
   } else if (!check_all.checked) {    // 체크박스 동의
      alert("이용약관에 전체동의 후 가입 가능합니다.");
      $('.partner_join_terms').focus();
      return false;
   } else {
      return true;
   }
}

//유효사항 체크
$("#cId").on("blur", function() {
   let cId = $(this).val();
   let param = $(".frm_partner_join").serialize();

   if (!iscId(cId)) {
      $("#cIdValidation").text("6~12자 사이의 영문 소문자와 숫자로 입력해주세요.")
   } else {
      $.ajax({
         url: "cIdValidation",
         type: "POST",
         cache: false,
         data: param,
         success: function(resp) {
            if (resp == 'validation') {
               $("#cIdValidation").text("중복된 아이디입니다.");
               $("#cIdValidation").css("color", "#dd3115")
               partner_join.cIdDuplication = true;
            } else {
               $("#cIdValidation").text("사용가능한 아이디입니다.");
               $("#cIdValidation").css("color", "#f8ca37")
               partner_join.cIdDuplication = false;
            }
         }
      })
   }
})

$("#cPwd").on("blur", function() {
   let cPwd = $(this).val();

   if (!iscPwd(cPwd)) {
      $("#cPwdValidation").text("대소문자 영어, 숫자를 사용하여 8~16자리로 입력해주세요.")
   } else {
      $("#cPwdValidation").text("")
   }
})

//비밀번호 일치여부 체크
$('#pwdCheck').on('keyup', function() {
   if ($('#cPwd').val() != $('#pwdCheck').val()) {
      $("#cPwdCheckValidation").text("비밀번호를 확인해주세요");
   } else {
      $("#cPwdCheckValidation").text("");
   }
})
$("#cmName").on("blur", function() {
   let cmName = $(this).val();
   if (!iscmName(cmName)) {
      $("#cmNameValidation").text("한글 2~6자리의 이름을 입력해주세요.")
   } else {
      $("#cmNameValidation").text("")
   }
})
$("#cPhone").on("blur", function() {
   let cPhone = $(this).val();
   let param = $(".frm_partner_join").serialize();
   if (!iscPhone(cPhone)) {
      $("#cPhoneValidation").text("휴대폰 번호를 다시 확인해주세요.")
   } else {
      // 중복 테스트
      $.ajax({
         url: "cPhoneValidation",
         type: "POST",
         cache: false,
         data: param, // data에 바로 serialze한 데이터를 넣는다.
         success: function(resp) {
            if (resp == 'validation') {
               $("#cPhoneValidation").text("중복된 휴대폰 번호입니다.");
               $("#cPhoneValidation").css("color", "#dd3115")
               partner_join.cPhoneDuplication = true;
            } else {
               $("#cPhoneValidation").text("사용가능한 휴대폰 번호입니다.");
               $("#cPhoneValidation").css("color", "#f8ca37")
               partner_join.cPhoneDuplication = false;
            }
         }
      })
   }
})
$("#cEmail").on("blur", function() {
   let cEmail = $(this).val();
   let param = $(".frm_partner_join").serialize();

   if (!iscEmail(cEmail)) {
      $("#cEmailValidation").text("이메일 주소를 다시 확인해주세요.")
   } else {
      // 중복 테스트
      $.ajax({
         url: "cEmailValidation",
         type: "POST",
         cache: false,
         data: param,
         success: function(resp) {
            if (resp == 'validation') {
               $("#cEmailValidation").text("중복된 이메일입니다.");
               $("#cEmailValidation").css("color", "#dd3115");
               partner_join.cEmailDuplication = true;
            } else {
               $("#cEmailValidation").text("사용가능한 이메일입니다.");
               $("#cEmailValidation").css("color", "#f8ca37");
               partner_join.cEmailDuplication = false;
            }
         }
      })
   }
})

$("#cbusiNum").on("blur", function() {
   let cbusiNum = $(this).val();
   let param = $(".frm_partner_join").serialize();

   if (!iscbusiNum(cbusiNum)) {
      $("#cBusiNumValidation").text("-를 제외한 11-12자리의 사업자등록번호를 입력해주세요.")
   } else {
      // 중복 테스트
      $.ajax({
         url: "cBusiNumValidation",
         type: "POST",
         cache: false,
         data: param,
         success: function(resp) {
            if (resp == 'validation') {
               $("#cBusiNumValidation").text("중복된 사업자 등록 번호입니다.");
               $("#cBusiNumValidation").css("color", "#dd3115");
               partner_join.cBusiNumDuplication = true;
            } else {
               $("#cBusiNumValidation").text("가입 가능한 사업자 등록번호입니다.");
               $("#cBusiNumValidation").css("color", "#f8ca37");
               partner_join.cBusiNumDuplication = false;
            }
         }
      })
   }
})
$("#cbusiEmail").on("blur", function() {
   let cbusiEmail = $(this).val();

   if (!iscbusiEmail(cbusiEmail)) {
      $("#cBusiEmailValidation").text("이메일을 확인후 작성해주세요.")
   } else {
      $("#cBusiEmailValidation").text("")
   }
})


//정규식으로 유효성 검사
function iscId(cId) {
   let cidRegExp = /^[a-z0-9]{6,12}$/;
   return cidRegExp.test(cId);
}

function iscPwd(cPwd) {
   let cpwdRegExp = /^(?=.*\d)(?=.*[A-Za-z]).{8,16}$/;
   return cpwdRegExp.test(cPwd);
}

function iscmName(cmName) {
   let cmNameRegExp = /^[가-힣]{2,6}$/;
   return cmNameRegExp.test(cmName);
}

function iscPhone(cPhone) {
   let cphoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
   return cphoneRegExp.test(cPhone);
}

function iscEmail(cEmail) {
   let cEmailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
   return cEmailRegExp.test(cEmail);
}

function iscbusiNum(cbusiNum) {
   let cbusiNumRegExp = /^[0-9]{11,12}$/;  //0~9까지의 숫자만 11-12자리
   return cbusiNumRegExp.test(cbusiNum);
}

function iscbusiEmail(cbusiEmail) {
   let cbusiEmailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
   return cbusiEmailRegExp.test(cbusiEmail);
}


/*partner_find_id*/
//아이디찾기
//아이디 찾기 버튼에 따라 항목 보여주기
$(".btn_pPhone").on("click", function() {
   $(".findPidPhone").css("display", "block");
   $(".findPidEmail").css("display", "none");
   $(this).css("border-bottom", "2px solid #f8ca37");
   $(".btn_pEmail").css("border-bottom", "none");
   $("#pName").val("")
   $("#pemail").val("")
   $("#pNameValidation").text("")
   $("#pEmailValidation").text("")
})
$(".btn_pEmail").on("click", function() {
   $(".findPidPhone").css("display", "none");
   $(".findPidEmail").css("display", "block");
   $(this).css("border-bottom", "2px solid #f8ca37");
   $(".btn_pPhone").css("border-bottom", "none");
   $("#pName").val("")
   $("#phone").val("")
   $("#pNameValidation").text("")
   $("#PhoneValidation").text("")
})

/*partner_join view */
partner_join_view.list = function(frm){
   frm.action = 'partner_request_list';
   frm.submit();
}

let state = frm_partner_join.state.value;
//console.log("state.." , state);

(function () {
    if (state == "등록요청") {
        $(".btn_join_modify").css("display", "inline-block");
    } else if (state == "요청반려"){
        $(".btn_join_modify").css("display", "inline-block");
    } else if(state == "등록완료"){
		$(".btn_join_modify").css("display", "none");
	}
})();

partner_join_view.modify = function(frm){
	
	window.scrollTo(0,0);
	
	$(".modi2").css("display", "inline-block");
	$(".modi1").css("display", "none");
	
	$(".btn_modisave").css("display", "inline-block");
	$(".btn_modidelete").css("display", "inline-block");
	$(".btn_mcancel").css("display", "inline-block");
	
	$(".btn_join_list").css("display", "none");
	$(".btn_join_modify").css("display", "none");
	
	$("#cId").attr("disabled",false);
	$("#cId").attr("readonly",true);
	$("#cPwd").attr("disabled",false);
	$("#cPwd").attr("readonly",true);
	$("#pwdCheck").attr("disabled",false);
	$("#pwdCheck").attr("readonly",true);
	$("#cmName").attr("disabled",false);
	$("#cPhone").attr("disabled",false);
	$("#cEmail").attr("disabled",false);
	$("#cbirth").attr("disabled",false);
	$("#m").attr("disabled",false);
	$("#f").attr("disabled",false);
	$("#cbusiBankName").attr("disabled",false);
	$("#cbusiBank").attr("disabled",false);
	$("#cbusiBankNum").attr("disabled",false);
	$("#cbusiName").attr("disabled",false);
	$("#crepName").attr("disabled",false);
	$("#cbusiNum").attr("disabled",false);
	$("#ctravelBus").attr("disabled",false);
	$("#cbusiEmail").attr("disabled",false);
	$("#cbusiPhone").attr("disabled",false);
	
	$("#att_busiImg").attr("disabled",false);
	$("#busiImg").attr("disabled",false);
	$("#att_travelImg").attr("disabled",false);
	$("#travelImg").attr("disabled",false);
}

//테스트전 //가입건 첨부파일이랑 텍스트 수정 //첨부파일 추가는 되었음 삭제만 하면됌
partner_join_view.save = function(){
	
	let modi1 = $('.att_busiImg').val();
	let modi2 = $('.att_travelImg').val();
	
	$("input[name=modiBusi]").val(modi1);
	$("input[name=modiTravel]").val(modi2);
	
	let param = $('.frm_partner_join').serialize();
	console.log(param);

	$.post('partner_join_reqModi', param, function(resp){
		let temp = $('.frm_partner_join_att')[0];
		let frmAtt = new FormData(temp);
		//첨부파일 저장
		$.ajax({
			data : frmAtt,
			type : 'POST',
			url : 'joinFileUpload',
			enctype : 'multipart/form-data',
			cache : false,
			contentType : false,
			processData : false,
			success : function(){
				alert("저장되었습니다.");
				location.href='partner_request_list';
			}, error: function(ex) {
                        console.log("error", ex);
               }
		})
		
	})
}

partner_join_view.delete = function(){
	
   	let yn = confirm('[CAMPIN] 해당 가입 요청건을 정말 삭제하시겠습니까? 삭제하면 캠지기 가입을 다시 하셔야 합니다.');
	if(!yn) return;
	
	let frm = document.frm_partner_join;
	
		//let pwd = prompt("[CAMPIN] 회원가입 요청을 삭제하려면 비밀빈호를 입력해주세요.");
		//if(pwd == null) return;	// 암호가 없으면 return;
		//frm.pwd.value = pwd;	// 암호가 있으면 값을 넣고
		
	let param = $(frm).serialize();
	$.post('partner_join_reqDel', param, function(resp){
			alert("[CAMPIN] 회원님의 회원가입 승인요청건이 삭제되었습니다. 감사합니다.");
            location.href='admin';	
	})
}

partner_join_view.cancel = function(frm){
   frm.action = 'partner_request_list';
   frm.submit();
}



