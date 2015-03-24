package fr.sii.survival.core.ext.animation;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.exception.AnimationInitializationException;

public interface AnimationProvider {
	public Animation provide() throws AnimationInitializationException;
}
