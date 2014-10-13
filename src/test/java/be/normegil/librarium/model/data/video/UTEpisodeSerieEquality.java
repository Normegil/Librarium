package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTEpisodeSerieEquality extends AbstractDataEqualityTest<EpisodeSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);

	@Override
	protected EpisodeSerie getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentEpisodeNumber() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		entity.setEpisodeNumber(entity.getEpisodeNumber() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentSeason() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		entity.setSeason(SERIE_SEASON_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}
}