package fr.sii.survival.core.service.action.rule;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.action.rule.registry.AllowActionRuleRegistry;

/**
 * Action service decorator that allows to register rules in order to prevent
 * some actions under some conditions.
 * 
 * @author Aurélien Baudet
 *
 */
public class DelegateRulesActionService implements ActionService {
	private ActionService delegate;

	private AllowActionRuleRegistry registry;

	public DelegateRulesActionService(ActionService delegate, AllowActionRuleRegistry registry) {
		super();
		this.delegate = delegate;
		this.registry = registry;
	}

	@Override
	public void addActionListener(ActionListener listener) {
		delegate.addActionListener(listener);
	}

	@Override
	public void removeActionListener(ActionListener listener) {
		delegate.removeActionListener(listener);
	}

	public boolean isAllowed(Game game, Player player, Action action) {
		for(AllowActionRule rule : registry.getRules()) {
			if(!rule.isAllowed(game, player, action)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void execute(Game game, Player player, Action action) throws ActionException {
		if(isAllowed(game, player, action)) {
			delegate.execute(game, player, action);
		}
	}

}
