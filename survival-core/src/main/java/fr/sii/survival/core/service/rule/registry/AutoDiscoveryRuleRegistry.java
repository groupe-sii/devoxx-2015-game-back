package fr.sii.survival.core.service.rule.registry;

import java.util.List;

import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rule.Rule;
import fr.sii.survival.core.util.AutoDiscoveryInstanceProvider;
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
	private RuleRegistry<R> delegate;
	
	private ExtensionService extensionService;
	
	private Class<R> clazz;
	
	private String[] packageNames;

	public AutoDiscoveryRuleRegistry(RuleRegistry<R> delegate, ExtensionService extensionService, Class<R> clazz) {
		this(delegate, extensionService, clazz, "fr.sii.survival.ext");
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
		for(R rule : AutoDiscoveryUtil.find(clazz, new AutoDiscoveryInstanceProvider<>("Rule", extensionService), packageNames)) {
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

	@Override
	public void reset() {
		delegate.reset();
	}

}
