package be.normegil.librarium.model.data.video;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTEpisodeSerieComparator extends AbstractDataComparableTest<EpisodeSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);

	@Override
	protected EpisodeSerie getNewEntity() {
		return FACTORY.getDefault();
	}

	@Override
	protected int compare(final EpisodeSerie entity1, final EpisodeSerie entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testEpisodeNumber_First() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		copy.setEpisodeNumber(entity.getEpisodeNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testEpisodeNumber_Second() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		copy.setEpisodeNumber(entity.getEpisodeNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testSeason_First() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		copy.setSeason(SERIE_SEASON_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testSeason_Second() throws Exception {
		EpisodeSerie entity = getEntity();
		EpisodeSerie copy = new EpisodeSerie(entity);
		copy.setSeason(SERIE_SEASON_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
