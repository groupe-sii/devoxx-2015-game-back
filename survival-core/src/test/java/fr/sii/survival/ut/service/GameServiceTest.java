package fr.sii.survival.ut.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.game.GameListenerRegistry;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.game.GameRunner;
import fr.sii.survival.core.service.game.GameSelector;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.game.SimpleGameService;
import fr.sii.survival.core.service.message.MessageService;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
	
	@Mock
	MultiGameHelper gameHelper;

	@Mock
	BoardService boardService;

	@Mock
	MessageService messageService;

	@Mock
	GameListenerRegistry listenerRegistry;

	@Mock
	ExtensionProvider extensionProvider;

	@Mock
	GameSelector gameSelector;

	@Mock
	GameRunner gameRunner;

	GameService gameService;
	
	@Before
	public void setUp() {
		when(boardService.create()).thenReturn(new Board(10, 10));
		gameService = new SimpleGameService(5, 100, boardService, listenerRegistry, gameHelper, gameSelector, gameRunner);
	}
	
	@Test
	public void create() {
		Game game = gameService.create();
		Assert.assertNotNull("Game should not be null", game);
	}
}
