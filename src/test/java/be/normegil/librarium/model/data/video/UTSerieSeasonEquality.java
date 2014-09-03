package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTSerieSeasonEquality extends AbstractDataEqualityTest<SerieSeason> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> FACTORY = FactoryRepository.get(SerieSeason.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> SERIE_FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> EPISODE_SERIE_FACTORY = FactoryRepository.get(EpisodeSerie.class);

	@Override
	protected SerieSeason getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentSerie() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		entity.setSerie(SERIE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentSeasonNumber() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		entity.setSeasonNumber(entity.getSeasonNumber() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentEpisode() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		entity.addEpisode(EPISODE_SERIE_FACTORY.getNext());
		assertEquals(entity, copy);
	}
}