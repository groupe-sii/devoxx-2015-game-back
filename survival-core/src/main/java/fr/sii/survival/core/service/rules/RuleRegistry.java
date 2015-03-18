package fr.sii.survival.core.service.rules;

import java.util.List;

/**
 * Registry that stores rule implementations to apply on the game management
 * 
 * @author aurelien
 *
 * @param <R>
 *            The type of the rule managed by this registry
 */
public interface RuleRegistry<R> {
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
