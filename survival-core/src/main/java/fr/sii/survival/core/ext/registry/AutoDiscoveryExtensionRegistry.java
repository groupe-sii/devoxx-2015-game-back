package fr.sii.survival.core.ext.registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

public class AutoDiscoveryExtensionRegistry implements ExtensionRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);

	private ExtensionRegistry delegate;

	private ExtensionService extensionService;

	private String[] packageNames;
	
	public AutoDiscoveryExtensionRegistry(ExtensionRegistry delegate, ExtensionService extensionService) {
		this(delegate, extensionService, "fr.sii.survival.ext");
	}

	public AutoDiscoveryExtensionRegistry(ExtensionRegistry delegate, ExtensionService extensionService, String... packageNames) {
		super();
		this.delegate = delegate;
		this.extensionService = extensionService;
		this.packageNames = packageNames;
		register();
	}
	
	public void register() {
		for(Class<EnemyExtension> ext : AutoDiscoveryUtil.find(EnemyExtension.class, new EnemyExtensionProvider(extensionService), packageNames)) {
			register(ext);
		}
	}
	
	@Override
	public void register(Class<EnemyExtension> extension) {
		delegate.register(extension);
	}

	@Override
	public List<Class<EnemyExtension>> getEnemyExtensions() {
		return delegate.getEnemyExtensions();
	}
	
	private static final class EnemyExtensionProvider implements Function<Class<? extends EnemyExtension>, Class<EnemyExtension>> {
		private ExtensionService extensionService;

		public EnemyExtensionProvider(ExtensionService extensionService) {
			super();
			this.extensionService = extensionService;
		}

		@Override
		public Class<EnemyExtension> apply(Class<? extends EnemyExtension> type) {
			if(!type.isInterface() && !Modifier.isAbstract(type.getModifiers()) && type.getConstructors().length>0) {
				if(type.isAnnotationPresent(Developer.class)) {
					checkDefaultConstructor(type);
				} else {
					LOG.error("Enemy: {} has no @Developer information => skipped", type.getName());
				}
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		private Class<EnemyExtension> checkDefaultConstructor(Class<? extends EnemyExtension> type) {
			for(Constructor<?> constructor : type.getConstructors()) {
				if(constructor.getParameterCount()==0) {
					LOG.info("Enemy: {} found and created by {}", type.getName(), extensionService.getDeveloper(type));
					return (Class<EnemyExtension>) type;
				}
			}
			LOG.error("Enemy: {} has no default constructor => skipped", type.getName());
			return null;
		}
		
	}

}
