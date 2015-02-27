package fr.sii.survival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.core.service.extension.ExtensionService;

@Configuration
public class ExtensionConfiguration {
	@Bean
	public ExtensionService extensionService() {
		// TODO: use real extension service
		return new ExtensionService() {
			
			@Override
			public Developer getDeveloper(PlayerListener listener) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Developer getDeveloper(ActionListener listener) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Developer getDeveloper(Class<? extends EnemyExtension> type) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Developer getDeveloper(BoardListener listener) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
