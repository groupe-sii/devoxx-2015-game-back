package fr.sii.survival.core.ext.behavior.action;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

/**
 * Action behavior that decorates another action behavior in order to generate a
 * cooldown. This is used to prevent enemy to execute action too often. He has
 * now a time to wait before executing again the action.
 * 
 * @author Aurélien Baudet
 *
 */
public class CooldownActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(CooldownActionBehavior.class);

	private EnemyActionBehavior delegate;
	
	private long last;
	
	private long cooldown;

	public CooldownActionBehavior(EnemyActionBehavior actionBehavior, long cooldown) {
		super();
		this.delegate = actionBehavior;
		this.cooldown = cooldown;
	}
	
	public CooldownActionBehavior(EnemyActionBehavior actionBehavior, int cooldown, TimeUnit unit) {
		this(actionBehavior, TimeUnit.MILLISECONDS.convert(cooldown, unit));
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		long now = System.currentTimeMillis();
		if(now - last > cooldown) {
			LOG.debug("Cooldown finished => executing delegate action (Game: {}, Cell: {})", game, cell);
			last = now;
			delegate.execute(game, cell);
		} else {
			LOG.debug("Cooldown not finished, waiting (last: {}, next: {}, now: {}, cooldown: {}ms)", new Date(now), new Date(last+cooldown), new Date(last), cooldown);
		}
	}
}
