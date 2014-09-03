package be.normegil.librarium.model.data.people;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import be.normegil.librarium.tool.test.AbstractDataComparatorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTEnterpriseComparator extends AbstractDataComparableTest<Enterprise> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> FACTORY = FactoryRepository.get(Enterprise.class);

	@Override
	protected Enterprise getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Enterprise entity1, final Enterprise entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Enterprise entity = getEntity();
		Enterprise copy = new Enterprise(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}
}
