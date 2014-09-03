package be.normegil.librarium.util;

import be.normegil.librarium.Constants;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UTCollectionComparatorSafety {

	private static final PropertyComparatorHelper COMPARATOR_HELPER = new PropertyComparatorHelper();
	private CollectionComparator entity;

	@Before
	public void setUp() throws Exception {
		entity = new CollectionComparator();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCompare_NullBothCollections() throws Exception {
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.EQUALS, entity.compare(null, null)));
	}

	@Test
	public void testCompare_NullFirstCollection() throws Exception {
		Collection collection = new ArrayList();
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compare(null, collection)));
	}

	@Test
	public void testCompare_NullSecondCollection() throws Exception {
		Collection collection = new ArrayList();
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compare(collection, null)));
	}

	@Test
	public void testCompareMaps_NullBothMaps() throws Exception {
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.EQUALS, entity.compareMaps(null, null)));
	}


	@Test
	public void testCompareMaps_NullFirstMap() throws Exception {
		Map map = new HashMap();
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(null, map)));
	}

	@Test
	public void testCompareMaps_NullSecondMap() throws Exception {
		Map map = new HashMap();
		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compareMaps(map, null)));
	}
}