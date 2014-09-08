package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.Constants;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public abstract class AbstractDataComparableTest<E> extends AbstractDataComparisonTest<E> {

	/**
	 * Make no Sense when using Comparable interface
	 */
	@Test
	public void testNull_First() throws Exception {
	}

	/**
	 * Make no Sense when using Comparable interface
	 */
	@Test
	public void testBothNull() throws Exception {
	}

	@Test
	public void testNull_Second() throws Exception {
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(getEntity(), null)));
	}


}
