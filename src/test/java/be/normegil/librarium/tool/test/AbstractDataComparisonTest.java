package be.normegil.librarium.tool.test;

import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDataComparisonTest<E> {

	private static final PropertyComparatorHelper COMPARATOR_HELPER = new PropertyComparatorHelper();

	private E entity;

	protected abstract E getNewEntity();

	protected abstract int compare(E entity1, E entity2);

	@Before
	public void setUp() throws Exception {
		entity = getNewEntity();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public abstract void testEquality() throws Exception;

	@Test
	public abstract void testNull_First() throws Exception;

	@Test
	public abstract void testNull_Second() throws Exception;

	@Test
	public abstract void testBothNull() throws Exception;

	protected E getEntity() {
		return entity;
	}

	protected PropertyComparatorHelper getComparatorHelper() {
		return COMPARATOR_HELPER;
	}

}
