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

public class UTResponsibleBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String NAME = "Name";
	private FakeResponsible.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeResponsible.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		Responsible copy = entity.from(responsible).build();
		assertEquals(responsible, copy);
	}

	@Test
	public void testSetName() throws Exception {
		Responsible responsible = entity
				.setName(NAME)
				.build();
		assertEquals(NAME, responsible.getName());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL url = URL_FACTORY.getNew();
		Responsible responsible = entity
				.setWikipediaPage(url)
				.build();
		assertEquals(url, responsible.getWikipediaPage());
	}
}