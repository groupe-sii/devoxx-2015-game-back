package fr.sii.survival.config;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

import fr.sii.survival.core.util.ClassLoaderHelper;
import fr.sii.survival.core.util.FileUtil;
import fr.sii.survival.core.util.SpriteUtil;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
@Component
public class StaticResourcesConfig extends WebMvcAutoConfigurationAdapter {
	public static final String STATIC_RESOURCE_URL_PREFIX = "images";
	
	public static final String STATIC_RESOURCE_FOLDER_PATH = "images";
	
	public static final String SPRITE_GENERATION_FOLDER = SpriteUtil.SPRITE_GENERATION_FOLDER;
	
	@Value("${cache.resources.static.period}")
	Integer cachePeriod;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		try {
			registry.addResourceHandler("/"+STATIC_RESOURCE_URL_PREFIX+"/**")
					.addResourceLocations("/"+STATIC_RESOURCE_FOLDER_PATH+"/", "classpath:/"+STATIC_RESOURCE_FOLDER_PATH+"/")
					.addResourceLocations(FileUtil.getDirectory(SPRITE_GENERATION_FOLDER).toAbsolutePath().toUri().toURL().toString())
					.setCachePeriod(cachePeriod)
					.resourceChain(true)
					.addResolver(new ExtensionResourceResolver())
					.addResolver(new GzipResourceResolver());
		} catch (IOException e) {
			throw new IllegalStateException("Failed to create sprite folder", e);
		}
	}

	public static class ExtensionResourceResolver extends PathResourceResolver {

		@Override
		protected Resource getResource(String resourcePath, Resource location) throws IOException {
			Resource resource = super.getResource(resourcePath, location);
			if(resource!=null) {
				return resource;
			}
			URL extensionsUrl = ClassLoaderHelper.getResource(Paths.get(STATIC_RESOURCE_FOLDER_PATH, resourcePath).toString());
			if(extensionsUrl!=null) {
				return new UrlResource(extensionsUrl);
			}
			return null;
		}
		
	}
}
