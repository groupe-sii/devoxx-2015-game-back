package fr.sii.survival.core.ext.behavior.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.GameContext;

/**
 * Manager that targets a random player every call.
 * 
 * @author Aurélien Baudet
 *
 */
public class RandomPlayerTargetBehavior implements TargetBehavior {

	private Predicate<Player> predicate;

	/**
	 * Target any kind of players (human player or enemy) dead or alive
	 */
	public RandomPlayerTargetBehavior() {
		this(Player.class);
	}

	/**
	 * Target any only players of the provided type
	 * 
	 * @param type
	 *            the type of the player to target
	 */
	public RandomPlayerTargetBehavior(Class<? extends Player> type) {
		this(p -> type.isAssignableFrom(p.getClass()));
	}

	/**
	 * Target only some players that fulfill the provided condition
	 * 
	 * @param predicate
	 *            the condition to apply on each player to check if it is
	 *            eligible for targeting
	 */
	public RandomPlayerTargetBehavior(Predicate<Player> predicate) {
		super();
		this.predicate = predicate;
	}

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		List<Player> players = context.getGame().getPlayers().stream()
								.filter(predicate)
								.collect(Collectors.toList());
		if (!players.isEmpty()) {
			Player player = players.get((int) Math.floor(Math.random() * players.size()));
			Cell cell = context.getBoard().getCell(player);
			return cell == null ? new ArrayList<>() : Arrays.asList(cell);
		} else {
			return new ArrayList<>();
		}
	}

}
