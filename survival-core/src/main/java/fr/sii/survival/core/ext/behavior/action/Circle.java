package fr.sii.survival.core.ext.behavior.action;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;

public class Circle implements Shape {

	private int radius;
	
	public Circle(int radius) {
		super();
		this.radius = radius;
	}

	@Override
	public List<Cell> getCells(Cell origin) {
		List<Cell> cells = new ArrayList<>();
		for(int j=origin.getY()-radius ; j<=origin.getY()+radius ; j++) {
			for(int i=origin.getX()-radius ; i<=origin.getX()+radius ; i++) {
				if((Math.pow(i-origin.getX(), 2) + Math.pow(j-origin.getY(), 2))<=Math.pow(radius, 2)) {
					cells.add(new Cell(i, j));
				}
			}
		}
		return cells;
	}

}
