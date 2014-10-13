package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URI;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UTEntityDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String REST_URI = "http://localhost:8080/rest";

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
		Entity e = GAME_FACTORY.getNew();
		new EntityHelper().setId(e, UUID.randomUUID());
		entity.fromBase(baseUri, e);
		URI expected = URI.create(REST_URI + Constants.URL.PATH_SEPARATOR + new RESTHelper().getPathFor(e.getClass()) + Constants.URL.PATH_SEPARATOR + e.getId());
		assertEquals(expected, entity.href);
	}
}
