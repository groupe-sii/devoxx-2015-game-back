package fr.sii.survival.controller;

import static fr.sii.survival.config.AnimationConfiguration.ANIMATION_MAPPING_PREFIX;
import static fr.sii.survival.config.WebSocketConfig.SERVER_PUBLISH_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.browser.Browser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.service.animation.AnimationService;

@Controller
public class AnimationController {
	@Autowired
	AnimationService animationService;
	
	@MessageMapping(ANIMATION_MAPPING_PREFIX+"/all")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/animation/all")
	@RequestMapping(value="animation", method=RequestMethod.GET)
	@ResponseBody
	@Cacheable("animations")
	public List<Animation> all() {
		return animationService.getAvailableAnimations();
	}

	@RequestMapping(value="animations.css", method=RequestMethod.GET, headers="Accept=text/css*", produces="text/css")
	public ModelAndView cssAnimations(Browser browser) {
		return new ModelAndView("css/animations", "animations", all()).addObject("vendorPrefix", browser.getVendorPrefix());
	}
	
}
