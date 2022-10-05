package com.campin.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Kakao_Login_Profile{
	public String id;
	public String connected_at;
	public Properties properties;
	public kakao_account kakao_account;

	public class Properties{
		public String nickname;
		
		// GETTERS AND SETTERS
		
		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	}
	
	public class kakao_account{
		public boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		public Boolean has_birthday;
		public Boolean birthday_needs_agreement;
		public String birthday;
		public String birthday_type;
		public Boolean has_gender;
		public Boolean gender_needs_agreement;
		public String gender;
		
		// GETTERS AND SETTERS
		
		public boolean isprofile_nickname_needs_agreement() {
			return profile_nickname_needs_agreement;
		}

		public void setprofile_nickname_needs_agreement(boolean profile_nickname_needs_agreement) {
			this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public Boolean gethas_email() {
			return has_email;
		}

		public void sethas_email(Boolean has_email) {
			this.has_email = has_email;
		}

		public Boolean getemail_needs_agreement() {
			return email_needs_agreement;
		}

		public void setemail_needs_agreement(Boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
		}

		public Boolean getis_email_valid() {
			return is_email_valid;
		}

		public void setis_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}

		public Boolean getis_email_verified() {
			return is_email_verified;
		}

		public void setis_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean gethas_birthday() {
			return has_birthday;
		}

		public void sethas_birthday(Boolean has_birthday) {
			this.has_birthday = has_birthday;
		}

		public Boolean getbirthday_needs_agreement() {
			return birthday_needs_agreement;
		}

		public void setbirthday_needs_agreement(Boolean birthday_needs_agreement) {
			this.birthday_needs_agreement = birthday_needs_agreement;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getbirthday_type() {
			return birthday_type;
		}

		public void setbirthday_type(String birthday_type) {
			this.birthday_type = birthday_type;
		}

		public Boolean gethas_gender() {
			return has_gender;
		}

		public void sethas_gender(Boolean has_gender) {
			this.has_gender = has_gender;
		}

		public Boolean getgender_needs_agreement() {
			return gender_needs_agreement;
		}

		public void setgender_needs_agreement(Boolean gender_needs_agreement) {
			this.gender_needs_agreement = gender_needs_agreement;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public class Profile{
			public String nickname;

			// GETTERS AND SETTERS
			
			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}
		}
	}

	// GETTERS AND SETTERS
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getconnected_at() {
		return connected_at;
	}

	public void setconnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public kakao_account getkakao_account() {
		return kakao_account;
	}

	public void setkakao_account(kakao_account kakao_account) {
		this.kakao_account = kakao_account;
	}
	
}