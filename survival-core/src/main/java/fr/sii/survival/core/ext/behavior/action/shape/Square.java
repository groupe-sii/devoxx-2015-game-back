package fr.sii.survival.core.ext.behavior.action.shape;

import fr.sii.survival.core.domain.board.Board;

public class Square extends Rectangle {

	public Square(Board board, int width) {
		super(board, width, width);
	}

}
