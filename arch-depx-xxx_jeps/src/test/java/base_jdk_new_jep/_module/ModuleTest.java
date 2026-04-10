package base_jdk_new_jep._module;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Exports;
import java.lang.module.ModuleDescriptor.Opens;
import java.lang.module.ModuleDescriptor.Provides;
import java.lang.module.ModuleDescriptor.Requires;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModuleTest {

	@Test
	void testName() throws Exception {
		Module javaBaseModule = HashMap.class.getModule();
		Assertions.assertEquals(javaBaseModule.isNamed(), true);
		Assertions.assertEquals(javaBaseModule.getName(), "java.base");

		ModuleLayer javaBaseModuleLayer = javaBaseModule.getLayer();
		assertTrue(javaBaseModuleLayer.configuration().findModule("java.base").isPresent());

		ModuleDescriptor moduleDescriptor = javaBaseModule.getDescriptor();
		Assertions.assertFalse(moduleDescriptor.isAutomatic());// false
		Assertions.assertFalse(moduleDescriptor.isOpen());

		ModuleDescriptor.Builder moduleBuilder = ModuleDescriptor.newModule("baeldung.base");
		ModuleDescriptor moduleDescriptor2 = moduleBuilder.build();

		Assertions.assertEquals(moduleDescriptor2.name(), "baeldung.base");

		Set<Requires> javaBaseRequires = javaBaseModule.getDescriptor().requires();
		Set<String> javaBaseRequiresNames = javaBaseRequires.stream()//
				.map(Requires::name)//
				.collect(Collectors.toSet());

		Set<Provides> javaBaseProvides = javaBaseModule.getDescriptor().provides();
		Set<String> javaBaseProvidesService = javaBaseProvides.stream()//
				.map(Provides::service)//
				.collect(Collectors.toSet());

		Set<Exports> javaBaseExports = javaBaseModule.getDescriptor().exports();
		Set<String> javaBaseExportsSource = javaBaseExports.stream()//
				.map(Exports::source)//
				.collect(Collectors.toSet());

		Module javaSqlModule = Driver.class.getModule();
		Set<String> javaSqlUses = javaSqlModule.getDescriptor().uses();

		Set<Opens> javaBaseOpens = javaBaseModule.getDescriptor().opens();
		Set<Opens> javaSqlOpens = javaSqlModule.getDescriptor().opens();

	}

}
