package fr.sii.survival.core.service.player;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StateChange.Change;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.PlayerListenerRegistry;
import fr.sii.survival.core.listener.player.PlayerListenerTrigger;

/**
 * Simple service that update player life and also triggers associated player
 * listeners:
 * <ul>
 * <li>Trigger when the player dies</li>
 * <li>Trigger when the player revives</li>
 * <li>Trigger when the player is healed</li>
 * <li>Trigger when the player is hit</li>
 * </ul>
 * 
 * This service ensures that current life is between 0 and maximum life.
 * 
 * This service prevents maximum life to be between minimum value and maximum
 * value. By default the maximum life could be set to 0 and maximum life could
 * be set to {@link Integer}.MAX_VALUE.
 * 
 * @author aurelien
 *
 */
public class SimplePlayerService implements PlayerService {
	private static Logger logger = LoggerFactory.getLogger(SimplePlayerService.class);

	/**
	 * The registry that stores the list of action listeners that will be
	 * triggered when an action is done
	 */
	private PlayerListenerRegistry listenerRegistry;

	/**
	 * The trigger helper useful to trigger player events
	 */
	private PlayerListenerTrigger playerListenerTrigger;

	/**
	 * The range of values accepted for maximum life
	 */
	private Range<Integer> maximumLifeRange;

	public SimplePlayerService(PlayerListenerManager listenerManager) {
		this(Ranges.closed(0, Integer.MAX_VALUE), listenerManager);
	}

	public SimplePlayerService(Range<Integer> maximumLifeRange, PlayerListenerManager listenerManager) {
		this(maximumLifeRange, listenerManager, listenerManager);
	}

	public SimplePlayerService(Range<Integer> maximumLifeRange, PlayerListenerRegistry listenerRegistry, PlayerListenerTrigger playerListenerTrigger) {
		super();
		this.maximumLifeRange = maximumLifeRange;
		this.listenerRegistry = listenerRegistry;
		this.playerListenerTrigger = playerListenerTrigger;
	}

	@Override
	public int updateCurrentLife(Player player, int increment) {
		Life life = player.getLife();
		int oldValue = life.getCurrent();
		int lifeValue = life.updateCurrent(increment);
		logger.info("update player life from {} to {}", oldValue, lifeValue);
		if (lifeValue <= 0) {
			// new life<=0 => player is now dead
			life.setCurrent(0);
			increment = -oldValue;
			playerListenerTrigger.triggerDead(player);
		} else if (lifeValue > life.getMax()) {
			// new life>max => prevent life to exceed max life
			life.setCurrent(life.getMax());
			increment = Math.min(life.getMax() - lifeValue, increment);
		}
		if (oldValue <= 0 && lifeValue > 0) {
			// player was dead and new life>=0 => player revived
			playerListenerTrigger.triggerRevived(player);
		} else if (increment > 0) {
			// player is healed
			playerListenerTrigger.triggerHealed(player, -increment);
		} else if (increment < 0) {
			// player is hit
			playerListenerTrigger.triggerHit(player, increment);
		}
		return increment;
	}

	@Override
	public int updateMaxLife(Player player, int increment) {
		Life life = player.getLife();
		int oldValue = life.getMax();
		int lifeValue = life.updateMax(increment);
		logger.info("update player maximum life from {} to {}", oldValue, lifeValue);
		if (lifeValue < maximumLifeRange.lowerEndpoint()) {
			life.setMax(maximumLifeRange.lowerEndpoint());
			increment = -oldValue;
		} else if(lifeValue > maximumLifeRange.upperEndpoint()) {
			life.setMax(maximumLifeRange.upperEndpoint());
			increment = Math.min(maximumLifeRange.upperEndpoint() - lifeValue, increment);
		}
		updateCurrentLife(player, 0);
		return increment;
	}

	@Override
	public List<StateChange> updateStates(Player player, List<StateChange> stateChanges) {
		logger.info("update states {} on {}", stateChanges, player);
		List<StateChange> appliedChanges = new ArrayList<StateChange>(stateChanges.size());
		for (StateChange change : stateChanges) {
			boolean applied = false;
			if (Change.ADD.equals(change.getChange())) {
				applied = player.getStates().addState(change.getState());
			} else {
				applied = player.getStates().removeState(change.getState());
			}
			if (applied) {
				appliedChanges.add(change);
			}
		}
		return appliedChanges;
	}

	@Override
	public void addPlayerListener(PlayerListener listener) {
		listenerRegistry.addPlayerListener(listener);
	}

	@Override
	public void removePlayerListener(PlayerListener listener) {
		listenerRegistry.removePlayerListener(listener);
	}

}
