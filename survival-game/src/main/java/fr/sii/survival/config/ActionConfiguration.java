package fr.sii.survival.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.listener.action.ActionListenerManager;
import fr.sii.survival.core.listener.action.SimpleActionListenerManager;
import fr.sii.survival.core.service.action.ActionManager;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.action.ChangePositionActionManager;
import fr.sii.survival.core.service.action.ChangeStateActionManager;
import fr.sii.survival.core.service.action.DelegateActionService;
import fr.sii.survival.core.service.action.MoveImageActionManager;
import fr.sii.survival.core.service.action.UpdateCurrentLifeActionManager;
import fr.sii.survival.core.service.action.UpdateMaxLifeActionManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;

@Configuration
public class ActionConfiguration {
	public static final String ACTION_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/action";
	public static final String ACTION_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/action";

	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PlayerService playerService;
	
	@Bean
	public ActionService actionService() {
		return new DelegateActionService(actionListenerManager(), actionManagers());
	}

	@Bean
	public ActionListenerManager actionListenerManager() {
		return new SimpleActionListenerManager(errorService, extensionService);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ActionManager<Action>> actionManagers() {
		ArrayList<ActionManager<Action>> managers = new ArrayList<>();
		managers.add((ActionManager) new UpdateCurrentLifeActionManager(boardService, playerService, actionListenerManager()));
		managers.add((ActionManager) new MoveImageActionManager(actionListenerManager()));
		managers.add((ActionManager) new ChangePositionActionManager(boardService, actionListenerManager()));
		managers.add((ActionManager) new UpdateMaxLifeActionManager(boardService, playerService, actionListenerManager()));
		managers.add((ActionManager) new ChangeStateActionManager(boardService, playerService, actionListenerManager()));
		return managers;
	}
}
