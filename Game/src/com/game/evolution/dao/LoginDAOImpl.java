package com.game.evolution.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.game.evolution.domain.Userinfo;

public class LoginDAOImpl extends HibernateDaoSupport implements LoginDAO {

	@Override
	public boolean login(String username, String password) {
		List<Userinfo> u = this.getHibernateTemplate().find(
				"from userinfo where username = ? and userpassword = ?",
				username, password);
		boolean flag = false;
		if(u.size()>0){
			flag = true;
		}
		return flag;
	}

}
