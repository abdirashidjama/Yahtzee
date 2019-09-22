package com.AbdirashidJamaA1.Yahtzee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
		assertTrue(die.getValue()<=6 && die.getValue()>0);
		

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
		assertTrue(die.getValue()<=6 && die.getValue()>0);
		
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
		assertTrue(rolledDice[0].getValue()<=6 && rolledDice[0].getValue()>0);
		assertTrue(rolledDice[1].getValue()<=6 && rolledDice[1].getValue()>0);
		assertTrue(rolledDice[2].getValue()<=6 && rolledDice[2].getValue()>0);
		assertTrue(rolledDice[3].getValue()<=6 && rolledDice[3].getValue()>0);
		assertTrue(rolledDice[4].getValue()<=6 && rolledDice[4].getValue()>0);
		
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
	
	public void testDicePassedProperly(){
		Game game= new Game();
		game.start();
		
		//other players dont have dice
		game.endTurn();
		//player two turn only they should have the dice
		assertNotNull(game.getPlayers()[1].getDice());
		assertNull(game.getPlayers()[2].getDice());
		assertNull(game.getPlayers()[0].getDice());
		game.endTurn();
		//Player 3's turn only they should have the dice
		assertNotNull(game.getPlayers()[2].getDice());
		assertNull(game.getPlayers()[1].getDice());
		assertNull(game.getPlayers()[0].getDice());
		game.endTurn();
		//Player 1 turn only they should have the dice
		assertNotNull(game.getPlayers()[0].getDice());
		assertNull(game.getPlayers()[1].getDice());
		assertNull(game.getPlayers()[2].getDice());
		
	}
	
	public void testTurns() {
		Game game = new Game();
		game.start();	
		//player 1 turn first
		assertEquals("P1", game.getTurn());
		game.endTurn(); //Player 2 turn
		assertEquals("P2", game.getTurn());
		game.endTurn(); //player 3 turn
		assertEquals("P3", game.getTurn());
		game.endTurn(); // back to player 1 turn
		assertEquals("P1", game.getTurn());
	}
	
	public void testPlayerRerollDice() {
		Game game = new Game();
		game.start();
		//p1 has dice		
		game.getPlayers()[0].receiveDice(game.getDice()); //sets all dice to 0 to test rolling
		game.reroll("2 3 4"); //choose to hold inner dice should reroll outer and others should have 0
		assertTrue(game.getDice()[0].getValue()<=6 && game.getDice()[0].getValue()>0);
		assertTrue(game.getDice()[4].getValue()<=6 && game.getDice()[4].getValue()>0);
		assertEquals(0,game.getDice()[1].getValue());
		assertEquals(0,game.getDice()[2].getValue());
		assertEquals(0,game.getDice()[3].getValue());
		//reroll outer dice
		game.getPlayers()[0].receiveDice(game.getDice());
		game.reroll("1 5"); //choose to hold inner dice should reroll outer and others should have 0
		assertTrue(game.getDice()[1].getValue()<=6 && game.getDice()[1].getValue()>0);
		assertTrue(game.getDice()[2].getValue()<=6 && game.getDice()[2].getValue()>0);
		assertTrue(game.getDice()[3].getValue()<=6 && game.getDice()[3].getValue()>0);
		assertEquals(0,game.getDice()[0].getValue());
		assertEquals(0,game.getDice()[4].getValue());
	}
	
	public void testPlayerScoreChance() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(1);
		game.getDice()[1].setValue(2);
		game.getDice()[2].setValue(3);
		game.getDice()[3].setValue(4);
		game.getDice()[4].setValue(5);
		game.score(12);
		assertEquals(15, game.getPlayers()[0].getPoints()); //test they got points added
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(12)); //test they have record of scorring chance
		assertTrue(15==(game.getPlayers()[0].getScoreSheet().get(12)));
	}
	
	public void testUpperSectionScore() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(1);
		game.getDice()[1].setValue(2);
		game.getDice()[2].setValue(3);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(5);
		game.score(1);
		game.score(2);
		game.score(3);
		game.score(4);
		game.score(5);
		game.score(6);
		//check score method works for 1-6 upper section that score exists in score card and correct score for each
		assertEquals(16, game.getPlayers()[0].getPoints()); //test they got points added
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(1)); 
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(2));
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(3));
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(4));
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(5));
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(6));
		assertTrue(1==(game.getPlayers()[0].getScoreSheet().get(1)));
		assertTrue(2==(game.getPlayers()[0].getScoreSheet().get(2)));
		assertTrue(3==(game.getPlayers()[0].getScoreSheet().get(3)));
		assertTrue(0==(game.getPlayers()[0].getScoreSheet().get(4)));
		assertTrue(10==(game.getPlayers()[0].getScoreSheet().get(5)));
		assertTrue(0==(game.getPlayers()[0].getScoreSheet().get(6)));		
	}
	
	public void testThreeOfAKind() {
		//test three of a kind works identifies right number and points when it exists
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(3);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(10);
		assertEquals(18, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(10)); 
		assertTrue(18==(game.getPlayers()[0].getScoreSheet().get(10)));
		
		//test it works when there is no three of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(10);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(10)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(10)));
		
	}
	
	public void testFourOfAKind() {
		//test Four of a kind works identifies right number and points when it exists
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(5);
		game.getDice()[1].setValue(5);
		game.getDice()[2].setValue(2);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(5);
		game.score(11);
		assertEquals(22, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(11)); 
		assertTrue(22==(game.getPlayers()[0].getScoreSheet().get(11)));
		
		//test it works when there is no Four of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(11);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(11)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(11)));	
	}
	
	public void testSmallStraight() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(2);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(8);
		assertEquals(30, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(8)); 
		assertTrue(30==(game.getPlayers()[0].getScoreSheet().get(8)));
		
		//test it works when there is no Four of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(8);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(8)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(8)));	
	}
	public void testLargeStraight() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(4);
		game.getDice()[1].setValue(2);
		game.getDice()[2].setValue(6);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(7);
		assertEquals(40, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(7)); 
		assertTrue(40==(game.getPlayers()[0].getScoreSheet().get(7)));
		
		//test it works when there is no Four of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(7);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(7)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(7)));	
	}
	
	public void testFullHouse() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(3);
		game.getDice()[2].setValue(2);
		game.getDice()[3].setValue(3);
		game.getDice()[4].setValue(2);
		game.score(9);
		assertEquals(25, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(9)); 
		assertTrue(25==(game.getPlayers()[0].getScoreSheet().get(9)));
		
		//test it works when there is no Four of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(9);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(9)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(9)));	
	}
	public void testFirstYahtzee() {
		Game game = new Game();
		game.start();
		game.getDice()[0].setValue(5);
		game.getDice()[1].setValue(5);
		game.getDice()[2].setValue(5);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(5);
		game.score(13);
		assertEquals(50, game.getPlayers()[0].getPoints());
		assertTrue(game.getPlayers()[0].getScoreSheet().containsKey(13)); 
		assertTrue(50==(game.getPlayers()[0].getScoreSheet().get(13)));
		
		//test it works when there is no Four of a kind
		
		game.endTurn();
		game.getDice()[0].setValue(3);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(5);
		game.getDice()[4].setValue(3);
		game.score(13);
		assertEquals(0, game.getPlayers()[1].getPoints());
		assertTrue(game.getPlayers()[1].getScoreSheet().containsKey(13)); 
		assertTrue(0==(game.getPlayers()[1].getScoreSheet().get(13)));	
	}
	
	public void testUpperBonus() {
		//case where there is an upper bonus
		Game game = new Game();
		game.start();
		//rig and give player 1 a scoresheet with 4 upper sections scored
		SortedMap<Integer, Integer> riggedScoreSheet = new TreeMap<Integer, Integer>();
		riggedScoreSheet.put(6, 18);
		riggedScoreSheet.put(5, 15);
		riggedScoreSheet.put(4, 16);
		riggedScoreSheet.put(3, 9);
		game.getPlayers()[0].setScoreSheet(riggedScoreSheet);
		game.getPlayers()[0].setPoints(58);
		//current upper score at 58 with 8 from bottoms two should be at 63 qualifying player for bonus points
		game.getDice()[0].setValue(2);
		game.getDice()[1].setValue(2);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(2);
		game.getDice()[4].setValue(2);		
		game.score(2);
		//total points should include bonus 101
		//score sheet includes bonus upper score at key 14
		assertEquals(101, game.getPlayers()[0].getPoints());
		assertTrue(35==(game.getPlayers()[0].getScoreSheet().get(14)));
		
	}
	
	public void testRounds() {
		Game game = new Game();
		game.start();
		assertEquals(1, game.getPlayers()[0].getRound());  ///player one first round
		game.score(10);
		game.endTurn();   // he ends his turn he should be on second round
		assertEquals(2, game.getPlayers()[0].getRound());
		
		game.score(3);
		game.endTurn();   //Player two end their turn and should be on the second round now	
		assertEquals(2, game.getPlayers()[1].getRound());
		
		game.score(6);
		game.endTurn();
		assertEquals(2, game.getPlayers()[2].getRound(3)); //player 3 is now on their second round
		
		game.score(2);
		game.endTurn();
		assertEquals(3, game.getPlayers()[0].getRound()); //player one should be on third round now
		
		
		
	}

}
