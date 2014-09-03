package be.normegil.librarium.util;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.DownloadLink;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UTMapComparator {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final PropertyComparatorHelper COMPARATOR_HELPER = new PropertyComparatorHelper();
	private static final long SMALLEST_KEY = -1L;
	private static Long index;
	private CollectionComparator entity;

	@Before
	public void setUp() throws Exception {
		entity = new CollectionComparator();
		index = 0L;
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCompareSameCollection() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.EQUALS, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_KeysDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(SMALLEST_KEY, entry2.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_KeysDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());
		map2.put(1000L, entry2.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_SameNumberOfElements_KeysDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());
		map1.put(1000L, entry2.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());
		map2.put(10000L, entry2.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_SameNumberOfElements_KeysDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());
		map1.put(10000L, entry2.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());
		map2.put(1000L, entry2.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_SameNumberOfElements_ValuesDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry3 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());
		map1.put(entry3.getKey(), entry3.getValue());

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());
		map2.put(entry3.getKey(), FACTORY.getNext(false));

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_SameNumberOfElements_ValuesDiffer() throws Exception {
		Map.Entry<Long, DownloadLink> entry1 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry2 = getNewEntry();
		Map.Entry<Long, DownloadLink> entry3 = getNewEntry();

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(entry1.getKey(), entry1.getValue());
		map1.put(entry2.getKey(), entry2.getValue());
		map1.put(entry3.getKey(), FACTORY.getNext(false));

		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(entry1.getKey(), entry1.getValue());
		map2.put(entry2.getKey(), entry2.getValue());
		map2.put(entry3.getKey(), entry3.getValue());

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compareMaps(map1, map2)));
	}

	@Test
	public void testCompareCollection_PriorityKey_KeyDiffer_ValueDiffer() throws Exception {
		Map<Long, DownloadLink> map2 = new HashMap<>();
		map2.put(4L, FACTORY.getNext(false));
		map2.put(5L, FACTORY.getNext(false));
		map2.put(6L, FACTORY.getNext(false));

		Map<Long, DownloadLink> map1 = new HashMap<>();
		map1.put(1L, FACTORY.getNext(false));
		map1.put(2L, FACTORY.getNext(false));
		map1.put(3L, FACTORY.getNext(false));

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compareMaps(map1, map2)));
	}

	private Map.Entry<Long, DownloadLink> getNewEntry() {
		AbstractMap.SimpleEntry<Long, DownloadLink> entry = new AbstractMap.SimpleEntry<>(index, FACTORY.getNext(false));
		index += 1;
		return entry;
	}
}
