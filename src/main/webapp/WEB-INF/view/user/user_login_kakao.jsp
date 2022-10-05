<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_login_kakao</title>
</head>
<body>
<!-- 	<a href="javascript:kakaoLogin();"></a>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
		// 647a0b3df3e72c1dace2bb1ff14c00d4
		window.Kakao.init("647a0b3df3e72c1dace2bb1ff14c00d4");
		function kakaoLogin(){
			window.Kakao.Auth.login({
				scope:'account_email, gender, birthday',
				success: function(authObj){
					console.log(authObj);
					window.Kakao.API.request({
						url: '/v2/user/me',
						success: res => {
							const kakao_account = res.kakao_account;
							console.log(kakao_account);
						}
					});
				}
			})
		}
	</script> -->
</body>
</html>