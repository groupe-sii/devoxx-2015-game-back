package fr.sii.survival.ut.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Range;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
	@Mock
	PlayerListenerManager listenerManager;
	
	PlayerService playerService;
	
	Player fullLifePlayer;
	Player midLifePlayer;
	Player deadPlayer;
	
	@Before
	public void setUp() {
		fullLifePlayer = new SimpleWizard("test", "default", 100);
		midLifePlayer = new SimpleWizard("test1", "default", 50, 100);
		deadPlayer = new SimpleWizard("test2", "default", 0, 100);
		playerService = new SimplePlayerService(Range.closed(20, 200), listenerManager);
	}
	
	@Test
	public void hit() {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -10);
		Assert.assertEquals("real increment should value -10", -10, inc);
		Assert.assertEquals("player life should be", 90, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(fullLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void severalHits() {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -10);
		Assert.assertEquals("real increment should value -10", -10, inc);
		Assert.assertEquals("player life should be", 90, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(fullLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(fullLifePlayer, -20);
		Assert.assertEquals("real increment should value -20", -20, inc);
		Assert.assertEquals("player life should be", 70, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(fullLifePlayer, 20);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(fullLifePlayer, -69);
		Assert.assertEquals("real increment should value -69", -69, inc);
		Assert.assertEquals("player life should be", 1, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(fullLifePlayer, 69);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void kill() {
		int inc = playerService.updateCurrentLife(fullLifePlayer, -150);
		Assert.assertEquals("real increment should value -100", -100, inc);
		Assert.assertEquals("player life should be", 0, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHit(fullLifePlayer, 100);
		Mockito.verify(listenerManager).triggerDead(fullLifePlayer);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void heal() {
		int inc = playerService.updateCurrentLife(midLifePlayer, 10);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 60, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void severalHeals() {
		int inc = playerService.updateCurrentLife(midLifePlayer, 10);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 60, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 20);
		Assert.assertEquals("real increment should value 20", 20, inc);
		Assert.assertEquals("player life should be", 80, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 20);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 12);
		Assert.assertEquals("real increment should value 12", 12, inc);
		Assert.assertEquals("player life should be", 92, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 12);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void revive() {
		int inc = playerService.updateCurrentLife(deadPlayer, 50);
		Assert.assertEquals("real increment should value 50", 50, inc);
		Assert.assertEquals("player life should be", 50, deadPlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, deadPlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerRevived(deadPlayer);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void lifeOverMax() {
		int inc = playerService.updateCurrentLife(midLifePlayer, 40);
		Assert.assertEquals("real increment should value 40", 40, inc);
		Assert.assertEquals("player life should be", 90, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 40);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 120);
		Assert.assertEquals("real increment should value 10", 10, inc);
		Assert.assertEquals("player life should be", 100, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerHealed(midLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateCurrentLife(midLifePlayer, 120);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 100, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 100, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
	
	@Test
	public void increaseMax() {
		int inc = playerService.updateMaxLife(fullLifePlayer, 20);
		Assert.assertEquals("real increment should value 20", 20, inc);
		Assert.assertEquals("player life should be", 100, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 120, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(fullLifePlayer, 20);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(fullLifePlayer, 40);
		Assert.assertEquals("real increment should value 40", 40, inc);
		Assert.assertEquals("player life should be", 100, fullLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 160, fullLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(fullLifePlayer, 40);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void decreaseMax() {
		int inc = playerService.updateMaxLife(midLifePlayer, -20);
		Assert.assertEquals("real increment should value -20", -20, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 80, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(midLifePlayer, -20);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, -40);
		Assert.assertEquals("real increment should value -40", -40, inc);
		Assert.assertEquals("player life should be", 40, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 40, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(midLifePlayer, -40);
		Mockito.verify(listenerManager).triggerHit(midLifePlayer, 10);
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void maxUnderLower() {
		int inc = playerService.updateMaxLife(midLifePlayer, -200);
		Assert.assertEquals("real increment should value -80", -80, inc);
		Assert.assertEquals("player life should be", 20, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 20, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(midLifePlayer, -80);
		Mockito.verify(listenerManager).triggerHit(midLifePlayer, 30);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, -200);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 20, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 20, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}

	@Test
	public void maxOverUpper() {
		int inc = playerService.updateMaxLife(midLifePlayer, 200);
		Assert.assertEquals("real increment should value 100", 100, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 200, midLifePlayer.getLife().getMax());
		Mockito.verify(listenerManager).triggerMaxLifeChanged(midLifePlayer, 100);
		Mockito.verifyNoMoreInteractions(listenerManager);
		
		inc = playerService.updateMaxLife(midLifePlayer, 100);
		Assert.assertEquals("real increment should value 0", 0, inc);
		Assert.assertEquals("player life should be", 50, midLifePlayer.getLife().getCurrent());
		Assert.assertEquals("player max life should be", 200, midLifePlayer.getLife().getMax());
		Mockito.verifyNoMoreInteractions(listenerManager);
	}
}
