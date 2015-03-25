package fr.sii.survival.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import fr.sii.survival.core.domain.image.UriImage;

@RestController
@RequestMapping("image")
public class ImageController {

	@RequestMapping(method = RequestMethod.GET)
	public Resource display(final HttpServletRequest request) throws MalformedURLException, IOException, URISyntaxException {
		// TODO: manage path or id ?
		// TODO: provide list of available images including generated sprites
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		return new UrlResource(new UriImage("images/Green-Monster-icon.png").getUri());
	}
}
