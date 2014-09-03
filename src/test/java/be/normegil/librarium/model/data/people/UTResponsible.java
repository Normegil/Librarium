package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTResponsible {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String ALTERNATIVE_NAME = "AlternativeName";
	private Responsible entity;

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
		Responsible copy = new FakeResponsible(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetName() throws Exception {
		entity.setName(ALTERNATIVE_NAME);
		assertEquals(ALTERNATIVE_NAME, entity.getName());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL url = URL_FACTORY.getNext();
		entity.setWikipediaPage(url);
		assertEquals(url, entity.getWikipediaPage());
	}
}
