package fr.sii.survival.core.ext.behavior.move;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.board.Cell;

public class FollowPlayerBehaviorTest {
	private FollowPlayerBehavior behavior = new FollowPlayerBehavior(null, null, 1, 1);

	@Test
	public void testComputeAllNull() {
		Cell result = behavior.computeNextCellMoveOne(null, null);

		Assert.assertNull(result);
	}

	@Test
	public void testComputeNoTarget() {
		Cell from = new Cell(2, 2);
		Cell to = null;
		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(from, result);
	}

	@Test
	public void testComputeMoveSameCell() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(2, 2);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(from, result);
		Assert.assertEquals(to, result);
	}

	@Test
	public void testComputeMoveCell2XRight() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(4, 2);
		Cell expected = new Cell(3, 2);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveCell2XLeft() {
		Cell from = new Cell(4, 2);
		Cell to = new Cell(2, 2);
		Cell expected = new Cell(3, 2);
		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveCell2YDown() {
		Cell from = new Cell(2, 4);
		Cell to = new Cell(2, 2);
		Cell expected = new Cell(2, 3);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveCell2YUp() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(2, 4);
		Cell expected = new Cell(2, 3);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveCell2XLeft2YUp() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(4, 4);
		Cell expected = new Cell(3, 2);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveCell2XLeft3YUp() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(4, 5);
		Cell expected = new Cell(2, 3);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testComputeMoveDistanceOne() {
		Cell from = new Cell(2, 2);
		Cell to = new Cell(2, 3);
		Cell expected = new Cell(2, 2);

		Cell result = behavior.computeNextCellMoveOne(from, to);

		Assert.assertEquals(expected, result);
	}
}
