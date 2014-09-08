package be.normegil.librarium.model.dao.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.game.GameSerie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTGameSerieDatabaseDAO extends AbstractDAOTest<GameSerieDatabaseDAO, GameSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected GameSerieDatabaseDAO initDAO() {
		return new GameSerieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final GameSerie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final GameSerie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final GameSerie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected GameSerie getNewData() {
		return FACTORY.getNext();
	}
}
