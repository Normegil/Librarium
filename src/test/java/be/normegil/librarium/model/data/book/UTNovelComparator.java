package be.normegil.librarium.model.data.book;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTNovelComparator extends AbstractDataComparableTest<Novel> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Novel> FACTORY = FactoryRepository.get(Novel.class);

	@Override
	protected Novel getNewEntity() {
		return FACTORY.getDefault();
	}

	@Override
	protected int compare(final Novel entity1, final Novel entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Novel entity = getEntity();
		Novel copy = new Novel(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}
}
