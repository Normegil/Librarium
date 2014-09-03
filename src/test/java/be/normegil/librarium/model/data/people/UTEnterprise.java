package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTEnterprise {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> FACTORY = FactoryRepository.get(Enterprise.class);
	private Enterprise entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Enterprise copy = new Enterprise(entity);
		assertEquals(entity, copy);
	}
}
