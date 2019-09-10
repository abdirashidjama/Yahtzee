package com.AbdirashidJamaA1.Yahtzee;

public class Player 
{

	Die [] dies;
	public Die [] getDies() {
		return this.dies;
	}
	public void receiveDies(Die [] receivedDies) {
		for(int i=0; i<=4;i++ ){
			receivedDies[i].setValue(0);
		}
		this.dies = receivedDies;
	}
}