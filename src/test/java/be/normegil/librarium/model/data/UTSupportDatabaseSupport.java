package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTSupportDatabaseSupport extends AbstractEntityDatabaseSupportTest<Support> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);

	public UTSupportDatabaseSupport() {
		super(Support.class);
	}

	@Override
	protected Support initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Support changeEntity(final Support entity) {
		entity.setName(entity.getName() + 1);
		return entity;
	}
}