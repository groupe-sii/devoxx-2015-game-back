package fr.sii.survival.core.domain;

public class Cell {
	/**
	 * The x position
	 */
	private int x;

	/**
	 * The y position
	 */
	private int y;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public Cell() {
		super();
	}

	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
