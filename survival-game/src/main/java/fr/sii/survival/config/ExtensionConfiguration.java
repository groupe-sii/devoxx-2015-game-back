package fr.sii.survival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.ext.annotation.AnnotationDeveloperProvider;
import fr.sii.survival.core.service.extension.DelegateExtensionService;
import fr.sii.survival.core.service.extension.ExtensionService;

@Configuration
public class ExtensionConfiguration {
	@Bean
	public ExtensionService extensionService() {
		return new DelegateExtensionService(new AnnotationDeveloperProvider());
	}
}
