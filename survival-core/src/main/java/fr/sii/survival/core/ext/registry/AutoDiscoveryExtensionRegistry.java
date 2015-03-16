package fr.sii.survival.core.ext.registry;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.extension.ExtensionService;

public class AutoDiscoveryExtensionRegistry extends SimpleEnemyExtensionRegistry {
	private static final Logger logger = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);

	public AutoDiscoveryExtensionRegistry(ExtensionService extensionService) {
		super(find("fr.sii.survival.ext", extensionService));
	}

	public AutoDiscoveryExtensionRegistry(String packageName, ExtensionService extensionService) {
		super(find(packageName, extensionService));
	}

	private static List<Class<? extends EnemyExtension>> find(String packageName, ExtensionService extensionService) {
		Reflections reflections = new Reflections(new ConfigurationBuilder().
			    addUrls(ClasspathHelper.forPackage(packageName)).
			    addUrls(ClasspathHelper.forPackage(EnemyExtension.class.getPackage().getName())));
		Set<Class<? extends EnemyExtension>> subTypes = reflections.getSubTypesOf(EnemyExtension.class);
		List<Class<? extends EnemyExtension>> impls = new ArrayList<>(subTypes.size());
		for(Class<? extends EnemyExtension> type : subTypes) {
			if(!type.isInterface() && type.getConstructors().length>0) {
				for(Constructor<?> constructor : type.getConstructors()) {
					if(constructor.getParameterCount()==0) {
						logger.info("found enemy extension {} created by {}", type.getName(), extensionService.getDeveloper(type));
						impls.add(type);
						break;
					}
				}
			}
		}
		return impls;
	}
}
