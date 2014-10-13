package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTGameSerieDatabaseSupport extends AbstractEntityDatabaseSupportTest<GameSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);

	public UTGameSerieDatabaseSupport() {
		super(GameSerie.class);
	}

	@Override
	protected GameSerie initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected GameSerie changeEntity(final GameSerie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}