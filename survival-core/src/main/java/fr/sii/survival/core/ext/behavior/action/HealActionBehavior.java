package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

/**
 * A behavior that heals all the players that are on the cell. The heal value is
 * provided by amount parameter. The amount must be positive.
 * 
 * @author Aurélien Baudet
 *
 */
public class HealActionBehavior extends SimpleActionBehavior {

	private int amount;

	/**
	 * Initializes the behavior with the heal amount
	 * 
	 * @param actionService
	 *            the action service used to execute the heal action
	 * @param enemy
	 *            your enemy
	 * @param amount
	 *            the heal amount (must be positive)
	 * @throws IllegalArgumentException
	 *             when the amount is negative
	 */
	public HealActionBehavior(ActionService actionService, Enemy enemy, int amount) {
		super(actionService, enemy);
		if (amount < 0) {
			throw new IllegalArgumentException("Negative heal is forbidden. Please use AttackActionBehavior instead");
		}
		this.amount = amount;
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, enemy, new UpdateCurrentLife(amount, cell));
	}

}
