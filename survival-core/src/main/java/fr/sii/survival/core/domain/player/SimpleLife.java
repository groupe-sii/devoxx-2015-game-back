package fr.sii.survival.core.domain.player;

/**
 * Simple life bean that just stores values. No verification is done on values
 * (example: no check is done if current life is over max life).
 * 
 * @author aurelien
 *
 */
public class SimpleLife implements Life {

	/**
	 * The current points of life
	 */
	private int current;

	/**
	 * The maximum points of life
	 */
	private int max;

	public SimpleLife(int max) {
		this(max, max);
	}

	public SimpleLife(int current, int max) {
		super();
		this.current = current;
		this.max = max;
	}

	@Override
	public int getCurrent() {
		return current;
	}

	@Override
	public void setCurrent(int life) {
		current = life;
	}

	@Override
	public int updateCurrent(int delta) {
		current += delta;
		return current;
	}

	@Override
	public int getMax() {
		return max;
	}

	@Override
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public int updateMax(int delta) {
		max += delta;
		return max;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(").append(current).append("/").append(max).append(")");
		return builder.toString();
	}
}
