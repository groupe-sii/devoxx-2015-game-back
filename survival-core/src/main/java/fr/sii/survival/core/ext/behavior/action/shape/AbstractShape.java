package fr.sii.survival.core.ext.behavior.action.shape;

import fr.sii.survival.core.domain.board.Board;

public abstract class AbstractShape implements Shape {

	protected Board board;

	public AbstractShape(Board board) {
		super();
		this.board = board;
	}

	protected boolean isInBoard(int x, int y) {
		return x>=0 && x<board.getWidth() && y>=0 && y<board.getHeight();
	}

}