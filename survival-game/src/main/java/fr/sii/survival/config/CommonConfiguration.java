package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.config.options.ExtensionsOptions;
import fr.sii.survival.core.reload.ExtensionsClassLoaderReloader;
import fr.sii.survival.core.reload.MD5Predicate;
import fr.sii.survival.core.reload.ReloadWatcher;
import fr.sii.survival.core.reload.FixedRateWatcher;
import fr.sii.survival.core.util.ClassLoaderHelper;
import fr.sii.survival.core.util.MultiGameHelper;

@Configuration
public class CommonConfiguration {
	@Autowired
	ExtensionsOptions extensionsOptions;
	
	@PostConstruct
	public void initialize() {
		ClassLoaderHelper.initialize(extensionsOptions.getUrl());
	}

	@Bean
	public MultiGameHelper multiGameHelper() {
		return new MultiGameHelper();
	}

	@Bean
	public ReloadWatcher reloadWatcher() {
		return new FixedRateWatcher(new MD5Predicate(), extensionsOptions.getReloadCheckRate());
	}

	@Bean
	public ExtensionsClassLoaderReloader classLoaderReloader() {
		return new ExtensionsClassLoaderReloader(reloadWatcher());
	}
}
