package fr.sii.survival.core.service.action;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.listener.action.ActionListenerRegistry;

public interface ActionService extends ActionListenerRegistry {
	public void execute(Action action);
}
