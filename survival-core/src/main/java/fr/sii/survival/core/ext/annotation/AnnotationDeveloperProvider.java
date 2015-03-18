package fr.sii.survival.core.ext.annotation;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.ext.DeveloperProvider;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.player.PlayerListener;

public class AnnotationDeveloperProvider implements DeveloperProvider {

	@Override
	public Developer getDeveloper(ActionListener listener) {
		return getDeveloperFromAnnotation(listener.getClass());
	}

	@Override
	public Developer getDeveloper(PlayerListener listener) {
		return getDeveloperFromAnnotation(listener.getClass());
	}

	@Override
	public Developer getDeveloper(BoardListener listener) {
		return getDeveloperFromAnnotation(listener.getClass());
	}
	
	@Override
	public Developer getDeveloper(GameListener listener) {
		return getDeveloperFromAnnotation(listener.getClass());
	}

	@Override
	public Developer getDeveloper(Class<?> type) {
		return getDeveloperFromAnnotation(type);
	}

	private static Developer getDeveloperFromAnnotation(Class<?> clazz) {
		fr.sii.survival.core.ext.annotation.Developer annotation = clazz.getAnnotation(fr.sii.survival.core.ext.annotation.Developer.class);
		return annotation==null ? null : new Developer(annotation);
	}
}
