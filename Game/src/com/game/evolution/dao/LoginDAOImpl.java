package com.game.evolution.dao;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.game.evolution.domain.Userinfo;
import com.game.evolution.websocket.WebSocketDDZ;
import com.opensymphony.xwork2.ActionContext;

public class LoginDAOImpl extends HibernateDaoSupport implements LoginDAO {

	@Override
	public boolean login(String username, String password) {
		List<Userinfo> u = this.getHibernateTemplate().find(
				"from Userinfo where username = ? and userpassword = ?",
				username, password);
		WebSocketDDZ webSocketTes = null;
		boolean flag = false;
		if (u.size() > 0) {
			Userinfo userinfo = new Userinfo();
			userinfo = u.get(0);
			ActionContext.getContext().getSession().put("userinfo", userinfo);
			flag = true;
		}
		return flag;
	}
}
