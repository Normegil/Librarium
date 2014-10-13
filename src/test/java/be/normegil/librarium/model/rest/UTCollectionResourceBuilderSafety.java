package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UTCollectionResourceBuilderSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final ClassWrapper<CollectionResource.Builder> CLASS = new ClassWrapper<>(CollectionResource.Builder.class);
	private static final Method BUILD_METHOD = CLASS.getMethod("build");
	private static final Method SET_OFFSET_METHOD = CLASS.getMethod("setOffset", long.class);
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
	public void testSetOffset_Negative() throws Exception {
		Validator.validate(entity, SET_OFFSET_METHOD, -1L);
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
	public void testSetBaseURL_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setBaseURL", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetTotalNumberOfItems_Negative() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setTotalNumberOfItems", long.class), -1L);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllItems_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllItems", List.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddItem_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addItem", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuild_Empty() throws Exception {
		Validator.validate(getConstructorFromBuilder(), entity);
		Validator.validate(entity, BUILD_METHOD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuild_OffsetHigherThanNumberOfElements() throws Exception {
		List<URL> urls = new ArrayList<>();
		urls.add(URL_FACTORY.getNew());
		urls.add(URL_FACTORY.getNew());
		urls.add(URL_FACTORY.getNew());
		URL baseURL = URL_FACTORY.getNew();

		entity
				.addAllItems(urls)
				.setOffset(2L)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(baseURL)
				.setTotalNumberOfItems(1L);

		entity.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuild_OffsetEqualsToNumberOfElementsWhenNotZero() throws Exception {
		List<URL> urls = new ArrayList<>();
		urls.add(URL_FACTORY.getNew());
		urls.add(URL_FACTORY.getNew());
		urls.add(URL_FACTORY.getNew());
		URL baseURL = URL_FACTORY.getNew();

		entity
				.addAllItems(urls)
				.setOffset(2L)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(baseURL)
				.setTotalNumberOfItems(2L);

		entity.build();
	}

	private Constructor<CollectionResource> getConstructorFromBuilder() {
		return new ClassWrapper<>(CollectionResource.class).getConstructor(CollectionResource.Init.class);
	}
}