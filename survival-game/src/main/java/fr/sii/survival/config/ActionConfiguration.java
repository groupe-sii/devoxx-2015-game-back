package fr.sii.survival.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.config.options.RuleOptions;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.listener.action.ActionListenerManager;
import fr.sii.survival.core.listener.action.SimpleActionListenerManager;
import fr.sii.survival.core.service.action.ActionManager;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.action.AddImageActionManager;
import fr.sii.survival.core.service.action.ChangePositionActionManager;
import fr.sii.survival.core.service.action.DelegateActionService;
import fr.sii.survival.core.service.action.MoveImageActionManager;
import fr.sii.survival.core.service.action.RemoveImageActionManager;
import fr.sii.survival.core.service.action.StartAnimationActionManager;
import fr.sii.survival.core.service.action.StopAnimationActionManager;
import fr.sii.survival.core.service.action.UpdateCurrentLifeActionManager;
import fr.sii.survival.core.service.action.UpdateMaxLifeActionManager;
import fr.sii.survival.core.service.action.UpdateStateActionManager;
import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.action.rule.DelegateRulesActionService;
import fr.sii.survival.core.service.action.rule.registry.AllowActionRuleRegistry;
import fr.sii.survival.core.service.action.rule.registry.AutoDiscoveryActionRuleRegistry;
import fr.sii.survival.core.service.action.rule.registry.PreFilteredActionRuleRegistry;
import fr.sii.survival.core.service.action.rule.registry.SimpleActionRuleRegistry;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.rule.registry.predicate.RegexRulePredicate;

@Configuration
public class ActionConfiguration {
	public static final String ACTION_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/action";

	@Autowired
	MessageService messageService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	RuleOptions ruleOptions;
	
	@Bean
	public ActionService actionService() {
		ActionService simpleActionService = new DelegateActionService(actionListenerManager(), actionManagers());
		return new DelegateRulesActionService(simpleActionService, allowActionRuleRegistry());
	}
	
	@Bean
	public AllowActionRuleRegistry allowActionRuleRegistry() {
		// transform list of exclusion patterns into a list of RegexPredicate. Then the list of predicates is combined using a Or operator
		Predicate<AllowActionRule> excludeFilter = ruleOptions.getExcludes().stream()
									.<Predicate<AllowActionRule>>map(exclude -> new RegexRulePredicate<AllowActionRule>(exclude))
									.reduce(Predicate::or)
									.orElse(p -> false);
		return new AutoDiscoveryActionRuleRegistry(new PreFilteredActionRuleRegistry(excludeFilter.negate(), new SimpleActionRuleRegistry()), extensionService, "fr.sii.survival.ext");
	}

	@Bean
	public ActionListenerManager actionListenerManager() {
		return new SimpleActionListenerManager(messageService, extensionService);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ActionManager<Action>> actionManagers() {
		List<ActionManager<Action>> managers = new ArrayList<>();
		managers.add((ActionManager) new UpdateCurrentLifeActionManager(boardService, playerService, actionListenerManager()));
		managers.add((ActionManager) new ChangePositionActionManager(boardService, actionListenerManager()));
		managers.add((ActionManager) new UpdateMaxLifeActionManager(boardService, playerService, actionListenerManager()));
		managers.add((ActionManager) new UpdateStateActionManager(boardService, playerService, actionListenerManager()));
		managers.add((ActionManager) new StartAnimationActionManager(actionListenerManager()));
		managers.add((ActionManager) new StopAnimationActionManager(actionListenerManager()));
		managers.add((ActionManager) new AddImageActionManager(actionListenerManager()));
		managers.add((ActionManager) new MoveImageActionManager(actionListenerManager()));
		managers.add((ActionManager) new RemoveImageActionManager(actionListenerManager()));
		return managers;
	}
}
