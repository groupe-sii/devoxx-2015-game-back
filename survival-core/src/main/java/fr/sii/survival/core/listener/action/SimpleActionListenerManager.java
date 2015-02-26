package fr.sii.survival.core.listener.action;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionListenerException;
import fr.sii.survival.core.service.error.ErrorService;
import fr.sii.survival.core.service.extension.ExtensionService;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleActionListenerManager implements ActionListenerManager {

	private Map<String, ActionListener> listeners;
	
	private ErrorService errorService;
	
	private ExtensionService extensionService;
	
	public SimpleActionListenerManager(ErrorService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimpleActionListenerManager(Map<String, ActionListener> listeners, ErrorService errorService, ExtensionService extensionService) {
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
	public void triggerLifeUpdated(Player player, UpdateLife action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.lifeUpdated(player, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger life updated event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerPositionChanged(Player player, ChangePosition action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.positionChanged(player, action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger position changed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerImageMoved(MoveImage action) {
		for(ActionListener listener : listeners.values()) {
			try {
				listener.imageMoved(action);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger image moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}
	
	private String getKey(ActionListener listener) {
		return listener.getClass().toString();
	}
}
