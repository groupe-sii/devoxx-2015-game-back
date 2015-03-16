package fr.sii.survival.core.ext.behavior.action.shape;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

public class Circle extends AbstractShape {

	private int radius;
	
	public Circle(Board board, int radius) {
		super(board);
		this.radius = radius;
	}

	@Override
	public List<Cell> getCells(Cell origin) {
		List<Cell> cells = new ArrayList<>();
		for(int j=origin.getY()-radius ; j<=origin.getY()+radius ; j++) {
			for(int i=origin.getX()-radius ; i<=origin.getX()+radius ; i++) {
				if(isInBoard(i, j) && isInCircle(origin, j, i)) {
					cells.add(new Cell(i, j));
				}
			}
		}
		return cells;
	}

	private boolean isInCircle(Cell origin, int j, int i) {
		return (Math.pow(i-origin.getX(), 2) + Math.pow(j-origin.getY(), 2))<=Math.pow(radius, 2);
	}

}
