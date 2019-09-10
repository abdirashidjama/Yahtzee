package com.AbdirashidJamaA1.Yahtzee;

public class Die 
{

	int value;
	public int getValue() {
		return value;
	}
	public void roll() {
		this.value = (int)(Math.random()*6 +1);
	}
}