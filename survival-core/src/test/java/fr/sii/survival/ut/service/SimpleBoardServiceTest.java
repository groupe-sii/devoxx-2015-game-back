package fr.sii.survival.ut.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.listener.board.BoardListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.FixedCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;

@RunWith(MockitoJUnitRunner.class)
public class SimpleBoardServiceTest {
	@Mock
	BoardListenerManager boardListenerManager;
	
	BoardService boardService;
	
	@Before
	public void setUp() {
		boardService = new SimpleBoardService(10, 10, new FixedCellProvider(), boardListenerManager);
	}
	
	@Test
	public void add() {
		Cell cell = boardService.add(new SimpleWizard());
		Assert.assertEquals("player should be added at cell {0,0}", new Cell(0, 0), cell);
	}
	
	@Test
	public void players() {
		boardService.add(new SimpleWizard());
		List<Player> players = boardService.getPlayers(new Cell(0, 0));
		Assert.assertEquals("cell {0,0} should contain 1 player", 1, players.size());
		// check that other cells are empty
		for(int i=1 ; i<10 ; i++) {
			for(int j=0 ; j<10 ; j++) {
				Assert.assertEquals("cell {"+i+","+j+"} should contain 0 player", 0, boardService.getPlayers(new Cell(i, j)).size());
			}
		}
		boardService.add(new SimpleWizard());
		players = boardService.getPlayers(new Cell(0, 0));
		Assert.assertEquals("cell {0,0} should contain 2 players", 2, players.size());
		// check that other cells are empty
		for(int i=1 ; i<10 ; i++) {
			for(int j=0 ; j<10 ; j++) {
				Assert.assertEquals("cell {"+i+","+j+"} should contain 0 player", 0, boardService.getPlayers(new Cell(i, j)).size());
			}
		}
	}
	
	@Test
	public void move() {
		SimpleWizard player = new SimpleWizard();
		boardService.add(player);
		Cell cell = boardService.move(player, new Cell(2, 5));
		Assert.assertEquals("player should be moved at cell {2,5}", new Cell(2, 5), cell);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void moveOut() {
		SimpleWizard player = new SimpleWizard();
		boardService.add(player);
		boardService.move(player, new Cell(10, 5));
	}
	
	@Test
	public void remove() {
		SimpleWizard player = new SimpleWizard();
		boardService.add(player);
		boardService.move(player, new Cell(2, 5));
		Cell cell = boardService.remove(player);
		Assert.assertEquals("player should be removed from cell {2,5}", new Cell(2, 5), cell);
	}
	
	
	@Test
	public void removeNobody() {
		SimpleWizard player = new SimpleWizard();
		Cell cell = boardService.remove(player);
		Assert.assertNull("cell should be null", cell);
	}

}
