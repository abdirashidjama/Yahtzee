package com.AbdirashidJamaA1.Yahtzee;

import java.util.SortedMap;
import java.util.TreeMap;

public class Player 
{
	int points;
	Die [] dice;
	SortedMap<Integer, Integer> scoreSheet;
	
	Player(){
		this.scoreSheet = new TreeMap<Integer, Integer>();
		this.points=0;
	}
	public Die [] getDice() {
		return this.dice;
	}
	public SortedMap<Integer, Integer> getScoreSheet(){
		return scoreSheet;
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
	
	public void score(int i) {
		switch(i) {
		case 12:
			scoreChance();
			break;
		}
	}
	public void scoreChance(){
		int p=0;
		for(int i=0; i<5; i++) {
			p=p+this.dice[i].getValue();
		}
		this.scoreSheet.put(12, p);
		this.points=points +p;
		
	}
}