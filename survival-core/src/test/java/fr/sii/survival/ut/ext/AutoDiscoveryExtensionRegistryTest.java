package fr.sii.survival.ut.ext;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.annotation.AnnotationDeveloperProvider;
import fr.sii.survival.core.ext.registry.AutoDiscoveryExtensionRegistry;
import fr.sii.survival.core.ext.registry.ExtensionRegistry;
import fr.sii.survival.core.service.extension.DelegateExtensionService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.mock.ext.BasicEnemyExtension;

@RunWith(MockitoJUnitRunner.class)
public class AutoDiscoveryExtensionRegistryTest {

	ExtensionService extensionService;

	ExtensionRegistry registry;

	@Before
	public void setUp() {
		extensionService = new DelegateExtensionService(new AnnotationDeveloperProvider());
		registry = new AutoDiscoveryExtensionRegistry(extensionService, "fr.sii.survival.mock.ext");
	}

	@Test
	public void basicEnemy() {
		List<Class<EnemyExtension>> classes = registry.getEnemyExtensions();
		Assert.assertEquals("should have 1 valid extension", 1, classes.size());
		Class<EnemyExtension> clazz = classes.get(0);
		Assert.assertEquals("class should be "+BasicEnemyExtension.class.getSimpleName(), BasicEnemyExtension.class, clazz);
		Assert.assertEquals("developer should be abaudet", "abaudet", extensionService.getDeveloper(clazz).getNickname());
	}
}
