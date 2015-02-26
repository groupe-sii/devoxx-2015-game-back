package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.controller.ErrorController;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.error.ErrorListener;
import fr.sii.survival.core.service.error.ErrorService;

@Configuration
public class ErrorConfiguration {
	@Autowired
	ErrorController errorController;
	
	@PostConstruct
	public void init() {
		errorService().addErrorListener(errorController);
	}
	
	@Bean
	public ErrorService errorService() {
		// TODO: use real error service
		return new ErrorService() {
			
			@Override
			public void addError(GameException e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addErrorListener(ErrorListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeErrorListener(ErrorListener listener) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
