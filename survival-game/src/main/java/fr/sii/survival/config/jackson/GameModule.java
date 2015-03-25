package fr.sii.survival.config.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fr.sii.survival.config.jackson.mixin.AddImageMixin;
import fr.sii.survival.config.jackson.mixin.Base64ServerImageMixin;
import fr.sii.survival.config.jackson.mixin.CellMixin;
import fr.sii.survival.config.jackson.mixin.ChangePositionMixin;
import fr.sii.survival.config.jackson.mixin.ClientImageMixin;
import fr.sii.survival.config.jackson.mixin.JsonNameIdMixin;
import fr.sii.survival.config.jackson.mixin.MoveImageMixin;
import fr.sii.survival.config.jackson.mixin.PlayerInfoMixin;
import fr.sii.survival.config.jackson.mixin.RemoveImageMixin;
import fr.sii.survival.config.jackson.mixin.SimpleLifeMixin;
import fr.sii.survival.config.jackson.mixin.StartAnimationMixin;
import fr.sii.survival.config.jackson.mixin.StateChangeMixin;
import fr.sii.survival.config.jackson.mixin.StopAnimationMixin;
import fr.sii.survival.config.jackson.mixin.UpdateLifeMixin;
import fr.sii.survival.config.jackson.mixin.UriImageMixin;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StopAnimation;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.action.UpdateMaxLife;
import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.SimpleLife;

@Component
public class GameModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8029862866377914201L;

	
	public GameModule() {
		super("survival-game", new Version(0, 0, 1, null, "fr.sii.survival", "survival-game"));
	}

	@Override
	public void setupModule(SetupContext context) {
		// add automatic class information for inheritance
		context.setMixInAnnotations(Action.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Player.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Life.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Image.class, JsonNameIdMixin.class);
		context.setMixInAnnotations(Animation.class, JsonNameIdMixin.class);
		// add specific information for deserialization
		context.setMixInAnnotations(AddImage.class, AddImageMixin.class);
		context.setMixInAnnotations(ChangePosition.class, ChangePositionMixin.class);
		context.setMixInAnnotations(MoveImage.class, MoveImageMixin.class);
		context.setMixInAnnotations(RemoveImage.class, RemoveImageMixin.class);
		context.setMixInAnnotations(StartAnimation.class, StartAnimationMixin.class);
		context.setMixInAnnotations(StateChange.class, StateChangeMixin.class);
		context.setMixInAnnotations(StopAnimation.class, StopAnimationMixin.class);
		context.setMixInAnnotations(UpdateLife.class, UpdateLifeMixin.class);
		context.setMixInAnnotations(UpdateCurrentLife.class, UpdateLifeMixin.class);
		context.setMixInAnnotations(UpdateMaxLife.class, UpdateLifeMixin.class);
		context.setMixInAnnotations(SimpleLife.class, SimpleLifeMixin.class);
		context.setMixInAnnotations(PlayerInfo.class, PlayerInfoMixin.class);
		context.setMixInAnnotations(UriImage.class, UriImageMixin.class);
		context.setMixInAnnotations(ClientImage.class, ClientImageMixin.class);
		context.setMixInAnnotations(Base64ServerImage.class, Base64ServerImageMixin.class);
		context.setMixInAnnotations(Cell.class, CellMixin.class);
	}

}
