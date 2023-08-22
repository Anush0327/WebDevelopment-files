package com.learing.mangatha.controller;

import Oops.Deck;
import Oops.Player;

public class MangathaController {
	private Deck deck;
	private Player p1;
	private Player p2;
	private boolean flag = false;
	public MangathaController() {
		deck = new Deck();
		deck.shuffle();
		p1 = new Player();
		p2 = new Player();
	}
	
	public void reset() {
		deck = new Deck();
		deck.shuffle();
	}
	public String PlayCard() {
		if(isFlag() == false)
			flag = true;
		else
			flag = false;
		return deck.removeFromTop().toString();
	}
	
	public void addPlayers(String bet,String card,String pos,String playerId) {
		if(playerId.equals("Player1")) {
			p1.setPlayer(bet, card, pos);
		} else
			p2.setPlayer(bet,card,pos);
	}
	 public String validate() {
		 String val = deck.getFromTop().toString();
		 if(val.equals(p1.getChoosenCard().toString())) {
			 if(isFlag()==p1.isPosition()) {
			 p1.setCash(p2.getBet());
			 return "Player1 Wins -> " + p1.getCash();
			 }
			 else
				 return String.format("Vendor Wins -> %d", (p1.getBet()+p2.getBet()));
		 }
		 else if(val.equals(p2.getChoosenCard().toString()) ) {
			 if(isFlag() == p2.isPosition()) {
			 p2.setCash(p1.getBet());
			 return "Player2 Wins -> " + p2.getCash();
			 }
			 else {
				 return String.format("Vendor Wins -> %d",(p1.getBet()+p2.getBet()));
			 }
		 }
		 else
			 return "Place next card";
	 }

	public boolean isFlag() {
		return flag;
	}
}
