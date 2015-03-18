package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class FleeingEnemyBehavior extends SimpleActionBehavior {

	private enum Insult {
		PABO ("Pas Beau !"),
		MECHANT ("Méchant !"),
		VILAIN ("Vilain !");
		
		private String name;
		   
		//Constructeur
		Insult(String name){
			this.name = name;
		}

		public static Insult getInsult(int index){
			return Insult.values()[index];
		}
		
		public String toString(){
			return name;
		}
	}
	
	private int iterate = 0;
	private int insults = Insult.values().length;
	
	public FleeingEnemyBehavior(ActionService actionService, Enemy enemy) {
		super(actionService, enemy);
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, enemy, new ChangeStates(cell, new StateChange(Insult.getInsult(iterate).toString())));
		iterate = (iterate++)%insults;
	}

}
