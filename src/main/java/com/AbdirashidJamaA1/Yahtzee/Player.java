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
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			scoreUpperSection(i);
			break;
		case 7:
			scoreLargeStraight();
			break;
		case 8:
			scoreSmallStraight();
			break;
		case 10:
			scoreThreeOfAKind();
			break;
		case 11:
			scoreFourOfAKind();
			break;
		case 12:
			scoreChance();
			break;
		}
	}
	public void scoreUpperSection(int n) {
		int p=0;
		for(int i=0; i<5; i++) {
			int dieValue = this.dice[i].getValue();
			if(dieValue ==n) {
				p=p+dieValue;
			}
		}
		this.scoreSheet.put(n, p);
		this.points=points+p;
	}
	public void scoreThreeOfAKind() {
		//array hold 5 spots each represent the amount of die of that value exist in dice
		int p=0;
		boolean found= false;
		int[] dieCount = {0,0,0,0,0};
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]++;
		}
		for(int i=0; i<5; i++) {
			if(dieCount[i]>=3) {
				p = (i+1)*3;
				this.points=this.points+p;
				found=true;
			}
		}
		if (!found) {
			this.points=this.points+p;
		}
		this.scoreSheet.put(10, p);
	}
	public void scoreChance(){
		int p=0;
		for(int i=0; i<5; i++) {
			p=p+this.dice[i].getValue();
		}
		this.scoreSheet.put(12, p);
		this.points=points +p;
		
	}
	public void scoreFourOfAKind() {
		//array hold 5 spots each represent the amount of die of that value exist in dice
		int p=0;
		boolean found= false;
		int[] dieCount = {0,0,0,0,0};
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]++;
		}
		for(int i=0; i<5; i++) {
			if(dieCount[i]>=4) {
				p = (i+1)*4;
				this.points=this.points+p;
				found=true;
			}
		}
		if (!found) {
			this.points=this.points+p;
		}
		this.scoreSheet.put(11, p);
	}
	public void scoreSmallStraight() {
		int p=0;
		int count=0;
		int[] dieCount = {0,0,0,0,0,0}; //each number represent if the die face is present
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]=1;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]==1) {
				count++;
				if(count ==4) {
					p=30;
					break;
				}
			}
			else {
				count=0;
			}
		}
		this.points = this.points +p;
		this.scoreSheet.put(8, p);
	}
	public void scoreLargeStraight() {
		int p=0;
		int count=0;
		int[] dieCount = {0,0,0,0,0,0}; //each number represent if the die face is present
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]=1;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]==1) {
				count++;
				if(count ==5) {
					p=40;
					break;
				}
			}
			else {
				count=0;
			}
		}
		this.points = this.points +p;
		this.scoreSheet.put(7, p);
	}

}