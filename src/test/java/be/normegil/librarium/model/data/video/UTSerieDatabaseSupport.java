package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTSerieDatabaseSupport extends AbstractEntityDatabaseSupportTest<Serie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> FACTORY = FactoryRepository.get(Serie.class);

	public UTSerieDatabaseSupport() {
		super(Serie.class);
	}

	@Override
	protected Serie initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected Serie changeEntity(final Serie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}