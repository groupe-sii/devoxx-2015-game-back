package fr.sii.survival.core.ext.registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

public class AutoDiscoveryExtensionRegistry extends SimpleEnemyExtensionRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);

	public AutoDiscoveryExtensionRegistry(ExtensionService extensionService) {
		this(extensionService, "fr.sii.survival.ext");
	}

	public AutoDiscoveryExtensionRegistry(ExtensionService extensionService, String... packageNames) {
		super(AutoDiscoveryUtil.find(EnemyExtension.class, new EnemyExtensionProvider(extensionService), packageNames));
	}
	
	private static final class EnemyExtensionProvider implements Function<Class<? extends EnemyExtension>, Class<EnemyExtension>> {
		private ExtensionService extensionService;

		public EnemyExtensionProvider(ExtensionService extensionService) {
			super();
			this.extensionService = extensionService;
		}

		@Override
		@SuppressWarnings("unchecked")
		public Class<EnemyExtension> apply(Class<? extends EnemyExtension> type) {
			if(!type.isInterface() && !Modifier.isAbstract(type.getModifiers()) && type.getConstructors().length>0) {
				for(Constructor<?> constructor : type.getConstructors()) {
					if(constructor.getParameterCount()==0) {
						LOG.info("found enemy extension {} created by {}", type.getName(), extensionService.getDeveloper(type));
						return (Class<EnemyExtension>) type;
					}
				}
			}
			return null;
		}
		
	}
}
