package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTReleaseDateComparator extends AbstractDataComparableTest<ReleaseDate> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> FACTORY = FactoryRepository.get(ReleaseDate.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	protected ReleaseDate getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final ReleaseDate entity1, final ReleaseDate entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testMedia_First() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate otherEntity = new ReleaseDate(MEDIA_FACTORY.getNext(), entity.getSupport(), entity.getDate());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testMedia_Second() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate otherEntity = new ReleaseDate(MEDIA_FACTORY.getNext(), entity.getSupport(), entity.getDate());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testSupport_First() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate otherEntity = new ReleaseDate(entity.getMedia(), SUPPORT_FACTORY.getNext(), entity.getDate());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testSupport_Second() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate otherEntity = new ReleaseDate(entity.getMedia(), SUPPORT_FACTORY.getNext(), entity.getDate());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testDate_First() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity);
		copy.setDate(entity.getDate().plusDays(1));
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testDate_Second() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity);
		copy.setDate(entity.getDate().plusDays(1));
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
