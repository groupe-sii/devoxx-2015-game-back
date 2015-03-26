package fr.sii.survival.core.ext.provider;

import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Helper provider that instantiates an enemy from its class
 * 
 * @author aurelien
 *
 */
public abstract class AbstractProvider implements ExtensionProvider {
	protected final ActionService actionService;

	protected final BoardService boardService;
	
	protected final PlayerService playerService;
	
	protected final ExtensionService extensionService;

	protected AbstractProvider(ActionService actionService, BoardService boardService, PlayerService playerService, ExtensionService extensionService) {
		super();
		this.actionService = actionService;
		this.boardService = boardService;
		this.playerService = playerService;
		this.extensionService = extensionService;
	}

	protected EnemyExtension instantiate(Class<EnemyExtension> type) throws ExtensionInitializationException {
		try {
			EnemyExtension instance = type.newInstance();
			instance.setActionService(actionService);
			instance.setPlayerService(playerService);
			instance.setBoardService(boardService);
			return instance;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ExtensionInitializationException("failed to initialize extension " + type.getName(), extensionService.getDeveloper(type), e);
		}
	}

}
