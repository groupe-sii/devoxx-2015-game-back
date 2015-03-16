package fr.sii.survival.core.service.game;

import java.util.function.Predicate;

import fr.sii.survival.core.domain.player.Player;

/**
 * Predicate that checks if the player implements/extends provided type
 * 
 * @author aurelien
 *
 */
public class PlayerTypePredicate implements Predicate<Player> {

	private Class<? extends Player> type;

	public PlayerTypePredicate(Class<? extends Player> type) {
		super();
		this.type = type;
	}

	@Override
	public boolean test(Player player) {
		return type.isAssignableFrom(player.getClass());
	}

}
