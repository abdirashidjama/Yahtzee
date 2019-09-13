package com.AbdirashidJamaA1.Yahtzee;

public class Game {
	private Player [] players;
	private Die [] dice;
	private String turn;
	Game(){
		this.players = new Player[3];
		this.dice = new Die[5];
	}
	public Player [] getPlayers() {return players;}
	
	public Die [] getDice() {return dice;}
	
	public void setPlayers(Player [] p) {this.players = p;}
	
	public void setDice(Die [] d) {this.dice=d;}
	
	public void endTurn() {
		//ends turn and switches it to next player
		switch(this.turn){
			case "P1":
				this.turn = "P2";
				this.players[1].receiveDice(this.players[0].getDice());
				this.players[0].giveDice();
				break;
			case "P2":
				this.turn = "P3";
				this.players[2].receiveDice(this.players[1].getDice());
				this.players[1].giveDice();
				break;
			case "P3":
				this.turn = "P1";
				this.players[0].receiveDice(this.players[2].getDice());
				this.players[2].giveDice();
				break;	
		}
	}
	public String getTurn() {return this.turn;}
	
	public void start() {
		//create 3 players and 5 dice
		for(int i=0; i <=2; i++) {
			this.players[i] = new Player();
			this.players[i].setPoints(0);
			
		}
		for(int i=0; i <=4; i++) {
			this.dice[i] = new Die();
		}
		//Player 1 turn first
			this.turn = "P1";
		//Give P1 die first
			this.players[0].receiveDice(this.dice);
	}
	
	
	
}