package fr.sii.survival.ut.helper;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.util.MultiGameHelper;

@RunWith(MockitoJUnitRunner.class)
public class MultiGameHelperTest {
	Game game1;
	
	Game game2;
	
	@Mock
	Board board1;
	
	@Mock
	Board board2;
	
	@Mock
	Board board3;
	
	@Mock
	Player player1;
	
	@Mock
	Player player2;
	
	@Mock
	Player player3;
	
	@Mock
	Player player4;
	
	MultiGameHelper multiGameHelper;

	@Before
	public void setUp() {
		multiGameHelper = new MultiGameHelper();
		// init players
		when(player1.getId()).thenReturn("player1");
		when(player2.getId()).thenReturn("player2");
		when(player3.getId()).thenReturn("player3");
		when(player4.getId()).thenReturn("player4");
		// init games
		game1 = new Game(board1);
		game2 = new Game(board2);
		// add players into games
		game1.addPlayer(player1);
		game1.addPlayer(player2);
		game2.addPlayer(player3);
		// register games
		multiGameHelper.set("1", game1);
		multiGameHelper.set("2", game2);
	}
	
	@Test
	public void existingGameFromPlayer() throws GameNotFoundException {
		Game game = multiGameHelper.getGame(player1);
		Assert.assertEquals("player1 should give game1", game1, game);

		game = multiGameHelper.getGame(player2);
		Assert.assertEquals("player2 should give game1", game1, game);

		game = multiGameHelper.getGame(player3);
		Assert.assertEquals("player3 should give game2", game2, game);
	}
	
	@Test(expected=GameNotFoundException.class)
	public void noGameFromPlayer() throws GameNotFoundException {
		multiGameHelper.getGame(player4);
	}
	
	@Test
	public void existingGameFromBoard() throws GameNotFoundException {
		Game game = multiGameHelper.getGame(board1);
		Assert.assertEquals("board1 should give game1", game1, game);

		game = multiGameHelper.getGame(board2);
		Assert.assertEquals("board2 should give game2", game2, game);
	}
	
	@Test(expected=GameNotFoundException.class)
	public void noGameFromBoard() throws GameNotFoundException {
		multiGameHelper.getGame(board3);
	}
}
