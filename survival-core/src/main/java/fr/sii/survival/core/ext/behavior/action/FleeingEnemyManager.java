package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class FleeingEnemyManager extends SimpleActionManager {

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
	
	public FleeingEnemyManager(ActionService actionService) {
		super(actionService);
	}

	@Override
	public void execute(Cell cell) {
		try {
			actionService.execute(new ChangeStates(cell, new StateChange(Insult.getInsult(iterate).toString())));
			iterate = (iterate++)%insults;
		} catch (ActionException e) {
			//DO Nothing
		}
	}

}
