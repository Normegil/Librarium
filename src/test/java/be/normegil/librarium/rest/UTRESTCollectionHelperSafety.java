package be.normegil.librarium.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.List;

public class UTRESTCollectionHelperSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final Class<RESTCollectionHelper> CLASS = new Class<>(RESTCollectionHelper.class);
	private static final int DEFAULT_LIMIT = 30;
	private static final int NUMBER_OF_ELEMENTS = DEFAULT_LIMIT * 2;
	private static final long DEFAULT_OFFSET = 5L;
	private DAO<Game> dao;
	private RESTCollectionHelper entity;

	@Before
	public void setUp() throws Exception {
		dao = new GameTestDAO(NUMBER_OF_ELEMENTS);
		entity = new RESTCollectionHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
		dao = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetCollectionResource_NullEntityList() throws Exception {
		Validator.validate(entity, getGetCollectionResourceMethod(), null, URL_FACTORY.getNext(), dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetCollectionResource_NullBaseURL() throws Exception {
		Validator.validate(entity, getGetCollectionResourceMethod(), dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT), null, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetCollectionResource_NullOffset() throws Exception {
		Validator.validate(entity, getGetCollectionResourceMethod(), dao, URL_FACTORY.getNext(), dao.getNumberOfElements(), null, DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetCollectionResource_NullLimit() throws Exception {
		Validator.validate(entity, getGetCollectionResourceMethod(), dao, URL_FACTORY.getNext(), dao.getNumberOfElements(), DEFAULT_OFFSET, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullEntityList() throws Exception {
		Validator.validate(entity, getConvertToURLsMethod(), null, URL_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullURL() throws Exception {
		Validator.validate(entity, getConvertToURLsMethod(), dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT), null);
	}

	private Method getConvertToURLsMethod() {
		return CLASS.getMethod("convertToURLs", List.class, URL.class);
	}

	private Method getGetCollectionResourceMethod() {
		return CLASS.getMethod("getCollectionResouurce", List.class, URL.class, Long.class, Long.class, Integer.class);
	}
}