package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public abstract class AbstractDataComparatorTest<E> extends AbstractDataComparisonTest<E> {

	private Comparator<E> comparator;

	protected abstract Comparator<E> getComparator();

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		comparator = getComparator();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		comparator = null;
		super.tearDown();
	}

	protected int compare(E entity1, E entity2) {
		return comparator.compare(entity1, entity2);
	}

	@Test
	public void testNull_First() throws Exception {
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(null, getEntity())));
	}

	@Test
	public void testNull_Second() throws Exception {
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(getEntity(), null)));
	}

	@Test
	public void testBothNull() throws Exception {
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(null, null)));
	}
}
