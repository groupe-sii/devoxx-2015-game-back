package fr.sii.survival.core.util;

import fr.sii.survival.core.domain.board.Cell;

public class CellUtil {
	private CellUtil() {
		super();
	}
	
	/**
	 * Compute the distance between the two cells
	 * 
	 * @param a
	 *            the position of the first cell
	 * @param b
	 *            the position of the second cell
	 * @return the distance
	 */
	public static double distance(Cell a, Cell b) {
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
	}
}
