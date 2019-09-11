package com.AbdirashidJamaA1.Yahtzee;

public class Game {
	private Player [] players;
	private Die [] dice;
	Game(){
		this.players = new Player[3];
		this.dice = new Die[5];
	}
	public Player [] getPlayers() {return players;}
	
	public Die [] getDice() {return dice;}
	
	public void setPlayers(Player [] p) {this.players = p;}
	
	public void setDice(Die [] d) {this.dice=d;}
	
	public void start() {
		//create 3 players and 5 dice
		for(int i=0; i <=3; i++) {
			this.players[i] = new Player();
			this.players[i].setPoints(0);
			
		}
		for(int i=0; i <=4; i++) {
			this.dice[i] = new Die();
		}
		

	}
	
	
	
}