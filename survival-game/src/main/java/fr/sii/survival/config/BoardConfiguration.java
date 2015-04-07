package fr.sii.survival.config;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import fr.sii.survival.config.options.BoardOptions;
import fr.sii.survival.config.options.RuleOptions;
import fr.sii.survival.core.listener.board.BoardListenerManager;
import fr.sii.survival.core.listener.board.SimpleBoardListenerManager;
import fr.sii.survival.core.reload.ReloadWatcher;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.RandomCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;
import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.board.rule.DelegateRulesBoardService;
import fr.sii.survival.core.service.board.rule.registry.AllowMoveRuleRegistry;
import fr.sii.survival.core.service.board.rule.registry.AutoDiscoveryBoardRuleRegistry;
import fr.sii.survival.core.service.board.rule.registry.HotReloadBoardRuleRegistry;
import fr.sii.survival.core.service.board.rule.registry.PreFilteredMoveRuleRegistry;
import fr.sii.survival.core.service.board.rule.registry.SimpleMoveRuleRegistry;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.rule.registry.predicate.RegexRulePredicate;
import fr.sii.survival.core.util.MultiGameHelper;

@Configuration
public class BoardConfiguration {
	public static final String BOARD_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/board";

	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;

	@Autowired
	MultiGameHelper multiGameHelper;
	
	@Autowired
	BoardOptions boardOptions;
	
	@Autowired
	RuleOptions ruleOptions;
	
	@Autowired
	ReloadWatcher reloadWatcher;
	
	@Bean
	public BoardService boardService() {
		BoardService simpleService = new SimpleBoardService(boardOptions.getWidth(), boardOptions.getHeight(), cellProvider(), boardListenerManager(), multiGameHelper);
		return new DelegateRulesBoardService(simpleService, allowMoveRuleRegistry());
	}

	@Bean
	@DependsOn("classLoaderReloader")
	public AllowMoveRuleRegistry allowMoveRuleRegistry() {
		// transform list of exclusion patterns into a list of RegexPredicate. Then the list of predicates is combined using a Or operator
		Predicate<AllowMoveRule> excludeFilter = ruleOptions.getExcludes().stream()
									.<Predicate<AllowMoveRule>>map(exclude -> new RegexRulePredicate<AllowMoveRule>(exclude))
									.reduce(Predicate::or)
									.orElse(p -> false);
		return new HotReloadBoardRuleRegistry(reloadWatcher, new AutoDiscoveryBoardRuleRegistry(new PreFilteredMoveRuleRegistry(excludeFilter.negate(), new SimpleMoveRuleRegistry()), extensionService, "fr.sii.survival.ext"));
	}

	@Bean
	public RandomCellProvider cellProvider() {
		return new RandomCellProvider();
	}

	@Bean
	public BoardListenerManager boardListenerManager() {
		return new SimpleBoardListenerManager(errorService, extensionService);
	}
}
