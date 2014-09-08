package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTUniverseDatabaseSupport extends AbstractEntityDatabaseSupportTest<Universe> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);

	public UTUniverseDatabaseSupport() {
		super(Universe.class);
	}

	@Override
	protected Universe initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected Universe changeEntity(final Universe entity) {
		entity.setName(entity.getName() + 1);
		return entity;
	}
}