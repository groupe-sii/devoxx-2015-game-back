package fr.sii.survival.core.listener.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionListenerException;
import fr.sii.survival.core.exception.PlayerListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

public class SimplePlayerListenerManager implements PlayerListenerManager {
	private Map<String, PlayerListener> listeners;
	
	private MessageService errorService;
	
	private ExtensionService extensionService;

	public SimplePlayerListenerManager(MessageService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimplePlayerListenerManager(Map<String, PlayerListener> listeners, MessageService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addPlayerListener(PlayerListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Player listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removePlayerListener(PlayerListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerDead(Game game, Player player) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.dead(game, player);
			} catch(Exception e) {
				errorService.addError(new PlayerListenerException("failed to trigger dead event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerRevived(Game game, Player player) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.revived(game, player);
			} catch(Exception e) {
				errorService.addError(new PlayerListenerException("failed to trigger revived event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerHit(Game game, Player player, int damage) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.hit(game, player, damage);
			} catch(Exception e) {
				errorService.addError(new ActionListenerException("failed to trigger hit event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerHealed(Game game, Player player, int amount) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.healed(game, player, amount);
			} catch(Exception e) {
				errorService.addError(new PlayerListenerException("failed to trigger healed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerStates(Game game, Player player, List<StateChange> changes) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.statesChanged(game, player, changes);
			} catch(Exception e) {
				errorService.addError(new PlayerListenerException("failed to trigger healed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerMaxLifeChanged(Game game, Player player, int amount) {
		for(PlayerListener listener : getListeners()) {
			try {
				listener.maxLifeChanged(game, player, amount);
			} catch(Exception e) {
				errorService.addError(new PlayerListenerException("failed to trigger max life changed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	private CopyOnWriteArrayList<PlayerListener> getListeners() {
		return new CopyOnWriteArrayList<>(listeners.values());
	}
	
	private String getKey(PlayerListener listener) {
		return listener.getClass().toString();
	}
}
