package com.game.evolution.action;

import java.util.ArrayList;
import java.util.List;

import com.game.evolution.domain.Card;
import com.opensymphony.xwork2.ActionSupport;

public class PlayAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String aaa;

	// 模拟发牌
	public String sendCards() {
		List<Card> cardList = new ArrayList<Card>();

		for (int i = 1; i < 16; i++) {
			
			
			if (i != 14 && i != 15) {
				for (int j = 0; j < 4; j++) {
					Card card = new Card();
					card.setValue(i);
					switch (j) {
					case 0:
						card.setType("红桃");
						break;
					case 1:
						card.setType("方片");
						break;
					case 2:
						card.setType("黑桃");
						break;
					case 3:
						card.setType("草花");
						break;
					default:
						card.setType("失败");
						break;
					}
					cardList.add(card);
				}
			} else {
				Card card = new Card();
				card.setValue(i);
				switch (i) {
				case 14:
					card.setType("小王");
					break;
				case 15:
					card.setType("大王");
					break;
				default:
					break;
				}
				cardList.add(card);

			}

		}
		for (Card cardmo : cardList) {
			String syo = cardmo.getType() + cardmo.getValue();
			System.out.println(syo);
		}
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
