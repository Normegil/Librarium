package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTManga {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> FACTORY = FactoryRepository.get(Manga.class);
	private Manga entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Manga copy = new Manga(entity);
		assertEquals(entity, copy);
	}
}
