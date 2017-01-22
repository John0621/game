package com.game.evolution.action;

import com.opensymphony.xwork2.ActionSupport;

public class TableAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String setin() {
		boolean flag = false;
		if (flag) {
			return "success";
		} else {
			return "false";
		}
	}
}
