package fr.sii.survival.core.ext.behavior.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	private Class<? extends Player> type;

	/**
	 * Target any kind of player (wizard or enemy)
	 */
	public RandomPlayerTargetBehavior() {
		this(Player.class);
	}

	/**
	 * Target only some kind of enemy
	 * 
	 * @param type
	 *            the type of the player to target
	 */
	public RandomPlayerTargetBehavior(Class<? extends Player> type) {
		super();
		this.type = type;
	}

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		List<Player> players = context.getGame().getPlayers().stream()
								.filter(p -> type.isAssignableFrom(p.getClass()))
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
