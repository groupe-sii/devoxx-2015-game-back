package fr.sii.survival.core.ext.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

public class AutoDiscoveryExtensionRegistry extends SimpleEnemyExtensionRegistry {
	private static Logger logger = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);

	public AutoDiscoveryExtensionRegistry(MessageService errorService, ExtensionService extensionService) {
		super(find("fr.sii.survival.ext", errorService, extensionService));
	}

	public AutoDiscoveryExtensionRegistry(String packageName, MessageService errorService, ExtensionService extensionService) {
		super(find(packageName, errorService, extensionService));
	}

	private static List<EnemyExtension> find(String packageName, MessageService errorService, ExtensionService extensionService) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends EnemyExtension>> subTypes = reflections.getSubTypesOf(EnemyExtension.class);
		List<EnemyExtension> instances = new ArrayList<EnemyExtension>();
		for(Class<? extends EnemyExtension> type : subTypes) {
			logger.info("found enemy extension {} created by {}", type.getName(), extensionService.getDeveloper(type));
			EnemyExtension instance = instantiate(type, errorService, extensionService);
			if(instance!=null) {
				logger.info("instantiate enemy extension {} created by {}", instance, extensionService.getDeveloper(type));
				instances.add(instance);
			}
		}
		return instances;
	}

	private static EnemyExtension instantiate(Class<? extends EnemyExtension> type, MessageService errorService, ExtensionService extensionService) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			errorService.addError(new ExtensionInitializationException("failed to initialize extension "+type.getName(), extensionService.getDeveloper(type), e));
			return null;
		}
	}
}
