package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

/**
 * A behavior that attacks all the players that are on the cell. The attack
 * value is provided by amount parameter. The amount must be positive.
 * 
 * @author Aurélien Baudet
 *
 */
public class AttackActionBehavior extends SimpleActionBehavior {

	private int amount;

	/**
	 * Initializes the behavior with the attack amount
	 * 
	 * @param actionService
	 *            the action service used to execute the attack action
	 * @param enemy
	 *            your enemy
	 * @param amount
	 *            the attack amount (must be positive)
	 * @throws IllegalArgumentException
	 *             when the amount is negative
	 */
	public AttackActionBehavior(ActionService actionService, Enemy enemy, int amount) {
		super(actionService, enemy);
		if (amount < 0) {
			throw new IllegalArgumentException("Negative attack is forbidden. Please use HealActionBehavior instead");
		}
		this.amount = amount;
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, enemy, new UpdateCurrentLife(-amount, cell));
	}

}
