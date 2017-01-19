package com.game.evolution.service;

import com.game.evolution.dao.LoginDAO;

public class LoginServiceImpl implements LoginService {
	private LoginDAO logindao;

	@Override
	public boolean login(String username, String password) {

		return logindao.login(username, password);
	}

	public LoginDAO getLogindao() {
		return logindao;
	}

	public void setLogindao(LoginDAO logindao) {
		this.logindao = logindao;
	}

}
