package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTGameDatabaseSupport extends AbstractEntityDatabaseSupportTest<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);

	public UTGameDatabaseSupport() {
		super(Game.class);
	}

	@Override
	protected Game initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Game changeEntity(final Game entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}