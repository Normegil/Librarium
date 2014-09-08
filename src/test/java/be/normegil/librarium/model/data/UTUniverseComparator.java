package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTUniverseComparator extends AbstractDataComparableTest<Universe> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private static final String NAME = "Name";

	@Override
	protected Universe getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Universe entity1, final Universe entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testName_First() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testName_Second() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		copy.setName(entity.getName() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testDescription_First() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		copy.setDescription(entity.getDescription() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testDescription_Second() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		copy.setDescription(entity.getDescription() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testMedia_Equality() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		copy.addMedia(MEDIA_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
