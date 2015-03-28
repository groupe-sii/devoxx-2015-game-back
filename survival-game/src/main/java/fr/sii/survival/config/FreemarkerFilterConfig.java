package fr.sii.survival.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.filter.FreemarkerStaticModelsFilter;

@Configuration
public class FreemarkerFilterConfig {

	/**
	 * Use {@link FilterRegistrationBean} in order to limit Freemarker filter
	 * scope. There is no need to initialize static models for REST requests
	 * that generate Json because templates are not used in this case
	 * 
	 * @return the filter registration bean
	 */
	@Bean
	public FilterRegistrationBean freemarkerFilterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new FreemarkerStaticModelsFilter());
		registration.addUrlPatterns("/animation");
		return registration;
	}
}
