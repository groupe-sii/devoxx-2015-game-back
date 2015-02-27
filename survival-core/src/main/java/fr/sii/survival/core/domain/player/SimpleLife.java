package fr.sii.survival.core.domain.player;

/**
 * Simple life bean that just stores values. No verification is done on values
 * (example: no verification is done if current life is over max life).
 * 
 * @author aurelien
 *
 */
public class SimpleLife implements Life {

	private int current;

	private int max;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public SimpleLife() {
		super();
	}
	
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
		return current + delta;
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
		return max + delta;
	}

}
