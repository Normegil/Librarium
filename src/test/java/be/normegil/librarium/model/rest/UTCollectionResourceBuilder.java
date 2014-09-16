package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.rest.RESTCollectionHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UTCollectionResourceBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long DEFAULT_OFFSET = 5L;
	private static final int DEFAULT_LIMIT = 30;
	private static final int DEFAULT_NUMBER_OF_ITEMS = DEFAULT_LIMIT * 2;
	private static final long FIRST_OFFSET = 0L;
	private CollectionResource.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = CollectionResource.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testSetOffset() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		assertEquals((Long) DEFAULT_OFFSET, collectionResource.getOffset());
	}

	@Test
	public void testOffsetNotPresent() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		assertEquals((Long) FIRST_OFFSET, collectionResource.getOffset());
	}

	@Test
	public void testSetLimit() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		assertEquals(DEFAULT_LIMIT, collectionResource.getLimit());
	}

	@Test
	public void testLimitNotPresent() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		assertEquals(ApplicationProperties.REST.DEFAULT_LIMIT, collectionResource.getLimit());
	}

	@Test
	public void testSetLimit_HigherThanMaximum() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setLimit(ApplicationProperties.REST.MAX_LIMIT + 10)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.setBaseURL(url)
				.build();
		assertEquals(ApplicationProperties.REST.MAX_LIMIT, collectionResource.getLimit());
	}

	@Test
	public void testFirstPageURL() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToFirstPage());
	}

	@Test
	public void testLastPageURL() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setTotalNumberOfItems(DEFAULT_LIMIT + 5)
				.setBaseURL(url)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToLastPage());
	}

	@Test
	public void testLastPageURL_LimitHigherThanTotalNumberOfItems() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setTotalNumberOfItems(DEFAULT_LIMIT - 5)
				.setBaseURL(url)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToLastPage());
	}

	@Test
	public void testLastPageURL_NumberOfItemsMultipleOfLimit() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setTotalNumberOfItems(DEFAULT_LIMIT * 3)
				.setBaseURL(url)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, DEFAULT_LIMIT * 2, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToLastPage());
	}

	@Test
	public void testPreviousPageURL_OffsetHigherThanLimit() throws Exception {
		URL url = URL_FACTORY.getNext();
		long offset = DEFAULT_LIMIT + 5;
		CollectionResource collectionResource = entity
				.setOffset(offset)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, offset - DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToPreviousPage());
	}

	@Test
	public void testPreviousPageURL_OffsetLowerThanLimit() throws Exception {
		URL url = URL_FACTORY.getNext();
		long offset = DEFAULT_LIMIT - 5;
		CollectionResource collectionResource = entity
				.setOffset(offset)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToPreviousPage());
	}

	@Test
	public void testPreviousPageURL_FirstOffset() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(FIRST_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.setBaseURL(url)
				.build();
		assertNull(collectionResource.getURLToPreviousPage());
	}

	@Test
	public void testNextPageURL() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();
		URL expected = new RESTCollectionHelper().getCollectionURL(url, DEFAULT_OFFSET + DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(expected, collectionResource.getURLToNextPage());
	}

	@Test
	public void testNextPageURL_LastPage() throws Exception {
		URL url = URL_FACTORY.getNext();
		long totalNumberOfItems = DEFAULT_LIMIT + 5;
		CollectionResource collectionResource = entity
				.setOffset(DEFAULT_LIMIT)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(totalNumberOfItems)
				.build();
		assertNull(collectionResource.getURLToNextPage());
	}

	@Test
	public void testAddAllItems() throws Exception {
		URL url = URL_FACTORY.getNext();
		List<URL> toAdd = new ArrayList<>();
		toAdd.add(URL_FACTORY.getNext());
		toAdd.add(URL_FACTORY.getNext());
		toAdd.add(URL_FACTORY.getNext());

		CollectionResource collectionResource = entity
				.addAllItems(toAdd)
				.setBaseURL(url)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();

		assertEquals(toAdd, collectionResource.getItems());
	}

	@Test
	public void testAddItem() throws Exception {
		URL url = URL_FACTORY.getNext();
		URL baseUrl = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.addItem(url)
				.setBaseURL(baseUrl)
				.setTotalNumberOfItems(DEFAULT_NUMBER_OF_ITEMS)
				.build();

		assertEquals(1, collectionResource.getItems().size());
		assertTrue(collectionResource.getItems().contains(url));
	}


}