package com.AbdirashidJamaA1.Yahtzee;

public class AbstractGameStepDefinition {
	protected Game game;
	protected Game getGame(){
		if(game==null) {
			game= new Game();
			game.start();
		}
		return game;
	}
	
}
