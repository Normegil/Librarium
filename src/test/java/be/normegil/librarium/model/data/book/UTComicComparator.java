package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.Constants;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTComicComparator extends AbstractDataComparableTest<Comic> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Comic> FACTORY = FactoryRepository.get(Comic.class);

	@Override
	protected Comic getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Comic entity1, final Comic entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Comic entity = getEntity();
		Comic copy = new Comic(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}
}
