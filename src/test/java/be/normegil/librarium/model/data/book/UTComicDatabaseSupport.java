package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTComicDatabaseSupport extends AbstractEntityDatabaseSupportTest<Comic> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Comic> FACTORY = FactoryRepository.get(Comic.class);

	public UTComicDatabaseSupport() {
		super(Comic.class);
	}

	@Override
	protected Comic initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected Comic changeEntity(final Comic entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}