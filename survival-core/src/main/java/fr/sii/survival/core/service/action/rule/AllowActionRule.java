package fr.sii.survival.core.service.action.rule;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.rule.Rule;

public interface AllowActionRule extends Rule {
	/**
	 * Check if the action can be executed by the player on the specified game.
	 * 
	 * @param game
	 *            the game to execute action on
	 * @param player
	 *            the player that initiated the action
	 * @param action
	 *            the action to execute
	 * @return true if the action is allowed, false otherwise
	 */
	public boolean isAllowed(Game game, Player player, Action action);
}
