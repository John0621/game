package com.game.evolution.action;

import com.game.evolution.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private LoginService loginservice;
	private String username;
	private String userpassword;
	public String login(){
		boolean flag = loginservice.login(username, userpassword);
		if(flag){
			return "Success";
		}else{
			return "input";
		}
	}
	public LoginService getLoginservice() {
		return loginservice;
	}

	public void setLoginservice(LoginService loginservice) {
		this.loginservice = loginservice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

}