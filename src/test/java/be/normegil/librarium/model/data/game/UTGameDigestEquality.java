package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DatabaseDAO;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.net.URI;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTGameDigestEquality extends AbstractDataEqualityTest<Game.GameDigest> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);

	@Override
	protected Game.GameDigest getNewEntity() {
		return new Game.GameDigest();
	}

	@Test
	public void testUnchanged() throws Exception {
		Game entity = getGame();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		Game.GameDigest digest1 = new Game.GameDigest();
		Game.GameDigest digest2 = new Game.GameDigest();
		digest1.fromBase(baseURI, entity);
		digest2.fromBase(baseURI, entity);
		assertEquals(digest1, digest2);
	}

	@Test
	public void testDifferentSeries() throws Exception {
		Game entity = getGame();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		Game.GameDigest digest1 = new Game.GameDigest();
		Game.GameDigest digest2 = new Game.GameDigest();
		digest1.fromBase(baseURI, entity);
		GameSerie serie = GAME_SERIE_FACTORY.getNext();
		new EntityHelper().setId(serie, UUID.randomUUID());
		entity.setSerie(serie);
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	private Game getGame() {
		Game game = GAME_FACTORY.getNext();
		new EntityHelper().setId(game.getSerie(), UUID.randomUUID());
		return game;
	}

	@Test
	public void testDifferentGameSerieDAO() throws Exception {
		Game.GameDigest digest1 = new Game.GameDigest();
		Game.GameDigest digest2 = new Game.GameDigest();
		digest1.setGameSerieDAO(new DatabaseDAO<>(GameSerie.class));
		digest2.setGameSerieDAO(new DatabaseDAO<>(GameSerie.class));
		assertEquals(digest1, digest2);
	}
}