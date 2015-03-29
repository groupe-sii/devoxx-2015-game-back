package fr.sii.survival.ut.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.board.BoardListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.core.service.board.FixedCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;
import fr.sii.survival.core.util.MultiGameHelper;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {
	@Mock
	BoardListenerManager boardListenerManager;
	
	@Mock
	MultiGameHelper gameHelper;
	
	BoardService boardService;

	Board board;
	
	@Before
	public void setUp() {
		boardService = new SimpleBoardService(10, 10, new FixedCellProvider(), boardListenerManager, gameHelper);
		board = boardService.create();
	}
	
	@Test
	public void add() throws GameException {
		Cell cell = boardService.add(board, new SimpleWizard("test", new ClientImage("default")));
		Assert.assertEquals("player should be added at cell {0,0}", new Cell(0, 0), cell);
	}
	
	@Test
	public void players() throws GameException {
		boardService.add(board, new SimpleWizard("test", new ClientImage("default")));
		List<Player> players = boardService.getPlayers(board, new Cell(0, 0));
		Assert.assertEquals("cell {0,0} should contain 1 player", 1, players.size());
		// check that other cells are empty
		for(int i=1 ; i<10 ; i++) {
			for(int j=0 ; j<10 ; j++) {
				Assert.assertEquals("cell {"+i+","+j+"} should contain 0 player", 0, boardService.getPlayers(board, new Cell(i, j)).size());
			}
		}
		boardService.add(board, new SimpleWizard("test", new ClientImage("default")));
		players = boardService.getPlayers(board, new Cell(0, 0));
		Assert.assertEquals("cell {0,0} should contain 2 players", 2, players.size());
		// check that other cells are empty
		for(int i=1 ; i<10 ; i++) {
			for(int j=0 ; j<10 ; j++) {
				Assert.assertEquals("cell {"+i+","+j+"} should contain 0 player", 0, boardService.getPlayers(board, new Cell(i, j)).size());
			}
		}
	}
	
	@Test
	public void move() throws GameException {
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		Cell cell = boardService.move(board, player, new Cell(2, 5));
		Assert.assertEquals("player should be moved at cell {2,5}", new Cell(2, 5), cell);
	}
	
	@Test
	public void moveUp() throws GameException {
		// prepare test
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		Cell cell = boardService.move(board, player, new Cell(2, 9));
		// real test
		for(int y=8 ; y>=0 ; y--) {
			cell = boardService.move(board, player, Direction.UP);
			Assert.assertEquals("player should be moved at cell {2,"+y+"}", new Cell(2, y), cell);
		}
		cell = boardService.move(board, player, Direction.UP);
		Assert.assertEquals("player should be moved at cell {2,0}", new Cell(2, 0), cell);
		cell = boardService.move(board, player, Direction.UP);
		Assert.assertEquals("player should be moved at cell {2,0}", new Cell(2, 0), cell);
	}
	
	@Test
	public void moveDown() throws GameException {
		// prepare test
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		Cell cell = boardService.move(board, player, new Cell(2, 0));
		// real test
		for(int y=1 ; y<=9 ; y++) {
			cell = boardService.move(board, player, Direction.DOWN);
			Assert.assertEquals("player should be moved at cell {2,"+y+"}", new Cell(2, y), cell);
		}
		cell = boardService.move(board, player, Direction.DOWN);
		Assert.assertEquals("player should be moved at cell {2,9}", new Cell(2, 9), cell);
		cell = boardService.move(board, player, Direction.DOWN);
		Assert.assertEquals("player should be moved at cell {2,9}", new Cell(2, 9), cell);
	}
	
	@Test
	public void moveLeft() throws GameException {
		// prepare test
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		Cell cell = boardService.move(board, player, new Cell(9, 2));
		// real test
		for(int x=8 ; x>=0 ; x--) {
			cell = boardService.move(board, player, Direction.LEFT);
			Assert.assertEquals("player should be moved at cell {"+x+",2}", new Cell(x, 2), cell);
		}
		cell = boardService.move(board, player, Direction.LEFT);
		Assert.assertEquals("player should be moved at cell {0,2}", new Cell(0, 2), cell);
		cell = boardService.move(board, player, Direction.LEFT);
		Assert.assertEquals("player should be moved at cell {0,2}", new Cell(0, 2), cell);
	}
	
	@Test
	public void moveRight() throws GameException {
		// prepare test
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		Cell cell = boardService.move(board, player, new Cell(0, 2));
		// real test
		for(int x=1 ; x<=9 ; x++) {
			cell = boardService.move(board, player, Direction.RIGHT);
			Assert.assertEquals("player should be moved at cell {"+x+",2}", new Cell(x, 2), cell);
		}
		cell = boardService.move(board, player, Direction.RIGHT);
		Assert.assertEquals("player should be moved at cell {9,2}", new Cell(9, 2), cell);
		cell = boardService.move(board, player, Direction.RIGHT);
		Assert.assertEquals("player should be moved at cell {9,2}", new Cell(9, 2), cell);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void moveOut() throws GameException {
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		boardService.move(board, player, new Cell(10, 5));
	}
	
	@Test
	public void remove() throws GameException {
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		boardService.add(board, player);
		boardService.move(board, player, new Cell(2, 5));
		Cell cell = boardService.remove(board, player);
		Assert.assertEquals("player should be removed from cell {2,5}", new Cell(2, 5), cell);
	}
	
	
	@Test
	public void removeNobody() throws GameException {
		SimpleWizard player = new SimpleWizard("test", new ClientImage("default"));
		Cell cell = boardService.remove(board, player);
		Assert.assertNull("cell should be null", cell);
	}

}
