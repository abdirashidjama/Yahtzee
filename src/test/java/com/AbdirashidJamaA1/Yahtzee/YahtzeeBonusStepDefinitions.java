package com.AbdirashidJamaA1.Yahtzee;

import static org.junit.Assert.assertEquals;

import java.util.SortedMap;
import java.util.TreeMap;

import io.cucumber.java.en.*;

public class YahtzeeBonusStepDefinitions extends AbstractGameStepDefinition{
	Game game = getGame();
	@Given("I have already scored a Yahtzee and have fifty points")
	public void i_have_already_scored_a_Yahtzee_and_have_fifty_points() {
	    // Write code here that turns the phrase above into concrete actions
		SortedMap<Integer, Integer> riggedScoreSheet = new TreeMap<Integer, Integer>();
		riggedScoreSheet.put(6, 18);
		game.getPlayers()[0].setScoreSheet(riggedScoreSheet);
		game.getPlayers()[0].setPoints(50);
	}

	@Given("a hand containing a Yahtzee")
	public void a_hand_containing_a_Yahtzee() {
	    // Write code here that turns the phrase above into concrete actions
	    game.getDice()[0].setValue(4);
		game.getDice()[1].setValue(4);
		game.getDice()[2].setValue(4);
		game.getDice()[3].setValue(4);
		game.getDice()[4].setValue(4);
	}

	@When("I score the Yahtzee")
	public void i_score_the_Yahtzee() {
	    // Write code here that turns the phrase above into concrete actions
	    game.score(13);
	}

	@Then("I should have Yahtzee bonus and a total score {int} points")
	public void i_should_have_Yahtzee_bonus_and_a_total_score_points(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int points = int1;
		assertEquals(points, game.getPlayers()[0].getPoints());
	}

}
