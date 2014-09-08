package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMediaComparator extends AbstractDataComparableTest<Media> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	protected Media getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Media entity1, final Media entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testUniverse_First() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		copy.addUniverse(UNIVERSE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testUniverse_Second() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		copy.addUniverse(UNIVERSE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testSupport_Equality() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		copy.addSupport(SUPPORT_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testReleaseDate_Equality() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		copy.addReleaseDate(SUPPORT_FACTORY.getNext(), LocalDate.now());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
