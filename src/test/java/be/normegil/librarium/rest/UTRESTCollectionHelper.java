package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UTRESTCollectionHelper {

	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;
	private DAO<Game> dao;
	private RESTCollectionHelper entity;

	@Before
	public void setUp() throws Exception {
		dao = new GameTestDAO(DEFAULT_LIMIT * 2);
		entity = new RESTCollectionHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
		dao = null;
	}

	@Test
	public void testGetCollectionResource() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_EmptyList() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanOffset() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_NullOffset() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_NullLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testConvertToURLs() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testConvertToURLs_EmptyList() throws Exception {
		throw new NotImplementedException();
	}
}
