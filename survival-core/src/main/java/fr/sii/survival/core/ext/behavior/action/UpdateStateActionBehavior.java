package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.UpdateStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StateChange.Change;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class UpdateStateActionBehavior extends SimpleActionBehavior {

	private StateChange change;

	public UpdateStateActionBehavior(ActionService actionService, Enemy enemy, String state, boolean add) {
		this(actionService, enemy, new StateChange(state, add ? Change.ADD : Change.REMOVE));
	}

	public UpdateStateActionBehavior(ActionService actionService, Enemy enemy, StateChange change) {
		super(actionService, enemy);
		this.change = change;
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, enemy, new UpdateStates(cell, change));
	}

}
