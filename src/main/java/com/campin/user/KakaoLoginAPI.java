package com.campin.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.campin.mybatis.UserMembershipMapper;


public class KakaoLoginAPI {
	
	@Autowired
	UserMembershipMapper mapper;
	
	// 카카오 로그아웃
	public void kakaoLogout(String accessToken) {
		String reqURL = "http://kapi.kakao.com/v1/user/logout";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer "+accessToken);
			int responseCode = conn.getResponseCode();
			//System.out.println("responseCode : "+responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 네이버 로그아웃
//	public void naverLogout(String accessToken) {
//		String reqURL = "http://kapi.kakao.com/v1/user/logout";
//		
//		try {
//			URL url = new URL(reqURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Authorization", "Bearer "+accessToken);
//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode : "+responseCode);
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			
//			String result = "";
//			String line = "";
//			
//			while((line = br.readLine()) != null) {
//				result += line;
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
	
//	// 카카오 로그인 인가코드, 토큰, api 가져오기
//	public String getAccessToken(String code) {
//		String accessToken = "";
//		String refreshToken = "";
//		String reqURL = "https://kauth.kakao.com/oauth/token";
//		
//		try {
//			URL url = new URL(reqURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//			
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//			StringBuilder sb = new StringBuilder();
//			sb.append("grant_type=authorization_code");
//			sb.append("&client_id=52a5ddfbec7726cdf5d3d2bd372751ad"); // 647a0b3df3e72c1dace2bb1ff14c00d4
//			sb.append("&redirect_uri=http://localhost:8282/user_login_kakao");
//			sb.append("&client_secret=GuimNpCKVrc7s2DGChAHAECqT7HjbKYA");
//			sb.append("&code="+code);
//			
//			bw.write(sb.toString());
//			bw.flush();
//			
//			int responseCode = conn.getResponseCode();
//			System.out.println("response code : "+responseCode);
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			
//			String line = "";
//			String result = "";
//			
//			while((line = br.readLine()) != null) {
//				result += line;
//			}
//			System.out.println("response body : "+result);
//			
//			JsonParser parser = new JsonParser();
//			JsonElement element = parser.parse(result);
//			
//			accessToken = element.getAsJsonObject().get("accessToken").getAsString(); // 여기서 에러나는듯..
//			refreshToken = element.getAsJsonObject().get("refreshToken").getAsString();
//			
//			System.out.println("access token : "+accessToken);
//	        System.out.println("refresh token : "+refreshToken);
//			
//			br.close();
//			bw.close(); // 요건 안 닫는 경우도 있나봄?
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return accessToken;
//	}
//
//	public HashMap<String, Object> getUserInfo(String accessToken) {
//		// 1) 요청하는 회원마다 가진 정보가 다를 수 있어 HashMap 타입으로 선언
//		HashMap<String, Object> userInfo = new HashMap<String, Object>();
//		
//		// 2) 아래의 url로 request하여 userInfo의 정보를 가져올 예정이므로 객체를 통해 url을 담음.
//		String reqUrl = "https://kapi.kakao.com/v2/user/me";
//		try {
//			URL url = new URL(reqUrl);
//			
//			// 3) connection 객체 선언
//			// java에서 HTTP로 데이터를 송,수신하기 위해선 HttpURLConnection 클래스 사용함.
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			
//			// 4) POST 요청을 위해 기본값이 false인 setDoOutput을 true로 변경
//			conn.setRequestMethod("POST"); // 요걸 "GET"으로 하는 경우도 있나봄.. 그럴 경우 하단의 setDoOutput과 setDoInput 코드는 필요 없어짐.
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			
//			// host server에 request에 필요한 KEY값을 세팅
//			conn.setRequestProperty("Authorization", "Bearer "+accessToken);
//			
//			// request : access token, 모든 정보를 받기 위한 용도
//			conn.setRequestProperty("charset", "UTF-8");  // 여기는 혹시 몰라 내가 추가함! 필요 없을 수도 있음.
//			
//			// 5) 카카오 서버로부터 userInfo에 대한 response가 true일 경우 200 아니면 400
//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode : "+responseCode);
//			System.out.println("요청 확인!");
//			
//			// 에러 지점! ★★★★★
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			
//			String line = "";
//			String result = "";
//			
//			while((line = br.readLine()) != null) {
//				result += line;
//			}
//			System.out.println("response body : "+result);
//			
//			JsonParser parser = new JsonParser();
//			JsonElement element = parser.parse(result);
//			
//			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//			
//			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
//			String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();
//			String birthday = kakaoAccount.getAsJsonObject().get("birthday").getAsString();
//			
//			userInfo.put("nickname", nickname);
//			userInfo.put("email", email);
//			userInfo.put("gender", gender);
//			userInfo.put("birthday", birthday);
//			System.out.println("유저 정보 : "+userInfo);
//			// URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정함.
//			// URL 연결은 입출력에 사용될 수 있음.
//			// URL 연결을 출력용으로 사용하려는 경우, DoOutput 플래그를 true로 설정하고, 
//			// 그렇지 않은 경우에는 false로 설정해야 함. 기본값은 false.
//			
//			
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return userInfo;
//	}

}
