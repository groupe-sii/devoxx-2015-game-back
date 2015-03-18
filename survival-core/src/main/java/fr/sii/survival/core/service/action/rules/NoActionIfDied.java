package fr.sii.survival.core.service.action.rules;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;

/**
 * Prevent the player to execute actions if he is died
 * 
 * @author aurelien
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class NoActionIfDied implements AllowActionRule {

	@Override
	public boolean isAllowed(Game game, Player player, Action action) {
		return isAlive(player);
	}

	private boolean isAlive(Player player) {
		return player.getLife().getCurrent()>0;
	}

}
