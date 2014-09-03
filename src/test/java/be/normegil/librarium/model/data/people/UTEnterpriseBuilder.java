package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTEnterpriseBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> ENTERPRISE_FACTORY = FactoryRepository.get(Enterprise.class);
	private Enterprise.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Enterprise.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Enterprise enterprise = ENTERPRISE_FACTORY.getNext();
		Enterprise copy = entity.from(enterprise).build();
		assertEquals(enterprise, copy);
	}
}