package com.campin.user;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.manager.AES;
import com.campin.mybatis.UserMembershipMapper;

@Service
@Transactional
public class UserMembershipService implements UserMembershipInterface {
	PageMembership page;
	AES aes;
	
	@Autowired
	UserMembershipMapper mapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public UserMembershipService() {
		aes = new AES();
	}

	// 회원가입
	@Override
	public boolean join(UserMembershipVo vo) {
		boolean b = true;
		try {
			//암호화
			String pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			String socialJoin = "n";
			vo.setSocialJoin(socialJoin);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mapper.join(vo);
			transaction.commit(status);
		}catch (Exception ex) {
			ex.printStackTrace();
			b = false;
		}
		return b;
	}
	
	// 카카오로 가입한 회원 찾기
	@Transactional(readOnly=true)
	public int findUserForKakao(Kakao_Login_Profile kakaoProfile) {
		String id = "u"+kakaoProfile.getId();
		kakaoProfile.setId(id);
		int userCode = 0;
		
		try {
			userCode = mapper.findUserById(kakaoProfile);
		}catch (Exception ex) {
			ex.getMessage();
		}
		
		return userCode;
	}

	
	// 카카오 아이디와 비번 활용하여 캠핀 임시 아이디와 비번 만들고 회원정보 입력하기
	public void switchInfo(Kakao_Login_Profile kakaoProfile, UserMembershipVo vo) {
		String id = kakaoProfile.getId();
		UUID notUsedPassword = UUID.randomUUID();
		String pwd = aes.enc("campin-"+notUsedPassword);
		String mName = kakaoProfile.getProperties().getNickname();
		String email = kakaoProfile.getkakao_account().getEmail();
		String birth = "0000"+kakaoProfile.getkakao_account().getBirthday();
		String phone = null;
		String gender = "";
		String socialJoin = "y";
		
		if(kakaoProfile.getkakao_account().getGender().equals("female")) {
			gender = "f";
		}else if (kakaoProfile.getkakao_account().getGender().equals("male")) {
			gender = "m";
		}

		vo.setId(id);
		vo.setPwd(pwd);
		vo.setmName(mName);
		vo.setEmail(email);
		vo.setBirth(birth);
		vo.setPhone(phone);
		vo.setGender(gender);
		vo.setSocialJoin(socialJoin);
		
		mapper.join_kakao1(vo);
		
	}
	
	// 카카오로 가입한 회원 - 추가 회원정보 입력
	public boolean profile_modify_kakao(UserMembershipVo vo) {
		boolean b = true;
		try {
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.join_kakao2(vo);
			if(cnt > 0) {
				transaction.commit(status);				
			} else {
				transaction.rollback(status);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	// 카카오 로그인
	public UserMembershipVo login_kakao1(UserMembershipVo vo, HttpServletRequest req) {
		UserMembershipVo rVo = null;
		HttpSession session = req.getSession();
		
		try {
			rVo = mapper.login_kakao1(vo);
			
			if(rVo != null) {
				session.setAttribute("id", rVo.getId());
				session.setAttribute("userCode", rVo.getUserCode());
				session.setAttribute("mName", rVo.getmName());
				//System.out.println("service rVo id : "+rVo.getId());
			}else {
				session.setAttribute("id", null);
				session.setAttribute("userCode", null);
				session.setAttribute("mName", null);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return rVo;
	}
	
	public UserMembershipVo login_kakao2(String id, HttpServletRequest req) {
		UserMembershipVo rVo = null;
		HttpSession session = req.getSession();
		
		try {
			rVo = mapper.login_kakao2(id);
			
			if(rVo != null) {
				session.setAttribute("id", rVo.getId());
				session.setAttribute("userCode", rVo.getUserCode());
				session.setAttribute("mName", rVo.getmName());
				//System.out.println("service rVo id : "+rVo.getId());
			}else {
				session.setAttribute("id", null);
				session.setAttribute("userCode", null);
				session.setAttribute("mName", null);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return rVo;
		
	}
	
	// 네이버로 가입한 회원 찾기
	@Transactional(readOnly=true)
	public int findUserForNaver(Naver_Login_Profile_Response naverProfileResponse) {
		String id = "u"+naverProfileResponse.getResponse().getId();
		naverProfileResponse.getResponse().setId(id.substring(0, 11));
		id = naverProfileResponse.getResponse().getId();
		////System.out.println("10번째까지로 잘라낸 아이디 : "+id);
		
		int userCode = 0;
		
		try {
			userCode = mapper.findUserById_naver(id);
		}catch (Exception ex) {
			ex.getMessage();
		}
		return userCode;
	}
	
	// 네이버 아이디와 비번 활용하여 캠핀 임시 아이디와 비번 만들고 회원정보 입력하기
	public void switchInfo2(Naver_Login_Profile_Response naverProfileResponse, UserMembershipVo vo) {
		String id = naverProfileResponse.getResponse().getId();
		UUID notUsedPassword = UUID.randomUUID();
		String pwd = aes.enc("campin-"+notUsedPassword);
		String mName = naverProfileResponse.getResponse().getName();
		String email = naverProfileResponse.getResponse().getEmail();
		String birth = naverProfileResponse.getResponse().getBirthyear()+"-"+naverProfileResponse.getResponse().getBirthday();
		String phone = naverProfileResponse.getResponse().getMobile();
		String gender = "";
		String socialJoin = "y";
		
		// 휴대폰 번호에서 "-"를 없애줌.
		phone = phone.replaceAll("[-]", "");
		
		if(naverProfileResponse.getResponse().getGender().equals("F")) {
			gender = "f";
		}else if (naverProfileResponse.getResponse().getGender().equals("M")) {
			gender = "m";
		}

		vo.setId(id);
		vo.setPwd(pwd);
		vo.setmName(mName);
		vo.setEmail(email);
		vo.setBirth(birth);
		vo.setPhone(phone);
		vo.setGender(gender);
		vo.setSocialJoin(socialJoin);
		
		mapper.join_naver(vo);
	}
	
	// 네이버 로그인
	public UserMembershipVo login_naver(String id, HttpServletRequest req) {
		UserMembershipVo rVo = null;
		HttpSession session = req.getSession();
		
		try {
			rVo = mapper.login_kakao2(id);
			
			if(rVo != null) {
				session.setAttribute("id", rVo.getId());
				session.setAttribute("userCode", rVo.getUserCode());
				session.setAttribute("mName", rVo.getmName());
				//System.out.println("service rVo id : "+rVo.getId());
			}else {
				session.setAttribute("id", null);
				session.setAttribute("userCode", null);
				session.setAttribute("mName", null);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return rVo;
		
	}
	
	// 로그인
	@Override
	public UserMembershipVo login(UserMembershipVo vo, HttpServletRequest req) {
		UserMembershipVo rVo = null;
		HttpSession session = req.getSession();
		
		try {
			String pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			rVo = mapper.login(vo);
			//System.out.println("service rVo : "+rVo);
			
			if(rVo != null) {
				session.setAttribute("id", rVo.getId());
				session.setAttribute("userCode", rVo.getUserCode());
				session.setAttribute("mName", rVo.getmName());
				//System.out.println("service rVo id : "+rVo.getId());
			}else {
				session.setAttribute("id", null);
				session.setAttribute("userCode", null);
				session.setAttribute("mName", null);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return rVo;
	}
	
	// 로그아웃
	@Override
	public void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("id", null);
		session.setAttribute("userCode", null);
		session.setAttribute("mName", null);
	}
	
	// 휴대폰 번호로 아이디 찾기
	@Override
	public String findIdByPhone(UserMembershipVo vo) {
		String id = "";
		
		try {
			id = mapper.findIdByPhone(vo);
		}catch (Exception ex) {
			ex.getMessage();
		}
		return id;
	}
	
	// 이메일 주소로 아이디 찾기
	@Override
	public String findIdByEmail(UserMembershipVo vo) {
		String id = "";
		
		try {
			id = mapper.findIdByEmail(vo);
		}catch (Exception ex) {
			ex.getMessage();
		}
		return id;
	}
	
	// 아이디 중복 체크
	@Override
	public boolean idValdation(String id) {
		boolean b = true;
		String result = mapper.idValidation(id);
		
		if(result == "" || result == null) {
			b = false;
		}
		return b;
	}
	
	// 이메일 중복 체크 (회원가입 시)
	@Override
	public boolean emailValidation(String email) {
		boolean b = true;
		String result = mapper.emailValidation(email);
		
		if(result == "" || result == null) {
			b = false;
		}
		return b;
	}
	
	// 이메일 중복 체크 (프로필 수정 시)
	@Override
	public String emailValidation2(String email) {
		String chkEmail = "";
		
		try {
			chkEmail = mapper.emailValidation(email);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return chkEmail;
	}
	
	// 휴대폰 중복 체크 (회원가입 시)
	@Override
	public boolean phoneValidation(String phone) {
		boolean b = true;
		String result = mapper.phoneValidation(phone);
		
		if(result == "" || result == null) {
			b = false;
		}
		return b;
	}
	
	// 휴대폰 중복 체크 (프로필 수정 시)
	@Override
	public String phoneValidation2(String phone) {
		String chkPhone = "";
		
		try {
			chkPhone = mapper.phoneValidation(phone);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return chkPhone;
	}
	
	// 임시 비밀번호 생성
	@Override
	public String tempPwd(UserMembershipVo vo) {
		String tempPwd = "";
		for(int i=0; i<13; i++) {
			tempPwd += (char)((Math.random() * 48)+58);
		}
		String encTempPwd = aes.enc(tempPwd);
		////System.out.println("service tempPwd before mapper : "+tempPwd);
		vo.setPwd(encTempPwd);
		mapper.updateTempPwd(vo);
		////System.out.println("service tempPwd after mapper : "+vo.getPwd());
		
		return tempPwd;
	}
	
	// 생성한 임시 비밀번호 이메일로 발송
	@Override
	public String sendEmail(UserMembershipVo vo, String tempPwd) {
		String alert = "";
		String charSet = "UTF-8";
		String hostSMTP = "smtp.naver.com"; 		// gmail 사용시 smtp.gmail.com
		String hostSMTPid = "ksklaura@naver.com";   // 서버 이메일 주소(보내는 사람)
		String hostSMTPpwd = "XEL4UTYUR3HN"; 		// 서버 이메일 비번
		
		// 보내는 사람 이메일주소, 제목, 내용
		String fromEmail = "ksklaura@naver.com"; 	// 보내는 사람 이메일 주소(받는 사람 이메일에 표시됨)
		String fromName = "CAMPIN";  				// 프로젝트 이름 또는 보내는 사람 이름
		String subject = "", msg = "";
		
		subject = "CAMPIN에서 회원님의 임시 비밀번호를 보내드립니다.";
		msg += "<h4>회원님의 비밀번호를 임시 비밀번호로 변경했습니다.</h4>";
		msg += "<span>비밀번호 확인 후 복사하여 로그인해주세요.</span><br/>";
		msg += "<span>로그인 후 비밀번호를 꼭 변경해주세요.</span> <br/>";
		msg += "<span>오늘도 즐거운 CAMPIN 되세요! :)<br/><br/>";
		msg += "<span>임시 비밀번호 : </span><b>" + tempPwd + "</b><br/><br/>";
		msg += "<span>- CAMPIN -<br/>";
		msg += "<span style='font-weight: bold'>" + "</span>";
		
		// 받는 사람 이메일 주소
		String myMail = mapper.emailCheck(vo);
		String mail = vo.getEmail();
		
		////System.out.println("대체 myMail에는 뭐가 들었니? "+myMail);
		////System.out.println("대체 vo.mail에는 뭐가 들었니? "+vo.getEmail());
		
		if(myMail.equals(mail)) { // .equals(vo.getEmail())
			try {
				HtmlEmail email = new HtmlEmail();
				email.setDebug(true);
				email.setCharset(charSet); // 한글 세팅
				email.setHostName(hostSMTP);
				email.setSmtpPort(587);
				
				email.setAuthentication(hostSMTPid, hostSMTPpwd);
				email.addTo(mail, charSet);
				email.setFrom(fromEmail, fromName, charSet);
				email.setSubject(subject);
				email.setHtmlMsg(msg);
				email.send(); // 메일 발송!
				alert = "email sent";
			}catch(Exception e) {
				e.printStackTrace();
				////System.out.println("이메일 발송 중 오류 발생함.");
				
				alert = "이메일 발송 중 오류가 발생했습니다. 잠시 후 다시 시도해주시기 바랍니다.";
			}
		}else {
			alert = "email not sent";
		}
		return alert;
	}
	
	// 프로필 수정 페이지에 회원정보 불러오기
	@Override
	public UserMembershipVo selectOneProfile(String id) {
		UserMembershipVo vo = null;
		try {
			vo = mapper.selectOneProfile(id);
			
			// Date 형식으로 출력되는 생년월일에서 "-"를 없애줌.
			String birth = vo.getBirth();
			birth = birth.replaceAll("[-]", "");
			vo.setBirth(birth);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}
	
	// 프로필 수정
	@Override
	public boolean profile_modify(UserMembershipVo vo) {
		boolean b = true;
		try {
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.profile_modify(vo);
			if(cnt > 0) {
				transaction.commit(status);				
			} else {
				transaction.rollback(status);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	// 입력한 기존 비밀번호와 DB에 저장된 기존 비밀번호 대조
	@Override
	public boolean pwdValidation(String id, String pwd) {
		boolean b = true;
		
		String chkPwd = "";
		////System.out.println("암호화 전 사용자가 입력한 기존 비밀번호 : "+pwd);
		chkPwd = aes.enc(pwd);
		////System.out.println("암호화 후 사용자가 입력한 기존 비밀번호 : "+chkPwd);
		
		String result = mapper.pwdValidation(id);
		////System.out.println("result : "+result);
		
		if(result.equals(chkPwd)) {
			b = false;
		}
		return b;
	}
		
	// 비밀번호 수정
	@Override
	public boolean pwd_modify(UserMembershipVo vo) {
		boolean b = true;
		//String pwd = "";
		////System.out.println("암호화 전 pwd : "+vo.getPwd());
		String pwd = aes.enc(vo.getPwd()); // 암호화
		vo.setPwd(pwd);
		try {
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.pwd_modify(vo);
			////System.out.println("암호화 후 pwd : "+pwd);
			if(cnt > 0) {
				transaction.commit(status);				
			} else {
				transaction.rollback(status);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	// 회원탈퇴
	@Override
	public boolean remove_account(UserMembershipVo vo) {
		boolean b = true;
		String pwd = aes.enc(vo.getPwd()); // 암호화
		vo.setPwd(pwd);
		try {
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.remove_account(vo);
			////System.out.println("여기까지 오긴 했니? service id  : "+vo.getId());
			////System.out.println("여기까지 오긴 했니? service pwd : "+vo.getPwd());
			////System.out.println("여기까지 오긴 했니? service cnt : "+cnt);
			if(cnt > 0) {
				transaction.commit(status);
			} else {
				transaction.rollback(status);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	// 카카오 회원탈퇴
	public boolean remove_account_kakao(UserMembershipVo vo) {
		boolean b = true;
		try {
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.remove_account_kakao(vo);
			if(cnt > 0) {
				transaction.commit(status);
			} else {
				transaction.rollback(status);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	// 회원탈퇴시 소셜 회원가입 여부 확인
	public String findOutIfSocial(UserMembershipVo vo) {
		String socialJoin = "";
		
		try {
			socialJoin = mapper.findOutIfSocial(vo);
		}catch (Exception ex) {
			ex.getMessage();
		}
		return socialJoin;
	}

}
