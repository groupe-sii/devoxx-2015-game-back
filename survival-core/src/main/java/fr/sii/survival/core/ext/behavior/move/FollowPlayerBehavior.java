package fr.sii.survival.core.ext.behavior.move;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.GameContext;

/**
 * Move manager trying to get closer from a followed player. The follower will
 * choose to move on x axis or y axis, several times.
 * 
 * @author Cyril Dejonghe
 *
 */
public class FollowPlayerBehavior implements EnemyMoveBehavior {
	/** Number of cells the follower will move each time */
	private int speed;
	private Player follower;
	private Player followed;


	public FollowPlayerBehavior(Player follower, Player followed, int speed) {
		super();
		this.follower = follower;
		this.followed = followed;
		this.speed = speed;
	}

	@Override
	public Cell getNextPosition(GameContext context) {
		Cell cellFrom = context.getBoard().getCell(follower);

		// Might move several times, depending on the speed
		for (int i = 0; i < speed;	 i++) {
			// The board refers the position of the different players
			Cell cellTo = context.getBoard().getCell(followed);
			
			cellFrom = computeNextCellMoveOne(cellFrom, cellTo);

		}
		return cellFrom;
	}

	/**
	 * Main algorithm to follow a player.
	 * 
	 * @param cellFrom
	 *            where the follower is
	 * @param cellTo
	 *            where the followed is
	 * @return the chosen cell where the follower should move to get closer
	 */
	protected static Cell computeNextCellMoveOne(Cell cellFrom, Cell cellTo) {
		int xDistance = cellTo.getX() - cellFrom.getX();
		int yDistance = cellTo.getY() - cellFrom.getY();
		
		if (Math.abs(xDistance) >= Math.abs(yDistance)) {
			if (Math.abs(xDistance) > 0) {
				// move +1 or -1 cell on x axis
				cellFrom = new Cell(cellFrom.getX() + (int) Math.signum(xDistance), cellFrom.getY());
			}
		} else if (Math.abs(yDistance) > 0) {
			// move +1 or -1 cell on y axis
			cellFrom = new Cell(cellFrom.getX(), cellFrom.getY() + (int) Math.signum(yDistance));
		}
		return cellFrom;
	}

	public void setFollowed(Player followed) {
		this.followed = followed;
	}

}
