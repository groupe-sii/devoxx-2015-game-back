package fr.sii.survival.ut.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Range;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
	@Mock
	PlayerListenerManager listenerManager;
	
	@Mock
	MultiGameHelper gameHelper;
	
	PlayerService playerService;
	
	Player fullLifePlayer;
	Player midLifePlayer;
	Player deadPlayer;
	
	@Before
	public void setUp() throws GameNotFoundException {
		fullLifePlayer = new SimpleWizard("test", new ClientImage("default"), 100);
		midLifePlayer = new SimpleWizard("test1", new ClientImage("default"), 50, 100);
		deadPlayer = new SimpleWizard("test2", new ClientImage("default"), 0, 100);
		playerService = new SimplePlayerService(100, Range.closed(20, 200), listenerManager, gameHelper);
	}
	
	@Test
	public void hit() throws GameException {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -10);
		Assert.assertEquals("real increment should value -10", -10, inc);
		Assert.assertEquals("player life should be", 90, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(any(), eq(fullLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void severalHits() throws GameException {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -10);
		Assert.assertEquals("real increment should value -10", -10, inc);
		Assert.assertEquals("player life should be", 90, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(any(), eq(fullLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(fullLifePlayer, -20);
		Assert.assertEquals("real increment should value -20", -20, inc);
		Assert.assertEquals("player life should be", 70, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(any(), eq(fullLifePlayer), eq(20));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(fullLifePlayer, -69);
		Assert.assertEquals("real increment should value -69", -69, inc);
		Assert.assertEquals("player life should be", 1, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(any(), eq(fullLifePlayer), eq(69));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void kill() throws GameException {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -150);
		Assert.assertEquals("real increment should value -100", -100, inc);
		Assert.assertEquals("player life should be", 0, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(any(), eq(fullLifePlayer), eq(100));
		Mockito.verify(listenerManager).triggerDead(any(), eq(fullLifePlayer));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void heal() throws GameException {
		int inc = playerService.updateCurrentLife(midLifePlayer, 10);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 60, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void severalHeals() throws GameException {
		int inc = playerService.updateCurrentLife(midLifePlayer, 10);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 60, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 20);
		Assert.assertEquals("real increment should value 20", 20, inc);
		Assert.assertEquals("player life should be", 80, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(20));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 12);
		Assert.assertEquals("real increment should value 12", 12, inc);
		Assert.assertEquals("player life should be", 92, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(12));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void revive() throws GameException {
		int inc = playerService.updateCurrentLife(deadPlayer, 50);
		Assert.assertEquals("real increment should value 50", 50, inc);
		Assert.assertEquals("player life should be", 50, deadPlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, deadPlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerRevived(any(), eq(deadPlayer));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void lifeOverMax() throws GameException {
		int inc = playerService.updateCurrentLife(midLifePlayer, 40);
		Assert.assertEquals("real increment should value 40", 40, inc);
		Assert.assertEquals("player life should be", 90, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(40));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 120);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 100, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(any(), eq(midLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 120);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 100, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void increaseMax() throws GameException {
		int inc = playerService.updateMaxLife(fullLifePlayer, 20);
		Assert.assertEquals("real increment should value 20", 20, inc);
		Assert.assertEquals("player life should be", 100, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 120, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(fullLifePlayer), eq(20));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(fullLifePlayer, 40);
		Assert.assertEquals("real increment should value 40", 40, inc);
		Assert.assertEquals("player life should be", 100, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 160, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(fullLifePlayer), eq(40));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void decreaseMax() throws GameException {
		int inc = playerService.updateMaxLife(midLifePlayer, -20);
		Assert.assertEquals("real increment should value -20", -20, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 80, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(midLifePlayer), eq(-20));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, -40);
		Assert.assertEquals("real increment should value -40", -40, inc);
		Assert.assertEquals("player life should be", 40, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 40, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(midLifePlayer), eq(-40));
		Mockito.verify(listenerManager).triggerHit(any(), eq(midLifePlayer), eq(10));
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void maxUnderLower() throws GameException {
		int inc = playerService.updateMaxLife(midLifePlayer, -200);
		Assert.assertEquals("real increment should value -80", -80, inc);
		Assert.assertEquals("player life should be", 20, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 20, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(midLifePlayer), eq(-80));
		Mockito.verify(listenerManager).triggerHit(any(), eq(midLifePlayer), eq(30));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, -200);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 20, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 20, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void maxOverUpper() throws GameException {
		int inc = playerService.updateMaxLife(midLifePlayer, 200);
		Assert.assertEquals("real increment should value 100", 100, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 200, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(any(), eq(midLifePlayer), eq(100));
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, 100);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 200, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
}
