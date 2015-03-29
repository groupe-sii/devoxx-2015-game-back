package fr.sii.survival.core.service.player;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StateChange.Change;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.PlayerListenerRegistry;
import fr.sii.survival.core.listener.player.PlayerListenerTrigger;
import fr.sii.survival.core.util.MultiGameHelper;

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
 * @author Aurélien Baudet
 *
 */
public class SimplePlayerService implements PlayerService {
	private static final Logger LOG = LoggerFactory.getLogger(SimplePlayerService.class);

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

	/**
	 * The default points of life for a newly created player
	 */
	private int defaultLife;

	/**
	 * Utility used to find game that is associated the the player
	 */
	private MultiGameHelper gameHelper;

	public SimplePlayerService(int defaultLife, PlayerListenerManager listenerManager, MultiGameHelper gameHelper) {
		this(defaultLife, Range.closed(0, Integer.MAX_VALUE), listenerManager, gameHelper);
	}

	public SimplePlayerService(int defaultLife, Range<Integer> maximumLifeRange, PlayerListenerManager listenerManager, MultiGameHelper gameHelper) {
		this(defaultLife, maximumLifeRange, listenerManager, listenerManager, gameHelper);
	}

	public SimplePlayerService(int defaultLife, Range<Integer> maximumLifeRange, PlayerListenerRegistry listenerRegistry, PlayerListenerTrigger playerListenerTrigger, MultiGameHelper gameHelper) {
		super();
		this.defaultLife = defaultLife;
		this.maximumLifeRange = maximumLifeRange;
		this.listenerRegistry = listenerRegistry;
		this.playerListenerTrigger = playerListenerTrigger;
		this.gameHelper = gameHelper;
	}
	
	@Override
	public Player create(PlayerInfo info) {
		return new SimpleWizard(info, defaultLife);
	}

	@Override
	public int updateCurrentLife(Player player, int increment) throws GameException {
		int inc = increment;
		Life life = player.getLife();
		int oldValue = life.getCurrent();
		int lifeValue = life.updateCurrent(inc);
		LOG.debug("update player life from {} to {}", oldValue, lifeValue);
		Game game = gameHelper.getGame(player);
		if (lifeValue <= 0) {
			// new life<=0 => player is now dead
			life.setCurrent(0);
			inc = -oldValue;
		} else if (lifeValue > life.getMax()) {
			// new life>max => prevent life to exceed max life
			life.setCurrent(life.getMax());
			inc = life.getMax() - oldValue;
		}
		// trigger events
		if(lifeValue <= 0 && oldValue>0) {
			// player was alive and new life<=0 => player dead
			playerListenerTrigger.triggerHit(game, player, -inc);
			playerListenerTrigger.triggerDead(game, player);
		} else if (oldValue <= 0 && lifeValue > 0) {
			// player was dead and new life>=0 => player revived
			playerListenerTrigger.triggerHealed(game, player, inc);
			playerListenerTrigger.triggerRevived(game, player);
		} else if (inc > 0 && oldValue>0) {
			// player is healed
			playerListenerTrigger.triggerHealed(game, player, inc);
		} else if (inc < 0 && oldValue>0) {
			// player is hit
			playerListenerTrigger.triggerHit(game, player, -inc);
		}
		return inc;
	}

	@Override
	public int updateMaxLife(Player player, int increment) throws GameException {
		int inc = increment;
		Life life = player.getLife();
		int oldValue = life.getMax();
		int lifeValue = life.updateMax(inc);
		LOG.debug("update player maximum life from {} to {}", oldValue, lifeValue);
		if (lifeValue < maximumLifeRange.lowerEndpoint()) {
			life.setMax(maximumLifeRange.lowerEndpoint());
			inc = -oldValue+maximumLifeRange.lowerEndpoint();
		} else if(lifeValue > maximumLifeRange.upperEndpoint()) {
			life.setMax(maximumLifeRange.upperEndpoint());
			inc = maximumLifeRange.upperEndpoint() - oldValue;
		}
		updateCurrentLife(player, 0);
		if(inc!=0) {
			playerListenerTrigger.triggerMaxLifeChanged(gameHelper.getGame(player), player, inc);
		}
		return inc;
	}

	@Override
	public List<StateChange> updateStates(Player player, List<StateChange> stateChanges) throws GameException {
		LOG.debug("update states {} on {}", stateChanges, player);
		List<StateChange> appliedChanges = new ArrayList<StateChange>(stateChanges.size());
		for (StateChange change : stateChanges) {
			boolean applied = false;
			if (Change.ADD.equals(change.getChange())) {
				applied = player.getPlayerStates().addState(change.getState());
			} else {
				applied = player.getPlayerStates().removeState(change.getState());
			}
			if (applied) {
				appliedChanges.add(change);
			}
		}
		if(!appliedChanges.isEmpty()) {
			playerListenerTrigger.triggerStates(gameHelper.getGame(player), player, appliedChanges);
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
