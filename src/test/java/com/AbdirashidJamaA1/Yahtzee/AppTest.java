package com.AbdirashidJamaA1.Yahtzee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase
{ 

	//test that Die can be rolled and can return a value between 1 and 6
	public void testrollDieOnce() {
		Die die = new Die();
		die.roll();
		//test that first roll return a value between on and 6
		assertTrue(die.getValue()<=6 || die.getValue()>0);
		

	}
	public void testrollDieTwoandManyTimesTest() {
		//Create a set that holds all possible die numbers 1 to 6 and one that we will add result from many rolls.
		
		Set<Integer> outputedNum = new HashSet<Integer>();
		Set<Integer> expectedNum = new HashSet<Integer>();
		Collections.addAll(expectedNum, 1,2,3,4,5,6);	
		Die die = new Die();
		
		//roll twice 
		die.roll();
		die.roll();
			
		//test that value obtained after two rolls is between one and 6
		assertTrue(die.getValue()<=6 || die.getValue()>0);
		
		//Die rolled many times test that all values are returned and are between 1 and 6
		for(int i=0; i <= 1000; i++) {
			die.roll();
			outputedNum.add(die.getValue()); // add value to a set of outputed numbers to sett		
		}
		assertEquals(expectedNum, outputedNum);
		
	}
	
	public void testPlayerRecievesDiesandRollsAll(){
		//initialize player and dies and put them in an array
		Player player = new Player();
		Die [] dies = new Die [5];
		for(int i=0; i <=4; i++) {
			dies[i] = new Die(); 
		}
		//player recieves the dies and rolls them all
		player.recieveDies(dies);
		//test all dies have initial value of 0
		Die [] receivedDies = player.getDies();
		assertEquals(0, receivedDies[0].getValue());
		assertEquals(0, receivedDies[1].getValue());
		assertEquals(0, receivedDies[2].getValue());
		assertEquals(0, receivedDies[3].getValue());
		assertEquals(0, receivedDies[4].getValue());
		
	}
}
