package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTComicBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Comic> COMIC_FACTORY = FactoryRepository.get(Comic.class);
	private Comic.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Comic.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Comic comic = COMIC_FACTORY.getNext();
		Comic copy = entity.from(comic).build();
		assertEquals(comic, copy);
	}
}