package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTMangaBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> MANGA_FACTORY = FactoryRepository.get(Manga.class);
	private Manga.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Manga.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Manga manga = MANGA_FACTORY.getNew();
		Manga copy = entity.from(manga).build();
		assertEquals(manga, copy);
	}
}