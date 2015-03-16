package fr.sii.survival.core.ext.behavior.action.shape;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

public class Rectangle extends AbstractShape {

	private int width;
	
	private int height;
	
	public Rectangle(Board board, int width, int height) {
		super(board);
		this.width = width;
		this.height = height;
	}

	@Override
	public List<Cell> getCells(Cell origin) {
		List<Cell> cells = new ArrayList<>();
		for(int j=origin.getY()-height/2 ; j<=origin.getY()+height/2 ; j++) {
			for(int i=origin.getX()-width/2 ; i<=origin.getX()+width/2 ; i++) {
				if(isInBoard(i, j)) {
					cells.add(new Cell(i, j));
				}
			}
		}
		return cells;
	}

}
