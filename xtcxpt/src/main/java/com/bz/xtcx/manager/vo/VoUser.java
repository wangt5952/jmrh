package com.bz.xtcx.manager.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoUser {

	private String userName;
	private String password;
	@JsonProperty("isAdmin")
	private boolean isAdmin;
	private String token;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
