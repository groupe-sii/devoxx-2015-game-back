package fr.sii.survival.core.service.rule.registry;

import java.util.List;

import fr.sii.survival.core.service.rule.Rule;

/**
 * Registry that stores rule implementations to apply on the game management
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            The type of the rule managed by this registry
 */
public interface RuleRegistry<R extends Rule> {
	/**
	 * Register a new rule
	 * 
	 * @param rule
	 *            the rule to register
	 */
	public void register(R rule);

	/**
	 * Get the whole list of rules to apply
	 * 
	 * @return the list of rules
	 */
	public List<R> getRules();
}
