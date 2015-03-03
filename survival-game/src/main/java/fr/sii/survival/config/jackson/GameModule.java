package fr.sii.survival.config.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;

@Component
public class GameModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8029862866377914201L;

	@Override
	public String getModuleName() {
		return "survival-game";
	}

	@Override
	public Version version() {
		return new Version(0, 0, 1, null, "fr.sii.survival", "survival-game");
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(Action.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Player.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Life.class, JsonNameIdMixin.class);
	}

}
