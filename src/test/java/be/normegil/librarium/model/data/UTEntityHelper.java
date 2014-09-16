package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
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

public class UTEntityHelper {

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
		URL baseURL = URL_FACTORY.getNext();

		List<Entity> entities = new ArrayList<>();
		entities.add(ENTITY_FACTORY.getNext());
		entities.add(ENTITY_FACTORY.getNext());
		entities.add(ENTITY_FACTORY.getNext());

		List<URL> expected = new ArrayList<>();
		for (Entity entity1 : entities) {
			expected.add(baseURL.addToPath(Constants.URL.PATH_SEPARATOR + entity1.getId()));
		}

		List<URL> toTest = entity.convertToURLs(entities, baseURL);

		assertEquals(expected, toTest);
	}

	@Test
	public void testConvertToURLs_EmptyList() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		List<URL> toTest = entity.convertToURLs(new ArrayList<>(), baseURL);
		assertTrue(toTest.isEmpty());
	}
}
