package com.AbdirashidJamaA1.Yahtzee;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;


public class RerollTestStepDefinitions extends AbstractGameStepDefinition{
	Game game = getGame();
	@Given("player recieves dice")
	public void player_recieve_dice() {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(0, game.getDice()[0].value);
	    assertEquals(0, game.getDice()[1].value);
	    assertEquals(0, game.getDice()[2].value);
	    assertEquals(0, game.getDice()[3].value);
	    assertEquals(0, game.getDice()[4].value);
	}

	@When("player chooses to reroll all the dice")
	public void player_chooses_to_reroll_all_the_dice() {
	    // Write code here that turns the phrase above into concrete actions
	    game.getPlayers()[0].rollDice();
	}

	@When("player choose {int} to score in")
	public void player_choose_to_score_in(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    game.score(int1);
	}

	@Then("player get points for that {int}")
	public void player_get_points_for_that(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertNotNull(game.getPlayers()[0].getScoreSheet().get(int1));
	    
	}
	
	/// rerolling specific die
	
	@Given("player recieves dice and {int} a couple")
	public void player_recieves_dice_and_a_couple(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int index = int1-1;
		game.getPlayers()[0].rollDice();
		for(int i=0; i<5;i++) {
			if(i!=index) {
				game.getDice()[i].setValue(0);
			}
		}
	}

	@When("player rolls the other dice that are not among the {int}")
	public void player_rolls_the_other_dice_that_are_not_among_the(Integer int1) {
	    // Write code here that turns the phrase above into concrete action
		String holdIndex= String.valueOf(int1);
	    game.reroll(holdIndex);
	}
	
	@Given("player recieves dice and {double} a couple")
	public void player_recieves_dice_and_a_couple(Double double1) {
	    // Write code here that turns the phrase above into concrete actions
		int index = double1.intValue()-1;
		game.getPlayers()[0].rollDice();
		for(int i=0; i<5;i++) {
			if(i!=index) {
				game.getDice()[i].setValue(0);
			}
		}
	}

	@When("player rolls the other dice that are not among the {double}")
	public void player_rolls_the_other_dice_that_are_not_among_the(Double double1) {
	    // Write code here that turns the phrase above into concrete actions
		String holdIndex= String.valueOf(double1);
	    game.reroll(holdIndex);
	}
	
	@Given("player recieves dice and {double}{double} a couple")
	public void player_recieves_dice_and_a_couple(Double double1, Double double2) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue()-1;
		int int2 = double2.intValue()-1;
		
		game.getPlayers()[0].rollDice();
		for(int i=0; i<5;i++) {
			if(i!=int1 || i!=int2) {
				game.getDice()[i].setValue(0);
			}
		}
	}
	

	@When("player rolls the other dice that are not among the {double}{double}")
	public void player_rolls_the_other_dice_that_are_not_among_the(Double double1, Double double2) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue();
		int int2 = double2.intValue();
		String holdIndex = String.valueOf(int1) + " " + String.valueOf(int2);
	    game.reroll(holdIndex);
	}
	
	@Given("player recieves dice and {double}{double}{double}{double} a couple")
	public void player_recieves_dice_and_a_couple(Double double1, Double double2, Double double3, Double double4) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue()-1;
		int int2 = double2.intValue()-1;
		int int3 = double3.intValue()-1;
		int int4 = double4.intValue()-1;
		
		game.getPlayers()[0].rollDice();
		for(int i=0; i<5;i++) {
			if(i!=int1 || i!=int2 || i!=int3 || i!=int4) {
				game.getDice()[i].setValue(0);
			}
		}
	}

	@When("player rolls the other dice that are not among the {double}{double}{double}{double}")
	public void player_rolls_the_other_dice_that_are_not_among_the(Double double1, Double double2, Double double3, Double double4) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue();
		int int2 = double2.intValue();
		int int3 = double3.intValue();
		int int4 = double4.intValue();
		String holdIndex = String.valueOf(int1) + " " + String.valueOf(int2) + " " + String.valueOf(int3)+ " " + String.valueOf(int4);
	    game.reroll(holdIndex);
	}

	@Given("player recieves dice and {double}{double}{double} a couple")
	public void player_recieves_dice_and_a_couple(Double double1, Double double2, Double double3) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue()-1;
		int int2 = double2.intValue()-1;
		int int3 = double3.intValue()-1;
		
		game.getPlayers()[0].rollDice();
		for(int i=0; i<5;i++) {
			if(i!=int1 || i!=int2 || i!=int3) {
				game.getDice()[i].setValue(0);
			}
		}
	}

	@When("player rolls the other dice that are not among the {double}{double}{double}")
	public void player_rolls_the_other_dice_that_are_not_among_the(Double double1, Double double2, Double double3) {
	    // Write code here that turns the phrase above into concrete actions
		int int1 = double1.intValue();
		int int2 = double2.intValue();
		int int3 = double3.intValue();
		String holdIndex = String.valueOf(int1) + " " + String.valueOf(int2) + " " + String.valueOf(int3);
	    game.reroll(holdIndex);
	}
	
	

}
