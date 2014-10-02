package be.normegil.librarium.model.data.game;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UTGameDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	@Mock
	private RESTHelper helper;

	private Game.GameDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new Game.GameDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFromBase_Serie() throws Exception {
		Game game = callFromBase();
		URI expected = new RESTHelper().getRESTUri(URI.create(REST_URI), GameSerie.class, game.getSerie());
		assertEquals(expected, entity.serie);
	}

	private Game callFromBase() {
		URI baseUri = URI.create(REST_URI);
		Game game = GAME_FACTORY.getNext();
		entity.fromBase(baseUri, game);
		URI expected = URI.create(REST_URI + Constants.URL.PATH_SEPARATOR + game.getId());
		when(helper.getRESTUri(baseUri, Game.class, game))
				.thenReturn(expected);
		return game;
	}

	@Test
	public void testToBase_Serie() throws Exception {
		Game game = callToBase();
		URI toTest = new RESTHelper().getRESTUri(URI.create(REST_URI), Universe.class, game.getSerie());
		assertEquals(entity.serie, toTest);
	}

	public Game callToBase() throws Exception {
		Game.Builder builder = Game.builder();
		Game media = GAME_FACTORY.getNext();
		entity.fromBase(URI.create(REST_URI), media);
		entity.toBase(builder);
		return builder.build();
	}

}
