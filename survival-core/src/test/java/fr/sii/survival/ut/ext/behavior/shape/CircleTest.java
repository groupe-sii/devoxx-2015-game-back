package fr.sii.survival.ut.ext.behavior.shape;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.behavior.action.shape.Circle;

public class CircleTest {
	private Board board;

	@Before
	public void setUp() {
		board = new Board(10, 10);
	}
	
	/**
	 * Assert that r=1, x=1, y=1 provides the following shape:
	 * 
	 * <pre>
	 *  x
	 * xxx
	 *  x
	 * </pre>
	 * 
	 * <pre>
	 *        {1,0}
	 *  {0,1} {1,1} {2,1}
	 *        {1,2}
	 * </pre>
	 */
	@Test
	public void r1x1y1() {
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(1, 0));
		cells.add(new Cell(0, 1));
		cells.add(new Cell(1, 1));
		cells.add(new Cell(2, 1));
		cells.add(new Cell(1, 2));
		List<Cell> actual = new Circle(board, 1).getCells(new Cell(1, 1));
		Assert.assertEquals("should contain 5 cells", 5, actual.size());
		Assert.assertEquals("should be same as expected list", cells, actual);
	}
	
	/**
	 * Assert that r=1, x=0, y=0 provides the following shape:
	 * 
	 * <pre>
	 *  x
	 * xxx
	 *  x
	 * </pre>
	 * 
	 * <pre>
	 *         {0,-1}
	 *  {-1,0} {0, 0} {1,0}
	 *         {0, 1}
	 * </pre>
	 */
	@Test
	public void r1x0y0() {
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(0, 0));
		cells.add(new Cell(1, 0));
		cells.add(new Cell(0, 1));
		List<Cell> actual = new Circle(board, 1).getCells(new Cell(0, 0));
		Assert.assertEquals("should contain 3 cells", 3, actual.size());
		Assert.assertEquals("should be same as expected list", cells, actual);
	}
	
	/**
	 * Assert that r=1, x=9, y=9 provides the following shape:
	 * 
	 * <pre>
	 *  x
	 * xxx
	 *  x
	 * </pre>
	 * 
	 * <pre>
	 *         {9, 8}
	 *   {8,9} {9, 9} {10,9}
	 *         {9,10}
	 * </pre>
	 */
	@Test
	public void r1x9y9() {
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(9, 8));
		cells.add(new Cell(8, 9));
		cells.add(new Cell(9, 9));
		List<Cell> actual = new Circle(board, 1).getCells(new Cell(9, 9));
		Assert.assertEquals("should contain 3 cells", 3, actual.size());
		Assert.assertEquals("should be same as expected list", cells, actual);
	}
	
	/**
	 * Assert that r=2, x=2, y=2 provides the following shape:
	 * 
	 * <pre>
	 *   x
	 *  xxx
	 * xxxxx
	 *  xxx
	 *   x
	 * </pre>
	 * 
	 * <pre>
	 *              {2,0}
	 *        {1,1} {2,1} {3,1}
	 *  {0,2} {1,2} {2,2} {3,2} {4,2}
	 *        {1,3} {2,3} {3,3}
	 *              {2,4}
	 * </pre>
	 */
	@Test
	public void r2x2y2() {
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(2, 0));
		cells.add(new Cell(1, 1));
		cells.add(new Cell(2, 1));
		cells.add(new Cell(3, 1));
		cells.add(new Cell(0, 2));
		cells.add(new Cell(1, 2));
		cells.add(new Cell(2, 2));
		cells.add(new Cell(3, 2));
		cells.add(new Cell(4, 2));
		cells.add(new Cell(1, 3));
		cells.add(new Cell(2, 3));
		cells.add(new Cell(3, 3));
		cells.add(new Cell(2, 4));
		List<Cell> actual = new Circle(board, 2).getCells(new Cell(2, 2));
		Assert.assertEquals("should contain 13 cells", 13, actual.size());
		Assert.assertEquals("should be same as expected list", cells, actual);
	}
}
