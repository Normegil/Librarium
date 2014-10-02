package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeEntity;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UTEntityDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	@Mock
	private RESTHelper helper;

	private Entity.EntityDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new Entity.EntityDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFromBase() throws Exception {
		URI baseUri = URI.create(REST_URI);
		Entity e = ENTITY_FACTORY.getNext();
		entity.fromBase(baseUri, e);
		URI expected = URI.create(REST_URI + Constants.URL.PATH_SEPARATOR + e.getId());
		when(helper.getRESTUri(baseUri, FakeEntity.class, e))
				.thenReturn(expected);
		assertEquals(expected, entity.uri);
	}
}
