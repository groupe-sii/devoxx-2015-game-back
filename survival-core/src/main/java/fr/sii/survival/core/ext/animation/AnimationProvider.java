package fr.sii.survival.core.ext.animation;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.exception.AnimationInitializationException;

/**
 * Interface for animation extensions
 * 
 * @author Aurélien Baudet
 *
 */
public interface AnimationProvider {
	/**
	 * Generate an animation
	 * 
	 * @return the animation
	 * @throws AnimationInitializationException
	 *             when the animation fails to initialize
	 */
	public Animation provide() throws AnimationInitializationException;
}
