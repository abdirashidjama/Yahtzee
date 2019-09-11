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
	
	public void testPlayerReceivesDies(){
		//initialize player and dies and put them in an array
		Player player = new Player();
		Die [] dice = new Die [5];
		for(int i=0; i <=4; i++) {
			dice[i] = new Die(); 
		}
		//player receives the dies
		player.receiveDice(dice);
		//test all dies have initial value of 0
		Die [] receivedDies = player.getDice();
		assertEquals(0, receivedDies[0].getValue());
		assertEquals(0, receivedDies[1].getValue());
		assertEquals(0, receivedDies[2].getValue());
		assertEquals(0, receivedDies[3].getValue());
		assertEquals(0, receivedDies[4].getValue());	
	}
	
	public void testPlayerReceivesandRollsDice(){
		//initialize player and dies and put them in an array
		Player player = new Player();
		Die [] dies = new Die [5];
		for(int i=0; i <=4; i++) {
			dies[i] = new Die(); 
		}
		//player receives the dies
		player.receiveDice(dies);
		
		//player receives the dies and rolls them all
		player.rollDice();
		
		Die [] rolledDice = player.getDice();
		
		//test all dies have a value between 1 and 6
		assertTrue(rolledDice[0].getValue()<=6 || rolledDice[0].getValue()>0);
		assertTrue(rolledDice[1].getValue()<=6 || rolledDice[1].getValue()>0);
		assertTrue(rolledDice[2].getValue()<=6 || rolledDice[2].getValue()>0);
		assertTrue(rolledDice[3].getValue()<=6 || rolledDice[3].getValue()>0);
		assertTrue(rolledDice[4].getValue()<=6 || rolledDice[4].getValue()>0);
		
	}
	
	public void testGameStartsPlayerNoPoints(){
		//set up players and dice objects
		Player [] players = new Player [3];
		for(int i=0; i <=2; i++) {
			players[i] = new Player();
		}
		
		Die [] dice = new Die [5];
		for(int i=0; i <=4; i++) {
			dice[i] = new Die(); 
		}
		
		//Start game
		Game game = new Game();
		game.setPlayers(players);
		game.setDice(dice);
		game.start();
		
		//test players not null
		assertEquals(0, game.getPlayers()[0].getPoints());
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertEquals(0, game.getPlayers()[2].getPoints());
	}
	
	public void testPlayerOneRecievesDiceFirst(){
		//Start game
		Game game= new Game();
		game.start();
		
		//test player1 received dice
		assertNotNull(game.getPlayers()[0].getDice());
		//other players dont have dice
		assertNull(game.getPlayers()[1].getDice());
		assertNull(game.getPlayers()[2].getDice());
	}
}
