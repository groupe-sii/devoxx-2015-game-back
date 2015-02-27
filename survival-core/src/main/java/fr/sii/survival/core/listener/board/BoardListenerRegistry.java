package fr.sii.survival.core.listener.board;

public interface BoardListenerRegistry {
	public void addBoardListener(BoardListener listener);
	
	public void removeBoardListener(BoardListener listener);
}
