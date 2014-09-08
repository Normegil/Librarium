package be.normegil.librarium.model.data.book;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBDComparator extends AbstractDataComparableTest<BD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BD> FACTORY = FactoryRepository.get(BD.class);

	@Override
	protected BD getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final BD entity1, final BD entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		BD entity = getEntity();
		BD copy = new BD(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}
}
