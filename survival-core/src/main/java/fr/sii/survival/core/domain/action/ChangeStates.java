package fr.sii.survival.core.domain.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;

public class ChangeStates implements Action {
	/**
	 * The cell to target
	 */
	private Cell cell;

	/**
	 * A list of changes to apply. Each change provide the name of the state and
	 * the action to apply on the provided state (add or remove)
	 */
	private List<StateChange> stateChanges;

	public ChangeStates(ChangeStates action) {
		this(action.getCell(), new ArrayList<>(action.getStateChanges()));
	}
	
	public ChangeStates(Cell cell, StateChange... changes) {
		this(cell, new ArrayList<>(Arrays.asList(changes)));
	}

	public ChangeStates(Cell cell, List<StateChange> stateChanges) {
		super();
		this.cell = cell;
		this.stateChanges = stateChanges;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public List<StateChange> getStateChanges() {
		return stateChanges;
	}

	public void setStateChanges(List<StateChange> stateChanges) {
		this.stateChanges = stateChanges;
	}

	public void addStateChange(StateChange stateChange) {
		stateChanges.add(stateChange);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((stateChanges == null) ? 0 : stateChanges.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangeStates other = (ChangeStates) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (stateChanges == null) {
			if (other.stateChanges != null)
				return false;
		} else if (!stateChanges.equals(other.stateChanges))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeStates [cell=").append(cell).append(", stateChanges=").append(stateChanges).append("]");
		return builder.toString();
	}
}
