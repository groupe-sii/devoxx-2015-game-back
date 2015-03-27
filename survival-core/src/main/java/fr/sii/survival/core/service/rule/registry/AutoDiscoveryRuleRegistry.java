package fr.sii.survival.core.service.rule.registry;

import java.lang.reflect.Modifier;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.registry.AutoDiscoveryExtensionRegistry;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rule.Rule;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

/**
 * Registry that automatically discovers implementations of specific rules
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            The type of the rules
 */
 public class AutoDiscoveryRuleRegistry<R extends Rule> extends SimpleRuleRegistry<R> {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);

	public AutoDiscoveryRuleRegistry(ExtensionService extensionService, Class<R> clazz) {
		this(extensionService, clazz, "fr.sii.survival.ext.rules");
	}

	public AutoDiscoveryRuleRegistry(ExtensionService extensionService, Class<R> clazz, String... packageNames) {
		super(AutoDiscoveryUtil.find(clazz, new RuleProvider<R>(extensionService), packageNames));
	}

	private static final class RuleProvider<R> implements Function<Class<? extends R>, R> {

		private ExtensionService extensionService;

		public RuleProvider(ExtensionService extensionService) {
			super();
			this.extensionService = extensionService;
		}

		@Override
		public R apply(Class<? extends R> type) {
			if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
				try {
					LOG.info("found rule {} created by {}", type.getName(), extensionService.getDeveloper(type));
					return type.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("cannot instantiate rule {} created by {}. Cause: {}", type.getName(), extensionService.getDeveloper(type), e);
				}
			}
			return null;
		}

	}

}
