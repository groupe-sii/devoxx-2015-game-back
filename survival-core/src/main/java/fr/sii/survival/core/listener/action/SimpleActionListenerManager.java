package fr.sii.survival.core.listener.action;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleActionListenerManager implements ActionListenerManager {

	private Map<String, ActionListener> listeners;
	
	private MessageService errorService;
	
	private ExtensionService extensionService;
	
	public SimpleActionListenerManager(MessageService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimpleActionListenerManager(Map<String, ActionListener> listeners, MessageService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addActionListener(ActionListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Action listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeActionListener(ActionListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerLifeUpdated(Game game, Player player, UpdateLife action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.lifeUpdated(game, player, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger life updated event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerPositionChanged(Game game, Player player, ChangePosition action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.positionChanged(game, player, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger position changed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerImageMoved(Game game, MoveImage action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.imageMoved(game, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger image moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}
	
	@Override
	public void triggerStateChanged(Game game, Player player, ChangeStates action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.stateChanged(game, player, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger state changed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}
	
	private String getKey(ActionListener listener) {
		return listener.getClass().toString();
	}
}
