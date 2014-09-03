package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTSerieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> SERIE_FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	private Serie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Serie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Serie serie = SERIE_FACTORY.getNext();
		Serie copy = entity.from(serie).build();
		assertEquals(serie, copy);
	}

	@Test
	public void testAddAllSeasons() throws Exception {
		Collection<SerieSeason> toAdd = new HashSet<>();
		toAdd.add(SERIE_SEASON_FACTORY.getNext());
		toAdd.add(SERIE_SEASON_FACTORY.getNext());
		toAdd.add(SERIE_SEASON_FACTORY.getNext());

		Serie serie = entity
				.addAllSeasons(toAdd)
				.build();

		assertTrue(serie.getSeasons().containsAll(toAdd));
	}

	@Test
	public void testAddSeason() throws Exception {
		SerieSeason season = SERIE_SEASON_FACTORY.getNext();
		Serie serie = entity
				.addSeason(season)
				.build();

		assertTrue(serie.getSeasons().contains(season));
	}
}