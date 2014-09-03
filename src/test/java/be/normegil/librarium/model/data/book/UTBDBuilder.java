package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTBDBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BD> BD_FACTORY = FactoryRepository.get(BD.class);
	private BD.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = BD.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		BD bd = BD_FACTORY.getNext();
		BD copy = entity.from(bd).build();
		assertEquals(bd, copy);
	}
}