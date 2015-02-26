package fr.sii.survival.config.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

import fr.sii.survival.core.domain.action.Action;

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
		context.setMixInAnnotations(Action.class, ActionMixin.class);
	}

}
