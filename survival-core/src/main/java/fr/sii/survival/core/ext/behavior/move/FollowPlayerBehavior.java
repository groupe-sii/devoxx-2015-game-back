package fr.sii.survival.core.ext.behavior.move;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger LOG = LoggerFactory.getLogger(FollowPlayerBehavior.class);

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
		LOG.debug("{} following {} at {} speed.", follower, followed, speed);
		Cell cellFrom = context.getBoard().getCell(getFollower());

		// Might move several times, depending on the speed
		for (int i = 0; i < speed;	 i++) {
			// The board refers the position of the different players
			Cell cellTo = context.getBoard().getCell(getFollowed());
			cellFrom = computeNextCellMoveOne(cellFrom, cellTo);
		}
		return cellFrom;
	}

	/**
	 * Main algorithm to follow a player.
	 * 
	 * @param from
	 *            where the follower is
	 * @param to
	 *            where the followed is
	 * @return the chosen cell where the follower should move to get closer
	 */
	protected static Cell computeNextCellMoveOne(Cell from, Cell to) {
		if (from == null) {
			return null;
		}
		if (to == null) {
			return from;
		}

		Cell result = from;

		int xDistance = to.getX() - from.getX();
		int yDistance = to.getY() - from.getY();
		
		if (Math.abs(xDistance) >= Math.abs(yDistance)) {
			if (Math.abs(xDistance) > 0) {
				// move +1 or -1 cell on x axis
				result = new Cell(from.getX() + (int) Math.signum(xDistance), from.getY());
			}
		} else if (Math.abs(yDistance) > 0) {
			// move +1 or -1 cell on y axis
			result = new Cell(from.getX(), from.getY() + (int) Math.signum(yDistance));
		}
		LOG.debug("Compute following move between {} and {} : {} ", from, to, result);

		return result;
	}

	public int getSpeed() {
		return speed;
	}

	public Player getFollower() {
		return follower;
	}

	public Player getFollowed() {
		return followed;
	}
}
