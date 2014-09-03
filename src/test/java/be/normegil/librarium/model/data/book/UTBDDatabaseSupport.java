package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractEntityDatabaseSupportTest;

public class UTBDDatabaseSupport extends AbstractEntityDatabaseSupportTest<BD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BD> FACTORY = FactoryRepository.get(BD.class);

	public UTBDDatabaseSupport() {
		super(BD.class);
	}

	@Override
	protected BD initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected BD changeEntity(final BD entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}