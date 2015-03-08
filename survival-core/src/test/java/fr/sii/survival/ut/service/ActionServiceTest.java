package fr.sii.survival.ut.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.ActionManagerNotFoundException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.action.ActionListenerManager;
import fr.sii.survival.core.service.action.ActionManager;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.action.ChangePositionActionManager;
import fr.sii.survival.core.service.action.ChangeStateActionManager;
import fr.sii.survival.core.service.action.DelegateActionService;
import fr.sii.survival.core.service.action.MoveImageActionManager;
import fr.sii.survival.core.service.action.UpdateCurrentLifeActionManager;
import fr.sii.survival.core.service.action.UpdateMaxLifeActionManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.player.PlayerService;

@RunWith(MockitoJUnitRunner.class)
public class ActionServiceTest {
	@Mock
	ActionListenerManager listenerManager;
	
	@Mock
	BoardService boardService;
	
	@Mock
	PlayerService playerService;
	
	@Mock
	Game game;
	
	ActionService actionService;
	
	@Before
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUp() {
		ArrayList<ActionManager<Action>> managers = new ArrayList<>();
		managers.add((ActionManager) new UpdateCurrentLifeActionManager(boardService, playerService, listenerManager));
		managers.add((ActionManager) new MoveImageActionManager(listenerManager));
		managers.add((ActionManager) new ChangePositionActionManager(boardService, listenerManager));
		managers.add((ActionManager) new UpdateMaxLifeActionManager(boardService, playerService, listenerManager));
		managers.add((ActionManager) new ChangeStateActionManager(boardService, playerService, listenerManager));
		actionService = new DelegateActionService(listenerManager, managers);
	}
	
	@Test
	public void move() throws GameException {
		Player player = new SimpleWizard("test", "default");
		Mockito.when(boardService.getPlayers(any(), eq(new Cell(0, 0)))).thenReturn(Arrays.asList(player));
		Mockito.when(boardService.move(any(), eq(player), eq(new Cell(9, 9)))).thenReturn(new Cell(9, 9));
		actionService.execute(game, new ChangePosition(new Cell(0, 0), new Cell(9, 9)));
		Mockito.verify(listenerManager).triggerPositionChanged(any(), eq(player), eq(new ChangePosition(new Cell(0, 0), new Cell(9, 9))));
	}
	
	@Test
	public void updateLife() {
		// TODO
	}
	
	@Test
	public void updateMaxLife() {
		// TODO
	}

	@Test(expected=ActionManagerNotFoundException.class)
	public void invalidAction() throws ActionException {
		actionService.execute(game, new Action() {});
	}
}
