package fr.sii.survival.core.listener.action;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.message.MessageService;

// TODO: use multi threads
public class ParallelActionListenerManager implements ActionListenerManager {

	private Map<String, ActionListener> listeners;
	
	private MessageService messageService;
	
	public ParallelActionListenerManager(MessageService errorManager) {
		this(new HashMap<>(), errorManager);
	}
	
	public ParallelActionListenerManager(Map<String, ActionListener> listeners, MessageService messageService) {
		super();
		this.listeners = listeners;
		this.messageService = messageService;
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
		// TODO
	}

	@Override
	public void triggerPositionChanged(Player player, ChangePosition action) {
		// TODO
	}

	@Override
	public void triggerImageMoved(MoveImage action) {
		// TODO
	}
	
	@Override
	public void triggerStateChanged(Player player, ChangeStates action) {
		// TODO
	}
	
	private String getKey(ActionListener listener) {
		return listener.getClass().toString();
	}
}
