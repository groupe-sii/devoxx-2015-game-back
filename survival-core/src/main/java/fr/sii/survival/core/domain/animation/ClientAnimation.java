package fr.sii.survival.core.domain.animation;

/**
 * Animation that is defined on the client side.
 * 
 * @author Aurélien Baudet
 *
 */
public class ClientAnimation extends AnimationBase {

	public ClientAnimation(String name, long duration, AnimationOptions options) {
		super(name, duration, options);
	}

	public ClientAnimation(String name, long duration) {
		super(name, duration);
	}

}
