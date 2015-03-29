package fr.sii.survival.core.service.rule.registry;

import java.lang.reflect.Modifier;
import java.util.List;
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
 public class AutoDiscoveryRuleRegistry<R extends Rule> implements RuleRegistry<R> {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryExtensionRegistry.class);
	
	private RuleRegistry<R> delegate;
	
	private ExtensionService extensionService;
	
	private Class<R> clazz;
	
	private String[] packageNames;

	public AutoDiscoveryRuleRegistry(RuleRegistry<R> delegate, ExtensionService extensionService, Class<R> clazz) {
		this(delegate, extensionService, clazz, "fr.sii.survival.ext.rules");
	}

	public AutoDiscoveryRuleRegistry(RuleRegistry<R> delegate, ExtensionService extensionService, Class<R> clazz, String... packageNames) {
		super();
		this.delegate = delegate;
		this.extensionService = extensionService;
		this.clazz = clazz;
		this.packageNames = packageNames;
		register();
	}
	
	public void register() {
		for(R rule : AutoDiscoveryUtil.find(clazz, new RuleProvider<R>(extensionService), packageNames)) {
			register(rule);
		}
	}

	@Override
	public void register(R rule) {
		delegate.register(rule);
	}

	@Override
	public List<R> getRules() {
		return delegate.getRules();
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
