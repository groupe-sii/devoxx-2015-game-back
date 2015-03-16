package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class AttackActionManager extends SimpleActionManager {

	private int amount;

	public AttackActionManager(ActionService actionService, int amount) {
		super(actionService);
		this.amount = amount;
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, new UpdateCurrentLife(-amount, cell));
	}

}
