package fr.sii.survival.core.ext.behavior.action;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;

public interface Shape {
	public List<Cell> getCells(Cell origin);
}
