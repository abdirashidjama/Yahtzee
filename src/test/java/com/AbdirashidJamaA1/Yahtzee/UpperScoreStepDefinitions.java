package com.AbdirashidJamaA1.Yahtzee;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.*;


public class UpperScoreStepDefinitions extends AbstractGameStepDefinition {
	Game game = getGame();
	@Given("^I recieve these (\\d+),(\\d+),(\\d+),(\\d+),(\\d+)$")
	public void i_want_to_write_a_step_with(int arg1, int arg2, int arg3, int arg4, int arg5) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    game.getDice()[0].setValue(arg1);
	    game.getDice()[1].setValue(arg2);
	    game.getDice()[2].setValue(arg3);
	    game.getDice()[3].setValue(arg4);
	    game.getDice()[4].setValue(arg5);    
	}

	@When("^I choose category (\\d+) to score in$")
	public void i_check_for_the_in_step(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    game.score(arg1);
	}

	@Then("^I get (\\d+)$")
	public void i_verify_the_in_step(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(arg1,game.getPlayers()[0].getPoints());
	}
}
