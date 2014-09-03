package be.normegil.librarium.model.data.video;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTSerieSeasonComparator extends AbstractDataComparableTest<SerieSeason> {

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

	@Override
	protected int compare(final SerieSeason entity1, final SerieSeason entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testSerie_First() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		copy.setSerie(SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testSerie_Second() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		copy.setSerie(SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testSeasonNumber_First() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		copy.setSeasonNumber(entity.getSeasonNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testSeasonNumber_Second() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		copy.setSeasonNumber(entity.getSeasonNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testEpisode_Equality() throws Exception {
		SerieSeason entity = getEntity();
		SerieSeason copy = new SerieSeason(entity);
		copy.addEpisode(EPISODE_SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
