package com.AbdirashidJamaA1.Yahtzee;

public class Die 
{
	double value;
	public double getValue() {
		return value;
	}
	public void roll() {
		this.value = Math.random()*4 +1;
	}
}