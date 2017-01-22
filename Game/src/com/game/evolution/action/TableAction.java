package com.game.evolution.action;

import com.opensymphony.xwork2.ActionSupport;

public class TableAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String aaa;

	public String setin() {
		boolean flag = false;
		if (flag) {
			this.aaa = "true";
		} else {
			this.aaa = "false";
		}
		return "success";
	}

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}


}
