package fr.sii.survival.ext.animation;

import java.io.IOException;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.domain.animation.SpriteAnimation;
import fr.sii.survival.core.domain.image.Sprite;
import fr.sii.survival.core.exception.AnimationInitializationException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.annotation.Developer;

/**
 * Animation that will start every time the CSS class "immobilized" is added on a player.
 * 
 * @author aurelien
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeAnimationProvider implements AnimationProvider {
	
	@Override
	public Animation provide() throws AnimationInitializationException {
		try {
			return new SpriteAnimation("immobilized", 5000, new Sprite("images/immobilized"), new float[] {
				// roots are getting out the earth
				0,
				2,
				4,
				6,
				8,
				10,
				12,
				14,
				16,
				// roots are out and don't move any more
				
				// roots are going back to earth
				94,
				96,
				98,
				100
			});
		} catch(IOException | MimetypeDetectionException e) {
			throw new AnimationInitializationException("Failed to initialize 'immobilized' animation", e);
		}
	}
	
}
