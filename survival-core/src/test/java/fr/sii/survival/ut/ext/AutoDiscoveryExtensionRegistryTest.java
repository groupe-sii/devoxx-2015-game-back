package fr.sii.survival.ut.ext;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.annotation.AnnotationDeveloperProvider;
import fr.sii.survival.core.ext.registry.AutoDiscoveryExtensionRegistry;
import fr.sii.survival.core.ext.registry.ExtensionRegistry;
import fr.sii.survival.core.service.extension.DelegateExtensionService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.mock.ext.BasicEnemyExtension;

@RunWith(MockitoJUnitRunner.class)
public class AutoDiscoveryExtensionRegistryTest {

	@Mock
	MessageService errorService;

	ExtensionService extensionService;

	ExtensionRegistry registry;

	@Before
	public void setUp() {
		extensionService = new DelegateExtensionService(new AnnotationDeveloperProvider());
		registry = new AutoDiscoveryExtensionRegistry("fr.sii.survival.mock.ext", errorService, extensionService);
	}

	@Test
	public void basicEnemy() {
		List<EnemyExtension> enemyExtensions = registry.getEnemyExtensions();
		Assert.assertEquals("should have 1 valid extension", 1, enemyExtensions.size());
		EnemyExtension enemyExtension = enemyExtensions.get(0);
		Assert.assertEquals("class should be "+BasicEnemyExtension.class.getSimpleName(), BasicEnemyExtension.class, enemyExtension.getClass());
		Assert.assertEquals("developer should be abaudet", "abaudet", extensionService.getDeveloper(enemyExtension.getClass()).getNickname());
		verify(errorService, times(1)).addError(anyObject());
	}
}
