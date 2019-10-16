package com.AbdirashidJamaA1.Yahtzee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.cucumber.java.en.*;

public class ScoreNoRerollDefinitions extends AbstractGameStepDefinition {
	Game game = getGame();

	@Given("I recieve dice")
	public void i_recieve_dice() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(0, game.getDice()[0].getValue());
	}

	@When("I roll dice")
	public void i_roll_dice() {
		// Write code here that turns the phrase above into concrete actions
		game.getPlayers()[0].rollDice();
	}

	@When("player choose {int} to score")
	public void player_choose_to_score(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		game.score(int1);
	}

	@Then("player get points for {int} in scoresheet")
	public void player_get_points_for_in_scoresheet(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertNotNull(game.getPlayers()[0].getScoreSheet().get(int1));
	}

}
