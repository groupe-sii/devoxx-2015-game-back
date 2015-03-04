package fr.sii.survival.ut.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.ActionManagerNotFoundException;
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
	public void move() throws ActionException {
		Player player = new SimpleWizard("test", "default");
		Mockito.when(boardService.getPlayers(new Cell(0, 0))).thenReturn(Arrays.asList(player));
		Mockito.when(boardService.move(player, new Cell(9, 9))).thenReturn(new Cell(9, 9));
		actionService.execute(new ChangePosition(new Cell(0, 0), new Cell(9, 9)));
		Mockito.verify(listenerManager).triggerPositionChanged(player, new ChangePosition(new Cell(0, 0), new Cell(9, 9)));
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
		actionService.execute(new Action() {});
	}
}
