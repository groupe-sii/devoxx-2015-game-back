package fr.sii.survival.core.domain.animation;

public class AnimationOptions {
	/**
	 * The number of times that the animation should be run:
	 * <ul>
	 * <li>1 means 1 execution (no repeat)</li>
	 * <li>2 and over means the exact number of execution</li>
	 * <li>0 means no execution</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 */
	private int count;

	/**
	 * Direction of the animation
	 */
	private Direction direction;

	/**
	 * The delay before starting the animation (in milliseconds)
	 */
	private long delay;

	/**
	 * The animation will be executed one time.
	 * 
	 * The animation will be executed in normal direction (starts by the
	 * beginning).
	 * 
	 * The animation will start after the provided delay (in milliseconds)
	 */
	public AnimationOptions() {
		this(Direction.NORMAL);
	}

	/**
	 * The animation will be executed one time.
	 * 
	 * The animation will be executed in normal direction (starts by the
	 * beginning).
	 * 
	 * The animation will start after the provided delay (in milliseconds)
	 * 
	 * @param delay
	 *            the delay before starting the animation (in milliseconds)
	 */
	public AnimationOptions(long delay) {
		this(Direction.NORMAL, delay);
	}

	/**
	 * The animation will be executed one time.
	 * 
	 * The animation will be executed in provided direction:
	 * <ul>
	 * <li>NORMAL means that animation starts by the beginning</li>
	 * <li>REVERSE means that animation starts by the end</li>
	 * <li>ALTERNATE means that animation starts by the beginning and when end
	 * is reached, the animation is played in the reverse direction</li>
	 * <li>ALTERNATE-REVERSE means that animation starts by the end and when
	 * start is reached, the animation is played in the normal direction</li>
	 * </ul>
	 * 
	 * The animation will start immediately.
	 * 
	 * @param direction
	 *            the direction of the animation
	 */
	public AnimationOptions(Direction direction) {
		this(direction, 0);
	}

	/**
	 * Animation that may be executed several times according to repeat
	 * parameter:
	 * <ul>
	 * <li>1 means 1 execution (no repeat)</li>
	 * <li>2 and over means the exact number of execution</li>
	 * <li>0 means no execution</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 * 
	 * The animation will be executed in normal direction (starts by the
	 * beginning).
	 * 
	 * The animation will start immediately.
	 * 
	 * @param count
	 *            the number of executions of the animation
	 */
	public AnimationOptions(int count) {
		this(count, 0);
	}

	/**
	 * The animation will be executed one time.
	 * 
	 * The animation will be executed in provided direction:
	 * <ul>
	 * <li>NORMAL means that animation starts by the beginning</li>
	 * <li>REVERSE means that animation starts by the end</li>
	 * <li>ALTERNATE means that animation starts by the beginning and when end
	 * is reached, the animation is played in the reverse direction</li>
	 * <li>ALTERNATE-REVERSE means that animation starts by the end and when
	 * start is reached, the animation is played in the normal direction</li>
	 * </ul>
	 * 
	 * The animation will start after the provided delay (in milliseconds)
	 * 
	 * @param direction
	 *            the direction of the animation
	 * @param delay
	 *            the delay before starting the animation (in milliseconds)
	 */
	public AnimationOptions(Direction direction, long delay) {
		this(1, direction, delay);
	}

	/**
	 * Animation that may be executed several times according to repeat
	 * parameter:
	 * <ul>
	 * <li>1 means 1 execution (no repeat)</li>
	 * <li>2 and over means the exact number of execution</li>
	 * <li>0 means no execution</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 * 
	 * The animation will be executed in provided direction:
	 * <ul>
	 * <li>NORMAL means that animation starts by the beginning</li>
	 * <li>REVERSE means that animation starts by the end</li>
	 * <li>ALTERNATE means that animation starts by the beginning and when end
	 * is reached, the animation is played in the reverse direction</li>
	 * <li>ALTERNATE-REVERSE means that animation starts by the end and when
	 * start is reached, the animation is played in the normal direction</li>
	 * </ul>
	 * 
	 * The animation will start immediately.
	 * 
	 * @param count
	 *            the number of executions of the animation
	 * @param direction
	 *            the direction of the animation
	 */
	public AnimationOptions(int count, Direction direction) {
		this(count, direction, 0);
	}

	/**
	 * Animation that may be executed several times according to repeat
	 * parameter:
	 * <ul>
	 * <li>1 means 1 execution (no repeat)</li>
	 * <li>2 and over means the exact number of execution</li>
	 * <li>0 means no execution</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 * 
	 * The animation will be executed in normal direction (starts by the
	 * beginning).
	 * 
	 * The animation will start after the provided delay (in milliseconds)
	 * 
	 * @param count
	 *            the number of executions of the animation
	 * @param delay
	 *            the delay before starting the animation (in milliseconds)
	 */
	public AnimationOptions(int count, long delay) {
		this(count, Direction.NORMAL, delay);
	}

	/**
	 * Animation that may be executed several times according to repeat
	 * parameter:
	 * <ul>
	 * <li>1 means 1 execution (no repeat)</li>
	 * <li>2 and over means the exact number of execution</li>
	 * <li>0 means no execution</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 * 
	 * The animation will be executed in provided direction:
	 * <ul>
	 * <li>NORMAL means that animation starts by the beginning</li>
	 * <li>REVERSE means that animation starts by the end</li>
	 * <li>ALTERNATE means that animation starts by the beginning and when end
	 * is reached, the animation is played in the reverse direction</li>
	 * <li>ALTERNATE-REVERSE means that animation starts by the end and when
	 * start is reached, the animation is played in the normal direction</li>
	 * </ul>
	 * 
	 * The animation will start after the provided delay (in milliseconds)
	 * 
	 * @param count
	 *            the number of executions of the animation
	 * @param direction
	 *            the direction of the animation
	 * @param delay
	 *            the delay before starting the animation (in milliseconds)
	 */
	public AnimationOptions(int count, Direction direction, long delay) {
		super();
		this.count = count;
		this.direction = direction;
		this.delay = delay;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnimationOptions [count=").append(count).append(", direction=").append(direction).append(", delay=").append(delay).append("]");
		return builder.toString();
	}
}
