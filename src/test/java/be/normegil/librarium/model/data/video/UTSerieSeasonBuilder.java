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

public class UTSerieSeasonBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> SERIE_FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> EPISODE_SERIE_FACTORY = FactoryRepository.get(EpisodeSerie.class);
	private SerieSeason.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = SerieSeason.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		SerieSeason serieSeason = SERIE_SEASON_FACTORY.getNew();
		SerieSeason copy = entity.from(serieSeason).build();
		assertEquals(serieSeason, copy);
	}

	@Test
	public void testSetSerie() throws Exception {
		Serie serie = SERIE_FACTORY.getNew();
		SerieSeason serieSeason = entity
				.setSerie(serie)
				.build();
		assertEquals(serie, serieSeason.getSerie());
	}

	@Test
	public void testSetSeasonNumber() throws Exception {
		long seasonNumber = 1L;
		SerieSeason serieSeason = entity
				.setSeasonNumber(seasonNumber)
				.build();
		assertEquals(seasonNumber, (Object) serieSeason.getSeasonNumber());
	}

	@Test
	public void testAddAllEpisodes() throws Exception {
		Collection<EpisodeSerie> toAdd = new HashSet<>();
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());

		SerieSeason serieSeason = entity
				.addAllEpisodes(toAdd)
				.build();

		assertTrue(serieSeason.getEpisodes().containsAll(toAdd));
	}

	@Test
	public void testAddEpisode() throws Exception {
		EpisodeSerie episodeSerie = EPISODE_SERIE_FACTORY.getNew();
		SerieSeason serieSeason = entity
				.addEpisode(episodeSerie)
				.build();

		assertTrue(serieSeason.getEpisodes().contains(episodeSerie));
	}
}