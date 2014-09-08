package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTResponsibleComparator extends AbstractDataComparableTest<Responsible> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);

	@Override
	protected Responsible getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Responsible entity1, final Responsible entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testName_First() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testName_Second() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testWikipediaPage_First() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testWikipediaPage_Second() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
