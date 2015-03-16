package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class HealActionManager extends SimpleActionManager {

	private int amount;

	public HealActionManager(ActionService actionService, int amount) {
		super(actionService);
		this.amount = amount;
	}

	@Override
	public void execute(Cell cell) throws ActionException {
		actionService.execute(new UpdateCurrentLife(amount, cell));
	}

}
