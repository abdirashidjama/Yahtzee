package com.AbdirashidJamaA1.Yahtzee;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.*;


public class UpperScoreBonusStepDefinitions extends AbstractGameStepDefinition {
	Game game = getGame();
	@Given("^I have upper section scores totalling fifty eight$")
	public void i_have_upper_section_scores_totalling_fifty_eight() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SortedMap<Integer, Integer> riggedScoreSheet = new TreeMap<Integer, Integer>();
		riggedScoreSheet.put(6, 18);
		riggedScoreSheet.put(5, 15);
		riggedScoreSheet.put(4, 16);
		riggedScoreSheet.put(3, 9);
		game.getPlayers()[0].setScoreSheet(riggedScoreSheet);
		game.getPlayers()[0].setPoints(58);
	}
	
	@Given("I have upper section scores totalling twenty five")
	public void i_have_upper_section_scores_totalling_twenty_five() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SortedMap<Integer, Integer> riggedScoreSheet = new TreeMap<Integer, Integer>();
		riggedScoreSheet.put(6, 6);
		riggedScoreSheet.put(5, 10);
		riggedScoreSheet.put(4, 4);
		riggedScoreSheet.put(3, 3);
		riggedScoreSheet.put(1, 2);
		game.getPlayers()[0].setScoreSheet(riggedScoreSheet);
		game.getPlayers()[0].setPoints(25);
	}

	@Given("^a hand containing containg eight points worth of two's$")
	public void a_hand_containing_containg_eight_points_worth_of_two_s() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		game.getDice()[0].setValue(2);
		game.getDice()[1].setValue(2);
		game.getDice()[2].setValue(1);
		game.getDice()[3].setValue(2);
		game.getDice()[4].setValue(2);
	}

	@When("^I score the hands as upperScore two$")
	public void i_score_the_hands_as_upperScore_two() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		game.score(2);
	}

	@Then("^I should have an upper bonus score of (\\d+)$")
	public void i_should_have_an_upper_bonus_score_of(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		int score=0;
		if(game.getPlayers()[0].getScoreSheet().get(14)!=null) {
			score= game.getPlayers()[0].getScoreSheet().get(14);
		}
		assertEquals(arg1, score);
	}

	@Then("^my total points should be (\\d+)$")
	public void my_total_points_should_be(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(arg1, game.getPlayers()[0].getPoints());
	}
}
