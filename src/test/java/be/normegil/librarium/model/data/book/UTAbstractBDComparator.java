package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTAbstractBDComparator extends AbstractDataComparableTest<AbstractBD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> FACTORY = FactoryRepository.get(AbstractBD.class);

	@Override
	protected AbstractBD getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final AbstractBD entity1, final AbstractBD entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		AbstractBD entity = getEntity();
		AbstractBD copy = new FakeAbstractBD(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testIssueNumber_First() throws Exception {
		AbstractBD entity = getEntity();
		AbstractBD copy = new FakeAbstractBD(entity);
		copy.setIssueNumber(entity.getIssueNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testIssueNumber_Second() throws Exception {
		AbstractBD entity = getEntity();
		AbstractBD copy = new FakeAbstractBD(entity);
		copy.setIssueNumber(entity.getIssueNumber() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
