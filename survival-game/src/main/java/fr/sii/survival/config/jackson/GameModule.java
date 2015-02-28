package fr.sii.survival.config.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.Player;

public class GameModule extends Module {

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
