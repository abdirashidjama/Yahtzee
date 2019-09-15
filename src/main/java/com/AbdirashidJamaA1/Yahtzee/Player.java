package com.AbdirashidJamaA1.Yahtzee;

public class Player 
{
	int points;
	Die [] dice;
	public Die [] getDice() {
		return this.dice;
	}
	public void giveDice(){
		this.dice=null; 
	}
	public void receiveDice(Die [] receivedDies) {
		for(int i=0; i<=4;i++ ){
			receivedDies[i].setValue(0);
		}
		this.dice = receivedDies;
	}
	public void rollDice() {
		for(int i=0; i<=4;i++ ){
			this.dice[i].roll();
		}
	}
	public void rollDie(int i) {
		dice[i].roll();
	}
	
	public int getPoints() {return points;}
	public void setPoints(int i) {this.points=i;}
}