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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UTCollectionComparator {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
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
	public void testCompareSameCollection_Ordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.EQUALS, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareSameCollection_Unordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link2);
		list2.add(link1);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.EQUALS, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_Ordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_Unordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));
		list2.add(link1);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_Ordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew();
		DownloadLink link2 = FACTORY.getNew();

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);
		list1.add(FACTORY.getNew(false, false));

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_Unordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link2);
		list1.add(FACTORY.getNew(false, false));
		list1.add(link1);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_SameNumberOfElements_Ordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);
		list1.add(FACTORY.getNew(false, false));

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PriorityFirst_SameNumberOfElements_Unordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(FACTORY.getNew(false, false));
		list1.add(link2);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));
		list2.add(link1);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_FIRST, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_SameNumberOfElements_Ordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link1);
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(link1);
		list1.add(link2);
		list1.add(FACTORY.getNew(false, false));

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compare(list1, list2)));
	}

	@Test
	public void testCompareCollection_PrioritySecond_SameNumberOfElements_Unordered() throws Exception {
		DownloadLink link1 = FACTORY.getNew(false, false);
		DownloadLink link2 = FACTORY.getNew(false, false);

		List<DownloadLink> list2 = new ArrayList<>();
		list2.add(link2);
		list2.add(FACTORY.getNew(false, false));
		list2.add(link1);

		List<DownloadLink> list1 = new ArrayList<>();
		list1.add(FACTORY.getNew(false, false));
		list1.add(link1);
		list1.add(link2);

		assertTrue(COMPARATOR_HELPER.testComparatorResult(Constants.Comparator.PRIORITY_SECOND, entity.compare(list1, list2)));
	}
}
