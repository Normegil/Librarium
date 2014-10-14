package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class UTEntityHelper {

	public static final String BASE_URI = "http://localhost:8080/rest/game/";
	public static final String REST_URI_WITH_ID = BASE_URI + EntityTestSuite.ID;
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private Entity.Helper entity;

	@Before
	public void setUp() throws Exception {
		entity = Entity.helper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testConvertToURLs() throws Exception {
		URL baseURL = URL_FACTORY.getNew();

		List<Entity> entities = new ArrayList<>();
		entities.add(ENTITY_FACTORY.getNew(true, true));
		entities.add(ENTITY_FACTORY.getNew(true, true));
		entities.add(ENTITY_FACTORY.getNew(true, true));

		List<URL> expected = new ArrayList<>();
		for (Entity entity1 : entities) {
			expected.add(baseURL.addToPath(Constants.URL.PATH_SEPARATOR + entity1.getId()));
		}

		List<URL> toTest = entity.convertToURLs(entities, baseURL);

		assertEquals(expected, toTest);
	}

	@Test
	public void testConvertToURLs_EmptyList() throws Exception {
		URL baseURL = URL_FACTORY.getNew();
		List<URL> toTest = entity.convertToURLs(new ArrayList<>(), baseURL);
		assertTrue(toTest.isEmpty());
	}

	@Test
	public void testGetIdFromRESTURI() throws Exception {
		UUID toTest = entity.getIdFromRESTURI(URI.create(REST_URI_WITH_ID));
		assertEquals(EntityTestSuite.ID, toTest);
	}

	@Test
	public void testSetIdFromDigest() throws Exception {
		UUID expected = entity.getIdFromRESTURI(URI.create(REST_URI_WITH_ID));
		Entity e = ENTITY_FACTORY.getNew();
		Entity.EntityDigest digest = new Entity.EntityDigest();
		digest.href = URI.create(REST_URI_WITH_ID);
		entity.setIdFromDigest(digest, e);
		assertEquals(expected, e.getId());
	}

	@Test
	public void testSetIdFromDigest_NullURI() throws Exception {
		Entity e = ENTITY_FACTORY.getNew();
		Entity.EntityDigest digest = new Entity.EntityDigest();
		entity.setIdFromDigest(digest, e);
		assertNull(e.getId());
	}
}
