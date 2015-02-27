package fr.sii.survival.core.ext.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.error.ErrorService;
import fr.sii.survival.core.service.extension.ExtensionService;

public class AutoDiscoveryExtensionRegistry extends SimpleEnemyExtensionRegistry {

	public AutoDiscoveryExtensionRegistry(ErrorService errorService, ExtensionService extensionService) {
		super(find("fr.sii.survival.ext", errorService, extensionService));
	}

	public AutoDiscoveryExtensionRegistry(String packageName, ErrorService errorService, ExtensionService extensionService) {
		super(find(packageName, errorService, extensionService));
	}

	private static List<EnemyExtension> find(String packageName, ErrorService errorService, ExtensionService extensionService) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends EnemyExtension>> subTypes = reflections.getSubTypesOf(EnemyExtension.class);
		List<EnemyExtension> instances = new ArrayList<EnemyExtension>();
		for(Class<? extends EnemyExtension> type : subTypes) {
			EnemyExtension instance = instantiate(type, errorService, extensionService);
			if(instance!=null) {
				instances.add(instance);
			}
		}
		return instances;
	}

	private static EnemyExtension instantiate(Class<? extends EnemyExtension> type, ErrorService errorService, ExtensionService extensionService) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			errorService.addError(new ExtensionInitializationException("failed to initialize extension "+type.getName(), extensionService.getDeveloper(type), e));
			return null;
		}
	}
}
