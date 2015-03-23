package fr.sii.survival.ext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.image.Animation;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.MultiActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RepeatedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryChangeState;
import fr.sii.survival.core.ext.behavior.action.animation.AnimationBuilder;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.ext.constants.States;

/**
 * Enemy that immobilize a player and hit him progressively
 * 
 * @author aurelien
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Immobilizator extends DelegateEnemyExtension {

	public Immobilizator() {
		super("Immobilizator", new ClientImage("npc5_fr1"), 5000);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
		try {
			int rate = 100;
			int spellDuration = 5000;
			return new CooldownActionBehavior(new MultiActionBehavior(
						// Action that plays a sprite on the targeted cell at start and play another sprite at end
						getAnimation(rate, spellDuration),
						// Action that adds "immobilized" state on the players available on targeted cell and remove it after 5 seconds
						new MultiActionBehavior(
								new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, 10), spellDuration/5, 5),
								new TemporaryChangeState(actionService, enemy, States.IMMOBILIZED.toString(), spellDuration))),
					10, TimeUnit.SECONDS);
		} catch(IOException | MimetypeDetectionException e) {
			throw new GameException("Failed to generate sprite 'images/immobilize'", e);
		}
	}

	private EnemyActionBehavior getAnimation(int rate, int spellDuration) throws IOException, MimetypeDetectionException {
		Animation immobilize = new Animation("images/immobilize/add", rate);
		Animation end = new Animation("images/immobilize/remove", rate);
		return new AnimationBuilder(actionService, enemy)
			// roots are getting out the earth and stay
			.addStep(0, immobilize)
			// roots are going back to earth
			.addStep(spellDuration-end.getImages().size()*rate, end)
			// the start sprite is removed after the second part of animation has started
			.addStep(spellDuration-(end.getImages().size()-1)*rate, immobilize, false)
			// the end sprite is removed at the end of the spell
			.addStep(spellDuration, end, false)
			.build();
	}
	
	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(Wizard.class);
	}

}
