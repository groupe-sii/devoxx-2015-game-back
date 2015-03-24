package fr.sii.survival.controller;

import static fr.sii.survival.config.AnimationConfiguration.ANIMATION_MAPPING_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.service.animation.AnimationService;

@Controller
public class AnimationController {
	@Autowired
	AnimationService animationService;
	
	@MessageMapping(ANIMATION_MAPPING_PREFIX+"/all")
	@SendToUser
	public List<Animation> all() {
		return animationService.getAvailableAnimations();
	}

}
