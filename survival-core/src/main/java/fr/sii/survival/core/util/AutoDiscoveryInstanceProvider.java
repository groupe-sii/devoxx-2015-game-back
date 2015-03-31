package fr.sii.survival.core.util;

import java.lang.reflect.Modifier;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.service.extension.ExtensionService;

public class AutoDiscoveryInstanceProvider<R> implements Function<Class<? extends R>, R> {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryInstanceProvider.class);

	private ExtensionService extensionService;

	private String what;

	public AutoDiscoveryInstanceProvider(String what, ExtensionService extensionService) {
		super();
		this.what = what;
		this.extensionService = extensionService;
	}

	@Override
	public R apply(Class<? extends R> type) {
		if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
			if(type.isAnnotationPresent(Developer.class)) {
				try {
					LOG.info("{}: {} found and created by {}", what, type.getName(), extensionService.getDeveloper(type));
					return type.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("{}: cannot instantiate {} created by {}. Cause: {}", what, type.getName(), extensionService.getDeveloper(type), e);
				}
			} else {
				LOG.error("{}: {} has @Developer information => skipped", what, type.getName());
			}
		}
		return null;
	}

}
