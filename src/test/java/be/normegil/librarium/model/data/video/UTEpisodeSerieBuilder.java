package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTEpisodeSerieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> EPISODE_SERIE_FACTORY = FactoryRepository.get(EpisodeSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	private EpisodeSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = EpisodeSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		EpisodeSerie episodeSerie = EPISODE_SERIE_FACTORY.getNext();
		EpisodeSerie copy = entity.from(episodeSerie).build();
		assertEquals(episodeSerie, copy);
	}

	@Test
	public void testSetEpisodeNumber() throws Exception {
		long episodeNumber = 1L;
		EpisodeSerie episodeSerie = entity
				.setEpisodeNumber(episodeNumber)
				.build();
		assertEquals(episodeNumber, (Object) episodeSerie.getEpisodeNumber());
	}

	@Test
	public void testSetSeason() throws Exception {
		SerieSeason season = SERIE_SEASON_FACTORY.getNext(true);
		EpisodeSerie episodeSerie = entity
				.setSeason(season)
				.build();
		assertEquals(season, episodeSerie.getSeason());
	}
}