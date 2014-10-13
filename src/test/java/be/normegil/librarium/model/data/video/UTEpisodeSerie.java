package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTEpisodeSerie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	private EpisodeSerie entity;

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
		EpisodeSerie copy = new EpisodeSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetEpisodeNumber() throws Exception {
		long episodeNumber = entity.getEpisodeNumber() + 1;
		entity.setEpisodeNumber(episodeNumber);
		assertEquals(episodeNumber, (Object) entity.getEpisodeNumber());
	}

	@Test
	public void testSetSeason() throws Exception {
		SerieSeason season = SERIE_SEASON_FACTORY.getNew();
		entity.setSeason(season);
		assertEquals(season, entity.getSeason());
	}
}
