package com.AbdirashidJamaA1.Yahtzee;

public class Player 
{

	Die [] dice;
	public Die [] getDice() {
		return this.dice;
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
}