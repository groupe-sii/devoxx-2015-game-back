package fr.sii.survival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.helper.MultiGameHelper;

@Configuration
public class CommonConfiguration {
	@Bean
	public MultiGameHelper multiGameHelper() {
		return new MultiGameHelper();
	}

}
