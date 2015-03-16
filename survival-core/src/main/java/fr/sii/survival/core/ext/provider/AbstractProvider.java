package fr.sii.survival.core.ext.provider;

import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.extension.ExtensionService;

/**
 * Helper provider that instantiates an enemy from its class
 * 
 * @author aurelien
 *
 */
public abstract class AbstractProvider implements ExtensionProvider {
	protected ActionService actionService;
	
	protected ExtensionService extensionService;

	protected AbstractProvider(ActionService actionService, ExtensionService extensionService) {
		super();
		this.actionService = actionService;
		this.extensionService = extensionService;
	}

	protected EnemyExtension instantiate(Class<? extends EnemyExtension> type) throws ExtensionInitializationException {
		try {
			EnemyExtension instance = type.newInstance();
			instance.setActionService(actionService);
			return instance;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ExtensionInitializationException("failed to initialize extension " + type.getName(), extensionService.getDeveloper(type), e);
		}
	}

}
