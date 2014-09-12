package be.normegil.librarium.model.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTCollectionResourceBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<CollectionResource> FACTORY = FactoryRepository.get(CollectionResource.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long DEFAULT_OFFSET = 5L;
	private static final int DEFAULT_LIMIT = 30;
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
	public void testFrom() throws Exception {
		CollectionResource resource = FACTORY.getNext(true);
		CollectionResource copy = entity.from(resource).build();
		assertEquals(resource, copy);
	}

	@Test
	public void testSetOffset() throws Exception {
		CollectionResource collectionResource = entity
				.setOffset(5L)
				.build();
		assertEquals((Long) DEFAULT_OFFSET, collectionResource.getOffset());
	}

	@Test
	public void testSetLimit() throws Exception {
		CollectionResource collectionResource = entity
				.setLimit(30)
				.build();
		assertEquals(DEFAULT_LIMIT, collectionResource.getLimit());
	}

	@Test
	public void testSetFirst() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setFirst(url)
				.build();
		assertEquals(url, collectionResource.getURLToFirstPage());
	}

	@Test
	public void testSetPrevious() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setPrevious(url)
				.build();
		assertEquals(url, collectionResource.getURLToPreviousPage());
	}

	@Test
	public void testSetNext() throws Exception {
		CollectionResource collectionResource = entity
				.setNext(URL_FACTORY.getNext())
				.build();
		assertEquals(URL_FACTORY.getNext(), collectionResource.getURLToNextPage());
	}

	@Test
	public void testSetLast() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.setLast(url)
				.build();
		assertEquals(url, collectionResource.getURLToLastPage());
	}

	@Test
	public void testAddAllItems() throws Exception {
		List<URL> toAdd = new ArrayList<>();
		toAdd.add(URL_FACTORY.getNext());
		toAdd.add(URL_FACTORY.getNext());
		toAdd.add(URL_FACTORY.getNext());

		CollectionResource collectionResource = entity
				.addAllItems(toAdd)
				.build();

		assertTrue(collectionResource.getItems().containsAll(toAdd));
	}

	@Test
	public void testAddItem() throws Exception {
		URL url = URL_FACTORY.getNext();
		CollectionResource collectionResource = entity
				.addItem(url)
				.build();

		assertTrue(collectionResource.getItems().contains(URL_FACTORY.getNext()));
	}
}