package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.rest.RESTCollectionHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.URLFactory;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UTCollectionResourceBuilderSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final Class<CollectionResource.Builder> CLASS = new Class<>(CollectionResource.Builder.class);
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;
	private static final Long FIRST_OFFSET = 0L;
	private static final Long DEFAULT_OFFSET = FIRST_OFFSET;
	private CollectionResource.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = CollectionResource.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", CollectionResource.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetOffset_Null() throws Exception {
		Validator.validate(entity, getSetOffsetMethod(), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetOffset_Empty() throws Exception {
		Validator.validate(entity, getSetOffsetMethod(), -1L);
	}

	private Method getSetOffsetMethod() {
		return CLASS.getMethod("setOffset", Long.class);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetLimit_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setLimit", int.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetLimit_Zero() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setLimit", int.class), 0);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetLimit_Negative() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setLimit", int.class), -1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetFirst_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setFirst", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetPrevious_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setPrevious", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetNext_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setNext", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetLast_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setLast", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllItems_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllItems", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddItem_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addItem", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuild_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuild_OffsetHigherThanNumberOfElements() throws Exception {
		fillBuilder();
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	private void fillBuilder() {
		List<URL> urls = new ArrayList<>();
		urls.add(URL_FACTORY.getNext());
		urls.add(URL_FACTORY.getNext());
		urls.add(URL_FACTORY.getNext());
		RESTCollectionHelper helper = new RESTCollectionHelper();
		URL baseURL = URL_FACTORY.getNext();
		entity
				.addAllItems(urls)
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setFirst(helper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT))
				.setLast(helper.getCollectionURL(baseURL, helper.getLastOffset(urls.size(), DEFAULT_LIMIT), DEFAULT_LIMIT));
	}
}