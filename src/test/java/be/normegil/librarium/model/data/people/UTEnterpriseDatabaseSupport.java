package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTEnterpriseDatabaseSupport extends AbstractEntityDatabaseSupportTest<Enterprise> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> FACTORY = FactoryRepository.get(Enterprise.class);

	public UTEnterpriseDatabaseSupport() {
		super(Enterprise.class);
	}

	@Override
	protected Enterprise initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Enterprise changeEntity(final Enterprise entity) {
		entity.setName(entity.getName() + 1);
		return entity;
	}
}