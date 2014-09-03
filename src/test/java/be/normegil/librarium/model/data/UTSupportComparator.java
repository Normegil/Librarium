package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import be.normegil.librarium.tool.test.AbstractDataComparatorTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTSupportComparator extends AbstractDataComparableTest<Support> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	protected Support getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Support entity1, final Support entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testName_First() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testName_Second() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testWikipediaPage_First() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testWikipediaPage_Second() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testMedia_Equality() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		copy.addMedia(MEDIA_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
