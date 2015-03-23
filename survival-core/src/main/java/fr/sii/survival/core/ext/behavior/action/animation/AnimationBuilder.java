package fr.sii.survival.core.ext.behavior.action.animation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.ext.behavior.action.AddImageBehavior;
import fr.sii.survival.core.ext.behavior.action.DelayedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.MultiActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RemoveImageBehavior;
import fr.sii.survival.core.service.action.ActionService;

public class AnimationBuilder {

	private List<EnemyActionBehavior> frames;

	private long duration;

	private ActionService actionService;

	private Enemy enemy;

	public AnimationBuilder() {
		this(0);
	}

	/**
	 * Initialize the builder with the whole duration of the animation. The
	 * whole duration is mandatory when adding percent steps.
	 * 
	 * @param duration
	 *            the whole duration in milliseconds
	 */
	public AnimationBuilder(long duration) {
		this(new ArrayList<>(), duration);
	}

	/**
	 * Initialize the builder with the whole duration of the animation. The
	 * whole duration is mandatory when adding percent steps.
	 * 
	 * @param duration
	 *            the whole duration
	 * @param unit
	 *            the unit for the duration
	 */
	public AnimationBuilder(int duration, TimeUnit unit) {
		this(TimeUnit.MILLISECONDS.convert(duration, unit));
	}

	protected AnimationBuilder(List<EnemyActionBehavior> frames, long duration) {
		this(null, null, frames, duration);
	}

	public AnimationBuilder(ActionService actionService, Enemy enemy) {
		this(actionService, enemy, 0);
	}

	/**
	 * Initialize the builder with the whole duration of the animation. The
	 * whole duration is mandatory when adding percent steps.
	 * 
	 * @param duration
	 *            the whole duration in milliseconds
	 */
	public AnimationBuilder(ActionService actionService, Enemy enemy, long duration) {
		this(actionService, enemy, new ArrayList<>(), duration);
	}

	/**
	 * Initialize the builder with the whole duration of the animation. The
	 * whole duration is mandatory when adding percent steps.
	 * 
	 * @param duration
	 *            the whole duration
	 * @param unit
	 *            the unit for the duration
	 */
	public AnimationBuilder(ActionService actionService, Enemy enemy, int duration, TimeUnit unit) {
		this(actionService, enemy, TimeUnit.MILLISECONDS.convert(duration, unit));
	}

	protected AnimationBuilder(ActionService actionService, Enemy enemy, List<EnemyActionBehavior> frames, long duration) {
		super();
		this.actionService = actionService;
		this.enemy = enemy;
		this.frames = frames;
		this.duration = duration;
	}

	/**
	 * Set the whole duration of the animation
	 * 
	 * @param duration
	 *            the duration
	 * @param unit
	 *            the unit of the duration
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder setDuration(int duration, TimeUnit unit) {
		return setDuration(TimeUnit.MILLISECONDS.convert(duration, unit));
	}

	/**
	 * Set the whole duration of the animation
	 * 
	 * @param duration
	 *            the duration in milliseconds
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	/**
	 * Add a step based on the whole duration with the provided percent. Whole
	 * duration MUST be set before calling this method.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param percent
	 *            the percent used to compute the real delay based on whole
	 *            duration
	 * @param image
	 *            the image to register
	 * @param add
	 *            true to add the image, false otherwise
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addPercentStep(int percent, Image image, boolean add) {
		return addStep(percent, getAction(image, add));
	}

	/**
	 * Add a step based on the whole duration with the provided percent. This
	 * step adds the provided image on the game. Whole duration MUST be set
	 * before calling this method.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param percent
	 *            the percent used to compute the real delay based on whole
	 *            duration
	 * @param image
	 *            the image to register
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addPercentStep(int percent, Image image) {
		return addStep(percent, image, true);
	}

	/**
	 * Add a step based on the whole duration with the provided percent. Whole
	 * duration MUST be set before calling this method
	 * 
	 * @param percent
	 *            the percent used to compute the real delay based on whole
	 *            duration
	 * @param action
	 *            the action to register
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addPercentStep(int percent, EnemyActionBehavior action) {
		return addStep(percent / 100 * duration, action);
	}

	/**
	 * Add a step at exact delay without taking care of whole duration. This
	 * step adds the provided image on the game.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param delay
	 *            the fixed delay
	 * @param unit
	 *            the unit for the delay
	 * @param image
	 *            the image to register
	 * @param add
	 *            true to add the image, false otherwise
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(int delay, TimeUnit unit, Image image) {
		return addStep(delay, unit, image, true);
	}

	/**
	 * Add a step at exact delay without taking care of whole duration.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param delay
	 *            the fixed delay
	 * @param unit
	 *            the unit for the delay
	 * @param image
	 *            the image to register
	 * @param add
	 *            true to add the image, false otherwise
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(int delay, TimeUnit unit, Image image, boolean add) {
		return addStep(delay, unit, getAction(image, add));
	}

	/**
	 * Add a step at exact delay without taking care of whole duration.
	 * 
	 * @param delay
	 *            the fixed delay
	 * @param unit
	 *            the unit for the delay
	 * @param action
	 *            the action to register
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(int delay, TimeUnit unit, EnemyActionBehavior action) {
		return addStep(TimeUnit.MILLISECONDS.convert(delay, unit), action);
	}

	/**
	 * Add a step at exact delay without taking care of whole duration. This
	 * step adds the provided image on the game.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param delay
	 *            the fixed delay in milliseconds
	 * @param image
	 *            the image to register
	 * @param add
	 *            true to add the image, false otherwise
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(long delay, Image image) {
		return addStep(delay, image, true);
	}

	/**
	 * Add a step at exact delay without taking care of whole duration.
	 * 
	 * {@link ActionService} and {@link Enemy} MUST be set before calling this
	 * method.
	 * 
	 * @param delay
	 *            the fixed delay in milliseconds
	 * @param image
	 *            the image to register
	 * @param add
	 *            true to add the image, false otherwise
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(long delay, Image image, boolean add) {
		return addStep(delay, getAction(image, add));
	}

	/**
	 * Add a step at exact delay without taking care of whole duration.
	 * 
	 * @param delay
	 *            the fixed delay in milliseconds
	 * @param action
	 *            the action to register
	 * @return the animation builder for chaining
	 */
	public AnimationBuilder addStep(long delay, EnemyActionBehavior action) {
		if (delay > 0) {
			action = new DelayedActionBehavior(action, delay);
		}
		frames.add(action);
		return this;
	}

	/**
	 * Generate the animation
	 * 
	 * @return the animation
	 */
	public EnemyActionBehavior build() {
		return new MultiActionBehavior(frames);
	}

	private EnemyActionBehavior getAction(Image image, boolean add) {
		if (add) {
			return new AddImageBehavior(actionService, enemy, image);
		} else {
			return new RemoveImageBehavior(actionService, enemy, image);
		}
	}

}
